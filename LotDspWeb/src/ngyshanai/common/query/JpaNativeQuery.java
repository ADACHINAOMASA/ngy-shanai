package ngyshanai.common.query;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Leang-Say
 */
public class JpaNativeQuery {

	protected Log logger = LogFactory.getLog(getClass());

	@Inject
	private EntityManager em;

	public JpaNativeQuery() {
	}

	public JpaNativeQuery(EntityManager em) {
		this.em = em;
	}

	// ----------------------------------------------------------- single result

	public <T> T getSingleResult(String sql, Class<T> resultClass) {
		return getSingleResult(sql, null, resultClass);
	}

	public <T> T getSingleResult(String sql, Object[] parameters, Class<T> resultClass) {
		validate(sql, resultClass);
		return JpaNativeQuerySupport.getSingleResult(em, sql, parameters, resultClass);
	}

	public Map<String, Object> getSingleResultAsMap(String sql) {
		return getSingleResultAsMap(sql, null);
	}

	public Map<String, Object> getSingleResultAsMap(String sql, Object[] parameters) {
		validate(sql);
		return JpaNativeQuerySupport.getSingleResultAsMap(em, sql, parameters);
	}

	// ----------------------------------------------------------- result list

	public <T> List<T> getResultList(String sql, Class<T> resultClass) {
		return getResultList(sql, null, resultClass);
	}

	public <T> List<T> getResultList(String sql, Object[] parameters, Class<T> resultClass) {
		validate(sql, resultClass);
		return JpaNativeQuerySupport.getResultList(em, sql, parameters, resultClass);
	}

	public List<Map<String, Object>> getResultListAsMap(String sql) {
		return getResultListAsMap(sql, new Object[] {});
	}

	public List<Map<String, Object>> getResultListAsMap(String sql, Object[] parameters) {
		validate(sql);
		return JpaNativeQuerySupport.getResultListAsMap(em, sql, parameters);
	}

	public List<Object[]> getResultListAsObject(String sql, Object[] parameters) {
		validate(sql);
		return JpaNativeQuerySupport.getResultListAsObject(em, sql, parameters);
	}

	public List<Map<String, Object>> getResultListAsMap(String sql, Map<String, Object> parameters) {
		validate(sql);
		return JpaNativeQuerySupport.getResultListAsMap(em, sql, parameters);
	}

	// ----------------------------------------------------------- executeUpate
	public int executeUpate(String sql, Object[] parameters) {
		validate(sql);
		return JpaNativeQuerySupport.executeUpate(em, sql, parameters);
	}
	
	public int executeUpate(String sql) {
		return executeUpate(sql, null);
	}

	// ----------------------------------------------------------- private

	private <T> boolean validate(String sql, Class<T> resultClass) {
		validate(sql);
		if (resultClass == null) {
			throw new IllegalArgumentException("resultClass can not be null.");
		}
		return true;
	}

	private boolean validate(String sql) {
		if (!hasValue(sql)) {
			throw new IllegalArgumentException("sql can not be null or empty.(sql=" + sql + ")");
		}
		return true;
	}

	private boolean hasValue(String val) {
		return (val != null && !"".equals(val.trim()));
	}

}
