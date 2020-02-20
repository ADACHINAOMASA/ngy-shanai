package nis.framework.auth;

import java.util.ArrayList;
import java.util.List;

public class Authorities {

	private List<Authority> auths = new ArrayList<Authority>();

	public Authorities(){
	}

	public Authorities(List<Authority> auths) {
		this.auths = auths;
	}

	public boolean has(Authority...auths) {
		List<String> names = new ArrayList<String>();
		for (Authority auth : auths) {
			names.add(auth.getName());
		}
		return has(names.toArray(new String[]{}));
	}

	public boolean has(Enum<?>...auths) {
		List<String> names = new ArrayList<String>();
		for (Enum<?> auth : auths) {
			names.add(auth.name());
		}
		return has(names.toArray(new String[]{}));
	}

	public boolean has(String...names) {
		for (Authority auth : auths) {
			for (String name : names) {
				if (auth.getName().equals(name)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * authsを取得します。
	 * @return auths
	 */
	public List<Authority> getAuths() {
	    return auths;
	}

	/**
	 * authsを設定します。
	 * @param auths auths
	 */
	public void setAuths(List<Authority> auths) {
	    this.auths = auths;
	}

}