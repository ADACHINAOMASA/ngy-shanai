package lotdsp.common.util;


import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 文字列に関する拡張ユーティリティ
 */
public class StringUtil {

	/**
	 * 空文字
	 */
	private static final String EMPTY = "";

	/**
	 * DB2用のエンコード
	 */
	public static String convDB2String(String value) {
		if (value == null) {
			return null;
		}
		try {
			return new String(value.getBytes("Cp943C"), "windows-31j");
		}
		catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException("入力された引数はCp943からwindows-31jにエンコードできません");
		}

	}

	/**
	 * 引数の値が Null、または空文字の場合は false を、それ以外の場合は true を返す。
	 * <ul>
	 * <li>Nullの扱い→false、スペースの扱い→false
	 * </ul>
	 * <br>
	 * 例： hasValue("") → false
	 * @param val 文字列
	 * @return 文字列が Null、空文字の場合 → false、それ以外の場合 → true
	 */
	public static boolean hasValue(String val) {
		return (val != null && !val.trim().equals(EMPTY));
	}

	/**
	 * 引数の値が Nullの場合は空文字を返す。
	 * <ul>
	 * <li>Nullの扱い→空文字、スペースの扱い→スペース
	 * </ul>
	 * <br>
	 * 例： nvl(null) → ""
	 * @param val 文字列
	 * @return Null回避された文字列
	 */
	public static String nvl(String val) {
		return nvl(val, EMPTY);
	}

	/**
	 * 引数の値が Null、空文字の場合は代わりの文字列を返す。
	 * <ul>
	 * <li>Nullの扱い→代わりの文字列、スペースの扱い→代わりの文字列
	 * </ul>
	 * <br>
	 * 例： nvl(null, "sub") → sub
	 * @param val 文字列
	 * @param sub 代わりの文字列
	 * @return Null回避された文字列
	 */
	public static String nvl(String val, String sub) {
		return (val == null || val.trim().equals(EMPTY) ? sub : val);
	}

	/**
	 * 引数の値が Nullの場合は空文字として比較。
	 * <ul>
	 * <li>Nullの扱い→空文字、スペースの扱い→スペース
	 * </ul>
	 * <br>
	 * 例： nvlEquals(null, null) → 0
	 * @param val 文字列
	 * @param dst 文字列
	 * @return nullをスペースとしたequals結果
	 */
	public static boolean nvlEquals(String val, String dst) {
		return (nvl(val).equals(nvl(dst)));
	}

	/**
	 * 指定された文字列を代わりの文字列に置き換えて返す。
	 * <ul>
	 * <li>Nullの扱い→Null、スペースの扱い→置き換えて返す
	 * </ul>
	 * <br>
	 * 例： replace("ABCABC", "A", "Z") → ZBCZBC
	 * @param val 文字列
	 * @param x 置換対象の文字列
	 * @param sub 代わりの文字列
	 * @return 置換された文字列
	 */
	public static String replace(String val, String x, String sub) {
		if (val == null) {
			return null;
		}
		StringBuffer buf = new StringBuffer(val);
		int i = buf.toString().indexOf(x);
		while (i >= 0) {
			buf.replace(i, i + x.length(), sub);
			i = buf.toString().indexOf(x);
		}
		return buf.toString();
	}

	/**
	 * 引数の文字列を指定区切り文字で分割して配列にして返す。
	 * <ul>
	 * <li>文字列に指定区切り文字が存在しないときはサイズ１の配列で返す
	 * <li>文字列の最初、最後が区切り文字のときも一つの文字列として扱う
	 * <li>Nullの扱い→Null、スペースの扱い→分割して返す
	 * </ul>
	 * <br>
	 * 例： split("1/2/34/5", "/") → 1, 2, 34, 5(サイズ４のString配列)
	 * @param val 文字列
	 * @param separator 区切り文字
	 * @return 分割された文字列配列
	 */
	public static String[] split(String val, char separator) {
		if (val == null) {
			return null;
		}
		ArrayList<String> array = new ArrayList<String>();
		int i = 0;
		int index = val.indexOf(separator, i);
		while (index > -1) {
			array.add(val.substring(i, index));
			i = index + 1;
			index = val.indexOf(separator, i);
		}
		array.add(val.substring(i));
		return array.toArray(new String[0]);
	}

	/**
	 * 2つの値が等しいかどうかを返す。
	 */
	public static boolean isEqual(String s1, String s2) {
		if (s1 == null || s2 == null) {
			return (s1 == null && s2 == null);
		}
		return s1.equals(s2);
	}

	/**
	 * {n} 形式を含む文字列をフォーマットして返す。
	 */
	public static String format(String message, Object param1) {
		return MessageFormat.format(message, new Object[] { param1 });
	}

	/**
	 * {n} 形式を含む文字列をフォーマットして返す。
	 */
	public static String format(String message, Object param1, Object param2) {
		return MessageFormat.format(message, new Object[] { param1, param2 });
	}

	/**
	 * {n} 形式を含む文字列をフォーマットして返す。
	 */
	public static String format(String message, Object param1, Object param2, Object param3) {
		return MessageFormat.format(message, new Object[] { param1, param2, param3 });
	}

	public static String encDb2Java(String text) {
		if (text == null) {
			return null;
		}
		try {
			return new String(text.getBytes("IBM-943"), "Windows-31J");
		}
		catch (UnsupportedEncodingException x) {
			return text;
		}
	}

	public static String fillPre(String val, int length, String fillStr) {
		if (val == null) {
			val = "";
		}
		int valLength = val.length();

		if (length - valLength > 0) {
			for (int j = 0; j < length - valLength; j++) {
				val = fillStr + val;
			}
		}
		return val;
	}

	/**
	 * 文字列の分割(区切り文字、退避文字指定)
	 * 分割時に退避文字に囲まれた区切り文字があった場合、それを一文として扱う
	 * 例 test,"test,test","test" → [test]["test,test"]["test"]
	 * @param str 対象文字列
	 * @param delim 区切り文字列
	 * @param escape 退避文字列
	 * @return ArrayList<String> 分割後の文字列を格納したリスト
	 */
	public static ArrayList<String> splitEscape(String str,String delim,String escape){
		// 区切り文字列文字数
		final int delimLength = delim.length();
		// 退避文字列文字数
		final int escapeLength = escape.length();
		// 現在位置
		int pos = 0;
		// サーチ位置
		int index = 0;
		// 終了フラグ
		boolean endFlg = false;
		// 結果リスト
		ArrayList<String> list = new ArrayList<String>();

		// 終了フラグが偽の間
		while(!endFlg){
			// 現在位置が対象文字列文字数ではないなら右側を評価
			// 現在位置の文字が退避文字なら
			if(pos != str.length() && str.substring(pos,pos + escapeLength).equals(escape)){
				while(true){
					// 現在位置は退避文字だったが、対応する退避文字が見つからなかった場合
					if((index = str.indexOf(escape, pos + escapeLength)) == -1){
						// 現在位置から最後までをリストに追加し、処理終了
						list.add(str.substring(pos));
						endFlg = true;
						break;
					}
					// 次の退避文字が見つかり、そのサーチ位置が文字列最後尾だったなら
					if(index + 1 == str.length()){
						// 現在位置から最後までをリストに追加し、処理終了
						list.add(str.substring(pos));
						endFlg = true;
						break;
					}
					// 退避文字が見つかり、その退避文字の次に区切り文字があるなら
					if(str.substring(index + escapeLength,index + escapeLength + delimLength).equals(delim)){
						// 現在位置から対応する退避文字までの文字列をリストに追加
						list.add(str.substring(pos, index + escapeLength));
						// 現在位置更新
						pos = index + escapeLength + delimLength;
						break;
					}
					// どのループにもたどり着かなかったなら、エラーとする
					throw new RuntimeException("構文の正しくないレコードが入った可能性があります。" + "[ " + str + "]");
				}
			// 現在位置が退避文字でなかったら
			}else{
				// 次の区切り文字が見つかったら
				if((index = str.indexOf(delim,pos)) != -1){
					// 現在位置から区切り文字の前までの文字列をリストに追加
					list.add(str.substring(pos,index));
					// 現在位置更新
					pos = index + delimLength;
				// 次の区切り文字が見つからなかったら
				}else{
					// 現在位置から最後までをリストに追加し、処理終了
					list.add(str.substring(pos));
					endFlg = true;
					break;
				}
			}
		}
		return list;
	}

	public static String trimTopBottom(String val,String trimStr){
		if (hasValue(val)){
			if(val.startsWith(trimStr) && val.endsWith(trimStr)){
				return val.substring(1, val.length() - 1);
			}
		}
		return val;
	}

	public static boolean hasNoValue(String val) {
		return !hasValue(val);
	}
	
	public static boolean isNotEqual(String s1, String s2) {
		return !isEqual(s1, s2);
	}
		
	public static String join(String delimeter, String... strArr) {
		if (strArr == null) {
			return "";
		}
		if (delimeter == null) {
			delimeter = "";
		}

		String result = "";
		for (String val : strArr) {
			if ("".equals(result)) {
				result = nvl(val);
			} else {
				result = result.concat(delimeter).concat(nvl(val));
			}
		}

		return result;
	}
	
	public static String[] split(String delimeter, String str) {
		if (str == null || "".equals(str)) {
			return null;
		}
		if (delimeter == null) {
			delimeter = "";
		}
		List<String> list = new ArrayList<String>();
		String[] arr = str.split(delimeter);
		for (int i = 0; i < arr.length; i++) {
			list.add(arr[i]);
		}
		return list.toArray(new String[list.size()]);
	}
	
	public static String joinDefault(String... strArr) {
		return join("-", strArr);
	}

	public static String[] splitDefault(String str) {
		return split("-", str);
	}
	
}
