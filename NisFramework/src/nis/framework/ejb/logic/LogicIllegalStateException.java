package nis.framework.ejb.logic;


public class LogicIllegalStateException extends LogicRuntimeException {

	private static final long serialVersionUID = 1L;

	public LogicIllegalStateException(String arg0, Throwable cause) {
		super(arg0, cause);
	}

	public LogicIllegalStateException(String arg0) {
		super(arg0);
	}

	public LogicIllegalStateException(Throwable cause) {
		super(cause);
	}

}
