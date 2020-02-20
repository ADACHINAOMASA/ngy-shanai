package nis.framework.properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import nis.framework.properties.support.PropertiesUtil;
import nis.framework.properties.support.StrConcator;

/**
 * クラスパスにある「XXX_mail.properties」ファイルから値を取得する。
 * @author Leang-Say
 */
public class MailProperties {

	protected static Log logger = LogFactory.getLog(MailProperties.class);

	public enum Kies {
		SMTP_SERVER("mail.smtp.server", ""), 
		SMTP_USER_NAME("mail.smtp.username", ""), 
		SMTP_PASSWORD("mail.smtp.password", ""), 
		SMTP_USER_AUTHENTICATION("mail.smtp.user-authentication", ""),

		ERROR_MAIL_FROM("mail.error.from", ""), 
		ERROR_MAIL_TO("mail.error.to",""), 
		ERROR_MAIL_SUBJECT("mail.error.subject", ""), 
		ERROR_MAIL_BODY("mail.error.body", ""),

		;

		private String key;
		private String defaultValue;

		Kies(String key, String defaultValue) {
			this.key = key;
			this.defaultValue = defaultValue;
		}

		public String getKey() {
			return key;
		}

		public String getDefaultValue() {
			return defaultValue;
		}
	}

	// -----------------------------------------------------------------------
	private static final String appName = AppProperties.getAppName();
	private static final String defaultProperitesFileName = "mail.properties";

	
	// ------------------------------------------------------------------------
	private static String fileName = StrConcator.concat(appName, defaultProperitesFileName);
	protected static PropertiesUtil props = PropertiesUtil.path(fileName).load();

	private MailProperties() {
	}
	public static void reload() {
		props = PropertiesUtil.path(fileName).load();
	}
	public static String get(String key, String... params) {
		return props.get(key, params);
	}
	// ------------------------------------------------------------------------------------
	public static String getSmtpServer() {
		return get(Kies.SMTP_SERVER.getKey());
	}

	public static String getSmtpUserName() {
		return get(Kies.SMTP_USER_NAME.getKey());
	}

	public static String getSmtpPassword() {
		return get(Kies.SMTP_PASSWORD.getKey());
	}

	public static String getUserAuthentication() {
		return get(Kies.SMTP_USER_AUTHENTICATION.getKey());
	}

	public static String getErrorMailFrom() {
		return get(Kies.ERROR_MAIL_FROM.getKey());
	}

	public static String getErrorMailTo() {
		return get(Kies.ERROR_MAIL_TO.getKey());
	}

	public static String getErrorMailSubject() {
		return get(Kies.ERROR_MAIL_SUBJECT.getKey());
	}

	public static String getErrorMailBody() {
		return get(Kies.ERROR_MAIL_BODY.getKey());
	}
}
