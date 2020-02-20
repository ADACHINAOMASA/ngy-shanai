package nis.framework.cdi;

import java.util.Set;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;

public class CdiBeansAccessor {

	@Inject
	private BeanManager bm;

	@SuppressWarnings("unchecked")
	public <T> T find(Class<T> beanClass) {
		if (bm == null) {
			bm = getBm();
		}
		return (T)getResolvedBean(beanClass);
	}

	private Object getResolvedBean(Class<?> beanClass) {
		Set<Bean<?>> beans = bm.getBeans(beanClass);
		Bean<?> bean = bm.resolve(beans);
		CreationalContext<?> cc = bm.createCreationalContext(bean);
		return bm.getReference(bean, beanClass, cc);
	}

	private BeanManager getBm() {
		try {
			Context context = new InitialContext();
			return (BeanManager) context.lookup("java:comp/BeanManager");
		} catch (javax.naming.NamingException e) {
	    	System.out.println("BeanManager Not Found");
			throw new IllegalStateException(e);
		}
	}
}
