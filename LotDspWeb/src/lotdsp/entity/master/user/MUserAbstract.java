package lotdsp.entity.master.user;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lotdsp.entity.oldframework.EntityNoControlBase;

@MappedSuperclass
public abstract class MUserAbstract extends EntityNoControlBase<MUserUpdater> {

	private static final long serialVersionUID = 1L;

	@Override
	protected Class<MUserUpdater> getUpdaterClass() {
		return MUserUpdater.class;
	}

	@EmbeddedId
	protected MUserKey key;

	@Column(name="KAISHA_CD")
	protected String kaishaCd;

	@Column(name="USER_CD")
	protected String userCd;

	@Column(name="USER_MEI")
	protected String userMei;

	@Column(name="TEL")
	protected String tel;
	
	@Column(name="KANRISHA_KBN")
	protected String kanrishaKbn;

	@Column(name="VERSION")
	protected BigDecimal version;

	@Column(name="TOROKUSHA_CD")
	protected String torokushaCd;

	@Column(name="TOROKU_TS")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date torokuTs;

	@Column(name="KOSHINSHA_CD")
	protected String koshinshaCd;

	@Column(name="KOSHIN_TS")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date koshinTs;

	public MUserAbstract(){
		super();
	}

	public MUserAbstract(MUserKey key){
		this.key = key;
	}

	public MUserKey getKey(){
		return key;
	}

	public void setKey(MUserKey key){
		this.key = key;
	}

	public String getKaishaCd(){
		return kaishaCd;
	}

	public void setKaishaCd(String kaishaCd){
		this.kaishaCd = kaishaCd;
	}

	public String getUserCd(){
		return userCd;
	}

	public void setUserCd(String userCd){
		this.userCd = userCd;
	}

	public String getUserMei(){
		return userMei;
	}

	public void setUserMei(String userMei){
		this.userMei = userMei;
	}

	public String getTel(){
		return tel;
	}

	public void setTel(String tel){
		this.tel = tel;
	}
	
	public String getKanrishaKbn() {
		return kanrishaKbn;
	}

	public void setKanrishaKbn(String kanrishaKbn) {
		this.kanrishaKbn = kanrishaKbn;
	}

	public BigDecimal getVersion(){
		return version;
	}

	public void setVersion(BigDecimal version){
		this.version = version;
	}

	public String getTorokushaCd(){
		return torokushaCd;
	}

	public void setTorokushaCd(String torokushaCd){
		this.torokushaCd = torokushaCd;
	}

	public Date getTorokuTs(){
		return torokuTs;
	}

	public void setTorokuTs(Date torokuTs){
		this.torokuTs = torokuTs;
	}

	public String getKoshinshaCd(){
		return koshinshaCd;
	}

	public void setKoshinshaCd(String koshinshaCd){
		this.koshinshaCd = koshinshaCd;
	}

	public Date getKoshinTs(){
		return koshinTs;
	}

	public void setKoshinTs(Date koshinTs){
		this.koshinTs = koshinTs;
	}

}

