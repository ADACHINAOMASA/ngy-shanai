package lotdsp.entity.master.user;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

import lotdsp.entity.oldframework.Updatable;
import lotdsp.entity.oldframework.UpdateInfo;
import nis.framework.oldframework.NumberUtil;

@Entity
@Table(name="M_USER")
public class MUser extends MUserAbstract 
       implements Updatable<MUserUpdater> {

	private static final long serialVersionUID = 1L;

	public MUser(){
		super();
	}

	public MUser(MUserKey key){
		super(key);
	}
	@Override
	public void update(MUserUpdater updater, UpdateInfo info) {
		kaishaCd = updater.getKaishaCd();
		userCd = updater.getUserCd();
		userMei = updater.getUserMei();
		tel = updater.getTel();
		kanrishaKbn = updater.getKanrishaKbn();
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

