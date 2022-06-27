/*
 * CladInfoBoxBean.java
 *
 * Created on 2011/07/01
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ngyshanai.domain.nagoya.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Takanori-Sasano
 */
public class CladInfoBoxBean implements Serializable {

    /** インスタンス */
    public CladInfoBoxBean() {
    }

    /**
     * プロパティ LINKKEY の値を保持。
     */
    private String linkKey;

    public String getLinkKey() {
	return linkKey;
    }

    public void setLinkKey(String linkKey) {
	this.linkKey = linkKey;
    }

    /**
     * 鋳造番号
     */
    private String kr_Cyno;

    public String getKr_Cyno() {
	return kr_Cyno;
    }

    public void setKr_Cyno(String kr_Cyno) {
	this.kr_Cyno = kr_Cyno;
    }

    /**
     * 材質
     */
    private String lta;

    public String getLta() {
	return lta;
    }

    public void setLta(String lta) {
	this.lta = lta;
    }

    /**
     * 条件A
     */
    private String JPJ;

    public String getJPJ() {
	return JPJ;
    }

    public void setJPJ(String JPJ) {
	this.JPJ = JPJ;
    }

    /**
     * 炉番
     */
    private BigDecimal JPRB;

    public BigDecimal getJPRB() {
	return JPRB;
    }

    public void setJPRB(BigDecimal JPRB) {
	this.JPRB = JPRB;
    }

    /**
     * 釜番
     */
    private BigDecimal JPRJ;

    public BigDecimal getJPRJ() {
	return JPRJ;
    }

    public void setJPRJ(BigDecimal JPRJ) {
	this.JPRJ = JPRJ;
    }

    /**
     * 作業日
     */
    private Date JPDT;

    public Date getJPDT() {
	return JPDT;
    }

    public void setJPDT(Date JPDT) {
	this.JPDT = JPDT;
    }

    /**
     * 第一均熱開始時刻
     */
    private Date jpdt2;

    public Date getJpdt2() {
	return jpdt2;
    }

    public void setJpdt2(Date jpdt2) {
	this.jpdt2 = jpdt2;
    }

    /**
     * 第一均熱温度
     */
    private BigDecimal jpo2;

    public BigDecimal getJpo2() {
	return jpo2;
    }

    public void setJpo2(BigDecimal jpo2) {
	this.jpo2 = jpo2;
    }

    /**
     * 第二均熱開始時刻
     */
    private Date jpdt3;

    public Date getJpdt3() {
	return jpdt3;
    }

    public void setJpdt3(Date jpdt3) {
	this.jpdt3 = jpdt3;
    }

    /**
     * 第二均熱温度
     */
    private BigDecimal jpo3;

    public BigDecimal getJpo3() {
	return jpo3;
    }

    public void setJpo3(BigDecimal jpo3) {
	this.jpo3 = jpo3;
    }

    /**
     * 炉出時刻
     */
    private Date jpdt4;

    public Date getJpdt4() {
	return jpdt4;
    }

    public void setJpdt4(Date jpdt4) {
	this.jpdt4 = jpdt4;
    }

}
