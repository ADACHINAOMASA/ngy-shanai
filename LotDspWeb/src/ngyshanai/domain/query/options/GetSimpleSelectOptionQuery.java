package ngyshanai.domain.query.options;

import java.util.Map;

import nis.framework.sql.NisQuery;
import nis.framework.util.bean.BeanUtil;
import nis.framework.web.option.Option;

import org.apache.commons.lang3.StringUtils;

import ngyshanai.common.msg.options.GetSimpleSelectOptionsRequestInfo;

public class GetSimpleSelectOptionQuery extends NisQuery<Option> {

	private GetSimpleSelectOptionsRequestInfo inMsg;

	public GetSimpleSelectOptionQuery(GetSimpleSelectOptionsRequestInfo inMsg){
		this.inMsg=inMsg;
	}

	@Override
	public String getSQL() {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		sql.append(inMsg.getValue() + " as value, ");
		sql.append(inMsg.getLabel() + " as label ");
		sql.append(" FROM ");
		sql.append(inMsg.getTable());
		if (StringUtils.isNotEmpty(inMsg.getWhere())) {
			sql.append(" WHERE ");
			sql.append(inMsg.getWhere());
		}
		sql.append(" ORDER BY ");
		if (StringUtils.isNotEmpty(inMsg.getOrder())) {
			sql.append(inMsg.getOrder());
		}
		else {
			sql.append("value");
		}
		return sql.toString();
	}

	@Override
	public Option record(Map<String, Object> record) {
		return BeanUtil.createBean(record, Option.class);
	}

}
