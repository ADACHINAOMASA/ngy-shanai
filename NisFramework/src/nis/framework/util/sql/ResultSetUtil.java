package nis.framework.util.sql;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nis.framework.util.bean.BeanUtil;

public class ResultSetUtil {

	public static Map<String, Object> convertMap(ResultSet rs) throws SQLException{
		return convertMap(rs, new ResultSetMappingRule() {
			@Override
			public Object convertData(Object data) {
				if (data instanceof java.sql.Date || data instanceof Timestamp) {
					data = new Date(((Date) data).getTime());
				}
				return data;
			}
		});
	}

	public static Map<String, Object> convertMap(ResultSet rs, ResultSetMappingRule rule) throws SQLException{
		ResultSetMetaData metaData = rs.getMetaData();

		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 1; i <= metaData.getColumnCount(); i++) {
			String name = metaData.getColumnLabel(i);

			Object value = rule.convertData(rs.getObject(name));
			if (value != null) {
				map.put(name, value);
			}
		}
		return map;
	}

	public static List<Map<String, Object>> convertMapData(List<Map<String, Object>> dataMaps) {
		return convertMapData(dataMaps, new ResultSetMsgMapper.MsgMappingRule());
	}

	public static List<Map<String, Object>> convertMapData(List<Map<String, Object>> dataMaps, ResultSetMappingRule rule) {
		List<Map<String, Object>> convertMaps = new ArrayList<Map<String,Object>>();
		for (Map<String, Object> dataMap : dataMaps) {
			convertMaps.add(convertMapData(dataMap, rule));
		}
		return convertMaps;
	}

	public static Map<String, Object> convertMapData(Map<String, Object> dataMap) {
		return convertMapData(dataMap, new ResultSetMsgMapper.MsgMappingRule());
	}

	public static Map<String, Object> convertMapData(Map<String, Object> dataMap, ResultSetMappingRule rule) {
		Map<String, Object> convertMap = new HashMap<String, Object>();
		for (String key : dataMap.keySet()) {
			if (dataMap.get(key) != null) {
				convertMap.put(key, rule.convertData(dataMap.get(key)));
			}
		}
		return convertMap;
	}

	public static <T> T convertBean(ResultSet rs, Class<T> beanClass) throws SQLException{
		return BeanUtil.createBean(convertMap(rs), beanClass);
	}

	public static <T> T convertBean(ResultSet rs, ResultSetMappingRule rule, Class<T> beanClass) throws SQLException{
		return BeanUtil.createBean(convertMap(rs, rule), beanClass);
	}

}
