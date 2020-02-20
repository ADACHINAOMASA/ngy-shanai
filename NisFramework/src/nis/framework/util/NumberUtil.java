package nis.framework.util;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.StringTokenizer;

/**
 * 数字に関する拡張ユーティリティ
 */
public class NumberUtil {

	/**
	 * ゼロ値
	 */
	protected static final BigDecimal ZERO = BigDecimal.valueOf(0);

	/**
	 * DecimalFormatオブジェクトを取得する。
	 *
	 * @param fmt
	 *            フォーマット文字列
	 */
	private static DecimalFormat getDecimalFormat(String pattern) {
		return new DecimalFormat(pattern);
	}

	/**
	 * 引数の値が Null の場合は false を、それ以外の場合は true を返す。
	 * <ul>
	 * <li>Nullの扱い→false、スペースの扱い→なし
	 * </ul>
	 * <br>
	 * 例： hasValue("123") → true
	 *
	 * @param val
	 *            数値
	 * @return 数値が Null or 0 の場合 → false、それ以外の場合 → true
	 */
	public static boolean hasValue(BigDecimal val) {
		return (val != null && !isZero(val));
	}

	/**
	 * 引数の値が Null、または空文字の場合はゼロを返す。
	 * <ul>
	 * <li>Nullの扱い→ゼロ、スペースの扱い→ゼロ
	 * </ul>
	 * <br>
	 * 例： nvl(null) → 0
	 *
	 * @param val
	 *            文字列
	 * @return Null回避された文字列
	 */
	public static String nvl(String val) {
		return (val == null || val.trim().equals("") ? "0" : val);
	}

	/**
	 * 数値が Nullの場合はゼロを返す。
	 * <ul>
	 * <li>Nullの扱い→ゼロ、スペースの扱い→なし
	 * </ul>
	 * <br>
	 * 例： nvl(null) → 0
	 *
	 * @param val
	 *            数値
	 * @return Null回避された数値
	 */
	public static BigDecimal nvl(BigDecimal val) {
		return (val == null ? ZERO : val);
	}



	/**
	 * 数値が Nullの場合はゼロとして比較。
	 * <ul>
	 * <li>Nullの扱い→ゼロ、スペースの扱い→なし
	 * </ul>
	 * <br>
	 * 例： CompareTo(null, null) → 0
	 *
	 * @param val
	 *            数値
	 * @param dst
	 *            数値
	 * @return nullを0としたcompareTo結果
	 */
	public static int nvlCompareTo(BigDecimal val, BigDecimal dst) {
		return (nvl(val).compareTo(nvl(dst)));
	}

	/**
	 * 数値を指定スケールの文字列へ変換する。
	 * <ul>
	 * <li>Nullの扱い→Null、スペースの扱い→なし
	 * </ul>
	 * <br>
	 * 例： format("12345", 1) → 12,345.0
	 *
	 * @param val
	 *            数値
	 * @param scale
	 *            小数点以下の桁数
	 * @return 指定スケールの文字列へ変換された数値
	 */
	public static String format(BigDecimal val, int scale) {
		if (val == null) {
			return null;
		}
		NumberFormat fmt = NumberFormat.getInstance();
		fmt.setRoundingMode(RoundingMode.HALF_UP);
		fmt.setParseIntegerOnly(scale == 0);
		fmt.setMaximumFractionDigits(scale);
		fmt.setMinimumFractionDigits(scale);
		return fmt.format(val.doubleValue());
	}

	/**
	 * 数値をデフォルトスケールの文字列へ変換する。
	 * <ul>
	 * <li>Nullの扱い→Null、スペースの扱い→なし
	 * </ul>
	 * <br>
	 * 例： format("1234.5") → 1,234.5
	 *
	 * @param val
	 *            数値
	 * @return 文字列へ変換された数値
	 */
	public static String format(BigDecimal val) {
		return format(val, val.scale());
	}

	/**
	 * 数値を指定されたパターンを使ってフォーマット変換する。
	 * <ul>
	 * <li>Nullの扱い→Null、スペースの扱い→エラー
	 * </ul>
	 * <br>
	 * 例： decimalFormat("123", "00000") → 00123
	 *
	 * @param val
	 *            数値
	 * @param pattern
	 *            フォーマットパターン
	 * @return 指定パターンに変換された数値
	 */
	public static String decimalFormat(BigDecimal val, String pattern) {
		if (val == null) {
			return null;
		}
		return getDecimalFormat(pattern).format(val);
	}

	/**
	 * 文字列を数値へ変換する。
	 * <ul>
	 * <li>変換に失敗したときは、ゼロを返す
	 * <li>Nullの扱い→Null、スペースの扱い→ゼロ
	 * </ul>
	 * <br>
	 * 例： parse("12,345.67") → 12345.67
	 *
	 * @param val
	 *            文字列
	 * @return 数値へ変換された文字列
	 */
	public static BigDecimal parse(String val) {
		if (val == null) {
			return null;
		}
		BigDecimal decimal = null;
		try {
			decimal = new BigDecimal(trimComma(val));
		} catch (NumberFormatException x) {
			decimal = ZERO;
		}
		return decimal;
	}

