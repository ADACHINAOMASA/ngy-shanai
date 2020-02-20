package nis.framework.util.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BeanUtil {

	public static <T> List<T> createBeans(List<Map<String, ? extends Object>> props, Class<T> beanClass){
		return createBeans(props, beanClass, new BeanMapper());
	}

	public static <T> List<T> createBeans(List<Map<String, ? extends Object>> props, Class<T> beanClass, BeanMapper mapper){
		List<T> results = new ArrayList<T>();
		for(final Map<String, ? extends Object> prop : props){
			results.add(createBean(prop, beanClass, mapper));
		}
		return results;
	}

	public static <T> T createBean(Map<String, ? extends Object> props, Class<T> beanClass){
		return createBean(props, beanClass, new BeanMapper());
	}

	public static <T> T createBean(Map<String, ? extends Object> props, Class<T> beanClass, BeanMapper mapper){
		return mapper.mappingIgnoreCase(beanClass, props);
	}

	public static <T> List<T> populate(List<Map<String, ? extends Object>> props, T bean){
		return populate(props, bean, new BeanMapper());
	}

	public static <T> List<T> populate(List<Map<String, ? extends Object>> props, T bean, BeanMapper mapper){
		List<T> results = new ArrayList<T>();
		for(final Map<String, ? extends Object> prop : props){
			results.add(populate(prop, bean, mapper));
		}
		return results;
	}

	public static <T> T populate(Map<String, ? extends Object> props, T bean){
		return populate(props, bean, new BeanMapper());
	}

	public static <T> T populate(Map<String, ? extends Object> props, T bean, BeanMapper mapper){
		return mapper.mappingIgnoreCase(bean, props);
	}

}
