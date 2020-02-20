package nis.framework.dictionary;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * スケール(実数)宣言
 * <p>
 *  数値フィールド限定。<br>
 *  この項目の小数点は指定した桁までしか入れられない。
 * </p>
 * @author Kengo-Nagase
 *
 */
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Scale {

	/**
	 * 小数点以下桁数
	 * @return
	 */
	int value();

}
