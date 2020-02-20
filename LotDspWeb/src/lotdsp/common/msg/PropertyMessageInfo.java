package lotdsp.common.msg;

import java.util.List;

import lotdsp.common.util.collection.Lists;

public class PropertyMessageInfo {

	private String key;

	private List<String> params = Lists.newArrayList();

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<String> getParams() {
		return params;
	}

	public void setParams(List<String> params) {
		this.params = params;
	}

}
