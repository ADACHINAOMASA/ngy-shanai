package lotdsp.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public interface EntityControlInfo {

	// String getTorokushaCd();
	// void setTorokushaCd(String torokushaCd);
	// Timestamp getTorokuYmdhms();
	// void setTorokuYmdhms(Timestamp torokuYmdhms);
	// String getKoshinshaCd();
	// void setKoshinshaCd(String koshinshaCd);
	// Timestamp getKoshinYmdhms();
	// void setKoshinYmdhms(Timestamp koshinYmdhms);
	// BigDecimal getVersion();
	// void setVersion(BigDecimal version);

	BigDecimal getVersion();

	void setVersion(BigDecimal version);

	String getTorokuUserid();

	void setTorokuUserid(String torokuUserid);

	Timestamp getTorokuYmdhms();

	void setTorokuYmdhms(Timestamp torokuYmdhms);

	String getTorokuFrontid();

	void setTorokuFrontid(String torokuFrontid);

	String getKoshinUserid();

	void setKoshinUserid(String koshinUserid);

	Timestamp getKoshinYmdhms();

	void setKoshinYmdhms(Timestamp koshinYmdhms);

	String getKoshinFrontid();

	void setKoshinFrontid(String koshinFrontid);

}
