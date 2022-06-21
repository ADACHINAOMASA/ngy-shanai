package ngyshanai.domain.query.list;

import java.util.Map;

import ngyshanai.common.AppConst;
import nis.framework.sql.NisQuery;

public class JuchuNoListFromSeisakuShijiKensakuQuery extends NisQuery<Map<String, Object>> {
	private String juchuNo;
	private String juchuNoHansu;

	public JuchuNoListFromSeisakuShijiKensakuQuery(String juchuNo,String juchuNoHansu) {
		this.juchuNo = juchuNo;
		this.juchuNoHansu = juchuNoHansu;
		
		this.setParameters();
	}

	private void setParameters() {
		addLikeParameter(" juchu_msi.juchu_no LIKE ? ", juchuNo, LIKE_MODE.CONTAINS);
		addParameter(" juchu_msi.juchu_kono = ? ", juchuNoHansu);
	}

	@Override
	public String getSQL() {
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT ");
		sql.append("   juchu_msi.juchu_no ");
		sql.append("   ,juchu_msi.juchu_kono ");
		sql.append("   ,max(juchu_msi.hinmei) as hinmei  ");
		sql.append(" FROM ");
		sql.append("   natjuchumsi juchu_msi  ");
		sql.append("   LEFT OUTER JOIN natjuchukhn juchu_khn  ");
		sql.append("     ON juchu_msi.juchu_no = juchu_khn.juchu_no  ");
		sql.append("   INNER JOIN NATSEISAKUSHIJIKHN seisaku");
		sql.append("     ON juchu_msi.juchu_no = seisaku.juchu_no  ");
		sql.append("     AND juchu_msi.juchu_kono = seisaku.juchu_kono  ");
		sql.append(" where ");
		sql.append("   juchu_msi.juchu_no IS NOT NULL  ");
		sql.append(createParameterString(true));
		sql.append(" group by ");
		sql.append(" juchu_msi.juchu_no, ");
		sql.append(" juchu_msi.juchu_kono ");
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
