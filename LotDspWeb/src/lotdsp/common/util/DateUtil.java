package lotdsp.common.util;


import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * 日付に関する拡張ユーティリティ
 */
public class DateUtil {

	/**
	 * 日付のフォーマットのデフォルト値
	 */
	private static final String FORMAT_DEFAULT = "yyyy/MM/dd";

	/**
	 * SimpleDateFormatオブジェクトを取得する。
	 *
	 * @param fmt
	 *            フォーマット文字列
	 */
	private static SimpleDateFormat getSimpleDateFormat(String fmt) {
		return new SimpleDateFormat(fmt);
	}

	/**
	 * 引数の値が Null の場合は false を、それ以外の場合は true を返す。
	 * <ul>
	 * <li>Nullの扱い→false、スペースの扱い→なし
	 * </ul>
	 * <br>
	 * 例： hasValue("2003/11/25") → true
	 *
	 * @param val
	 *            日付
	 * @return 日付が Null の場合 → false、それ以外の場合 → true
	 */
	public static boolean hasValue(Date val) {
		return (val != null);
	}

	/**
	 * 引数の値を<i>SQL DATE</i>として扱うことが出来るクラスに変換する。
	 * <ul>
	 * <li>Nullの扱い→Null、スペースの扱い→なし
	 * </ul>
	 * <br>
	 * 例： toSqlDate("2003/11/25") → 2003/11/25(java.sql.Date)
	 *
	 * @param val
	 *            日付
	 * @return 変換された日付
	 */
	public static java.sql.Date toSqlDate(Date val) {
		if (val == null) {
			return null;
		}
		return new java.sql.Date(val.getTime());
	}

	public static String format(Object val, String fmt) {
		if (val == null) {
			return null;
		}
		return getSimpleDateFormat(fmt).format(val);
	}

	/**
	 * 日付を指定フォーマットの文字列へ変換する。
	 * <ul>
	 * <li>Nullの扱い→Null、スペースの扱い→なし
	 * </ul>
	 * <br>
	 * 例： format("2003/11/25", "MM/dd") → 11/25
	 *
	 * @param val
	 *            日付
	 * @param fmt
	 *            指定フォーマット
	 * @return 日付を指定フォーマットに変換した文字列
	 */
	public static String format(Date val, String fmt) {
		if (val == null) {
			return null;
		}
		return getSimpleDateFormat(fmt).format(val);
	}

	/**
	 * 日付を yyyy/MM/dd 形式の文字列へ変換する。
	 *
	 * @param val
	 *            日付
	 * @return 日付を指定フォーマットに変換した文字列
	 */
	public static String format(Date val) {
		return format(val, FORMAT_DEFAULT);
	}

	/**
	 * 文字列を指定フォーマットの日付クラスに変換する。
	 * <ul>
	 * <li>変換に失敗したときは、Nullを返す
	 * <li>Nullの扱い→Null、スペースの扱い→Null
	 * </ul>
	 * <br>
	 * 例： parse("2003/11/25", "MM/dd") → 11/25(java.util.Date)
	 *
	 * @param val
	 *            文字列
	 * @param fmt
	 *            指定フォーマット
	 * @return 文字列を指定フォーマットに変換した日付
	 */
	public static Date parse(String val, String fmt) {
		if (val == null) {
			return null;
		}
		Date date = null;
		try {
			date = getSimpleDateFormat(fmt).parse(val);
		} catch (ParseException x) {
		}
		return date;
	}

	/**
	 * yyyy/MM/dd 形式の文字列を日付クラスに変換する。
	 * <ul>
	 * <li>変換に失敗したときは、Nullを返す
	 * <li>Nullの扱い→Null、スペースの扱い→Null
	 * </ul>
	 *
	 * @param val
	 *            文字列 (yyyy/MM/dd形式)
	 * @param fmt
	 *            指定フォーマット
	 * @return 文字列を指定フォーマットに変換した日付
	 */
	public static Date parse(String val) {
		return parse(val, FORMAT_DEFAULT);
	}

