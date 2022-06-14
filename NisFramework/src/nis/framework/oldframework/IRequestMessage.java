package nis.framework.oldframework;

import java.math.BigDecimal;

/**
 * RequestMessageインターフェース
 */
public interface IRequestMessage extends IJobMessage {

	/**
	 * ユーザーIDを取得する。
	 * @return ユーザーID
	 */
	public String getUserId();

	/**
	 * ユーザーIDを設定する。
	 * @param userId ユーザーID
	 */
	public void setUserId(String userId);

	/**
	 * セッションIDを取得する。
	 * @return セッションID
	 */
	public String getSessionId();

	/**
	 * セッションIDを設定する。
	 * @param sessionId セッションID
	 */
	public void setSessionId(String sessionId);

	/**
	 * フロントIDを取得する。
	 * @return フロントID
	 */
	public String getFrontId();

	/**
	 * フロントIDを設定する。
	 * @param frontId フロントID
	 */
	public void setFrontId(String frontId);

	/**
	 * バージョンを取得する。
	 * @return バージョン
	 */
	public BigDecimal getVersion();

	/**
	 * バージョンを設定する。
	 * @param version バージョン
	 */
	public void setVersion(BigDecimal version);

}
