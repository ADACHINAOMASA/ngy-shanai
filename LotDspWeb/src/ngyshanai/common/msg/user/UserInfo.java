package ngyshanai.common.msg.user;

import nis.framework.dictionary.InputModel;

@InputModel
public class UserInfo {

	private String userCd;
	
	private String userMei;
	
	private String tel;
	
	private String mailAddress;
	
	private String kengenId;
	
	private String password;

	public String getUserCd() {
		return userCd;
	}

	public void setUserCd(String userCd) {
		this.userCd = userCd;
	}

	public String getUserMei() {
		return userMei;
	}

	public void setUserMei(String userMei) {
		this.userMei = userMei;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getKengenId() {
		return kengenId;
	}

	public void setKengenId(String kengenId) {
		this.kengenId = kengenId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString(this);
	}
}
