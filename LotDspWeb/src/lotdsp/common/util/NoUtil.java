package lotdsp.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leang-Say
 */
public class NoUtil {

	private static final String DEFAULT_DELEMETER = "-";


	public static String join(String delimeter, String... values) {
		if (values == null) {
			return "";
		}
		if (delimeter == null) {
			delimeter = "";
		}

		String result = "";
		for (String val : values) {
			if ("".equals(result)) {
				result = val;
			} else {
				result = result.concat(delimeter).concat(val);
			}
		}

		return result;
	}
	
	public static String[] split(String delimeter, String no) {
		if (no == null || "".equals(no)) {
			return null;
		}
		if (delimeter == null) {
			delimeter = "";
		}
		List<String> list = new ArrayList<String>();
		String[] arr = no.split(delimeter);
		for (int i = 0; i < arr.length; i++) {
			list.add(arr[i]);
		}

		return list.toArray(new String[list.size()]);

	}

	public static String joinDefault(String... values) {
		return join(DEFAULT_DELEMETER, values);
	}

	public static String[] splitDefault(String no) {
		return split(DEFAULT_DELEMETER, no);
	}

}
