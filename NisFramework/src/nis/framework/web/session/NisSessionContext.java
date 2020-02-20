package nis.framework.web.session;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.SessionScoped;

import nis.framework.web.user.UserProfile;

/**
 * セッションコンテキスト
 * 名前にNisがついているのはEJB3.0にも同名のクラスがあり、使用している為
 * @author Kengo-Nagase
 *
 */
@SessionScoped
public class NisSessionContext implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * コンテナ
	 * 基本的に好きに使って構わないが、
	 * 元々用意されている機能で対応できない場合に使う事を推奨
	 */
	private Map<String, Object> container = new HashMap<String, Object>();

	/**
	 * ユーザー情報
	 * TODO:UserProfileを直接inject出来るように
	 */
	private UserProfile userProfile;

	/**
	 * コンテナへの値挿入
	 * @param key
	 * @param value
	 */
	public void putContainerValue(String key, Object value) {
		container.put(key, value);
	}

	/**
	 * コンテナから値取得
	 * @param key
	 * @return
	 */
	public Object getContainerValue(String key) {
		return container.get(key);
	}

	/**
	 * userProfileを取得します。
	 * @return userProfile
	 */
	public UserProfile getUserProfile() {
		return userProfile;
	}

	/**
	 * userProfileを設定します。
	 * @param userProfile userProfile
	 */
	public void setUserProfile(UserProfile userProfile) {
	    this.userProfile = userProfile;
	}

}
