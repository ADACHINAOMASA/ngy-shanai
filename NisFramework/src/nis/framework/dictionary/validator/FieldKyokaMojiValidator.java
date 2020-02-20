package nis.framework.dictionary.validator;

import java.lang.reflect.Field;
import java.text.MessageFormat;

import javax.inject.Inject;

import nis.framework.dictionary.KyokaMoji;
import nis.framework.validate.FieldValidator;
import nis.framework.validate.FieldValidatorUtil;
import nis.framework.validate.ValidateContext;
import nis.framework.validate.ValidateException;

public class FieldKyokaMojiValidator implements FieldValidator<KyokaMoji>{

	@Inject
	private ValidateContext context;

	// TODO:メッセージの外部定義

	@Override
	public void validate(Object val, KyokaMoji an, Field field) {
		if (val == null) {
			return;
		}
		if (val instanceof java.lang.String) {
			// TODO:要検討
			if (!((java.lang.String) val).matches(an.value() + "*")) {
				context.addMsg(
						MessageFormat.format("{0}に入力出来ない文字が含まれています。"
								, FieldValidatorUtil.getLabel(field)));
			}
			return;
		}
		throw new ValidateException("対応していない型：" + val.getClass().getName());
	}

}
