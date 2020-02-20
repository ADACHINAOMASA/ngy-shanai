/*
 * LotProgInfoBean.java
 *
 * Created on 2006/10/10, 16:15
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package lotdsp.domain.nagoya.logic;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Hirohiko-Matsushita
 */
public class LotProgInfoBean implements Serializable {

    /** Creates a new instance of LotProgInfoBean */
    public LotProgInfoBean() {
    }

    /**
    * <p>結合キー</p>
    */
    private String	linkkey;
    /**
    * <p>ロット番号</p>
    */
    private String	ltno;
    /**
    * <p>鋳造番号</p>
    */
    private String	cyno;
    /**
    * <p>検査番号</p>
    */
    private String	knno;
    /**
    * <p>オーダー番号</p>
    */
    private String	juno;
    /**
    * <p>需要分野コード</p>
    */
    private String	jbcd;
    /**
    * <p>ビジネスユニットコード</p>
    */
    private String	bucode;
    /**
    * <p>用途コード</p>
    */
    private String	yotoc;
    /**
    * <p>用途名</p>
    */
    private String	yotoname;
    /**
    * <p>材質</p>
    */
    private String	jua;
    /**
    * <p>調質</p>
    */
    private String	jub;
    /**
    * <p>板厚</p>
    */
    private BigDecimal	jux;
    /**
    * <p>板幅</p>
    */
    private BigDecimal	juy;
    /**
    * <p>板丈</p>
    */
    private BigDecimal	juz;
    /**
    * <p>材質</p>
    */
    private String	lta;
    /**
    * <p>調質</p>
    */
    private String	ltb;
    /**
    * <p>板厚</p>
    */
    private BigDecimal	ltx;
    /**
    * <p>板幅</p>
    */
    private BigDecimal	lty;
    /**
    * <p>板丈</p>
    */
    private BigDecimal	ltz;
    /**
    * <p>スラブ記号</p>
    */
    private String	skigo;
    /**
    * <p>スラブ本数</p>
    */
    private BigDecimal	jup;
    /**
    * <p>製品単重</p>
    */
    private BigDecimal	tanw;
    /**
    * <p>比重</p>
    */
    private BigDecimal	hijyu;
    /**
    * <p>投入量</p>
    */
    private BigDecimal	sw;
    /**
    * <p>納期連番３</p>
    */
    private String	noki3_renban;
    /**
    * <p>出荷日</p>
    */
    private Date	nokiymd;
    /**
    * <p>取引先名</p>
    */
    private String	tokui;
    /**
    * <p>送り先名</p>
    */
    private String	oname;
    /**
    * <p>納入先名</p>
    */
    private String	nonyu;
    /**
    * <p>地区コ－ド</p>
    */
    private BigDecimal	chikucd;
    /**
    * <p>販売員コ－ド</p>
    */
    private BigDecimal	jujsno;
    /**
    * <p>営業納期</p>
    */
    private Date	enoki;
    /**
    * <p>工場納期</p>
    */
    private Date	knoki;
    /**
    * <p>購入鋳造年月日時分秒</p>
    */
    private Date	jcdtm;
    /**
    * <p>購入鋳造重量</p>
    */
    private BigDecimal	jcw;
    /**
    * <p>作業年月日時分秒</p>
    */
    private Date	jswdtm;
    /**
    * <p>重量</p>
    */
    private BigDecimal	jsww;
    /**
    * <p>作業年月日時分秒</p>
    */
    private Date	jmdtm;
    /**
    * <p>重量</p>
    */
    private BigDecimal	jmw;
    /**
    * <p>実績_PIT作業_年月日</p>
    */
    private Date	jpdt;
    /**
    * <p>実績_PIT炉番</p>
    */
    private BigDecimal	jprb;
    /**
    * <p>実績_PIT炉順</p>
    */
    private BigDecimal	jprj;

    /**
     * <p>最終更新日</p>
     */
    private Date edtm;

    /**
    * <p>結合キー取得メソッド</p>
    *
    * @return   結合キー
    * @exception なし
    */
    public String getlinkkey(){
            return linkkey;
    }

