package ngyshanai.domain.nagoya.bean;

import java.io.Serializable;
import java.util.Date;

/*
 * ロット問合せユーザー情報
 */
public class LotDspUserBean implements Serializable {

    private String userId;
    private String simei;
    private String furigana;
    private String userpass;
    private Date createDate;
    private Date modDate;

    public LotDspUserBean() {
    }

    public String getUser_Id() {
	return userId;
    }

    public void setUser_Id(String userId) {
	this.userId = userId;
    }

    public Date getCreate_Date() {
	return createDate;
    }

    public void setCreate_Date(Date createDate) {
	this.createDate = createDate;
    }

    public String getFurigana() {
	return furigana;
    }

    public void setFurigana(String furigana) {
	this.furigana = furigana;
    }

    public Date getMod_Date() {
	return modDate;
    }

    public void setMod_Date(Date modDate) {
	this.modDate = modDate;
    }

    public String getSimei() {
	return simei;
    }

    public void setSimei(String simei) {
	this.simei = simei;
    }

    public String getUserpass() {
	return userpass;
    }

    public void setUserpass(String userpass) {
	this.userpass = userpass;
    }

}
