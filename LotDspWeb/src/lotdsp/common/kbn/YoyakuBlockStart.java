package lotdsp.common.kbn;

import nis.framework.kbn.GeneralKbn;
import nis.framework.kbn.KbnUtils;
import nis.framework.web.option.SelectOptions;

@SelectOptions
public enum YoyakuBlockStart implements GeneralKbn {

	/**
	 * コマ1
	 */
	KOMA1("1", "8:00"),

	/**
	 * コマ2
	 */
	KOMA2("2", "8:30"),
	
	/**
	 * コマ3
	 */
	KOMA3("3", "9:00"),
	
	/**
	 * コマ4
	 */
	KOMA4("4", "9:30"),
	
	/**
	 * コマ5
	 */
	KOMA5("5", "10:00"),
	
	/**
	 * コマ6
	 */
	KOMA6("6", "10:30"),
	
	/**
	 * コマ7
	 */
	KOMA7("7", "11:00"),
	
	/**
	 * コマ8
	 */
	KOMA8("8", "11:30"),
	
	/**
	 * コマ9
	 */
	KOMA9("9", "12:00"),
	
	/**
	 * コマ10
	 */
	KOMA10("10", "12:30"),
	
	/**
	 * コマ11
	 */
	KOMA11("11", "13:00"),
	
	/**
	 * コマ12
	 */
	KOMA12("12", "13:30"),
	
	/**
	 * コマ13
	 */
	KOMA13("13", "14:00"),
	
	/**
	 * コマ14
	 */
	KOMA14("14", "14:30"),
	
	/**
	 * コマ15
	 */
	KOMA15("15", "15:00"),
	
	/**
	 * コマ16
	 */
	KOMA16("16", "15:30"),
	
	/**
	 * コマ17
	 */
	KOMA17("17", "16:00"),
	
	/**
	 * コマ18
	 */
	KOMA18("18", "16:30"),
	
	/**
	 * コマ19
	 */
	KOMA19("19", "17:00"),
	
	/**
	 * コマ20
	 */
	KOMA20("20", "17:30"),
	
	/**
	 * コマ21
	 */
	KOMA21("21", "18:00"),
	
	/**
	 * コマ22
	 */
	KOMA22("22", "18:30"),
	
	/**
	 * コマ23
	 */
	KOMA23("23", "19:00"),
	
	/**
	 * コマ24
	 */
	KOMA24("24", "19:30"),
	
	/**
	 * 未指定（必ず作成すること）
	 */
	NULL(null, null);

	private String literal;

	private String label;

	/**
	 * コンストラクタ
	 */
	private YoyakuBlockStart(String literal, String label) {
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
	static public YoyakuBlockStart literalOf(String literal) {
		return KbnUtils.literalOf(literal, values());
	}

	/**
	 * 区分内容を表す内部表現から直接外部表現を取得
	 */
	static public String label(String literal) {
		return literalOf(literal).label();
	}
}