    /**
    * <p>結合キー設定メソッド</p>
    *
    * @param plinkkey 結合キー
    * @exception なし
    */
    public void setlinkkey( String plinkkey ){
            linkkey = plinkkey;
    }
    /**
    * <p>ロット番号取得メソッド</p>
    *
    * @return    ロット番号
    * @exception なし
    */
    public String getltno(){
            return ltno;
    }

    /**
    * <p>ロット番号設定メソッド</p>
    *
    * @param pltno ロット番号
    * @exception なし
    */
    public void setltno( String pltno ){
            ltno = pltno;
    }

    /**
    * <p>鋳造番号取得メソッド</p>
    *
    * @return    鋳造番号
    * @exception なし
    */
    public String getcyno(){
            return cyno;
    }

    /**
    * <p>鋳造番号設定メソッド</p>
    *
    * @param pcyno 鋳造番号
    * @exception なし
    */
    public void setcyno( String pcyno ){
            cyno = pcyno;
    }

    /**
    * <p>検査番号取得メソッド</p>
    *
    * @return    検査番号
    * @exception なし
    */
    public String getknno(){
            return knno;
    }

    /**
    * <p>検査番号設定メソッド</p>
    *
    * @param pknno 検査番号
    * @exception なし
    */
    public void setknno( String pknno ){
            knno = pknno;
    }

    /**
    * <p>オーダー番号取得メソッド</p>
    *
    * @return    オーダー番号
    * @exception なし
    */
    public String getjuno(){
            return juno;
    }

    /**
    * <p>オーダー番号設定メソッド</p>
    *
    * @param pjuno オーダー番号
    * @exception なし
    */
    public void setjuno( String pjuno ){
            juno = pjuno;
    }

    /**
    * <p>需要分野コード取得メソッド</p>
    *
    * @return    需要分野コード
    * @exception なし
    */
    public String getjbcd(){
            return jbcd;
    }

    /**
    * <p>需要分野コード設定メソッド</p>
    *
    * @param pjbcd 需要分野コード
    * @exception なし
    */
    public void setjbcd( String pjbcd ){
            jbcd = pjbcd;
    }

    /**
    * <p>ビジネスユニットコード取得メソッド</p>
    *
    * @return    ビジネスユニットコード
    * @exception なし
    */
    public String getbucode(){
            return bucode;
    }

    /**
    * <p>ビジネスユニットコード設定メソッド</p>
    *
    * @param pbucode ビジネスユニットコード
    * @exception なし
    */
    public void setbucode( String pbucode ){
            bucode = pbucode;
    }

    /**
    * <p>用途コード取得メソッド</p>
    *
    * @return    用途コード
    * @exception なし
    */
    public String getyotoc(){
            return yotoc;
    }

    /**
    * <p>用途コード設定メソッド</p>
    *
    * @param pyotoc 用途コード
    * @exception なし
    */
    public void setyotoc( String pyotoc ){
            yotoc = pyotoc;
    }

    /**
    * <p>用途名取得メソッド</p>
    *
    * @return    用途名
    * @exception なし
    */
    public String getyotoname(){
            return yotoname;
    }

    /**
    * <p>用途名設定メソッド</p>
    *
    * @param pyotoname 用途名
    * @exception なし
    */
    public void setyotoname( String pyotoname ){
            yotoname = pyotoname;
    }

    /**
    * <p>材質取得メソッド</p>
    *
    * @return    材質
    * @exception なし
    */
    public String getjua(){
            return jua;
    }

    /**
    * <p>材質設定メソッド</p>
    *
    * @param pjua 材質
    * @exception なし
    */
    public void setjua( String pjua ){
            jua = pjua;
    }

    /**
    * <p>調質取得メソッド</p>
    *
    * @return    調質
    * @exception なし
    */
    public String getjub(){
            return jub;
    }

    /**
    * <p>調質設定メソッド</p>
    *
    * @param pjub 調質
    * @exception なし
    */
    public void setjub( String pjub ){
            jub = pjub;
    }

    /**
    * <p>板厚取得メソッド</p>
    *
    * @return    板厚
    * @exception なし
    */
    public BigDecimal getjux(){
            return jux;
    }

    /**
    * <p>板厚設定メソッド</p>
    *
    * @param pjux 板厚
    * @exception なし
    */
    public void setjux( BigDecimal pjux ){
            jux = pjux;
    }

