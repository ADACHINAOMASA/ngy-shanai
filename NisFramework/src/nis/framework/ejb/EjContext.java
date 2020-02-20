package nis.framework.ejb;

import javax.ejb.SessionContext;
import javax.naming.Context;
import javax.naming.InitialContext;

public class EjContext {
	
	private static final EjContext thisInc = new EjContext();
	
	public static EjContext get() {
		return thisInc;
	}

	private EjContext() {
	}
	
	/**
	 * EJB�̃Z�b�V�����R���e�L�X�g�擾
	 * @Resource��Jersey���ɒ�`�����邽�߁Awebsphere���̂c�h�ł͗��p�s�ɂȂ��Ă���B
	 * �Z�b�V�������J�n����Ă����ԂŌĂяo�����ƁB
	 * �Z�b�V�������J�n����Ă��Ȃ���null���Ԃ�
	 * �E�̂悤�ȏ�����́~�@private SessionContext context = EjContext.get().getSessionContext();
	 */
	public SessionContext getSessionContext() {
		try {
			Context context = new InitialContext();
			return (SessionContext) context.lookup("java:comp/EJBContext");
		} catch (javax.naming.NamingException e) {
	    	System.out.println("SessionContext��������܂���ł����B");
			//throw new IllegalArgumentException(e);
		}
		return null;
	}
	
}
