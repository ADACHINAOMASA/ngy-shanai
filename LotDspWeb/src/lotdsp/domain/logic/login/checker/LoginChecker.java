package lotdsp.domain.logic.login.checker;

import javax.inject.Inject;

import lotdsp.common.msg.login.LoginInfo;
import lotdsp.entity.master.user.MUser;
import lotdsp.entity.master.user.MUserAccessor;
import nis.framework.ejb.logic.CheckLogic;
import nis.framework.ejb.logic.ServiceContext;

public class LoginChecker extends CommonLoginChecker {

	@Inject
	private ServiceContext svContext;
	
	@CheckLogic
	public boolean check(LoginInfo in) {
		
		MUserAccessor ac = new MUserAccessor();
		MUser entity = ac.find(in.getId(), in.getPassword());
		
        if (entity == null) {
            svContext.getAlerts().addDanger("IDが存在しないか、パスワードが誤っています。");
            return false;
        }
        
        return true;
	}

}
