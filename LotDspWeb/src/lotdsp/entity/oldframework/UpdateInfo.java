package lotdsp.entity.oldframework;

import java.io.Serializable;
import java.sql.Timestamp;

import nis.framework.oldframework.IRequestMessage;

public class UpdateInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String user;
	private Timestamp time;
	private String form;

	public UpdateInfo() {
		time = new Timestamp(System.currentTimeMillis());
	}

	public UpdateInfo(String user) {
		this();
		this.user = user;
	}

	public UpdateInfo(IRequestMessage msg) {
		this();
		user = msg.getUserId();
		form = msg.getFrontId();
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Timestamp getTime() {
		return time;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}
}
