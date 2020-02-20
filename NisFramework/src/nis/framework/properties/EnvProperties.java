package nis.framework.properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import nis.framework.properties.support.PropertiesUtil;
import nis.framework.properties.support.StrConcator;
import nis.framework.util.StringUtil;

/**
 * クラスパスにある「XXX_env.properties」ファイルから値を取得する。
 * @author Leang-Say
 */
public class EnvProperties {

	protected static Log logger = LogFactory.getLog(EnvProperties.class);

	public enum Kies {
		DEBUG_SQL("debug.sql", ""), 
		DEBUG_LOGIC("debug.logic", ""), 
		
		APP_WORKING_DIR("app.working.dir", ""), 
		IMAGE_ROOT_DIR("image.root.dir", ""), 
		PDF_ROOT_DIR("pdf.root.dir",""), 
		TEMP_FILE_ROOT_DIR("tempfile.root.dir", ""),
		
		IMAGE_SERVER_URL("image.server.url", ""),
		
		CONNECTION_DRIVER("db.connection.driver", ""),
		CONNECTION_URL("db.connection.url", ""),
		CONNECTION_USER("db.connection.user", ""),
		CONNECTION_PASSORD("db.connection.password", ""),
	
		SOAP_SCHAPP01_URL("soap.schapp01.url", ""),
		SOAP_ICASAPP01_URL("soap.icasapp01.url", ""),
		
		LOT_TOIAWASE_URL("lot.toiawase.url", ""),
		
		SYSTEM_VERSION("system.version",""),
	
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

	private static final String appName = AppProperties.getAppName();
	private static final String defaultProperitesFileName = "env.properties";
	
	
	// ------------------------------------------------------------------------
	private static String fileName = StrConcator.concat(appName, defaultProperitesFileName);
	protected static PropertiesUtil props = PropertiesUtil.path(fileName).load();

	private EnvProperties() {
	}

	public static void reload() {
		props = PropertiesUtil.path(fileName).load();
	}

	public static String get(String key, String... params) {
		try {	
			return props==null? "":props.get(key, params);
		} catch (Exception e) {
			logger.debug("key="+key+"で取得する際エラー発生！エラー："+e.getMessage());
		}
		return null;
	}
	
	// ------------------------------------------------------------------------------------
	public static boolean getDebugSql() {
		try {	
			String val=get(Kies.DEBUG_SQL.getKey());
			if(StringUtil.hasNoValue(val)) {
				return false;
			}
			return "true".equalsIgnoreCase(val)?true:false;
		} catch (Exception e) {
		}
		return false;
	}
	
	public static boolean getDebugLogic() {
		try {
			String val=get(Kies.DEBUG_LOGIC.getKey());
			if(StringUtil.hasNoValue(val)) {
				return false;
			}
			return "true".equalsIgnoreCase(val)?true:false;
		} catch (Exception e) {
		}
		return false;
	}
	
	public static String getAppWorkingDir() {
		return get(Kies.APP_WORKING_DIR.getKey());
	}

	public static String getImageRootDir() {
		return get(Kies.IMAGE_ROOT_DIR.getKey());
	}

	public static String getPdfRootDir() {
		return get(Kies.PDF_ROOT_DIR.getKey());
	}

	public static String getTempFileRootDir() {
		return get(Kies.TEMP_FILE_ROOT_DIR.getKey());
	}
	
	public static String getImageServerUrl() {
		return get(Kies.IMAGE_SERVER_URL.getKey());
	}
	
	public static String getDriverName() {
		return get(Kies.CONNECTION_DRIVER.getKey());
	}
	
	public static String getConnectionUrl() {
		return get(Kies.CONNECTION_URL.getKey());
	}
	
	public static String getUser() {
		return get(Kies.CONNECTION_USER.getKey());
	}
	
	public static String getPassword() {
		return get(Kies.CONNECTION_PASSORD.getKey());
	}
	
	public static String getSoapSchAppURL() {
		return get(Kies.SOAP_SCHAPP01_URL.getKey());
	}
	
	public static String getSoapIcasApp01URL() {
		return get(Kies.SOAP_ICASAPP01_URL.getKey());
	}
	
	public static String getLotToiawaseUrl(String lotNo) {
		return get(Kies.LOT_TOIAWASE_URL.getKey(),lotNo);
	}
	
	public static String getSystemVersion() {
		return get(Kies.SYSTEM_VERSION.getKey());
	}
	
}
