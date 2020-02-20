package nis.framework.ejb.logic;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class LogicContext {

	private Map<String, Object> container = new HashMap<String, Object>();

	public void putContainerValue(String key, String value) {
		container.put(key, value);
	}

	public Object getContainerValue(String key) {
		return container.get(key);
	}

}
