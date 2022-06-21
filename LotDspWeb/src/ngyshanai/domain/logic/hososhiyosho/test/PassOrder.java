package ngyshanai.domain.logic.hososhiyosho.test;





/*
 * PassOrder.java
 *
 * Created on 2006/10/09, 11:44
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

import java.io.Serializable;
import java.util.*;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 * PassOrderは、作業順1ロット毎のデータを保持するBeanクラスです
 * @author Kazuya-Isomura
 * @author Hideyuki-Yamamoto @since 2010/7/16
 */
public class PassOrder implements Serializable {
    
    // 1ロットが保持するパス順の最大数
    public static final int PASS_ORDER_MAX = 5;
    
    /** インスタンスを生成する */
    public PassOrder() {
    }

    /**
     * プロパティ ltNo の値を保持。
     */
    private String ltNo;

    /**
     * プロパティ ltNo の取得メソッド。
     * @return プロパティ ltNo の値。
     */
    public String getLtNo() {

        return this.ltNo;
    }

    /**
     * プロパティ ltNo の設定メソッド。
     * @param ltNo プロパティ ltNo の新しい値。
     */
    public void setLtNo(String ltNo) {

        this.ltNo = ltNo;
    }

    /**
     * プロパティ lta の値を保持。
     */
    private String lta;

    /**
     * プロパティ lta の取得メソッド。
     * @return プロパティ lta の値。
     */
    public String getLta() {

        return this.lta;
    }

    /**
     * プロパティ lta の設定メソッド。
     * @param lta プロパティ lta の新しい値。
     */
    public void setLta(String lta) {

        this.lta = lta;
    }

    /**
     * プロパティ genAtsu の値を保持。
     */
    private double genAtsu;

    /**
     * プロパティ genAtsu の取得メソッド。
     * @return プロパティ genAtsu の値。
     */
    public double getGenAtsu() {

        return this.genAtsu;
    }

    /**
     * プロパティ genAtsu の設定メソッド。
     * @param genAtsu プロパティ genAtsu の新しい値。
     */
    public void setGenAtsu(double genAtsu) {

        this.genAtsu = genAtsu;
    }

    /**
     * プロパティ dstAtsu の値を保持。
     */
    private double dstAtsu;

    /**
     * プロパティ dstAtsu の取得メソッド。
     * @return プロパティ dstAtsu の値。
     */
    public double getDstAtsu() {

        return this.dstAtsu;
    }

    /**
     * プロパティ dstAtsu の設定メソッド。
     * @param dstAtsu プロパティ dstAtsu の新しい値。
     */
    public void setDstAtsu(double dstAtsu) {

        this.dstAtsu = dstAtsu;
    }

    /**
     * プロパティ dstHaba の値を保持。
     */
    private double dstHaba;

    /**
     * プロパティ dstHaba の取得メソッド。
     * @return プロパティ dstHaba の値。
     */
    public double getDstHaba() {

        return this.dstHaba;
    }

    /**
     * プロパティ dstHaba の設定メソッド。
     * @param dstHaba プロパティ dstHaba の新しい値。
     */
    public void setDstHaba(double dstHaba) {

        this.dstHaba = dstHaba;
    }

    /**
     * プロパティ nextSetsubi の値を保持。
     */
    private String nextSetsubi;

    /**
     * プロパティ nextSetsubi の取得メソッド。
     * @return プロパティ nextSetsubi の値。
     */
    public String getNextSetsubi() {

        return this.nextSetsubi;
    }

    /**
     * プロパティ nextSetsubi の設定メソッド。
     * @param nextSetsubi プロパティ nextSetsubi の新しい値。
     */
    public void setNextSetsubi(String nextSetsubi) {

        this.nextSetsubi = nextSetsubi;
    }

    /**
     * プロパティ passCount の値を保持。
     */
    private int passCount;

    /**
     * プロパティ passCount の取得メソッド。
     * @return プロパティ passCount の値。
     */
    public int getPassCount() {

        return this.passCount;
    }

    /**
     * プロパティ passCount の設定メソッド。
     * @param passCount プロパティ passCount の新しい値。
     */
    public void setPassCount(int passCount) {

        this.passCount = passCount;
    }

    /**
     * プロパティ passOrder の値を保持。
     */
    private int[] passOrder = new int[PASS_ORDER_MAX];

    /**
     * プロパティ passOrder の取得メソッド。
     * @return プロパティ passOrder の値。
     */
    public int[] getPassOrder() {

        return this.passOrder;
    }

    /**
     * プロパティ passOrder[index] の取得メソッド。
     * @param index プロパティ passOrder のindex値。
     * @return プロパティ passOrder の値。
     */
     public int getPassOrderByIndex(int index){
        return this.passOrder[index];
    }
    
    /**
     * プロパティ passOrder の設定メソッド。
     * @param passOrder プロパティ passOrder の新しい値。
     */
    public void setPassOrder(int[] passOrder) {

        this.passOrder = passOrder;
    }
    
    /**
     * プロパティ passOrder[index] の設定メソッド。
     * @param index プロパティ passOrder のindex値。
     * @param passOrder プロパティ passOrder の新しい値。
     */
    public void setPassOrderByIndex(int index, int passOrder){
        this.passOrder[index] = passOrder;
    }
    
    /**
     * プロパティ passOrder の初期化メソッド。
     */
    public void clearPassOrder(){
        for(int idx = 0; idx < PASS_ORDER_MAX; idx++){
            this.passOrder[idx] = 0;
        }
    }

