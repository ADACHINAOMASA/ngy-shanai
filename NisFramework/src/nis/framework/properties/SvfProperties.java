package nis.framework.properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import nis.framework.properties.support.PropertiesUtil;
import nis.framework.properties.support.StrConcator;

/**
 * クラスパスにある「XXX_svf.properties」ファイルから値を取得する。
 * @author Leang-Say
 */
public class SvfProperties {

	protected Log logger = LogFactory.getLog(getClass());
	
	public enum Kies {
		SVF_SERVER_ADDRESS("svf.server.address", ""), 
		SVF_SERVER_BACKUP_ADDRESS("svf.backup.address", ""),

		SVF_FORM_ROOT_DIR("svf.form.rootdir", ""), 
		SVF_QUERY_ROOT_DIR("svf.query.rootdir",""), 
		SVF_CONNECTION_STRING("svf.connection.string", ""),

		SVF_PRINTER_MODE("svf.printer.mode", "0"),
		SVF_DIRECTPRINT_PRINTER_NAME("svf.directprint.printername",""),
		
		SVF_IMAGE_ROOT_DIR("svf.image.rootdir",""),
		
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
	
	// --------------------------------------------------------------------------------------------
	private static final String appName = AppProperties.getAppName();
	private static final String defaultProperitesFileName = "svf.properties";
	
	// ------------------------------------------------------------------------
	private static String fileName = StrConcator.concat(appName, defaultProperitesFileName);
	protected static PropertiesUtil props = PropertiesUtil.path(fileName).load();

	private SvfProperties() {
	}

	public static void reload() {
		props = PropertiesUtil.path(fileName).load();
	}

	public static String get(String key, String... params) {
		return props.get(key, params);
	}
	
	public static String getFileName(){
		return fileName;
	}
	
	// ----------------------------------------------------------------------------------------
	
	public static int getSvfPrinterMode() {
		String val = props.getString(Kies.SVF_PRINTER_MODE.getKey());
		if (val == null || "".equals(val.trim())) {
			val = props.getString(Kies.SVF_PRINTER_MODE.getDefaultValue());
		}
		if (val == null || "".equals(val.trim())) {
			val = "0";
		}
		return Integer.valueOf(val);
	}

	public static String getSvfServerAddress(){
		return props.getString(Kies.SVF_SERVER_ADDRESS.getKey());
	}
	
	public static String getSvfServerBackupAddress(){
		return props.getString(Kies.SVF_SERVER_BACKUP_ADDRESS.getKey());
	}

	public static String getSvfFormRootDir(){
		return props.getString(Kies.SVF_FORM_ROOT_DIR.getKey());
	}
	
	public static String getSvfQueryRootDir(){
		return props.getString(Kies.SVF_QUERY_ROOT_DIR.getKey());
	}
	
	public static String getSvfConnectionString(){
		return props.getString(Kies.SVF_CONNECTION_STRING.getKey());
	}
	
	public static String getSvfDirectPrintPrinterName(){
		return props.getString(Kies.SVF_DIRECTPRINT_PRINTER_NAME.getKey());
	}
	
	public static String getSvfImageRootDir(){
		return props.getString(Kies.SVF_IMAGE_ROOT_DIR.getKey());
	}
	
}
