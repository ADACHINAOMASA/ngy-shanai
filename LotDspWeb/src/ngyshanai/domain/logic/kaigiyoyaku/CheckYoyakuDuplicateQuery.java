package ngyshanai.domain.logic.kaigiyoyaku;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import ngyshanai.common.msg.kaigiyoyaku.YoyakuInfo;
import nis.framework.sql.NisQuery;

public class CheckYoyakuDuplicateQuery extends NisQuery<YoyakuInfo>{
	
	private String kaigishitsuCd;
	private Date yoyakuDate;
	private String yoyakuBlockStart;
	private String yoyakuBlockEnd;
	
	public CheckYoyakuDuplicateQuery(String kaigishitsuCd, Date yoyakuDate, String yoyakuBlockStart, String yoyakuBlockEnd) {
		this.kaigishitsuCd = kaigishitsuCd;
		this.yoyakuDate = yoyakuDate;
		this.yoyakuBlockStart = yoyakuBlockStart;
		this.yoyakuBlockEnd = yoyakuBlockEnd;
		
		this.setParameters();
	}

	private void setParameters() {
		addParameter("y.KAIGISHITSU_CD = ? ", kaigishitsuCd);
		addParameter("y.YOYAKU_DATE = ? ", yoyakuDate);
		addParameter("y.YOYAKU_BLOCK_START <= ? ", BigDecimal.valueOf(Integer.parseInt(yoyakuBlockEnd)));
		addParameter("y.YOYAKU_BLOCK_END >= ? ", BigDecimal.valueOf(Integer.parseInt(yoyakuBlockStart)));
	}

	@Override
	public String getSQL() {
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT ");
		sql.append("   * ");
		sql.append(" FROM ");
		sql.append("   YOYAKU_TABLE y  ");
		sql.append(" WHERE ");
		sql.append(" y.KAIGISHITSU_CD IS NOT NULL ");
		sql.append(createParameterString(true));

		logger.info(getClass().getSimpleName() + "=" + sql.toString());
		return sql.toString();
	}

	// KaigiYoyakuUpdateLogicで予約IDのみ必要となる
	@Override
	public YoyakuInfo record(Map<String, Object> record) {
		YoyakuInfo info = new YoyakuInfo();
		
		info.setYoyakuCd(record.get("YOYAKU_CD").toString());
		
		return info;
	}
}
