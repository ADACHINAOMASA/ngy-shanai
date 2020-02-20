package nis.framework.dictionary.validator;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Collection;

import javax.inject.Inject;

import nis.framework.dictionary.MaxLength;
import nis.framework.dictionary.Minus;
import nis.framework.dictionary.Scale;
import nis.framework.validate.FieldValidator;
import nis.framework.validate.FieldValidatorUtil;
import nis.framework.validate.ValidateContext;
import nis.framework.validate.ValidateException;

public class FieldMaxLengthValidator implements FieldValidator<MaxLength>{

	@Inject
	private ValidateContext context;

	// TODO:メッセージの外部定義

	@Override
	public void validate(Object val, MaxLength an, Field field) {
		if (val == null) {
			return;
		}
		int max = an.value();
		if (val instanceof java.lang.String) {
			if (!isValid((String)val, max)) {
				context.addMsg(
						MessageFormat.format("{0}は最大半角{1}文字までです。"
								, FieldValidatorUtil.getLabel(field), max));
			}
			return;
		}
		if (val instanceof java.math.BigDecimal) {
			// かなり苦しいが、とりあえずマイナスチェックもここで
			if (field.isAnnotationPresent(Scale.class)) {
				int scale = field.getAnnotation(Scale.class).value();
				if (!isValid((BigDecimal)val, max, scale)) {
					context.addMsg(
							MessageFormat.format("{0}は整数{1}桁、小数{2}桁までです。"
									, FieldValidatorUtil.getLabel(field), max - scale, scale));
				}
			}
			else {
				if (!isValid((BigDecimal)val, max)) {
					context.addMsg(
							MessageFormat.format("{0}は最大{1}桁までです。"
									, FieldValidatorUtil.getLabel(field), max));
				}
			}
			if (!field.isAnnotationPresent(Minus.class)) {
				if (isMinus((BigDecimal)val)) {
					context.addMsg(
							MessageFormat.format("{0}はマイナス不可です。"
									, FieldValidatorUtil.getLabel(field), max));
				}
			}
			return;
		}
		if (val instanceof java.util.Collection){
			if(!isValid((Collection<?>)val, max)) {
				context.addMsg(
						MessageFormat.format("{0}は最大{1}個までです。"
								, FieldValidatorUtil.getLabel(field), max));
			}
			return;
		}
		throw new ValidateException("対応していない型：" + val.getClass().getName());
	}

	private boolean isValid(String val, int max) {
		return val.getBytes().length <= max;
	}

	private boolean isValid(BigDecimal val, int max) {
		return val.precision() <= max;
	}

	private boolean isValid(BigDecimal val, int max, int scale) {
		return val.precision() - val.scale() <= max - scale && val.scale() <= scale;
	}

	private boolean isMinus(BigDecimal val) {
		return val.compareTo(BigDecimal.ZERO) < 0;
	}

	private boolean isValid(Collection<?> val, int max) {
		return val.size() <= max;
	}

}
