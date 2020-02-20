package nis.framework.dictionary;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;

import nis.framework.dictionary.validator.FieldMaxLengthValidator;
import nis.framework.validate.FieldValidator;
import nis.framework.validate.ValidateDictionaly;

/**
 * 最大桁数宣言
 * <p>
 *  この項目は最大でこの桁数(byte)までしか入れられない。<br>
 *  数値の場合も全体byteで指定。<br>
 * </p>
 * @author Kengo-Nagase
 *
 */
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@ValidateDictionaly
public @interface MaxLength {

	/**
	 * 最大桁数(byte)
	 * @return
	 */
	int value();

	/**
	 * サーバーサイドValidator
	 * @return
	 */
	@Nonbinding Class<? extends FieldValidator<MaxLength>> validator() default FieldMaxLengthValidator.class;

}
