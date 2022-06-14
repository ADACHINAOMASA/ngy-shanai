package lotdsp.entity.fromnis2015.repo;

import java.util.Map;

/**
 * JPAFindAll用テンプレート
 * @author Kengo-Nagase
 *
 */
public class NisJpaFindAll {

	private String jpql;

	private Map<String, Object> parameters;

	private int maxResult;

	private boolean safe;

	public NisJpaFindAll(String jpql, Map<String, Object> parameters, int maxResult, boolean safe) {
		this.jpql = jpql;
		this.parameters = parameters;
		this.maxResult = maxResult;
		this.safe = safe;
	}

	public String getJpql() {
		return jpql;
	}

	public void setJpql(String jpql) {
		this.jpql = jpql;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}

	public boolean isSafe() {
		return safe;
	}

	public void setSafe(boolean safe) {
		this.safe = safe;
	}

}
