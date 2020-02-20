package nis.framework.sql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import nis.framework.util.bean.BeanUtil;
import nis.framework.util.sql.ResultSetUtil;

// TODO:このパッケージ内は古い仕組みを参考・流用しているので、要見直し

public class NisQueryExecutor {

	@Inject
    private EntityManager em;

	public NisQueryExecutor(){}

	public NisQueryExecutor(EntityManager em){
		this.em = em;
	}

	public <T> List<T> executeQuery(String query, List<Object> parameters, Class<T> resultClass) {
		Query nQuery = em.createNativeQuery(query, Map.class);
		int i = 1;
		for (Object parameter: parameters) {
			nQuery.setParameter(i++, parameter);
		}
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> records = (List<Map<String, Object>>)nQuery.getResultList();

		List<T> results = new ArrayList<T>();
		for(Map<String, Object> record : ResultSetUtil.convertMapData((List<Map<String, Object>>)records)) {
			results.add(BeanUtil.createBean(record, resultClass));
		}

		return results;
	}

	public <T> List<T> executeQuery(NisQuery<T> query) {
		Query nQuery = em.createNativeQuery(query.getSQL(), Map.class);
		int i = 1;
		for (NisParameter parameter: query.getParameters()) {
			nQuery.setParameter(i++, parameter.getValue());
		}
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> records = (List<Map<String, Object>>)nQuery.getResultList();
		List<T> results = new ArrayList<T>();
		for(Map<String, Object> record : records) {
			results.add(query.record(record != null ? ResultSetUtil.convertMapData(record) : new HashMap<String, Object>()));
		}
		return results;
	}

}
