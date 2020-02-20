package lotdsp.domain.query.list;

import java.util.Map;

import lotdsp.common.AppConst;
import nis.framework.sql.NisQuery;

public class NonyusakiListKensakuQuery extends NisQuery<Map<String, Object>> {
	
	private String nonyusakiCd;
	private String nonyusakiMei;

	public NonyusakiListKensakuQuery(String nonyusakiCd,String nonyusakiMei) {
		this.nonyusakiCd = nonyusakiCd;
		this.nonyusakiMei = nonyusakiMei;
		
		this.setParameters();
	}

	private void setParameters() {
		addLikeParameter(" nonyusaki_cd LIKE ? ", nonyusakiCd, LIKE_MODE.STARTS_WITH);
		addLikeParameter(" nonyusaki_mei LIKE ? ", nonyusakiMei, LIKE_MODE.CONTAINS);
	}

	@Override
	public String getSQL() {
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT ");
		sql.append("   nonyusaki_cd ");
		sql.append("   , nonyusaki_mei  ");
		sql.append(" FROM ");
		sql.append("   nfmnonyusaki  ");
		sql.append(" WHERE ");
		sql.append("   nonyusaki_cd IS NOT NULL  ");
		sql.append(createParameterString(true));
		sql.append(" ORDER BY ");
		sql.append("   nonyusaki_cd ");
		db2FetchFirst(sql, AppConst.KENSAKU_KENSU_MAX);

		logger.info(getClass().getSimpleName() + "=" + sql.toString());
		return sql.toString();
	}

	@Override
	public Map<String, Object> record(Map<String, Object> record) {
		record.put("nonyusakiCd", record.get("NONYUSAKI_CD"));
		record.put("nonyusakiMei", record.get("NONYUSAKI_MEI"));
		return record;
	}
}
