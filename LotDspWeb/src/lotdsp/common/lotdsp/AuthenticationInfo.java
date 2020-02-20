package lotdsp.common.lotdsp;

import nis.framework.dictionary.InputModel;

@InputModel
public class AuthenticationInfo {

	private String ip;
	private String ltno;
	private String knno;
	private String cyno;
	private String linkkey;
	private String mode;
	private String tab;
	private String site;

	private int result;
	private boolean error;
	private String errorMessage;
	private String version;


	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getLtno() {
		return ltno;
	}
	public void setLtno(String ltno) {
		this.ltno = ltno;
	}
	public String getKnno() {
		return knno;
	}
	public void setKnno(String knno) {
		this.knno = knno;
	}
	public String getCyno() {
		return cyno;
	}
	public void setCyno(String cyno) {
		this.cyno = cyno;
	}
	public String getLinkkey() {
		return linkkey;
	}
	public void setLinkkey(String linkkey) {
		this.linkkey = linkkey;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getTab() {
		return tab;
	}
	public void setTab(String tab) {
		this.tab = tab;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}


}
