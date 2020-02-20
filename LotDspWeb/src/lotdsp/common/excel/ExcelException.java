package lotdsp.common.excel;

import java.io.PrintStream;
import java.io.PrintWriter;

public class ExcelException extends Exception {

	private static final long serialVersionUID = -3758630928756333715L;

	private Throwable cause;

	public ExcelException(String message) {
		super(message);
	}

	public ExcelException(Throwable cause) {
		this.cause = cause;
	}

	public Throwable getCause() {
		return cause;
	}

	public String getMessage() {
		if (cause != null) {
			return cause.getMessage();
		} else {
			return super.getMessage();
		}
	}

	public void printStackTrace() {
		printStackTrace(System.err);
	}

	public void printStackTrace(PrintStream s) {
		printStackTrace(new PrintWriter(s, true));
	}

	public void printStackTrace(PrintWriter w) {
		super.printStackTrace(w);
		if (cause != null) {
			w.print("Caused by: ");
			cause.printStackTrace(w);
		}
	}

}
