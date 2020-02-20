package nis.framework.ejb.logic.checker;

import javax.inject.Inject;

import nis.framework.auth.Authorities;
import nis.framework.ejb.logic.Auth;
import nis.framework.ejb.logic.Permission;
import nis.framework.web.session.NisSessionContext;

/**
 * デフォルト権限制御用チェッカー
 * <p>
 *  権限制御でデフォルトで使用されるチェッカー<br>
 *  設定されたAuthをそのユーザーが持っていない場合、エラーとする
 * </p>
 * @author Kengo-Nagase
 *
 */
public class DefaultPermissionChecker {

	@Inject
	private NisSessionContext nisSnContext;

	public boolean check(Permission permission) {
		Authorities authority = nisSnContext.getUserProfile().getAuthority();
		for (Auth auth : permission.value()) {
			if (authority.has(auth.value())) {
				return true;
			}
		}
		return false;
	}

}
