package nis.framework.dictionary;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ������̏���l
 */
@Target({ ElementType.FIELD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface InitValueMojiretsu {

	String value() default "";
	
	/**
	 * ����l���󔒂̎��A����l�Ƃ��Ă�����Z�b�g���邩�ǂ���
	 */
	boolean emptySet() default false;

}
