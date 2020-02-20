package nis.framework.util.sql;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import nis.framework.util.bean.BeanMapper;

public class ResultSetMapper extends BeanMapper {

	private ResultSetMappingRule rule;

	public ResultSetMapper() {
	}

	public ResultSetMapper(ResultSetMappingRule rule) {
		this.rule = rule;
	}

	public <T> T mappingIgnoreCase(Class<T> type, ResultSet rs) throws SQLException {
		return mappingIgnoreCase(type, createMap(rs));
	}

	public <T> T mapping(Class<T> type, ResultSet rs) throws SQLException {
		return mapping(type, createMap(rs));
	}

	public <T> T mappingIgnoreCase(T bean, ResultSet rs) throws SQLException {
		return mappingIgnoreCase(bean, createMap(rs));
	}

	public <T> T mapping(T bean, ResultSet rs) throws SQLException {
		return mapping(bean, createMap(rs));
	}

	private Map<String, Object> createMap(ResultSet rs) throws SQLException {
		ResultSetMetaData metaData = rs.getMetaData();

		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 1; i <= metaData.getColumnCount(); i++) {
			String name = metaData.getColumnLabel(i);
			Object value = rs.getObject(name);
			if (rule != null) {
				value = rule.convertData(value);
			}
			if (value != null) {
				map.put(name, value);
			}
		}
		return map;
	}

}
