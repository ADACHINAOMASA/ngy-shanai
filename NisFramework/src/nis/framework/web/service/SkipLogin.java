package nis.framework.web.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ログイン不要宣言
 * <p>
 *  このメソッドまたはクラスは、ログインしなくても使って良い。<br>
 * </p>
 * @author Kengo-Nagase
 *
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface SkipLogin {

}
