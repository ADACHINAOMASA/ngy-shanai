package nis.framework.ejb.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;

import nis.framework.web.alert.UIAlerts;
import nis.framework.web.response.ResponseStatus;

@RequestScoped
public class ServiceContext implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<String, Object> container = new HashMap<String, Object>();

	private UIAlerts alerts = new UIAlerts();

	private ResponseStatus status = ResponseStatus.SUCCESS;

	public void putContainerValue(String key, String value) {
		container.put(key, value);
	}

	public Object getContainerValue(String key) {
		return container.get(key);
	}

	public UIAlerts getAlerts() {
		return alerts;
	}

	public ResponseStatus getStatus() {
		return status;
	}

	public void toStatusSuccess() {
		status = ResponseStatus.SUCCESS;
	}

	public void toStatusError() {
		status = ResponseStatus.ERROR;
	}

	public void toStatusWarning() {
		status = ResponseStatus.WARNING;
	}

	public boolean isStatusSuccess() {
		return status == ResponseStatus.SUCCESS;
	}

	public boolean isStatusError() {
		return status == ResponseStatus.ERROR;
	}

	public boolean isStatusWarning() {
		return status == ResponseStatus.WARNING;
	}

}
