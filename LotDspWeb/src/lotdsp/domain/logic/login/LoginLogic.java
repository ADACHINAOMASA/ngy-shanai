package lotdsp.domain.logic.login;

import lotdsp.common.kbn.Kengen;
import lotdsp.common.msg.login.LoginInfo;
import lotdsp.domain.logic.login.checker.LoginChecker;
import nis.framework.auth.AuthoritiesBuilder;
import nis.framework.ejb.logic.Checker;
import nis.framework.ejb.logic.Logic;
import nis.framework.web.user.UserProfile;

public class LoginLogic {

	@Logic(preCheck = @Checker(value = LoginChecker.class) )
	public UserProfile execute(LoginInfo in) {
		AuthoritiesBuilder builder = AuthoritiesBuilder.newModel();
		builder.add(Kengen.KANRISHA.name());

		UserProfile userProfile = new UserProfile();
		
		userProfile.setAuthority(builder.build());
		return userProfile;
	}


}
