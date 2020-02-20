package nis.framework.web.propertyrule;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * プロパティルール組立ヘルパ<br>
 * PropertyRuleBuilder使用補助クラス
 * </p>
 * @author Kengo-Nagase
 *
 */
public class PropertyRuleBuildHelper {

	//TODO:日付型

	/**
	 * 文字列ルールセット
	 * @param maxlength 最大文字数(バイト数)
	 * @param multibyte 全角を許すか true = 許す
	 * @param required 必須か true = 必須
	 * @param pattern 入力制限パターン(js正規表現)
	 * @return ルール群
	 */
	public static List<PropertyRule> string(int maxlength, boolean multibyte, boolean required, String pattern) {
		return join(
				PropertyRuleFactory.get().maxLength(maxlength)
				,PropertyRuleFactory.get().multibyte(multibyte)
				,PropertyRuleFactory.get().required(required)
				,PropertyRuleFactory.get().pattern(pattern)
				);
	}

	/**
	 * 文字列ルールセット
	 * @param maxlength 最大文字数(バイト数)
	 * @param multibyte 全角を許すか true = 許す
	 * @param required 必須か true = 必須
	 * @return ルール群
	 */
	public static List<PropertyRule> string(int maxlength, boolean multibyte, boolean required) {
		return join(
				PropertyRuleFactory.get().maxLength(maxlength)
				,PropertyRuleFactory.get().multibyte(multibyte)
				,PropertyRuleFactory.get().required(required)
				);
	}

	/**
	 * コード（文字列の内、コードとして扱うもの）ルールセット
	 * 半角大文字英数字オンリーの文字列
	 * @param maxlength 最大文字数(バイト数)
	 * @param required 必須か true = 必須
	 * @return ルール群
	 */
	public static List<PropertyRule> code(int maxlength, boolean required) {
		return join(
				string(maxlength, false, required, PropertyRuleTypePattern.CODE.getExp())
				,PropertyRuleFactory.get().uppercase(true)
				);
	}

	/**
	 * コード（文字列の内、コードとして扱うもの）ルールセット（否必須）
	 * 半角大文字英数字オンリーの文字列
	 * @param maxlength 最大文字数(バイト数)
	 * @return ルール群
	 */
	public static List<PropertyRule> code(int maxlength) {
		return code(maxlength, false);
	}

	/**
	 * コード（文字列の内、コードとして扱うもの）ルールセット（必須）
	 * 半角大文字英数字オンリーの文字列
	 * @param maxlength 最大文字数(バイト数)
	 * @return
	 */
	public static List<PropertyRule> codeRequired(int maxlength) {
		return code(maxlength, true);
	}

	/**
	 * 数値のルールセット(小数点なし)
	 * @param maxlength 最大整数桁数
	 * @return
	 */
	public static List<PropertyRule> number(int maxlength) {
		return number(maxlength, false, false);
	}

	/**
	 * 数値のルールセット（小数点なし）
	 * @param maxlength 最大整数桁数
	 * @param required 必須か true = 必須
	 * @param minus マイナスを許可するか true = 許可
	 * @return
	 */
	public static List<PropertyRule> number(int maxlength, boolean required, boolean minus) {
		return join(PropertyRuleFactory.get().maxLength(maxlength)
				,PropertyRuleFactory.get().required(required)
				,PropertyRuleFactory.get().minus(minus)
				);
	}

	/**
	 * 数値のルールセット
	 * @param maxlength 最大整数桁数
	 * @param scale 小数桁数
	 * @param required 必須か true = 必須
	 * @param minus マイナスを許可するか true = 許可
	 * @return
	 */
	public static List<PropertyRule> number(int maxlength, int scale, boolean required, boolean minus) {
		return join(PropertyRuleFactory.get().maxLength(maxlength)
				,PropertyRuleFactory.get().scale(scale)
				,PropertyRuleFactory.get().required(required)
				,PropertyRuleFactory.get().minus(minus)
				);
	}

	/**
	 * ルール同士の結合
	 * @param list
	 * @param rules
	 * @return
	 */
	private static List<PropertyRule> join(List<PropertyRule> list, PropertyRule...rules) {
		List<PropertyRule> joinRules = new ArrayList<PropertyRule>();
		joinRules.addAll(list);
		joinRules.addAll(join(rules));
		return joinRules;
	}

	/**
	 * ルール同士の結合
	 * @param rules
	 * @return
	 */
	private static List<PropertyRule> join(PropertyRule...rules) {
		List<PropertyRule> joinRules = new ArrayList<PropertyRule>();
		for (PropertyRule rule : rules) {
			joinRules.add(rule);
		}
		return joinRules;
	}

}
