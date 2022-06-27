package ngyshanai.common;

public class AppConst {

	public static final int MAX_RETRY = 10;

	// 検索最大件数
	public static final int KENSAKU_KENSU_MAX = 1000;

	// 検索件数10件
	public static final int KENSAKU_KENSU_TEN = 10;
	
	public static final int MAX_BIKO = 9;

	// 画面モード 新規
	public static final String GAMEN_NEW = "00";
	// 画面モード 訂正 ドラフト
	public static final String GAMEN_TEISEI_DORAFUTO = "10";
	// 画面モード 訂正 確定
	public static final String GAMEN_TEISEI_KAKUTEI = "11";
	// 画面モード 改訂
	public static final String GAMEN_KAITEI = "20";

	// タブモード
	public static final String TAB_KAKO_KEISAN_KAKOHI = "0";
	public static final String TAB_KAKO_KEISAN_ZAIRYO_ITAZAI = "1";
	public static final String TAB_KAKO_KEISAN_ZAIRYO_KATAZAI = "2";
	public static final String TAB_KAKO_KEISAN_HOZAI = "3";
	public static final String TAB_KAKO_KEISAN_NAISEI_CHUKAN_BUHIN = "4";
	public static final String TAB_KAKO_KEISAN_KAKOTSUKI_ALUMI_ZAI = "5";
	public static final String TAB_KAKO_KEISAN_GAICHU_KAKOHIN = "6";

	// 見積管理
	public static final int MITSUMORI_MAX_BIKO = 9;
	public static final int MITSUMORI_MAX_UCHIWAKE = 7;

	// 各種マスタ
	public static final String MITSUMORI_KINGAKU_JOGEN_MST_CD_DEFAULT = "1"; 

	
	public static final String IRAISHA="依頼者";
	public static final String SHONINSHA="見積承認者";
	public static final String SHONINSHA_DAIRI="代理見積承認者";
	public static final String SEIZO_SHONINSHA="製造承認者";
	public static final String SEIZO_SHONINSHA_DAIRI="代理製造承認者";
	public static final String KESSAISHA="決裁者";
	public static final String KESSAISHA_DAIRI="代理決裁者";
	
	//原価積算
	public static final String GAICHU_SETSUBI_CD="12";
	
	// 検索最大件数
	public static final int MAX_EXCEL_RECORD = 500;

	
	// スポットの納入先名
	public static final String SPOT_NONYUSAKI_CD = "99999997";
	
	
}
