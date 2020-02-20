package nis.framework.web.alert;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * <p>
 * レスポンス用アラート一覧<br>
 * アラートに関する制御は大体ここ経由で行う<br>
 * アラートがレスポンスとしてJson化される際にルートになるクラス
 * </p>
 * @author Kengo-Nagase
 *
 */
public class UIAlerts {

	/**
	 * アラート一覧
	 */
	private List<UIAlert> alerts = new ArrayList<UIAlert>();

	/**
	 * <p>
	 * 成功の追加<br>
	 * 成功：処理の正常終了アナウンス等、ユーザーのアクションに対する肯定的なメッセージ<br>
	 * 色はデフォルトでは緑
	 * </p>
	 * @param msg メッセージ本文
	 */
	public void addSuccess(String msg) {
		alerts.add(UIAlertFactory.success(msg));
	}

	/**
	 * <p>
	 * 情報の追加<br>
	 * 情報：ユーザーのアクションに対するヒントや付随情報、状況、説明等のメッセージ<br>
	 * 色はデフォルトでは水色
	 * </p>
	 * @param msg メッセージ本文
	 */
	public void addInfo(String msg) {
		alerts.add(UIAlertFactory.info(msg));
	}

	/**
	 * <p>
	 * 警告の追加<br>
	 * 警告：ユーザーのアクションに対する事前、事後の確認のメッセージ<br>
	 * 色はデフォルトでは黄色
	 * </p>
	 * @param msg メッセージ本文
	 */
	public void addWarning(String msg) {
		alerts.add(UIAlertFactory.warning(msg));
	}

	/**
	 * <p>
	 * 危険の追加<br>
	 * 危険：ユーザーのアクションが何らかの要因によって失敗した<br>
	 *       あるいは何かが正常な状態でない場合のメッセージ<br>
	 * 色はデフォルトでは赤
	 * </p>
	 * @param msg メッセージ本文
	 */
	public void addDanger(String msg) {
		alerts.add(UIAlertFactory.danger(msg));
	}

	/**
	 * 成功を持っているか
	 * @return true = 持っている
	 */
	public boolean hasSuccess() {
		return hasType(UIAlertType.success);
	}

	/**
	 * 情報を持っているか
	 * @return true = 持っている
	 */
	public boolean hasInfo() {
		return hasType(UIAlertType.info);
	}

	/**
	 * 警告を持っているか
	 * @return true = 持っている
	 */
	public boolean hasWarning() {
		return hasType(UIAlertType.warning);
	}

	/**
	 * 危険を持っているか
	 * @return true = 持っている
	 */
	public boolean hasDanger() {
		return hasType(UIAlertType.danger);
	}

	/**
	 * 指定したタイプのアラートを持っているか
	 * @param type
	 * @return true = 持っている
	 */
	private boolean hasType(UIAlertType type) {
		for (UIAlert alert : alerts) {
			return alert.getType() == type;
		}
		return false;
	}

	/**
	 * 成功をクリア
	 */
	public void clearSuccess() {
		clear(UIAlertType.success);
	}

	/**
	 * 情報をクリア
	 */
	public void clearInfo() {
		clear(UIAlertType.info);
	}

	/**
	 * 警告をクリア
	 */
	public void clearWarning() {
		clear(UIAlertType.warning);
	}

	/**
	 * 危険をクリア
	 */
	public void clearDanger() {
		clear(UIAlertType.danger);
	}

	/**
	 * アラートのクリア
	 * @param type
	 */
	public void clear(UIAlertType type) {
		List<UIAlert> newAlerts = new ArrayList<UIAlert>();
		for (UIAlert alert : alerts) {
			if (alert.getType() != type) {
				newAlerts.add(alert);
			}
		}
		this.alerts = newAlerts;
	}

	/**
	 * アラート一覧の取得
	 * @return
	 */
	public List<UIAlert> getAlerts() {
		return alerts;
	}

	/**
	 * アラートのクリア
	 */
	public void clear() {
		alerts.clear();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
