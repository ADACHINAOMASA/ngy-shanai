package ngyshanai.domain.logic.kaigiyoyaku;

import java.util.Date;
import java.util.Map;

import ngyshanai.common.msg.kaigiyoyaku.YoyakuInfo;
import nis.framework.sql.NisQuery;

public class SearchMaishuYoyakuQuery extends NisQuery<YoyakuInfo>{

	private String maishuYoyakuId;
	
	public SearchMaishuYoyakuQuery(String maishuYoyakuId) {
		this.maishuYoyakuId = maishuYoyakuId;
		
		this.setParameters();
	}

	private void setParameters() {
		addParameter("　y.MAISHU_YOYAKU_ID = ? ", maishuYoyakuId);
	}

	@Override
	public String getSQL() {
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT ");
		sql.append("   y.YOYAKU_DATE ");
		sql.append("   , y.YOYAKU_ID ");
		sql.append(" FROM ");
		sql.append("   YOYAKU_TABLE y  ");
		sql.append(" WHERE ");
		sql.append("   y.KAIGISHITSU_CD IS NOT NULL ");
		sql.append(createParameterString(true));

		logger.info(getClass().getSimpleName() + "=" + sql.toString());
		return sql.toString();
	}

	// MaishuYoyakuDeleteLogicで日付と予約IDは使いたいのでそれらだけ入れておく
	@Override
	public YoyakuInfo record(Map<String, Object> record) {
		YoyakuInfo info = new YoyakuInfo();
		
		info.setYoyakuDate((Date)record.get("YOYAKU_DATE"));
		info.setYoyakuId(record.get("YOYAKU_ID").toString());
		
		return info;
	}
}
