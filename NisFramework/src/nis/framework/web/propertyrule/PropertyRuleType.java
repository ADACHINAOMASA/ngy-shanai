package nis.framework.web.propertyrule;

/**
 *
 * <p>
 * プロパティルール種類<br>
 * </p>
 * @author Kengo-Nagase
 *
 */
public enum PropertyRuleType {
	type,
	// 最小桁
	minlength,
	// 最大桁
	maxlength,
	// サイズ
	size,
	// サイジング有効
	sizing,
	// 全角半角の扱い
	multibyte,
	// 大文字の指定
	uppercase,
	// 入力可能規則（js正規表現）
	pattern,
	// 整数部桁数
	decimallength,
	// マイナスの扱い
	minus,
	// 小数部桁数
	scale,
	// 必須
	required,
	// 入力クラス
	inputclass,
	// オプション選択
	options,
}
