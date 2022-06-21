package ngyshanai.common.query;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ngyshanai.common.util.StringUtil;
import ngyshanai.common.util.bean.Beanz;
import nis.framework.properties.EnvProperties;

/**
 * @author Leang-Say
 */
public class JpaNativeQuerySupport {

	protected static Log logger = LogFactory.getLog(JpaNativeQuerySupport.class);
	
	private static boolean isDebug = EnvProperties.getDebugSql();
	
	// --------------------------------------------------------------getResultList
	public synchronized static <T> List<T> getResultList(EntityManager em, StringBuilder sql, Object[] parameters,Class<T> resultClass) {
		return getResultList(em, sql.toString(), parameters, resultClass);
	}

	public synchronized  static <T> List<T> getResultList(EntityManager em, String sql, Object[] parameters, Class<T> resultClass) {
		try {
			debug(sql, parameters);
			
			Query query = em.createNativeQuery(sql, Map.class);
			setParameters(query, parameters);
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> list = (List<Map<String, Object>>) query.getResultList();
			List<T> results = new ArrayList<T>();
			for (Map<String, Object> record : list) {
				results.add(Beanz.toBean(convertData(record), resultClass));
			}
			return results;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public synchronized  static List<Map<String, Object>> getResultListAsMap(EntityManager em, String sql, Object[] parameters) {
		try {
			debug(sql, parameters);
			
			Query query = em.createNativeQuery(sql, Map.class);
			setParameters(query, parameters);
			return getResultListAsMap(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public synchronized  static List<Map<String, Object>> getResultListAsMap(EntityManager em, String sql,Map<String, Object> parameters) {
		try {
			debug(sql, parameters);
			
			Query query = em.createNativeQuery(sql, Map.class);
			setParameters(query, parameters);
			return getResultListAsMap(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public synchronized  static List<Object[]> getResultListAsObject(EntityManager em, String sql, Object[] parameters) {
		try {
			debug(sql, parameters);
			
			Query query = em.createNativeQuery(sql);
			setParameters(query, parameters);
			@SuppressWarnings("unchecked")
			List<Object[]> list = query.getResultList();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private synchronized  static List<Map<String, Object>> getResultListAsMap(Query query) {
		try {
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> list = (List<Map<String, Object>>) query.getResultList();
			for (Map<String, Object> record : list) {
				convertData(record);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// --------------------------------------------------------------getSingleResult

	public synchronized  static <T> T getSingleResult(EntityManager em, StringBuilder sql, Object[] parameters,Class<T> resultClass) {
		return getSingleResult(em, sql.toString(), parameters, resultClass);
	}

	public synchronized  static <T> T getSingleResult(EntityManager em, String sql, Object[] parameters, Class<T> resultClass) {
		try {
			debug(sql, parameters);
			
			Map<String, Object> record = getSingleResultAsMap(em, sql, parameters);
			return Beanz.toBean(record, resultClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public synchronized  static <T> T getSingleResultAsPojo(EntityManager em, String sql, Object[] parameters, Class<T> resultClass) {
		try {
			debug(sql, parameters);
			
			Query query = em.createNativeQuery(sql, resultClass);
			setParameters(query, parameters);
			@SuppressWarnings("unchecked")
			T result = (T) query.getSingleResult();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public synchronized  static Map<String, Object> getSingleResultAsMap(EntityManager em, String sql, Object[] parameters) {
		try {
			debug(sql, parameters);
			
			Query query = em.createNativeQuery(sql, Map.class);
			setParameters(query, parameters);
			@SuppressWarnings("unchecked")
			Map<String, Object> record = convertData((Map<String, Object>) query.getSingleResult());
			return record;
		} catch (Exception e) {
		}
		return null;
	}
	
	// -------------------------------------------------------------- executeUpdate
	public synchronized  static int executeUpate(EntityManager em, String sql, Object[] parameters) {
		debug(sql, parameters);
		
		Query query = em.createNativeQuery(sql);
		setParameters(query, parameters);
		return query.executeUpdate();
	}
	
	// -------------------------------------------------------------------------------------- private
	
	protected synchronized  static void setParameters(Query query, Map<String, Object> parameters) {
		if (parameters != null && !parameters.isEmpty()) {
			int i = 1;
			for (Entry<String, Object> e : parameters.entrySet()) {
				query.setParameter(i++, e.getValue());
			}
		}
	}

	private synchronized  static void setParameters(Query query, Object[] parameters) {
		if (parameters != null && parameters.length > 0) {
			int i = 1;
			for (Object param : parameters) {
				query.setParameter(i++, param);
			}
		}
	}

	private synchronized  static Map<String, Object> convertData(Map<String, Object> map) {
		for (Entry<String, Object> e : map.entrySet()) {
			map.put(e.getKey(), convertData(e.getValue()));
		}
		return map;
	}

	private synchronized static Object convertData(Object data) {
		if (data != null) {
			if (data instanceof java.sql.Date || data instanceof Timestamp) {
				data = new Date(((Date) data).getTime());
			}
			if (data instanceof java.lang.String) {
				try {
					data = new String(((String) data).getBytes("Cp943C"), "windows-31j");
				} catch (UnsupportedEncodingException e) {
				}
			}
		}
		return data;
	}
	
	private synchronized  static void debug(String sql,Map<String, Object> parameters) {
		if(isDebug) {
			if (parameters != null && !parameters.isEmpty()) {
				logger.info("parameters:["+toString(parameters)+"]");
			}
			if(StringUtil.hasValue(sql)) {
				logger.info("  sql="+sql);
			}
		}
	}
	
	private synchronized  static void debug(String sql,Object[] parameters) {
		if(isDebug) {
			if (parameters != null && parameters.length > 0) {
				logger.info("parameters:["+toString(parameters)+"]");
			}
			if(StringUtil.hasValue(sql)) {
				logger.info("   sql="+sql);
			}
		}
	}
	
	private synchronized static String toString(Object[] list) {
		String result = "";
		for (Object str : list) {
			if (StringUtil.hasValue(result)) {
				result = result + ",";
			}
			result = result + str;
		}
		return result;
	}
	
	private synchronized static String toString(Map<String, Object> parameters) {
		String result = "";
		for (Entry<String, Object> e : parameters.entrySet()) {
			if (StringUtil.hasValue(result)) {
				result = result + ",";
			}
			result = result + e.getKey()+"="+e.getValue();
		}	
		return result;
	}

}
