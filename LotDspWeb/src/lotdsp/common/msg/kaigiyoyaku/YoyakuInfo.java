package lotdsp.common.msg.kaigiyoyaku;

import java.math.BigDecimal;
import java.util.Date;

import nis.framework.dictionary.InputModel;
import nis.framework.dictionary.MaxLength;
import nis.framework.dictionary.Required;

@InputModel
public class YoyakuInfo {

//	@DB("YOYAKU_TABLE.KAIGISHITSU_CD")
	private String kaigishitsuCd;
	
//	@DB("YOYAKU_TABLE.YOYAKU_DATE")
	private Date yoyakuDate;
	
	@Required
//	@DB("YOYAKU_TABLE.YOYAKU_BLOCK_START")
	private String yoyakuBlockStart;
	
	@Required
//	@DB("YOYAKU_TABLE.YOYAKU_BLOCK_END")
	private String yoyakuBlockEnd;
	
//	@DB("YOYAKU_TABLE.YOYAKUSHA_CD")
	private String yoyakushaCd;
	
	@MaxLength(100)
//	@DB("YOYAKU_TABLE.BIKO")
	private String biko;
	
	@Required
	@MaxLength(20)
//	@DB("YOYAKU_TABLE.YOYAKUSHA_NAME")
	private String yoyakushaName;
	
	@Required
	@MaxLength(15)
//	@DB("YOYAKU_TABLE.TEL")
	private String tel;
	
	@Required
//	@DB("YOYAKU_TABLE.IMPORTANCE")
	private String importance;
	
//	@DB("YOYAKU_TABLE.ISRESERVED")
	private String isreserved;
	
	private String maishuYoyakuId;
	
	private BigDecimal version;

	public String getKaigishitsuCd() {
		return kaigishitsuCd;
	}

	public void setKaigishitsuCd(String kaigishitsuCd) {
		this.kaigishitsuCd = kaigishitsuCd;
	}

	public Date getYoyakuDate() {
		return yoyakuDate;
	}

	public void setYoyakuDate(Date yoyakuDate) {
		this.yoyakuDate = yoyakuDate;
	}

	public String getYoyakuBlockStart() {
		return yoyakuBlockStart;
	}

	public void setYoyakuBlockStart(String yoyakuBlockStart) {
		this.yoyakuBlockStart = yoyakuBlockStart;
	}

	public String getYoyakuBlockEnd() {
		return yoyakuBlockEnd;
	}

	public void setYoyakuBlockEnd(String yoyakuBlockEnd) {
		this.yoyakuBlockEnd = yoyakuBlockEnd;
	}

	public String getYoyakushaCd() {
		return yoyakushaCd;
	}

	public void setYoyakushaCd(String yoyakushaCd) {
		this.yoyakushaCd = yoyakushaCd;
	}

	public String getBiko() {
		return biko;
	}

	public void setBiko(String biko) {
		this.biko = biko;
	}

	public String getYoyakushaName() {
		return yoyakushaName;
	}

	public void setYoyakushaName(String yoyakushaName) {
		this.yoyakushaName = yoyakushaName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getImportance() {
		return importance;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}

	public String getIsreserved() {
		return isreserved;
	}

	public void setIsreserved(String isreserved) {
		this.isreserved = isreserved;
	}

	public String getMaishuYoyakuId() {
		return maishuYoyakuId;
	}

	public void setMaishuYoyakuId(String maishuYoyakuId) {
		this.maishuYoyakuId = maishuYoyakuId;
	}

	public BigDecimal getVersion() {
		return version;
	}

	public void setVersion(BigDecimal version) {
		this.version = version;
	}
	
}
