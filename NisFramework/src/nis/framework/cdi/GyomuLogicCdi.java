package nis.framework.cdi;

import java.lang.reflect.Field;
import java.util.Set;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;

import nis.framework.ejb.Executable;

public class GyomuLogicCdi {

	private static final GyomuLogicCdi thisInc = new GyomuLogicCdi();

	private GyomuLogicCdi() {
	}

	public static GyomuLogicCdi get() {
		return thisInc;
	}

	// 入力値を可変長に出来ないものか

	@SuppressWarnings("unchecked")
	public <I,O> Executable<I, O> resolve(Class<? extends Executable<I, O>> logic) {
		Object bean = resolveByCdi(logic);
		Field[] fields = bean.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			// ��͌��ʂ��L���b�V������d�g�݂͕t����B
			Field field = fields[i];
			Inject injano = field.getAnnotation(Inject.class);
			if (injano == null) {
			}
			else {
				Object fieldBean = resolveByCdi(field.getType());
				try {
					field.setAccessible(true);
					field.set(bean, fieldBean);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return (Executable<I, O>)bean;
	}

	public Object resolveLogic(Class<?> logic) {
		Object bean = resolveByCdi(logic);
		Field[] fields = bean.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			// ��͌��ʂ��L���b�V������d�g�݂͕t����B
			Field field = fields[i];
			Inject injano = field.getAnnotation(Inject.class);
			if (injano == null) {
			}
			else {
				Object fieldBean = resolveByCdi(field.getType());
				try {
					field.setAccessible(true);
					field.set(bean, fieldBean);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return bean;
	}

	public Object resolveByCdi(@SuppressWarnings("rawtypes") Class logic) {
		BeanManager bm = getBm();
		Set<Bean<?>> beans = bm.getBeans(logic);
//        for (Bean<?> bean : beans) {
//            System.out.println(bean.getBeanClass().getName());
//        }
		Bean<?> bean = bm.resolve(beans);
		CreationalContext<?> cc = bm.createCreationalContext(bean);
		return bm.getReference(bean, logic, cc);
	}

	private BeanManager getBm() {
		try {
			Context context = new InitialContext();
			return (BeanManager) context.lookup("java:comp/BeanManager");
		} catch (javax.naming.NamingException e) {
	    	System.out.println("BeanManager��������܂���ł����B");
			//throw new IllegalArgumentException(e);
		}
		return null;
	}
}
