package nis.framework.sql;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// TODO:古い作りを流用しているので改善の余地あり

public abstract class NisQuery <T>{

	private List<NisParameter> parameters = new ArrayList<NisParameter>();

	protected Log logger = LogFactory.getLog(getClass());

	public abstract String getSQL();

	public List<NisParameter> getParameters() {
		return parameters;
	}

	protected boolean hasParameter() {
		return !parameters.isEmpty();
	}

	/**
	 * 文字列型のパラメータを追加する。
	 *
	 * @param param パラメータ文字列
	 * @param value パラメータ値
	 */
	protected void addParameter(String param, String value) {
		if (StringUtils.isNotEmpty(value)) {
			parameters.add(new NisParameter(param, value));
		}
	}

	/**
	 * 数値型のパラメータを追加する。
	 *
	 * @param param パラメータ文字列
	 * @param value パラメータ値
	 */
	protected void addParameter(String param, BigDecimal value) {
		if (value != null) {
			parameters.add(new NisParameter(param, value));
		}
	}

	/**
	 * 日付型のパラメータを追加する。
	 *
	 * @param param パラメータ文字列
	 * @param value パラメータ値
	 */
	protected void addParameter(String param, Date value) {
		if (value != null) {
			parameters.add(new NisParameter(param, value));
		}
	}

	/**
	 * LIKE検索のパラメータを追加する。
	 *
	 * @param param パラメータ文字列 ('%'は不要)
	 * @param value パラメータ値
	 * @param mode 検索モード (前方一致、後方一致、部分一致、汎用Like (アスタリスクを%に置換)
	 */
	protected void addLikeParameter(String param, String value, LIKE_MODE mode) {
		if (StringUtils.isNotEmpty(value)) {
			if (mode == LIKE_MODE.STARTS_WITH) {
				parameters.add(new NisParameter(param, value + "%"));
			}
			else if (mode == LIKE_MODE.ENDS_WITH) {
				parameters.add(new NisParameter(param, "%" + value));
			}
			else if (mode == LIKE_MODE.CONTAINS) {
				parameters.add(new NisParameter(param, "%" + value + "%"));
			}
			else if (mode == LIKE_MODE.FREELIKE) {
				parameters.add(new NisParameter(param, value.replaceAll("\\*", "%")));
			}
		}
	}

	protected String createParameterString(boolean appendAnd) {
		StringBuffer sql = new StringBuffer();

		for (NisParameter parameter : parameters) {
			if (StringUtils.isNotEmpty(parameter.getParameter())) {
				sql.append("AND ");
				sql.append(parameter.getParameter());
				sql.append(" ");
			}
		}

		// appendAndがfalseの場合は先頭の「AND 」を削る
		if (!appendAnd && hasParameter()) {
			sql.delete(0, 4);
		}

		if (logger.isDebugEnabled() && hasParameter()) {
			logger.debug("条件文字列: " + sql);
		}

		return sql.toString();
	}
	
	protected void db2FetchFirst(StringBuilder sql,int maxRecord){
		sql.append(" FETCH FIRST "+maxRecord+" ROWS ONLY ");
	}
	
	protected void db2FetchFirst(StringBuffer sql,int maxRecord){
		sql.append(" FETCH FIRST "+maxRecord+" ROWS ONLY ");
	}
	
	protected void limit(StringBuffer sql,int maxRecord){
		db2FetchFirst(sql, maxRecord);
	}
	
	protected void limit(StringBuilder sql,int maxRecord){
		db2FetchFirst(sql, maxRecord);
	}

	protected enum LIKE_MODE {
		/**
		 * 前方一致 (LIKE '～%')
		 */
		STARTS_WITH,
		/**
		 * 後方一致 (LIKE '%～')
		 */
		ENDS_WITH,
		/*
		 * 部分一致 (LIKE '%～%')
		 */
		CONTAINS,
		/**
		 * 汎用Like (アスタリスクを%に置換)
		 */
		FREELIKE

	}
	public abstract T record(Map<String, Object> record);

}
