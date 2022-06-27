package ngyshanai.domain.logic.login.checker;

import java.util.List;

import javax.inject.Inject;

import ngyshanai.common.msg.login.LoginInfo;
import ngyshanai.common.msg.user.UserInfo;
import nis.framework.ejb.logic.CheckLogic;
import nis.framework.ejb.logic.ServiceContext;
import nis.framework.sql.NisQueryExecutor;

public class LoginChecker extends CommonLoginChecker {

	@Inject
	private NisQueryExecutor queryExecutor;
	
	@Inject
	private ServiceContext svContext;
	
	@CheckLogic
	public boolean check(LoginInfo in) {
		
		List<UserInfo> infos = queryExecutor.executeQuery(new LoginCheckQuery(in.getId(), in.getPassword()));
		
        if (infos.size() == 0) {
            svContext.getAlerts().addDanger("IDが存在しないか、パスワードが誤っています。");
            return false;
        }
        
        return true;
	}

}
