package nis.framework.ejb.logic;

import java.io.PrintStream;
import java.io.PrintWriter;

public class LogicInterceptException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Throwable cause = null;

	public LogicInterceptException(String arg0) {
		super(arg0);
	}

	public LogicInterceptException(String arg0, Throwable cause) {
		super(arg0);
		this.cause = cause;
	}

	public LogicInterceptException(Throwable cause) {
		super(cause.getMessage());
		this.cause = cause;
	}

	public Throwable getCause() {
		return cause;
	}

	public String getMessage() {
		String message = super.getMessage();
		if (cause != null) {
			if (message != null) {
				message += ":" + cause.getMessage();
			}
			else {
				message = cause.getMessage();
			}
		}
		return message;
	}

	public void printStackTrace() {
		printStackTrace(System.err);
	}

	public void printStackTrace(PrintStream stream) {
		printStackTrace(new PrintWriter(stream, true));
	}

	public void printStackTrace(PrintWriter writer) {
		super.printStackTrace(writer);
		if (cause != null) {
			writer.print("Caused by: ");
			cause.printStackTrace(writer);
		}
	}

}
