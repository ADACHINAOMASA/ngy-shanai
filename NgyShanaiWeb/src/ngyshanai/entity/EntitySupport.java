package ngyshanai.entity;

import java.util.Map;

import ngyshanai.common.util.collection.Maps;

public class EntitySupport {

	public static Map<String, String> defaultIgnoredProperties() {

		Map<String, String> ignoredMap = Maps.newHashMap();

		ignoredMap.put("version", "version");
		ignoredMap.put("torokuUserid", "torokuUserid");
		ignoredMap.put("torokuYmdhms", "torokuYmdhms");
		ignoredMap.put("torokuFrontid", "torokuFrontid");
		ignoredMap.put("koshinUserid", "koshinUserid");
		ignoredMap.put("koshinYmdhms", "koshinYmdhms");
		ignoredMap.put("koshinFrontid", "koshinFrontid");

		return ignoredMap;
	}

	public static Map<String, String> ignoredProperties(String... properties) {
		Map<String, String> ignoredMap = Maps.newHashMap();
		return appendIgnoredProperties(ignoredMap, properties);
	}

	public static Map<String, String> appendIgnoredProperties(Map<String, String> ignoredMap, String... properties) {
		for (String prop : properties) {
			ignoredMap.put(prop, prop);
		}
		return ignoredMap;
	}

}
