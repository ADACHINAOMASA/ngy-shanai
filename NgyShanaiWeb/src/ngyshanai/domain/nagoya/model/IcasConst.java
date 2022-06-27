/*
 * IcasConst.java
 *
 * Created on 2006/12/13, 9:52
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package ngyshanai.domain.nagoya.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Hirohiko-Matsushita
 */
public class IcasConst {
    
    /** Creates a new instance of IcasConst */
    private IcasConst() {
    }
    
    // 形状 KJYO
    public static final String KJYO_1 = "ｲﾀ";
    public static final String KJYO_2 = "ｻｰｸﾙ";
    public static final String KJYO_3 = "ｺｲﾙ";
    
    // 納期符号 G2 2014/07/28 納期符号コード追加対応　Y.moriyama
    /*
    public static final String G2_1 = "ｼﾞｾﾞﾝﾔｸｿｸﾋﾝ ";
    public static final String G2_2 = "ｺｷﾔｸﾉｳｷﾋﾝ   ";
    public static final String G2_3 = "ﾉｳｷﾁﾖｳｾｲﾋﾝ  ";
    public static final String G2_4 = "ｴｲｷﾞﾖｳﾉｳｷﾋﾝ ";
    public static final String G2_5 = "ｶﾝﾊﾞﾝﾋﾝ     ";
    */
    public static final String G2_1 = "ｼﾞｾﾞﾝﾔｸｿｸﾋﾝ ";
    public static final String G2_2 = "ｺｷﾔｸｼﾃｲﾋﾝ   ";
    public static final String G2_3 = "ﾀﾝﾉｳｷﾋﾝ     ";
    public static final String G2_4 = "ﾄｳｹﾞﾂｳﾘｱｹﾞ  ";
    public static final String G2_5 = "ｺｷﾔｸｻﾞｲｺﾋﾝ  ";
    public static final String G2_6 = "ｶﾝﾊﾞﾝﾋﾝ     ";
    public static final String G2_C = "C:ﾁﾕｳｶﾝｿｻﾞｲ ";
    public static final String G2_D = "D:HOTﾀﾞﾐｰ   ";
    public static final String G2_K = "K:ｶﾜｻﾞｲ     ";
    public static final String G2_G = "G:ｷﾞｶｸﾋﾝ    ";
    public static final String G2_S = "S:ﾖﾋﾞｼｻﾞｲ   ";
    public static final String G2_Y = "Y:ｹﾞﾝｶﾐﾂﾓﾘ  ";
    public static final String G2_Z = "Z:ﾏｽﾀｰﾛﾂﾄ   ";

    
    // 直・倉区分,余剰対象品,設備名 YOJYO
    public static final String YOJYO_1 = "ﾁﾖｸ ";
    public static final String YOJYO_2 = "ﾏﾙﾖ ";
    
