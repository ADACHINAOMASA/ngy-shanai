package nis.framework.jpa;

import javax.enterprise.inject.Produces;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import nis.framework.properties.AppProperties;

public class EmContext {

	protected Log logger = LogFactory.getLog(getClass());

	private EntityManager em;

	private static final EmContext thisInc = new EmContext();

	public static EmContext get() {

		return thisInc;
	}

	private EmContext() {
		String name = getDefaultJpaPersistenceContextName();
		try {
			Context context = new InitialContext();
			em = (EntityManager) context.lookup("java:comp/env/" + name);
		} catch (javax.naming.NamingException e) {
			e.printStackTrace();
			logger.info("JPA Name: " + name);
		}
	}

	@Produces
	public EntityManager getEntityManager() {
		return em;
	}

	private String getDefaultJpaPersistenceContextName() {
		return AppProperties.getJpaPersistenceContextDefaultName();
	}

}
