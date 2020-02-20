package nis.framework.dictionary;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 大文字小文字指定宣言
 * <p>
 *  この項目には指定したCase(大文字、小文字、混在)しか入れられない<br>
 *  文字列限定<br>
 *  実際の動作としては入力できないのではなく入力後に強制変換<br>
 *  また、小文字はまだ未実装<br>
 * </p>
 * @author Kengo-Nagase
 *
 */
@Target({ ElementType.FIELD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface OmojiKomoji {

	OmojiKomojiElm value() default OmojiKomojiElm.NONE;

	public enum OmojiKomojiElm {

		/**
		 * 大文字限定
		 */
		OMOJI,

		/**
		 * 小文字限定
		 */
		KOMOJI,

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