package nis.framework.properties.support;

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
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.scannotation.ClasspathUrlFinder;

/**
 * WEB-INF/classesの下に、すべてﾘｿｰｽファイルをロードする
 * @author Leang-Say
 */
public enum WebInfResourceLoader {

	INSTANCE;

	protected Log logger = LogFactory.getLog(getClass());

	private final transient ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();

	private static final String DEFAULT_BASE_RESOURCE = "/WEB-INF/classes/";
	private final String[] DEFAULT_TARGET_RESOURCES = {"*.properties"};
	
	private static final String[] TARGET_ARCHIVES = { ".war", ".ear" };
	private static final String tempFileName = "_resource.zip";

	private String baseResource = null;
	private String[] targetResources = null;

	private WebInfResourceLoader() {
		if (hasNoValue(baseResource)) {
			this.baseResource = DEFAULT_BASE_RESOURCE;
		}
		if (targetResources == null) {
			targetResources = DEFAULT_TARGET_RESOURCES;
		}
		loadResources(this.baseResource, this.targetResources);
		logger.info(this.getClass().getSimpleName() + " first load ...");
	}

	public String get(String propertyFileName, String key) {
		return map.get(concat(propertyFileName, key));
	}
	
	public String getString(String propertyFileName, String key) {
		return map.get(concat(propertyFileName, key));
	}
	
	public String getProperty(String propertyFileName, String key) {
		return map.get(concat(propertyFileName, key));
	}

	public Map<String, String> getMap() {
		return map;
	}

	public String get(String propertyFileName, String key, String... params) {
		if (hasNoValue(key)) {
			throw new NullPointerException("keyが指定されていません。");
		}
		if (hasNoValue(get(propertyFileName, key))) {
			logger.warn("key=" + key + "の値が設定されていません。キーが正しいか確認してください。");
			return "";
		} else {
			return new MessageFormat(get(propertyFileName, key)).format(params);
		}
	}

	public synchronized void reload() {
		loadResources(this.baseResource, this.targetResources);
	}

