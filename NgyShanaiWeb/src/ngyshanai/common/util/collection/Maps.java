package ngyshanai.common.util.collection;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import ngyshanai.common.util.DateUtil;

public class Maps {

	public static <K, V> Map<K, V> newHashMap() {
		return new HashMap<K, V>();
	}

	public static <K, V> Map<K, V> newHashMap(int expectedSize) {
		return new HashMap<K, V>(expectedSize);
	}

	public static <K, V> Map<K, V> newHashMap(Map<? extends K, ? extends V> map) {
		return new HashMap<K, V>(map);
	}

	public static <K, V> Map<K, V> newLinkedHashMap() {
		return new LinkedHashMap<K, V>();
	}

	public static <K, V> Map<K, V> newLinkedHashMap(Map<? extends K, ? extends V> map) {
		return new LinkedHashMap<K, V>(map);
	}

	public static <K extends Comparable<?>, V> TreeMap<K, V> newTreeMap() {
		return new TreeMap<K, V>();
	}

	public static <K, V> TreeMap<K, V> newTreeMap(SortedMap<K, ? extends V> map) {
		return new TreeMap<K, V>(map);
	}

	public static <K, V> Map<K, V> newConcurrentHashMap() {
		return new ConcurrentHashMap<K, V>();
	}

	public static boolean isNullOrEmpty(final Map<?, ?> map) {
		return (map == null || map.isEmpty());
	}

	public static String getIgnoreCase(Map<String, String> map, String key) {
		if (isNullOrEmpty(map) || !hasValue(key)) {
			return null;
		}
		for (Entry<String, String> e : map.entrySet()) {
			if (e.getKey().equalsIgnoreCase(key)) {
				return e.getValue();
			}
		}
		return null;
	}
	
	public static Object getIgnoreCaseAsObject(Map<String, Object> map, String key) {
		if (isNullOrEmpty(map) || !hasValue(key)) {
			return null;
		}
		for (Entry<String, Object> e : map.entrySet()) {
			if (e.getKey().equalsIgnoreCase(key)) {
				return e.getValue();
			}
		}
		return null;
	}
	
	public static String getIgnoreCaseAsString(Map<String, Object> map, String key) {
		if (isNullOrEmpty(map) || !hasValue(key)) {
			return null;
		}
		for (Entry<String, Object> e : map.entrySet()) {
			if (e.getKey().equalsIgnoreCase(key)) {
				return (String)e.getValue();
			}
		}
		return null;
	}
	
	public static BigDecimal getIgnoreCaseAsBigDecimal(Map<String, Object> map, String key) {
		if (isNullOrEmpty(map) || !hasValue(key)) {
			return null;
		}
		for (Entry<String, Object> e : map.entrySet()) {
			if (e.getKey().equalsIgnoreCase(key)) {
				return (BigDecimal)e.getValue();
			}
		}
		return null;
	}
	
	public static Integer getIgnoreCaseAsInteger(Map<String, Object> map, String key) {
		if (isNullOrEmpty(map) || !hasValue(key)) {
			return null;
		}
		for (Entry<String, Object> e : map.entrySet()) {
			if (e.getKey().equalsIgnoreCase(key)) {
				return (Integer)e.getValue();
			}
		}
		return null;
	}
	
	public static String getIgnoreCaseAsString2(Map<String, Object> map, String key) {
		if (isNullOrEmpty(map) || !hasValue(key)) {
			return null;
		}
		String val=(String)map.get(key);
		if(hasValue(val)) {
			return val;
		}
		val=(String)map.get(key.toUpperCase());
		if(hasValue(val)) {
			return val;
		}
		val=(String)map.get(key.toLowerCase());
		if(hasValue(val)) {
			return val;
		}	
		return null;
	}
	
	public static Date getIgnoreCaseAsDate2(Map<String, Object> map, String key) {
		if (isNullOrEmpty(map) || !hasValue(key)) {
			return null;
		}
		Date val=(Date)map.get(key);
		if(DateUtil.hasValue(val)) {
			return val;
		}
		val=(Date)map.get(key.toUpperCase());
		if(DateUtil.hasValue(val)) {
			return val;
		}
		val=(Date)map.get(key.toLowerCase());
		if(DateUtil.hasValue(val)) {
			return val;
		}	
		return null;
	}
	

	private static boolean hasValue(String val) {
		return (val != null && !val.trim().equals(""));
	}

}
