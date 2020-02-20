package nis.framework.svf;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * 印刷例外クラス
 */
public class PrintException extends Exception {

	private static final long serialVersionUID = 7730857468024655643L;

	/**
	 * 例外の原因
	 */
	private Throwable cause;

	/**
	 * コンストラクタ
	 * @param message 詳細メッセージ
	 */
	public PrintException(String message) {
		super(message);
	}

	/**
	 * コンストラクタ
	 * @param cause 例外の原因
	 */
	public PrintException(Throwable cause) {
		this.cause = cause;
	}

	/**
	 * 例外の原因を返す。
	 */
	public Throwable getCause() {
		return cause;
	}

	/**
	 * 詳細メッセージを返す。
	 */
	public String getMessage() {
		if (cause != null) {
			return cause.getMessage();
		}
		else {
			return super.getMessage();
		}
	}

	/**
	 * トレース出力する。
	 */
	public void printStackTrace() {
		printStackTrace(System.err);
	}

	/**
	 * トレース出力する。
	 * @param 出力ストリーム
	 */
	public void printStackTrace(PrintStream s) {
		printStackTrace(new PrintWriter(s, true));
	}

	/**
	 * トレース出力する。
	 * @param 出力ライター
	 */
	public void printStackTrace(PrintWriter w) {
		super.printStackTrace(w);
		if (cause != null) {
			w.print("Caused by: ");
			cause.printStackTrace(w);
		}
	}

}
