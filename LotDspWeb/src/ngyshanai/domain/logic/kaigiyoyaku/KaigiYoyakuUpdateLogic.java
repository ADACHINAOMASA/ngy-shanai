package ngyshanai.domain.logic.kaigiyoyaku;

import java.util.List;

import javax.inject.Inject;

import ngyshanai.common.msg.kaigiyoyaku.YoyakuInfo;
import ngyshanai.entity.master.yoyaku.YoyakuTable;
import ngyshanai.entity.master.yoyaku.YoyakuTableAccessor;
import ngyshanai.entity.master.yoyaku.YoyakuTableUpdaterImpl;
import ngyshanai.entity.oldframework.UpdateInfo;
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
		
		UpdateInfo updateInfo = new UpdateInfo(snContext.getUserProfile());
		
		YoyakuTableAccessor ac = new YoyakuTableAccessor();
		YoyakuTable entity = ac.find(info.getYoyakuCd());
		
		List<YoyakuInfo> infos = queryExecutor.executeQuery(new CheckYoyakuDuplicateQuery(info.getKaigishitsuCd(), info.getYoyakuDate(), info.getYoyakuBlockStart(), info.getYoyakuBlockEnd()));
		
		for (YoyakuInfo yInfo : infos) {
			if (yInfo.getYoyakuCd().equals(info.getYoyakuCd())) {
				infos.remove(infos.indexOf(yInfo));
				break;
			}
		}
		
		if (infos.size() >= 1) {
			svContext.getAlerts().addDanger("他の予約と時間が重複しています。");
			return false;
		}
		
		info.setMaishuYoyakuCd(null);
		
		entity.update(new YoyakuTableUpdaterImpl(info, updateInfo), updateInfo);
		
		return true;
	}
}
