package ngyshanai.common.msg.passwordhenko;

import java.math.BigDecimal;
import java.sql.Timestamp;

import nis.framework.dictionary.InputModel;
import nis.framework.dictionary.MaxLength;

import nis.framework.dictionary.Zenhankaku;
import nis.framework.dictionary.Zenhankaku.ZenhankakuElm;

@InputModel
public class PasswordHenkoInfo {

	@MaxLength(10)
	@Zenhankaku(ZenhankakuElm.HANKAKU)
	private String userId;// ユーザＩＤ

	@MaxLength(20)
	@Zenhankaku(ZenhankakuElm.HANKAKU)
	private String password;// 現在のパスワード

	@MaxLength(20)
	@Zenhankaku(ZenhankakuElm.HANKAKU)
	private String newPassword;// 新しいパスワード

	@MaxLength(20)
	@Zenhankaku(ZenhankakuElm.HANKAKU)
	private String newPasswordConfirm;// 新しいパスワード（確認用）

	private String torokushaCd;// 登録者コード
	private Timestamp torokuYmdhms;// 登録日時
	private String koshinshaCd;// 更新者コード
	private Timestamp koshinYmdhms;// 更新日時
	private BigDecimal version;// バージョン
	
	private String errorCode;
	private String errorMessage;
	private boolean hasError;

	public PasswordHenkoInfo() {
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
	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getNewPasswordConfirm() {
		return newPasswordConfirm;
	}

	public void setNewPasswordConfirm(String newPasswordConfirm) {
		this.newPasswordConfirm = newPasswordConfirm;
	}
	public String getTorokushaCd() {
		return torokushaCd;
	}

	public void setTorokushaCd(String torokushaCd) {
		this.torokushaCd = torokushaCd;
	}
	public Timestamp getTorokuYmdhms() {
		return torokuYmdhms;
	}

	public void setTorokuYmdhms(Timestamp torokuYmdhms) {
		this.torokuYmdhms = torokuYmdhms;
	}
	public String getKoshinshaCd() {
		return koshinshaCd;
	}

	public void setKoshinshaCd(String koshinshaCd) {
		this.koshinshaCd = koshinshaCd;
	}
	public Timestamp getKoshinYmdhms() {
		return koshinYmdhms;
	}

	public void setKoshinYmdhms(Timestamp koshinYmdhms) {
		this.koshinYmdhms = koshinYmdhms;
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

