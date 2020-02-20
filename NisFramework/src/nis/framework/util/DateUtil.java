package nis.framework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

public class DateUtil {

	/**
	 * まずcommonsのDateUtilsを探し、それでもないならこちらを使用
	 * 追加も同様
	 */

	/**
	 * JSとのやりとり用
	 * @param text
	 * @return
	 */
	public static Date parse(String text) {
		Date date;
    	if (text.matches("^\\d{8}$")
				|| text.matches("^\\d{4}\\/\\d{2}\\/\\d{2}$")
				|| text.matches("^\\d{4}\\-\\d{2}\\-\\d{2}$")
				|| text.matches("^\\d{4}\\-\\d{2}\\-\\d{2}T\\d{2}\\:\\d{2}\\:\\d{2}\\.\\d{3}Z$")) {
			try {
				date = DateUtils.parseDate(text
						, "yyyyMMdd" , "yyyy/MM/dd" , "yyyy-MM-dd"
						, "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			} catch (ParseException e) {
				throw new AssertionError(e);
			}
		}
		else if (text.matches("^\\d*$")){
			date = new Date(Long.valueOf(text));
		}
		else {
			throw new IllegalArgumentException("日付として解析できない値を受け取りました：" + text);
		}
    	return date;
	}

	/**
	 * 時分秒以下クリア
	 * @param date
	 * @return
	 */
	public static Date clearHMS(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
	}

	public static String format(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	public static Date sqlDateToDate(Date d) {
		return parse(format(d, "yyyy/MM/dd"));
	}
	
}
