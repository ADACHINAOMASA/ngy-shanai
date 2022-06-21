package ngyshanai.common.msg.kaigiyoyaku;

import java.math.BigDecimal;
import java.util.List;

import nis.framework.dictionary.InputModel;

@InputModel
public class KaigishitsuInfo {

//	@DB("M_KAIGISHITSU.KAIGISHITSU_CD")
	private String kaigishitsuCd;
	
//	@DB("M_KAIGISHITSU.KAIGISHITSU_MEI")
	private String kaigishitsuMei;
	
//	@DB("M_KAIGISHITSU.CATEGORY_CD")
	private String categoryCd;
	
	private List<YoyakuInfo> yoyakuInfo;
	
	private BigDecimal version;

	public String getKaigishitsuCd() {
		return kaigishitsuCd;
	}

	public void setKaigishitsuCd(String kaigishitsuCd) {
		this.kaigishitsuCd = kaigishitsuCd;
	}

	public String getKaigishitsuMei() {
		return kaigishitsuMei;
	}

	public void setKaigishitsuMei(String kaigishitsuMei) {
		this.kaigishitsuMei = kaigishitsuMei;
	}

	public String getCategoryCd() {
		return categoryCd;
	}

	public void setCategoryCd(String categoryCd) {
		this.categoryCd = categoryCd;
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
