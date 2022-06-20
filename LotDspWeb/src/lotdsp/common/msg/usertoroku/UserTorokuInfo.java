package lotdsp.common.msg.usertoroku;

import java.math.BigDecimal;
import java.sql.Timestamp;

import nis.framework.dictionary.InputModel;
import nis.framework.dictionary.MaxLength;

import nis.framework.dictionary.Zenhankaku;
import nis.framework.dictionary.Zenhankaku.ZenhankakuElm;

@InputModel
public class UserTorokuInfo {

	@MaxLength(8)
	@Zenhankaku(ZenhankakuElm.HANKAKU)
	private String userId;// ユーザＩＤ

	@MaxLength(20)
	@Zenhankaku(ZenhankakuElm.HANKAKU)
	private String password;// 新しいパスワード

	@MaxLength(20)
	@Zenhankaku(ZenhankakuElm.HANKAKU)
	private String passwordConfirm;// 新しいパスワード（確認用）

	private String torokushaCd;// 登録者コード
	private Timestamp torokuTs;// 登録日時
	private String koshinshaCd;// 更新者コード
	private Timestamp koshinTs;// 更新日時
	private BigDecimal version;// バージョン
	
	private String errorCode;
	private String errorMessage;
	private boolean hasError;

	public UserTorokuInfo() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	public String getTorokushaCd() {
		return torokushaCd;
	}

	public void setTorokushaCd(String torokushaCd) {
		this.torokushaCd = torokushaCd;
	}
	public Timestamp getTorokuTs() {
		return torokuTs;
	}

	public void setTorokuTs(Timestamp torokuTs) {
		this.torokuTs = torokuTs;
	}
	public String getKoshinshaCd() {
		return koshinshaCd;
	}

	public void setKoshinshaCd(String koshinshaCd) {
		this.koshinshaCd = koshinshaCd;
	}
	public Timestamp getKoshinTs() {
		return koshinTs;
	}

	public void setKoshinTs(Timestamp koshinTs) {
		this.koshinTs = koshinTs;
	}
	public BigDecimal getVersion() {
		return version;
	}

	public void setVersion(BigDecimal version) {
		this.version = version;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public boolean isHasError() {
		return hasError;
	}

	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}

	@Override
	public String toString() {
		return org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString(this);
	}
}

