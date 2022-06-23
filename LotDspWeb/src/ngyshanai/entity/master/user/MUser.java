package ngyshanai.entity.master.user;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

import ngyshanai.entity.oldframework.Updatable;
import ngyshanai.entity.oldframework.UpdateInfo;
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
		userMei = updater.getUserMei();
		tel = updater.getTel();
		mailAddress = updater.getMailAddress();
		password = updater.getPassword();
		kengenId = updater.getKengenId();
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

