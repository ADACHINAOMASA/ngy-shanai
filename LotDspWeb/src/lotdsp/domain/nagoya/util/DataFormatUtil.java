package lotdsp.domain.nagoya.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class DataFormatUtil {

	private static String toString(Object obj) {
		if (obj == null) {
			return null;
		}
		return obj.toString();
	}

	public static String getString(Map<?, ?> map, String key) {
		if (map.get(key) == null) {
			return null;
		} else {
			return toString(map.get(key));
		}
	}

	public static String getDecimalAsString(Map<?, ?> map, String key, String format) {
		if (map.get(key) == null) {
			return null;
		} else {
			DecimalFormat decimalFormat = new DecimalFormat(format);
			return decimalFormat.format(map.get(key));
		}
	}

	public static String getSchDateValue(Map<?, ?> map, Date schDateBefore) {
		try {
			if (map == null) {
				return null;
			}

			Date dt = new Date();
			if (map.get("ENDDATE") == null) {
				if (map.get("SCHDATE") != null) {
					dt = (Date) map.get("SCHDATE");
				}
			} else {
				dt = (Date) map.get("ENDDATE");
			}
			SimpleDateFormat df0 = new SimpleDateFormat("M/dd");
			SimpleDateFormat df1 = new SimpleDateFormat("M/dd HH:mm");
			SimpleDateFormat df2 = new SimpleDateFormat("     HH:mm");
			if (schDateBefore != null && df0.format(schDateBefore).equals(df0.format(dt))) {
				return df2.format(dt);
			} else {
				schDateBefore = new Date(dt.getTime());
				return df1.format(dt);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
