package ngyshanai.entity.master.yoyaku;

import java.math.BigDecimal;
import java.util.Date;

public interface YoyakuTableUpdater {

	public String getKaigishitsuCd();
	public Date getYoyakuDate();
	public String getYoyakuBlockStart();
	public String getYoyakuBlockEnd();
	public String getYoyakushaCd();
	public String getBiko();
	public String getYoyakushaName();
	public String getTel();
	public String getImportance();
	public String getIsreserved();
	public String getMaishuYoyakuId();
	public BigDecimal getVersion();
	public String getTorokushaCd();
	public Date getTorokuTs();
	public String getKoshinshaCd();
	public Date getKoshinshaTs();
}

