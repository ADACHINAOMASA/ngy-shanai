package nis.framework.dictionary;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;

import nis.framework.dictionary.validator.FieldKyokaMojiValidator;
import nis.framework.validate.FieldValidator;
import nis.framework.validate.ValidateDictionaly;

/**
 * 入力許可文字宣言
 * <p>
 *  このフィールドには指定されたパターンの文字しか入れられない。<br>
 *  パターンは正規表現(JavaScript)で記述する。<br>
 * </p>
 * @author Kengo-Nagase
 *
 */
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@ValidateDictionaly
public @interface KyokaMoji {

	String value() default "";

	/**
	 * サーバーサイドValidator
	 * @return
	 */
	@Nonbinding Class<? extends FieldValidator<KyokaMoji>> validator() default FieldKyokaMojiValidator.class;

}
