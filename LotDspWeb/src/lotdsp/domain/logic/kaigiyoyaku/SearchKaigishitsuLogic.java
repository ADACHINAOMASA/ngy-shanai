package lotdsp.domain.logic.kaigiyoyaku;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import lotdsp.common.msg.kaigiyoyaku.KaigishitsuInfo;
import lotdsp.common.msg.kaigiyoyaku.YoyakuInfo;
import nis.framework.ejb.logic.Logic;
import nis.framework.sql.NisQueryExecutor;

public class SearchKaigishitsuLogic {

	@Inject
	private NisQueryExecutor queryExecutor;
	
	@Logic
	public List<KaigishitsuInfo> execute(Date hizuke) {
		
		//会議室予約のコマ数（8時から19時までの30分刻みで24コマ）
		final int yoyakuBlockSu =24; 
				
		List<KaigishitsuInfo> kaigishitsuInfos = queryExecutor.executeQuery(new SearchKaigishitsuQuery());
		
		List<YoyakuInfo> yoyakuInfos = new ArrayList<YoyakuInfo>();
		
		// 予約情報を格納するための空の入れ物（yoyakuInfos）を作る
		for (KaigishitsuInfo a: kaigishitsuInfos) {
			for(int i = 1; i <= yoyakuBlockSu; i++) {
				YoyakuInfo yInfo = new YoyakuInfo();
				yInfo.setKaigishitsuCd(a.getKaigishitsuCd());
				yInfo.setYoyakuBlockStart(Integer.toString(i));
				yInfo.setYoyakuBlockEnd(Integer.toString(i));
				yoyakuInfos.add(yInfo);
			}
		}
		
		List<YoyakuInfo> dbYoyakuInfo = queryExecutor.executeQuery(new SearchYoyakuQuery(hizuke));
		
		// dbYoyakuInfo（指定した日付の予約情報）を上で作った入れ物に当てはめていく

		for (YoyakuInfo b: dbYoyakuInfo) {
			for (int i = 0; i < yoyakuInfos.size(); i++) {
				YoyakuInfo c = yoyakuInfos.get(i);
				int yoyakuBlock = Integer.parseInt(c.getYoyakuBlockStart());
				if (b.getKaigishitsuCd().equals(c.getKaigishitsuCd()) && yoyakuBlock >= Integer.parseInt(b.getYoyakuBlockStart()) && yoyakuBlock <= Integer.parseInt(b.getYoyakuBlockEnd())) {
					yoyakuInfos.set(i, b);
				}
			}
		}

		// 上で作った予約情報を、会議室ごとに割り振っていく
		int index = 0;

		for (KaigishitsuInfo a: kaigishitsuInfos) {
			List<YoyakuInfo> infos = new ArrayList<YoyakuInfo>();
			for(int i = 0; i < yoyakuBlockSu; i++) {
				infos.add(yoyakuInfos.get(index * yoyakuBlockSu + i));
			}
			a.setYoyakuInfo(infos);
			index ++;
		}

		return kaigishitsuInfos;
	}
}
