package nis.framework.validate;

import java.lang.reflect.Field;

import nis.framework.dictionary.Label;

import org.apache.commons.lang3.StringUtils;

public class FieldValidatorUtil {

	// TODO:とりあえずのもの
	public static String getLabel(Field field) {
		if (!field.isAnnotationPresent(Label.class)) {
			return field.getName();
		}
		Label label = field.getAnnotation(Label.class);
		// TODO:IDにするときはこの辺で
		return StringUtils.isNotEmpty(label.label()) ? label.label() : label.value();
	}
}
