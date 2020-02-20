package nis.framework.dictionary;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 全角半角指定宣言
 * <p>
 *  この項目には指定したCase(全角、半角)しか入れられない<br>
 *  文字列限定<br>
 *  実際の動作としてはime-modeを使用。<br>
 * </p>
 * @author Kengo-Nagase
 *
 */
@Target({ ElementType.FIELD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Zenhankaku {

	ZenhankakuElm value() default ZenhankakuElm.NONE;

	public enum ZenhankakuElm {

		/**
		 * 半角
		 */
		HANKAKU,

		/**
		 * 全角
		 */
		ZENKAKU,

		/**
		 * 混在
		 */
		KONZAI,

		/**
		 * なし
		 */
		NONE

	}


}
