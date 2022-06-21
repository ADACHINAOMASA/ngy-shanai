package ngyshanai.common.msg.login;

import nis.framework.dictionary.InputModel;
import nis.framework.dictionary.MaxLength;
import nis.framework.dictionary.OmojiKomoji;
import nis.framework.dictionary.OmojiKomoji.OmojiKomojiElm;
import nis.framework.dictionary.Zenhankaku;
import nis.framework.dictionary.Zenhankaku.ZenhankakuElm;

@InputModel
public class LoginInfo {

	private String id;

	@MaxLength(200)
	@Zenhankaku(ZenhankakuElm.HANKAKU)
	@OmojiKomoji(OmojiKomojiElm.KONZAI)
	private String password;

	@MaxLength(8)
	@Zenhankaku(ZenhankakuElm.HANKAKU)
	@OmojiKomoji(OmojiKomojiElm.KONZAI)
	private String userId;
	
	@MaxLength(40)
	private String userName;

	/**
	 * idを取得します。
	 * @return id
	 */
	public String getId() {
	    return id;
	}

	/**
	 * idを設定します。
	 * @param id id
	 */
	public void setId(String id) {
	    this.id = id;
	}

	/**
	 * passwordを取得します。
	 * @return password
	 */
	public String getPassword() {
	    return password;
	}

	/**
	 * passwordを設定します。
	 * @param password password
	 */
	public void setPassword(String password) {
	    this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
}