	/**
	 * 文字列からスラッシュを取り除く。
	 * <ul>
	 * <li>Nullの扱い→Null、スペースの扱い→スペース
	 * </ul>
	 * <br>
	 * 例： trimSlash("2003/11/25") → 20031125
	 *
	 * @param val
	 *            文字列
	 * @return スラッシュが取り除かれた文字列
	 */
	public static String trimSlash(String val) {
		if (val == null) {
			return null;
		}
		return trimming(val, "/");
	}

	/**
	 * 文字列にスラッシュを追加する。
	 * <ul>
	 * <li>このメソッドは文字列が8桁、6桁、4桁のときのみ機能します
	 * <li>それ以外の桁数の文字列の場合は、そのまま返します
	 * <li>Nullの扱い→Null、スペースの扱い→スペース
	 * </ul>
	 * <br>
	 * 例： addSlash("20031125") → 2003/11/25
	 *
	 * @param val
	 *            文字列
	 * @return スラッシュが追加された文字列
	 */
	public static String addSlash(String val) {
		if (val == null) {
			return null;
		}
		String newVal = trimSlash(val);
		StringBuffer buf = new StringBuffer();
		switch (newVal.length()) {
		// → yyyy/MM/dd
		case 8: {
			buf.append(newVal.substring(0, 4)); // yyyy
			buf.append("/");
			buf.append(newVal.substring(4, 6)); // MM
			buf.append("/");
			buf.append(newVal.substring(6, 8)); // dd
			break;
		}
		// → yyyy/MM
		case 6: {
			buf.append(newVal.substring(0, 4)); // yyyy
			buf.append("/");
			buf.append(newVal.substring(4, 6)); // MM
			break;
		}
		// → MM/dd
		case 4: {
			buf.append(newVal.substring(0, 2)); // MM
			buf.append("/");
			buf.append(newVal.substring(2, 4)); // dd
			break;
		}
		// それ以外
		default: {
			return (val);
		}
		}
		return buf.toString();
	}

	/**
	 * 文字列からコロンを取り除く。
	 * <ul>
	 * <li>Nullの扱い→Null、スペースの扱い→スペース
	 * </ul>
	 * <br>
	 * 例： trimColon("13:59") → 1359
	 *
	 * @param val
	 *            文字列
	 * @return コロンが取り除かれた文字列
	 */
	public static String trimColon(String val) {
		if (val == null) {
			return null;
		}
		return trimming(val, ":");
	}

	/**
	 * 文字列にコロンを追加する。
	 * <ul>
	 * <li>このメソッドは文字列が6桁、4桁のときのみ機能します
	 * <li>それ以外の桁数の文字列の場合は、そのまま返します
	 * <li>Nullの扱い→Null、スペースの扱い→スペース
	 * </ul>
	 * <br>
	 * 例： addColon("1359") → 13:59
	 *
	 * @param val
	 *            文字列
	 * @return コロンが追加された文字列
	 */
	public static String addColon(String val) {
		if (val == null) {
			return null;
		}
		String newVal = trimColon(val);
		StringBuffer buf = new StringBuffer();
		switch (newVal.length()) {
		// → HH:mm:ss
		case 6: {
			buf.append(newVal.substring(0, 2)); // HH
			buf.append(":");
			buf.append(newVal.substring(2, 4)); // mm
			buf.append(":");
			buf.append(newVal.substring(4, 6)); // ss
			break;
		}
		// → HH:mm
		case 4: {
			buf.append(newVal.substring(0, 2)); // HH
			buf.append(":");
			buf.append(newVal.substring(2, 4)); // mm
			break;
		}
		// それ以外
		default: {
			return (val);
		}
		}
		return buf.toString();
	}

	/**
	 * 今日の日付を文字列で取得する。
	 * <ul>
	 * <li>Nullの扱い→なし、スペースの扱い→なし
	 * </ul>
	 * <br>
	 * 例： getToday() → 2003/11/25
	 *
	 * @return 今日の日付文字列
	 */
	public static String today() {
		return format(Calendar.getInstance().getTime());
	}

