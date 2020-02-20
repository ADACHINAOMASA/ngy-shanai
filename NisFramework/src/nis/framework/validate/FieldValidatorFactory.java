package nis.framework.validate;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import nis.framework.dictionary.KyokaMoji;
import nis.framework.dictionary.MaxLength;
import nis.framework.dictionary.MinLength;
import nis.framework.dictionary.Options;
import nis.framework.dictionary.Required;

public class FieldValidatorFactory {

	// TODO:本当はここの分岐を作りたくないが、リフレクションでやると高コストになるので
	//      各アノテーションを振り分けた上で直接メソッドを呼び出す

	@SuppressWarnings("unchecked")
	public static <T extends Annotation> Class<? extends FieldValidator<T>> get(T anno) {
		if (anno instanceof Required) {
			return (Class<? extends FieldValidator<T>>)
					Required.class.cast(anno).validator();
		}
		if (anno instanceof MaxLength) {
			return (Class<? extends FieldValidator<T>>)
					MaxLength.class.cast(anno).validator();
		}
		if (anno instanceof MinLength) {
			return (Class<? extends FieldValidator<T>>)
					MinLength.class.cast(anno).validator();
		}
		if (anno instanceof KyokaMoji) {
			return (Class<? extends FieldValidator<T>>)
					KyokaMoji.class.cast(anno).validator();
		}
		if (anno instanceof Options) {
			return (Class<? extends FieldValidator<T>>)
					Options.class.cast(anno).validator();
		}
		try {
			Method method = anno.getClass().getMethod("validator");
			return (Class<? extends FieldValidator<T>>) method.invoke(anno);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new IllegalStateException(e);
		}
	}

}
