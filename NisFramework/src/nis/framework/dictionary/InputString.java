package nis.framework.dictionary;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 文字列入力指定宣言
 * <p>
 *  このフィールドは元の型に関わらず、入力上の扱いは文字列である。<br>
 * </p>
 * @author Kengo-Nagase
 *
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface InputString {

}
