package lotdsp.common.util;

import java.io.UnsupportedEncodingException;

import com.google.common.io.BaseEncoding;

public class GuavaBase64Util {

	private static final String DEFAULT_ENCODING = "utf-8";

	private GuavaBase64Util() {
	}

	public static String encodeBase64URLSafe(String plainText) {
		if (plainText == null || "".equals(plainText)) {
			return plainText;
		}
		return BaseEncoding.base64Url().omitPadding().encode(plainText.getBytes());
	}

	public static String decode(String encodedText) {
		if (encodedText == null || "".equals(encodedText)) {
			return encodedText;
		}

		try {
			byte[] decodedPhraseAsBytes = BaseEncoding.base64Url().decode(encodedText);
			return new String(decodedPhraseAsBytes, DEFAULT_ENCODING);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return null;
	}
}
