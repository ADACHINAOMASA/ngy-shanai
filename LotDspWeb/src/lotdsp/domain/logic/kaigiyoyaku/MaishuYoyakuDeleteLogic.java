package lotdsp.domain.logic.kaigiyoyaku;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import lotdsp.common.msg.kaigiyoyaku.YoyakuInfo;
import lotdsp.entity.master.yoyaku.YoyakuTable;
import lotdsp.entity.master.yoyaku.YoyakuTableAccessor;
import lotdsp.entity.oldframework.UpdateInfo;
import nis.framework.ejb.logic.Logic;
import nis.framework.sql.NisQueryExecutor;

public class MaishuYoyakuDeleteLogic {

	@Inject
	private NisQueryExecutor queryExecutor;
	
	@Logic
	public boolean execute(String kaigishitsuCd, Date yoyakuDate, String yoyakuBlockStart, String maishuYoyakuId) {
	
		List<YoyakuInfo> maishuYoyakuInfos = queryExecutor.executeQuery(new SearchMaishuYoyakuQuery(maishuYoyakuId));
		
		YoyakuTableAccessor ac = new YoyakuTableAccessor();
		
		for (YoyakuInfo info : maishuYoyakuInfos) {
			YoyakuTable entity = ac.find(kaigishitsuCd, info.getYoyakuDate(), yoyakuBlockStart);
			
			if (entity == null) {
				continue;
			}
			
			entity.delete(new UpdateInfo());
		}
		
		return true;
	}
}
