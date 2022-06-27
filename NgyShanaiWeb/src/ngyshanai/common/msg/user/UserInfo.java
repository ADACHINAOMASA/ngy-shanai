package ngyshanai.common.msg.user;

import nis.framework.dictionary.InputModel;

@InputModel
public class UserInfo {

	private String userCd;
	
	private String userName;
	
	private String tel;
	
	private String mailAddress;
	
	private String password;

	public String getUserCd() {
		return userCd;
	}

	public void setUserCd(String userCd) {
		this.userCd = userCd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