    // 伝達メッセージ CONVNA
    public ArrayList getConvna() {
        ArrayList list = new ArrayList();      
        list.add("00               00");
        list.add("01CM3............00");
        list.add("00               00");
        list.add("03 * ﾌｼﾞPS       06");
        list.add("00               00");
        list.add("05 *H14,6 ﾊｸｼﾞ   04");
        list.add("00               00");
        list.add("00               00");
        list.add("00               00");
        list.add("00               00");
        list.add("10 *FS ｲﾀ PSW,ST 02");
        list.add("11 *FS4 ｲﾀ (A)   04");
        list.add("12 *FS4 ｲﾀ (G)   04");
        list.add("13 *FS ｲﾀ ｿﾉﾀ    04");
        list.add("14 *0.25=> ﾌｲﾝ   02");
        list.add("15 *TLV ｺｲﾙ PS   02");
        list.add("16 *ｺｲﾙ          02");
        list.add("17 *300P         02");
        list.add("18 *ｻ-ｸﾙ         01");
        list.add("19 * ｿ ﾉ ﾀ       01");
        list.add("00               00");
        list.add("21CM1............00");
        list.add("00               00");
        list.add("23 *H14,6 ﾊｸｼﾞ   06");
        list.add("00               00");
        list.add("25  ｿﾉﾀ ﾊｸｼﾞ(ILS)03");
        list.add("26  ｿﾉﾀ ﾊｸｼﾞ     08");
        list.add("27  TLV FS2      04");
        list.add("28  ﾚﾍﾞﾗ-        03");
        list.add("29  VC           02");
        list.add("30  CAF FS4      04");
        list.add("31  TLV FS4(A)   04");
        list.add("32  TLV FS4(G)   04");
        list.add("33  FS4 ｲﾀ (A)   04");
        list.add("34  FS4 ｲﾀ (G)   04");
        list.add("35  BM  FS3      04");
        list.add("36  TLV ｺｲﾙ      02");
        list.add("37  TLV ｺｲﾙ  PS  02");
        list.add("38  LS  ｺｲﾙ      04");
        list.add("00               00");
        list.add("40  0.25=> ﾌｲﾝ   04");
        list.add("00               00");
        list.add("42  GSS ｺｲﾙ      02");
        list.add("00               00");
        list.add("00               00");
        list.add("00               00");
        list.add("46  AP1 AP3      04");
        list.add("47  AP2 AP4      03");
        list.add("48  CS           01");
        list.add("49  P            02");
        list.add("50  300P         01");
        list.add("00               00");
        list.add("00               00");
        list.add("00               00");
        list.add("00               00");
        list.add("00               00");
        list.add("00               00");
        list.add("00               00");
        list.add("58  QF CM        01");
        list.add("59   ｿ ﾉ ﾀ       01");
        list.add("00               00");
        list.add("61ﾌﾟﾚ-ﾄ..........00");
        list.add("00               00");
        list.add("63  2HI          04");
        list.add("00               00");
        list.add("65  2HI QF       01");
        list.add("00               00");
        list.add("00               00");
        list.add("68  HOT          02");
        list.add("00               00");
        list.add("70  HOT QF       01");
        list.add("00               00");
        list.add("00               00");
        list.add("73ALL............00");
        list.add("00               00");        
        return list;
    }

    //マグカード発行メッセージ
    public static final String MASMSG_1 = "MAGﾊﾂｺｳｽﾞﾐ";
    public static final String MASMSG_2 = "MAGﾐﾊﾂｺｳ";
    
    // 登録区分メッセージ MSG1
    public static final String MSG1_1 = "ｻｲｶｺｳ     ";
    public static final String MSG1_2 = "ﾊﾝﾄﾞﾘﾝｸﾞ  ";
    public static final String MSG1_3 = "ｼﾞﾕﾀｸ ﾖﾘ  ";
    public static final String MSG1_4 = "ﾎﾘﾕｳﾋﾝ ﾖﾘ ";
    public static final String MSG1_5 = "ﾌﾘｶｴ ﾖﾘ   ";
    public static final String MSG1_6 = "ﾎﾘﾕｳ ｼｼﾞ  ";
    public static final String MSG1_7 = "ﾋﾉﾍﾞ ﾖﾃｲ  ";
    public static final String MSG1_8 = "ｼﾕｳｾｲ     ";
    public static final String MSG1_9 = "ﾄﾘｹｼ      ";
    
    // 切取指示メッセージ MSG2
    public static final String MSG2_1 = "          ";
    public static final String MSG2_2 = "ｻｲﾃﾊｲ     ";
    public static final String MSG2_3 = "          ";
    public static final String MSG2_4 = "ｹｲｶｸ ｾｲｻﾝ ";
    public static final String MSG2_5 = "ｻｲｼﾞﾄﾞﾘ   ";
    public static final String MSG2_6 = "          ";
    public static final String MSG2_7 = "          ";
    public static final String MSG2_8 = "          ";
    public static final String MSG2_9 = "          ";
    
