package lotdsp.domain.soapclient;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import lotdsp.domain.logic.hososhiyosho.test.CommonConst;
import lotdsp.domain.logic.hososhiyosho.test.CommonUtility;

public class MapUtil {

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

	public static String getString(Map<?, ?> map, String key, int teisiSta, int teisiEnd) {
		Object teisiItem = map.get(CommonConst.TEISI_ITEM);
		if (teisiItem == null) {
			if (map.get(key) == null) {
				return null;
			} else {
				return toString(map.get(key));
			}
		} else {
			return CommonUtility.ExtractString(teisiItem.toString(), teisiSta, teisiEnd);
		}
	}

	public static String getDecimalAsString(Map<?, ?> map, String key, int teisiSta, int teisiEnd, String format) {
		Object teisiItem = map.get(CommonConst.TEISI_ITEM);
		if (teisiItem == null) {
			if (map.get(key) == null) {
				return null;
			} else {
				DecimalFormat decimalFormat = new DecimalFormat(format);
				return decimalFormat.format(map.get(key));
			}
		} else {
			return CommonUtility.ExtractString(teisiItem.toString(), teisiSta, teisiEnd);
		}
	}

	// --------------------------------------------------------------------------------------------------------------

//	public static String getYotoJpValue(Map<?, ?> map, String key, int teisiSta, int teisiEnd) {
//		Object teisiItem = map.get(CommonConst.TEISI_ITEM);
//		if (teisiItem == null) {
//
//			if (map.get(key) == null) {
//				return null;
//			} else {
//				if (map.get(key).toString().length() > 8) {
//					return map.get(key).toString().substring(0, 7) + "...";
//				} else {
//					return map.get(key).toString();
//				}
//			}
//		} else {
//			return CommonUtility.ExtractString(teisiItem.toString(), teisiSta, teisiEnd);
//		}
//	}

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

	public static String getTrkStsValue(Map<?, ?> map) {
		try {
			if (map == null) {
				return null;
			}
			switch (Double.valueOf(map.get("TRKSTS").toString()).intValue()) {
			case CommonConst.TRKSTS_KAISI_CODE:
				return CommonUtility.GetStrFormat((Date) map.get("STATIME"), "HH:mm");
			case CommonConst.TRKSTS_IPPAN_CODE:
				switch (Integer.parseInt(map.get("DATAKBN").toString(), 10)) {
				case CommonConst.DATAKBN_TEISI_CODE:
					return GetStopTimeString(map.get(CommonConst.STOPTIME_ITEM).toString());
				default:
					return null;
				}
			default:
				return map.get("TRKSTSMOJI").toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static String GetStopTimeString(String stopTime) {
		BigDecimal stoptime = new BigDecimal(stopTime);
		BigDecimal oneDay = new BigDecimal("86400.0");
		BigDecimal result = stoptime.divide(oneDay, 1, BigDecimal.ROUND_HALF_UP);
		if (result.doubleValue() >= 1.0) {
			DecimalFormat decimalFormat = new DecimalFormat(CommonConst.FORMAT_STOPTIME_DAY);
			return decimalFormat.format(result) + "日";
		} else {
			BigDecimal oneHour = new BigDecimal("60.0");
			result = stoptime.divide(oneHour, 0, BigDecimal.ROUND_HALF_UP);
			if (result.equals(new BigDecimal("0.0"))) {
				return null;
			} else {
				DecimalFormat decimalFormat = new DecimalFormat(CommonConst.FORMAT_STOPTIME_MIN);
				return decimalFormat.format(result) + "分";
			}
		}
	}

}
