package ngyshanai.domain.logic.kaigiyoyaku;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.RandomStringUtils;

import ngyshanai.common.msg.kaigiyoyaku.YoyakuInfo;
import ngyshanai.entity.master.yoyaku.YoyakuTable;
import ngyshanai.entity.master.yoyaku.YoyakuTableAccessor;
import ngyshanai.entity.master.yoyaku.YoyakuTableUpdaterImpl;
import ngyshanai.entity.oldframework.UpdateInfo;
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
		
		// 全体的にDBの予約件数が多くなったらかなり重くなりそうなので良い方法を考えたい
		
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
			
			// 予約重複チェック
			if (queryExecutor.executeQuery(new CheckYoyakuDuplicateQuery(kaigishitsuCd, date, yoyakuBlockStart, yoyakuBlockEnd)).size() != 0) {
				svContext.getAlerts().addDanger("他の予約と時間が重複しています。");
				result = false;
				break;
			}
		}
			
		if (result == false) {
			return result;
		}
		
		// 毎週予約IDの作成
		for (int i = 0; i <= 10; i++) {
			maishuYoyakuId = RandomStringUtils.randomAlphanumeric(10);
			List<YoyakuInfo> infos = queryExecutor.executeQuery(new SearchMaishuYoyakuQuery(maishuYoyakuId));
			if (infos.size() == 0) {
				info.setMaishuYoyakuId(maishuYoyakuId);
				break;
			}
			//毎週予約IDが見つからなかったとき
			if(i >= 10) {
				svContext.getAlerts().addDanger("重複しない毎週予約IDが見つかりませんでした。予約データを整理してください。");
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
			
			// 予約ID作成
			for (int i = 0; i <= 10; i++) {
				String yoyakuId = RandomStringUtils.randomAlphanumeric(10);
				YoyakuTable chofuku = ac.find(yoyakuId);
				if (chofuku == null || chofuku.notExist()) {
					info.setYoyakuId(yoyakuId);
					break;
				}
				//予約IDが見つからなかったとき
				if(i >= 10) {
					svContext.getAlerts().addDanger("重複しない予約IDが見つかりませんでした。予約データを整理してください。");
					result = false;
					break;
				}
			}
			
			 if (result == false) {
				 break;
			 }
			
			YoyakuTable entity = ac.create(info.getYoyakuId());
			
			entity.update(new YoyakuTableUpdaterImpl(info, updateInfo), updateInfo);
		}
		
		return result;
		
	}
}
