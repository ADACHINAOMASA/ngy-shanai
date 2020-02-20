package nis.framework.dictionary;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;

import nis.framework.dictionary.validator.FieldOptionsValidator;
import nis.framework.validate.FieldValidator;
import nis.framework.validate.ValidateDictionaly;

/**
 * 選択オプション関連付け宣言
 * <p>
 *  このフィールドには指定したSelectOptionsに存在する値しか入れられない。<br>
 *  valueには関連付けるSelectOptions名が入る。
 *  省略した場合はプロパティ名がそのまま使われる。
 * </p>
 * @author Kengo-Nagase
 *
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@ValidateDictionaly
public @interface Options {

	/**
	 * SelectOptions名の指定
	 * 省略した場合はプロパティ名となる
	 * @return
	 */
	String value() default "";

	/**
	 * サーバーサイドValidator
	 * @return
	 */
	@Nonbinding Class<? extends FieldValidator<Options>> validator() default FieldOptionsValidator.class;

}
