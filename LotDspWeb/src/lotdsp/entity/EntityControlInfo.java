package lotdsp.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public interface EntityControlInfo {

	BigDecimal getVersion();

	void setVersion(BigDecimal version);

	String getTorokushaCd();

	void setTorokushaCd(String torokushaCd);

	Timestamp getTorokuTs();

	void setTorokuTs(Timestamp torokuTs);

	String getKoshinshaCd();

	void setKoshinshaCd(String koshinshaCd);

	Timestamp getKoshinTs();

	void setKoshinTs(Timestamp koshinTs);

}