    /**
     * プロパティ passNo の値を保持。
     */
    private double[] passNo = new double[PASS_ORDER_MAX];

    /**
     * プロパティ passNo の取得メソッド。
     * @return プロパティ passNo の値。
     */
    public double[] getPassNo() {

        return this.passNo;
    }

    /**
     * プロパティ passNo[index] の取得メソッド。
     * @param index プロパティ passNo のindex値。
     * @return プロパティ passNo の値。
     */
     public double getPassNoByIndex(int index){
        return this.passNo[index];
    }

    /**
     * プロパティ passNo の設定メソッド。
     * @param passNo プロパティ passNo の新しい値。
     */
    public void setPassNo(double[] passNo) {

        this.passNo = passNo;
    }
    
    /**
     * プロパティ passNo[index] の設定メソッド。
     * @param index プロパティ passNo のindex値。
     * @param passNo プロパティ passNo の新しい値。
     */
    public void setPassNoByIndex(int index, double passNo){
        this.passNo[index] = passNo;
    }
    
    /**
     * プロパティ passNo の初期化メソッド。
     */
    public void clearPassNo(){
        for(int idx = 0; idx < PASS_ORDER_MAX; idx++){
            this.passNo[idx] = 0.0;
        }
    }

    /**
     * プロパティ endDate の値を保持。
     */
    private Date[] endDate = new Date[PASS_ORDER_MAX];

    /**
     * プロパティ endDate の取得メソッド。
     * @return プロパティ endDate の値。
     */
    public Date[] getEndDate() {

        return this.endDate;
    }

    /**
     * プロパティ endDate[index] の取得メソッド。
     * @param index プロパティ endDate のindex値。
     * @return プロパティ endDate の値。
     */
     public Date getEndDateByIndex(int index){
        return this.endDate[index];
    }

    /**
     * プロパティ endDate の設定メソッド。
     * @param endDate プロパティ endDate の新しい値。
     */
    public void setEndDate(Date[] endDate) {

        this.endDate = endDate;
    }
  
    /**
     * プロパティ endDate[index] の設定メソッド。
     * @param index プロパティ endDate のindex値。
     * @param endDate プロパティ endDate の新しい値。
     */
    public void setEndDateByIndex(int index, Date endDate){
        this.endDate[index] = endDate;
    }
    
    /**
     * プロパティ endDate の初期化メソッド。
     */
    public void clearEndDate(){
        for(int idx = 0; idx < PASS_ORDER_MAX; idx++){
            this.endDate[idx] = null;
        }
    }

    /**
     * プロパティ setLinkKey の値を保持。
     */
    private String setLinkKey;

    /**
     * プロパティ setLinkKey の取得メソッド。
     * @return プロパティ setLinkKey の値。
     */
    public String getSetLinkKey() {

        return this.setLinkKey;
    }

    /**
     * プロパティ setLinkKey の設定メソッド。
     * @param setLinkKey プロパティ setLinkKey の新しい値。
     */
    public void setSetLinkKey(String setLinkKey) {

        this.setLinkKey = setLinkKey;
    }
	
	// WAS v7.0 対応
	/**
	 * 1パス目のオーダー番号を取得します。
	 * @return 1パス目のオーダー番号
	 * @since 2010/7/16
	 */
	public int getPassOrder1() {
		return getPassOrder()[0];
	}
	
	/**
	 * 1パス目のオーダー番号を設定します。
	 * @param passOrder 1パス目のオーダー番号
	 * @since 2010/7/16
	 */
	public void setPassOrder1(int passOrder) {
		setPassOrderByIndex(0, passOrder);
	}
	
	/**
	 * 2パス目のオーダー番号を取得します。
	 * @return 2パス目のオーダー番号
	 * @since 2010/7/16
	 */
	public int getPassOrder2() {
		return getPassOrder()[1];
	}
	
	/**
	 * 2パス目のオーダー番号を設定します。
	 * @param passOrder 2パス目のオーダー番号
	 * @since 2010/7/16
	 */
	public void setPassOrder2(int passOrder) {
		setPassOrderByIndex(1, passOrder);
	}
	
	/**
	 * 3パス目のオーダー番号を取得します。
	 * @return 3パス目のオーダー番号
	 * @since 2010/7/16
	 */
	public int getPassOrder3() {
		return getPassOrder()[2];
	}
	
	/**
	 * 3パス目のオーダー番号を設定します。
	 * @param passOrder 3パス目のオーダー番号
	 * @since 2010/7/16
	 */
	public void setPassOrder3(int passOrder) {
		setPassOrderByIndex(2, passOrder);
	}
	
	/**
	 * 4パス目のオーダー番号を取得します。
	 * @return 4パス目のオーダー番号
	 * @since 2010/7/16
	 */
	public int getPassOrder4() {
		return getPassOrder()[3];
	}
	
	/**
	 * 4パス目のオーダー番号を設定します。
	 * @param passOrder 4パス目のオーダー番号
	 * @since 2010/7/16
	 */
	public void setPassOrder4(int passOrder) {
		setPassOrderByIndex(3, passOrder);
	}
	
	/**
	 * 5パス目のオーダー番号を取得します。
	 * @return 5パス目のオーダー番号
	 * @since 2010/7/16
	 */
	public int getPassOrder5() {
		return getPassOrder()[4];
	}
	
	/**
	 * 5パス目のオーダー番号を設定します。
	 * @param passOrder 5パス目のオーダー番号
	 * @since 2010/7/16
	 */
	public void setPassOrder5(int passOrder) {
		setPassOrderByIndex(4, passOrder);
	}
	
}
