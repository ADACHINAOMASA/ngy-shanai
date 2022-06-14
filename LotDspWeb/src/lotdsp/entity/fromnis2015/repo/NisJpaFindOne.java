package lotdsp.entity.fromnis2015.repo;

import java.util.Map;

/**
 * JpaFindOneテンプレート
 * @author Kengo-Nagase
 *
 */
public class NisJpaFindOne {

	private String jpql;

	private Map<String, Object> parameters;

	private boolean safe;

	public NisJpaFindOne(String jpql, Map<String, Object> parameters, boolean safe) {
		this.jpql = jpql;
		this.parameters = parameters;
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

	public boolean isSafe() {
		return safe;
	}

	public void setSafe(boolean safe) {
		this.safe = safe;
	}

}
