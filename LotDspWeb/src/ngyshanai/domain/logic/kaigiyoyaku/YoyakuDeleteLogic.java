package ngyshanai.domain.logic.kaigiyoyaku;

import javax.inject.Inject;

import ngyshanai.entity.master.yoyaku.YoyakuTable;
import ngyshanai.entity.master.yoyaku.YoyakuTableAccessor;
import ngyshanai.entity.oldframework.UpdateInfo;
import nis.framework.ejb.logic.Logic;
import nis.framework.ejb.logic.ServiceContext;

public class YoyakuDeleteLogic {

	@Inject
	private ServiceContext svContext;
	
	@Logic
	public boolean execute(String yoyakuCd) {
	
		YoyakuTableAccessor ac = new YoyakuTableAccessor();
		YoyakuTable entity = ac.find(yoyakuCd);
		
		if (entity == null) {
			svContext.getAlerts().addDanger("削除しようとした予約情報は存在しません。既に削除されている可能性があります。");
			return false;
		}
		
		entity.delete(new UpdateInfo());
		
		return true;
	
	}
}
