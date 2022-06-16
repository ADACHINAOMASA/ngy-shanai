package lotdsp.entity.master.yoyaku;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lotdsp.entity.oldframework.EntityKeyAbstract;

@Embeddable
public class YoyakuTableKey extends EntityKeyAbstract {

	private static final long serialVersionUID = 1L;

	@Column(name="KAIGISHITSU_CD")
	private String kaigishitsuCd;
	
	@Column(name="YOYAKU_DATE")
	private Date yoyakuDate;
	
	@Column(name="YOYAKU_BLOCK_START")
	private String yoyakuBlockStart;

	public YoyakuTableKey(){
		super();
	}

	public YoyakuTableKey(String kaigishitsuCd, Date yoyakuDate, String yoyakuBlockStart){
		this.kaigishitsuCd = kaigishitsuCd;
		this.yoyakuDate = yoyakuDate;
		this.yoyakuBlockStart = yoyakuBlockStart;
	}

	public String getkaigishitsuCd(){
		return kaigishitsuCd;
	}

	public void setkaigishitsuCd(String kaigishitsuCd){
		this.kaigishitsuCd = kaigishitsuCd;
	}

	public Date getyoyakuDate() {
		return yoyakuDate;
	}

	public void setyoyakuDate(Date yoyakuDate) {
		this.yoyakuDate = yoyakuDate;
	}

	public String getYoyakuBlockStart() {
		return yoyakuBlockStart;
	}

	public void setYoyakuBlockStart(String yoyakuBlockStart) {
		this.yoyakuBlockStart = yoyakuBlockStart;
	}

	@Override
	public String toString() {
		return org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString(this);
	}
	@Override
	public int hashCode() {
		return org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode(this);
	}
	@Override
	public boolean equals(Object obj) {
		return org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals(this, obj);
	}

}

