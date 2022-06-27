package ngyshanai.entity.oldframework;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import nis.framework.util.NumberUtil;

@MappedSuperclass
public abstract class EntityTransactionControlAbstract<U> extends EntityAbstract<U> {

	private static final long serialVersionUID = 1L;

	@Column(name = "SAKUSEISHA")
	private String sakuseisha;

	@Column(name = "SAKUSEI_HI")
	private Timestamp sakuseiHi;

	@Column(name = "KOSHINSHA")
	private String koshinsha;

	@Column(name = "KOSHIN_HI")
	private Timestamp koshinHi;

	@Column(name = "VERSION")
	private BigDecimal version;

	public String getSakuseisha() {
		return sakuseisha;
	}

	public void setSakuseisha(String sakuseisha) {
		this.sakuseisha = sakuseisha;
	}

	public Timestamp getSakuseiHi() {
		return sakuseiHi;
	}

	public void setSakuseiHi(Timestamp sakuseiHi) {
		this.sakuseiHi = sakuseiHi;
	}

	public String getKoshinsha() {
		return koshinsha;
	}

	public void setKoshinsha(String koshinsha) {
		this.koshinsha = koshinsha;
	}

	public Timestamp getKoshinHi() {
		return koshinHi;
	}

	public void setKoshinHi(Timestamp koshinHi) {
		this.koshinHi = koshinHi;
	}

	public BigDecimal getVersion() {
		return version;
	}

	protected void control(UpdateInfo info) {
		control(info.getUser(), info.getTime());
	}

	protected void control(String user, Timestamp ts) {
		if (getSakuseisha() == null && getSakuseiHi() == null) {
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
		sakuseisha = user;
		sakuseiHi = ts;
		koshinsha = user;
		koshinHi = ts;
		version = BigDecimal.ONE;
	}

	/**
	 *  増加
	 */
	protected void increment(UpdateInfo info) {
		increment(info.getUser(), info.getTime());
	}

	protected void increment(String user, Timestamp ts) {
		koshinsha = user;
		koshinHi = ts;
		version = (NumberUtil.nvl(version).compareTo(BigDecimal.valueOf(99999)) >= 0) ? BigDecimal.ONE : NumberUtil.nvl(version).add(BigDecimal.ONE);
	}

}
