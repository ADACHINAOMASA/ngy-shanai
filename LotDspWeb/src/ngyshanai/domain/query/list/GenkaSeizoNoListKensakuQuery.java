package ngyshanai.domain.query.list;

import java.util.Map;

import ngyshanai.common.AppConst;
import nis.framework.sql.NisQuery;

public class GenkaSeizoNoListKensakuQuery extends NisQuery<Map<String, Object>> {
	private String seizoNo;

	public GenkaSeizoNoListKensakuQuery(String seizoNo) {
		this.seizoNo = seizoNo;
		this.setParameters();
	}

	private void setParameters() {
		addLikeParameter("s_renkei.seizo_no LIKE ? ", seizoNo, LIKE_MODE.STARTS_WITH);
	}

	@Override
	public String getSQL() {
		StringBuilder sb = new StringBuilder();

		sb.append("select ");
		sb.append("  khn.seizo_no ");
		sb.append("  , khn.hinmei ");
		sb.append("  , khn.koshin_ymdhms ");
		sb.append("  , khn.toroku_ymdhms ");
		sb.append("from ");
		sb.append("  ( ");
		sb.append("    select ");
		sb.append("      seizo_no ");
		sb.append("    from ");
		sb.append("      ppm_t_seisaku_shiji_genka_renkei ");
		sb.append("    group by ");
		sb.append("      seizo_no ");
		sb.append("  ) as seizo ");
		sb.append("  inner join natseisakushijikhn khn ");
		sb.append("    on seizo.seizo_no = khn.seizo_no ");
		sb.append("where ");
		sb.append("  seizo.seizo_no is not null ");
		sb.append(createParameterString(true));
		sb.append("order by ");
		sb.append("  coalesce(khn.koshin_ymdhms, khn.toroku_ymdhms) desc ");
		limit(sb, AppConst.KENSAKU_KENSU_MAX);

		logger.info(getClass().getSimpleName() + "=" + sb.toString());
		return sb.toString();
	}

	@Override
	public Map<String, Object> record(Map<String, Object> record) {
		record.put("seizoNo", record.get("SEIZO_NO"));
		record.put("hinmei", record.get("HINMEI"));
		return record;
	}
	
}
