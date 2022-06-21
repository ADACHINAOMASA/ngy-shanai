package ngyshanai.domain.logic.kaigiyoyaku;

import java.util.Map;
import java.util.Objects;

import ngyshanai.common.msg.kaigiyoyaku.KaigishitsuInfo;
import nis.framework.sql.NisQuery;

public class SearchKaigishitsuQuery extends NisQuery<KaigishitsuInfo> {

	public SearchKaigishitsuQuery() {
		this.setParameters();
	}

	private void setParameters() {
	}

	@Override
	public String getSQL() {
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT ");
		sql.append("   k.KAIGISHITSU_CD ");
		sql.append("   , k.KAIGISHITSU_MEI ");
		sql.append("   , k.CATEGORY_CD  ");
		sql.append(" FROM ");
		sql.append("   M_KAIGISHITSU k  ");
		sql.append(" ORDER BY ");
		sql.append("   k.KAIGISHITSU_CD ");

		logger.info(getClass().getSimpleName() + "=" + sql.toString());
		return sql.toString();
	}

	@Override
	public KaigishitsuInfo record(Map<String, Object> record) {
		KaigishitsuInfo info = new KaigishitsuInfo();
		
		info.setKaigishitsuCd(nullCheck(record.get("KAIGISHITSU_CD")));
		info.setKaigishitsuMei(nullCheck(record.get("KAIGISHITSU_MEI")));
		info.setCategoryCd(nullCheck(record.get("CATEGORY_CD")));
		
		return info;
	}
	
	private String nullCheck(Object obj) {
		if (Objects.nonNull(obj)) {
			return obj.toString();
		}
		else {
			return "";
		}
	}
}

