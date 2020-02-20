package nis.framework.web.option;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * <p>
 * オプション操作用ユーティリティ<br>
 * </p>
 * @author Kengo-Nagase
 *
 */
public class OptionUtil {

	/**
	 * ラベル取得
	 * @param option
	 * @return
	 */
	public static String getLabel(Option option) {
		if (option == null) {
			return StringUtils.EMPTY;
		}
		return option.getLabel();
	}

}
