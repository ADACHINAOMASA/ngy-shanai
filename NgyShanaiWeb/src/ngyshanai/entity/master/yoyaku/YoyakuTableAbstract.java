package ngyshanai.entity.master.yoyaku;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ngyshanai.entity.oldframework.EntityNoControlBase;

@MappedSuperclass
public abstract class YoyakuTableAbstract extends EntityNoControlBase<YoyakuTableUpdater> {

	private static final long serialVersionUID = 1L;

	@Override
	protected Class<YoyakuTableUpdater> getUpdaterClass() {
		return YoyakuTableUpdater.class;
	}

	@EmbeddedId
	protected YoyakuTableKey key;

	@Column(name="KAIGISHITSU_CD")
	protected String kaigishitsuCd;
	
	@Column(name="YOYAKU_DATE")
	protected Date yoyakuDate;
	
	@Column(name="YOYAKU_BLOCK_START")
	protected String yoyakuBlockStart;
	
	@Column(name="YOYAKU_BLOCK_END")
	protected String yoyakuBlockEnd;
	
	@Column(name="YOYAKUSHA_CD")
	protected String yoyakushaCd;
	
	@Column(name="BIKO")
	protected String biko;
	
	@Column(name="YOYAKUSHA_NAME")
	protected String yoyakushaName;
	
	@Column(name="TEL")
	protected String tel;
	
	@Column(name="IMPORTANCE")
	protected String importance;
	
	@Column(name="ISRESERVED")
	protected String isreserved;
	
	@Column(name="MAISHU_YOYAKU_CD")
	protected String maishuYoyakuCd;
	
	@Column(name="VERSION")
	protected BigDecimal version;

	@Column(name="TOROKUSHA_CD")
	protected String torokushaCd;

	@Column(name="TOROKU_TS")
	@Temporal(TemporalType.TIMESTAMP)
	protected Timestamp torokuTs;

	@Column(name="KOSHINSHA_CD")
	protected String koshinshaCd;

	@Column(name="KOSHIN_TS")
	@Temporal(TemporalType.TIMESTAMP)
	protected Timestamp koshinTs;

	public YoyakuTableAbstract(){
		super();
	}

	public YoyakuTableAbstract(YoyakuTableKey key){
		this.key = key;
	}

	public YoyakuTableKey getKey(){
		return key;
	}

	public void setKey(YoyakuTableKey key){
		this.key = key;
	}

	public String getKaigishitsuCd() {
		return kaigishitsuCd;
	}

	public void setKaigishitsuCd(String kaigishitsuCd) {
		this.kaigishitsuCd = kaigishitsuCd;
	}

	public Date getYoyakuDate() {
		return yoyakuDate;
	}

	public void setYoyakuDate(Date yoyakuDate) {
		this.yoyakuDate = yoyakuDate;
	}

	public String getYoyakuBlockStart() {
		return yoyakuBlockStart;
	}

	public void setYoyakuBlockStart(String yoyakuBlockStart) {
		this.yoyakuBlockStart = yoyakuBlockStart;
	}

	public String getYoyakuBlockEnd() {
		return yoyakuBlockEnd;
	}

	public void setYoyakuBlockEnd(String yoyakuBlockEnd) {
		this.yoyakuBlockEnd = yoyakuBlockEnd;
	}

	public String getYoyakushaCd() {
		return yoyakushaCd;
	}

	public void setYoyakushaCd(String yoyakushaCd) {
		this.yoyakushaCd = yoyakushaCd;
	}

	public String getBiko() {
		return biko;
	}

	public void setBiko(String biko) {
		this.biko = biko;
	}

	public String getYoyakushaName() {
		return yoyakushaName;
	}

	public void setYoyakushaName(String yoyakushaName) {
		this.yoyakushaName = yoyakushaName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getImportance() {
		return importance;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}

	public String getIsreserved() {
		return isreserved;
	}

	public void setIsreserved(String isreserved) {
		this.isreserved = isreserved;
	}

	public String getMaishuYoyakuCd() {
		return maishuYoyakuCd;
	}

	public void setMaishuYoyakuCd(String maishuYoyakuCd) {
		this.maishuYoyakuCd = maishuYoyakuCd;
	}

	public BigDecimal getVersion() {
		return version;
	}

	public void setVersion(BigDecimal version) {
		this.version = version;
	}

	public String getTorokushaCd(){
		return torokushaCd;
	}

	public void setTorokushaCd(String torokushaCd){
		this.torokushaCd = torokushaCd;
	}

	public Timestamp getTorokuTs(){
		return torokuTs;
	}

	public void setTorokuTs(Timestamp torokuTs){
		this.torokuTs = torokuTs;
	}

	public String getKoshinshaCd(){
		return koshinshaCd;
	}

	public void setKoshinshaCd(String koshinshaCd){
		this.koshinshaCd = koshinshaCd;
	}

	public Timestamp getKoshinTs(){
		return koshinTs;
	}

	public void setKoshinTs(Timestamp koshinTs){
		this.koshinTs = koshinTs;
	}

}
