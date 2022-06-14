package lotdsp.entity.oldframework;

import java.math.BigDecimal;
import java.util.Date;

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

	@Column(name="TOROKU_YMDHMS")
	@Temporal(TemporalType.TIMESTAMP)
	private Date torokuYmdhms;

	@Column(name="KOSHINSHA_CD")
	private String koshinshaCd;

	@Column(name="KOSHIN_YMDHMS")
	@Temporal(TemporalType.TIMESTAMP)
	private Date koshinYmdhms;

	@Column(name="VERSION")
	private BigDecimal version;

	@Column(name="SAKUJO_FLG")
	private String sakujoFlg;

	public void setSakujoFlg(String sakujoFlg) {
		this.sakujoFlg = sakujoFlg;
	}

	public String getTorokushaCd() {
		return torokushaCd;
	}

	public void setTorokushaCd(String torokushaCd) {
		this.torokushaCd = torokushaCd;
	}

	public Date getTorokuYmdhms() {
		return torokuYmdhms;
	}

	public void setTorokuYmdhms(Date torokuYmdhms) {
		this.torokuYmdhms = torokuYmdhms;
	}

	public String getKoshinshaCd() {
		return koshinshaCd;
	}

	public void setKoshinCd(String koshinshaCd) {
		this.koshinshaCd = koshinshaCd;
	}

	public Date getKoshinYmdhms() {
		return koshinYmdhms;
	}

	public void setKoshinYmdhms(Date koshinYmdhms) {
		this.koshinYmdhms = koshinYmdhms;
	}

	public BigDecimal getVersion() {
		return version;
	}

	public String getSakujoFlg() {
		return sakujoFlg;
	}

//	public boolean checkVersion(BigDecimal version) {
//		if(version==null)return false;
//		return version.compareTo(getVersion()) == 0;
//	}

	protected void control(UpdateInfo info) {
		control(info.getUser(), info.getTime());
	}

	protected void control(String user, Date ts) {
//		if (getVersion() == null) {
		if (getTorokushaCd() == null && getTorokuYmdhms() == null) {
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

	protected void validate(String user, Date ts) {
		torokushaCd = user;
		torokuYmdhms = ts;
		koshinshaCd = user;
		koshinYmdhms = ts;
		version = BigDecimal.ONE;
		sakujoFlg = "0";
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
	protected void invalidate(String user, Date ts) {
		koshinshaCd = user;
		koshinYmdhms = ts;
		version = (version.compareTo(BigDecimal.valueOf(99999)) >= 0)?
				BigDecimal.ONE : version.add(BigDecimal.ONE);
		sakujoFlg = "1";
	}



	/**
	 *  増加
	 */
	protected void increment(UpdateInfo info) {
		increment(info.getUser(), info.getTime());
	}

	protected void increment(String user, Date ts) {
		koshinshaCd = user;
		koshinYmdhms = ts;
		version = (version.compareTo(BigDecimal.valueOf(99999)) >= 0)?
				BigDecimal.ONE : version.add(BigDecimal.ONE);
	}

}
