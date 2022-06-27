package ngyshanai.common.flg;

public class SakujoFlg extends Flag {

	public static final String SAKUJO = ON;
	public static final String NOT_SAKUJO = OFF;

	public static boolean isSakujo(String val) {
		return isOn(val);
	}

	public static boolean isNotSakujo(String val) {
		return !isSakujo(val);
	}

}
