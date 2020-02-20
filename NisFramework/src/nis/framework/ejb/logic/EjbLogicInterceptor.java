package nis.framework.ejb.logic;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;

// LogicExecutorを使わずEJBを直接呼び出すパターンでのサンプル

@Interceptor
@Logic
public class EjbLogicInterceptor {

	@Inject
	private ServiceContext svContext;

	@Inject
	private BeanManager bm;

	@Inject
	private Log log;

	@Resource
	private SessionContext snContext;

	// TODO:ログ処理を追い出す

	@AroundInvoke
	public Object intercept(InvocationContext ic) throws Exception {
		long time = 0;
		if (log.isInfoEnabled()) {
			log.info("START:" + getTarget(ic));
			time = System.currentTimeMillis();
		}
		if (log.isInfoEnabled()) {
			int cnt = 1;
			for (Object parameter : ic.getParameters()) {
				log.info("	PARAM" + cnt + ":" + parameter.toString());
				cnt++;
			}
		}

		Logic logic = ic.getMethod().getAnnotation(Logic.class);

		if (!check(logic.preCheck(), ic.getParameters())) {
			if (log.isInfoEnabled()) {
				log.info("END  :" + getTarget(ic));
				log.info("	TIME: " + (System.currentTimeMillis() - time) + "ms");
				log.info("	STATE:PRE-CHECK-ERROR");
				log.info("	RETURN:" + svContext.getAlerts());
			}
			return error(ic.getMethod().getReturnType().newInstance());
		}

		Object result = null;
		try {
			result = ic.proceed();
		} catch(Exception e) {
			e.printStackTrace();
			svContext.getAlerts().addDanger("ビジネスロジック実行中にエラーが発生しました。");
			svContext.toStatusError();
		} finally {
			if (svContext.isStatusError()) {
				snContext.setRollbackOnly();
				return error(ic.getMethod().getReturnType().newInstance());
			}
		}

		assert result != null;
		if (!check(logic.postCheck(), result)) {
			snContext.setRollbackOnly();
			if (log.isInfoEnabled()) {
				log.info("END  :" + getTarget(ic));
				log.info("	TIME: " + (System.currentTimeMillis() - time) + "ms");
				log.info("	STATE:POST-CHECK-ERROR");
				log.info("	RETURN:" + svContext.getAlerts());
			}
			return error(ic.getMethod().getReturnType().newInstance());
		}

		if (log.isInfoEnabled()) {
			log.info("END  :" + getTarget(ic));
			log.info("	TIME: " + (System.currentTimeMillis() - time) + "ms");
			log.info("	STATE:SUCCESS");
		}

		if (log.isInfoEnabled()) {
			log.info("	RETURN:" + result != null ? result.toString(): "null");
		}

		return success(result);
	}

	private boolean check(Checker[] checkerAns, Object...parameters) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		for (Checker checkerAn : checkerAns) {
			if (!check(checkerAn, parameters)) {
				return false;
			}
		}
		return true;
	}

	private boolean check(Checker checkerAn, Object...parameters) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> checkerClass = checkerAn.value();
		List<Method> checkMethods = new ArrayList<Method>();
		for (Method method : checkerClass.getMethods()) {
			if (method.isAnnotationPresent(CheckLogic.class)) {
				checkMethods.add(method);
			}
		}
		Object checker = resolve(checkerClass);
		Method checkMethod = null;
		if (checkMethods.size() == 1) {
			checkMethod = checkMethods.get(0);
		} else {
			for(Method method : checkMethods) {
				String name = method.getAnnotation(CheckLogic.class).value();
				if (StringUtils.isEmpty(name)) {
					name = method.getName();
				}
				if (checkerAn.name().equals(name)) {
					checkMethod = method;
					break;
				}
			}
			if (checkMethod == null) {
				throw new LogicInterceptException("CheckLogicが見つかりません。");
			}
		}
		List<Object> inParameters = new ArrayList<Object>();
		for (Class<?> clazz : checkMethod.getParameterTypes()) {
			for (Object param : parameters) {
				if (clazz.isAssignableFrom(param.getClass()) && !inParameters.contains(param)) {
					inParameters.add(param);
					break;
				}
			}
		}
		return (boolean)checkMethod.invoke(checker, inParameters.toArray());
	}

	private String getTarget(InvocationContext ic) {
		return ic.getTarget().getClass().getName() + "." + ic.getMethod().getName();
	}

	private Object success(Object result) {
		svContext.toStatusSuccess();
		return result;
	}

	private Object error(Object result) {
		svContext.toStatusError();
		return result;
	}

	private <I> I resolve(Class<I> logic) {
		I bean = resolveByCdi(logic);
		Field[] fields = bean.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			Inject injano = field.getAnnotation(Inject.class);
			if (injano == null) {
			}
			else {
				Object fieldBean = resolveByCdi(field.getType());
				try {
					field.setAccessible(true);
					field.set(bean, fieldBean);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return bean;
	}

	@SuppressWarnings("unchecked")
	private <I> I resolveByCdi(Class<I> logic) {
		Set<Bean<?>> beans = bm.getBeans(logic);
		Bean<?> bean = bm.resolve(beans);
		CreationalContext<?> cc = bm.createCreationalContext(bean);
		return (I)bm.getReference(bean, logic, cc);
	}

}
