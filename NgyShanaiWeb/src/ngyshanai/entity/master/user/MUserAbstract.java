package ngyshanai.entity.master.user;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ngyshanai.entity.oldframework.EntityNoControlBase;

@MappedSuperclass
public abstract class MUserAbstract extends EntityNoControlBase<MUserUpdater> {

	private static final long serialVersionUID = 1L;

	@Override
	protected Class<MUserUpdater> getUpdaterClass() {
		return MUserUpdater.class;
	}

	@EmbeddedId
	protected MUserKey key;

	@Column(name="USER_CD")
	protected String userCd;

	@Column(name="USER_NAME")
	protected String userName;

	@Column(name="TEL")
	protected String tel;
	
	@Column(name="MAIL_ADDRESS")
	protected String mailAddress;
	
	@Column(name="PASSWORD")
	protected String password;

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

	public String getUserCd(){
		return userCd;
	}

	public void setUserCd(String userCd){
		this.userCd = userCd;
	}

	public String getUserName(){
		return userName;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getTel(){
		return tel;
	}

	public void setTel(String tel){
		this.tel = tel;
	}
	
	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

