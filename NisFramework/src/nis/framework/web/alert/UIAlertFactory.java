package nis.framework.web.alert;

/**
 * レスポンス用アラートファクトリー
 * @author Kengo-Nagase
 *
 */
public class UIAlertFactory {

	/**
	 * アラート生成（成功）
	 * @param msg
	 * @return 成功
	 */
	public static UIAlert success(String msg) {
		return create(UIAlertType.success, msg);
	}

	/**
	 * アラート生成（情報）
	 * @param msg
	 * @return 情報
	 */
	public static UIAlert info(String msg) {
		return create(UIAlertType.info, msg);
	}

	/**
	 * アラート生成（警告）
	 * @param msg
	 * @return 警告
	 */
	public static UIAlert warning(String msg) {
		return create(UIAlertType.warning, msg);
	}

	/**
	 * アラート生成（危険）
	 * @param msg
	 * @return 危険
	 */
	public static UIAlert danger(String msg) {
		return create(UIAlertType.danger, msg);
	}

	/**
	 * アラート生成
	 * @return
	 */
	public static UIAlert create() {
		return new UIAlert();
	}

	/**
	 * アラート生成
	 * @param type
	 * @param msg
	 * @return
	 */
	public static UIAlert create(UIAlertType type, String msg) {
		return new UIAlert(type, msg);
	}
}
