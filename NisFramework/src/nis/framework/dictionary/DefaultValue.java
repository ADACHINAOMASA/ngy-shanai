package nis.framework.dictionary;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 初期値
 * <p>
 *  初期値を指定<br>
 *  付与するプロパティの型によって振る舞いが変わるので注意<br>
 *  String:valueをそのまま<br>
 *  BigDecimal:valueを数値に変換、数値に変換できない場合は例外<br>
 *  Date:valueを日付に変換、フォーマットはyyyy/MM/dd,yyyy-MM-dd,yyyyMMddの3種<br>
 *       また、固定値としてcurrent_dateを指定すると現在日時となる
 * </p>
 * @author Kengo-Nagase
 *
 */
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DefaultValue {

	String value();

}
