package lotdsp.common.util;


import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author Leang-Say
 */
public class IdUtil {

	public static String getUUID() {
		return UUID.randomUUID().toString();
	}

	public static String getNumberID(int digit) {
		return RandomStringUtils.randomNumeric(digit);
	}

	public static String getAlphaNumericID(int digit) {
		return RandomStringUtils.randomAlphanumeric(digit);
	}

	public static String getStringID(int digit) {
		return RandomStringUtils.random(digit, "ABC1234567890!%&'");
	}

}
