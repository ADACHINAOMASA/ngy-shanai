package nis.framework.web.propertyrule;

/**
 * プロパティルール
 * @author Kengo-Nagase
 *
 */
public class PropertyRule {

	/**
	 * タイプ
	 */
	private PropertyRuleType type;

	/**
	 * 値
	 */
	private String value;

	/**
	 *
	 * @param type
	 * @param value
	 */
	public PropertyRule(PropertyRuleType type, String value) {
		this.type = type;
		this.value = value;
	}

	/**
	 * typeを取得します。
	 * @return type
	 */
	public PropertyRuleType getType() {
	    return type;
	}

	/**
	 * typeを設定します。
	 * @param type type
	 */
	public void setType(PropertyRuleType type) {
	    this.type = type;
	}

	/**
	 * valueを取得します。
	 * @return value
	 */
	public String getValue() {
	    return value;
	}

	/**
	 * valueを設定します。
	 * @param value value
	 */
	public void setValue(String value) {
	    this.value = value;
	}

}
