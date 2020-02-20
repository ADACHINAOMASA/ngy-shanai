package nis.framework.properties.support;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Leang-Say
 */
public class PropertiesUtil {

	protected Log logger = LogFactory.getLog(getClass());

	private static final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();

	private String resourcePath;

	public PropertiesUtil(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	public static PropertiesUtil path(String resourcePath) {
		return new PropertiesUtil(resourcePath);
	}

	public PropertiesUtil load() {
		Properties props;
		try {
			props = load(resourcePath);
			propertiesToMap(props, map);
		} catch (Exception e) {
			e.printStackTrace();
			props = null;
			return null;
		}
		
		return this;
	}

	private Properties load(final String resourcePath) throws IOException {
		if (resourcePath == null || "".equals(resourcePath.trim())) {
			throw new IllegalArgumentException("resourcePath cannot be null or empty");
		}
		InputStream inStream = null;
		if (resourcePath.contains(":")) {
			inStream = new FileInputStream(resourcePath);
		} else {
			inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourcePath);
		}
		
		if(inStream==null) {
			throw new NullPointerException(resourcePath+"が存在しません。");
		}
		
		return loadAsProperties(inStream);
	}

	public String getProperty(String key, String... params) {
		return get(key, params);
	}

	public String getString(String key, String... params) {
		return get(key, params);
	}

	public String get(String key, String... params) {
		return new MessageFormat(map.get(key)).format(params);
	}

	private void propertiesToMap(Properties from, Map<String, String> map) {
		for (final String name : from.stringPropertyNames()) {
			map.put(name, from.getProperty(name));
		}
	}

	private Properties loadAsProperties(InputStream inStream) throws IOException {		
		Properties properties = new Properties();
		try {
			properties.load(inStream);
		} finally {
			closeQuietly(inStream);
		}
		return properties;
	}

	protected String urlDecode(String value) {
		try {
			return URLDecoder.decode(value, "UTF-8");
		} catch (UnsupportedEncodingException ex) {
		}
		return value;
	}

	private void closeQuietly(InputStream input) {
		try {
			if (input != null) {
				input.close();
				input = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void printAll() {
		for (Entry<String, String> e : map.entrySet()) {
			logger.info(e.getKey() + "=" + e.getValue());
		}
	}
	
//	public static void main(String[] arg) {
//		String resourcePath = "D:/NmtPpmWork/resources/nmtppm_env.properties";
//		PropertiesUtil props = PropertiesUtil.path(resourcePath).load();
//		props.printAll();
//		System.err.println("" + props.get("juchu.renkei.url1", "Hello World!!!"));
//		System.err.println("----------------------------------------------------");
//
//		String resourcePath1 = "nmtppm_env.properties";
//		PropertiesUtil props1 = PropertiesUtil.path(resourcePath1).load();
//		props1.printAll();
//	}
	
}
