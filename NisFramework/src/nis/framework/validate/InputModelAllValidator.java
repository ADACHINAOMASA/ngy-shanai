package nis.framework.validate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import javax.inject.Inject;

import nis.framework.dictionary.InputModel;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;


public class InputModelAllValidator implements InputModelValidator {

	@Inject
	private ValidateContext context;

	@Override
	public boolean validate(Object inputModel) {
		if (!inputModel.getClass().isAnnotationPresent(InputModel.class)) {
			throw new IllegalArgumentException("InputModelでないものが渡されました。" + inputModel.getClass().getName());
		}

		// フィールドを一つずつ取り出す（順番は単純に定義順）
		// TODO:構造の整理
		try {
			for (Field field : inputModel.getClass().getDeclaredFields()) {
				// TODO:valid対象
				for (Annotation anno : field.getAnnotations()) {
					// アノテーションがvalidate対象か
					if (anno.annotationType().isAnnotationPresent(ValidateDictionaly.class)) {
						// TODO:リフレクションの重さを検証しておく
						context.getFieldValidator(anno).validate(PropertyUtils.getProperty(inputModel, field.getName()), anno, field);
						if (context.isValidateBreak()) {
							break;
						}
					}
				}
				if (context.isValidateBreak()) {
					break;
				}
				Class<?> fieldClass = field.getType();
				if (fieldClass.isAnnotationPresent(InputModel.class)) {
					validate(BeanUtils.getProperty(inputModel, field.getName()));
				}
				// TODO:要検証
				else if (Collection.class.equals(fieldClass)
							&& fieldClass.getTypeParameters()[0].getClass().isAnnotationPresent(InputModel.class)) {
					for (Object member : (Collection<?>)PropertyUtils.getProperty(inputModel, field.getName())) {
						validate(member);
					}
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new IllegalStateException(e);
		}

		return context.hasMsgs();
	}

}
