package nis.framework.ejb.logic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import nis.framework.ejb.logic.checker.DefaultPermissionChecker;

/**
 * 権限制御指定アノテーション
 * <p>
 *  Logicアノテーション内で制御を行う権限と制御用のチェッカーを指定する為に使用する。<br>
 *  各属性について<br>
 *  value:権限制御に使用する権限（複数化）を指定する。デフォルトでは許可する権限を指定。<br>
 *  checker:権限制御に使用するチェッカーを指定する。デフォルトでは指定された権限のみを許可するチェッカー<br>
 *  権限制御用のチェッカーを独自に用意したい場合はDefaultPermissionCheckerを参考に。
 * </p>
 * @author Kengo-Nagase
 *
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {

	/**
	 * 権限
	 * @return
	 */
	Auth[] value() default @Auth;

	/**
	 * 権限制御用チェッカー
	 * @return
	 */
	PermissionChecker checker() default @PermissionChecker(value=DefaultPermissionChecker.class);
}
