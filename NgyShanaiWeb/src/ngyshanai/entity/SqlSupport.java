package ngyshanai.entity;

import java.util.List;

import ngyshanai.common.util.StringUtil;
import ngyshanai.common.util.collection.CollectionUtil;

public class SqlSupport {

	public synchronized static StringBuilder appendUpdateInfo(StringBuilder sql) {
		appendTableUpdateInfo(sql, "");
		return sql;
	}

	public synchronized static StringBuilder appendTableUpdateInfo(StringBuilder sql, String alias) {

		sql.append("  , " + alias + "version as version ");
		sql.append("  , " + alias + "toroku_userid as torokuUserid ");
		sql.append("  , " + alias + "toroku_ymdhms as torokuYmdhms ");
		sql.append("  , " + alias + "toroku_frontid as torokuFrontid ");
		sql.append("  , " + alias + "koshin_userid as koshinUserid ");
		sql.append("  , " + alias + "koshin_ymdhms as koshinYmdhms ");
		sql.append("  , " + alias + "koshin_frontid as koshinFrontid ");

		return sql;
	}

	public synchronized static String createInOperatorString(List<String> list) {
		if (CollectionUtil.isNullOrEmpty(list)) {
			return "''";
		}
		String inOperstor = "";
		for (String str : list) {
			if (StringUtil.hasValue(inOperstor)) {
				inOperstor = inOperstor + ",";
			}
			inOperstor = inOperstor + "'" + str + "'";
		}
		return inOperstor;
	}


}
