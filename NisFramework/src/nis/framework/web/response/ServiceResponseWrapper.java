package nis.framework.web.response;

import nis.framework.web.alert.UIAlerts;

/**
 * <p>
 * サービスからのレスポンスのラッパー<br>
 * 基本的に全てのレスポンスはこのラッパーで包まれる<br>
 * 元々のレスポンスに手を加える事なくアラートやステータスを付与するのが目的
 * </p>
 * @author Kengo-Nagase
 *
 */
public class ServiceResponseWrapper {

	/**
	 * ステータス
	 */
	private ResponseStatus status = ResponseStatus.SUCCESS;

	/**
	 * アラート
	 */
	private UIAlerts alerts = new UIAlerts();

	/**
	 * レスポンス用ラッパーである事の判定に用いる
	 */
	private final boolean responseObj = true;

	/**
	 * バイトデータか
	 */
	private boolean byteData = false;

	/**
	 * レスポンス本体
	 */
	private Object entity;

	public ServiceResponseWrapper(Object entity) {
		this.entity = entity;
	}

	/**
	 * statusを取得します。
	 * @return status
	 */
	public ResponseStatus getStatus() {
	    return status;
	}

	/**
	 * statusを設定します。
	 * @param status status
	 */
	public void setStatus(ResponseStatus status) {
	    this.status = status;
	}

	/**
	 * alertsを取得します。
	 * @return alerts
	 */
	public UIAlerts getAlerts() {
	    return alerts;
	}

	/**
	 * alertsを設定します。
	 * @param alerts alerts
	 */
	public void setAlerts(UIAlerts alerts) {
	    this.alerts = alerts;
	}

	/**
	 * responseObjを取得します。
	 * @return responseObj
	 */
	public boolean isResponseObj() {
	    return responseObj;
	}

	/**
	 * byteDataを取得します。
	 * @return byteData
	 */
	public boolean isByteData() {
	    return byteData;
	}

	/**
	 * byteDataを設定します。
	 * @param byteData byteData
	 */
	public void setByteData(boolean byteData) {
	    this.byteData = byteData;
	}

	/**
	 * entityを取得します。
	 * @return entity
	 */
	public Object getEntity() {
	    return entity;
	}

	/**
	 * entityを設定します。
	 * @param entity entity
	 */
	public void setEntity(Object entity) {
	    this.entity = entity;
	}

}
