package lotdsp.entity.master.user;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lotdsp.entity.oldframework.EntityKeyAbstract;

@Embeddable
public class MUserKey extends EntityKeyAbstract {

	private static final long serialVersionUID = 1L;

	@Column(name="USER_CD")
	private String userCd;
	
	@Column(name="PASSWORD")
	private String password;

	public MUserKey(){
		super();
	}

	public MUserKey(String userCd, String password){
		this.userCd = userCd;
		this.password = password;
	}

	public String getUserCd(){
		return userCd;
	}

	public void setUserCd(String userCd){
		this.userCd = userCd;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

