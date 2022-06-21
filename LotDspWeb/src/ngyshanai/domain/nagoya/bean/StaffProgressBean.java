/*
 * StaffProgressBean.java
 *
 * Created on 2006/11/15, 17:32
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package ngyshanai.domain.nagoya.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Hirohiko-Matsushita
 */
public class StaffProgressBean implements Serializable {

    /** Creates a new instance of StaffProgressBean */
    public StaffProgressBean() {
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
     * プロパティ SBD の値を保持。
     */
    private BigDecimal SBD;

    /**
     * プロパティ SBD の取得メソッド。
     * @return プロパティ SBD の値。
     */
    public BigDecimal getSBD() {

        return this.SBD;
    }

    /**
     * プロパティ SBD の設定メソッド。
     * @param SBD プロパティ SBD の新しい値。
     */
    public void setSBD(BigDecimal SBD) {

        this.SBD = SBD;
    }

    /**
     * プロパティ KBD の値を保持。
     */
    private BigDecimal KBD;

    /**
     * プロパティ KBD の取得メソッド。
     * @return プロパティ KBD の値。
     */
    public BigDecimal getKBD() {

        return this.KBD;
    }

    /**
     * プロパティ KBD の設定メソッド。
     * @param KBD プロパティ KBD の新しい値。
     */
    public void setKBD(BigDecimal KBD) {

        this.KBD = KBD;
    }

    /**
     * プロパティ HOHDT の値を保持。
     */
    private String HOHDT;

    /**
     * プロパティ HOHDT の取得メソッド。
     * @return プロパティ HOHDT の値。
     */
    public String getHOHDT() {

        return this.HOHDT;
    }

    /**
     * プロパティ HOHDT の設定メソッド。
     * @param HOHDT プロパティ HOHDT の新しい値。
     */
    public void setHOHDT(String HOHDT) {

        this.HOHDT = HOHDT;
    }

    /**
     * プロパティ HOKDT の値を保持。
     */
    private String HOKDT;

    /**
     * プロパティ HOKDT の取得メソッド。
     * @return プロパティ HOKDT の値。
     */
    public String getHOKDT() {

        return this.HOKDT;
    }

    /**
     * プロパティ HOKDT の設定メソッド。
     * @param HOKDT プロパティ HOKDT の新しい値。
     */
    public void setHOKDT(String HOKDT) {

        this.HOKDT = HOKDT;
    }

    /**
     * プロパティ KKN の値を保持。
     */
    private BigDecimal KKN;

    /**
     * プロパティ KKN の取得メソッド。
     * @return プロパティ KKN の値。
     */
    public BigDecimal getKKN() {

        return this.KKN;
    }

    /**
     * プロパティ KKN の設定メソッド。
     * @param KKN プロパティ KKN の新しい値。
     */
    public void setKKN(BigDecimal KKN) {

        this.KKN = KKN;
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
     * プロパティ SAITEHAILTNO の値を保持。
     */
    private String SAITEHAILTNO;

    /**
     * プロパティ SAITEHAILTNO の取得メソッド。
     * @return プロパティ SAITEHAILTNO の値。
     */
    public String getSAITEHAILTNO() {

        return this.SAITEHAILTNO;
    }

    /**
     * プロパティ SAITEHAILTNO の設定メソッド。
     * @param SAITEHAILTNO プロパティ SAITEHAILTNO の新しい値。
     */
    public void setSAITEHAILTNO(String SAITEHAILTNO) {

        this.SAITEHAILTNO = SAITEHAILTNO;
    }

    /**
     * プロパティ SAITEHAICYUNO の値を保持。
     */
    private String SAITEHAICYUNO;

    /**
     * プロパティ SAITEHAICYUNO の取得メソッド。
     * @return プロパティ SAITEHAICYUNO の値。
     */
    public String getSAITEHAICYUNO() {

        return this.SAITEHAICYUNO;
    }

    /**
     * プロパティ SAITEHAICYUNO の設定メソッド。
     * @param SAITEHAICYUNO プロパティ SAITEHAICYUNO の新しい値。
     */
    public void setSAITEHAICYUNO(String SAITEHAICYUNO) {

        this.SAITEHAICYUNO = SAITEHAICYUNO;
    }

    /**
     * プロパティ SAILINKKEY の値を保持。
     */
    private String SAILINKKEY;

    /**
     * プロパティ SAILINKKEY の取得メソッド。
     * @return プロパティ SAILINKKEY の値。
     */
    public String getSAILINKKEY() {

        return this.SAILINKKEY;
    }

    /**
     * プロパティ SAILINKKEY の設定メソッド。
     * @param SAILINKKEY プロパティ SAILINKKEY の新しい値。
     */
    public void setSAILINKKEY(String SAILINKKEY) {

        this.SAILINKKEY = SAILINKKEY;
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
     * プロパティ HIKICODE の値を保持。
     */
    private String HIKICODE;

    /**
     * プロパティ HIKICODE の取得メソッド。
     * @return プロパティ HIKICODE の値。
     */
    public String getHIKICODE() {

        return this.HIKICODE;
    }

    /**
     * プロパティ HIKICODE の設定メソッド。
     * @param HIKICODE プロパティ HIKICODE の新しい値。
     */
    public void setHIKICODE(String HIKICODE) {

        this.HIKICODE = HIKICODE;
    }

    /**
     * プロパティ MSNOK の値を保持。
     */
    private String MSNOK;

    /**
     * プロパティ MSNOK の取得メソッド。
     * @return プロパティ MSNOK の値。
     */
    public String getMSNOK() {

        return this.MSNOK;
    }

    /**
     * プロパティ MSNOK の設定メソッド。
     * @param MSNOK プロパティ MSNOK の新しい値。
     */
    public void setMSNOK(String MSNOK) {

        this.MSNOK = MSNOK;
    }

    /**
     * プロパティ MSNOH の値を保持。
     */
    private String MSNOH;

    /**
     * プロパティ MSNOH の取得メソッド。
     * @return プロパティ MSNOH の値。
     */
    public String getMSNOH() {

        return this.MSNOH;
    }

    /**
     * プロパティ MSNOH の設定メソッド。
     * @param MSNOH プロパティ MSNOH の新しい値。
     */
    public void setMSNOH(String MSNOH) {

        this.MSNOH = MSNOH;
    }

    /**
     * プロパティ MSNOS の値を保持。
     */
    private String MSNOS;

    /**
     * プロパティ MSNOS の取得メソッド。
     * @return プロパティ MSNOS の値。
     */
    public String getMSNOS() {

        return this.MSNOS;
    }

    /**
     * プロパティ MSNOS の設定メソッド。
     * @param MSNOS プロパティ MSNOS の新しい値。
     */
    public void setMSNOS(String MSNOS) {

        this.MSNOS = MSNOS;
    }

    /**
     * プロパティ MODDATE の値を保持。
     */
    private Date MODDATE;

    /**
     * プロパティ MODDATE の取得メソッド。
     * @return プロパティ MODDATE の値。
     */
    public Date getMODDATE() {

        return this.MODDATE;
    }

    /**
     * プロパティ MODDATE の設定メソッド。
     * @param MODDATE プロパティ MODDATE の新しい値。
     */
    public void setMODDATE(Date MODDATE) {

        this.MODDATE = MODDATE;
    }

    /**
     * プロパティ staffProgressBoxInfos の値を保持。
     */
    private List staffProgressBoxInfos;

    /**
     * プロパティ staffProgressBoxInfos の取得メソッド。
     * @return プロパティ staffProgressBoxInfos の値。
     */
    public List getStaffProgressBoxInfos() {

        return this.staffProgressBoxInfos;
    }

    /**
     * プロパティ staffProgressBoxInfos の設定メソッド。
     * @param staffProgressBoxInfos プロパティ staffProgressBoxInfos の新しい値。
     */
    public void setStaffProgressBoxInfos(List staffProgressBoxInfos) {

        this.staffProgressBoxInfos = staffProgressBoxInfos;
    }

    /**
     * プロパティ GAI_IDX1 の値を保持。
     */
    private String GAI_IDX1;

    /**
     * プロパティ GAI_IDX1 の取得メソッド。
     * @return プロパティ GAI_IDX1 の値。
     */
    public String getGAI_IDX1() {

        return this.GAI_IDX1;
    }

    /**
     * プロパティ GAI_IDX1 の設定メソッド。
     * @param GAI_IDX1 プロパティ GAI_IDX1 の新しい値。
     */
    public void setGAI_IDX1(String GAI_IDX1) {

        this.GAI_IDX1 = GAI_IDX1;
    }

    /**
     * プロパティ GAI_IDX2 の値を保持。
     */
    private String GAI_IDX2;

    /**
     * プロパティ GAI_IDX2 の取得メソッド。
     * @return プロパティ GAI_IDX2 の値。
     */
    public String getGAI_IDX2() {

        return this.GAI_IDX2;
    }

    /**
     * プロパティ GAI_IDX2 の設定メソッド。
     * @param GAI_IDX2 プロパティ GAI_IDX2 の新しい値。
     */
    public void setGAI_IDX2(String GAI_IDX2) {

        this.GAI_IDX2 = GAI_IDX2;
    }

    /**
     * プロパティ GAI_IDX3 の値を保持。
     */
    private String GAI_IDX3;

    /**
     * プロパティ GAI_IDX3 の取得メソッド。
     * @return プロパティ GAI_IDX3 の値。
     */
    public String getGAI_IDX3() {

        return this.GAI_IDX3;
    }

    /**
     * プロパティ GAI_IDX3 の設定メソッド。
     * @param GAI_IDX3 プロパティ GAI_IDX3 の新しい値。
     */
    public void setGAI_IDX3(String GAI_IDX3) {

        this.GAI_IDX3 = GAI_IDX3;
    }

    /**
     * プロパティ GAI_SETUBI1 の値を保持。
     */
    private String GAI_SETUBI1;

    /**
     * プロパティ GAI_SETSUBI1 の取得メソッド。
     * @return プロパティ GAI_SETSUBI1 の値。
     */
    public String getGAI_SETUBI1()  {

        return this.GAI_SETUBI1;
    }

    /**
     * プロパティ GAI_SETSUBI1 の設定メソッド。
     * @param GAI_SETSUBI1 プロパティ GAI_SETSUBI1 の新しい値。
     */
    public void setGAI_SETUBI1(String GAI_SETUBI1)  {

        this.GAI_SETUBI1 = GAI_SETUBI1;
    }

    /**
     * プロパティ GAI_SETUBI2 の値を保持。
     */
    private String GAI_SETUBI2;

    /**
     * プロパティ GAI_SETSUBI2 の取得メソッド。
     * @return プロパティ GAI_SETSUBI2 の値。
     */
    public String getGAI_SETUBI2()  {

        return this.GAI_SETUBI2;
    }

    /**
     * プロパティ GAI_SETSUBI2 の設定メソッド。
     * @param GAI_SETSUBI2 プロパティ GAI_SETSUBI2 の新しい値。
     */
    public void setGAI_SETUBI2(String GAI_SETUBI2)  {

        this.GAI_SETUBI2 = GAI_SETUBI2;
    }

    /**
     * プロパティ GAI_SETUBI3 の値を保持。
     */
    private String GAI_SETUBI3;

    /**
     * プロパティ GAI_SETSUBI3 の取得メソッド。
     * @return プロパティ GAI_SETSUBI3 の値。
     */
    public String getGAI_SETUBI3()  {

        return this.GAI_SETUBI3;
    }

    /**
     * プロパティ GAI_SETSUBI3 の設定メソッド。
     * @param GAI_SETSUBI3 プロパティ GAI_SETSUBI3 の新しい値。
     */
    public void setGAI_SETUBI3(String GAI_SETUBI3)  {

        this.GAI_SETUBI3 = GAI_SETUBI3;
    }

    /**
     * プロパティ GAI_CD1 の値を保持。
     */
    private String GAI_CD1;

    /**
     * プロパティ GAI_CD1 の取得メソッド。
     * @return プロパティ GAI_CD1 の値。
     */
    public String getGAI_CD1() {

        return this.GAI_CD1;
    }

    /**
     * プロパティ GAI_CD1 の設定メソッド。
     * @param GAI_CD1 プロパティ GAI_CD1 の新しい値。
     */
    public void setGAI_CD1(String GAI_CD1) {

        this.GAI_CD1 = GAI_CD1;
    }

    /**
     * プロパティ GAI_CD2 の値を保持。
     */
    private String GAI_CD2;

    /**
     * プロパティ GAI_CD2 の取得メソッド。
     * @return プロパティ GAI_CD2 の値。
     */
    public String getGAI_CD2() {

        return this.GAI_CD2;
    }

    /**
     * プロパティ GAI_CD2 の設定メソッド。
     * @param GAI_CD2 プロパティ GAI_CD2 の新しい値。
     */
    public void setGAI_CD2(String GAI_CD2) {

        this.GAI_CD2 = GAI_CD2;
    }

    /**
     * プロパティ GAI_CD3 の値を保持。
     */
    private String GAI_CD3;

    /**
     * プロパティ GAI_CD3 の取得メソッド。
     * @return プロパティ GAI_CD3 の値。
     */
    public String getGAI_CD3() {

        return this.GAI_CD3;
    }

    /**
     * プロパティ GAI_CD3 の設定メソッド。
     * @param GAI_CD3 プロパティ GAI_CD3 の新しい値。
     */
    public void setGAI_CD3(String GAI_CD3) {

        this.GAI_CD3 = GAI_CD3;
    }

    /**
     * プロパティ GAI_TODAY1 の値を保持。
     */
    private Date GAI_TODAY1;

    /**
     * プロパティ GAI_TODAY1 の取得メソッド。
     * @return プロパティ GAI_TODAY1 の値。
     */
    public Date getGAI_TODAY1() {

        return this.GAI_TODAY1;
    }

    /**
     * プロパティ GAI_TODAY1 の設定メソッド。
     * @param GAI_TODAY1 プロパティ GAI_TODAY1 の新しい値。
     */
    public void setGAI_TODAY1(Date GAI_TODAY1) {

        this.GAI_TODAY1 = GAI_TODAY1;
    }

    /**
     * プロパティ GAI_TODAY2 の値を保持。
     */
    private Date GAI_TODAY2;

    /**
     * プロパティ GAI_TODAY2 の取得メソッド。
     * @return プロパティ GAI_TODAY2 の値。
     */
    public Date getGAI_TODAY2() {

        return this.GAI_TODAY2;
    }

    /**
     * プロパティ GAI_TODAY2 の設定メソッド。
     * @param GAI_TODAY2 プロパティ GAI_TODAY2 の新しい値。
     */
    public void setGAI_TODAY2(Date GAI_TODAY2) {

        this.GAI_TODAY2 = GAI_TODAY2;
    }

    /**
     * プロパティ GAI_TODAY3 の値を保持。
     */
    private Date GAI_TODAY3;

    /**
     * プロパティ GAI_TODAY3 の取得メソッド。
     * @return プロパティ GAI_TODAY3 の値。
     */
    public Date getGAI_TODAY3() {

        return this.GAI_TODAY3;
    }

    /**
     * プロパティ GAI_TODAY3 の設定メソッド。
     * @param GAI_TODAY3 プロパティ GAI_TODAY3 の新しい値。
     */
    public void setGAI_TODAY3(Date GAI_TODAY3) {

        this.GAI_TODAY3 = GAI_TODAY3;
    }

    /**
     * プロパティ GAI_OKURI1 の値を保持。
     */
    private String GAI_OKURI1;

    /**
     * プロパティ GAI_OKURI1 の取得メソッド。
     * @return プロパティ GAI_OKURI1 の値。
     */
    public String getGAI_OKURI1() {

        return this.GAI_OKURI1;
    }

    /**
     * プロパティ GAI_OKURI1 の設定メソッド。
     * @param GAI_OKURI1 プロパティ GAI_OKURI1 の新しい値。
     */
    public void setGAI_OKURI1(String GAI_OKURI1) {

        this.GAI_OKURI1 = GAI_OKURI1;
    }

    /**
     * プロパティ GAI_OKURI2 の値を保持。
     */
    private String GAI_OKURI2;

    /**
     * プロパティ GAI_OKURI2 の取得メソッド。
     * @return プロパティ GAI_OKURI2 の値。
     */
    public String getGAI_OKURI2() {

        return this.GAI_OKURI2;
    }

    /**
     * プロパティ GAI_OKURI2 の設定メソッド。
     * @param GAI_OKURI2 プロパティ GAI_OKURI2 の新しい値。
     */
    public void setGAI_OKURI2(String GAI_OKURI2) {

        this.GAI_OKURI2 = GAI_OKURI2;
    }

    /**
     * プロパティ GAI_OKURI3 の値を保持。
     */
    private String GAI_OKURI3;

    /**
     * プロパティ GAI_OKURI3 の取得メソッド。
     * @return プロパティ GAI_OKURI3 の値。
     */
    public String getGAI_OKURI3() {

        return this.GAI_OKURI3;
    }

    /**
     * プロパティ GAI_OKURI3 の設定メソッド。
     * @param GAI_OKURI3 プロパティ GAI_OKURI3 の新しい値。
     */
    public void setGAI_OKURI3(String GAI_OKURI3) {

        this.GAI_OKURI3 = GAI_OKURI3;
    }

    /**
     * プロパティ SRAITO の値を保持。
     */
    private BigDecimal SRAITO;

    /**
     * プロパティ SRAITO の取得メソッド。
     * @return プロパティ SRAITO の値。
     */
    public BigDecimal getSRAITO() {

        return this.SRAITO;
    }

    /**
     * プロパティ SRAITO の設定メソッド。
     * @param SRAITO プロパティ SRAITO の新しい値。
     */
    public void setSRAITO(BigDecimal SRAITO) {

        this.SRAITO = SRAITO;
    }

    /**
     * プロパティ SAI_COUNT の値を保持。
     */
    private BigDecimal SAI_COUNT;

    /**
     * プロパティ SAI_COUNT の取得メソッド。
     * @return プロパティ SAI_COUNT の値。
     */
    public BigDecimal getSAI_COUNT() {

        return this.SAI_COUNT;
    }

    /**
     * プロパティ SAI_COUNT の設定メソッド。
     * @param SAI_COUNT プロパティ SAI_COUNT の新しい値。
     */
    public void setSAI_COUNT(BigDecimal SAI_COUNT) {

        this.SAI_COUNT = SAI_COUNT;
    }

    /**
     * プロパティ staffProgressSBoxInfos の値を保持。
     */
    private List staffProgressSBoxInfos;

    /**
     * プロパティ staffProgressSBoxInfos の取得メソッド。
     * @return プロパティ staffProgressSBoxInfos の値。
     */
    public List getStaffProgressSBoxInfos() {

        return this.staffProgressSBoxInfos;
    }

    /**
     * プロパティ staffProgressSBoxInfos の設定メソッド。
     * @param staffProgressSBoxInfos プロパティ staffProgressSBoxInfos の新しい値。
     */
    public void setStaffProgressSBoxInfos(List staffProgressSBoxInfos) {

        this.staffProgressSBoxInfos = staffProgressSBoxInfos;
    }

    /**
     * プロパティ staffProgressJBoxInfos の値を保持。
     */
    private List staffProgressJBoxInfos;

    /**
     * プロパティ staffProgressJBoxInfos の取得メソッド。
     * @return プロパティ staffProgressJBoxInfos の値。
     */
    public List getStaffProgressJBoxInfos() {

        return this.staffProgressJBoxInfos;
    }

    /**
     * プロパティ staffProgressJBoxInfos の設定メソッド。
     * @param staffProgressJBoxInfos プロパティ staffProgressJBoxInfos の新しい値。
     */
    public void setStaffProgressJBoxInfos(List staffProgressJBoxInfos) {

        this.staffProgressJBoxInfos = staffProgressJBoxInfos;
    }

    // 2012-10-05 test  -start-
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
    // 2012-10-05 test  -end-
}
