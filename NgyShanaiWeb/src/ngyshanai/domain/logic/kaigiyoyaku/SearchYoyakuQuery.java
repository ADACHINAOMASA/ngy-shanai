package ngyshanai.domain.logic.kaigiyoyaku;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

import ngyshanai.common.msg.kaigiyoyaku.YoyakuInfo;
import nis.framework.sql.NisQuery;

public class SearchYoyakuQuery extends NisQuery<YoyakuInfo> {

	private Date yoyakuDate;
	
	public SearchYoyakuQuery(Date hizuke) {
		this.yoyakuDate = hizuke;
		
		this.setParameters();
	}

	private void setParameters() {
		addParameter("y.YOYAKU_DATE = ? ", yoyakuDate);
	}

	@Override
	public String getSQL() {
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT ");
		sql.append("   y.YOYAKU_CD ");
		sql.append("   ,y.KAIGISHITSU_CD ");
		sql.append("   , y.YOYAKU_DATE ");
		sql.append("   , y.YOYAKU_BLOCK_START  ");
		sql.append("   , y.YOYAKU_BLOCK_END  ");
		sql.append("   , y.YOYAKUSHA_CD  ");
		sql.append("   , y.BIKO  ");
		sql.append("   , y.YOYAKUSHA_NAME  ");
		sql.append("   , y.TEL  ");
		sql.append("   , y.IMPORTANCE  ");
		sql.append("   , y.ISRESERVED  ");
		sql.append("   , y.MAISHU_YOYAKU_CD  ");
		sql.append(" FROM ");
		sql.append("   YOYAKU_TABLE y  ");
		sql.append("   JOIN M_KAIGISHITSU k  ");
		sql.append("   ON y.KAIGISHITSU_CD = k.KAIGISHITSU_CD  ");
		sql.append(" WHERE ");
		sql.append("   y.KAIGISHITSU_CD IS NOT NULL  ");
		sql.append(createParameterString(true));
		sql.append(" ORDER BY ");
		sql.append("   y.KAIGISHITSU_CD ASC ");
		sql.append("   , y.YOYAKU_BLOCK_START ASC  ");

		logger.info(getClass().getSimpleName() + "=" + sql.toString());
		return sql.toString();
	}

	@Override
	public YoyakuInfo record(Map<String, Object> record) {
		YoyakuInfo info = new YoyakuInfo();
		
		info.setYoyakuCd(record.get("YOYAKU_CD").toString());
		info.setKaigishitsuCd(record.get("KAIGISHITSU_CD").toString());
		info.setYoyakuDate((Date)record.get("YOYAKU_DATE"));
		info.setYoyakuBlockStart(record.get("YOYAKU_BLOCK_START").toString());
		info.setYoyakuBlockEnd(record.get("YOYAKU_BLOCK_END").toString());
		info.setYoyakushaCd(record.get("YOYAKUSHA_CD").toString());
		
		info.setBiko(nullCheck(record.get("BIKO")));
		info.setYoyakushaName(nullCheck(record.get("YOYAKUSHA_NAME")));
		info.setTel(nullCheck(record.get("TEL")));
		info.setImportance(nullCheck(record.get("IMPORTANCE")));
		info.setIsreserved(nullCheck(record.get("ISRESERVED")));
		info.setMaishuYoyakuCd(nullCheck(record.get("MAISHU_YOYAKU_CD")));
		
		return info;
	}
	
	private String nullCheck(Object obj) {
		if (Objects.nonNull(obj)) {
			return obj.toString();
		}
		else {
			return "";
		}
	}
}
