package lotdsp.entity.master.yoyaku;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import lotdsp.common.msg.kaigiyoyaku.YoyakuInfo;
import lotdsp.entity.oldframework.UpdateInfo;

public class YoyakuTableUpdaterImpl implements YoyakuTableUpdater {
	
	private YoyakuInfo inMsg;
	
	@SuppressWarnings("unused")
	private UpdateInfo updaterInfo;
	
	public YoyakuTableUpdaterImpl(YoyakuInfo inMsg, UpdateInfo updaterInfo) {
		this.inMsg = inMsg;
		this.updaterInfo = updaterInfo;
	}
	
	@Override
	public String getKaigishitsuCd() {
		return inMsg.getKaigishitsuCd();
	}
	
	@Override
	public Date getYoyakuDate() {
		return inMsg.getYoyakuDate();
	}
	
	@Override
	public String getYoyakuBlockStart() {
		return inMsg.getYoyakuBlockStart();
	}
	
	@Override
	public String getYoyakuBlockEnd() {
		return inMsg.getYoyakuBlockEnd();
	}
	
	@Override
	public String getYoyakushaCd() {
		return inMsg.getYoyakushaCd();
	}
	
	@Override
	public String getBiko() {
		return inMsg.getBiko();
	}
	
	@Override
	public String getYoyakushaName() {
		return inMsg.getYoyakushaName();
	}
	
	@Override
	public String getTel() {
		return inMsg.getTel();
	}
	
	@Override
	public String getImportance() {
		return inMsg.getImportance();
	}
	
	@Override
	public String getIsreserved() {
		return inMsg.getIsreserved();
	}
	
	@Override
	public String getMaishuYoyakuId() {
		return inMsg.getMaishuYoyakuId();
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
	public Timestamp getKoshinshaTs() {
		return null;
	}
}
