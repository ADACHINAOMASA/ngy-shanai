package ngyshanai.entity.fromnis2015.repo;

import java.util.List;

/**
 * JpaFindNativeテンプレート
 * @author Kengo-Nagase
 *
 */
public class NisJpaFindNative {

	private String sql;

	private List<Object> parameters;

	private int maxResult;

	public NisJpaFindNative(String sql, List<Object> parameters, int maxResult) {
		this.sql = sql;
		this.parameters = parameters;
		this.maxResult = maxResult;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public List<Object> getParameters() {
		return parameters;
	}

	public void setParameters(List<Object> parameters) {
		this.parameters = parameters;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}

}
