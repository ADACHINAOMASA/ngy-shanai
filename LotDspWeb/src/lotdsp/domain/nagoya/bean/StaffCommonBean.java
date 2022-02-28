/*
 * StaffCommonBean.java
 *
 * Created on 2006/11/15, 16:27
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package lotdsp.domain.nagoya.bean;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lotdsp.domain.nagoya.util.NumberUtil;
import lotdsp.domain.nagoya.util.StringUtil;

/**
 *
 * @author Hirohiko-Matsushita
 */
public class StaffCommonBean implements Serializable {

    /** Creates a new instance of StaffCommonBean */
    public StaffCommonBean() {
    }

    /**
     * プロパティ LTNO の値を保持。
     */
    private String LTNO;

    /**
     * プロパティ LTNO の取得メソッド。
     * @return プロパティ LTNO の値。
     */
    public String getLTNO() {

        return this.LTNO;
    }

    /**
     * プロパティ LTNO の設定メソッド。
     * @param LTNO プロパティ LTNO の新しい値。
     */
    public void setLTNO(String LTNO) {

        this.LTNO = LTNO;
    }

    /**
     * プロパティ CYNO の値を保持。
     */
    private String CYNO;

    /**
     * プロパティ CYNO の取得メソッド。
     * @return プロパティ CYNO の値。
     */
    public String getCYNO() {

        return this.CYNO;
    }

    /**
     * プロパティ CYNO の設定メソッド。
     * @param CYNO プロパティ CYNO の新しい値。
     */
    public void setCYNO(String CYNO) {

        this.CYNO = CYNO;
    }

    /**
     * プロパティ KNNO の値を保持。
     */
    private String KNNO;

    /**
     * プロパティ KNNO の取得メソッド。
     * @return プロパティ KNNO の値。
     */
    public String getKNNO() {

        return this.KNNO;
    }

    /**
     * プロパティ KNNO の設定メソッド。
     * @param KNNO プロパティ KNNO の新しい値。
     */
    public void setKNNO(String KNNO) {

        this.KNNO = KNNO;
    }

    /**
     * プロパティ JUNO の値を保持。
     */
    private String JUNO;

    /**
     * プロパティ JUNO の取得メソッド。
     * @return プロパティ JUNO の値。
     */
    public String getJUNO() {

        return this.JUNO;
    }

    /**
     * プロパティ JUNO の設定メソッド。
     * @param JUNO プロパティ JUNO の新しい値。
     */
    public void setJUNO(String JUNO) {

        this.JUNO = JUNO;
    }

    /**
     * プロパティ JBCD の値を保持。
     */
    private String JBCD;

    /**
     * プロパティ JBCD の取得メソッド。
     * @return プロパティ JBCD の値。
     */
    public String getJBCD() {

        return this.JBCD;
    }

    /**
     * プロパティ JBCD の設定メソッド。
     * @param JBCD プロパティ JBCD の新しい値。
     */
    public void setJBCD(String JBCD) {

        this.JBCD = JBCD;
    }

    /**
     * プロパティ BUCODE の値を保持。
     */
    private String BUCODE;

    /**
     * プロパティ BUCODE の取得メソッド。
     * @return プロパティ BUCODE の値。
     */
    public String getBUCODE() {

        return this.BUCODE;
    }

    /**
     * プロパティ BUCODE の設定メソッド。
     * @param BUCODE プロパティ BUCODE の新しい値。
     */
    public void setBUCODE(String BUCODE) {

        this.BUCODE = BUCODE;
    }

    /**
     * プロパティ YOTOC の値を保持。
     */
    private String YOTOC;

    /**
     * プロパティ YOTOC の取得メソッド。
     * @return プロパティ YOTOC の値。
     */
    public String getYOTOC() {

        return this.YOTOC;
    }

    /**
     * プロパティ YOTOC の設定メソッド。
     * @param YOTOC プロパティ YOTOC の新しい値。
     */
    public void setYOTOC(String YOTOC) {

        this.YOTOC = YOTOC;
    }

