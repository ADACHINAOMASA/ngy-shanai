package lotdsp.domain.logic.kaigiyoyaku;

import javax.inject.Inject;

import lotdsp.common.msg.kaigiyoyaku.YoyakuInfo;
import lotdsp.entity.oldframework.UpdateInfo;
import nis.framework.ejb.logic.Logic;
import nis.framework.ejb.logic.ServiceContext;
import nis.framework.sql.NisQueryExecutor;
import nis.framework.web.session.NisSessionContext;

public class KaigiYoyakuUpdateLogic {

	@Inject
	private NisQueryExecutor queryExecutor;
	
	@Inject
	private ServiceContext svContext;
	
	@Inject
	private NisSessionContext snContext;
	
	@Logic
	public boolean execute(YoyakuInfo info) {
		
		int yoyakuDuplications = queryExecutor.executeQuery(new CheckYoyakuDuplicateQuery(info.getKaigishitsuCd(), info.getYoyakuDate(), info.getYoyakuBlockStart(), info.getYoyakuBlockEnd())).size();
		
		UpdateInfo updateInfo = new UpdateInfo(snContext.getUserProfile());
		
		return true;
	}
}
