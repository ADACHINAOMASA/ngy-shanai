package ngyshanai.common.kbn;

import nis.framework.kbn.GeneralKbn;
import nis.framework.kbn.KbnUtils;
import nis.framework.web.option.SelectOptions;

@SelectOptions
public enum YoyakuBlockEnd implements GeneralKbn {

	/**
	 * コマ1
	 */
	KOMA1("1", "8:30"),

	/**
	 * コマ2
	 */
	KOMA2("2", "9:00"),
	
	/**
	 * コマ3
	 */
	KOMA3("3", "9:30"),
	
	/**
	 * コマ4
	 */
	KOMA4("4", "10:00"),
	
	/**
	 * コマ5
	 */
	KOMA5("5", "10:30"),
	
	/**
	 * コマ6
	 */
	KOMA6("6", "11:00"),
	
	/**
	 * コマ7
	 */
	KOMA7("7", "11:30"),
	
	/**
	 * コマ8
	 */
	KOMA8("8", "12:00"),
	
	/**
	 * コマ9
	 */
	KOMA9("9", "12:30"),
	
	/**
	 * コマ10
	 */
	KOMA10("10", "13:00"),
	
	/**
	 * コマ11
	 */
	KOMA11("11", "13:30"),
	
	/**
	 * コマ12
	 */
	KOMA12("12", "14:00"),
	
	/**
	 * コマ13
	 */
	KOMA13("13", "14:30"),
	
	/**
	 * コマ14
	 */
	KOMA14("14", "15:00"),
	
	/**
	 * コマ15
	 */
	KOMA15("15", "15:30"),
	
	/**
	 * コマ16
	 */
	KOMA16("16", "16:00"),
	
	/**
	 * コマ17
	 */
	KOMA17("17", "16:30"),
	
	/**
	 * コマ18
	 */
	KOMA18("18", "17:00"),
	
	/**
	 * コマ19
	 */
	KOMA19("19", "17:30"),
	
	/**
	 * コマ20
	 */
	KOMA20("20", "18:00"),
	
	/**
	 * コマ21
	 */
	KOMA21("21", "18:30"),
	
	/**
	 * コマ22
	 */
	KOMA22("22", "19:00"),
	
	/**
	 * コマ23
	 */
	KOMA23("23", "19:30"),
	
	/**
	 * コマ24
	 */
	KOMA24("24", "20:00"),
	
	/**
	 * 未指定（必ず作成すること）
	 */
	NULL(null, null);

	private String literal;

	private String label;

	/**
	 * コンストラクタ
	 */
	private YoyakuBlockEnd(String literal, String label) {
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
	static public YoyakuBlockEnd literalOf(String literal) {
		return KbnUtils.literalOf(literal, values());
	}

	/**
	 * 区分内容を表す内部表現から直接外部表現を取得
	 */
	static public String label(String literal) {
		return literalOf(literal).label();
	}
}
