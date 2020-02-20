package nis.framework.dictionary;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;

import nis.framework.dictionary.validator.FieldMinLengthValidator;
import nis.framework.validate.FieldValidator;
import nis.framework.validate.ValidateDictionaly;

/**
 * 最小桁数宣言
 * <p>
 *  この項目は最低でもこの桁数(byte)は入力されねばならない。<br>
 * </p>
 * @author Kengo-Nagase
 *
 */
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@ValidateDictionaly
public @interface MinLength {

	/**
	 * 桁数(byte)
	 * @return
	 */
	int value() default 0;

	/**
	 * サーバーサイドValidator
	 * @return
	 */
	@Nonbinding Class<? extends FieldValidator<MinLength>> validator() default FieldMinLengthValidator.class;

}
