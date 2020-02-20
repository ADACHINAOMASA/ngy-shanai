package nis.framework.dictionary.item;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import nis.framework.dictionary.ConstraintSet;
import nis.framework.dictionary.DisplayFormat;
import nis.framework.dictionary.InputKoho;
import nis.framework.dictionary.InputType;
import nis.framework.dictionary.InputTypeElm;
import nis.framework.dictionary.KbnFlgStaKohoIchiran;
import nis.framework.dictionary.KyokaMoji;
import nis.framework.dictionary.MaxLength;
import nis.framework.dictionary.MinLength;
import nis.framework.dictionary.Mojiretsu;
import nis.framework.dictionary.OmojiKomoji;
import nis.framework.dictionary.OmojiKomoji.OmojiKomojiElm;
import nis.framework.dictionary.UiName;
import nis.framework.dictionary.Zenhankaku;
import nis.framework.dictionary.Zenhankaku.ZenhankakuElm;

/**
 * �I�[�_�[���@��`			���ڂP���`����<br>
 *
 * �@�\���� : �I�[�_�[��<br>
 * �@�ő包�� : �U<br>
 * �@�ŏ����� : �U<br>
 * �@���p<br>
 * �@�啶��<br>
 * �@�������� : [0-9],[A-Z]<br>
 * �@�\���t�H�[�}�b�g ######-##<br>
 */
@UiName("�I�[�_�[��")
@Mojiretsu(maxLength = @MaxLength(6)
		, minLength = @MinLength(6)
		, zenhankaku = @Zenhankaku(ZenhankakuElm.HANKAKU)
		, omojiKomoji = @OmojiKomoji(OmojiKomojiElm.OMOJI)
		, kyokaMoji = @KyokaMoji("[0-9],[A-Z]")
		, displayFormat = @DisplayFormat("######-##")
		, inputType = @InputType(InputTypeElm.COMBO)
		, inputKoho = @InputKoho(KbnFlgStaKohoIchiran.ORDER_NO)
)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@ConstraintSet
public @interface OrderNo {
	// �����ɗv�f��ǉ�����ꍇ�́A�A�m�e�[�V�����g�����`���Ă��������B
}
