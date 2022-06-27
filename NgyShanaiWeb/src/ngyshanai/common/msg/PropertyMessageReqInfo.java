package ngyshanai.common.msg;

import java.util.List;

import ngyshanai.common.util.collection.Lists;

public class PropertyMessageReqInfo {

	private List<PropertyMessageInfo> messages = Lists.newArrayList();

	public List<PropertyMessageInfo> getMessages() {
		return messages;
	}

	public void setMessages(List<PropertyMessageInfo> messages) {
		this.messages = messages;
	}

}