    /**
    * <p>板幅取得メソッド</p>
    *
    * @return    板幅
    * @exception なし
    */
    public BigDecimal getjuy(){
            return juy;
    }

    /**
    * <p>板幅設定メソッド</p>
    *
    * @param pjuy 板幅
    * @exception なし
    */
    public void setjuy( BigDecimal pjuy ){
            juy = pjuy;
    }

    /**
    * <p>板丈取得メソッド</p>
    *
    * @return    板丈
    * @exception なし
    */
    public BigDecimal getjuz(){
            return juz;
    }

    /**
    * <p>板丈設定メソッド</p>
    *
    * @param pjuz 板丈
    * @exception なし
    */
    public void setjuz( BigDecimal pjuz ){
            juz = pjuz;
    }

    /**
    * <p>材質取得メソッド</p>
    *
    * @return    材質
    * @exception なし
    */
    public String getlta(){
            return lta;
    }

    /**
    * <p>材質設定メソッド</p>
    *
    * @param plta 材質
    * @exception なし
    */
    public void setlta( String plta ){
            lta = plta;
    }

    /**
    * <p>調質取得メソッド</p>
    *
    * @return    調質
    * @exception なし
    */
    public String getltb(){
            return ltb;
    }

    /**
    * <p>調質設定メソッド</p>
    *
    * @param pltb 調質
    * @exception なし
    */
    public void setltb( String pltb ){
            ltb = pltb;
    }

    /**
    * <p>板厚取得メソッド</p>
    *
    * @return    板厚
    * @exception なし
    */
    public BigDecimal getltx(){
            return ltx;
    }

    /**
    * <p>板厚設定メソッド</p>
    *
    * @param pltx 板厚
    * @exception なし
    */
    public void setltx( BigDecimal pltx ){
            ltx = pltx;
    }

    /**
    * <p>板幅取得メソッド</p>
    *
    * @return    板幅
    * @exception なし
    */
    public BigDecimal getlty(){
            return lty;
    }

    /**
    * <p>板幅設定メソッド</p>
    *
    * @param plty 板幅
    * @exception なし
    */
    public void setlty( BigDecimal plty ){
            lty = plty;
    }

    /**
    * <p>板丈取得メソッド</p>
    *
    * @return    板丈
    * @exception なし
    */
    public BigDecimal getltz(){
            return ltz;
    }

    /**
    * <p>板丈設定メソッド</p>
    *
    * @param pltz 板丈
    * @exception なし
    */
    public void setltz( BigDecimal pltz ){
            ltz = pltz;
    }

    /**
    * <p>スラブ記号取得メソッド</p>
    *
    * @return    スラブ記号
    * @exception なし
    */
    public String getskigo(){
            return skigo;
    }

    /**
    * <p>スラブ記号設定メソッド</p>
    *
    * @param pskigo スラブ記号
    * @exception なし
    */
    public void setskigo( String pskigo ){
            skigo = pskigo;
    }

    /**
    * <p>スラブ本数取得メソッド</p>
    *
    * @return    スラブ本数
    * @exception なし
    */
    public BigDecimal getjup(){
            return jup;
    }

    /**
    * <p>スラブ本数設定メソッド</p>
    *
    * @param pjup スラブ本数
    * @exception なし
    */
    public void setjup( BigDecimal pjup ){
            jup = pjup;
    }

    /**
    * <p>製品単重取得メソッド</p>
    *
    * @return    製品単重
    * @exception なし
    */
    public BigDecimal gettanw(){
            return tanw;
    }

    /**
    * <p>製品単重設定メソッド</p>
    *
    * @param ptanw 製品単重
    * @exception なし
    */
    public void settanw( BigDecimal ptanw ){
            tanw = ptanw;
    }

    /**
    * <p>比重取得メソッド</p>
    *
    * @return    比重
    * @exception なし
    */
    public BigDecimal gethijyu(){
            return hijyu;
    }

    /**
    * <p>比重設定メソッド</p>
    *
    * @param phijyu 比重
    * @exception なし
    */
    public void sethijyu( BigDecimal phijyu ){
            hijyu = phijyu;
    }

