package lotdsp.domain.logic.kaigiyoyaku;

import java.util.Date;

import javax.inject.Inject;

import lotdsp.entity.master.yoyaku.YoyakuTable;
import lotdsp.entity.master.yoyaku.YoyakuTableAccessor;
import lotdsp.entity.oldframework.UpdateInfo;
import nis.framework.ejb.logic.Logic;
import nis.framework.ejb.logic.ServiceContext;

public class YoyakuDeleteLogic {

	@Inject
	private ServiceContext svContext;
	
	@Logic
	public boolean execute(String kaigishitsuCd, Date yoyakuDate, String yoyakuBlockStart) {
	
		YoyakuTableAccessor ac = new YoyakuTableAccessor();
		YoyakuTable entity = ac.find(kaigishitsuCd, yoyakuDate, yoyakuBlockStart);
		
		if (entity == null) {
			svContext.getAlerts().addDanger("削除しようとした予約情報は存在しません。既に削除されている可能性があります。");
			return false;
		}
		
		entity.delete(new UpdateInfo());
		
		return true;
	
	}
}
