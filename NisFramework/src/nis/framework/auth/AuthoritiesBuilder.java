package nis.framework.auth;

import java.util.ArrayList;
import java.util.List;

public class AuthoritiesBuilder {

	private List<Authority> auths = new ArrayList<Authority>();

	private AuthoritiesBuilder(){}

	public static AuthoritiesBuilder newModel() {
		return new AuthoritiesBuilder();
	}

	public AuthoritiesBuilder add(Authority...auths) {
		for (Authority auth : auths) {
			this.auths.add(auth);
		}
		return this;
	}

	public AuthoritiesBuilder add(String...auths) {
		List<Authority> addAuths = new ArrayList<Authority>();
		for (String auth : auths) {
			addAuths.add(new Authority(auth));
		}
		return add(addAuths.toArray(new Authority[]{}));
	}

	public AuthoritiesBuilder add(Enum<?>...auths) {
		List<Authority> addAuths = new ArrayList<Authority>();
		for (Enum<?> auth : auths) {
			addAuths.add(new Authority(auth.name()));
		}
		return add(addAuths.toArray(new Authority[]{}));
	}

	public Authorities build() {
		return new Authorities(auths);
	}

}
