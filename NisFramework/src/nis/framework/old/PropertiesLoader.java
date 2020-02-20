package nis.framework.old;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.scannotation.ClasspathUrlFinder;

public class PropertiesLoader {

	private Log logger = LogFactory.getLog(getClass());

	private static final String DEFAULT_BASE_RESOURCE = "/WEB-INF/classes/";
	private static final String DEFAULT_TARGET_FILE = "*.{properties}";

	private final transient Map<String, String> map = new HashMap<String, String>();

	private String resourcePath;
	private String targetFile;

	public PropertiesLoader() {
		this(DEFAULT_BASE_RESOURCE, DEFAULT_TARGET_FILE);
	}

	public PropertiesLoader(String target) {
		this(DEFAULT_BASE_RESOURCE, target);
	}

	public PropertiesLoader(String baseResource, String target) {
		this.resourcePath = defaultValueIfhasNoValue(baseResource, DEFAULT_BASE_RESOURCE);
		this.targetFile = defaultValueIfhasNoValue(target, DEFAULT_TARGET_FILE);
	}

	public void load() {
		String rootPath = getResourceRootPath();
		File file = new File(rootPath);
		if (file.isDirectory()) {
			try {
				Path dir = FileSystems.getDefault().getPath(rootPath.concat(resourcePath));
				DirectoryStream<Path> dirStream = Files.newDirectoryStream(dir, targetFile);
				for (Path path : dirStream) {
					propertiesToMap(load(dir.toString() + "/" + path.getFileName()), map);
				}
				dirStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String get(String key){
		return get(key, false);
	}

	public String get(String key, String... params) {
		return get(key, false, params);
	}

	public String get(String key, boolean reloadIfNotExist, String... params) {

		if(hasNoValue(key)){
			throw new NullPointerException("keyが指定されていません。");
		}

		if (!map.containsKey(key)) {
			if (reloadIfNotExist) {
				load();
			}
		}

		if(hasNoValue(map.get(key))){
			logger.warn("key="+key+"の値が設定されていません。キーが正しいか確認してください。");
			return "";
		}else{
			return new MessageFormat(nvl(map.get(key),"")).format(params);
		}
	}

	private Properties load(final String resourcePath) throws IOException {
		if (resourcePath == null || "".equals(resourcePath.trim())) {
			throw new IllegalArgumentException("resource path=" + resourcePath + " not found.");
		}
		InputStream inStream = null;
		if (resourcePath.contains(":")) {
			inStream = new FileInputStream(resourcePath);
		} else {
			inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourcePath);
		}
		return loadAsProperties(inStream);
	}

	private void propertiesToMap(Properties from, Map<String, String> to) {
		for (final String name : from.stringPropertyNames()) {
			to.put(name, from.getProperty(name));
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

	protected String getResourceRootPath() {
		URL classpathUrl = ClasspathUrlFinder.findResourceBase(resourcePath);
		if (classpathUrl == null) {
			return null;
		}
		return removeStart(urlDecode(classpathUrl.getPath()), "/");
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

	private boolean hasNoValue(String val) {
		return val == null || "".equals(val.trim());
	}

	private String defaultValueIfhasNoValue(String val, String defaultVal) {
		return hasNoValue(val) ? defaultVal : val;
	}

	private String removeStart(String val, String remove) {
		if (hasNoValue(val)) {
			return val;
		}
		if (val.startsWith(remove)) {
			return val.substring(remove.length());
		}
		return val;
	}

	private String nvl(String val, String sub) {
		return (val == null || val.trim().equals("") ? sub : val);
	}

}
