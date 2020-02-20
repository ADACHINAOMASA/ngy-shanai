package lotdsp.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public abstract class AbstractEntity implements EntityControlInfo {

	//private static final String DELETE_FLAG_OFF = "0";
	//private static final String DELETE_FLAG_ON = "1";

	public void invalid(String userId, Timestamp ts) {
		//defleteFlag(DELETE_FLAG);
		//setUpdateInfo(userId, ts);
	}

	public void valid(String userId, Timestamp ts) {
		//defleteFlag(VALID);
		//setUpdateInfo(userId, ts);
	}

	public boolean isValid() {
		//return VALID.equals(defleteFlag());
		return true;
	}

	public boolean checkVersion(BigDecimal version) {
		if (version == null) {
			return false;
		}
		return version.compareTo(version()) == 0;
	}

	public void setUpdateInfo(String userId, Timestamp ts) {
		updatedBy(userId);
		updatedTs(ts);
		incrementVersion();
	}

	public void setCreateInfo(String userId, Timestamp ts) {
		createdBy(userId);
		createdTs(ts);
		//defleteFlag(VALID);
		version(BigDecimal.ONE);
	}

	public void setConrollInfo(String userId, Timestamp ts) {
		if (createdBy() == null) {
			setCreateInfo(userId, ts);
		} else {
			setUpdateInfo(userId, ts);
		}
	}

	public void incrementVersion() {
		BigDecimal currentVersion = version();
		if (currentVersion == null) {
			currentVersion = BigDecimal.ZERO;
		}
		version((currentVersion.compareTo(BigDecimal.valueOf(99999)) >= 0) ? BigDecimal.ONE: currentVersion.add(BigDecimal.ONE));
	}

	// エンティティのFooterをラップするメソッド
	// EntityControlInfoに入っているメソッドをラップする
	// --------------------------------------------------------------------------------------------------------------↓
	private String createdBy() {
		return getTorokuUserid();//
	}

	private void createdBy(String createdBy) {
		setTorokuUserid(createdBy);
	}

	private void createdTs(Timestamp createdTs) {
		setTorokuYmdhms(createdTs);
	}

	private void updatedBy(String updatedBy) {
		setKoshinUserid(updatedBy);
	}

	private void updatedTs(Timestamp updatedTs) {
		setKoshinYmdhms(updatedTs);
	}

//	private String defleteFlag() {
//		//return getSakujoFlg();
//		return null;
//	}
//
//	private void defleteFlag(String defleteFlg) {
//		//setSakujoFlg(defleteFlg);
//	}

	private BigDecimal version() {
		return getVersion();
	}

	private void version(BigDecimal version) {
		setVersion(version);
	}

	// --------------------------------------------------------------------------------------------------------------↑
}