    /**
     * プロパティ YOTONAME の値を保持。
     */
    private String YOTONAME;

    /**
     * プロパティ YOTONAME の取得メソッド。
     * @return プロパティ YOTONAME の値。
     */
    public String getYOTONAME() {

        return this.YOTONAME;
    }

    /**
     * プロパティ YOTONAME の設定メソッド。
     * @param YOTONAME プロパティ YOTONAME の新しい値。
     */
    public void setYOTONAME(String YOTONAME) {

        this.YOTONAME = YOTONAME;
    }

    /**
     * プロパティ CHIKUCD の値を保持。
     */
    private BigDecimal CHIKUCD;

    /**
     * プロパティ CHIKUCD の取得メソッド。
     * @return プロパティ CHIKUCD の値。
     */
    public BigDecimal getCHIKUCD() {

        return this.CHIKUCD;
    }

    /**
     * プロパティ CHIKUCD の設定メソッド。
     * @param CHIKUCD プロパティ CHIKUCD の新しい値。
     */
    public void setCHIKUCD(BigDecimal CHIKUCD) {

        this.CHIKUCD = CHIKUCD;
    }

    /**
     * プロパティ JUJSNO の値を保持。
     */
    private BigDecimal JUJSNO;

    /**
     * プロパティ JUJSNO の取得メソッド。
     * @return プロパティ JUJSNO の値。
     */
    public BigDecimal getJUJSNO() {

        return this.JUJSNO;
    }

    /**
     * プロパティ JUJSNO の設定メソッド。
     * @param JUJSNO プロパティ JUJSNO の新しい値。
     */
    public void setJUJSNO(BigDecimal JUJSNO) {

        this.JUJSNO = JUJSNO;
    }

    /**
     * プロパティ TANTOSHA_MEI の値を保持。
     */
    private String TANTOSHA_MEI;

    /**
     * プロパティ TANTOSHA_MEI の取得メソッド。
     * @return プロパティ TANTOSHA_MEI の値。
     */
    public String getTANTOSHA_MEI() {

        return this.TANTOSHA_MEI;
    }

    /**
     * プロパティ TANTOSHA_MEI の設定メソッド。
     * @param TANTOSHA_MEI プロパティ TANTOSHA_MEI の新しい値。
     */
    public void setTANTOSHA_MEI(String TANTOSHA_MEI) {

        this.TANTOSHA_MEI = TANTOSHA_MEI;
    }

    /**
     * プロパティ ENOKI の値を保持。
     */
    private Date ENOKI;

    /**
     * プロパティ ENOKI の取得メソッド。
     * @return プロパティ ENOKI の値。
     */
    public Date getENOKI() {

        return this.ENOKI;
    }

    /**
     * プロパティ ENOKI の設定メソッド。
     * @param ENOKI プロパティ ENOKI の新しい値。
     */
    public void setENOKI(Date ENOKI) {

        this.ENOKI = ENOKI;
    }

    /**
     * プロパティ JUA の値を保持。
     */
    private String JUA;

    /**
     * プロパティ JUA の取得メソッド。
     * @return プロパティ JUA の値。
     */
    public String getJUA() {

        return this.JUA;
    }

    /**
     * プロパティ JUA の設定メソッド。
     * @param JUA プロパティ JUA の新しい値。
     */
    public void setJUA(String JUA) {

        this.JUA = JUA;
    }

    /**
     * プロパティ JUB の値を保持。
     */
    private String JUB;

    /**
     * プロパティ JUB の取得メソッド。
     * @return プロパティ JUB の値。
     */
    public String getJUB() {

        return this.JUB;
    }

    /**
     * プロパティ JUB の設定メソッド。
     * @param JUB プロパティ JUB の新しい値。
     */
    public void setJUB(String JUB) {

        this.JUB = JUB;
    }

    /**
     * プロパティ JUX の値を保持。
     */
    private BigDecimal JUX;

    /**
     * プロパティ JUX の取得メソッド。
     * @return プロパティ JUX の値。
     */
    public BigDecimal getJUX() {

        return this.JUX;
    }

