package nis.framework.properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import nis.framework.properties.support.PropertiesUtil;
import nis.framework.properties.support.StrConcator;

/**
 * クラスパスにある「XXX_excel.properties」ファイルから値を取得する。
 */
public class ExcelProperties {

	protected Log logger = LogFactory.getLog(getClass());
	
	public enum Kies {
		EXCEL_TEMPLATE_ROOT_DIR("excel.template.rootdir", ""),
		EXCEL_TEMPLATE_HOSOSHIYOSHO("excel.template.hososhiyosho",""),
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
	private static final String defaultProperitesFileName = "excel.properties";
	
	// ------------------------------------------------------------------------
	private static String fileName = StrConcator.concat(appName, defaultProperitesFileName);
	protected static PropertiesUtil props = PropertiesUtil.path(fileName).load();

	private ExcelProperties() {
	}
	public static void reload() {
		props = PropertiesUtil.path(fileName).load();
	}
	
	public static String get(String key, String... params) {
		return props==null? "":props.get(key, params);
	}
	
	// ------------------------------------------------------------------------------------
	public static String getExcelTemplateRootDir() {
		return get(Kies.EXCEL_TEMPLATE_ROOT_DIR.getKey());
	}
	
	public static String getHosoShiyoshoTemplate() {
		return get(Kies.EXCEL_TEMPLATE_HOSOSHIYOSHO.getKey());
	}
	
}
