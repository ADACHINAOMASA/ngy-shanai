/*
 * SearchKeyBean.java
 *
 * Created on 2006/11/15, 16:27
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package ngyshanai.domain.nagoya.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Hirohiko-Matsushita
 */
public class SearchKeyBean implements Serializable {

    /** Creates a new instance of StaffCommonBean */
    public SearchKeyBean() {
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
     * 鋳番年
     */
    private BigDecimal CYUZO_YY;

    public BigDecimal getCYUZO_YY(){
	return this.CYUZO_YY;
    }

    public void setCYUZO_YY(BigDecimal CYUZO_YY){
	this.CYUZO_YY = CYUZO_YY;
    }

    /**
     * 判別番号
     */
    private String TBLORDER;

    public String getTBLORDER(){
	return this.TBLORDER;
    }

    public void setTBLORDER(String TBLORDER){
	this.TBLORDER = TBLORDER;
    }

}
