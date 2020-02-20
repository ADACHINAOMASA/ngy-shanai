package nis.framework.web.option;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * <p>
 * 選択オプション<br>
 * ここからオプションクラスを生成するという宣言<br>
 * Enumもしくはメソッドに対して付与すると、スタートアップ時にオプション生成のターゲットとなる<br>
 * valueは生成されるオプション群のキーとなる<br>
 * 省略した場合はクラス名やメソッド名をそのまま使う
 * </p>
 * @author Kengo-Nagase
 *
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SelectOptions {
	String value() default "";
}
