package nis.framework.dictionary;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 */
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Label {

	// 多言語対応を考えてvalueはIDに
	String value() default "";

	String label() default "";

}