    /** 棚卸メッセージ MSG3_1
     *  TANA-MSG.
     *     02  FILLER          PIC  X(5)   VALUE   'ﾀﾅNO.'.
     *     02  TANA-SEQ1       PIC  9(2).
     *     02  FILLER          PIC  X(1)   VALUE   '-'.
     *     02  TANA-SEQ2       PIC  9(3).
     *     02  TANA-WT         PIC  ZZ,ZZ9.9B.
     *     02  FILLER          PIC  X(2)   VALUE   'KG'. 
     */
    public static final String MSG3_1_1 = "ﾀﾅNO.";
    public static final String MSG3_1_2 = "-";
    public static final String MSG3_1_3 = "KG";
    
    // 火延区分メッセージ MSG3_2
    public static final String MSG3_2_1 = "ﾋﾉﾍﾞ ﾏｴ";
    public static final String MSG3_2_2 = "PIT ﾗﾝ ｺﾞ";
    
    // 引当・保留メッセージ MSG3_3
    public static final String MSG3_3_1 = "Aｺﾞ(ｹﾝｼﾖｳ)";
    public static final String MSG3_3_2 = "* ｹﾝｻ A *";
    public static final String MSG3_3_3 = "* AｺﾞBｺﾞ *";
    public static final String MSG3_3_4 = "* AﾏｴBｺﾞ *";
    public static final String MSG3_3_5 = "Aﾏｴ(ｹﾝｼﾖｳ)";
    public static final String MSG3_3_6 = "+ﾋｷｱﾃｽﾞﾐ+";
    public static final String MSG3_3_7 = "*ﾋｷｱﾃｽﾞﾐ*";
    public static final String MSG3_3_8 = " ﾁﾖｳｾｲﾏﾁ";
    public static final String MSG3_3_9 = "PAC ﾁﾖｳﾏﾁ";
    public static final String MSG3_3_10 = "*ｶﾘｸﾗﾋｷｱﾃ*";
    public static final String MSG3_3_11 = "*ｶﾘｸﾗｹﾝｻA*";
    
    // ロットアウトメッセージ MSG3_4
    public static final String MSG3_4_1 = "* ﾛﾂﾄｱｳﾄ *";
    public static final String MSG3_4_2 = "* ｶﾝﾘﾖｳ  *";    
    
    // 工程ストア－メッセージ MSG3_5
    public static final String MSG3_5_1 = "ｺｳﾃｲ ｽﾄｱ-";
    
    // 特急・払出区分メッセージ MSG4
    public static final String MSG4_1 = "ﾔｸｿｸ";
    public static final String MSG4_2 = "ﾄﾂｷﾕｳ";
    public static final String MSG4_3 = "(ｶﾘﾋｷｱﾃ)";
    public static final String MSG4_4 = "(ｸﾗｲﾚ)";
    public static final String MSG4_5 = "(ﾌﾘｶｴ ｶｺｳ)";    
    public static final String MSG4_6 = "(ｸｽﾞﾊﾗｲﾀﾞｼ)";
    //add 2017/4/27
    public static final String MSG4_7 = "ｿﾄｲｷ";
    public static final String MSG4_8 = "ｿﾄﾓﾄﾞﾘ";

    // 輸出・保税区分メッセージ MSG5
    public static final String MSG5_1 = "ﾎｾﾞｲｽﾗﾌﾞ";
    public static final String MSG5_2 = "ﾕｼﾕﾂ ﾋﾝ";
    
    // テスト品メッセージ MSG6 ﾃｽﾄ ﾋﾝ(NOKI3-RENBAN）
    public static final String MSG6_1 = "ﾃｽﾄ ﾋﾝ(";
    public static final String MSG6_2 = ")";
    
