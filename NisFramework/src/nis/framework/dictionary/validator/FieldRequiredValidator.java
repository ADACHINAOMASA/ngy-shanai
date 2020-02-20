package nis.framework.dictionary.validator;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Collection;

import javax.inject.Inject;

import nis.framework.dictionary.Required;
import nis.framework.type.Hiduke;
import nis.framework.type.Nengetsu;
import nis.framework.validate.FieldValidator;
import nis.framework.validate.FieldValidatorUtil;
import nis.framework.validate.ValidateContext;

import org.apache.commons.lang3.StringUtils;

public class FieldRequiredValidator implements FieldValidator<Required>{

	@Inject
	private ValidateContext context;

	// TODO:メッセージの外部定義
	private final String MSG = "{0}は必須入力です。";

	@Override
	public void validate(Object val, Required an, Field field) {
		if (!validate(val)) {
			addMsg(field);
		}
	}

	private boolean validate(Object val) {
		if (val instanceof java.lang.String) {
			return isValid((String)val);
		}
		if (val instanceof java.math.BigDecimal){
			return isValid((BigDecimal)val);
		}
		if (val instanceof Nengetsu){
			return isValid((Nengetsu)val);
		}
		if (val instanceof Hiduke){
			return isValid((Hiduke)val);
		}
		if (val instanceof java.util.Collection){
			return isValid((Collection<?>)val);
		}
		return isValid(val);
	}

	private boolean isValid(String val) {
		return StringUtils.isNotEmpty(val);
	}

	private boolean isValid(BigDecimal val) {
		return val != null && BigDecimal.ZERO.compareTo(val) != 0;
	}

	private boolean isValid(Nengetsu val) {
		return val != null && !val.isNull();
	}

	private boolean isValid(Hiduke val) {
		return val != null && !val.isNull();
	}

	private boolean isValid(Collection<?> val) {
		return val != null && !val.isEmpty();
	}

	private boolean isValid(Object val) {
		return val != null;
	}

	private void addMsg(Field field) {
		context.addMsg(MessageFormat.format(MSG, FieldValidatorUtil.getLabel(field)));
	}


}
