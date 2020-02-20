package nis.framework.dictionary;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * �Ɩ����x���̕�����ɑ΂��鐧��@�@�@�@VO�̋@�\<br>
 * 
 * ������ɕK�v�ȃZ�b�g
 * 
 */
@Target({ ElementType.FIELD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@ConstraintSet
public @interface Mojiretsu {

	/**
	 * �ő包��
	 */
	MaxLength maxLength();

	/**
	 * �ŏ�����
	 */
	MinLength minLength() default @MinLength;

	/**
	 * ����l
	 */
	InitValueMojiretsu initValue() default @InitValueMojiretsu("");

	/**
	 * �S�p���p�w��
	 */
	Zenhankaku zenhankaku() default @Zenhankaku;

	/**
	 * �啶���������w��
	 */
	OmojiKomoji omojiKomoji() default @OmojiKomoji;

	/**
	 * ��������
	 */
	KyokaMoji kyokaMoji() default @KyokaMoji;

	/**
	 * �\���t�H�[�}�b�g������
	 */
	DisplayFormat displayFormat() default @DisplayFormat;

	/**
	 * ��͕�@
	 */
	InputType inputType() default @InputType;

	/**
	 * ��͌��
	 */
	InputKoho inputKoho() default @InputKoho;

	/**
	 * �����_�C�A���O
	 */
	SerchDialog serchDialog() default @SerchDialog;

}
