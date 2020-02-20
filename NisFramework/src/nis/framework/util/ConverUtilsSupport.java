package nis.framework.util;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;

public class ConverUtilsSupport {

	public static ConverUtilsSupport create() {
		return new ConverUtilsSupport();
	}

	public void register() {
		ConvertUtils.register(new Converter() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public Object convert(Class type, Object arg1) {
				Object value = arg1;

				if (value == null) {
					return null;
				}

				if (value instanceof java.util.List && !((java.util.List) value).isEmpty()) {
					value = ((java.util.List) value).get(0);
				}
				if (value instanceof java.lang.String) {
					return type.cast(DateUtil.parse((String) value));
				}
				if (value instanceof java.util.Date) {
					return type.cast(value);
				}
				
				return type.cast(value);
			}

		}, java.util.Date.class);

		ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
		ConvertUtils.register(new SqlTimestampConverter(null), Timestamp.class);
	}
}