    /**
     * プロパティ JUX の設定メソッド。
     * @param JUX プロパティ JUX の新しい値。
     */
    public void setJUX(BigDecimal JUX) {

        this.JUX = JUX;
    }

    /**
     * プロパティ JUY の値を保持。
     */
    private BigDecimal JUY;

    /**
     * プロパティ JUY の取得メソッド。
     * @return プロパティ JUY の値。
     */
    public BigDecimal getJUY() {

        return this.JUY;
    }

    /**
     * プロパティ JUY の設定メソッド。
     * @param JUY プロパティ JUY の新しい値。
     */
    public void setJUY(BigDecimal JUY) {

        this.JUY = JUY;
    }

    /**
     * プロパティ JUZ の値を保持。
     */
    private BigDecimal JUZ;

    /**
     * プロパティ JUZ の取得メソッド。
     * @return プロパティ JUZ の値。
     */
    public BigDecimal getJUZ() {

        return this.JUZ;
    }

    /**
     * プロパティ JUZ の設定メソッド。
     * @param JUZ プロパティ JUZ の新しい値。
     */
    public void setJUZ(BigDecimal JUZ) {

        this.JUZ = JUZ;
    }

    /**
     * プロパティ JUW の値を保持。
     */
    private BigDecimal JUW;

    /**
     * プロパティ JUW の取得メソッド。
     * @return プロパティ JUW の値。
     */
    public BigDecimal getJUW() {

        return this.JUW;
    }

    /**
     * プロパティ JUW の設定メソッド。
     * @param JUW プロパティ JUW の新しい値。
     */
    public void setJUW(BigDecimal JUW) {

        this.JUW = JUW;
    }

    /**
     * プロパティ LTA の値を保持。
     */
    private String LTA;

    /**
     * プロパティ LTA の取得メソッド。
     * @return プロパティ LTA の値。
     */
    public String getLTA() {

        return this.LTA;
    }

    /**
     * プロパティ LTA の設定メソッド。
     * @param LTA プロパティ LTA の新しい値。
     */
    public void setLTA(String LTA) {

        this.LTA = LTA;
    }

    /**
     * プロパティ LTB の値を保持。
     */
    private String LTB;

    /**
     * プロパティ LTB の取得メソッド。
     * @return プロパティ LTB の値。
     */
    public String getLTB() {

        return this.LTB;
    }

    /**
     * プロパティ LTB の設定メソッド。
     * @param LTB プロパティ LTB の新しい値。
     */
    public void setLTB(String LTB) {

        this.LTB = LTB;
    }

    /**
     * プロパティ LTX の値を保持。
     */
    private BigDecimal LTX;

    /**
     * プロパティ LTX の取得メソッド。
     * @return プロパティ LTX の値。
     */
    public BigDecimal getLTX() {

        return this.LTX;
    }

    /**
     * プロパティ LTX の設定メソッド。
     * @param LTX プロパティ LTX の新しい値。
     */
    public void setLTX(BigDecimal LTX) {

        this.LTX = LTX;
    }

    /**
     * プロパティ LTY の値を保持。
     */
    private BigDecimal LTY;

    /**
     * プロパティ LTY の取得メソッド。
     * @return プロパティ LTY の値。
     */
    public BigDecimal getLTY() {

        return this.LTY;
    }

    /**
     * プロパティ LTY の設定メソッド。
     * @param LTY プロパティ LTY の新しい値。
     */
    public void setLTY(BigDecimal LTY) {

        this.LTY = LTY;
    }

    /**
     * プロパティ LTZ の値を保持。
     */
    private BigDecimal LTZ;

    /**
     * プロパティ LTZ の取得メソッド。
     * @return プロパティ LTZ の値。
     */
    public BigDecimal getLTZ() {

        return this.LTZ;
    }

    /**
     * プロパティ LTZ の設定メソッド。
     * @param LTZ プロパティ LTZ の新しい値。
     */
    public void setLTZ(BigDecimal LTZ) {

        this.LTZ = LTZ;
    }

    /**
     * プロパティ RYOW の値を保持。
     */
    private BigDecimal RYOW;

