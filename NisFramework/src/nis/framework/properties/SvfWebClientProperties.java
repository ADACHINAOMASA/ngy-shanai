package nis.framework.properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import nis.framework.properties.support.WebInfResourceLoader;

/**
 * 「svfwebclient.properties」ファイルから値を取得する
 * @author Leang-Say
 */
public class SvfWebClientProperties {

	protected Log logger = LogFactory.getLog(getClass());

	public enum Kies {
		SVF_WEBCLIENT_OBJECT_ID("svfwebclient.object.id", ""),
		SVF_WEBCLIENT_OBJECT_CLASSID("svfwebclient.object.classid", ""),
		SVF_WEBCLIENT_OBJECT_CODEBASE("svfwebclient.object.codebase", ""),
		SVF_WEBCLIENT_OBJECT_RAW("svfwebclient.object.raw", ""),

		SVF_WEBCLIENT_OBJECT_PRINTER("svfwebclient.object.printer", ""),

		SVF_WEBCLIENT_OBJECT_PRINTER_YOKO("svfwebclient.object.printer.yoko", ""),
		SVF_WEBCLIENT_OBJECT_PRINTER_TATE("svfwebclient.object.printer.tate", ""),

		SVF_WEBCLIENT_OBJECT_DOCNAME("svfwebclient.object.docname", ""),
		SVF_WEBCLIENT_OBJECT_ENDURL("svfwebclient.object.endurl", ""),
		SVF_WEBCLIENT_OBJECT_ERRORURL("svfwebclient.object.errorurl", ""),
		SVF_WEBCLIENT_OBJECT_CANCELURL("svfwebclient.object.cancelurl", ""),

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
	
	public static final String FILE_NAME = "svfwebclient.properties";
	private static final WebInfResourceLoader props = WebInfResourceLoader.INSTANCE;;

	private SvfWebClientProperties() {
	}

	public static void reload() {
		props.reload();
	}

	public static String get(String key) {
		return props.get(FILE_NAME, key);
	}
	
	public static String getString(String key) {
		return get(key);
	}
	
	public static String getProperty(String key) {
		return get(key);
	}

	//-----------------------------------------------------------------------------------------------------------------//
	public static String getId(){
		return get(Kies.SVF_WEBCLIENT_OBJECT_ID.getKey());
	}

	public static String getClassId() {
		return get(Kies.SVF_WEBCLIENT_OBJECT_CLASSID.getKey());
	}

	public static String getCodeBase() {
		return get(Kies.SVF_WEBCLIENT_OBJECT_CODEBASE.getKey());
	}

	public static String getRaw() {
		return get(Kies.SVF_WEBCLIENT_OBJECT_RAW.getKey());
	}

	public static String getPrinter() {
		return get(Kies.SVF_WEBCLIENT_OBJECT_PRINTER.getKey());
	}

	public static String getPrinterYoko() {
		return get(Kies.SVF_WEBCLIENT_OBJECT_PRINTER_YOKO.getKey());
	}

	public static String getPrinterTate() {
		return get(Kies.SVF_WEBCLIENT_OBJECT_PRINTER_TATE.getKey());
	}

	public String getDocName() {
		return get(Kies.SVF_WEBCLIENT_OBJECT_DOCNAME.getKey());
	}

	public static String getEndUrl() {
		return get(Kies.SVF_WEBCLIENT_OBJECT_ENDURL.getKey());
	}

	public static String getErrorUrl() {
		return get(Kies.SVF_WEBCLIENT_OBJECT_ERRORURL.getKey());
	}
	
	public static String getCancelUrl() {
		return get(Kies.SVF_WEBCLIENT_OBJECT_CANCELURL.getKey());
	}

}
