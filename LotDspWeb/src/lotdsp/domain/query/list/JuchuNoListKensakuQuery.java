package lotdsp.domain.query.list;

import java.util.Map;

import lotdsp.common.AppConst;
import nis.framework.sql.NisQuery;

public class JuchuNoListKensakuQuery extends NisQuery<Map<String, Object>> {
	private String juchuNo;
	private String juchuNoHansu;
	protected String mitsumoriNo;
	protected String mitsumoriNoHansu;

	public JuchuNoListKensakuQuery(String juchuNo,String juchuNoHansu,String mitsumoriNo, String mitsumoriNoHansu) {
		this.juchuNo = juchuNo;
		this.juchuNoHansu = juchuNoHansu;
		this.mitsumoriNo = mitsumoriNo;
		this.mitsumoriNoHansu = mitsumoriNoHansu;
		
		this.setParameters();
	}

	private void setParameters() {
		addLikeParameter(" juchu_msi.juchu_no LIKE ? ", juchuNo, LIKE_MODE.CONTAINS);
		addParameter("　juchu_msi.juchu_kono = ? ", juchuNoHansu);
		/*
		addLikeParameter(" juchu_khn.mitsumori_no LIKE ? ", mitsumoriNo, LIKE_MODE.STARTS_WITH);
		addParameter("　juchu_khn.mitsumori_no_hansu = ? ", mitsumoriNoHansu);
		*/
	}

	@Override
	public String getSQL() {
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT ");
		sql.append("   juchu_msi.juchu_no ");
		sql.append("   ,juchu_msi.juchu_kono ");
		sql.append("   ,juchu_msi.hinmei  ");
		sql.append(" FROM ");
		sql.append("   natjuchumsi juchu_msi  ");
		sql.append("   LEFT OUTER JOIN natjuchukhn juchu_khn  ");
		sql.append("     ON juchu_msi.juchu_no = juchu_khn.juchu_no  ");
		sql.append(" where ");
		sql.append("   juchu_msi.juchu_no IS NOT NULL  ");
		sql.append(createParameterString(true));
		sql.append(" 	and coalesce(juchu_khn.mitsumori_no,'')<>'' ");
		sql.append(" 	and coalesce(juchu_khn.mitsumori_no_hansu,'')<>'' ");
		sql.append(" ORDER BY ");
		sql.append("   juchu_msi.juchu_no ");
		sql.append("   , juchu_msi.juchu_kono ");
		db2FetchFirst(sql, AppConst.KENSAKU_KENSU_MAX);

		logger.info(getClass().getSimpleName() + "=" + sql.toString());
		return sql.toString();
	}

	@Override
	public Map<String, Object> record(Map<String, Object> record) {
		record.put("juchuNoWithKoNo", record.get("JUCHU_NO") + "-" + record.get("JUCHU_KONO"));
		record.put("juchuNo", record.get("JUCHU_NO"));
		record.put("juchuKoNo", record.get("JUCHU_KONO"));
		record.put("hinmei", record.get("HINMEI"));
		return record;
	}
}