    /**
     * プロパティ RYOW の取得メソッド。
     * @return プロパティ RYOW の値。
     */
    public BigDecimal getRYOW() {

        return this.RYOW;
    }

    /**
     * プロパティ RYOW の設定メソッド。
     * @param RYOW プロパティ RYOW の新しい値。
     */
    public void setRYOW(BigDecimal RYOW) {

        this.RYOW = RYOW;
    }

    /**
     * プロパティ TOKUI の値を保持。
     */
    private String TOKUI;

    /**
     * プロパティ TOKUI の取得メソッド。
     * @return プロパティ TOKUI の値。
     */
    public String getTOKUI() {

        return this.TOKUI;
    }

    /**
     * プロパティ TOKUI の設定メソッド。
     * @param TOKUI プロパティ TOKUI の新しい値。
     */
    public void setTOKUI(String TOKUI) {

        this.TOKUI = TOKUI;
    }

    /**
     * プロパティ ONAME の値を保持。
     */
    private String ONAME;

    /**
     * プロパティ ONAME の取得メソッド。
     * @return プロパティ ONAME の値。
     */
    public String getONAME() {

        return this.ONAME;
    }

    /**
     * プロパティ ONAME の設定メソッド。
     * @param ONAME プロパティ ONAME の新しい値。
     */
    public void setONAME(String ONAME) {

        this.ONAME = ONAME;
    }

    /**
     * プロパティ KSD の値を保持。
     */
    private Date KSD;

    /**
     * プロパティ KSD の取得メソッド。
     * @return プロパティ KSD の値。
     */
    public Date getKSD() {

        return this.KSD;
    }

    /**
     * プロパティ KSD の設定メソッド。
     * @param KSD プロパティ KSD の新しい値。
     */
    public void setKSD(Date KSD) {

        this.KSD = KSD;
    }

    /**
     * プロパティ NONYU の値を保持。
     */
    private String NONYU;

    /**
     * プロパティ NONYU の取得メソッド。
     * @return プロパティ NONYU の値。
     */
    public String getNONYU() {

        return this.NONYU;
    }

    /**
     * プロパティ NONYU の設定メソッド。
     * @param NONYU プロパティ NONYU の新しい値。
     */
    public void setNONYU(String NONYU) {

        this.NONYU = NONYU;
    }

    /**
     * プロパティ LINKKEY の値を保持。
     */
    private String LINKKEY;

    /**
     * プロパティ LINKKEY の取得メソッド。
     * @return プロパティ LINKKEY の値。
     */
    public String getLINKKEY() {

        return this.LINKKEY;
    }

    /**
     * プロパティ LINKKEY の設定メソッド。
     * @param LINKKEY プロパティ LINKKEY の新しい値。
     */
    public void setLINKKEY(String LINKKEY) {

        this.LINKKEY = LINKKEY;
    }

    /**
     * プロパティ SYSDATE の値を保持。
     */
    private Date SYSDATE;

    /**
     * プロパティ SYSDATE の取得メソッド。
     * @return プロパティ SYSDATE の値。
     */
    public Date getSYSDATE() {

        return this.SYSDATE;
    }

    /**
     * プロパティ SYSDATE の設定メソッド。
     * @param SYSDATE プロパティ SYSDATE の新しい値。
     */
    public void setSYSDATE(Date SYSDATE) {

        this.SYSDATE = SYSDATE;
    }

    /**
     * プロパティ EDTM の値を保持。
     */
    private Date EDTM;

    /**
     * プロパティ EDTM の取得メソッド。
     * @return プロパティ EDTM の値。
     */
    public Date getEDTM() {

        return this.EDTM;
    }

    /**
     * プロパティ EDTM の設定メソッド。
     * @param EDTM プロパティ EDTM の新しい値。
     */
    public void setEDTM(Date EDTM) {

        this.EDTM = EDTM;
    }

    /**
     * プロパティ DATAKBN の値を保持。
     */
    private String DATAKBN;

