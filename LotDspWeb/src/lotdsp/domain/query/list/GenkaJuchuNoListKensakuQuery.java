package lotdsp.domain.query.list;

import java.util.Map;

import lotdsp.common.AppConst;
import nis.framework.sql.NisQuery;

public class GenkaJuchuNoListKensakuQuery extends NisQuery<Map<String, Object>> {
	private String juchuNo;
	private String juchuNoHansu;
	protected String mitsumoriNo;
	protected String mitsumoriNoHansu;

	public GenkaJuchuNoListKensakuQuery(String juchuNo,String juchuNoHansu,String mitsumoriNo, String mitsumoriNoHansu) {
		this.juchuNo = juchuNo;
		this.juchuNoHansu = juchuNoHansu;
		this.mitsumoriNo = mitsumoriNo;
		this.mitsumoriNoHansu = mitsumoriNoHansu;
		
		this.setParameters();
	}

	private void setParameters() {
		addLikeParameter(" j_msi.juchu_no LIKE ? ", juchuNo, LIKE_MODE.CONTAINS);
		addParameter("　j_msi.juchu_kono = ? ", juchuNoHansu);
	}

	@Override
	public String getSQL() {
		StringBuilder sb = new StringBuilder();
		sb.append("select ");
		sb.append("  j_msi.juchu_no ");
		sb.append("  , j_msi.juchu_kono ");
		sb.append("  , j_msi.hinmei ");
		sb.append("from ");
		sb.append("  ( ");
		sb.append("    select ");
		sb.append("      juchu_msi.juchu_no ");
		sb.append("      , juchu_msi.juchu_kono ");
		sb.append("    from ");
		sb.append("      natjuchumsi juchu_msi ");
		sb.append("      left outer join natjuchukhn juchu_khn ");
		sb.append("        on juchu_msi.juchu_no = juchu_khn.juchu_no ");
		sb.append("      left outer join ppm_t_mitsumori_genka_renkei renkei ");
		sb.append("        on renkei.mitsumori_no = juchu_khn.mitsumori_no ");
		sb.append("        and renkei.mitsumori_no_hansu = juchu_khn.mitsumori_no_hansu ");
		sb.append("    where ");
		sb.append("      juchu_msi.juchu_no is not null ");
		sb.append("      and coalesce(juchu_khn.mitsumori_no, '') <> '' ");
		sb.append("      and coalesce(juchu_khn.mitsumori_no_hansu, '') <> '' ");
		sb.append("      and renkei.genka_no is not null ");
		sb.append("    group by ");
		sb.append("      juchu_msi.juchu_no ");
		sb.append("      , juchu_msi.juchu_kono ");
		sb.append("  ) v_juchu ");
		sb.append("  inner join natjuchumsi j_msi ");
		sb.append("    on v_juchu.juchu_no = j_msi.juchu_no ");
		sb.append("    and v_juchu.juchu_kono = j_msi.juchu_kono ");
		sb.append(" where ");
		sb.append("   j_msi.juchu_no IS NOT NULL  ");
		sb.append(createParameterString(true));
		sb.append("order by ");
		sb.append("  coalesce(j_msi.koshin_ymdhms, j_msi.toroku_ymdhms) desc ");
		sb.append("  , j_msi.juchu_no ");
		sb.append("  , j_msi.juchu_kono ");
		limit(sb, AppConst.KENSAKU_KENSU_MAX);


		logger.info(getClass().getSimpleName() + "=" + sb.toString());
		return sb.toString();
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
