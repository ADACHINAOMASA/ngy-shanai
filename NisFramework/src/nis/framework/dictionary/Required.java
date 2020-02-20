package nis.framework.dictionary;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;

import nis.framework.dictionary.validator.FieldRequiredValidator;
import nis.framework.validate.FieldValidator;
import nis.framework.validate.ValidateDictionaly;

/**
 * 必須宣言
 * <p>
 *  このフィールドは必須項目である。<br>
 * </p>
 * @author Kengo-Nagase
 *
 */
@Target({ ElementType.FIELD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@ValidateDictionaly
public @interface Required {

	/**
	 * サーバーサイドValidator
	 * @return
	 */
	@Nonbinding Class<? extends FieldValidator<Required>> validator() default FieldRequiredValidator.class;

}
