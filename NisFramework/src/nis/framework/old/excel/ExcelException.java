/*
 * �쐬��: 2007/08/16
 */
package nis.framework.old.excel;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * �G�N�Z����O
 */
public class ExcelException extends Exception {

	private static final long serialVersionUID = -3758630928756333715L;

	/**
	 * ��O�̌���
	 */
	private Throwable cause;

	/**
	 * �R���X�g���N�^
	 */
	public ExcelException(String message) {
		super(message);
	}

	/**
	 * �R���X�g���N�^
	 */
	public ExcelException(Throwable cause) {
		this.cause = cause;
	}

	/**
	 * ��O�̌�����Ԃ�
	 */
	public Throwable getCause() {
		return cause;
	}

	/**
	 * �ڍ׃��b�Z�[�W��Ԃ�
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
	 * �g���[�X�o�͂���
	 */
	public void printStackTrace() {
		printStackTrace(System.err);
	}

	/**
	 * �g���[�X�o�͂���
	 */
	public void printStackTrace(PrintStream s) {
		printStackTrace(new PrintWriter(s, true));
	}

	/**
	 * �g���[�X�o�͂���
	 */
	public void printStackTrace(PrintWriter w) {
		super.printStackTrace(w);
		if (cause != null) {
			w.print("Caused by: ");
			cause.printStackTrace(w);
		}
	}

}
