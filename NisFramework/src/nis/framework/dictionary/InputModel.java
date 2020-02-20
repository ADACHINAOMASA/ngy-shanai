package nis.framework.dictionary;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * InputModel宣言
 * <p>
 *  このアノテーションが付与されたPOJOクラスはInputModelである。<br>
 *  valueにはInputModel名を記述。<br>
 *  省略した場合はクラス名がそのままモデル名になる<br>
 * </p>
 * @author Kengo-Nagase
 *
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface InputModel {

	/**
	 * InputModel名
	 * 省略した場合はクラス名がそのままモデル名になる
	 * @return
	 */
	String value() default "";

}