package ngyshanai.entity.oldframework;

import java.sql.Timestamp;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class EntityNoControlBase<U> extends EntityAbstract<U> {

	private static final long serialVersionUID = 1L;

	protected void control(UpdateInfo info) {
	}


	protected void control(String user, Timestamp ts) {
	}

}
