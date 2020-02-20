package nis.framework.properties;

import java.util.Map;

import nis.framework.properties.support.WebInfResourceLoader;

/**
 * 「messages.properties」ファイルから値を取得するクラス。
 * @author Leang-Say
 */
public class Messages {

	protected static String FILE_NAME = "messages.properties";
	
	protected static final WebInfResourceLoader props = WebInfResourceLoader.INSTANCE;
	
	public static void reload() {
		props.reload();
	}
	
	public static Map<String,String> getAll(){
		return props.getMap();
	}

	public static String getValue(String fileName, String key) {
		return props.get(fileName, key);
	}
	public static String getValue(String fileName, String key, String... params) {
		return props.get(fileName, key, params);
	}
	
	public static String get(String key) {
		return getValue(FILE_NAME, key);
	}
	public static String get(String key, String... params) {
		return getValue(FILE_NAME, key,params);
	}

	public static String of(String key) {
		return get(key);
	}
	public static String of(String key, String... params) {
		return get(key, params);
	}
		
	public static String getMessage(String key) {
		return get(key);
	}
	public static String getMessage(String key, String... params) {
		return get(key, params);
	}

	public static String getString(String key) {
		return get(key);
	}
	public static String getString(String key, String... params) {
		return get(key, params);
	}
	
	public static String getProperty(String key) {
		return get(key);
	}
	public static String getProperty(String key, String... params) {
		return get(key, params);
	}

}
