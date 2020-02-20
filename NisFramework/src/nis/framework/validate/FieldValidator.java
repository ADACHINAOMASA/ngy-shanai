package nis.framework.validate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public interface FieldValidator<T extends Annotation> {

	public void validate(Object val, T anno, Field field);

}
