package ngyshanai.domain.logic.passwordhenko;

import javax.inject.Inject;

import ngyshanai.common.msg.passwordhenko.PasswordHenkoInfo;
import ngyshanai.domain.logic.passwordhenko.checker.PasswordHenkoChecker;
import nis.framework.ejb.logic.Logic;
import nis.framework.ejb.logic.ServiceContext;

public class PasswordHenkoLogic {

	@Inject
	protected PasswordHenkoChecker checker;

	@Inject
	protected ServiceContext svContext;

	@Logic
	public PasswordHenkoInfo execute(PasswordHenkoInfo in) {
		if (!checker.check(in)) {
			in.setHasError(true);
			return in;
		}
		// パスワード更新
		updatePassword(in);

		return in;
	}

	private void updatePassword(PasswordHenkoInfo in) {
		in.setHasError(false);
	}

}
