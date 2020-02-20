package nis.framework.web.alert;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * レスポンス用アラート
 * @author Kengo-Nagase
 *
 */
public class UIAlert {

	private UIAlertType type;

	private String msg;

	public UIAlert() {
	}

	public UIAlert(UIAlertType type, String msg) {
		this.type = type;
		this.msg = msg;
	}

	/**
	 * typeを取得します。
	 * @return type
	 */
	public UIAlertType getType() {
	    return type;
	}

	/**
	 * typeを設定します。
	 * @param type type
	 */
	public void setType(UIAlertType type) {
	    this.type = type;
	}

	/**
	 * msgを取得します。
	 * @return msg
	 */
	public String getMsg() {
	    return msg;
	}

	/**
	 * msgを設定します。
	 * @param msg msg
	 */
	public void setMsg(String msg) {
	    this.msg = msg;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
