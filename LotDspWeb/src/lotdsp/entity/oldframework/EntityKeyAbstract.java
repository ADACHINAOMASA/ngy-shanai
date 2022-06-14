package lotdsp.entity.oldframework;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import nis.framework.oldframework.PropertyReflector;

public abstract class EntityKeyAbstract implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean equals(Object o) {
		if (o instanceof EntityKeyAbstract) {
			EntityKeyAbstract x = (EntityKeyAbstract) o;

			EqualsBuilder b = new EqualsBuilder();
			for (String name : PropertyReflector.getPropertyNames(this)) {
				b.append(PropertyReflector.getProperty(this, name), PropertyReflector.getProperty(x, name));
			}
			return b.isEquals();
		}
		return false;
	}

	@Override
	public int hashCode() {
		HashCodeBuilder b = new HashCodeBuilder();
		for (String name : PropertyReflector.getPropertyNames(this)) {
			b.append(PropertyReflector.getProperty(this, name));
		}
		return b.toHashCode();
	}

	@Override
	public String toString() {
		ToStringBuilder b = new ToStringBuilder(this);
		for (String name : PropertyReflector.getPropertyNames(this)) {
			b.append(name, PropertyReflector.getProperty(this, name));
		}
		return b.toString();

	}

}
