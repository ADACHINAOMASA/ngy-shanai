package lotdsp.entity.master.user;

import java.math.BigDecimal;
import java.util.Date;

public interface MUserUpdater {

	public String getKaishaCd();
	public String getUserCd();
	public String getUserMei();
	public String getTel();
	public String getKanrishaKbn();
	public String getPassword();
	public BigDecimal getVersion();
	public String getTorokushaCd();
	public Date getTorokuTs();
	public String getKoshinshaCd();
	public Date getKoshinTs();

}

