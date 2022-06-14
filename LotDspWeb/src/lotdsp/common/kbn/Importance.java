package lotdsp.common.kbn;

import nis.framework.kbn.GeneralKbn;
import nis.framework.kbn.KbnUtils;
import nis.framework.web.option.SelectOptions;

@SelectOptions
public enum Importance implements GeneralKbn{
	
	/**
	 * 重要
	 */
	IMPORTANT("0", "重要"),
	
	/**
	 * 普通
	 */
	NORMAL("1", "普通"),
	
	/**
	 * 場所変更可
	 */
	BASHOHENKOOK("2", "場所変更可"),
	
	/**
	 * 時間変更可
	 */
	JIKANHENKOOK("3", "時間変更可"),
	
	/**
	 * 未指定（必ず作成すること）
	 */
	NULL(null, null);

	private String literal;

	private String label;

	/**
	 * コンストラクタ
	 */
	private Importance(String literal, String label) {
		this.literal = literal;
		this.label = label;
	}

	/**
	 * 区分内容を表す内部表現を取得
	 */
	public String literal() {
		return literal;
	}

	/**
	 * 区分内容を表す外部表現を取得
	 */
	public String label() {
		return label;
	}

	/**
	 * 区分内容を表す文字列から列挙子を取得
	 * (フレームワーク規約)
	 */
	static public Importance literalOf(String literal) {
		return KbnUtils.literalOf(literal, values());
	}

	/**
	 * 区分内容を表す内部表現から直接外部表現を取得
	 */
	static public String label(String literal) {
		return literalOf(literal).label();
	}
}