	/**
	 * 文字列を指定スケールの数値へ変換する。
	 * <ul>
	 * <li>丸め処理は切捨てとする
	 * <li>変換に失敗したときは、ゼロを返す
	 * <li>Nullの扱い→Null、スペースの扱い→ゼロ
	 * </ul>
	 * <br>
	 * 例： parse("12,345.67", 1) → 12345.7
	 *
	 * @param val
	 *            文字列
	 * @param scale
	 *            小数点以下の桁数
	 * @return 指定スケールの数値へ変換された文字列
	 */
	public static BigDecimal parse(String val, int scale) {
		if (val == null) {
			return null;
		}
		BigDecimal decimal = parse(val);
		decimal = decimal.setScale(scale, BigDecimal.ROUND_DOWN);
		return decimal;
	}

	/**
	 * 数値を指定スケールの数値になるように丸め処理を行う。
	 * <ul>
	 * <li>Nullの扱い→Null、スペースの扱い→なし
	 * </ul>
	 * <br>
	 * 例： round("12345.67", 1, BigDecimal.ROUND_UP) → 12345.7
	 *
	 * @param val
	 *            数値
	 * @param scale
	 *            小数点以下の桁数
	 * @param mode
	 *            丸めモード
	 * @return 丸め処理を行った数値
	 */
	public static BigDecimal round(BigDecimal val, int scale, int mode) {
		if (val == null) {
			return null;
		}
		return val.setScale(scale, mode);
	}

	/**
	 * 文字列からカンマを取り除く。
	 * <ul>
	 * <li>Nullの扱い→Null、スペースの扱い→スペース
	 * </ul>
	 * <br>
	 * 例： trimComma("123,456") → 123456
	 *
	 * @param val
	 *            文字列
	 * @return カンマが取り除かれた文字列
	 */
	public static String trimComma(String val) {
		if (val == null) {
			return null;
		}
		return trimming(val, ",");
	}

	/**
	 * 文字列にカンマを追加する。
	 * <ul>
	 * <li>数値として判断できないときは、そのまま返します。
	 * <li>Nullの扱い→Null、スペースの扱い→スペース
	 * </ul>
	 * <br>
	 * 例： addComma("123456") → 123,456
	 *
	 * @param val
	 *            文字列
	 * @param scale
	 *            小数点以下の桁数
	 * @return カンマが追加された文字列
	 */
	public static String addComma(String val, int scale) {
		if (val == null) {
			return null;
		}
		BigDecimal decimal = null;
		try {
			decimal = new BigDecimal(trimComma(val));
		} catch (NumberFormatException x) {
			return val;
		}
		return format(decimal, scale);
	}

