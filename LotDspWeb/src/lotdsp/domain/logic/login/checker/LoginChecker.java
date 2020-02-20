package lotdsp.domain.logic.login.checker;

import javax.inject.Inject;

import lotdsp.common.msg.login.LoginInfo;
import nis.framework.ejb.logic.CheckLogic;
import nis.framework.ejb.logic.ServiceContext;

public class LoginChecker extends CommonLoginChecker {

	@Inject
	private ServiceContext svContext;
	
	@CheckLogic
	public boolean check(LoginInfo in) {
		if (!checkUser(in.getId(), in.getPassword(), svContext)) {
			return false;
		}
		return true;
	}

}
