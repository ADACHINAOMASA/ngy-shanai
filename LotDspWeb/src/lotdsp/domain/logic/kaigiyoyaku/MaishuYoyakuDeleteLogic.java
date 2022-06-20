package lotdsp.domain.logic.kaigiyoyaku;

import java.util.Calendar;
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
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		Date today = cal.getTime();
		
		for (YoyakuInfo info : maishuYoyakuInfos) {
			
			if (info.getYoyakuDate().compareTo(today) < 0) {
				continue;
			}
			
			YoyakuTable entity = ac.find(info.getYoyakuId());
			
			entity.delete(new UpdateInfo());
		}
		
		return true;
	}
}
