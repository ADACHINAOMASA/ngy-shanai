package nis.framework.web.user;

import nis.framework.auth.Authorities;


/**
 * <p>
 * ユーザー情報<br>
 * TODO:どんな情報を取得するか<br>
 * TODO:固有の情報を追加しやすくする
 * </p>
 * @author Kengo-Nagase
 *
 */
public class UserProfile {

	private String userId;

	private String userName;

	private Authorities authority = new Authorities();

	/**
	 * userIdを取得します。
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * userIdを設定します。
	 * @param userId userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * userNameを取得します。
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * userNameを設定します。
	 * @param userName userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * authorityを取得します。
	 * @return authority
	 */
	public Authorities getAuthority() {
	    return authority;
	}

	/**
	 * authorityを設定します。
	 * @param authority authority
	 */
	public void setAuthority(Authorities authority) {
	    this.authority = authority;
	}

}
