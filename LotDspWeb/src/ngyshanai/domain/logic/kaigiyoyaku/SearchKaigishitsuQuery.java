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
		sql.append("   , k.ROOM_NAME ");
		sql.append("   , k.LOCATION  ");
		sql.append("   , k.ETC  ");
		sql.append("   , k.ORDER_ID  ");
		sql.append(" FROM ");
		sql.append("   M_KAIGISHITSU k  ");
		sql.append(" ORDER BY ");
		sql.append("   k.ORDER_ID ");

		logger.info(getClass().getSimpleName() + "=" + sql.toString());
		return sql.toString();
	}

	@Override
	public KaigishitsuInfo record(Map<String, Object> record) {
		KaigishitsuInfo info = new KaigishitsuInfo();
		
		info.setKaigishitsuCd(record.get("KAIGISHITSU_CD").toString());
		info.setRoomName(nullCheck(record.get("ROOM_NAME")));
		info.setLocation(nullCheck(record.get("LOCATION")));
		info.setEtc(nullCheck(record.get("ETC")));
		
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

