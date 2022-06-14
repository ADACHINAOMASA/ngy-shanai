/**
 * $Id: PropertyReflector.java,v 1.1 2013/04/30 05:44:32 kengo-nagase Exp $
 */
package nis.framework.oldframework;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

public class PropertyReflector {

	private static Map<String, Map<String, PropertyDescriptor>> cache = new HashMap<String, Map<String, PropertyDescriptor>>();

	public static Map<String, PropertyDescriptor> getPropertyDescriptors(Class<?> _class) {
		Map<String, PropertyDescriptor> props = cache.get(_class.getName());
		if (props == null) {
			Map<String, PropertyDescriptor> map = new HashMap<String, PropertyDescriptor>();
			for (PropertyDescriptor prop : PropertyUtils.getPropertyDescriptors(_class)) {
				if (!prop.getName().equals("class")) {
					map.put(prop.getName(), prop);
				}
			}
			props = map;
			cache.put(_class.getName(), props);
		}
		return props;
	}

	public static List<String> getPropertyNames(Object bean) {
		return getPropertyNames(bean.getClass());
	}

	public static List<String> getPropertyNames(Class<?> _class) {
		List<String> l = new ArrayList<String>();
		for (PropertyDescriptor prop : getPropertyDescriptors(_class).values()) {
			l.add(prop.getName());
		}
		return l;
	}

	public static Class<?> getPropertyType(Object bean, String name) {
		return getPropertyType(bean.getClass(), name);
	}

	public static Class<?> getPropertyType(Class<?> _class, String name) {
		PropertyDescriptor prop = getPropertyDescriptors(_class).get(name);
		if (prop != null) {
			return prop.getPropertyType();
		}
		return null;
	}

	public static Object getProperty(Object bean, String name) {
		PropertyDescriptor prop = getPropertyDescriptors(bean.getClass()).get(name);
		if (prop != null) {
			try {
				return prop.getReadMethod().invoke(bean, new Object[0]);
			}
			catch (Exception x) {
			}
		}
		return null;
	}

	public static void setProperty(Object bean, String name, Object value) {
		PropertyDescriptor prop = getPropertyDescriptors(bean.getClass()).get(name);
		if (prop != null) {
			try {
				prop.getWriteMethod().invoke(bean, value);
			}
			catch (Exception x) {
			}
		}
	}

	public static boolean matchPropertyType(Object fromBean, Object toBean, String name) {
		return matchPropertyType(fromBean.getClass(), toBean.getClass(), name);
	}

	public static boolean matchPropertyType(Class<?> fromClass, Class<?> toClass, String name) {
		Class<?> fromType = getPropertyType(fromClass, name);
		Class<?> toType = getPropertyType(toClass, name);
		if (fromType == null || toType == null) {
			return false;
		}
		return toType.isAssignableFrom(fromType);
	}

}
