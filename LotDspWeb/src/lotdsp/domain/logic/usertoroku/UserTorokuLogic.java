package lotdsp.domain.logic.usertoroku;

import java.util.List;

import javax.inject.Inject;

import lotdsp.common.msg.user.UserInfo;
import lotdsp.common.msg.usertoroku.UserTorokuInfo;
import lotdsp.entity.master.user.MUser;
import lotdsp.entity.master.user.MUserAccessor;
import lotdsp.entity.master.user.MUserKey;
import lotdsp.entity.master.user.MUserUpdaterImpl;
import lotdsp.entity.oldframework.UpdateInfo;
import nis.framework.ejb.logic.Logic;
import nis.framework.ejb.logic.ServiceContext;
import nis.framework.sql.NisQueryExecutor;

public class UserTorokuLogic {

	@Inject
	private NisQueryExecutor queryExecutor;

	@Inject
	protected ServiceContext svContext;

	@Logic
	public boolean execute(UserTorokuInfo in) {
		
		List<UserInfo> infos = queryExecutor.executeQuery(new GetUserQuery(in.getUserId()));
		
		if (infos.size() >= 1) {
			svContext.getAlerts().addDanger("ユーザーIDが重複します。入力し直してください。");
			return false;
		}
		
		MUserAccessor ac = new MUserAccessor();
		MUser entity = ac.create(new MUserKey(in.getUserId()));
		
		UpdateInfo updateInfo = new UpdateInfo();
		
		UserInfo info = new UserInfo();
		info.setUserCd(in.getUserId());
		info.setPassword(in.getPassword());
		
		entity.update(new MUserUpdaterImpl(info, updateInfo), updateInfo);
		
		return true;
	}

}
