package lotdsp.entity.master.yoyaku;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

import lotdsp.entity.oldframework.Updatable;
import lotdsp.entity.oldframework.UpdateInfo;
import nis.framework.oldframework.NumberUtil;

@Entity
@Table(name="YOYAKU_TABLE")
public class YoyakuTable extends YoyakuTableAbstract 
       implements Updatable<YoyakuTableUpdater> {

	private static final long serialVersionUID = 1L;

	public YoyakuTable(){
		super();
	}

	public YoyakuTable(YoyakuTableKey key){
		super(key);
	}
	@Override
	public void update(YoyakuTableUpdater updater, UpdateInfo info) {
		kaigishitsuCd = updater.getKaigishitsuCd();
		yoyakuDate = updater.getYoyakuDate();
		yoyakuBlockStart = updater.getYoyakuBlockStart();
		yoyakuBlockEnd = updater.getYoyakuBlockEnd();
		yoyakushaCd = updater.getYoyakushaCd();
		biko = updater.getBiko();
		yoyakushaName = updater.getYoyakushaName();
		tel = updater.getTel();
		importance = updater.getImportance();
		isreserved = "1";
		maishuYoyakuId = updater.getMaishuYoyakuId();
		if (NumberUtil.isBigger(NumberUtil.nvl(version), BigDecimal.ZERO)) {
			version = NumberUtil.addNvl(version, BigDecimal.ONE);
			koshinshaCd = info.getUser();
			koshinTs = info.getTime();
		} else {
			version = BigDecimal.ONE;
			torokushaCd = info.getUser();
			torokuTs = info.getTime();
		}
//		control(info);
	}

	@Override
	public void delete(UpdateInfo info){
		remove();
	}

}

