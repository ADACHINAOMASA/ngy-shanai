package nis.framework.model;

public class PrintResultMessage extends JobMessage {

	private static final long serialVersionUID = 7251274879374564993L;

	public static final int NONE = -1;
	public static final int PDF = 0;
	public static final int WDP = 1;
	public static final int WDD = 2;
	public static final int PDF_OPEN = 9;

	/**
	 * PDF名
	 */
	private String pdfName;

	/**
	 * 印刷結果
	 */
	private byte[] result;

	/**
	 * 印刷結果の種類
	 */
	private int resultType;

	/**
	 * コンストラクタ
	 */
	public PrintResultMessage() {
	}

	/**
	 * PDF名を取得する。
	 * @return PDF名
	 */
	public String getPdfName() {
		return pdfName;
	}

	/**
	 * PDF名を設定する。
	 * @param result PDF名
	 */
	public void setPdfName(String pdfName) {
		this.pdfName = pdfName;
	}

	/**
	 * 印刷結果を取得する。
	 * @return 印刷結果
	 */
	public byte[] getResult() {
		return result;
	}

	/**
	 * 印刷結果を設定する。
	 * @param result 印刷結果
	 */
	public void setResult(byte[] result) {
		this.result = result;
	}

	/**
	 * 印刷結果の種類を取得する。
	 * @return 印刷結果の種類
	 */
	public int getResultType() {
		return resultType;
	}

	/**
	 * 印刷結果の種類を設定する。
	 * @param resultType
	 */
	public void setResultType(int resultType) {
		this.resultType = resultType;
	}

	/**
	 * 印刷結果のサイズを取得する。
	 * @return 印刷結果のサイズ
	 */
	public long getSize() {
		if (result == null) {
			return 0;
		}
		return result.length;
	}

}
