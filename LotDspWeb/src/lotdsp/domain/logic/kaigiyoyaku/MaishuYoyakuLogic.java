package lotdsp.domain.logic.kaigiyoyaku;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.RandomStringUtils;

import lotdsp.common.msg.kaigiyoyaku.YoyakuInfo;
import lotdsp.entity.master.yoyaku.YoyakuTable;
import lotdsp.entity.master.yoyaku.YoyakuTableAccessor;
import lotdsp.entity.master.yoyaku.YoyakuTableUpdaterImpl;
import lotdsp.entity.oldframework.UpdateInfo;
import nis.framework.ejb.logic.Logic;
import nis.framework.ejb.logic.ServiceContext;
import nis.framework.sql.NisQueryExecutor;
import nis.framework.web.session.NisSessionContext;

public class MaishuYoyakuLogic {

	@Inject
	private ServiceContext svContext;

	@Inject
	private NisQueryExecutor queryExecutor;
	
	@Inject
	private NisSessionContext snContext;
	
	@Logic
	public boolean execute(Date maishuEnd, YoyakuInfo info) {
		
boolean result = true;
		
		String maishuYoyakuId = "";
		String kaigishitsuCd = info.getKaigishitsuCd();
		String yoyakuBlockStart = info.getYoyakuBlockStart();
		String yoyakuBlockEnd = info.getYoyakuBlockEnd();
		UpdateInfo updateInfo = new UpdateInfo(snContext.getUserProfile());
		
		LocalDate yoyakuDate = info.getYoyakuDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate maishuEndDate = maishuEnd.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		int yoyakuTimes = (int)ChronoUnit.DAYS.between(yoyakuDate,maishuEndDate) / 7;
		
		for (int i = 0; i <= yoyakuTimes; i++) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(info.getYoyakuDate());
			cal.add(Calendar.DAY_OF_MONTH, 7 * i);
			
			Date date = cal.getTime();
			
			//予約重複チェック（DBの予約件数が多くなるとかなり重くなりそうなので良い方法を考えたい）
			if (queryExecutor.executeQuery(new CheckYoyakuDuplicateQuery(kaigishitsuCd, date, yoyakuBlockStart, yoyakuBlockEnd)).size() != 0) {
				svContext.getAlerts().addDanger("他の予約と時間が重複しています。");
				result = false;
				break;
			}
		}
			
		if (result == false) {
			return result;
		}
		
		for (int i = 0;;i++) {
			maishuYoyakuId = RandomStringUtils.randomAlphanumeric(10);
			List<YoyakuInfo> infos = queryExecutor.executeQuery(new SearchMaishuYoyakuQuery(maishuYoyakuId));
			if (infos.size() == 0) {
				info.setMaishuYoyakuId(maishuYoyakuId);
				break;
			}
			//無限ループ対策（無理やり）
			if(i >= 10) {
				svContext.getAlerts().addDanger("毎週予約機能が無限ループに陥っている可能性があります。プログラムを確認してください。");
				result = false;
				break;
			}
		}
		
		if (result == false) {
			return result;
		}
		
		for (int j = 0; j <= yoyakuTimes; j++) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(info.getYoyakuDate());
			
			if (j != 0) {
				cal.add(Calendar.DAY_OF_MONTH, 7);
			}

			Date date = cal.getTime();
			info.setYoyakuDate(date);
			
			YoyakuTableAccessor ac = new YoyakuTableAccessor();
			YoyakuTable entity = ac.find(kaigishitsuCd, date, yoyakuBlockStart);
			
			if (entity == null || entity.notExist()) {
				entity = ac.create(kaigishitsuCd, date, yoyakuBlockStart);
			}
			
			entity.update(new YoyakuTableUpdaterImpl(info, updateInfo), updateInfo);
		}
		
		return result;
		
	}
}
