package nis.framework.properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import nis.framework.properties.support.WebInfResourceLoader;

/**
 * 「app.properties」ファイルから値を取得する。
 * @author Leang-Say
 */
public class AppProperties {

	protected Log logger = LogFactory.getLog(getClass());

	public enum Kies {
		APP_NAME("app.name", ""), 
		DATA_SOURCE_JNDI_DEFAULT("datasource-jndi-default",""),
		JPA_PERSISTENCT_CONTEXT_DEFAULT("jpa-pc-default", ""),
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
	
	public static final String FILE_NAME = "app.properties";
	protected static final WebInfResourceLoader props = WebInfResourceLoader.INSTANCE;;

	private AppProperties() {
	}

	public static void reload() {
		props.reload();
	}

	public static String get(String key) {
		return props.get(FILE_NAME, key);
	}

	// --------------------------------------------------------------------
	public static String getAppName() {
		return props.get(FILE_NAME, Kies.APP_NAME.getKey());
	}

	public static String getJpaPersistenceContextDefaultName() {
		return props.get(FILE_NAME, Kies.JPA_PERSISTENCT_CONTEXT_DEFAULT.getKey());
	}

	public static String getDataSourceJndiDefaultName() {
		return props.get(FILE_NAME, Kies.DATA_SOURCE_JNDI_DEFAULT.getKey());
	}

}
