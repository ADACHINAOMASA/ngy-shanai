package lotdsp.entity.oldframework;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import nis.framework.util.NumberUtil;

@MappedSuperclass
public abstract class EntityControlBase<U> extends EntityAbstract<U> {

	private static final long serialVersionUID = 1L;

	@Column(name = "CREATE_USER")
	private String createUser;

	@Column(name = "CREATE_TS")
	private Timestamp createTs;

	@Column(name = "UPDATE_USER")
	private String updateUser;

	@Column(name = "UPDATE_TS")
	private Timestamp updateTs;

	@Column(name = "VERSION")
	private BigDecimal version;

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Timestamp getCreateTs() {
		return createTs;
	}

	public void setCreateTs(Timestamp createTs) {
		this.createTs = createTs;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Timestamp getUpdateTs() {
		return updateTs;
	}

	public void setUpdateTs(Timestamp updateTs) {
		this.updateTs = updateTs;
	}

	public void setVersion(BigDecimal version) {
		this.version = version;
	}

	public BigDecimal getVersion() {
		return version;
	}


	protected void control(UpdateInfo info) {
		control(info.getUser(), info.getTime());
	}

	protected void control(String user, Timestamp ts) {
		if (getCreateUser() == null && getCreateTs() == null) {
			validate(user, ts);
		}
		else {
			increment(user, ts);
		}
	}

	/**
	 *  論理的に有効にする
	 *  特に意識して呼び出さなくて良い。controlを使えば良い
	 */
	protected void validate(UpdateInfo info) {
		validate(info.getUser(), info.getTime());
	}

	protected void validate(String user, Timestamp ts) {
		createUser = user;
		createTs = ts;
		updateUser = user;
		updateTs = ts;
		version = BigDecimal.ONE;
	}

	/**
	 *  増加
	 */
	protected void increment(UpdateInfo info) {
		increment(info.getUser(), info.getTime());
	}

	protected void increment(String user, Timestamp ts) {
		updateUser = user;
		updateTs = ts;
		version = (NumberUtil.nvl(version).compareTo(BigDecimal.valueOf(99999)) >= 0) ? BigDecimal.ONE : NumberUtil.nvl(version).add(BigDecimal.ONE);
	}

}
