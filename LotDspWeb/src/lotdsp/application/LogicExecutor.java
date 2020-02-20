package lotdsp.application;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import nis.framework.cdi.GyomuLogicCdi;
import nis.framework.cdi.interceptor.LogIntercept;
import nis.framework.ejb.Executable;
import nis.framework.ejb.logic.Logic;
import nis.framework.ejb.logic.LogicInterceptException;

@Stateless
@LocalBean
public class LogicExecutor {

	@Resource
	private SessionContext context;

	@LogIntercept
	public<I, O> O execute(Class<? extends Executable<I, O>> logic, I in) {
		Executable<I, O> executable = GyomuLogicCdi.get().resolve(logic);

		O out = executable.execute(in);

		if (out == null) {
			context.setRollbackOnly();
		}

		return out;

	}

	/**
	 * Executableを使わないパターン
	 * 試用中だが問題なければこちらをメインに切り替える
	 * @param logicClass
	 * @param in
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@LogIntercept
	public<O> O execute(Class<?> logicClass, Object...in) {
		Object logic = GyomuLogicCdi.get().resolveLogic(logicClass);

		/*
		 *  AOP組み込み後のメソッドからはアノテーションが消えるので、
		 *  元のクラスから定義を取得し、その後同名同引数のメソッドを取得し直す。
		 */

		List<Method> origLogicMethods = new ArrayList<Method>();
		for (Method method : logicClass.getMethods()) {
			if (method.isAnnotationPresent(Logic.class)) {
				origLogicMethods.add(method);
			}
		}
		Method origLogicMethod = null;
		if (origLogicMethods.size() == 1) {
			origLogicMethod = origLogicMethods.get(0);
		} else {
			throw new LogicInterceptException("Logicが見つかりません。");
		}
		Method logicMethod = searchLogicMethod(logic, origLogicMethod);

		if (logicMethod == null) {
			throw new AssertionError();
		}

		O out = null;
		try {
			out = (O)logicMethod.invoke(logic, in);
		} catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new IllegalStateException(e);
		}

		if (out == null) {
			context.setRollbackOnly();
		}

		return out;

	}

	private Method searchLogicMethod(Object logic ,Method origLogicMethod) {
		Method logicMethod = null;
		for (Method method : logic.getClass().getMethods()) {
			if (!method.getName().equals(origLogicMethod.getName())){
				continue;
			}
			if (method.getParameterTypes().length != origLogicMethod.getParameterTypes().length) {
				continue;
			}
			for (int i = 0;i < method.getParameterTypes().length;i++) {
				if (!method.getParameterTypes()[i].isAssignableFrom(origLogicMethod.getParameterTypes()[i])) {
					continue;
				}
			}
			logicMethod = method;
			break;
		}
		return logicMethod;
	}

}
