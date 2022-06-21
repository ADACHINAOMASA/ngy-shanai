package ngyshanai.common.util.bean;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang.builder.ToStringBuilder;

public class SimpleBeanConverter {

	private SimpleBeanConverter(){}

	private static synchronized void map(java.lang.Object from,java.lang.Object to, boolean debugMode) {

		if (to == null || from == null){
			return;
		}

		try {
			BeanUtilsBean beanUtilsBean = BeanUtilsBean.getInstance();
			beanUtilsBean.getConvertUtils().register(new org.apache.commons.beanutils.converters.BigDecimalConverter(null), BigDecimal.class);
			beanUtilsBean.getConvertUtils().register(new org.apache.commons.beanutils.converters.SqlTimestampConverter(null), Timestamp.class);
			beanUtilsBean.getConvertUtils().register(new org.apache.commons.beanutils.converters.SqlDateConverter(null), Date.class);
			beanUtilsBean.getConvertUtils().register(new org.apache.commons.beanutils.converters.DateConverter(null), Date.class);

			beanUtilsBean.copyProperties(to, from);

			if (debugMode) {
				System.out.print("From : "+ ToStringBuilder.reflectionToString(from));
				System.out.print("To   : "+ ToStringBuilder.reflectionToString(to));
			}

		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InvocationTargetException ex) {
			ex.printStackTrace();
		}
	}

	public static synchronized void convert(java.lang.Object from,java.lang.Object to) {
		map(from, to, false);
	}

	public static synchronized <T> T toBean(java.lang.Object from,Class<T> toType, boolean debugMode) {
		T to = newInstance(toType);
		map(from, to, debugMode);
		return to;
	}

	public static synchronized <T> T toBean(java.lang.Object from, Class<T> to) {
		return toBean(from, to, false);
	}

	@SuppressWarnings("unchecked")
	public static synchronized <T> T toBean(final ResultSet rs, Class<T> type) {
		BeanProcessor procesor = new BeanProcessor();
		try {
			return (T) procesor.toBean(rs, type);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newInstance(type);
	}

	private static <T> T newInstance(Class<T> c) {
		try {
			return c.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

}
