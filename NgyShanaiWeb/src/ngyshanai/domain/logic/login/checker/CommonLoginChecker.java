package ngyshanai.domain.logic.login.checker;

import nis.framework.ejb.logic.ServiceContext;
import nis.framework.properties.Messages;
import nis.framework.util.StringUtil;

public class CommonLoginChecker {

	public boolean checkUser(String userId, String password, ServiceContext svContext) {

		if (StringUtil.hasNoValue(userId)) {
			svContext.getAlerts().addDanger(Messages.of("error.common.required", "ユーザID"));
			return false;
		}

		if (StringUtil.hasNoValue(password)) {
			svContext.getAlerts().addDanger(Messages.of("error.common.required", "パスワード"));
			return false;
		}

		return true;
	}

}
