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

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.logging.Log;


// TODO:Priority制御

@Interceptor
@Logic
public class LogicInterceptor {

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
		Logic logic = ic.getMethod().getAnnotation(Logic.class);

		if (hasPermission(logic) && !checkPermission(logic)) {
			if (log.isInfoEnabled()) {
				log.info("    STATE:NOT-AUTHORIZED");
			}
			svContext.getAlerts().addDanger("この処理を実行する権限がありません。");
			return error(null);
		}

		if (!check(logic.preCheck(), ic.getParameters())) {
			if (log.isInfoEnabled()) {
				log.info("    STATE:PRE-CHECK-ERROR");
				log.info("    ALERTS:" + ToStringBuilder.reflectionToString(svContext.getAlerts(), ToStringStyle.JSON_STYLE));
			}
			return error(null);
		}

		Object result = null;
		try {
			result = ic.proceed();
		} catch(Exception e) {
			e.printStackTrace();
			// TODO:強制クリアにする？
			//      とりあえず成功だけクリアにする。追加側で細かい制御を出来るようにするべきか。
			svContext.getAlerts().clearSuccess();
			svContext.getAlerts().addDanger("ビジネスロジック実行中に例外エラーが発生しました。処理は中止されます。");
			svContext.toStatusError();
		} finally {
			if (svContext.isStatusError()) {
				return null;
			}
		}

		// TODO:戻り値でnullが返ってきた時の対処をどうするか
		if (result == null) {
			svContext.getAlerts().clearSuccess();
			if (log.isInfoEnabled()) {
				log.info("    STATE:RESULT-IS-NULL");
				log.info("    ALERTS:" + ToStringBuilder.reflectionToString(svContext.getAlerts(), ToStringStyle.JSON_STYLE));
			}
			return error(null);
		}

		if (!check(logic.postCheck(), result)) {
			// TODO:例外キャッチ時と同様
			svContext.getAlerts().clearSuccess();
			if (log.isInfoEnabled()) {
				log.info("    STATE:POST-CHECK-ERROR");
				log.info("    ALERTS:" + ToStringBuilder.reflectionToString(svContext.getAlerts(), ToStringStyle.JSON_STYLE));
			}
			return error(null);
		}

		if (log.isInfoEnabled()) {
			log.info("    STATE:SUCCESS");
			log.info("    ALERTS:" + ToStringBuilder.reflectionToString(svContext.getAlerts(), ToStringStyle.JSON_STYLE));
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
				Object inParam = param;
				if(StringUtils.isNotEmpty(checkerAn.property())) {
					if (checkerAn.property().indexOf(".") == -1) {
						try {
							inParam = PropertyUtils.getProperty(param, checkerAn.property());
						} catch (NoSuchMethodException | SecurityException e) {
						}
					}
					else {
						try {
							inParam = PropertyUtils.getNestedProperty(param, checkerAn.property());
						} catch (NoSuchMethodException | SecurityException e) {
						}
					}
				}
				if (clazz.isAssignableFrom(inParam.getClass()) && !inParameters.contains(inParam)) {
					inParameters.add(inParam);
					break;
				}
			}
		}
		if (checkMethod.getParameterTypes().length != inParameters.size()) {
			throw new LogicInterceptException("Checkerで必要な引数がLogic側に定義されていません。CheckerとLogicの引数を見直してください。");
		}
		return (boolean)checkMethod.invoke(checker, inParameters.toArray());
	}

	private boolean hasPermission(Logic logic) {
		for (Permission permission : logic.permission()) {
			for (Auth auth : permission.value()) {
				for (String name : auth.value()) {
					if (StringUtils.isNotEmpty(name)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean checkPermission(Logic logic) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		for (Permission permission : logic.permission()) {
			Class<?> checkerClass = permission.checker().value();
			String checkMethodName = permission.checker().name();
			Method checkMethod = checkerClass.getMethod(checkMethodName, Permission.class);
			if (checkMethod == null) {
				throw new LogicInterceptException("PermissionCheckerのメソッドが見つかりません。");
			}
			Object checker = resolve(checkerClass);

			if ((boolean)checkMethod.invoke(checker, permission)) {
				return true;
			}
		}
		return false;
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
