package lotdsp.common.msg;

import java.util.Map;

import lotdsp.common.util.collection.Maps;

public class MeishoInfo {

	private String type;
	
	private Map<String, Object> parameters = Maps.newHashMap();
	
	private Map<String, Object> results = Maps.newHashMap();

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	public Map<String, Object> getResults() {
		return results;
	}

	public void setResults(Map<String, Object> results) {
		this.results = results;
	}

}
