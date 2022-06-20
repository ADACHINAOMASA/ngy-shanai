package lotdsp.entity.master.yoyaku;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lotdsp.entity.oldframework.EntityKeyAbstract;

@Embeddable
public class YoyakuTableKey extends EntityKeyAbstract {

	private static final long serialVersionUID = 1L;

	@Column(name="YOYAKU_ID")
	private String yoyakuId;
	
	public YoyakuTableKey(){
		super();
	}

	public YoyakuTableKey(String yoyakuId){
		this.yoyakuId = yoyakuId;
	}

	public String getYoyakuId() {
		return yoyakuId;
	}

	public void setYoyakuId(String yoyakuId) {
		this.yoyakuId = yoyakuId;
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

