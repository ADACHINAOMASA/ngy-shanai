package lotdsp.domain.logic.kaigiyoyaku;

import javax.inject.Inject;

import lotdsp.common.msg.kaigiyoyaku.YoyakuInfo;
import lotdsp.entity.master.yoyaku.YoyakuTable;
import lotdsp.entity.master.yoyaku.YoyakuTableAccessor;
import lotdsp.entity.master.yoyaku.YoyakuTableUpdaterImpl;
import lotdsp.entity.oldframework.UpdateInfo;
import nis.framework.ejb.logic.Logic;
import nis.framework.ejb.logic.ServiceContext;
import nis.framework.sql.NisQueryExecutor;
import nis.framework.web.session.NisSessionContext;

public class KaigiYoyakuLogic {

	@Inject
	private NisQueryExecutor queryExecutor;
	
	@Inject
	private ServiceContext svContext;
	
	@Inject
	private NisSessionContext snContext;
	
	@Logic
	public boolean execute(YoyakuInfo info) {
		
		if (queryExecutor.executeQuery(new CheckYoyakuDuplicateQuery(info.getKaigishitsuCd(), info.getYoyakuDate(), info.getYoyakuBlockStart(), info.getYoyakuBlockEnd())).size() != 0) {
			svContext.getAlerts().addDanger("他の予約と時間が重複しています。");
			return false;
		}
		
		UpdateInfo updateInfo = new UpdateInfo(snContext.getUserProfile());
		
		YoyakuTableAccessor ac = new YoyakuTableAccessor();
		YoyakuTable entity = ac.find(info.getKaigishitsuCd(), info.getYoyakuDate(), info.getYoyakuBlockStart());
		
		if (entity == null || entity.notExist()) {
			entity = ac.create(info.getKaigishitsuCd(), info.getYoyakuDate(), info.getYoyakuBlockStart());
		}
		
		entity.update(new YoyakuTableUpdaterImpl(info, updateInfo), updateInfo);
		
		return true;
	}
}
