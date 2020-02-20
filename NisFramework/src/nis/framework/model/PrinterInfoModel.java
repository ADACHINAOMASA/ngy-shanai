package nis.framework.model;

/**
 * プリンタ情報メッセージクラス。
 * @author Toshiyuki-Ichikawa
 */
public class PrinterInfoModel extends JobMessage {

	private static final long serialVersionUID = -8297797463182460494L;

	/**
	 * フォームID
	 */
	private String formId;

	/**
	 * 印刷モード
	 */
	private String printMode;

	/**
	 * プリンタ名
	 */
	private String printerMei;

	/**
	 * トレイ
	 */
	private int tray;

	/**
	 * パス
	 */
	private String path;

	/**
	 * コンストラクタ
	 */
	public PrinterInfoModel() {
	}

	/**
	 * フォームIDを取得する。
	 * @return フォームID
	 */
	public String getFormId() {
		return formId;
	}

	/**
	 * フォームIDを設定する。
	 * @param formId フォームID
	 */
	public void setFormId(String formId) {
		this.formId = formId;
	}

	/**
	 * 印刷モードを取得する。
	 * @return 印刷モード
	 */
	public String getPrintMode() {
		return printMode;
	}

	/**
	 * 印刷モードを設定する。
	 * @param printMode 印刷モード
	 */
	public void setPrintMode(String printMode) {
		this.printMode = printMode;
	}

	/**
	 * プリンタ名を取得する。
	 * @return プリンタ名
	 */
	public String getPrinterMei() {
		return printerMei;
	}

	/**
	 * プリンタ名を設定する。
	 * @param printerMei プリンタ名
	 */
	public void setPrinterMei(String printerMei) {
		this.printerMei = printerMei;
	}

	/**
	 * トレイを取得する。
	 * @return トレイ
	 */
	public int getTray() {
		return tray;
	}

	/**
	 * トレイを設定する。
	 * @param tray トレイ
	 */
	public void setTray(int tray) {
		this.tray = tray;
	}

	/**
	 * パスを取得する。
	 * @return パス
	 */
	public String getPath() {
		return path;
	}

	/**
	 * パスを設定する。
	 * @param path パス
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * PDF印刷かどうかを返す。
	 */
	public boolean isPdfPrint() {
		return printMode == null || printMode.equals("0");
	}

	/**
	 * ネットワーク直接印刷かどうかを返す。
	 */
	public boolean isNetworkDirectPrint() {
		return printMode.equals("1");
	}

	/**
	 * ローカル直接印刷かどうかを返す。
	 */
	public boolean isLocalDirectPrint() {
		return printMode.equals("2");
	}

	/**
	 * ローカル選択印刷かどうかを返す。
	 */
	public boolean isLocalSelectPrint() {
		return printMode.equals("3");
	}

	/**
	 * ディスク保存印刷かどうかを返す。
	 */
	public boolean isDiskSavePrint() {
		return printMode.equals("4");
	}
}
