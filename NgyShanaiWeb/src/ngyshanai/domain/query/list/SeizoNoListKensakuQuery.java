package ngyshanai.domain.query.list;

import java.util.Map;

import ngyshanai.common.AppConst;
import nis.framework.sql.NisQuery;

public class SeizoNoListKensakuQuery extends NisQuery<Map<String, Object>> {
	private String seizoNo;

	public SeizoNoListKensakuQuery(String seizoNo) {
		this.seizoNo = seizoNo;
		this.setParameters();
	}

	private void setParameters() {
		addLikeParameter("seisaku_shiji_khn.seizo_no LIKE ? ", seizoNo, LIKE_MODE.STARTS_WITH);
	}

	@Override
	public String getSQL() {
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT ");
		sql.append("   seisaku_shiji_khn.seizo_no ");
		sql.append("   , seisaku_shiji_khn.hinmei ");
		sql.append("   , juchu_khn.mitsumori_no ");
		sql.append("   , juchu_khn.mitsumori_no_hansu  ");
		sql.append(" FROM ");
		sql.append("   natseisakushijikhn seisaku_shiji_khn  ");
		sql.append("   LEFT OUTER JOIN natjuchukhn juchu_khn  ");
		sql.append("     ON seisaku_shiji_khn.juchu_no = juchu_khn.juchu_no  ");
		sql.append(" WHERE ");
		sql.append("   coalesce(juchu_khn.mitsumori_no, '') <> ''  ");
		sql.append("   AND coalesce(juchu_khn.mitsumori_no_hansu, '') <> ''  ");
		sql.append(createParameterString(true));
		
		sql.append(" ORDER BY ");
		sql.append("   seisaku_shiji_khn.seizo_no ");
		
		db2FetchFirst(sql, AppConst.KENSAKU_KENSU_MAX);

		logger.info(getClass().getSimpleName() + "=" + sql.toString());
		return sql.toString();
	}

	@Override
	public Map<String, Object> record(Map<String, Object> record) {
		record.put("seizoNo", record.get("SEIZO_NO"));
		record.put("hinmei", record.get("HINMEI"));
		return record;
	}
}
