/*
 * CladInfoBean.java
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
import java.util.List;

/**
 *
 * @author Takanori-Sasano
 */
public class CladInfoBean implements Serializable {

    /** インスタンス */
    public CladInfoBean() {
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
     * 組合せパターン
     */
    private String kr_Pat;

    public String getKr_Pat() {
	return kr_Pat;
    }

    public void setKr_Pat(String kr_Pat) {
	this.kr_Pat = kr_Pat;
    }

    /**
     * 面削代
     */
    private BigDecimal h_Mens;

    public BigDecimal getH_Mens() {
	return h_Mens;
    }

    public void setH_Mens(BigDecimal h_Mens) {
	this.h_Mens = h_Mens;
    }

    /**
     * 重量A
     */
    private BigDecimal jmw;

    public BigDecimal getJmw() {
	return jmw;
    }

    public void setJmw(BigDecimal jmw) {
	this.jmw = jmw;
    }

    /**
     * 面削後厚
     */
    private BigDecimal jmx;

    public BigDecimal getJmx() {
	return jmx;
    }

    public void setJmx(BigDecimal jmx) {
	this.jmx = jmx;
    }

    /**
     * 面削作業日
     */
    private Date jmdtm;

    public Date getJmdtm() {
	return jmdtm;
    }

    public void setJmdtm(Date jmdtm) {
	this.jmdtm = jmdtm;
    }

    /**
     * PIT設計条件A
     */
    private String pitj;

    public String getPitj() {
	return pitj;
    }

    public void setPitj(String pitj) {
	this.pitj = pitj;
    }

    /**
     * PIT設計炉番
     */
    private BigDecimal pit;

    public BigDecimal getPit() {
	return pit;
    }

    public void setPit(BigDecimal pit) {
	this.pit = pit;
    }

    /**
     * PIT設計釜番
     */
    private BigDecimal prj;

    public BigDecimal getPrj() {
	return prj;
    }

    public void setPrj(BigDecimal prj) {
	this.prj = prj;
    }

    /**
     * PIT設計作業日
     */
    private Date pitbi;

    public Date getPitbi() {
	return pitbi;
    }

    public void setPitbi(Date pitbi) {
	this.pitbi = pitbi;
    }

    /**
     * PIT実績条件A
     */
    private String jpj;

    public String getJpj() {
	return jpj;
    }

    public void setJpj(String jpj) {
	this.jpj = jpj;
    }

    /**
     * PIT実績炉番
     */
    private BigDecimal jprb;

    public BigDecimal getJprb() {
	return jprb;
    }

    public void setJprb(BigDecimal jprb) {
	this.jprb = jprb;
    }

    /**
     * PIT実績釜番
     */
    private BigDecimal jprj;

    public BigDecimal getJprj() {
	return jprj;
    }

    public void setJprj(BigDecimal jprj) {
	this.jprj = jprj;
    }

    /**
     * PIT実績作業日
     */
    private Date jpdt;

    public Date getJpdt() {
	return jpdt;
    }

    public void setJpdt(Date jpdt) {
	this.jpdt = jpdt;
    }

    /**
     * クラッド皮材情報リスト
     */
    private List cladInfoBox;

    public List getCladInfoBox() {
        return cladInfoBox;
    }

    public void setCladInfoBox(List cladInfoBox) {
        this.cladInfoBox = cladInfoBox;
    }

}
