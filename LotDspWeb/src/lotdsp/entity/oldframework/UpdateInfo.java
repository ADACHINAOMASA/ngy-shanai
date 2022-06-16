package lotdsp.entity.oldframework;

import java.io.Serializable;
import java.sql.Timestamp;

import nis.framework.web.user.UserProfile;

public class UpdateInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String user;
	private Timestamp time;

	public UpdateInfo() {
		time = new Timestamp(System.currentTimeMillis());
	}

	public UpdateInfo(String user) {
		this();
		this.user = user;
	}

	public UpdateInfo(UserProfile msg) {
		this();
		user = msg.getUserId();
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
}
