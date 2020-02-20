package nis.framework.dictionary.validator;

import java.lang.reflect.Field;
import java.text.MessageFormat;

import javax.inject.Inject;

import nis.framework.dictionary.Options;
import nis.framework.validate.FieldValidator;
import nis.framework.validate.FieldValidatorUtil;
import nis.framework.validate.ValidateContext;
import nis.framework.validate.ValidateException;
import nis.framework.web.option.OptionsContainer;

import org.apache.commons.lang3.StringUtils;

public class FieldOptionsValidator implements FieldValidator<Options>{

	@Inject
	private ValidateContext context;

	@Inject
	private OptionsContainer optionsContainer;

	// TODO:メッセージの外部定義

	@Override
	public void validate(Object val, Options an, Field field) {
		if (val == null) {
			return;
		}
		if (val instanceof java.lang.String) {
			String strVal = (String)val;
			if (StringUtils.isEmpty(strVal)) {
				return;
			}
			String optionsKey = an.value();
			if (StringUtils.isEmpty(optionsKey)) {
				optionsKey = field.getName();
			}
			nis.framework.web.option.Options options = optionsContainer.getOptions(optionsKey);
			if (!options.containsOption(strVal)) {
				context.addMsg(
						MessageFormat.format("{0}に存在しない値が入力されています。"
								, FieldValidatorUtil.getLabel(field)));
			}
			return;
		}
		throw new ValidateException("対応していない型：" + val.getClass().getName());
	}

}
