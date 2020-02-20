package nis.framework.util.bean;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BeanMapper {

	private Log logger = LogFactory.getLog(BeanMapper.class);

	public BeanMapper() {
	}

	public <T> T mappingIgnoreCase(Class<T> type, String[] names, Object[] values) {
		return mappingIgnoreCase(type, createMap(names, values));
	}

	public <T> T mapping(Class<T> type, String[] names, Object[] values) {
		return mapping(type, createMap(names, values));
	}

	public <T> T mappingIgnoreCase(T bean, String[] names, Object[] values) {
		return mappingIgnoreCase(bean, createMap(names, values));
	}

	public <T> T mapping(T bean, String[] names, Object[] values) {
		return mapping(bean, createMap(names, values));
	}

	public <T> T mappingIgnoreCase(Class<T> type, Map<String, ? extends Object> map) {
		try {
			return mappingIgnoreCase(type.newInstance(), map);
		}
		catch (InstantiationException x) {
			throw new MappingException(x);
		}
		catch (IllegalAccessException x) {
			throw new MappingException(x);
		}
	}

	public <T> T mapping(Class<T> type, Map<String, ? extends Object> map) {
		try {
			return mapping(type.newInstance(), map);
		}
		catch (InstantiationException x) {
			throw new MappingException(x);
		}
		catch (IllegalAccessException x) {
			throw new MappingException(x);
		}
	}

	public <T> T mappingIgnoreCase(T bean, Map<String, ? extends Object> map) {
		return mapping(bean, properMap(bean, map));
	}

	public <T> T mapping(T bean, Map<String, ? extends Object> map) {
		try {
			BeanUtils.populate(bean, map);
		}
		catch (InvocationTargetException x) {
			throw new MappingException(x);
		}
		catch (IllegalAccessException x) {
			throw new MappingException(x);
		}
		return bean;
	}

	private Map<String, Object> createMap(String[] names, Object[] values) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < names.length; i++) {
			map.put(names[i], values[i]);
		}
		return map;
	}

	private Map<String, Object> properMap(Object bean, Map<String, ? extends Object> map) {
		PropertyDescriptor[] props = PropertyUtils.getPropertyDescriptors(bean);
		Map<String, Object> proper = new HashMap<String, Object>();
		for (String key : map.keySet()) {
			boolean contains = false;
			for (PropertyDescriptor prop : props) {
				if (key.equalsIgnoreCase(prop.getName())) {
					proper.put(prop.getName(), map.get(key));
					contains = true;
					break;
				}
				if (key.replace("_", "").equalsIgnoreCase(prop.getName())) {
					proper.put(prop.getName(), map.get(key));
					contains = true;
					break;
				}
			}
			if (logger.isDebugEnabled() && !contains) {
				logger.debug("対応するプロパティが見つかりませんでした。:" + key);
			}
		}
		return proper;
	}

}
