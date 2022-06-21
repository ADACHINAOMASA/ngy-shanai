package ngyshanai.domain.logic.kaigiyoyaku;

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

public class KaigiYoyakuLogic {

	@Inject
	private NisQueryExecutor queryExecutor;
	
	@Inject
	private ServiceContext svContext;
	
	@Inject
	private NisSessionContext snContext;
	
	@Logic
	public boolean execute(YoyakuInfo info) {
		
		boolean result = true;
		
		YoyakuTableAccessor ac = new YoyakuTableAccessor();
		
		// 予約時間帯の重複チェック
		if (queryExecutor.executeQuery(new CheckYoyakuDuplicateQuery(info.getKaigishitsuCd(), info.getYoyakuDate(), info.getYoyakuBlockStart(), info.getYoyakuBlockEnd())).size() != 0) {
			svContext.getAlerts().addDanger("他の予約と時間が重複しています。");
			return false;
		}
		
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
			return result;
		}
		
		UpdateInfo updateInfo = new UpdateInfo(snContext.getUserProfile());
		
		YoyakuTable entity = ac.create(info.getYoyakuId());
		
		entity.update(new YoyakuTableUpdaterImpl(info, updateInfo), updateInfo);
		
		return result;
	}
}
