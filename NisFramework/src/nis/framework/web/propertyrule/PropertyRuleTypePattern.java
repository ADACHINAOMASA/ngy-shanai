package nis.framework.web.propertyrule;

/**
 *
 * <p>
 * プロパティルール入力制限パターン<br>
 * プリセットにする予定
 * </p>
 * @author Kengo-Nagase
 *
 */
public enum PropertyRuleTypePattern {
	/**
	 * コード系(英数字のみ)
	 */
	CODE("\\w"),
	/**
	 * 数値のみ
	 */
	NUMBER("[0-9]"),
	/**
	 * アルファベットのみ
	 */
	ALPHABET("[A-z]"),
	/**
	 * アルファベットと数値のみ
	 */
	NUMBER_ALPHABET("[0-9A-z]");

	private String exp;

	private PropertyRuleTypePattern(String exp) {
		this.exp = exp;
	}

	public String getExp() {
		return exp;
	}

}
