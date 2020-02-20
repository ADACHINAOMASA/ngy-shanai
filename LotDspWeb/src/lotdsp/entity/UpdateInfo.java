package lotdsp.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import nis.framework.web.user.UserProfile;

public class UpdateInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userId;
	private Timestamp time;
	private String form;

	public UpdateInfo() {
		time = new Timestamp(System.currentTimeMillis());
	}

	public UpdateInfo(String user) {
		this();
		this.userId = user;
	}

	public UpdateInfo(UserProfile msg) {
		this();
		userId = msg.getUserId();
	}

	public String getUser() {
		return userId;
	}

	public void setUser(String user) {
		this.userId = user;
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
