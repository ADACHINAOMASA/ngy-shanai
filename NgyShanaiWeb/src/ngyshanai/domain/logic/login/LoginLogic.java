package ngyshanai.domain.logic.login;

import ngyshanai.common.kbn.Kengen;
import ngyshanai.common.msg.login.LoginInfo;
import ngyshanai.domain.logic.login.checker.LoginChecker;
import nis.framework.auth.AuthoritiesBuilder;
import nis.framework.ejb.logic.Checker;
import nis.framework.ejb.logic.Logic;
import nis.framework.web.user.UserProfile;

public class LoginLogic {

	@Logic(preCheck = @Checker(value = LoginChecker.class) )
	public UserProfile execute(LoginInfo in) {
		AuthoritiesBuilder builder = AuthoritiesBuilder.newModel();
		builder.add(Kengen.KANRISHA.name());

		UserProfile userProfile = createUserProfile(in);
		
		userProfile.setAuthority(builder.build());
		return userProfile;
	}

	private UserProfile createUserProfile(LoginInfo in) {
        UserProfile userProfile = new UserProfile();

        userProfile.setUserId(in.getId());
        userProfile.setUserName(in.getUserName());
        // 権限設定は後回し

        return userProfile;
    }
	
}
