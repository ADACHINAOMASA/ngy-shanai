package nis.framework.ejb.logic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * チェック用ロジック宣言
 * <p>
 *  チェッカーとして使用するメソッドに付与する<br>
 *  valueにはそのチェック用ロジックの識別子を設定する<br>
 *  チェッカー内に複数のチェック用ロジックがなければ設定する必要はない
 * </p>
 * @author Kengo-Nagase
 *
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckLogic {

	/**
	 * 識別子
	 * @return
	 */
	String value() default "";
}
