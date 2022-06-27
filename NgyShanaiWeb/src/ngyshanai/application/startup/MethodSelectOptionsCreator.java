package ngyshanai.application.startup;

import java.lang.reflect.Method;
import java.util.List;

import ngyshanai.application.LogicExecutor;
import nis.framework.web.option.Option;

public class MethodSelectOptionsCreator implements SelectOptionsCreator{

	public Method optionsMethod;

	public MethodSelectOptionsCreator(Method optionsMethod) {
		this.optionsMethod = optionsMethod;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Option> create(LogicExecutor ejb) {
		try {
			if (optionsMethod.getParameterTypes().length > 0
					&& optionsMethod.getParameterTypes()[0].equals(LogicExecutor.class)) {
				return  (List<Option>)optionsMethod.invoke(optionsMethod.getDeclaringClass().newInstance(), ejb);
			}
			else {
				return  (List<Option>)optionsMethod.invoke(optionsMethod.getDeclaringClass().newInstance());
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
}
