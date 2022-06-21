package ngyshanai.common.util.bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import jodd.bean.BeanUtil;

public class Beanz {

	private static final String[] DEFAULT_IGNORES = new String[] { "_", "-" };

	public static void populate(final Map<String, ? extends Object> src, Object dest) {
		try {
			BeanUtils.populate(dest, src);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public static <T> T copy(Object src, T dest) {
		try {
			BeanUtils.copyProperties(dest, src);
			return (T) dest;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T copy(Object src, Class<T> dest) {
		try {
			T to = newInstance(dest);
			BeanUtils.copyProperties(to, src);
			return to;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Object getPropertyForceSilent(Object bean, String name) {
		return BeanUtil.forcedSilent.getProperty(bean, name);
	}

	public static void setPropertyForceSilent(Object bean, String name, Object value) {
		BeanUtil.forcedSilent.setProperty(bean, name, value);
	}

	public static void copyPropertiesForceSilent(Object src, Object dest) {
		copyPropertiesForceSilent(src, dest, null);
	}


	/*
	public static void setProperty(Object bean, String name, Object value) {
		try {
			BeanUtils.setProperty(bean, name, value);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	 */
	
	public static String getStringPropertyForceSilent(Object bean, String name) {
		
		/*
		try {
			return BeanUtils.getProperty(bean, name);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
		*/
		
		return (String)getPropertyForceSilent(bean, name);
	}
	
	public static void copyPropertiesForceSilent(Object src, Object dest, Map<String, String> ignoredMap) {

		PropertyDescriptor[] props = getPropertyDescriptors(src);

		for (int i = 0; i < props.length; i++) {

			String name = props[i].getName();
			if (ignoredMap == null || ignoredMap.isEmpty()) {
				Object value = getPropertyForceSilent(src, name);
				//if (value != null) {
					setPropertyForceSilent(dest, name, value);
				//}
			} else {
				if (!ignoredMap.containsKey(name)) {
					Object value = getPropertyForceSilent(src, name);
					//if (value != null) {
						setPropertyForceSilent(dest, name, value);
					//}
				}
			}
		}
	}

	

	@SuppressWarnings("unchecked")
	public static <T> T toBean(final ResultSet rs, Class<T> type) {
		try {
			return (T) new BeanProcessor().toBean(rs, type);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newInstance(type);
	}

	public static <T> T toBean(final Map<String, Object> map, T bean, String[] ingnores) {
		try {
			mapToProperties(map, bean, ingnores);
			return bean;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T toBean(final Map<String, Object> map, T bean) {
		return toBean(map, bean, DEFAULT_IGNORES);
	}

	public static <T> T toBean(final Map<String, Object> map, Class<T> type) {
		return toBean(map, newInstance(type), DEFAULT_IGNORES);
	}

	public static <T> T newInstance(Class<T> c) {
		try {
			return c.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static PropertyDescriptor[] getPropertyDescriptors(Object bean) {
		return PropertyUtils.getPropertyDescriptors(bean);
	}

	public static PropertyDescriptor[] getPropertyDescriptors(Class<?> type) throws Exception {
		BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo(type);
		} catch (IntrospectionException e) {
			throw new Exception("Bean introspection failed: " + e.getMessage());
		}
		return beanInfo.getPropertyDescriptors();
	}

	// -------------------------------------------------------------------- private
	private static Object mapToProperties(final Map<String, Object> map, Object bean, String[] ignores)
			throws Exception {

		PropertyDescriptor[] props = getPropertyDescriptors(bean);

		for (Entry<String, Object> e : map.entrySet()) {

			String name = replaceIgnores(e.getKey(), ignores);
			Object value = convertData(e.getValue());

			for (int i = 0; i < props.length; i++) {
				if (name.equalsIgnoreCase(props[i].getName())) {
					//setProperty(bean, props[i].getName(), value);
					setPropertyForceSilent(bean, props[i].getName(), value);
					break;
				}
			}

		}

		return bean;
	}

	private static String replaceIgnores(String name, String[] ignores) {
		if (name == null || ignores == null || ignores.length == 0) {
			return name;
		}
		String result = name;
		for (String ignore : ignores) {
			result = result.replace(ignore, "");
		}
		return result;
	}

	private static Object convertData(Object data) {
		if (data != null) {
			if (data instanceof java.sql.Date || data instanceof Timestamp) {
				data = new Date(((Date) data).getTime());
			}
			if (data instanceof java.lang.String) {
				try {
					data = new String(((String) data).getBytes("Cp943C"), "windows-31j");
					;
				} catch (UnsupportedEncodingException e) {
				}
			}
		}
		return data;
	}

}