    // 保留区分メッセージ MSG7
    public static final String MSG7_1 = "ﾎﾘﾕｳ      ";
    public static final String MSG7_2 = "Rﾎﾘﾕｳ     ";
    public static final String MSG7_3 = "Fﾎﾘﾕｳ     ";
    public static final String MSG7_4 = "Aﾎﾘﾕｳ     ";
    public static final String MSG7_5 = "Kﾎﾘﾕｳ     ";
    public static final String MSG7_6 = "Bﾎﾘﾕｳ     ";
    public static final String MSG7_7 = "Sﾎﾘﾕｳ     ";
    public static final String MSG7_8 = "ﾎﾘﾕｳ ｶｲｼﾞﾖ";
    public static final String MSG7_9 = "          ";
	
    // 倉庫行きHOTコイル保留区分メッセージ
    public static final String SOKOIKI_MSG = "(ｿｳｺ)";
    
    // LN変換区分メッセージ MSG8
    public static final String MSG8_1 = "L/N";
    
    // 技術保留区分メッセージ MSG9
    public static final String MSG9 = "Gﾎ";
	/**
	 * 保留退避区分を表すクラスです。
	 * @author Hideyuki-Yamamoto
	 * @since 2011/01/21
	 */
	public static class HokbnSave {
		/**
		 * 工程保留(区分:1, 画面表示:Zﾎ)
		 */
		public static final HokbnSave HORYU_Z = new HokbnSave("1", "Zﾎ");
		
		/**
		 * HOT保留(区分:2, 画面表示:Rﾎ)
		 */
		public static final HokbnSave HORYU_R = new HokbnSave("2", "Rﾎ");
		
		/**
		 * 仕上保留(区分:3, 画面表示:Fﾎ)
		 */
		public static final HokbnSave HORYU_F = new HokbnSave("3", "Fﾎ");
		
		/**
		 * 保留(区分:4, 画面表示:Aﾎ)
		 */
		public static final HokbnSave HORYU_A = new HokbnSave("4", "Aﾎ");
		
		/**
		 * 梱包保留(区分:5, 画面表示:Kﾎ)
		 */
		public static final HokbnSave HORYU_K = new HokbnSave("5", "Kﾎ");
		
		/**
		 * スケジューラ保留(区分:7, 画面表示:Sﾎ)
		 */
		public static final HokbnSave HORYU_S = new HokbnSave("7", "Sﾎ");
		
		private final String horyuKbn;
		private final String dispMessage;
		private HokbnSave(String horyuKbn, String dispMessage) {
			this.horyuKbn = horyuKbn;
			this.dispMessage = dispMessage;
		}
		private String getHoryuKbn() {
			return horyuKbn;
		}
		private String getDispMessage() {
			return dispMessage;
		}
		
		/**
		 * 保留区分から画面出力に用いるメッセージを取得します。
		 * @param horyuKbn 保留区分
		 * @return 画面出力に用いるメッセージ
		 */
		public static String getDispMsgFromHoryuKbn(String horyuKbn) {
			HokbnSave hokbnSave = enumMapper.get(horyuKbn);
			return hokbnSave == null ? MSG7_6 : "Bﾎ<-" + hokbnSave.getDispMessage();
		}
		
		private static class enumMapper {
			private static final Map enumMap;
			static {
				enumMap = new HashMap();
				put(HORYU_Z);
				put(HORYU_R);
				put(HORYU_F);
				put(HORYU_A);
				put(HORYU_K);
				put(HORYU_S);
			}
			
			private static HokbnSave get(String key) {
				return (HokbnSave) enumMap.get(key);
			}
			
			private static void put(HokbnSave hokbnSave) {
				if (enumMap.containsKey(hokbnSave.getHoryuKbn())) {
					throw new IllegalArgumentException("保留区分 " + hokbnSave.getHoryuKbn() + " が重複しています。");
				}
				enumMap.put(hokbnSave.getHoryuKbn(), hokbnSave);
			}
		}
	}
	
}
