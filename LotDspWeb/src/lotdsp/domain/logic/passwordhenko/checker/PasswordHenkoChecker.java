package lotdsp.domain.logic.passwordhenko.checker;

import javax.inject.Inject;

import lotdsp.common.msg.passwordhenko.PasswordHenkoInfo;
import lotdsp.domain.logic.login.checker.CommonLoginChecker;
import nis.framework.util.StringUtil;
import nis.framework.ejb.logic.CheckLogic;
import nis.framework.ejb.logic.ServiceContext;
import nis.framework.properties.Messages;

public class PasswordHenkoChecker extends CommonLoginChecker {

	@Inject
	private ServiceContext svContext;

	@CheckLogic
	public boolean check(PasswordHenkoInfo in) {

		if (!checkUser(in.getUserId(), in.getPassword(), svContext)) {
			return false;
		}

		if (!checkNewPassword(in, svContext)) {
			return false;
		}

		return true;
	}

	public boolean checkNewPassword(PasswordHenkoInfo in, ServiceContext svContext) {

		if (StringUtil.hasNoValue(in.getNewPassword())) {
			svContext.getAlerts().addDanger(Messages.of("error.common.required", "新しいパスワード"));
			return false;
		}

		if (StringUtil.isEqual(in.getPassword(), in.getNewPassword())) {
			in.setNewPassword(null);
			in.setNewPasswordConfirm(null);
			svContext.getAlerts().addDanger(Messages.of("message.common.no-change", "新しいパスワード", "現在のパスワード"));
			return false;
		}

		if (StringUtil.hasNoValue(in.getNewPasswordConfirm())) {
			svContext.getAlerts().addDanger(Messages.of("error.common.required", "新しいパスワード（確認用）"));
			return false;
		}

		if (StringUtil.isNotEqual(in.getNewPassword(), in.getNewPasswordConfirm())) {
			in.setNewPasswordConfirm(null);
			svContext.getAlerts().addDanger(Messages.of("message.common.not-match", "新しいパスワード", "新しいパスワード（確認用）"));
			return false;
		}

		return true;
	}
}
