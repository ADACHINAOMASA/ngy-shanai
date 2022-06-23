package ngyshanai.common.msg.kaigiyoyaku;

import java.math.BigDecimal;
import java.util.List;

import nis.framework.dictionary.InputModel;

@InputModel
public class KaigishitsuInfo {

	private String kaigishitsuCd;
	
	private String roomName;
	
	private String location;
	
	private String etc;
	
	private BigDecimal orderId;
	
	private List<YoyakuInfo> yoyakuInfo;
	
	private BigDecimal version;

	public String getKaigishitsuCd() {
		return kaigishitsuCd;
	}

	public void setKaigishitsuCd(String kaigishitsuCd) {
		this.kaigishitsuCd = kaigishitsuCd;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEtc() {
		return etc;
	}

	public void setEtc(String etc) {
		this.etc = etc;
	}

	public BigDecimal getOrderId() {
		return orderId;
	}

	public void setOrderId(BigDecimal orderId) {
		this.orderId = orderId;
	}

	public List<YoyakuInfo> getYoyakuInfo() {
		return yoyakuInfo;
	}

	public void setYoyakuInfo(List<YoyakuInfo> yoyakuInfo) {
		this.yoyakuInfo = yoyakuInfo;
	}

	public BigDecimal getVersion() {
		return version;
	}

	public void setVersion(BigDecimal version) {
		this.version = version;
	}
}