    /**
    * <p>投入量取得メソッド</p>
    *
    * @return    投入量
    * @exception なし
    */
    public BigDecimal getsw(){
            return sw;
    }

    /**
    * <p>投入量設定メソッド</p>
    *
    * @param psw 投入量
    * @exception なし
    */
    public void setsw( BigDecimal psw ){
            sw = psw;
    }

    /**
    * <p>納期連番３取得メソッド</p>
    *
    * @return    納期連番３
    * @exception なし
    */
    public String getnoki3_renban(){
            return noki3_renban;
    }

    /**
    * <p>納期連番３設定メソッド</p>
    *
    * @param pnoki3_renban 納期連番３
    * @exception なし
    */
    public void setnoki3_renban( String pnoki3_renban ){
            noki3_renban = pnoki3_renban;
    }

    /**
    * <p>出荷日取得メソッド</p>
    *
    * @return    出荷日
    * @exception なし
    */
    public Date getnokiymd(){
            return nokiymd;
    }

    /**
    * <p>出荷日設定メソッド</p>
    *
    * @param pnokiymd 出荷日
    * @exception なし
    */
    public void setnokiymd( Date pnokiymd ){
            nokiymd = pnokiymd;
    }

    /**
    * <p>取引先名取得メソッド</p>
    *
    * @return    取引先名
    * @exception なし
    */
    public String gettokui(){
            return tokui;
    }

    /**
    * <p>取引先名設定メソッド</p>
    *
    * @param ptokui 取引先名
    * @exception なし
    */
    public void settokui( String ptokui ){
            tokui = ptokui;
    }

    /**
    * <p>送り先名取得メソッド</p>
    *
    * @return    送り先名
    * @exception なし
    */
    public String getoname(){
            return oname;
    }

    /**
    * <p>送り先名設定メソッド</p>
    *
    * @param poname 送り先名
    * @exception なし
    */
    public void setoname( String poname ){
            oname = poname;
    }

    /**
    * <p>納入先名取得メソッド</p>
    *
    * @return    納入先名
    * @exception なし
    */
    public String getnonyu(){
            return nonyu;
    }

    /**
    * <p>納入先名設定メソッド</p>
    *
    * @param pnonyu 納入先名
    * @exception なし
    */
    public void setnonyu( String pnonyu ){
            nonyu = pnonyu;
    }

    /**
    * <p>地区コ－ド取得メソッド</p>
    *
    * @return    地区コ－ド
    * @exception なし
    */
    public BigDecimal getchikucd(){
            return chikucd;
    }

    /**
    * <p>地区コ－ド設定メソッド</p>
    *
    * @param pchikucd 地区コ－ド
    * @exception なし
    */
    public void setchikucd( BigDecimal pchikucd ){
            chikucd = pchikucd;
    }

    /**
    * <p>販売員コ－ド取得メソッド</p>
    *
    * @return    販売員コ－ド
    * @exception なし
    */
    public BigDecimal getjujsno(){
            return jujsno;
    }

    /**
    * <p>販売員コ－ド設定メソッド</p>
    *
    * @param pjujsno 販売員コ－ド
    * @exception なし
    */
    public void setjujsno( BigDecimal pjujsno ){
            jujsno = pjujsno;
    }

    /**
    * <p>営業納期取得メソッド</p>
    *
    * @return    営業納期
    * @exception なし
    */
    public Date getenoki(){
            return enoki;
    }

    /**
    * <p>営業納期設定メソッド</p>
    *
    * @param penoki 営業納期
    * @exception なし
    */
    public void setenoki( Date penoki ){
            enoki = penoki;
    }

    /**
    * <p>工場納期取得メソッド</p>
    *
    * @return    工場納期
    * @exception なし
    */
    public Date getknoki(){
            return knoki;
    }

    /**
    * <p>工場納期設定メソッド</p>
    *
    * @param pknoki 工場納期
    * @exception なし
    */
    public void setknoki( Date pknoki ){
            knoki = pknoki;
    }

    /**
    * <p>購入鋳造年月日時分秒取得メソッド</p>
    *
    * @return    購入鋳造年月日時分秒
    * @exception なし
    */
    public Date getjcdtm(){
            return jcdtm;
    }

