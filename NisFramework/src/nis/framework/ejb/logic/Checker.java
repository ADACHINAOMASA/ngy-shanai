package nis.framework.ejb.logic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * チェッカー指定アノテーション
 * <p>
 *  Logicアノテーション内でチェッカーを指定する為に使用する。<br>
 *  各属性について<br>
 *  value:チェッカーとして使用するクラス<br>
 *  name:呼び出すチェック処理の指定<br>
 *  property:チェッカーに渡すプロパティを指定
 * </p>
 * @author Kengo-Nagase
 *
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Checker {

	/**
	 * チェッカークラス
	 * @return
	 */
	Class<?> value();

	/**
	 * チェック処理名
	 * @return
	 */
	String name() default "";

	/**
	 * チェッカーに渡すプロパティ名
	 * @return
	 */
	String property() default "";
}
