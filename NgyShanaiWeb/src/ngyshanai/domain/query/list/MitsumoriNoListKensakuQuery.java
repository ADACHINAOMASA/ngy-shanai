package ngyshanai.domain.query.list;

import java.util.Map;

import ngyshanai.common.AppConst;
import nis.framework.sql.NisQuery;

public class MitsumoriNoListKensakuQuery extends NisQuery<Map<String, Object>> {
	private String mitsumoriNo;
	private String mitsumoriNoHansu;

	public MitsumoriNoListKensakuQuery(String mitsumoriNo, String mitsumoriNoHansu) {
		this.mitsumoriNo = mitsumoriNo;
		this.mitsumoriNoHansu = mitsumoriNoHansu;
		this.setParameters();
	}

	private void setParameters() {
		addLikeParameter(" mitsumori_no LIKE ? ", mitsumoriNo, LIKE_MODE.STARTS_WITH);
		addParameter("　mitsumori_no_hansu = ? ", mitsumoriNoHansu);
	}

	@Override
	public String getSQL() {
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT ");
		sql.append("   mitsumori_no  ");
		sql.append("   , mitsumori_no_hansu  ");
		sql.append("   , kenmei ");
		sql.append(" FROM ");
		sql.append("   ppm_t_mitsumori_khn  ");
		sql.append(" WHERE ");
		sql.append("   mitsumori_no IS NOT NULL  ");
		sql.append(createParameterString(true));
		sql.append(" ORDER BY ");
		sql.append("   mitsumori_no ");
		sql.append("   , mitsumori_no_hansu  ");
		db2FetchFirst(sql, AppConst.KENSAKU_KENSU_MAX);

		logger.info(getClass().getSimpleName() + "=" + sql.toString());
		return sql.toString();
	}

	@Override
	public Map<String, Object> record(Map<String, Object> record) {
		record.put("mitsumoriNoWithHansu", record.get("MITSUMORI_NO") + "-" + record.get("MITSUMORI_NO_HANSU"));
		record.put("mitsumoriNo", record.get("MITSUMORI_NO"));
		record.put("mitsumoriNoHansu", record.get("MITSUMORI_NO_HANSU"));
		record.put("kenmei", record.get("KENMEI"));
		return record;
	}
}