	/**
	 * 文字列が数値として解釈可能な場合は true を、解釈不可能な場合は false を返す。
	 * <ul>
	 * <li>Nullの扱い→false、スペースの扱い→false
	 * </ul>
	 * <br>
	 * 例： isNum("+1,000") → true
	 *
	 * @param val
	 *            文字列
	 * @return 数値として解釈可能な場合 → true、解釈不可能な場合 → false
	 */
	public static boolean isNum(String val) {
		if (val == null) {
			return false;
		}
		try {
			new BigDecimal(trimComma(val));
		} catch (NumberFormatException x) {
			return false;
		}
		return true;
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
	 * BigDecimalの数値比較をする
	 * <ul>
	 * <br>
	 * val1 > val2 → true
	 * </ul>
	 * <br>
	 *
	 * @param val1
	 *            比較元
	 * @param val2
	 *            比較対象
	 * @return 比較元が大きい true
	 */
	public static boolean isBigger(BigDecimal val1, BigDecimal val2) {
		int result = val1.compareTo(val2);
		return (result == 1);
	}

	/**
	 * BigDecimalの数値比較をする
	 * <ul>
	 * <br>
	 * val1 < val2 → true
	 * </ul>
	 * <br>
	 *
	 * @param val1
	 *            比較元
	 * @param val2
	 *            比較対象
	 * @return 比較元が小さい true
	 */
	public static boolean isSmaller(BigDecimal val1, BigDecimal val2) {
		int result = val1.compareTo(val2);
		return (result == -1);
	}

	/**
	 * 数値の比較をする
	 * <ul>
	 * <br>
	 * val1 = val2 → true
	 * </ul>
	 * <br>
	 *
	 * @param val1
	 *            比較元
	 * @param val2
	 *            比較対象
	 * @return 比較対象と同じ true
	 */
	public static boolean equals(BigDecimal val1, BigDecimal val2) {
		int result = val1.compareTo(val2);
		return (result == 0);
	}

	/**
	 * 数値の比較をする
	 * <ul>
	 * <br>
	 * val1 = val2 → true
	 * </ul>
	 * <br>
	 *
	 * @param val1
	 *            比較元
	 * @param val2
	 *            比較対象
	 * @return 比較対象と同じ true
	 */
	public static boolean equals(BigDecimal val1, int val2) {
		BigDecimal val3 = new BigDecimal(val2);
		int result = val1.compareTo(val3);
		return (result == 0);
	}

	/**
	 * 数値をインクリメントする。
	 *
	 * @param value
	 *            数値
	 * @return インクリメントされた値
	 */
	public static BigDecimal increment(BigDecimal value) {
		if (value == null) {
			return null;
		}
		return value.add(BigDecimal.valueOf(1));
	}

	/**
	 * 2つの値が等しいかどうかを返す。
	 */
	public static boolean isEqual(BigDecimal d1, BigDecimal d2) {
		if (d1 == null || d2 == null) {
			return (d1 == null && d2 == null);
		}
		return d1.compareTo(d2) == 0;
	}

	/**
	 * 数値をデクリメントする。
	 *
	 * @param value
	 *            数値
	 * @return デクリメントされた値
	 */
	public static BigDecimal decrement(BigDecimal value) {
		if (value == null) {
			return null;
		}
		return value.subtract(BigDecimal.valueOf(1));
	}

	/**
	 * 数値をゼロかどうかを返す。 Nullの扱い→true、スペースの扱い→false
	 */
	public static boolean isZero(BigDecimal value) {
		return (value == null || ZERO.compareTo(value) == 0);
	}

	/**
	 * 数値の絶対値を返す
	 */
	public static BigDecimal getZettaichi(BigDecimal value) {
		if (value == null) {
			return null;
		}
		return value.abs();
	}


	/**
	 * 数値が Nullの場合はゼロとして比較。
	 * <ul>
	 * <li>Nullの扱い→ゼロ、スペースの扱い→なし
	 * </ul>
	 * <br>
	 * 例： CompareTo(null) → 0
	 *
	 * @param val 数値
	 * @return nullを0としたcompareTo結果
	 */
	public static int nvlCompareToZero(BigDecimal val) {
		return BigDecimal.ZERO.compareTo(nvl(val));
	}

	/**
	 * 文字列を数値へ変換する。
	 * <ul>
	 * <li>変換に失敗したときは、ゼロを返す
	 * <li>Null、スペースの扱い→ゼロ
	 * </ul>
	 * <br>
	 * 例： parse("12,345.67") → 12345.67
	 *
	 * @param val 文字列
	 * @return 数値へ変換された文字列
	 */
	public static BigDecimal parse0(String val) {
		if (val == null) {
			return ZERO;
		}
		BigDecimal decimal = null;
		try {
			decimal = new BigDecimal(trimComma(val));
		}
		catch (NumberFormatException x) {
			decimal = ZERO;
		}
		return decimal;
	}

	/**
	 * nvlをかけ文字列変換する。 <br>
	 * 例： nvlToString(null) → 0
	 *
	 * @param val 数値
	 * @return 文字列
	 */
	public static String nvlToString(BigDecimal val) {
		return nvl(val).toString();
	}

	/**
	 * nvlをかけ文字列変換する。 <br>
	 * 例： nvlToString(null) → ""
	 *
	 * @param val 数値
	 * @return 文字列
	 */
	public static String nvlToString2(BigDecimal val) {
		return (val == null ? "" : val.toString());
	}
	
	/**
	 * ゼロの場合はNULLを返す。
	 * Nullの扱い→Null、スペースの扱い→なし
	 *
	 * @param val 数値
	 * @return 文字列
	 */
	public static BigDecimal zeroToNull(BigDecimal val) {
		if (isZero(val)) {
			return null;
		}
		return val;
	}

	/**
	 * nullの場合を回避した加算。num1+num2
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static BigDecimal add(BigDecimal num1,BigDecimal num2){
		if(num1==null){
			if(num2==null){
				return null;
			}else{
				return num2;
			}
		}else{
			if(num2==null){
				return num1;
			}else{
				return num1.add(num2);
			}
		}
	}

	/**
	 * nullの場合を回避した減算。num1-num2
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static BigDecimal subtract(BigDecimal num1,BigDecimal num2){
		if(num1==null){
			if(num2==null){
				return null;
			}else{
				return BigDecimal.ZERO.subtract(num2);
			}
		}else{
			if(num2==null){
				return num1;
			}else{
				return num1.subtract(num2);
			}
		}
	}

	/**
	 * nullの場合を回避した乗算。num1*num2
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static BigDecimal multiply(BigDecimal num1,BigDecimal num2){
		if(num1==null && num2==null){
			return null;
		}else{
			if(num1==null || num2==null){
				return BigDecimal.ZERO;
			}else{
				return num1.multiply(num2);
			}
		}
	}

	/**
	 * nullを回避したString変換
	 */
	public static String toString(BigDecimal num){
		if(num==null){
			return null;
		}else{
			return num.toString();
		}
	}

	/**
	 * 符号の変換地を返す
	 */
	public static BigDecimal minusNum(BigDecimal num){
		return multiply(num, new BigDecimal(-1));
	}


}