    /**
    * <p>購入鋳造年月日時分秒設定メソッド</p>
    *
    * @param pjcdtm 購入鋳造年月日時分秒
    * @exception なし
    */
    public void setjcdtm( Date pjcdtm ){
            jcdtm = pjcdtm;
    }

    /**
    * <p>購入鋳造重量取得メソッド</p>
    *
    * @return    購入鋳造重量
    * @exception なし
    */
    public BigDecimal getjcw(){
            return jcw;
    }

    /**
    * <p>購入鋳造重量設定メソッド</p>
    *
    * @param pjcw 購入鋳造重量
    * @exception なし
    */
    public void setjcw( BigDecimal pjcw ){
            jcw = pjcw;
    }

    /**
    * <p>作業年月日時分秒取得メソッド</p>
    *
    * @return    作業年月日時分秒
    * @exception なし
    */
    public Date getjswdtm(){
            return jswdtm;
    }

    /**
    * <p>作業年月日時分秒設定メソッド</p>
    *
    * @param pjswdtm 作業年月日時分秒
    * @exception なし
    */
    public void setjswdtm( Date pjswdtm ){
            jswdtm = pjswdtm;
    }

    /**
    * <p>重量取得メソッド</p>
    *
    * @return    重量
    * @exception なし
    */
    public BigDecimal getjsww(){
            return jsww;
    }

    /**
    * <p>重量設定メソッド</p>
    *
    * @param pjsww 重量
    * @exception なし
    */
    public void setjsww( BigDecimal pjsww ){
            jsww = pjsww;
    }

    /**
    * <p>作業年月日時分秒取得メソッド</p>
    *
    * @return    作業年月日時分秒
    * @exception なし
    */
    public Date getjmdtm(){
            return jmdtm;
    }

    /**
    * <p>作業年月日時分秒設定メソッド</p>
    *
    * @param pjmdtm 作業年月日時分秒
    * @exception なし
    */
    public void setjmdtm( Date pjmdtm ){
            jmdtm = pjmdtm;
    }

    /**
    * <p>重量取得メソッド</p>
    *
    * @return    重量
    * @exception なし
    */
    public BigDecimal getjmw(){
            return jmw;
    }

    /**
    * <p>重量設定メソッド</p>
    *
    * @param pjmw 重量
    * @exception なし
    */
    public void setjmw( BigDecimal pjmw ){
            jmw = pjmw;
    }

    /**
    * <p>実績_PIT作業_年月日取得メソッド</p>
    *
    * @return    実績_PIT作業_年月日
    * @exception なし
    */
    public Date getjpdt(){
            return jpdt;
    }

    /**
    * <p>実績_PIT作業_年月日設定メソッド</p>
    *
    * @param pjpdt 実績_PIT作業_年月日
    * @exception なし
    */
    public void setjpdt( Date pjpdt ){
            jpdt = pjpdt;
    }

    /**
    * <p>実績_PIT炉番取得メソッド</p>
    *
    * @return    実績_PIT炉番
    * @exception なし
    */
    public BigDecimal getjprb(){
            return jprb;
    }

    /**
    * <p>実績_PIT炉番設定メソッド</p>
    *
    * @param pjprb 実績_PIT炉番
    * @exception なし
    */
    public void setjprb( BigDecimal pjprb ){
            jprb = pjprb;
    }

    /**
    * <p>実績_PIT炉順取得メソッド</p>
    *
    * @return    実績_PIT炉順
    * @exception なし
    */
    public BigDecimal getjprj(){
            return jprj;
    }

    /**
    * <p>実績_PIT炉順設定メソッド</p>
    *
    * @param pjprj 実績_PIT炉順
    * @exception なし
    */
    public void setjprj( BigDecimal pjprj ){
            jprj = pjprj;
    }

    /**
     * プロパティ edtm の取得メソッド。
     * @return プロパティ edtm の値。
     */
    public Date getedtm() {

        return edtm;
    }

    /**
     * プロパティ edtm の設定メソッド。
     * @param edtm プロパティ edtm の新しい値。
     */
    public void setedtm(Date pedtm) {

        edtm = pedtm;
    }



}
