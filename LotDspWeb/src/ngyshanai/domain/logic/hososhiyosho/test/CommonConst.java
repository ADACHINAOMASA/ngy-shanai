package ngyshanai.domain.logic.hososhiyosho.test;





/**
 *
 * @author Hirohiko-Matsushita
 */
public class CommonConst {

    /** Creates CMSchViewInfo new instance of CommonConst */
    public CommonConst() {
    }

    /**
     * 登録ステータス
     */
    //一般
    public static final int TRKSTS_IPPAN_CODE = 0;
    //開始
    public static final int TRKSTS_KAISI_CODE = 1;
    //実績待ち
    public static final int TRKSTS_JISSEKI_CODE = 2;
    //継続
    public static final int TRKSTS_KEIZOKU_CODE = 3;
    //完了
    public static final int TRKSTS_KANRYO_CODE = 9;

    /**
     * データ区分
     */
    //仕掛データ
    public static final int DATAKBN_SIKAKARI_CODE = 1;
    //ロット情報
    public static final int DATAKBN_LOTINF_CODE = 2;
    //点検版
    public static final int DATAKBN_TENKENBAN_CODE = 7;
    public static final String TENKENBAN_NAME = "点検板";
    //返り材
    public static final int DATAKBN_KAERIZAI_CODE = 8;
    public static final String KAERIZAI_NAME = "返り材";
    //設備停止
    public static final int DATAKBN_TEISI_CODE = 9;
    public static final String TEISI_NAME = "設備停止";
    public static final String TEISI_ITEM = "TEISIJPNAME";
    public static final String STOPTIME_ITEM = "ROLLTIME";
    public static final String CHOKI_TEISI_CD = "2000";  // V2.1.00 追加


    //冷延スケジュール一覧の設備停止名称を列幅に合わせて分配するリスト項目
    public static final String[] TEISI_DISP_ITEM_NAME = {"COILNO","ltNo","lta","inAtsu","otAtsu","otHaba","draft","xcompe",};
    public static final String[] TEISI_DISP_ITEM_DIGIT = {"4","5","3","3","3","3","3","3"};

    /**
     * 画面タイプ名
     * 冷延スケジュール一覧自動画面のコードを変更した場合は、
     * schList4CM.jsの54行目のform1:dispTypeのswitch文を修正して下さい
     */
    //メニュー画面
    public static final int DISP_TYPE_MENU = 0;
    //冷延スケジュール一覧自動画面
    public static final int DISP_TYPE_AUTO = 1;
    //冷延スケジュール一覧手動画面（一般用全機能版）
    public static final int DISP_TYPE_MANU_ALL = 2;
    //冷延スケジュール一覧手動画面（一般用完了信号許可版）
    public static final int DISP_TYPE_MANU_KANRYO = 3;
    //冷延スケジュール一覧手動画面（段取り変更用）
    public static final int DISP_TYPE_MANU_DANDORI = 4;
    //サイクル一覧
    public static final int DISP_TYPE_CYCLE = 5;
    //冷延スケジュール参照
    public static final int DISP_TYPE_SCHEDULE = 6;
    //エラー画面
    public static final int DISP_TYPE_ERROR = 9;

    //実績操作コード
    //ロット実績
    public static final String JISSEKI_OP_CODE_LOT = "0101";
    //設備停止
    public static final String JISSEKI_OP_CODE_TEISI = "0201";
    //オペレータ登録
    public static final String JISSEKI_OP_CODE_OPE = "0301";
    //ロール登録
    public static final String JISSEKI_OP_CODE_ROLL = "0401";

    //項目フォーマット
    //入側板厚
    public static final String FORMAT_INATSU = "#####0.0##";
    //出側板厚
    public static final String FORMAT_OTATSU = "#####0.0##";
    //圧下率
    public static final String FORMAT_AKKARITSU = "#####0.0";
    //冷延幅
    public static final String FORMAT_OTHABA = "#####0";
    //製品板厚
    public static final String FORMAT_LTX = "##0.00";
    //製品板幅
    public static final String FORMAT_LTY = "####0";
    //コンペ値
    public static final String FORMAT_XCOMPE = "#####0.0";
    //計算コンペ値
    public static final String FORMAT_CXCOMPE = "#####0.00";
    //設備停止時間（日）
    public static final String FORMAT_STOPTIME_DAY = "#####0.0";
    //設備停止時間（分）
    public static final String FORMAT_STOPTIME_MIN = "#####0";
    //ｻｲｸﾙ
    public static final String FORMAT_CYCLE = "#####0";
    //ｺｲﾙ
    public static final String FORMAT_COIL = "#####0";
    //ﾊﾟｽ
    public static final String FORMAT_PASS = "#####0";
    //ｻｲｸﾙｵｰﾀﾞｰ
    public static final String FORMAT_CYCLEORDER = "#####0";
    //ﾊﾟﾀｰﾝ
    public static final String FORMAT_PATTERN = "#####0";

    //開始ボタンの実行間隔（単位：秒）
    public static final int START_INTERVAL_TIME = 10;

}