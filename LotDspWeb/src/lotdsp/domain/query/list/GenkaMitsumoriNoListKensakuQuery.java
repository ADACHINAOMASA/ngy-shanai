package lotdsp.domain.query.list;

import java.util.Map;

import lotdsp.common.AppConst;
import nis.framework.sql.NisQuery;

public class GenkaMitsumoriNoListKensakuQuery extends NisQuery<Map<String, Object>> {
	private String mitsumoriNo;
	private String mitsumoriNoHansu;

	public GenkaMitsumoriNoListKensakuQuery(String mitsumoriNo, String mitsumoriNoHansu) {
		this.mitsumoriNo = mitsumoriNo;
		this.mitsumoriNoHansu = mitsumoriNoHansu;
		this.setParameters();
	}

	private void setParameters() {
		addLikeParameter(" m_khn.mitsumori_no LIKE ? ", mitsumoriNo, LIKE_MODE.STARTS_WITH);
		addParameter("　m_khn.mitsumori_no_hansu = ? ", mitsumoriNoHansu);
	}

	@Override
	public String getSQL() {
		StringBuilder sb = new StringBuilder();

		sb.append("select ");
		sb.append("  m_khn.mitsumori_no ");
		sb.append("  , m_khn.mitsumori_no_hansu ");
		sb.append("  , m_khn.kenmei ");
		sb.append("from ");
		sb.append("  ( ");
		sb.append("    select ");
		sb.append("      renkei.mitsumori_no ");
		sb.append("      , renkei.mitsumori_no_hansu ");
		sb.append("    from ");
		sb.append("      ppm_t_mitsumori_genka_renkei renkei ");
		sb.append("    where ");
		sb.append("      renkei.genka_no is not null ");
		sb.append("    group by ");
		sb.append("      renkei.mitsumori_no ");
		sb.append("      , renkei.mitsumori_no_hansu ");
		sb.append("  ) v_genka ");
		sb.append("  inner join ppm_t_mitsumori_khn m_khn ");
		sb.append("    on v_genka.mitsumori_no = m_khn.mitsumori_no ");
		sb.append("    and v_genka.mitsumori_no_hansu = m_khn.mitsumori_no_hansu ");
		sb.append("where ");
		sb.append("  m_khn.mitsumori_no is not null ");
		sb.append(createParameterString(true));
		sb.append("order by ");
		sb.append("  m_khn.mitsumori_no ");
		sb.append("  , m_khn.mitsumori_no_hansu ");
		limit(sb, AppConst.KENSAKU_KENSU_MAX);

		logger.info(getClass().getSimpleName() + "=" + sb.toString());
		return sb.toString();
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
