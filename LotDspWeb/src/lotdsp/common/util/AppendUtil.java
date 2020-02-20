package lotdsp.common.util;

public class AppendUtil {
	public static final String COMMA = ",";
	public static final String SINGLE_QUOTATION = "'";
	public static final String DOUBLE_QUOTATION = "\"";
	public static final String LINE_SEPARATROE = System.getProperty("line.separator");
	public static final String EMPTY = "";

	public static void append(StringBuilder buff, String str) {
		if (!StringUtil.hasValue(buff.toString())) {
			append(buff, str, true);
		} else {
			append(buff, str, false);
		}
	}

	public static void appendFirst(StringBuilder buff, String str) {
		append(buff, str, true);
	}

	public static void append(StringBuilder buff, String str, boolean firstElement) {
		append(buff, str, firstElement, COMMA, SINGLE_QUOTATION, SINGLE_QUOTATION);
	}

	public static void append(StringBuilder buff, String str, boolean firstElement, String delimiter) {
		append(buff, str, firstElement, delimiter, SINGLE_QUOTATION, SINGLE_QUOTATION);
	}

	public static void append(StringBuilder buff, String str, boolean firstElement, String delimiter,
			String surroundStart, String surroundEnd) {
		if (!firstElement) {
			buff.append(delimiter);
		}
		if (hasNoValue(surroundStart) && hasNoValue(surroundEnd)) {
			buff.append(str);
		} else {
			buff.append(surroundStart).append(str).append(surroundEnd);
		}
	}

	public static void appendNewLine(StringBuilder buff, String str) {
		if (!StringUtil.hasValue(buff.toString())) {
			append(buff, str, true, LINE_SEPARATROE, "", "");
		} else {
			append(buff, str, false, LINE_SEPARATROE, "", "");
		}
	}

	private static boolean hasValue(String val) {
		return (val != null && !val.trim().equals(EMPTY));
	}

	private static boolean hasNoValue(String val) {
		return !hasValue(val);
	}

}
