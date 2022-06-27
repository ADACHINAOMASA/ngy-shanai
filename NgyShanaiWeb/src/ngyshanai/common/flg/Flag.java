package ngyshanai.common.flg;

/**
 * 汎用ON/OFFチェックフラグ OFF = 0 ON = 1
 * 
 * @author Kengo-Nagase
 */
public abstract class Flag {

	// OFF
	public static final String OFF = "0";
	// ON
	public static final String ON = "1";

	/**
	 * OFF(=0)か nullまたは空文字もfalseとなる
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isOff(String val) {
		return OFF.equals(val);
	}

	/**
	 * OFF(=0)か nullまたは空文字の場合はtrueとなる
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isOffNvl(String val) {
		if (val == null || val.equals("")) {
			return true;
		}
		return isOff(val);
	}

	/**
	 * ON(=1)か
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isOn(String val) {
		return ON.equals(val);
	}

	/**
	 * ON(=1)か nullまたは空文字の場合はfalseとなる
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isOnNvl(String val) {
		if (val == null || val.equals("")) {
			return false;
		}
		return isOn(val);
	}

}
