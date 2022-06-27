/*
 * IcasBoxBean.java
 *
 * Created on 2006/11/15, 19:41
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package ngyshanai.domain.nagoya.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Hirohiko-Matsushita
 */
public class IcasBoxBean implements Serializable {

    /** Creates a new instance of IcasBoxBean */
    public IcasBoxBean() {
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
     * プロパティ BOXNO の値を保持。
     */
    private BigDecimal BOXNO;

    /**
     * プロパティ BOXNO の取得メソッド。
     * @return プロパティ BOXNO の値。
     */
    public BigDecimal getBOXNO() {

        return this.BOXNO;
    }

    /**
     * プロパティ BOXNO の設定メソッド。
     * @param BOXNO プロパティ BOXNO の新しい値。
     */
    public void setBOXNO(BigDecimal BOXNO) {

        this.BOXNO = BOXNO;
    }

    /**
     * プロパティ SBYMD の値を保持。
     */
    private Date SBYMD;

    /**
     * プロパティ SBYMD の取得メソッド。
     * @return プロパティ SBYMD の値。
     */
    public Date getSBYMD() {

        return this.SBYMD;
    }

    /**
     * プロパティ SBYMD の設定メソッド。
     * @param SBYMD プロパティ SBYMD の新しい値。
     */
    public void setSBYMD(Date SBYMD) {

        this.SBYMD = SBYMD;
    }

    /**
     * プロパティ SBMMDD の値を保持。
     */
    private Date SBMMDD;

    /**
     * プロパティ SBMMDD の取得メソッド。
     * @return プロパティ SBMMDD の値。
     */
    public Date getSBMMDD() {

        return this.SBMMDD;
    }

    /**
     * プロパティ SBMMDD の設定メソッド。
     * @param SBMMDD プロパティ SBMMDD の新しい値。
     */
    public void setSBMMDD(Date SBMMDD) {

        this.SBMMDD = SBMMDD;
    }

    /**
     * プロパティ SBMMDDC の値を保持。
     */
    private BigDecimal SBMMDDC;

    /**
     * プロパティ SBMMDDC の取得メソッド。
     * @return プロパティ SBMMDDC の値。
     */
    public BigDecimal getSBMMDDC() {

        return this.SBMMDDC;
    }

    /**
     * プロパティ SBMMDDC の設定メソッド。
     * @param SBMMDDC プロパティ SBMMDDC の新しい値。
     */
    public void setSBMMDDC(BigDecimal SBMMDDC) {

        this.SBMMDDC = SBMMDDC;
    }

    /**
     * プロパティ SBSM の値を保持。
     */
    private String SBSM;

    /**
     * プロパティ SBSM の取得メソッド。
     * @return プロパティ SBSM の値。
     */
    public String getSBSM() {

        return this.SBSM;
    }

    /**
     * プロパティ SBSM の設定メソッド。
     * @param SBSM プロパティ SBSM の新しい値。
     */
    public void setSBSM(String SBSM) {

        this.SBSM = SBSM;
    }

    /**
     * プロパティ SBZ の値を保持。
     */
    private BigDecimal SBZ;

    /**
     * プロパティ SBZ の取得メソッド。
     * @return プロパティ SBZ の値。
     */
    public BigDecimal getSBZ() {

        return this.SBZ;
    }

    /**
     * プロパティ SBZ の設定メソッド。
     * @param SBZ プロパティ SBZ の新しい値。
     */
    public void setSBZ(BigDecimal SBZ) {

        this.SBZ = SBZ;
    }

    /**
     * プロパティ SBPAT の値を保持。
     */
    private String SBPAT;

    /**
     * プロパティ SBPAT の取得メソッド。
     * @return プロパティ SBPAT の値。
     */
    public String getSBPAT() {

        return this.SBPAT;
    }

    /**
     * プロパティ SBPAT の設定メソッド。
     * @param SBPAT プロパティ SBPAT の新しい値。
     */
    public void setSBPAT(String SBPAT) {

        this.SBPAT = SBPAT;
    }

    /**
     * プロパティ SBX の値を保持。
     */
    private BigDecimal SBX;

    /**
     * プロパティ SBX の取得メソッド。
     * @return プロパティ SBX の値。
     */
    public BigDecimal getSBX() {

        return this.SBX;
    }

    /**
     * プロパティ SBX の設定メソッド。
     * @param SBX プロパティ SBX の新しい値。
     */
    public void setSBX(BigDecimal SBX) {

        this.SBX = SBX;
    }

    /**
     * プロパティ SH_SBW の値を保持。
     */
    private BigDecimal SH_SBW;

    /**
     * プロパティ SH_SBW の取得メソッド。
     * @return プロパティ SH_SBW の値。
     */
    public BigDecimal getSH_SBW() {

        return this.SH_SBW;
    }

    /**
     * プロパティ SH_SBW の設定メソッド。
     * @param SH_SBW プロパティ SH_SBW の新しい値。
     */
    public void setSH_SBW(BigDecimal SH_SBW) {

        this.SH_SBW = SH_SBW;
    }

    /**
     * プロパティ JBSM の値を保持。
     */
    private String JBSM;

    /**
     * プロパティ JBSM の取得メソッド。
     * @return プロパティ JBSM の値。
     */
    public String getJBSM() {

        return this.JBSM;
    }

    /**
     * プロパティ JBSM の設定メソッド。
     * @param JBSM プロパティ JBSM の新しい値。
     */
    public void setJBSM(String JBSM) {

        this.JBSM = JBSM;
    }

    /**
     * プロパティ JBRW の値を保持。
     */
    private BigDecimal JBRW;

    /**
     * プロパティ JBRW の取得メソッド。
     * @return プロパティ JBRW の値。
     */
    public BigDecimal getJBRW() {

        return this.JBRW;
    }

    /**
     * プロパティ JBRW の設定メソッド。
     * @param JBRW プロパティ JBRW の新しい値。
     */
    public void setJBRW(BigDecimal JBRW) {

        this.JBRW = JBRW;
    }

    /**
     * プロパティ JBRMH の値を保持。
     */
    private BigDecimal JBRMH;

    /**
     * プロパティ JBRMH の取得メソッド。
     * @return プロパティ JBRMH の値。
     */
    public BigDecimal getJBRMH() {

        return this.JBRMH;
    }

    /**
     * プロパティ JBRMH の設定メソッド。
     * @param JBRMH プロパティ JBRMH の新しい値。
     */
    public void setJBRMH(BigDecimal JBRMH) {

        this.JBRMH = JBRMH;
    }

    /**
     * プロパティ JBPAT の値を保持。
     */
    private String JBPAT;

    /**
     * プロパティ JBPAT の取得メソッド。
     * @return プロパティ JBPAT の値。
     */
    public String getJBPAT() {

        return this.JBPAT;
    }

    /**
     * プロパティ JBPAT の設定メソッド。
     * @param JBPAT プロパティ JBPAT の新しい値。
     */
    public void setJBPAT(String JBPAT) {

        this.JBPAT = JBPAT;
    }

    /**
     * プロパティ JBEDTM の値を保持。
     */
    private Date JBEDTM;

    /**
     * プロパティ JBEDTM の取得メソッド。
     * @return プロパティ JBEDTM の値。
     */
    public Date getJBEDTM() {

        return this.JBEDTM;
    }

    /**
     * プロパティ JBEDTM の設定メソッド。
     * @param JBEDTM プロパティ JBEDTM の新しい値。
     */
    public void setJBEDTM(Date JBEDTM) {

        this.JBEDTM = JBEDTM;
    }

    /**
     * プロパティ JBX の値を保持。
     */
    private BigDecimal JBX;

    /**
     * プロパティ JBX の取得メソッド。
     * @return プロパティ JBX の値。
     */
    public BigDecimal getJBX() {

        return this.JBX;
    }

    /**
     * プロパティ JBX の設定メソッド。
     * @param JBX プロパティ JBX の新しい値。
     */
    public void setJBX(BigDecimal JBX) {

        this.JBX = JBX;
    }

    /**
     * プロパティ JBY の値を保持。
     */
    private BigDecimal JBY;

    /**
     * プロパティ JBY の取得メソッド。
     * @return プロパティ JBY の値。
     */
    public BigDecimal getJBY() {

        return this.JBY;
    }

    /**
     * プロパティ JBY の設定メソッド。
     * @param JBY プロパティ JBY の新しい値。
     */
    public void setJBY(BigDecimal JBY) {

        this.JBY = JBY;
    }

    /**
     * プロパティ JBZ の値を保持。
     */
    private BigDecimal JBZ;

    /**
     * プロパティ JBZ の取得メソッド。
     * @return プロパティ JBZ の値。
     */
    public BigDecimal getJBZ() {

        return this.JBZ;
    }

    /**
     * プロパティ JBZ の設定メソッド。
     * @param JBZ プロパティ JBZ の新しい値。
     */
    public void setJBZ(BigDecimal JBZ) {

        this.JBZ = JBZ;
    }

    /**
     * プロパティ JBBN の値を保持。
     */
    private BigDecimal JBBN;

    /**
     * プロパティ JBBN の取得メソッド。
     * @return プロパティ JBBN の値。
     */
    public BigDecimal getJBBN() {

        return this.JBBN;
    }

    /**
     * プロパティ JBBN の設定メソッド。
     * @param JBBN プロパティ JBBN の新しい値。
     */
    public void setJBBN(BigDecimal JBBN) {

        this.JBBN = JBBN;
    }

    /**
     * プロパティ STARTDT の値を保持。
     */
    private String STARTDT;

    /**
     * プロパティ STARTDD の取得メソッド。
     * @return プロパティ STARTDD の値。
     */
    public String getSTARTDT()  {

        return this.STARTDT;
    }

    /**
     * プロパティ STARTDD の設定メソッド。
     * @param STARTDD プロパティ STARTDD の新しい値。
     */
    public void setSTARTDT(String STARTDT)  {

        this.STARTDT = STARTDT;
    }

    /**
     * プロパティ ENDDT の値を保持。
     */
    private String ENDDT;

    /**
     * プロパティ ENDDD の取得メソッド。
     * @return プロパティ ENDDD の値。
     */
    public String getENDDT()  {

        return this.ENDDT;
    }

    /**
     * プロパティ ENDDD の設定メソッド。
     * @param ENDDD プロパティ ENDDD の新しい値。
     */
    public void setENDDT(String ENDDT)  {

        this.ENDDT = ENDDT;
    }

    /**
     * プロパティ PROC_KBN の値を保持。
     */
    private String PROC_KBN;

    /**
     * プロパティ PROC_KBN の取得メソッド。
     * @return プロパティ PROC_KBN の値。
     */
    public String getPROC_KBN() {

        return this.PROC_KBN;
    }

    /**
     * プロパティ PROC_KBN の設定メソッド。
     * @param PROC_KBN プロパティ PROC_KBN の新しい値。
     */
    public void setPROC_KBN(String PROC_KBN) {

        this.PROC_KBN = PROC_KBN;
    }

    /**
     * プロパティ a11 の値を保持。
     */
    private String a11;

    /**
     * プロパティ a11 の取得メソッド。
     * @return プロパティ a11 の値。
     */
    public String getA11() {

        return this.a11;
    }

    /**
     * プロパティ a11 の設定メソッド。
     * @param a11 プロパティ a11 の新しい値。
     */
    public void setA11(String a11) {

        this.a11 = a11;
    }

    /**
     * プロパティ SBK の値を保持。
     */
    private BigDecimal SBK;

    /**
     * プロパティ SBK の取得メソッド。
     * @return プロパティ SBK の値。
     */
    public BigDecimal getSBK() {

        return this.SBK;
    }

    /**
     * プロパティ SBK の設定メソッド。
     * @param SBK プロパティ SBK の新しい値。
     */
    public void setSBK(BigDecimal SBK) {

        this.SBK = SBK;
    }

    /**
     * プロパティ SBMH の値を保持。
     */
    private BigDecimal SBMH;

    /**
     * プロパティ SBMH の取得メソッド。
     * @return プロパティ SBMH の値。
     */
    public BigDecimal getSBMH() {

        return this.SBMH;
    }

    /**
     * プロパティ SBMH の設定メソッド。
     * @param SBMH プロパティ SBMH の新しい値。
     */
    public void setSBMH(BigDecimal SBMH) {

        this.SBMH = SBMH;
    }

    /**
     * プロパティ SBY の値を保持。
     */
    private BigDecimal SBY;

    /**
     * プロパティ SBY の取得メソッド。
     * @return プロパティ SBY の値。
     */
    public BigDecimal getSBY() {

        return this.SBY;
    }

    /**
     * プロパティ SBY の設定メソッド。
     * @param SBY プロパティ SBY の新しい値。
     */
    public void setSBY(BigDecimal SBY) {

        this.SBY = SBY;
    }

    /**
     * プロパティ SBW の値を保持。
     */
    private BigDecimal SBW;

    /**
     * プロパティ SBW の取得メソッド。
     * @return プロパティ SBW の値。
     */
    public BigDecimal getSBW() {

        return this.SBW;
    }

    /**
     * プロパティ SBW の設定メソッド。
     * @param SBW プロパティ SBW の新しい値。
     */
    public void setSBW(BigDecimal SBW) {

        this.SBW = SBW;
    }
    // 2012/03/20 sasano add end

}
