package nis.framework.util.sql;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;

public class ResultSetMsgMapper extends ResultSetMapper {

	public ResultSetMsgMapper() {
		super(new MsgMappingRule());
	}

	protected static class MsgMappingRule implements ResultSetMappingRule {

		public Object convertData(Object data) {
			if (data != null) {
				if (data instanceof java.sql.Date || data instanceof Timestamp) {
					data = new Date(((Date) data).getTime());
				}

				if (data instanceof java.lang.String ) {
					try {
						data = new String(((String)data).getBytes("Cp943C"), "windows-31j");;
					}
					catch (UnsupportedEncodingException e) {}
				}
			}
			return data;
		}

	}

}
