package nis.framework.dictionary.validator;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Collection;

import javax.inject.Inject;

import nis.framework.dictionary.MinLength;
import nis.framework.dictionary.Scale;
import nis.framework.validate.FieldValidator;
import nis.framework.validate.FieldValidatorUtil;
import nis.framework.validate.ValidateContext;
import nis.framework.validate.ValidateException;

public class FieldMinLengthValidator implements FieldValidator<MinLength>{

	@Inject
	private ValidateContext context;

	// TODO:メッセージの外部定義

	@Override
	public void validate(Object val, MinLength an, Field field) {
		if (val == null) {
			return;
		}
		int min = an.value();
		if (val instanceof java.lang.String) {
			if (!isValid((String)val, min)) {
				context.addMsg(
						MessageFormat.format("{0}は最低半角{1}文字必要です。"
								, FieldValidatorUtil.getLabel(field), min));
			}
			return;
		}
		if (val instanceof java.math.BigDecimal) {
			if (field.isAnnotationPresent(Scale.class)) {
				int scale = field.getAnnotation(Scale.class).value();
				if (!isValid((BigDecimal)val, min, scale)) {
					context.addMsg(
							MessageFormat.format("{0}は最低整数{1}桁必要です。"
									, FieldValidatorUtil.getLabel(field), min - scale, scale));
				}
			}
			else {
				if (!isValid((BigDecimal)val, min)) {
					context.addMsg(
							MessageFormat.format("{0}は最低{1}桁必要です。"
									, FieldValidatorUtil.getLabel(field), min));
				}
			}
			return;
		}
		if (val instanceof java.util.Collection){
			if(!isValid((Collection<?>)val, min)) {
				context.addMsg(
						MessageFormat.format("{0}は最低{1}個必要です。"
								, FieldValidatorUtil.getLabel(field), min));
			}
			return;
		}
		throw new ValidateException("対応していない型：" + val.getClass().getName());
	}

	private boolean isValid(String val, int min) {
		return val.getBytes().length >= min;
	}

	private boolean isValid(BigDecimal val, int min) {
		return val.precision() >= min;
	}

	private boolean isValid(BigDecimal val, int min, int scale) {
		// 0の場合0桁扱い
		return val.precision() - val.scale() >= min;
	}

	private boolean isValid(Collection<?> val, int min) {
		return val.size() >= min;
	}

}
