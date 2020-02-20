/*
 * LotSchCboBean.java
 *
 * Created on 2006/10/10, 20:19
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package lotdsp.domain.nagoya.logic;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Hirohiko-Matsushita
 */
public class LotSchCboBean implements Serializable {

    /** Creates a new instance of LotSchCboBean */
    public LotSchCboBean() {
    }

    /**
     * プロパティ ltno の値を保持。
     */
    private String ltno;

    /**
     * プロパティ ltno の取得メソッド。
     * @return プロパティ ltno の値。
     */
    public String getLtno() {

        return this.ltno;
    }

    /**
     * プロパティ ltno の設定メソッド。
     * @param ltno プロパティ ltno の新しい値。
     */
    public void setLtno(String ltno) {

        this.ltno = ltno;
    }

    /**
     * プロパティ cyno の値を保持。
     */
    private String cyno;

    /**
     * プロパティ cyno の取得メソッド。
     * @return プロパティ cyno の値。
     */
    public String getCyno() {

        return this.cyno;
    }

    /**
     * プロパティ cyno の設定メソッド。
     * @param cyno プロパティ cyno の新しい値。
     */
    public void setCyno(String cyno) {

        this.cyno = cyno;
    }

    /**
     * プロパティ knno の値を保持。
     */
    private String knno;

    /**
     * プロパティ knno の取得メソッド。
     * @return プロパティ knno の値。
     */
    public String getKnno() {

        return this.knno;
    }

    /**
     * プロパティ knno の設定メソッド。
     * @param knno プロパティ knno の新しい値。
     */
    public void setKnno(String knno) {

        this.knno = knno;
    }

    /**
     * プロパティ jua の値を保持。
     */
    private String jua;

    /**
     * プロパティ jua の取得メソッド。
     * @return プロパティ jua の値。
     */
    public String getJua() {

        return this.jua;
    }

    /**
     * プロパティ jua の設定メソッド。
     * @param jua プロパティ jua の新しい値。
     */
    public void setJua(String jua) {

        this.jua = jua;
    }

    /**
     * プロパティ ltx の値を保持。
     */
    private BigDecimal ltx;

    /**
     * プロパティ ltx の取得メソッド。
     * @return プロパティ ltx の値。
     */
    public BigDecimal getLtx() {

        return this.ltx;
    }

    /**
     * プロパティ ltx の設定メソッド。
     * @param ltx プロパティ ltx の新しい値。
     */
    public void setLtx(BigDecimal ltx) {

        this.ltx = ltx;
    }

}
