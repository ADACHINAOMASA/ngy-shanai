package ngyshanai.entity.master.user;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import ngyshanai.entity.oldframework.EntityKeyAbstract;

@Embeddable
public class MUserKey extends EntityKeyAbstract {

	private static final long serialVersionUID = 1L;

	@Column(name="USER_CD")
	private String userCd;
	
	public MUserKey(){
		super();
	}

	public MUserKey(String userCd){
		this.userCd = userCd;
	}

	public String getUserCd(){
		return userCd;
	}

	public void setUserCd(String userCd){
		this.userCd = userCd;
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

