package ngyshanai.entity.master.user;

import java.math.BigDecimal;
import java.sql.Timestamp;

import ngyshanai.common.msg.user.UserInfo;
import ngyshanai.entity.oldframework.UpdateInfo;

public class MUserUpdaterImpl implements MUserUpdater{
	
	private UserInfo inMsg;
	
	@SuppressWarnings("unused")
	private UpdateInfo updaterInfo;
	
	public MUserUpdaterImpl(UserInfo inMsg, UpdateInfo updateInfo) {
		this.inMsg = inMsg;
		this.updaterInfo = updateInfo;
	}
	
	@Override
	public String getUserMei() {
		return inMsg.getUserMei();
	}
	
	@Override
	public String getTel() {
		return inMsg.getTel();
	}
	
	@Override
	public String getMailAddress() {
		return inMsg.getMailAddress();
	}
	
	@Override
	public String getPassword() {
		return inMsg.getPassword();
	}
	
	@Override
	public BigDecimal getVersion() {
		return null;
	}

	@Override
	public String getTorokushaCd() {
		return null;
	}

	@Override
	public Timestamp getTorokuTs() {
		return null;
	}

	@Override
	public String getKoshinshaCd() {
		return null;
	}

	@Override
	public Timestamp getKoshinTs() {
		return null;
	}
}
