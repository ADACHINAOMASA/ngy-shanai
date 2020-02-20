/*
 * IcasBean.java
 *
 * Created on 2006/11/15, 19:09
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package lotdsp.domain.nagoya.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Hirohiko-Matsushita
 */
public class IcasBean implements Serializable {

    /** Creates a new instance of IcasBean */
    public IcasBean() {
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
     * プロパティ JCD201 の値を保持。
     */
    private String JCD201;

    /**
     * プロパティ JCD201 の取得メソッド。
     * @return プロパティ JCD201 の値。
     */
    public String getJCD201() {

        return this.JCD201;
    }

    /**
     * プロパティ JCD201 の設定メソッド。
     * @param JCD201 プロパティ JCD201 の新しい値。
     */
    public void setJCD201(String JCD201) {

        this.JCD201 = JCD201;
    }

    /**
     * プロパティ PITBI の値を保持。
     */
    private Date PITBI;

    /**
     * プロパティ PITBI の取得メソッド。
     * @return プロパティ PITBI の値。
     */
    public Date getPITBI() {

        return this.PITBI;
    }

    /**
     * プロパティ PITBI の設定メソッド。
     * @param PITBI プロパティ PITBI の新しい値。
     */
    public void setPITBI(Date PITBI) {

        this.PITBI = PITBI;
    }

    /**
     * プロパティ m_MARK の値を保持。
     */
    private String m_MARK;

    /**
     * プロパティ m_MARK の取得メソッド。
     * @return プロパティ m_MARK の値。
     */
    public String getM_MARK() {

        return this.m_MARK;
    }

    /**
     * プロパティ m_MARK の設定メソッド。
     * @param m_MARK プロパティ m_MARK の新しい値。
     */
    public void setM_MARK(String m_MARK) {

        this.m_MARK = m_MARK;
    }

    /**
     * プロパティ GPKBN の値を保持。
     */
    private String GPKBN;

    /**
     * プロパティ GPKBN の取得メソッド。
     * @return プロパティ GPKBN の値。
     */
    public String getGPKBN() {

        return this.GPKBN;
    }

    /**
     * プロパティ GPKBN の設定メソッド。
     * @param GPKBN プロパティ GPKBN の新しい値。
     */
    public void setGPKBN(String GPKBN) {

        this.GPKBN = GPKBN;
    }

    /**
     * プロパティ e_MARK の値を保持。
     */
    private String e_MARK;

    /**
     * プロパティ e_MARK の取得メソッド。
     * @return プロパティ e_MARK の値。
     */
    public String getE_MARK() {

        return this.e_MARK;
    }

    /**
     * プロパティ e_MARK の設定メソッド。
     * @param e_MARK プロパティ e_MARK の新しい値。
     */
    public void setE_MARK(String e_MARK) {

        this.e_MARK = e_MARK;
    }

    /**
     * プロパティ KNOKI の値を保持。
     */
    private Date KNOKI;

    /**
     * プロパティ KNOKI の取得メソッド。
     * @return プロパティ KNOKI の値。
     */
    public Date getKNOKI() {

        return this.KNOKI;
    }

    /**
     * プロパティ KNOKI の設定メソッド。
     * @param KNOKI プロパティ KNOKI の新しい値。
     */
    public void setKNOKI(Date KNOKI) {

        this.KNOKI = KNOKI;
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
     * プロパティ JUN の値を保持。
     */
    private BigDecimal JUN;

    /**
     * プロパティ JUN の取得メソッド。
     * @return プロパティ JUN の値。
     */
    public BigDecimal getJUN() {

        return this.JUN;
    }

    /**
     * プロパティ JUN の設定メソッド。
     * @param JUN プロパティ JUN の新しい値。
     */
    public void setJUN(BigDecimal JUN) {

        this.JUN = JUN;
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
     * プロパティ TANW の値を保持。
     */
    private BigDecimal TANW;

    /**
     * プロパティ TANW の取得メソッド。
     * @return プロパティ TANW の値。
     */
    public BigDecimal getTANW() {

        return this.TANW;
    }

    /**
     * プロパティ TANW の設定メソッド。
     * @param TANW プロパティ TANW の新しい値。
     */
    public void setTANW(BigDecimal TANW) {

        this.TANW = TANW;
    }

    /**
     * プロパティ HIJYU の値を保持。
     */
    private BigDecimal HIJYU;

    /**
     * プロパティ HIJYU の取得メソッド。
     * @return プロパティ HIJYU の値。
     */
    public BigDecimal getHIJYU() {

        return this.HIJYU;
    }

    /**
     * プロパティ HIJYU の設定メソッド。
     * @param HIJYU プロパティ HIJYU の新しい値。
     */
    public void setHIJYU(BigDecimal HIJYU) {

        this.HIJYU = HIJYU;
    }

    /**
     * プロパティ SKIGO の値を保持。
     */
    private String SKIGO;

    /**
     * プロパティ SKIGO の取得メソッド。
     * @return プロパティ SKIGO の値。
     */
    public String getSKIGO() {

        return this.SKIGO;
    }

    /**
     * プロパティ SKIGO の設定メソッド。
     * @param SKIGO プロパティ SKIGO の新しい値。
     */
    public void setSKIGO(String SKIGO) {

        this.SKIGO = SKIGO;
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
     * プロパティ PITJ の値を保持。
     */
    private String PITJ;

    /**
     * プロパティ PITJ の取得メソッド。
     * @return プロパティ PITJ の値。
     */
    public String getPITJ() {

        return this.PITJ;
    }

    /**
     * プロパティ PITJ の設定メソッド。
     * @param PITJ プロパティ PITJ の新しい値。
     */
    public void setPITJ(String PITJ) {

        this.PITJ = PITJ;
    }

    /**
     * プロパティ HOTJ の値を保持。
     */
    private String HOTJ;

    /**
     * プロパティ HOTJ の取得メソッド。
     * @return プロパティ HOTJ の値。
     */
    public String getHOTJ() {

        return this.HOTJ;
    }

    /**
     * プロパティ HOTJ の設定メソッド。
     * @param HOTJ プロパティ HOTJ の新しい値。
     */
    public void setHOTJ(String HOTJ) {

        this.HOTJ = HOTJ;
    }

    /**
     * プロパティ SSKW の値を保持。
     */
    private BigDecimal SSKW;

    /**
     * プロパティ SSKW の取得メソッド。
     * @return プロパティ SSKW の値。
     */
    public BigDecimal getSSKW() {

        return this.SSKW;
    }

    /**
     * プロパティ SSKW の設定メソッド。
     * @param SSKW プロパティ SSKW の新しい値。
     */
    public void setSSKW(BigDecimal SSKW) {

        this.SSKW = SSKW;
    }

    /**
     * プロパティ SH_RYOW の値を保持。
     */
    private BigDecimal SH_RYOW;

    /**
     * プロパティ SH_RYOW の取得メソッド。
     * @return プロパティ SH_RYOW の値。
     */
    public BigDecimal getSH_RYOW() {

        return this.SH_RYOW;
    }

    /**
     * プロパティ SH_RYOW の設定メソッド。
     * @param SH_RYOW プロパティ SH_RYOW の新しい値。
     */
    public void setSH_RYOW(BigDecimal SH_RYOW) {

        this.SH_RYOW = SH_RYOW;
    }

    /**
     * プロパティ SW の値を保持。
     */
    private BigDecimal SW;

    /**
     * プロパティ SW の取得メソッド。
     * @return プロパティ SW の値。
     */
    public BigDecimal getSW() {

        return this.SW;
    }

    /**
     * プロパティ SW の設定メソッド。
     * @param SW プロパティ SW の新しい値。
     */
    public void setSW(BigDecimal SW) {

        this.SW = SW;
    }

    /**
     * プロパティ MENW の値を保持。
     */
    private BigDecimal MENW;

    /**
     * プロパティ MENW の取得メソッド。
     * @return プロパティ MENW の値。
     */
    public BigDecimal getMENW() {

        return this.MENW;
    }

    /**
     * プロパティ MENW の設定メソッド。
     * @param MENW プロパティ MENW の新しい値。
     */
    public void setMENW(BigDecimal MENW) {

        this.MENW = MENW;
    }

    /**
     * プロパティ SLB_KBN の値を保持。
     */
    private String SLB_KBN;

    /**
     * プロパティ SLB_KBN の取得メソッド。
     * @return プロパティ SLB_KBN の値。
     */
    public String getSLB_KBN() {

        return this.SLB_KBN;
    }

    /**
     * プロパティ SLB_KBN の設定メソッド。
     * @param SLB_KBN プロパティ SLB_KBN の新しい値。
     */
    public void setSLB_KBN(String SLB_KBN) {

        this.SLB_KBN = SLB_KBN;
    }

    /**
     * プロパティ SLB_YUKOY の値を保持。
     */
    private String SLB_YUKOY;

    /**
     * プロパティ SLB_YUKOY の取得メソッド。
     * @return プロパティ SLB_YUKOY の値。
     */
    public String getSLB_YUKOY() {

        return this.SLB_YUKOY;
    }

    /**
     * プロパティ SLB_YUKOY の設定メソッド。
     * @param SLB_YUKOY プロパティ SLB_YUKOY の新しい値。
     */
    public void setSLB_YUKOY(String SLB_YUKOY) {

        this.SLB_YUKOY = SLB_YUKOY;
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
     * プロパティ SGNO の値を保持。
     */
    private String SGNO;

    /**
     * プロパティ SGNO の取得メソッド。
     * @return プロパティ SGNO の値。
     */
    public String getSGNO() {

        return this.SGNO;
    }

    /**
     * プロパティ SGNO の設定メソッド。
     * @param SGNO プロパティ SGNO の新しい値。
     */
    public void setSGNO(String SGNO) {

        this.SGNO = SGNO;
    }

    /**
     * プロパティ BENO の値を保持。
     */
    private BigDecimal BENO;

    /**
     * プロパティ BENO の取得メソッド。
     * @return プロパティ BENO の値。
     */
    public BigDecimal getBENO() {

        return this.BENO;
    }

    /**
     * プロパティ BENO の設定メソッド。
     * @param BENO プロパティ BENO の新しい値。
     */
    public void setBENO(BigDecimal BENO) {

        this.BENO = BENO;
    }

    /**
     * プロパティ PRJ の値を保持。
     */
    private BigDecimal PRJ;

    /**
     * プロパティ PRJ の取得メソッド。
     * @return プロパティ PRJ の値。
     */
    public BigDecimal getPRJ() {

        return this.PRJ;
    }

    /**
     * プロパティ PRJ の設定メソッド。
     * @param PRJ プロパティ PRJ の新しい値。
     */
    public void setPRJ(BigDecimal PRJ) {

        this.PRJ = PRJ;
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
     * プロパティ ON3N の値を保持。
     */
    private String ON3N;

    /**
     * プロパティ ON3N の取得メソッド。
     * @return プロパティ ON3N の値。
     */
    public String getON3N() {

        return this.ON3N;
    }

    /**
     * プロパティ ON3N の設定メソッド。
     * @param ON3N プロパティ ON3N の新しい値。
     */
    public void setON3N(String ON3N) {

        this.ON3N = ON3N;
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
     * プロパティ MASYOTOC の値を保持。
     */
    private String MASYOTOC;

    /**
     * プロパティ MASYOTOC の取得メソッド。
     * @return プロパティ MASYOTOC の値。
     */
    public String getMASYOTOC() {

        return this.MASYOTOC;
    }

    /**
     * プロパティ MASYOTOC の設定メソッド。
     * @param MASYOTOC プロパティ MASYOTOC の新しい値。
     */
    public void setMASYOTOC(String MASYOTOC) {

        this.MASYOTOC = MASYOTOC;
    }

    /**
     * プロパティ JCD202 の値を保持。
     */
    private String JCD202;

    /**
     * プロパティ JCD202 の取得メソッド。
     * @return プロパティ JCD202 の値。
     */
    public String getJCD202() {

        return this.JCD202;
    }

    /**
     * プロパティ JCD202 の設定メソッド。
     * @param JCD202 プロパティ JCD202 の新しい値。
     */
    public void setJCD202(String JCD202) {

        this.JCD202 = JCD202;
    }

    /**
     * プロパティ g2 の値を保持。
     */
    private String g2;

    /**
     * プロパティ g2 の取得メソッド。
     * @return プロパティ g2 の値。
     */
    public String getG2() {

        return this.g2;
    }

    /**
     * プロパティ g2 の設定メソッド。
     * @param g2 プロパティ g2 の新しい値。
     */
    public void setG2(String g2) {

        this.g2 = g2;
    }

    /**
     * プロパティ JCD112 の値を保持。
     */
    private String JCD112;

    /**
     * プロパティ JCD112 の取得メソッド。
     * @return プロパティ JCD112 の値。
     */
    public String getJCD112() {

        return this.JCD112;
    }

    /**
     * プロパティ JCD112 の設定メソッド。
     * @param JCD112 プロパティ JCD112 の新しい値。
     */
    public void setJCD112(String JCD112) {

        this.JCD112 = JCD112;
    }

    /**
     * プロパティ JCD114 の値を保持。
     */
    private String JCD114;

    /**
     * プロパティ JCD114 の取得メソッド。
     * @return プロパティ JCD114 の値。
     */
    public String getJCD114() {

        return this.JCD114;
    }

    /**
     * プロパティ JCD114 の設定メソッド。
     * @param JCD114 プロパティ JCD114 の新しい値。
     */
    public void setJCD114(String JCD114) {

        this.JCD114 = JCD114;
    }

    /**
     * プロパティ JCD118 の値を保持。
     */
    private String JCD118;

    /**
     * プロパティ JCD118 の取得メソッド。
     * @return プロパティ JCD118 の値。
     */
    public String getJCD118() {

        return this.JCD118;
    }

    /**
     * プロパティ JCD118 の設定メソッド。
     * @param JCD118 プロパティ JCD118 の新しい値。
     */
    public void setJCD118(String JCD118) {

        this.JCD118 = JCD118;
    }

    /**
     * プロパティ NOKIYMD の値を保持。
     */
    private Date NOKIYMD;

    /**
     * プロパティ NOKIYMD の取得メソッド。
     * @return プロパティ NOKIYMD の値。
     */
    public Date getNOKIYMD() {

        return this.NOKIYMD;
    }

    /**
     * プロパティ NOKIYMD の設定メソッド。
     * @param NOKIYMD プロパティ NOKIYMD の新しい値。
     */
    public void setNOKIYMD(Date NOKIYMD) {

        this.NOKIYMD = NOKIYMD;
    }

    /**
     * プロパティ PITMAG_FLG の値を保持。
     */
    private BigDecimal PITMAG_FLG;

    /**
     * プロパティ PITMAG_FLG の取得メソッド。
     * @return プロパティ PITMAG_FLG の値。
     */
    public BigDecimal getPITMAG_FLG() {

        return this.PITMAG_FLG;
    }

    /**
     * プロパティ PITMAG_FLG の設定メソッド。
     * @param PITMAG_FLG プロパティ PITMAG_FLG の新しい値。
     */
    public void setPITMAG_FLG(BigDecimal PITMAG_FLG) {

        this.PITMAG_FLG = PITMAG_FLG;
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
     * プロパティ JCD106 の値を保持。
     */
    private String JCD106;

    /**
     * プロパティ JCD106 の取得メソッド。
     * @return プロパティ JCD106 の値。
     */
    public String getJCD106() {

        return this.JCD106;
    }

    /**
     * プロパティ JCD106 の設定メソッド。
     * @param JCD106 プロパティ JCD106 の新しい値。
     */
    public void setJCD106(String JCD106) {

        this.JCD106 = JCD106;
    }

    /**
     * プロパティ SIJI の値を保持。
     */
    private String SIJI;

    /**
     * プロパティ SIJI の取得メソッド。
     * @return プロパティ SIJI の値。
     */
    public String getSIJI() {

        return this.SIJI;
    }

    /**
     * プロパティ SIJI の設定メソッド。
     * @param SIJI プロパティ SIJI の新しい値。
     */
    public void setSIJI(String SIJI) {

        this.SIJI = SIJI;
    }

    /**
     * プロパティ JCD101 の値を保持。
     */
    private String JCD101;

    /**
     * プロパティ JCD101 の取得メソッド。
     * @return プロパティ JCD101 の値。
     */
    public String getJCD101() {

        return this.JCD101;
    }

    /**
     * プロパティ JCD101 の設定メソッド。
     * @param JCD101 プロパティ JCD101 の新しい値。
     */
    public void setJCD101(String JCD101) {

        this.JCD101 = JCD101;
    }

    /**
     * プロパティ NOKI3_RENBAN の値を保持。
     */
    private String NOKI3_RENBAN;

    /**
     * プロパティ NOKI3_RENBAN の取得メソッド。
     * @return プロパティ NOKI3_RENBAN の値。
     */
    public String getNOKI3_RENBAN() {

        return this.NOKI3_RENBAN;
    }

    /**
     * プロパティ NOKI3_RENBAN の設定メソッド。
     * @param NOKI3_RENBAN プロパティ NOKI3_RENBAN の新しい値。
     */
    public void setNOKI3_RENBAN(String NOKI3_RENBAN) {

        this.NOKI3_RENBAN = NOKI3_RENBAN;
    }

    /**
     * プロパティ HEN_LN の値を保持。
     */
    private String HEN_LN;

    /**
     * プロパティ HEN_LN の取得メソッド。
     * @return プロパティ HEN_LN の値。
     */
    public String getHEN_LN() {

        return this.HEN_LN;
    }

    /**
     * プロパティ HEN_LN の設定メソッド。
     * @param HEN_LN プロパティ HEN_LN の新しい値。
     */
    public void setHEN_LN(String HEN_LN) {

        this.HEN_LN = HEN_LN;
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
     * プロパティ SCHEDSM の値を保持。
     */
    private String SCHEDSM;

    /**
     * プロパティ SCHEDSM の取得メソッド。
     * @return プロパティ SCHEDSM の値。
     */
    public String getSCHEDSM() {

        return this.SCHEDSM;
    }

    /**
     * プロパティ SCHEDSM の設定メソッド。
     * @param SCHEDSM プロパティ SCHEDSM の新しい値。
     */
    public void setSCHEDSM(String SCHEDSM) {

        this.SCHEDSM = SCHEDSM;
    }

    /**
     * プロパティ CHOSEI の値を保持。
     */
    private BigDecimal CHOSEI;

    /**
     * プロパティ CHOSEI の取得メソッド。
     * @return プロパティ CHOSEI の値。
     */
    public BigDecimal getCHOSEI() {

        return this.CHOSEI;
    }

    /**
     * プロパティ CHOSEI の設定メソッド。
     * @param CHOSEI プロパティ CHOSEI の新しい値。
     */
    public void setCHOSEI(BigDecimal CHOSEI) {

        this.CHOSEI = CHOSEI;
    }

    /**
     * プロパティ JHF1X の値を保持。
     */
    private BigDecimal JHF1X;

    /**
     * プロパティ JHF1X の取得メソッド。
     * @return プロパティ JHF1X の値。
     */
    public BigDecimal getJHF1X() {

        return this.JHF1X;
    }

    /**
     * プロパティ JHF1X の設定メソッド。
     * @param JHF1X プロパティ JHF1X の新しい値。
     */
    public void setJHF1X(BigDecimal JHF1X) {

        this.JHF1X = JHF1X;
    }

    /**
     * プロパティ JHFTMP の値を保持。
     */
    private BigDecimal JHFTMP;

    /**
     * プロパティ JHFTMP の取得メソッド。
     * @return プロパティ JHFTMP の値。
     */
    public BigDecimal getJHFTMP() {

        return this.JHFTMP;
    }

    /**
     * プロパティ JHFTMP の設定メソッド。
     * @param JHFTMP プロパティ JHFTMP の新しい値。
     */
    public void setJHFTMP(BigDecimal JHFTMP) {

        this.JHFTMP = JHFTMP;
    }

    /**
     * プロパティ JHF1TMP の値を保持。
     */
    private BigDecimal JHF1TMP;

    /**
     * プロパティ JHF1TMP の取得メソッド。
     * @return プロパティ JHF1TMP の値。
     */
    public BigDecimal getJHF1TMP() {

        return this.JHF1TMP;
    }

    /**
     * プロパティ JHF1TMP の設定メソッド。
     * @param JHF1TMP プロパティ JHF1TMP の新しい値。
     */
    public void setJHF1TMP(BigDecimal JHF1TMP) {

        this.JHF1TMP = JHF1TMP;
    }

    /**
     * プロパティ HOTPC の値を保持。
     */
    private String HOTPC;

    /**
     * プロパティ HOTPC の取得メソッド。
     * @return プロパティ HOTPC の値。
     */
    public String getHOTPC() {

        return this.HOTPC;
    }

    /**
     * プロパティ HOTPC の設定メソッド。
     * @param HOTPC プロパティ HOTPC の新しい値。
     */
    public void setHOTPC(String HOTPC) {

        this.HOTPC = HOTPC;
    }

    /**
     * プロパティ HIKICODE の値を保持。
     */
    private BigDecimal HIKICODE;

    /**
     * プロパティ HIKICODE の取得メソッド。
     * @return プロパティ HIKICODE の値。
     */
    public BigDecimal getHIKICODE() {

        return this.HIKICODE;
    }

    /**
     * プロパティ HIKICODE の設定メソッド。
     * @param HIKICODE プロパティ HIKICODE の新しい値。
     */
    public void setHIKICODE(BigDecimal HIKICODE) {

        this.HIKICODE = HIKICODE;
    }

    /**
     * プロパティ KSTR の値を保持。
     */
    private String KSTR;

    /**
     * プロパティ KSTR の取得メソッド。
     * @return プロパティ KSTR の値。
     */
    public String getKSTR() {

        return this.KSTR;
    }

    /**
     * プロパティ KSTR の設定メソッド。
     * @param KSTR プロパティ KSTR の新しい値。
     */
    public void setKSTR(String KSTR) {

        this.KSTR = KSTR;
    }

    /**
     * プロパティ KSHC の値を保持。
     */
    private String KSHC;

    /**
     * プロパティ KSHC の取得メソッド。
     * @return プロパティ KSHC の値。
     */
    public String getKSHC() {

        return this.KSHC;
    }

    /**
     * プロパティ KSHC の設定メソッド。
     * @param KSHC プロパティ KSHC の新しい値。
     */
    public void setKSHC(String KSHC) {

        this.KSHC = KSHC;
    }

    /**
     * プロパティ MODW の値を保持。
     */
    private BigDecimal MODW;

    /**
     * プロパティ MODW の取得メソッド。
     * @return プロパティ MODW の値。
     */
    public BigDecimal getMODW() {

        return this.MODW;
    }

    /**
     * プロパティ MODW の設定メソッド。
     * @param MODW プロパティ MODW の新しい値。
     */
    public void setMODW(BigDecimal MODW) {

        this.MODW = MODW;
    }

    /**
     * プロパティ MODN の値を保持。
     */
    private BigDecimal MODN;

    /**
     * プロパティ MODN の取得メソッド。
     * @return プロパティ MODN の値。
     */
    public BigDecimal getMODN() {

        return this.MODN;
    }

    /**
     * プロパティ MODN の設定メソッド。
     * @param MODN プロパティ MODN の新しい値。
     */
    public void setMODN(BigDecimal MODN) {

        this.MODN = MODN;
    }

    /**
     * プロパティ JPDT の値を保持。
     */
    private Date JPDT;

    /**
     * プロパティ JPDT の取得メソッド。
     * @return プロパティ JPDT の値。
     */
    public Date getJPDT() {

        return this.JPDT;
    }

    /**
     * プロパティ JPDT の設定メソッド。
     * @param JPDT プロパティ JPDT の新しい値。
     */
    public void setJPDT(Date JPDT) {

        this.JPDT = JPDT;
    }

    /**
     * プロパティ JPRB の値を保持。
     */
    private BigDecimal JPRB;

    /**
     * プロパティ JPRB の取得メソッド。
     * @return プロパティ JPRB の値。
     */
    public BigDecimal getJPRB() {

        return this.JPRB;
    }

    /**
     * プロパティ JPRB の設定メソッド。
     * @param JPRB プロパティ JPRB の新しい値。
     */
    public void setJPRB(BigDecimal JPRB) {

        this.JPRB = JPRB;
    }

    /**
     * プロパティ JPRJ の値を保持。
     */
    private BigDecimal JPRJ;

    /**
     * プロパティ JPRJ の取得メソッド。
     * @return プロパティ JPRJ の値。
     */
    public BigDecimal getJPRJ() {

        return this.JPRJ;
    }

    /**
     * プロパティ JPRJ の設定メソッド。
     * @param JPRJ プロパティ JPRJ の新しい値。
     */
    public void setJPRJ(BigDecimal JPRJ) {

        this.JPRJ = JPRJ;
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
     * プロパティ SAI1_FRMDTM の値を保持。
     */
    private Date SAI1_FRMDTM;

    /**
     * プロパティ SAI1_FRMDTM の取得メソッド。
     * @return プロパティ SAI1_FRMDTM の値。
     */
    public Date getSAI1_FRMDTM() {

        return this.SAI1_FRMDTM;
    }

    /**
     * プロパティ SAI1_FRMDTM の設定メソッド。
     * @param SAI1_FRMDTM プロパティ SAI1_FRMDTM の新しい値。
     */
    public void setSAI1_FRMDTM(Date SAI1_FRMDTM) {

        this.SAI1_FRMDTM = SAI1_FRMDTM;
    }

    /**
     * プロパティ FRTDTM の値を保持。
     */
    private Date FRTDTM;

    /**
     * プロパティ FRTDTM の取得メソッド。
     * @return プロパティ FRTDTM の値。
     */
    public Date getFRTDTM() {

        return this.FRTDTM;
    }

    /**
     * プロパティ FRTDTM の設定メソッド。
     * @param FRTDTM プロパティ FRTDTM の新しい値。
     */
    public void setFRTDTM(Date FRTDTM) {

        this.FRTDTM = FRTDTM;
    }

    /**
     * プロパティ TNKBN の値を保持。
     */
    private String TNKBN;

    /**
     * プロパティ TNKBN の取得メソッド。
     * @return プロパティ TNKBN の値。
     */
    public String getTNKBN() {

        return this.TNKBN;
    }

    /**
     * プロパティ TNKBN の設定メソッド。
     * @param TNKBN プロパティ TNKBN の新しい値。
     */
    public void setTNKBN(String TNKBN) {

        this.TNKBN = TNKBN;
    }

    /**
     * プロパティ TNSEQ の値を保持。
     */
    private BigDecimal TNSEQ;

    /**
     * プロパティ TNSEQ の取得メソッド。
     * @return プロパティ TNSEQ の値。
     */
    public BigDecimal getTNSEQ() {

        return this.TNSEQ;
    }

    /**
     * プロパティ TNSEQ の設定メソッド。
     * @param TNSEQ プロパティ TNSEQ の新しい値。
     */
    public void setTNSEQ(BigDecimal TNSEQ) {

        this.TNSEQ = TNSEQ;
    }

    /**
     * プロパティ TNWGT の値を保持。
     */
    private BigDecimal TNWGT;

    /**
     * プロパティ TNWGT の取得メソッド。
     * @return プロパティ TNWGT の値。
     */
    public BigDecimal getTNWGT() {

        return this.TNWGT;
    }

    /**
     * プロパティ TNWGT の設定メソッド。
     * @param TNWGT プロパティ TNWGT の新しい値。
     */
    public void setTNWGT(BigDecimal TNWGT) {

        this.TNWGT = TNWGT;
    }

    /**
     * プロパティ AKNO の値を保持。
     */
    private BigDecimal AKNO;

    /**
     * プロパティ AKNO の取得メソッド。
     * @return プロパティ AKNO の値。
     */
    public BigDecimal getAKNO() {

        return this.AKNO;
    }

    /**
     * プロパティ AKNO の設定メソッド。
     * @param AKNO プロパティ AKNO の新しい値。
     */
    public void setAKNO(BigDecimal AKNO) {

        this.AKNO = AKNO;
    }

    /**
     * プロパティ LO の値を保持。
     */
    private String LO;

    /**
     * プロパティ LO の取得メソッド。
     * @return プロパティ LO の値。
     */
    public String getLO() {

        return this.LO;
    }

    /**
     * プロパティ LO の設定メソッド。
     * @param LO プロパティ LO の新しい値。
     */
    public void setLO(String LO) {

        this.LO = LO;
    }

    /**
     * プロパティ HOKBN の値を保持。
     */
    private String HOKBN;

    /**
     * プロパティ HOKBN の取得メソッド。
     * @return プロパティ HOKBN の値。
     */
    public String getHOKBN() {

        return this.HOKBN;
    }

    /**
     * プロパティ HOKBN の設定メソッド。
     * @param HOKBN プロパティ HOKBN の新しい値。
     */
    public void setHOKBN(String HOKBN) {

        this.HOKBN = HOKBN;
    }

    /**
     * プロパティ SOKOIKIKBN の値を保持。
     */
    private String SOKOIKIKBN;

    /**
     * プロパティ SOKOIKIKBN の取得メソッド。
     * @return プロパティ SOKOIKIKBN の値。
     */
    public String getSOKOIKIKBN() {

        return this.SOKOIKIKBN;
    }

    /**
     * プロパティ SOKOIKIKBN の設定メソッド。
     * @param SOKOIKIKBN プロパティ SOKOIKIKBN の新しい値。
     */
    public void setSOKOIKIKBN(String SOKOIKIKBN) {

        this.SOKOIKIKBN = SOKOIKIKBN;
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
     * プロパティ ONAMEJ の値を保持。
     */
    private String ONAMEJ;

    /**
     * プロパティ ONAMEJ の取得メソッド。
     * @return プロパティ ONAMEJ の値。
     */
    public String getONAMEJ() {

        return this.ONAMEJ;
    }

    /**
     * プロパティ ONAMEJ の設定メソッド。
     * @param ONAMEJ プロパティ ONAMEJ の新しい値。
     */
    public void setONAMEJ(String ONAMEJ) {

        this.ONAMEJ = ONAMEJ;
    }

    /**
     * プロパティ TOKUI_TNNAME の値を保持。
     */
    private String TOKUI_TNNAME;

    /**
     * プロパティ TOKUI_TNNAME の取得メソッド。
     * @return プロパティ TOKUI_TNNAME の値。
     */
    public String getTOKUI_TNNAME() {

        return this.TOKUI_TNNAME;
    }

    /**
     * プロパティ TOKUI_TNNAME の設定メソッド。
     * @param TOKUI_TNNAME プロパティ TOKUI_TNNAME の新しい値。
     */
    public void setTOKUI_TNNAME(String TOKUI_TNNAME) {

        this.TOKUI_TNNAME = TOKUI_TNNAME;
    }

    /**
     * プロパティ NONYU_TNNAME の値を保持。
     */
    private String NONYU_TNNAME;

    /**
     * プロパティ NONYU_TNNAME の取得メソッド。
     * @return プロパティ NONYU_TNNAME の値。
     */
    public String getNONYU_TNNAME() {

        return this.NONYU_TNNAME;
    }

    /**
     * プロパティ NONYU_TNNAME の設定メソッド。
     * @param NONYU_TNNAME プロパティ NONYU_TNNAME の新しい値。
     */
    public void setNONYU_TNNAME(String NONYU_TNNAME) {

        this.NONYU_TNNAME = NONYU_TNNAME;
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
     * プロパティ staffProgressBoxInfos の値を保持。
     */
    private List icasBoxInfos;

    /**
     * プロパティ staffProgressBoxInfos の取得メソッド。
     * @return プロパティ staffProgressBoxInfos の値。
     */
    public List getIcasBoxInfos() {

        return this.icasBoxInfos;
    }

    /**
     * プロパティ staffProgressBoxInfos の設定メソッド。
     * @param staffProgressBoxInfos プロパティ staffProgressBoxInfos の新しい値。
     */
    public void setIcasBoxInfos(List icasBoxInfos) {

        this.icasBoxInfos = icasBoxInfos;
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
     * プロパティ KJYO の値を保持。
     */
    private String KJYO;

    /**
     * プロパティ KJYO の取得メソッド。
     * @return プロパティ KJYO の値。
     */
    public String getKJYO() {

        return this.KJYO;
    }

    /**
     * プロパティ KJYO の設定メソッド。
     * @param KJYO プロパティ KJYO の新しい値。
     */
    public void setKJYO(String KJYO) {

        this.KJYO = KJYO;
    }

    /**
     * プロパティ PITY の値を保持。
     */
    private String PITY;

    /**
     * プロパティ PITY の取得メソッド。
     * @return プロパティ PITY の値。
     */
    public String getPITY() {

        return this.PITY;
    }

    /**
     * プロパティ PITY の設定メソッド。
     * @param PITY プロパティ PITY の新しい値。
     */
    public void setPITY(String PITY) {

        this.PITY = PITY;
    }

    /**
     * プロパティ NOKI の値を保持。
     */
    private String NOKI;

    /**
     * プロパティ NOKI の取得メソッド。
     * @return プロパティ NOKI の値。
     */
    public String getNOKI() {

        return this.NOKI;
    }

    /**
     * プロパティ NOKI の設定メソッド。
     * @param NOKI プロパティ NOKI の新しい値。
     */
    public void setNOKI(String NOKI) {

        this.NOKI = NOKI;
    }

    /**
     * プロパティ JUMH の値を保持。
     */
    private BigDecimal JUMH;

    /**
     * プロパティ JUMH の取得メソッド。
     * @return プロパティ JUMH の値。
     */
    public BigDecimal getJUMH() {

        return this.JUMH;
    }

    /**
     * プロパティ JUMH の設定メソッド。
     * @param JUMH プロパティ JUMH の新しい値。
     */
    public void setJUMH(BigDecimal JUMH) {

        this.JUMH = JUMH;
    }

    /**
     * プロパティ JU の値を保持。
     */
    private BigDecimal JU;

    /**
     * プロパティ JU の取得メソッド。
     * @return プロパティ JU の値。
     */
    public BigDecimal getJU() {

        return this.JU;
    }

    /**
     * プロパティ JU の設定メソッド。
     * @param JU プロパティ JU の新しい値。
     */
    public void setJU(BigDecimal JU) {

        this.JU = JU;
    }

    /**
     * プロパティ SEIZO の値を保持。
     */
    private BigDecimal SEIZO;

    /**
     * プロパティ SEIZO の取得メソッド。
     * @return プロパティ SEIZO の値。
     */
    public BigDecimal getSEIZO() {

        return this.SEIZO;
    }

    /**
     * プロパティ SEIZO の設定メソッド。
     * @param SEIZO プロパティ SEIZO の新しい値。
     */
    public void setSEIZO(BigDecimal SEIZO) {

        this.SEIZO = SEIZO;
    }

    /**
     * プロパティ YUKOY の値を保持。
     */
    private String YUKOY;

    /**
     * プロパティ YUKOY の取得メソッド。
     * @return プロパティ YUKOY の値。
     */
    public String getYUKOY() {

        return this.YUKOY;
    }

    /**
     * プロパティ YUKOY の設定メソッド。
     * @param YUKOY プロパティ YUKOY の新しい値。
     */
    public void setYUKOY(String YUKOY) {

        this.YUKOY = YUKOY;
    }

    /**
     * プロパティ CASDT の値を保持。
     */
    private String CASDT;

    /**
     * プロパティ CASDT の取得メソッド。
     * @return プロパティ CASDT の値。
     */
    public String getCASDT() {

        return this.CASDT;
    }

    /**
     * プロパティ CASDT の設定メソッド。
     * @param CASDT プロパティ CASDT の新しい値。
     */
    public void setCASDT(String CASDT) {

        this.CASDT = CASDT;
    }

    /**
     * プロパティ CASW の値を保持。
     */
    private BigDecimal CASW;

    /**
     * プロパティ CASW の取得メソッド。
     * @return プロパティ CASW の値。
     */
    public BigDecimal getCASW() {

        return this.CASW;
    }

    /**
     * プロパティ CASW の設定メソッド。
     * @param CASW プロパティ CASW の新しい値。
     */
    public void setCASW(BigDecimal CASW) {

        this.CASW = CASW;
    }

    /**
     * プロパティ SSWDT の値を保持。
     */
    private String SSWDT;

    /**
     * プロパティ SSWDT の取得メソッド。
     * @return プロパティ SSWDT の値。
     */
    public String getSSWDT() {

        return this.SSWDT;
    }

    /**
     * プロパティ SSWDT の設定メソッド。
     * @param SSWDT プロパティ SSWDT の新しい値。
     */
    public void setSSWDT(String SSWDT) {

        this.SSWDT = SSWDT;
    }

    /**
     * プロパティ SSWW の値を保持。
     */
    private BigDecimal SSWW;

    /**
     * プロパティ SSWW の取得メソッド。
     * @return プロパティ SSWW の値。
     */
    public BigDecimal getSSWW() {

        return this.SSWW;
    }

    /**
     * プロパティ SSWW の設定メソッド。
     * @param SSWW プロパティ SSWW の新しい値。
     */
    public void setSSWW(BigDecimal SSWW) {

        this.SSWW = SSWW;
    }

    /**
     * プロパティ SCADT の値を保持。
     */
    private String SCADT;

    /**
     * プロパティ SCADT の取得メソッド。
     * @return プロパティ SCADT の値。
     */
    public String getSCADT() {

        return this.SCADT;
    }

    /**
     * プロパティ SCADT の設定メソッド。
     * @param SCADT プロパティ SCADT の新しい値。
     */
    public void setSCADT(String SCADT) {

        this.SCADT = SCADT;
    }

    /**
     * プロパティ SCAW の値を保持。
     */
    private BigDecimal SCAW;

    /**
     * プロパティ SCAW の取得メソッド。
     * @return プロパティ SCAW の値。
     */
    public BigDecimal getSCAW() {

        return this.SCAW;
    }

    /**
     * プロパティ SCAW の設定メソッド。
     * @param SCAW プロパティ SCAW の新しい値。
     */
    public void setSCAW(BigDecimal SCAW) {

        this.SCAW = SCAW;
    }

    /**
     * プロパティ ROLW の値を保持。
     */
    private String ROLW;

    /**
     * プロパティ ROLW の取得メソッド。
     * @return プロパティ ROLW の値。
     */
    public String getROLW() {

        return this.ROLW;
    }

    /**
     * プロパティ ROLW の設定メソッド。
     * @param ROLW プロパティ ROLW の新しい値。
     */
    public void setROLW(String ROLW) {

        this.ROLW = ROLW;
    }

    /**
     * プロパティ PITDT の値を保持。
     */
    private String PITDT;

    /**
     * プロパティ PITDT の取得メソッド。
     * @return プロパティ PITDT の値。
     */
    public String getPITDT() {

        return this.PITDT;
    }

    /**
     * プロパティ PITDT の設定メソッド。
     * @param PITDT プロパティ PITDT の新しい値。
     */
    public void setPITDT(String PITDT) {

        this.PITDT = PITDT;
    }

    /**
     * プロパティ PTR の値を保持。
     */
    private BigDecimal PTR;

    /**
     * プロパティ PTR の取得メソッド。
     * @return プロパティ PTR の値。
     */
    public BigDecimal getPTR() {

        return this.PTR;
    }

    /**
     * プロパティ PTR の設定メソッド。
     * @param PTR プロパティ PTR の新しい値。
     */
    public void setPTR(BigDecimal PTR) {

        this.PTR = PTR;
    }

    /**
     * プロパティ PTJ の値を保持。
     */
    private String PTJ;

    /**
     * プロパティ PTJ の取得メソッド。
     * @return プロパティ PTJ の値。
     */
    public String getPTJ() {

        return this.PTJ;
    }

    /**
     * プロパティ PTJ の設定メソッド。
     * @param PTJ プロパティ PTJ の新しい値。
     */
    public void setPTJ(String PTJ) {

        this.PTJ = PTJ;
    }

    /**
     * プロパティ HOTZ の値を保持。
     */
    private BigDecimal HOTZ;

    /**
     * プロパティ HOTZ の取得メソッド。
     * @return プロパティ HOTZ の値。
     */
    public BigDecimal getHOTZ() {

        return this.HOTZ;
    }

    /**
     * プロパティ HOTZ の設定メソッド。
     * @param HOTZ プロパティ HOTZ の新しい値。
     */
    public void setHOTZ(BigDecimal HOTZ) {

        this.HOTZ = HOTZ;
    }

    /**
     * プロパティ HT1 の値を保持。
     */
    private BigDecimal HT1;

    /**
     * プロパティ HT1 の取得メソッド。
     * @return プロパティ HT1 の値。
     */
    public BigDecimal getHT1() {

        return this.HT1;
    }

    /**
     * プロパティ HT1 の設定メソッド。
     * @param HT1 プロパティ HT1 の新しい値。
     */
    public void setHT1(BigDecimal HT1) {

        this.HT1 = HT1;
    }

    /**
     * プロパティ HT2 の値を保持。
     */
    private BigDecimal HT2;

    /**
     * プロパティ HT2 の取得メソッド。
     * @return プロパティ HT2 の値。
     */
    public BigDecimal getHT2() {

        return this.HT2;
    }

    /**
     * プロパティ HT2 の設定メソッド。
     * @param HT2 プロパティ HT2 の新しい値。
     */
    public void setHT2(BigDecimal HT2) {

        this.HT2 = HT2;
    }

    /**
     * プロパティ KSW の値を保持。
     */
    private String KSW;

    /**
     * プロパティ KSW の取得メソッド。
     * @return プロパティ KSW の値。
     */
    public String getKSW() {

        return this.KSW;
    }

    /**
     * プロパティ KSW の設定メソッド。
     * @param KSW プロパティ KSW の新しい値。
     */
    public void setKSW(String KSW) {

        this.KSW = KSW;
    }

    /**
     * プロパティ OKURI の値を保持。
     */
    private String OKURI;

    /**
     * プロパティ OKURI の取得メソッド。
     * @return プロパティ OKURI の値。
     */
    public String getOKURI() {

        return this.OKURI;
    }

    /**
     * プロパティ OKURI の設定メソッド。
     * @param OKURI プロパティ OKURI の新しい値。
     */
    public void setOKURI(String OKURI) {

        this.OKURI = OKURI;
    }

    /**
     * プロパティ NONNA の値を保持。
     */
    private String NONNA;

    /**
     * プロパティ NONNA の取得メソッド。
     * @return プロパティ NONNA の値。
     */
    public String getNONNA() {

        return this.NONNA;
    }

    /**
     * プロパティ NONNA の設定メソッド。
     * @param NONNA プロパティ NONNA の新しい値。
     */
    public void setNONNA(String NONNA) {

        this.NONNA = NONNA;
    }

    /**
     * プロパティ YOTON の値を保持。
     */
    private String YOTON;

    /**
     * プロパティ YOTON の取得メソッド。
     * @return プロパティ YOTON の値。
     */
    public String getYOTON() {

        return this.YOTON;
    }

    /**
     * プロパティ YOTON の設定メソッド。
     * @param YOTON プロパティ YOTON の新しい値。
     */
    public void setYOTON(String YOTON) {

        this.YOTON = YOTON;
    }

    /**
     * プロパティ SKSBSM の値を保持。
     */
    private String SKSBSM;

    /**
     * プロパティ SKSBSM の取得メソッド。
     * @return プロパティ SKSBSM の値。
     */
    public String getSKSBSM() {

        return this.SKSBSM;
    }

    /**
     * プロパティ SKSBSM の設定メソッド。
     * @param SKSBSM プロパティ SKSBSM の新しい値。
     */
    public void setSKSBSM(String SKSBSM) {

        this.SKSBSM = SKSBSM;
    }

    /**
     * プロパティ SYUDTM の値を保持。
     */
    private String SYUDTM;

    /**
     * プロパティ SYUDT の取得メソッド。
     * @return プロパティ SYUDT の値。
     */
    public String getSYUDTM()  {

        return this.SYUDTM;
    }

    /**
     * プロパティ SYUDT の設定メソッド。
     * @param SYUDT プロパティ SYUDT の新しい値。
     */
    public void setSYUDTM(String SYUDTM)  {

        this.SYUDTM = SYUDTM;
    }

    /**
     * プロパティ ADDDT の値を保持。
     */
    private String ADDDT;

    /**
     * プロパティ ADDDT の取得メソッド。
     * @return プロパティ ADDDT の値。
     */
    public String getADDDT() {

        return this.ADDDT;
    }

    /**
     * プロパティ ADDDT の設定メソッド。
     * @param ADDDT プロパティ ADDDT の新しい値。
     */
    public void setADDDT(String ADDDT) {

        this.ADDDT = ADDDT;
    }

    /**
     * プロパティ MNTDTM の値を保持。
     */
    private String MNTDTM;

    /**
     * プロパティ SYUTM の取得メソッド。
     * @return プロパティ SYUTM の値。
     */
    public String getMNTDTM()  {

        return this.MNTDTM;
    }

    /**
     * プロパティ SYUTM の設定メソッド。
     * @param SYUTM プロパティ SYUTM の新しい値。
     */
    public void setMNTDTM(String MNTDTM)  {

        this.MNTDTM = MNTDTM;
    }

    /**
     * プロパティ KURA の値を保持。
     */
    private BigDecimal KURA;

    /**
     * プロパティ KURA の取得メソッド。
     * @return プロパティ KURA の値。
     */
    public BigDecimal getKURA() {

        return this.KURA;
    }

    /**
     * プロパティ KURA の設定メソッド。
     * @param KURA プロパティ KURA の新しい値。
     */
    public void setKURA(BigDecimal KURA) {

        this.KURA = KURA;
    }

    /**
     * プロパティ SLAB の値を保持。
     */
    private BigDecimal SLAB;

    /**
     * プロパティ SLAB の取得メソッド。
     * @return プロパティ SLAB の値。
     */
    public BigDecimal getSLAB() {

        return this.SLAB;
    }

    /**
     * プロパティ SLAB の設定メソッド。
     * @param SLAB プロパティ SLAB の新しい値。
     */
    public void setSLAB(BigDecimal SLAB) {

        this.SLAB = SLAB;
    }

    /**
     * プロパティ SCA の値を保持。
     */
    private BigDecimal SCA;

    /**
     * プロパティ SCA の取得メソッド。
     * @return プロパティ SCA の値。
     */
    public BigDecimal getSCA() {

        return this.SCA;
    }

    /**
     * プロパティ SCA の設定メソッド。
     * @param SCA プロパティ SCA の新しい値。
     */
    public void setSCA(BigDecimal SCA) {

        this.SCA = SCA;
    }

    /**
     * プロパティ SAGW の値を保持。
     */
    private String SAGW;

    /**
     * プロパティ SAGW の取得メソッド。
     * @return プロパティ SAGW の値。
     */
    public String getSAGW() {

        return this.SAGW;
    }

    /**
     * プロパティ SAGW の設定メソッド。
     * @param SAGW プロパティ SAGW の新しい値。
     */
    public void setSAGW(String SAGW) {

        this.SAGW = SAGW;
    }

    /**
     * プロパティ TA の値を保持。
     */
    private String TA;

    /**
     * プロパティ TA の取得メソッド。
     * @return プロパティ TA の値。
     */
    public String getTA() {

        return this.TA;
    }

    /**
     * プロパティ TA の設定メソッド。
     * @param TA プロパティ TA の新しい値。
     */
    public void setTA(String TA) {

        this.TA = TA;
    }

    /**
     * プロパティ HPT の値を保持。
     */
    private String HPT;

    /**
     * プロパティ HPT の取得メソッド。
     * @return プロパティ HPT の値。
     */
    public String getHPT() {

        return this.HPT;
    }

    /**
     * プロパティ HPT の設定メソッド。
     * @param HPT プロパティ HPT の新しい値。
     */
    public void setHPT(String HPT) {

        this.HPT = HPT;
    }

    /**
     * プロパティ ROLL の値を保持。
     */
    private String ROLL;

    /**
     * プロパティ ROLL の取得メソッド。
     * @return プロパティ ROLL の値。
     */
    public String getROLL() {

        return this.ROLL;
    }

    /**
     * プロパティ ROLL の設定メソッド。
     * @param ROLL プロパティ ROLL の新しい値。
     */
    public void setROLL(String ROLL) {

        this.ROLL = ROLL;
    }

    /**
     * プロパティ RYOU の値を保持。
     */
    private String RYOU;

    /**
     * プロパティ RYOU の取得メソッド。
     * @return プロパティ RYOU の値。
     */
    public String getRYOU() {

        return this.RYOU;
    }

    /**
     * プロパティ RYOU の設定メソッド。
     * @param RYOU プロパティ RYOU の新しい値。
     */
    public void setRYOU(String RYOU) {

        this.RYOU = RYOU;
    }

    /**
     * プロパティ RYOW の値を保持。
     */
    private String RYOW;

    /**
     * プロパティ RYOW の取得メソッド。
     * @return プロパティ RYOW の値。
     */
    public String getRYOW() {

        return this.RYOW;
    }

    /**
     * プロパティ RYOW の設定メソッド。
     * @param RYOW プロパティ RYOW の新しい値。
     */
    public void setRYOW(String RYOW) {

        this.RYOW = RYOW;
    }

    /**
     * プロパティ RYON の値を保持。
     */
    private String RYON;

    /**
     * プロパティ RYON の取得メソッド。
     * @return プロパティ RYON の値。
     */
    public String getRYON() {

        return this.RYON;
    }

    /**
     * プロパティ RYON の設定メソッド。
     * @param RYON プロパティ RYON の新しい値。
     */
    public void setRYON(String RYON) {

        this.RYON = RYON;
    }

    /**
     * プロパティ BU の値を保持。
     */
    private String BU;

    /**
     * プロパティ BU の取得メソッド。
     * @return プロパティ BU の値。
     */
    public String getBU() {

        return this.BU;
    }

    /**
     * プロパティ BU の設定メソッド。
     * @param BU プロパティ BU の新しい値。
     */
    public void setBU(String BU) {

        this.BU = BU;
    }

    /**
     * プロパティ BR の値を保持。
     */
    private String BR;

    /**
     * プロパティ BR の取得メソッド。
     * @return プロパティ BR の値。
     */
    public String getBR() {

        return this.BR;
    }

    /**
     * プロパティ BR の設定メソッド。
     * @param BR プロパティ BR の新しい値。
     */
    public void setBR(String BR) {

        this.BR = BR;
    }

    /**
     * プロパティ TR の値を保持。
     */
    private String TR;

    /**
     * プロパティ TR の取得メソッド。
     * @return プロパティ TR の値。
     */
    public String getTR() {

        return this.TR;
    }

    /**
     * プロパティ TR の設定メソッド。
     * @param TR プロパティ TR の新しい値。
     */
    public void setTR(String TR) {

        this.TR = TR;
    }

    /**
     * プロパティ YOJYO の値を保持。
     */
    private String YOJYO;

    /**
     * プロパティ YOJYO の取得メソッド。
     * @return プロパティ YOJYO の値。
     */
    public String getYOJYO() {

        return this.YOJYO;
    }

    /**
     * プロパティ YOJYO の設定メソッド。
     * @param YOJYO プロパティ YOJYO の新しい値。
     */
    public void setYOJYO(String YOJYO) {

        this.YOJYO = YOJYO;
    }

    /**
     * プロパティ ALDT の値を保持。
     */
    private String ALDT;

    /**
     * プロパティ ALDT の取得メソッド。
     * @return プロパティ ALDT の値。
     */
    public String getALDT() {

        return this.ALDT;
    }

    /**
     * プロパティ ALDT の設定メソッド。
     * @param ALDT プロパティ ALDT の新しい値。
     */
    public void setALDT(String ALDT) {

        this.ALDT = ALDT;
    }

    /**
     * プロパティ SYDT の値を保持。
     */
    private String SYDT;

    /**
     * プロパティ SYDT の取得メソッド。
     * @return プロパティ SYDT の値。
     */
    public String getSYDT() {

        return this.SYDT;
    }

    /**
     * プロパティ SYDT の設定メソッド。
     * @param SYDT プロパティ SYDT の新しい値。
     */
    public void setSYDT(String SYDT) {

        this.SYDT = SYDT;
    }

    /**
     * プロパティ MAGMSG の値を保持。
     */
    private String MAGMSG;

    /**
     * プロパティ MAGMSG の取得メソッド。
     * @return プロパティ MAGMSG の値。
     */
    public String getMAGMSG() {

        return this.MAGMSG;
    }

    /**
     * プロパティ MAGMSG の設定メソッド。
     * @param MAGMSG プロパティ MAGMSG の新しい値。
     */
    public void setMAGMSG(String MAGMSG) {

        this.MAGMSG = MAGMSG;
    }

    /**
     * プロパティ HHMM の値を保持。
     */
    private String HHMM;

    /**
     * プロパティ HHMM の取得メソッド。
     * @return プロパティ HHMM の値。
     */
    public String getHHMM() {

        return this.HHMM;
    }

    /**
     * プロパティ HHMM の設定メソッド。
     * @param HHMM プロパティ HHMM の新しい値。
     */
    public void setHHMM(String HHMM) {

        this.HHMM = HHMM;
    }

    /**
     * プロパティ b の値を保持。
     */
    private String b;

    /**
     * プロパティ b の取得メソッド。
     * @return プロパティ b の値。
     */
    public String getB() {

        return this.b;
    }

    /**
     * プロパティ b の設定メソッド。
     * @param b プロパティ b の新しい値。
     */
    public void setB(String b) {

        this.b = b;
    }

    /**
     * プロパティ HPC の値を保持。
     */
    private String HPC;

    /**
     * プロパティ HPC の取得メソッド。
     * @return プロパティ HPC の値。
     */
    public String getHPC() {

        return this.HPC;
    }

    /**
     * プロパティ HPC の設定メソッド。
     * @param HPC プロパティ HPC の新しい値。
     */
    public void setHPC(String HPC) {

        this.HPC = HPC;
    }

    /**
     * プロパティ MSG の値を保持。
     */
    private String MSG;

    /**
     * プロパティ MSG の取得メソッド。
     * @return プロパティ MSG の値。
     */
    public String getMSG() {

        return this.MSG;
    }

    /**
     * プロパティ MSG の設定メソッド。
     * @param MSG プロパティ MSG の新しい値。
     */
    public void setMSG(String MSG) {

        this.MSG = MSG;
    }

    /**
     * プロパティ p の値を保持。
     */
    private BigDecimal p;

    /**
     * プロパティ p の取得メソッド。
     * @return プロパティ p の値。
     */
    public BigDecimal getP() {

        return this.p;
    }

    /**
     * プロパティ p の設定メソッド。
     * @param p プロパティ p の新しい値。
     */
    public void setP(BigDecimal p) {

        this.p = p;
    }

    /**
     * プロパティ ZAIC02 の値を保持。
     */
    private String ZAIC02;

    /**
     * プロパティ ZAIC02 の取得メソッド。
     * @return プロパティ ZAIC02 の値。
     */
    public String getZAIC02() {

        return this.ZAIC02;
    }

    /**
     * プロパティ ZAIC02 の設定メソッド。
     * @param ZAIC02 プロパティ ZAIC02 の新しい値。
     */
    public void setZAIC02(String ZAIC02) {

        this.ZAIC02 = ZAIC02;
    }

    /**
     * プロパティ KC5 の値を保持。
     */
    private String KC5;

    /**
     * プロパティ KC5 の取得メソッド。
     * @return プロパティ KC5 の値。
     */
    public String getKC5() {

        return this.KC5;
    }

    /**
     * プロパティ KC5 の設定メソッド。
     * @param KC5 プロパティ KC5 の新しい値。
     */
    public void setKC5(String KC5) {

        this.KC5 = KC5;
    }

    /**
     * プロパティ HIKIKBN の値を保持。
     */
    private String HIKIKBN;

    /**
     * プロパティ HIKIKBN の取得メソッド。
     * @return プロパティ HIKIKBN の値。
     */
    public String getHIKIKBN() {

        return this.HIKIKBN;
    }

    /**
     * プロパティ HIKIKBN の設定メソッド。
     * @param HIKIKBN プロパティ HIKIKBN の新しい値。
     */
    public void setHIKIKBN(String HIKIKBN) {

        this.HIKIKBN = HIKIKBN;
    }

    /**
     * プロパティ LOT_FLAG の値を保持。
     */
    private String LOT_FLAG;

    /**
     * プロパティ LOT_FLAG の取得メソッド。
     * @return プロパティ LOT_FLAG の値。
     */
    public String getLOT_FLAG() {

        return this.LOT_FLAG;
    }

    /**
     * プロパティ LOT_FLAG の設定メソッド。
     * @param LOT_FLAG プロパティ LOT_FLAG の新しい値。
     */
    public void setLOT_FLAG(String LOT_FLAG) {

        this.LOT_FLAG = LOT_FLAG;
    }

    /**
     * プロパティ MSG1 の値を保持。
     */
    private String MSG1;

    /**
     * プロパティ MSG1 の取得メソッド。
     * @return プロパティ MSG1 の値。
     */
    public String getMSG1() {

        return this.MSG1;
    }

    /**
     * プロパティ MSG1 の設定メソッド。
     * @param MSG1 プロパティ MSG1 の新しい値。
     */
    public void setMSG1(String MSG1) {

        this.MSG1 = MSG1;
    }

    /**
     * プロパティ MSG2 の値を保持。
     */
    private String MSG2;

    /**
     * プロパティ MSG2 の取得メソッド。
     * @return プロパティ MSG2 の値。
     */
    public String getMSG2() {

        return this.MSG2;
    }

    /**
     * プロパティ MSG2 の設定メソッド。
     * @param MSG2 プロパティ MSG2 の新しい値。
     */
    public void setMSG2(String MSG2) {

        this.MSG2 = MSG2;
    }

    /**
     * プロパティ MSG3 の値を保持。
     */
    private String MSG3;

    /**
     * プロパティ MSG3 の取得メソッド。
     * @return プロパティ MSG3 の値。
     */
    public String getMSG3() {

        return this.MSG3;
    }

    /**
     * プロパティ MSG3 の設定メソッド。
     * @param MSG3 プロパティ MSG3 の新しい値。
     */
    public void setMSG3(String MSG3) {

        this.MSG3 = MSG3;
    }

    /**
     * プロパティ MSG4 の値を保持。
     */
    private String MSG4;

    /**
     * プロパティ MSG4 の取得メソッド。
     * @return プロパティ MSG4 の値。
     */
    public String getMSG4() {

        return this.MSG4;
    }

    /**
     * プロパティ MSG4 の設定メソッド。
     * @param MSG4 プロパティ MSG4 の新しい値。
     */
    public void setMSG4(String MSG4) {

        this.MSG4 = MSG4;
    }

    /**
     * プロパティ MSG5 の値を保持。
     */
    private String MSG5;

    /**
     * プロパティ MSG5 の取得メソッド。
     * @return プロパティ MSG5 の値。
     */
    public String getMSG5() {

        return this.MSG5;
    }

    /**
     * プロパティ MSG5 の設定メソッド。
     * @param MSG5 プロパティ MSG5 の新しい値。
     */
    public void setMSG5(String MSG5) {

        this.MSG5 = MSG5;
    }

    /**
     * プロパティ MSG6 の値を保持。
     */
    private String MSG6;

    /**
     * プロパティ MSG6 の取得メソッド。
     * @return プロパティ MSG6 の値。
     */
    public String getMSG6() {

        return this.MSG6;
    }

    /**
     * プロパティ MSG6 の設定メソッド。
     * @param MSG6 プロパティ MSG6 の新しい値。
     */
    public void setMSG6(String MSG6) {

        this.MSG6 = MSG6;
    }

    /**
     * プロパティ MSG7 の値を保持。
     */
    private String MSG7;

    /**
     * プロパティ MSG7 の取得メソッド。
     * @return プロパティ MSG7 の値。
     */
    public String getMSG7() {

        return this.MSG7;
    }

    /**
     * プロパティ MSG7 の設定メソッド。
     * @param MSG7 プロパティ MSG7 の新しい値。
     */
    public void setMSG7(String MSG7) {

        this.MSG7 = MSG7;
    }

    /**
     * プロパティ MSG8 の値を保持。
     */
    private String MSG8;

    /**
     * プロパティ MSG8 の取得メソッド。
     * @return プロパティ MSG8 の値。
     */
    public String getMSG8() {

        return this.MSG8;
    }

    /**
     * プロパティ MSG8 の設定メソッド。
     * @param MSG8 プロパティ MSG8 の新しい値。
     */
    public void setMSG8(String MSG8) {

        this.MSG8 = MSG8;
    }

    private String MSG9;

    public String getMSG9() {
	return this.MSG9;
    }

    public void setMSG9(String MSG9) {
	this.MSG9 = MSG9;
    }
    /**
     * プロパティ CONVNA の値を保持。
     */
    private String CONVNA;

    /**
     * プロパティ CONVNA の取得メソッド。
     * @return プロパティ CONVNA の値。
     */
    public String getCONVNA() {

        return this.CONVNA;
    }

    /**
     * プロパティ CONVNA の設定メソッド。
     * @param CONVNA プロパティ CONVNA の新しい値。
     */
    public void setCONVNA(String CONVNA) {

        this.CONVNA = CONVNA;
    }

    /**
     * プロパティ OTW の値を保持。
     */
    private String OTW;

    /**
     * プロパティ OTW の取得メソッド。
     * @return プロパティ OTW の値。
     */
    public String getOTW() {

        return this.OTW;
    }

    /**
     * プロパティ OTW の設定メソッド。
     * @param OTW プロパティ OTW の新しい値。
     */
    public void setOTW(String OTW) {

        this.OTW = OTW;
    }

    /**
     * プロパティ CFST の値を保持。
     */
    private BigDecimal CFST;

    /**
     * プロパティ CFST の取得メソッド。
     * @return プロパティ CFST の値。
     */
    public BigDecimal getCFST() {

        return this.CFST;
    }

    /**
     * プロパティ CFST の設定メソッド。
     * @param CFST プロパティ CFST の新しい値。
     */
    public void setCFST(BigDecimal CFST) {

        this.CFST = CFST;
    }

    // @S 2011/01/21 ADD
	/**
     * プロパティ 保留区分(退避) の値を保持。
     */
    private String HOKBN_SAVE;

    /**
     * プロパティ 保留区分(退避) の取得メソッド。
     * @return プロパティ 保留区分(退避) の値。
     */
    public String getHOKBN_SAVE() {

        return this.HOKBN_SAVE;
    }

    /**
     * プロパティ 保留区分(退避) の設定メソッド。
     * @param HOKBN_SAVE プロパティ 保留区分(退避) の新しい値。
     */
    public void setHOKBN_SAVE(String HOKBN_SAVE) {

        this.HOKBN_SAVE = HOKBN_SAVE;
    }
	// @E 2011/01/21 ADD

    // 2012/03/20 sasano add sta
    private String KARIKON_FLG;

    public String getKARIKON_FLG() {
	return this.KARIKON_FLG;
    }

    public void setKARIKON_FLG(String KARIKON_FLG) {
	this.KARIKON_FLG = KARIKON_FLG;
    }

    private String GIJUTSU_HOKBN;

    public String getGIJUTSU_HOKBN() {
	return this.GIJUTSU_HOKBN;
    }

    public void setGIJUTSU_HOKBN(String GIJUTSU_HOKBN) {
	this.GIJUTSU_HOKBN = GIJUTSU_HOKBN;
    }
    // 2012/03/20 sasano add end
    private String JRFLG;

    public String getJRFLG() {
	return this.JRFLG;
    }

    public void setJRFLG(String JRFLG) {
	this.JRFLG = JRFLG;
    }

    private String JRSTDT;

    public String getJRSTDT() {
	return this.JRSTDT;
    }

    public void setJRSTDT(String JRSTDT) {
	this.JRSTDT = JRSTDT;
    }

    private String JREDDT;

    public String getJREDDT() {
	return this.JREDDT;
    }

    public void setJREDDT(String JREDDT) {
	this.JREDDT = JREDDT;
    }

    private BigDecimal JRINT;
    /**
     * プロパティ JRINT の取得メソッド。
     * @return プロパティ JRINT の値。
     */
    public BigDecimal getJRINT() {
        return this.JRINT;
    }

    /**
     * プロパティ JRINT の設定メソッド。
     * @param JRINT プロパティ INTPRJ の新しい値。
     */
    public void setJRINT(BigDecimal JRINT) {
        this.JRINT = JRINT;
    }
    private BigDecimal JRTMP;
    /**
     * プロパティ JRINT の取得メソッド。
     * @return プロパティ JRINT の値。
     */
    public BigDecimal getJRTMP() {
        return this.JRTMP;
    }

    /**
     * プロパティ JRINT の設定メソッド。
     * @param JRINT プロパティ INTPRJ の新しい値。
     */
    public void setJRTMP(BigDecimal JRTMP) {
        this.JRTMP = JRTMP;
    }
   // 2012/10/04 表示項目追加 -start-
    /**
     * プロパティ OMOTE の値を保持。
     */
    private String OMOTE;

    /**
     * プロパティ OMOTE の取得メソッド。
     * @return プロパティ OMOTE の値。
     */
    public String getOMOTE() {

        return this.OMOTE;
    }

    /**
     * プロパティ OMOTE の設定メソッド。
     * @param OMOTE プロパティ OMOTE の新しい値。
     */
    public void setOMOTE(String OMOTE) {

        this.OMOTE = OMOTE;
    }

    /**
     * プロパティ URA の値を保持。
     */
    private String URA;

    /**
     * プロパティ URA の取得メソッド。
     * @return プロパティ URA の値。
     */
    public String getURA() {

        return this.URA;
    }

    /**
     * プロパティ URA の設定メソッド。
     * @param URA プロパティ URA の新しい値。
     */
    public void setURA(String URA) {

        this.URA = URA;
    }

    /**
     * プロパティ HANTEN の値を保持。
     */
    private String HANTEN;

    /**
     * プロパティ HANTEN の取得メソッド。
     * @return プロパティ HANTEN の値。
     */
    public String getHANTEN() {

        return this.HANTEN;
    }

    /**
     * プロパティ HANTEN の設定メソッド。
     * @param HANTEN プロパティ HANTEN の新しい値。
     */
    public void setHANTEN(String HANTEN) {

        this.HANTEN = HANTEN;
    }

    /**
     * プロパティ PA の値を保持。
     */
    private String PA;

    /**
     * プロパティ PA の取得メソッド。
     * @return プロパティ PA の値。
     */
    public String getPA() {
        return this.PA;
    }

    /**
     * プロパティ PA の設定メソッド。
     * @param PA プロパティ PA の新しい値。
     */
    public void setPA(String PA) {
        this.PA = PA;
    }

    /**
     * プロパティ CLR の値を保持。
     */
    private String CLR;

    /**
     * プロパティ CLR の取得メソッド。
     * @return プロパティ CLR の値。
     */
    public String getCLR() {
        return this.CLR;
    }

    /**
     * プロパティ CLR の設定メソッド。
     * @param CLR プロパティ CLR の新しい値。
     */
    public void setCLR(String CLR) {
        this.CLR = CLR;
    }

    /**
     * プロパティ MEN の値を保持。
     */
    private String MEN;

    /**
     * プロパティ MEN の取得メソッド。
     * @return プロパティ MEN の値。
     */
    public String getMEN() {

        return this.MEN;
    }

    /**
     * プロパティ MEN の設定メソッド。
     * @param PA プロパティ MEN の新しい値。
     */
    public void setMEN(String MEN) {

        this.MEN = MEN;
    }

    /**
     * プロパティ TUYA の値を保持。
     */
    private String TUYA;

    /**
     * プロパティ TUYA の取得メソッド。
     * @return プロパティ TUYA の値。
     */
    public String getTUYA() {

        return this.TUYA;
    }

    /**
     * プロパティ TUYA の設定メソッド。
     * @param TUYA プロパティ TUYA の新しい値。
     */
    public void setTUYA(String TUYA) {

        this.TUYA = TUYA;
    }

    /**
     * プロパティ FUKO の値を保持。
     */
    private String FUKO;

    /**
     * プロパティ FUKO の取得メソッド。
     * @return プロパティ FUKO の値。
     */
    public String getFUKO() {

        return this.FUKO;
    }

    /**
     * プロパティ FUKO の設定メソッド。
     * @param PA プロパティ FUKO の新しい値。
     */
    public void setFUKO(String FUKO) {

        this.FUKO = FUKO;
    }
    // 2012/10/04 表示項目追加 -end-

    private String JUNO202;

    public String getJUNO202() {
	return JUNO202;
    }

    public void setJUNO202(String JUNO202) {
	this.JUNO202 = JUNO202;
    }

}
