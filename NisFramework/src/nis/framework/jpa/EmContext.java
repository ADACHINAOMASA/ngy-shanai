package nis.framework.jpa;

import javax.enterprise.inject.Produces;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import nis.framework.cdi.CdiBeansAccessor;
import nis.framework.properties.AppProperties;

public class EmContext {

	protected Log logger = LogFactory.getLog(getClass());

	private EntityManager em;

	private static final EmContext thisInc = new EmContext();

	public static EntityManager get() {
        return CdiBeansAccessor.get().find(EntityManager.class);
    }

	private EntityManager getEm() {
		String name = getDefaultJpaPersistenceContextName();
		try {
			Context context = new InitialContext();
			em = (EntityManager) context.lookup("java:comp/env/" + name);
		} catch (javax.naming.NamingException e) {
			e.printStackTrace();
			logger.info("JPA Name: " + name);
		}
		return em;
	}

	@Produces
	public EntityManager getEntityManager() {
		return getEm();
	}

	private String getDefaultJpaPersistenceContextName() {
		return AppProperties.getJpaPersistenceContextDefaultName();
	}

}
