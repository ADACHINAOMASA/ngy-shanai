package lotdsp.common.msg.hososhiyosho;

import java.math.BigDecimal;

import nis.framework.dictionary.DefaultValue;
import nis.framework.dictionary.InputModel;
import nis.framework.dictionary.MaxLength;
import nis.framework.dictionary.OmojiKomoji;
import nis.framework.dictionary.OmojiKomoji.OmojiKomojiElm;
import nis.framework.dictionary.Zenhankaku;
import nis.framework.dictionary.Zenhankaku.ZenhankakuElm;

@InputModel
public class HosoShiyoshoHeaderInfo {

	@MaxLength(30)
	@Zenhankaku(ZenhankakuElm.HANKAKU)
	@OmojiKomoji(OmojiKomojiElm.OMOJI)
	//@KyokaMoji("\\w")
	private String pkgSpecNo;

	@MaxLength(3)
	@DefaultValue("1")
	private BigDecimal scalingFactor;
	
	private String viewWidth;
	
	private String viewHeight;
	
	private boolean isGenba;
	
	
	public String getPkgSpecNo() {
		return pkgSpecNo;
	}

	public void setPkgSpecNo(String pkgSpecNo) {
		this.pkgSpecNo = pkgSpecNo;
	}

	public BigDecimal getScalingFactor() {
		return scalingFactor;
	}

	public void setScalingFactor(BigDecimal scalingFactor) {
		this.scalingFactor = scalingFactor;
	}

	public String getViewWidth() {
		return viewWidth;
	}

	public void setViewWidth(String viewWidth) {
		this.viewWidth = viewWidth;
	}

	public String getViewHeight() {
		return viewHeight;
	}

	public void setViewHeight(String viewHeight) {
		this.viewHeight = viewHeight;
	}

	public boolean isGenba() {
		return isGenba;
	}

	public void setGenba(boolean isGenba) {
		this.isGenba = isGenba;
	}

	
}