	public void loadResources(String baseResource, String[] targetFileNamePatterns) {

		if (hasNoValue(baseResource)) {
			throw new IllegalArgumentException("baseResourceのパスを指定されていません。baseResource=" + baseResource);
		}

		if (targetFileNamePatterns == null || targetFileNamePatterns.length == 0) {
			this.targetResources = new String[] { "*" };
		} else {
			this.targetResources = targetFileNamePatterns;
		}
		
		this.baseResource = baseResource;
		String resourcePath = getWebInfResourcePath(baseResource);
		if (resourcePath == null || "".equals(resourcePath.trim())) {
			logger.info("【" + baseResource + "】が見つかりませんでした。");
			return;
		} else {
			if (resourcePath.startsWith("/")) {
				resourcePath = resourcePath.substring(1, resourcePath.length());
			}
		}

		File file = new File(resourcePath);
		if (!file.exists()) {
			logger.info("【" + baseResource + "】が存在しませんでした。");
			return;
		}
		
		map.clear();
		if (file.isDirectory()) {
			Path dir = FileSystems.getDefault().getPath(resourcePath.concat(baseResource));

			try {
				List<Path> pathList = new ArrayList<Path>();
				listFiles(dir, this.targetResources, pathList);
				for (Path p : pathList) {
					propertiesToMap(load(p.toString()), p.getFileName().toString(), map);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			if (isTarget(file.getPath(), TARGET_ARCHIVES)) {
				try {
					loadFromArchive(file, map, this.targetResources);
				} catch (ZipException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	// -----------------------------------------------------------------------------------

	private void listFiles(Path path, String[] targetResources, List<Path> files) throws IOException {
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
			for (Path entry : stream) {
				if (Files.isDirectory(entry)) {
					listFiles(entry, targetResources, files);
				}
				if (isTarget(entry.toString(), targetResources)) {
					files.add(entry);
				}
			}
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

	private Properties loadAsProperties(InputStream inStream) throws IOException {
		Properties properties = new Properties();
		try {
			properties.load(inStream);
		} finally {
			closeQuietly(inStream);
		}
		return properties;
	}

	private String getWebInfResourcePath(String base) {
		URL classpathUrl = ClasspathUrlFinder.findResourceBase(base);
		if (classpathUrl == null) {
			return null;
		}
		return urlDecode(classpathUrl.getPath());
	}

	private String getJarUrlPath(String rootPath, String resourcePath) {
		return "jar:file:/" + rootPath.concat("!/").concat(resourcePath);
	}

	private boolean isTarget(String suffix, String... targets) {
		if (suffix == null || targets == null || targets.length == 0) {
			return false;
		}
		for (String str : targets) {
			if (suffix.endsWith(str.replace("*", "").toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	private void propertiesToMap(Properties from, String fileName, Map<String, String> to) {
		for (final String name : from.stringPropertyNames()) {
			to.put(concat(fileName, name), from.getProperty(name));
		}
	}

	private void loadFromArchive(File file, Map<String, String> map, String[] targetFilenamePattern)
			throws ZipException, IOException {
		ZipFile zip = null;

		try {
			zip = new ZipFile(file);
			String userHome = getOrCreateUserHomeDir(file.getName());

			Enumeration<? extends ZipEntry> entries = zip.entries();
			while (entries.hasMoreElements()) {
				ZipEntry entry = entries.nextElement();

				if (isTarget(entry.getName(), targetFilenamePattern)) {
					InputStream is = zip.getInputStream(entry);

					Path temp = createFile(userHome.concat("/").concat(tempFileName), is);
					closeQuietly(is);

					ZipFile innerZip = new ZipFile(temp.toString());
					Enumeration<? extends ZipEntry> innerEntries = innerZip.entries();
					while (innerEntries.hasMoreElements()) {
						ZipEntry innerEntry = innerEntries.nextElement();

						if (isTarget(innerEntry.getName(), targetFilenamePattern)) {
							URL url = new URL(getJarUrlPath(temp.toString(), innerEntry.getName()));
							InputStream is2 = url.openStream();
							propertiesToMap(loadAsProperties(is2), innerEntry.getName(), map);
							closeQuietly(is2);
							url = null;
						}
					}
					closeQuietly(innerZip);

					delete(temp.toString());
				}

			}

		} finally {
			closeQuietly(zip);
		}

	}

	private String urlDecode(String value) {
		try {
			return URLDecoder.decode(value, "UTF-8");
		} catch (UnsupportedEncodingException ex) {
		}
		return value;
	}

	private String getUserHomeDir(String name) {
		return System.getProperty("user.home") + File.separator + "." + name;
	}

	private String getOrCreateUserHomeDir(String name) {
		String path = getUserHomeDir(name);
		File customDir = new File(path);
		if (customDir.exists()) {
			logger.info(customDir + " already exists");
		} else if (customDir.mkdirs()) {
			logger.info(customDir + " was created");
		} else {
			logger.info(customDir + " was not created");
		}
		return path;
	}

	private Path createFile(String filePath, InputStream input) throws IOException {
		delete(filePath);

		Path temp = Files.createFile(Paths.get(filePath));
		Files.copy(input, temp, StandardCopyOption.REPLACE_EXISTING);

		closeQuietly(input);

		return temp;
	}

	private void delete(String path) {
		File file = new File(path);
		if (file.exists()) {
			try {
				file.setWritable(true);
				if (!file.delete()) {
					file.deleteOnExit();
				}
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
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

	private void closeQuietly(ZipFile zip) {
		try {
			if (zip != null) {
				zip.close();
				zip = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String nvl(String val) {
		return nvl(val, "");
	}

	private String nvl(String val, String sub) {
		return (val == null || val.trim().equals("") ? sub : val);
	}

	private boolean hasNoValue(String val) {
		return val == null || "".equals(val.trim());
	}

	private String concat(String val1, String val2) {
		return nvl(val1).toLowerCase().concat("_").concat(nvl(val2).toLowerCase());
	}
}