	/**
	 * 日付に対する 月初 を取得する。
	 * <ul>
	 * <li>Nullの扱い→Null、スペースの扱い→なし
	 * </ul>
	 * <br>
	 * 例： getBeginningOfMonth("2003/11/25") → 2003/11/01
	 *
	 * @param val
	 *            日付
	 * @return 月初
	 */
	public static Date getBeginningOfMonth(Date val) {
		if (val == null) {
			return null;
		}
		// カレンダーオブジェクト
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(val);

		// 最小値を設定しなおす
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMinimum(Calendar.DAY_OF_MONTH));

		return calendar.getTime();
	}

	/**
	 * 日付に対する 月末 を取得する。
	 * <ul>
	 * <li>Nullの扱い→Null、スペースの扱い→なし
	 * </ul>
	 * <br>
	 * 例： getEndOfMonth("2003/11/25") → 2003/11/30
	 *
	 * @param val
	 *            日付
	 * @return 月初
	 */
	public static Date getEndOfMonth(Date val) {
		if (val == null) {
			return null;
		}
		// カレンダーオブジェクト
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(val);

		// 最大値を設定しなおす
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

		return calendar.getTime();
	}

	/**
	 * 文字列が日付として解釈可能な場合は true を、解釈不可能な場合は false を返す。
	 * <ul>
	 * <li>フォーマット yyyy/MM/dd でチェックします
	 * <li>Nullの扱い→false、スペースの扱い→false
	 * </ul>
	 * <br>
	 * 例： isValidDate("2003/11/25") → true
	 *
	 * @param val
	 *            文字列
	 * @return 日付として解釈可能な場合 → true、解釈不可能な場合 → false
	 */
	public static boolean isValidDate(String val) {
		return isValidDate(val, "yyyy/MM/dd");
	}

	/**
	 * 文字列が日付として解釈可能な場合は true を、解釈不可能な場合は false を返す。
	 * <ul>
	 * <li>Nullの扱い→false、スペースの扱い→false
	 * </ul>
	 * <br>
	 * 例： isValidDate("2003/11/25", "yyyy/MM/dd") → true
	 *
	 * @param val
	 *            文字列
	 * @param fmt
	 *            指定フォーマット
	 * @return 日付として解釈可能な場合 → true、解釈不可能な場合 → false
	 */
	public static boolean isValidDate(String val, String fmt) {
		if (val == null) {
			return false;
		}
		// 日付型チェック
		try {
			SimpleDateFormat fmter = new SimpleDateFormat(fmt);
			// date 型に変換
			Date date = fmter.parse(val);

			// 文字列にしても同じになる？
			String newval = fmter.format(date);
			return (newval.equals(val));
		}
		// date に変換できない
		catch (ParseException x) {
			return false;
		}
	}

	/**
	 * 文字列が時刻として解釈可能な場合は true を、解釈不可能な場合は false を返す。
	 * <ul>
	 * <li>フォーマット HH:mm でチェックします
	 * <li>Nullの扱い→false、スペースの扱い→false
	 * </ul>
	 * <br>
	 * 例： isValidTime("20:30") → true
	 *
	 * @param val
	 *            文字列
	 * @return 時刻として解釈可能な場合 → true、解釈不可能な場合 → false
	 */
	public static boolean isValidTime(String val) {
		return isValidDate(val, "HH:mm");
	}

	/**
	 * 文字列が時刻として解釈可能な場合は true を、解釈不可能な場合は false を返す。
	 * <ul>
	 * <li>Nullの扱い→false、スペースの扱い→false
	 * </ul>
	 * <br>
	 * 例： isValidTime("2030", "HHmm") → true
	 *
	 * @param val
	 *            文字列
	 * @param fmt
	 *            指定フォーマット
	 * @return 時刻として解釈可能な場合 → true、解釈不可能な場合 → false
	 */
	public static boolean isValidTime(String val, String fmt) {
		return isValidDate(val, fmt);
	}

	/**
	 * 文字列から指定された文字を取り除く。
	 *
	 * @param val
	 *            文字列
	 * @param t
	 *            取り除く文字
	 * @return 指定された文字が取り除かれた文字列
	 */
	private static String trimming(String val, String t) {
		if (val == null) {
			return null;
		}
		StringTokenizer token = new StringTokenizer(val, t);
		StringBuffer buf = new StringBuffer();
		while (token.hasMoreElements()) {
			buf.append(token.nextElement());
		}
		return buf.toString();
	}

	/**
	 * 2つの値が等しいかどうかを返す。
	 */
	public static boolean isEqual(Date d1, Date d2) {
		if (d1 == null || d2 == null) {
			return (d1 == null && d2 == null);
		}
		return d1.compareTo(d2) == 0;
	}

	/**
	 * 曜日名を返す
	 *
	 * @param value
	 *            日付
	 * @return 曜日名
	 */
	public static String getYobiMei(Date value) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(value);

		return new String[] { "", "日", "月", "火", "水", "木", "金", "土" }[calendar
				.get(Calendar.DAY_OF_WEEK)];
	}

	/**
	 * 日付をフォーマットして文字列で比較する
	 */
	public static int compare(Date value1, Date value2, String fmt) {
		return format(value1, fmt).compareTo(format(value2, fmt));
	}

	/**
	 * 日付をフォーマットして文字列で比較する
	 */
	public static boolean isEqual(Date value1, Date value2, String fmt) {
		return compare(value1, value2, fmt) == 0;
	}

	/**
	 * Dateに任意の月数を加算して返す。
	 *
	 * @param Date
	 *            日付
	 * @param int 加算したい月数
	 * @return Date 日付
	 */
	public static Date addMonth(Date ymd, int month) {
		if (ymd == null) {
			return null;
		}
		if (month == 0) {
			return ymd;
		}

		Calendar calender = Calendar.getInstance();
		calender.setTime(ymd);
		calender.add(Calendar.MONTH, month);

		return calender.getTime();
	}

	/**
	 * Dateに任意の日数を加算して返す。
	 *
	 * @param Date
	 *            日付
	 * @param int 加算したい日数
	 * @return Date 日付
	 */
	public static Date addDate(Date ymd, int date) {
		if (ymd == null) {
			return null;
		}
		if (date == 0) {
			return ymd;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(ymd);
		calendar.add(Calendar.DATE, date);

		return calendar.getTime();
	}

	/**
	 * 今日の日付をDateで取得する。 例： getToday() → 2003/11/25
	 *
	 * @return 今日の日付文字列
	 */
	public static Date getToday() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * 日付をタイムスタンプ型にして返す
	 */
	public static Timestamp toTimestamp(Date value) {
		if (value == null) {
			return null;
		}
		return new Timestamp(value.getTime());
	}

	/**
	 * 日付を時間にして返す
	 */
	public static Time toTime(Date value) {
		if (value == null) {
			return null;
		}
		return new Time(value.getTime());
	}

	/**
	 * 開始終了の時間を返す
	 */
	public static String getTime(String startTime, String endTime) {
		if (startTime == null || endTime == null) {
			return null;
		}
		if (startTime.length() < 4 || endTime.length() < 4) {
			return null;
		}
		String tmpStartTime = trimColon(startTime);
		String tmpEndTime = trimColon(endTime);

		BigDecimal bStartTime = new BigDecimal(tmpStartTime.substring(0, 2))
				.multiply(BigDecimal.valueOf(60)).add(
						new BigDecimal(tmpStartTime.substring(2, 4)));
		BigDecimal bEndTime = null;
		if (tmpStartTime.compareTo(tmpEndTime) <= 0) {
			bEndTime = new BigDecimal(tmpEndTime.substring(0, 2)).multiply(
					BigDecimal.valueOf(60)).add(
					new BigDecimal(tmpEndTime.substring(2, 4)));
		} else {
			bEndTime = new BigDecimal(tmpEndTime.substring(0, 2))
					.add(BigDecimal.valueOf(24))
					.multiply(BigDecimal.valueOf(60))
					.add(new BigDecimal(tmpEndTime.substring(2, 4)));
		}

		BigDecimal time = bEndTime.subtract(bStartTime);
		String hh = time.divide(BigDecimal.valueOf(60), 0,
				BigDecimal.ROUND_DOWN).toString();
		if (hh.length() == 1) {
			hh = "0" + hh;
		}
		String mm = time.subtract(
				new BigDecimal(hh).multiply(BigDecimal.valueOf(60))).toString();
		if (mm.length() == 1) {
			mm = "0" + mm;
		}
		String separator = "";
		if (!tmpStartTime.equals(startTime)) {
			separator = ":";
		}
		return hh + separator + mm;
	}

	/**
	 * 時間を分にして返す
	 */
	public static BigDecimal getMinute(String time) {
		if (time == null) {
			return null;
		}
		if (time.length() < 4) {
			return null;
		}
		String tmpTime = trimColon(time);

		BigDecimal bTime = new BigDecimal(tmpTime.substring(0, 2)).multiply(
				BigDecimal.valueOf(60)).add(
				new BigDecimal(tmpTime.substring(2, 4)));
		return bTime;
	}

	/**
	 * 年度始めの年月を取得する
	 *
	 * @param String
	 *            対象年月
	 * @return String 対象年月の年度開始月
	 */
	public static String getStartNendoYm(String taishoYm) {
		if (taishoYm == null || taishoYm.length() < 6) {
			return null;
		}
		// 同一年の４月を開始とする
		String kaishiTaishoYm = taishoYm.substring(0, 4) + "0"
				+ String.valueOf(Calendar.APRIL + 1);
		if (taishoYm.substring(4, 6).equals(
				"0" + String.valueOf(Calendar.JANUARY + 1))
				|| taishoYm.substring(4, 6).equals(
						"0" + String.valueOf(Calendar.FEBRUARY + 1))
				|| taishoYm.substring(4, 6).equals(
						"0" + String.valueOf(Calendar.MARCH + 1))) {
			// １月～３月の場合は前年の４月を開始とする
			BigDecimal kaishiTaishoYmDecimal = NumberUtil.parse(taishoYm
					.substring(0, 4));
			kaishiTaishoYm = String.valueOf(kaishiTaishoYmDecimal
					.subtract(BigDecimal.valueOf(1)))
					+ "0"
					+ String.valueOf(Calendar.APRIL + 1);
		}
		return kaishiTaishoYm;
	}

	/**
	 * 年月日（文字列）から年月を取得する
	 *
	 * @param String
	 *            対象年月日
	 * @return String 対象年月
	 */
	public static String getNengetsu(String taishoHi) {
		if (taishoHi == null || taishoHi.length() < 8) {
			return null;
		}
		return taishoHi.substring(0, 6);
	}

	// toTimeメソッドのテスト
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		System.out.println(DateUtil.trimColon(
				DateUtil.toTime(cal.getTime()).toString()).substring(0, 4));
	}

	/**
	 * 今日の日付を指定フォーマットの文字列へ変換する。
	 */
	public static String formatToday(String fmt) {
		return format(getToday(), fmt);
	}

	public static boolean isSmallerOrEqual(Date d1, Date d2) {
		if (d1 == null || d2 == null) {
			return (d1 == null && d2 == null);
		}
		return (d1.compareTo(d2) < 0 || d1.compareTo(d2) == 0);
	}
	
	public static boolean isSmallerOrEqual(Date d1, Date d2, String fmt) {
		int result=compare(d1, d2, fmt);
		return (result < 0 || result == 0);
	}
	
	public static boolean isBiggerOrEqual(Date d1, Date d2, String fmt) {
		int result=compare(d1, d2, fmt);
		return (result > 0 || result == 0);
	}
	

	/**
	 * 2つの日付を比較をする
	 * d1 =< d2 → true
	 * @param d1 比較元
	 * @param d2 比較対象
	 * @return 比較元が小さい true
	 */
	public static boolean isSmaller(Date d1, Date d2) {
		if (isEqual(d1, d2) || d1.before(d2)) {
			return true;
		}
		return false;
	}

	/**
	 * 2つの日付を比較をする
	 * d1 => d2 → true
	 * @param d1 比較元
	 * @param d2 比較対象
	 * @return 比較元が大きい true
	 */
	public static boolean isBigger(Date d1, Date d2) {
		if (isEqual(d1, d2) || d1.after(d2)) {
			return true;
		}
		return false;
	}
}
