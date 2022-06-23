package ngyshanai.entity.master.yoyaku;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import ngyshanai.entity.oldframework.EntityKeyAbstract;

@Embeddable
public class YoyakuTableKey extends EntityKeyAbstract {

	private static final long serialVersionUID = 1L;

	@Column(name="YOYAKU_CD")
	private String yoyakuCd;
	
	public YoyakuTableKey(){
		super();
	}

	public YoyakuTableKey(String yoyakuCd){
		this.yoyakuCd = yoyakuCd;
	}

	public String getYoyakuCd() {
		return yoyakuCd;
	}

	public void setYoyakuCd(String yoyakuCd) {
		this.yoyakuCd = yoyakuCd;
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

