package nis.framework.web.option;

/**
 *
 * <p>
 * オプション（キーとラベルのセット）クラス<br>
 * HTMLのoptionと対応
 * </p>
 * @author Kengo-Nagase
 *
 */
public class Option {

	/**
	 * ラベル
	 */
	private String label;

	/**
	 * 値
	 */
	private String value;

	/**
	 *
	 */
	public Option(){}

	/**
	 *
	 * @param label
	 * @param value
	 */
	public Option(String label, String value) {
		this.label = label;
		this.value = value;
	}

	/**
	 * labelを取得します。
	 * @return label
	 */
	public String getLabel() {
	    return label;
	}

	/**
	 * labelを設定します。
	 * @param label label
	 */
	public void setLabel(String label) {
	    this.label = label;
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
