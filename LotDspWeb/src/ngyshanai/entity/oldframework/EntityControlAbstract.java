package ngyshanai.entity.oldframework;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("rawtypes")
@MappedSuperclass
public abstract class EntityControlAbstract extends EntityAbstract {

	private static final long serialVersionUID = 1L;

	@Column(name="TOROKUSHA_CD")
	private String torokushaCd;

	@Column(name="TOROKU_TS")
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp torokuTs;

	@Column(name="KOSHINSHA_CD")
	private String koshinshaCd;

	@Column(name="KOSHIN_TS")
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp koshinTs;

	@Column(name="VERSION")
	private BigDecimal version;

	public String getTorokushaCd() {
		return torokushaCd;
	}

	public void setTorokushaCd(String torokushaCd) {
		this.torokushaCd = torokushaCd;
	}

	public Timestamp getTorokuTs() {
		return torokuTs;
	}

	public void setTorokuTs(Timestamp torokuTs) {
		this.torokuTs = torokuTs;
	}

	public String getKoshinshaCd() {
		return koshinshaCd;
	}

	public void setKoshinCd(String koshinshaCd) {
		this.koshinshaCd = koshinshaCd;
	}

	public Timestamp getKoshinTs() {
		return koshinTs;
	}

	public void setKoshinTs(Timestamp koshinTs) {
		this.koshinTs = koshinTs;
	}

	public BigDecimal getVersion() {
		return version;
	}

//	public boolean checkVersion(BigDecimal version) {
//		if(version==null)return false;
//		return version.compareTo(getVersion()) == 0;
//	}

	protected void control(UpdateInfo info) {
		control(info.getUser(), info.getTime());
	}

	protected void control(String user, Timestamp ts) {
//		if (getVersion() == null) {
		if (getTorokushaCd() == null && getTorokuTs() == null) {
			validate(user, ts);
		}
		else {
			increment(user, ts);
		}
	}

	/**
	 *  有効
	 */
	protected void validate(UpdateInfo info) {
		validate(info.getUser(), info.getTime());
	}

	protected void validate(String user, Timestamp ts) {
		torokushaCd = user;
		torokuTs = ts;
		koshinshaCd = user;
		koshinTs = ts;
		version = BigDecimal.ONE;
	}

	/**
	 * @param user
	 * @param ts
	 */
	protected void invalidate(UpdateInfo info) {
		invalidate(info.getUser(), info.getTime());
	}

	/**
	 * @param user
	 * @param ts
	 */
	protected void invalidate(String user, Timestamp ts) {
		koshinshaCd = user;
		koshinTs = ts;
		version = (version.compareTo(BigDecimal.valueOf(99999)) >= 0)?
				BigDecimal.ONE : version.add(BigDecimal.ONE);
	}



	/**
	 *  増加
	 */
	protected void increment(UpdateInfo info) {
		increment(info.getUser(), info.getTime());
	}

	protected void increment(String user, Timestamp ts) {
		koshinshaCd = user;
		koshinTs = ts;
		version = (version.compareTo(BigDecimal.valueOf(99999)) >= 0)?
				BigDecimal.ONE : version.add(BigDecimal.ONE);
	}

}
