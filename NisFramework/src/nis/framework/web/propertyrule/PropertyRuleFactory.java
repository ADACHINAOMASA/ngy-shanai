package nis.framework.web.propertyrule;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;

import nis.framework.dictionary.InputDate;
import nis.framework.dictionary.InputNumber;
import nis.framework.dictionary.InputString;
import nis.framework.dictionary.KyokaMoji;
import nis.framework.dictionary.MaxLength;
import nis.framework.dictionary.MinLength;
import nis.framework.dictionary.Minus;
import nis.framework.dictionary.OmojiKomoji;
import nis.framework.dictionary.OmojiKomoji.OmojiKomojiElm;
import nis.framework.dictionary.Options;
import nis.framework.dictionary.Required;
import nis.framework.dictionary.Scale;
import nis.framework.dictionary.Zenhankaku;
import nis.framework.dictionary.Zenhankaku.ZenhankakuElm;
import nis.framework.type.Hiduke;
import nis.framework.type.Nengetsu;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * <p>
 * プロパティルールファクトリ<br>
 * アノテーションから対応するルールを生成
 * </p>
 * @author Kengo-Nagase
 *
 */
public class PropertyRuleFactory {

	/**
	 * シングルトン
	 */
	private static PropertyRuleFactory thisInstance = new PropertyRuleFactory();

	/**
	 *
	 */
	private PropertyRuleFactory(){}

	/**
	 * シングルトン
	 * @return
	 */
	public static PropertyRuleFactory get() {
		return thisInstance;
	}

	/**
	 * 生成
	 * @param anno
	 * @return ルール
	 */
	public PropertyRule create(Annotation anno) {
		return create(anno, null);
	}

	/**
	 * 生成
	 * @param anno
	 * @return ルール
	 */
	public PropertyRule create(Annotation anno, Field field) {
		if (anno instanceof MaxLength) {
			return maxLength(((MaxLength) anno).value());
		}
		else if (anno instanceof MinLength) {
			return minLength(((MinLength) anno).value());
		}
		else if (anno instanceof OmojiKomoji) {
			OmojiKomojiElm value = ((OmojiKomoji) anno).value();
			if (OmojiKomojiElm.OMOJI.equals(value)) {
				return uppercase(true);
			}
		}
		else if (anno instanceof Zenhankaku) {
			ZenhankakuElm value = ((Zenhankaku) anno).value();
			if (ZenhankakuElm.HANKAKU.equals(value)) {
				return multibyte(false);
			}
		}
		else if (anno instanceof KyokaMoji) {
			return pattern(((KyokaMoji) anno).value());
		}
		else if (anno instanceof Scale) {
			return scale(((Scale) anno).value());
		}
		else if (anno instanceof Required) {
			return required(true);
		}
		else if (anno instanceof Minus) {
			return minus(true);
		}
		else if (anno instanceof InputString) {
			return inputclass(PropertyRuleTypeInputClass.string);
		}
		else if (anno instanceof InputNumber) {
			return inputclass(PropertyRuleTypeInputClass.number);
		}
		else if (anno instanceof InputDate) {
			return inputclass(PropertyRuleTypeInputClass.date);
		}
		else if (anno instanceof Options) {
			String name = ((Options) anno).value();
			if (StringUtils.isEmpty(name) && field != null) {
				name = field.getName();
			}
			if (StringUtils.isEmpty(name)) {
				name = "undefined";
			}
			return options(name);
		}

		return null;
	}

	/**
	 * 生成（クラスより）
	 * 初期値用
	 * @param clazz
	 * @return
	 */
	public PropertyRule create(Class<?> clazz) {
		if (String.class.isAssignableFrom(clazz)) {
			return inputclass("string");
		}
		else if (BigDecimal.class.isAssignableFrom(clazz)) {
			return inputclass("number");
		}
		else if (Date.class.isAssignableFrom(clazz)) {
			return inputclass("date");
		}
		else if (Hiduke.class.isAssignableFrom(clazz)) {
			return inputclass("date");
		}
		else if (Nengetsu.class.isAssignableFrom(clazz)) {
			return inputclass("date");
		}
		// TODO:Timestamp
		return inputclass("etc");
	}

	/**
	 * 最小桁数
	 * @param value
	 * @return
	 */
	public PropertyRule minLength(int value) {
		return createRule(PropertyRuleType.minlength, String.valueOf(value));
	}

	/**
	 * 最大桁数
	 * @param value
	 * @return
	 */
	public PropertyRule maxLength(int value) {
		return createRule(PropertyRuleType.maxlength, String.valueOf(value));
	}

	/**
	 * サイズ
	 * @param value
	 * @return
	 */
	public PropertyRule size(int value) {
		return createRule(PropertyRuleType.size, String.valueOf(value));
	}

	/**
	 * サイジング（サイズを設定するか）
	 * @param value
	 * @return
	 */
	public PropertyRule sizing(boolean value) {
		return createRule(PropertyRuleType.sizing, String.valueOf(value));
	}

	/**
	 * 全角の扱い
	 * @param value
	 * @return
	 */
	public PropertyRule multibyte(boolean value) {
		return createRule(PropertyRuleType.multibyte, String.valueOf(value));
	}

	/**
	 * 大文字
	 * @param value
	 * @return
	 */
	public PropertyRule uppercase(boolean value) {
		return createRule(PropertyRuleType.uppercase, String.valueOf(value));
	}

	/**
	 * 入力パターン（js正規表現）
	 * @param pattern
	 * @return
	 */
	public PropertyRule pattern(PropertyRuleTypePattern pattern) {
		return pattern(pattern.getExp());
	}

	/**
	 * 入力パターン（js正規表現）
	 * @param value
	 * @return
	 */
	public PropertyRule pattern(String value) {
		return createRule(PropertyRuleType.pattern, value);
	}

	/**
	 * 整数部桁数
	 * @param value
	 * @return
	 */
	public PropertyRule decimalLength(int value) {
		return createRule(PropertyRuleType.decimallength, String.valueOf(value));
	}

	/**
	 * マイナス許可
	 * @param value
	 * @return
	 */
	public PropertyRule minus(boolean value) {
		return createRule(PropertyRuleType.minus, String.valueOf(value));
	}

	/**
	 * 小数部桁数
	 * @param value
	 * @return
	 */
	public PropertyRule scale(int value) {
		return createRule(PropertyRuleType.scale, String.valueOf(value));
	}

	/**
	 * 必須
	 * @param value
	 * @return
	 */
	public PropertyRule required(boolean value) {
		return createRule(PropertyRuleType.required, String.valueOf(value));
	}

	/**
	 * 画面入力クラス
	 * @param inputClass
	 * @return
	 */
	public PropertyRule inputclass(PropertyRuleTypeInputClass inputClass) {
		return inputclass(inputClass.name());
	}

	/**
	 * 画面入力クラス
	 * @param value
	 * @return
	 */
	public PropertyRule inputclass(String value) {
		return createRule(PropertyRuleType.inputclass, value);
	}

	/**
	 * オプション選択
	 * @param value
	 * @return
	 */
	public PropertyRule options(String value) {
		return createRule(PropertyRuleType.options, value);
	}

	/**
	 * ルール生成
	 * @param type
	 * @param value
	 * @return
	 */
	public PropertyRule createRule(PropertyRuleType type, String value) {
		return new PropertyRule(type, value);
	}

}