    /**
     * プロパティ DATAKBN の取得メソッド。
     * @return プロパティ DATAKBN の値。
     */
    public String getDATAKBN() {

        return this.DATAKBN;
    }

    /**
     * プロパティ DATAKBN の設定メソッド。
     * @param DATAKBN プロパティ DATAKBN の新しい値。
     */
    public void setDATAKBN(String DATAKBN) {

        this.DATAKBN = DATAKBN;
    }
    /**
     * プロパティ JCD105 の値を保持。
     */
    private String JCD105;

    /**
     * プロパティ JCD105 の取得メソッド。
     * @return プロパティ JCD105 の値。
     */
    public String getJCD105() {

        return this.JCD105;
    }

    /**
     * プロパティ JCD105 の設定メソッド。
     * @param JCD105 プロパティ JCD105 の新しい値。
     */
    public void setJCD105(String JCD105) {

        this.JCD105 = JCD105;
    }

    /**
     * プロパティ JUP の値を保持。
     */
    private BigDecimal JUP;

    /**
     * プロパティ JUP の取得メソッド。
     * @return プロパティ JUP の値。
     */
    public BigDecimal getJUP() {

        return this.JUP;
    }

    /**
     * プロパティ JUP の設定メソッド。
     * @param JUP プロパティ JUP の新しい値。
     */
    public void setJUP(BigDecimal JUP) {

        this.JUP = JUP;
    }

   /**
    *  試験者名
    */
   private String JUNO202;

   public String getJUNO202() {
        return this.JUNO202;
   }

   public void setJUNO202(String JUNO202) {
       if(NumberUtil.isNum(JUNO202)) {
	   this.JUNO202 = "";
       }
       else {
           this.JUNO202 = JUNO202;
       }
   }

   /**
    * Yマーク
    */
   private Date YMARK;

   /**
    * 試験No
    */
   private String SHIKENNO;

   /**
    * 仮梱
    */
   private String KARIKON;

   /**
    * 納期符号
    */
   private String NOKIFUGO;

   /**
    *  鋳番年
    */
   private BigDecimal CYUZO_YY;

   /**
    *  納入仕様書No
    */
   private String NONYUNO;
   
   public String getNONYUNO() {
	   return NONYUNO;
   }
   
   public void setNONYUNO(String NONYUNO) {
	   this.NONYUNO = NONYUNO;
   }

   public Date getYMARK() {
	return YMARK;
    }

    public void setYMARK(Date YMARK) {
	this.YMARK = YMARK;
    }

    public String getSHIKENNO() {
	return SHIKENNO;
    }

    public void setSHIKENNO(String SHIKENNO) {
	this.SHIKENNO = SHIKENNO;
    }

    public String getKARIKON() {
	return KARIKON;
    }

    public void setKARIKON(String KARIKON) {
	this.KARIKON = KARIKON;
    }

    public String getNOKIFUGO() {
	return NOKIFUGO;
    }

    public void setNOKIFUGO(String NOKIFUGO) {
	this.NOKIFUGO = NOKIFUGO;
    }

    public BigDecimal getCYUZO_YY() {
        return this.CYUZO_YY;
    }

    public void setCYUZO_YY(BigDecimal CYUZO_YY) {
        this.CYUZO_YY = CYUZO_YY;
    }

    /**
     *  データ区分CSS判定
     */
    public String getCSS(){
	if(StringUtil.isEqual(getDATAKBN(), "PIT前")){
	    return "DataKbnBlack";
	}
	else if(StringUtil.isEqual(getDATAKBN(), "仮PIT中")){
	    return "DataKbnBlue";
	}
	else if(StringUtil.isEqual(getDATAKBN(), "本PIT中")){
	    return "DataKbnRed";
	}
	else if(StringUtil.isEqual(getDATAKBN(), "HOT後")){
	    return "DataKbnViolet";
	}
	else if(StringUtil.isEqual(getDATAKBN(), "倉入")){
	    return "DataKbnGreen";
	}
	else if(StringUtil.isEqual(getDATAKBN(), "仕損")){
	    return "DataKbnRed";
	}
	return "DataKbnBlack";
    }

}
