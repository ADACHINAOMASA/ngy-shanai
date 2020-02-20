package nis.framework.ejb.logic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;

import nis.framework.ejb.logic.checker.DefaultLogicChecker;

/**
 * ロジック宣言アノテーション
 * <p>
 *  LogicExecutorで実行されるLogicである事を宣言する<br>
 *  各属性について<br>
 *  preCheck:事前チェックに使用するチェッカーを指定（複数指定可能）<br>
 *  postCheck:事後チェックに使用するチェッカーを指定（複数指定可能）<br>
 *  permission:権限制御の指定
 * </p>
 * @author Kengo-Nagase
 *
 */
@InterceptorBinding
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Logic {

	/**
	 * 事前チェック
	 * @return
	 */
	@Nonbinding Checker[] preCheck() default @Checker(value=DefaultLogicChecker.class, name="pre");

	/**
	 * 事後チェック
	 * @return
	 */
	@Nonbinding Checker[] postCheck() default @Checker(value=DefaultLogicChecker.class, name="post");

	/**
	 * 権限制御の指定
	 * @return
	 */
	@Nonbinding Permission[] permission() default @Permission;
}
