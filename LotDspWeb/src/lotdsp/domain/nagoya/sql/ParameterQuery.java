/*
 * ParameterQuery.java
 *
 * Created on 2006/10/17, 16:08
 * Copyright Hirohiko-Matsushita
 *
 */

package lotdsp.domain.nagoya.sql;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lotdsp.domain.nagoya.log.Log;
import lotdsp.domain.nagoya.log.LogFactory;
import lotdsp.domain.nagoya.util.DateUtil;
import lotdsp.domain.nagoya.util.NumberUtil;
import lotdsp.domain.nagoya.util.StringUtil;

/**
 * パラメータを使用したQueryの基底クラス。
 * @author Hirohiko-Matsushita
 */
//public abstract class ParameterQuery implements Query {
public abstract class ParameterQuery {

	/**
	 * 前方一致 (LIKE '～%')
	 */
	protected static final int STARTS_WITH = 1;

	/**
	 * 後方一致 (LIKE '%～')
	 */
	protected static final int ENDS_WITH = 2;

	/**
	 * 部分一致 (LIKE '%～%')
	 */
	protected static final int CONTAINS = 3;

	/**
	 * ログオブジェクト
	 */
	protected Log logger = LogFactory.getLog(getClass());

	/**
	 * パラメータのリスト
	 */
	private List paramList = new ArrayList();

	/**
	 * 文字列型のパラメータを追加する。
	 * @param param パラメータ文字列
	 * @param value パラメータ値
	 */
	protected void addParameter(String param, String value) {
		if (StringUtil.hasValue(value)) {
			paramList.add(new Parameter(param, value));
		}
	}

	/**
	 * 数値型のパラメータを追加する。
	 * @param param パラメータ文字列
	 * @param value パラメータ値
	 */
	protected void addParameter(String param, BigDecimal value) {
		if (NumberUtil.hasValue(value)) {
			paramList.add(new Parameter(param, value));
		}
	}

	/**
	 * 日付型のパラメータを追加する。
	 * @param param パラメータ文字列
	 * @param value パラメータ値
	 */
	protected void addParameter(String param, Date value) {
		if (DateUtil.hasValue(value)) {
			paramList.add(new Parameter(param, value));
		}
	}

	/**
	 * LIKE検索のパラメータを追加する。
	 * @param param パラメータ文字列 ('%'は不要)
	 * @param value パラメータ値
	 * @param mode 検索モード (前方一致、後方一致、部分一致)
	 */
	protected void addLikeParameter(String param, String value, int mode) {
		if (StringUtil.hasValue(value)) {
			if (mode == STARTS_WITH) {
				paramList.add(new Parameter(param, value + "%"));
			}
			else if (mode == ENDS_WITH) {
				paramList.add(new Parameter(param, "%" + value));
			}
			else if (mode == CONTAINS) {
				paramList.add(new Parameter(param, "%" + value + "%"));
			}
		}
	}

	/**
	 * パラメータから、SQL WHERE句の条件文字列を作成する。
	 * @param appendAnd 条件文字列を「AND」から始める場合はtrue
	 */
	protected String getParameterString(boolean appendAnd) {
		StringBuffer sql = new StringBuffer();

		for (int n = 0; n < paramList.size(); n++) {
			Parameter param = (Parameter) paramList.get(n);
			if (StringUtil.hasValue(param.getParameter())) {
				sql.append("AND ");
				sql.append(param.getParameter());
				sql.append(" ");
			}
		}

		// appendAndがfalseの場合は先頭の「AND 」を削る
		if (!appendAnd && getParameterCount() > 0) {
			sql.delete(0, 4);
		}

		if (logger.isDebugEnabled() && paramList.size() > 0) {
			logger.debug("条件文字列: " + sql);
		}

		return sql.toString();
	}

	/**
	 * 値が設定されているパラメータの数を返す。
	 */
	protected int getParameterCount() {
		return paramList.size();
	}

	/**
	 * SQLで使用するパラメータ値を設定する。
	 */
	public void setParameters(PreparedStatement st) throws SQLException {
		// パラメータ値のトレース
		if (logger.isDebugEnabled() && paramList.size() > 0) {
			StringBuffer buf = new StringBuffer();
			for (int n = 0; n < paramList.size(); n++) {
				Parameter param = (Parameter) paramList.get(n);
				if (n > 0) {
					buf.append(", ");
				}
				buf.append(param.getValue());
			}
			logger.debug("パラメータ: " + buf);
		}

		int index = 1;

		for (int n = 0; n < paramList.size(); n++) {
			Parameter param = (Parameter) paramList.get(n);

			if (param.isString()) {
				st.setString(index++, (String) param.getValue());
			}
			else if (param.isBigDecimal()) {
				st.setBigDecimal(index++, (BigDecimal) param.getValue());
			}
			else if (param.isDate()) {
				st.setDate(index++, DateUtil.toSqlDate((Date) param.getValue()));
			}
		}
	}
}

