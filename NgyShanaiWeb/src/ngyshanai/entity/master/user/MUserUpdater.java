package ngyshanai.entity.master.user;

import java.math.BigDecimal;
import java.util.Date;

public interface MUserUpdater {

	public String getUserName();
	public String getTel();
	public String getMailAddress();
	public String getPassword();
	public BigDecimal getVersion();
	public String getTorokushaCd();
	public Date getTorokuTs();
	public String getKoshinshaCd();
	public Date getKoshinTs();

}

