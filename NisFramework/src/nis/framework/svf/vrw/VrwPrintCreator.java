package nis.framework.svf.vrw;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jp.co.fit.vfreport.Svf;
import jp.co.fit.vfreport.Vrw32;
import nis.framework.svf.Print;
import nis.framework.svf.PrintException;
import nis.framework.svf.PrintSet;


/**
 * 印刷作成クラス
 */
public class VrwPrintCreator {

	/**
	 * プリンタ　PDF出力
	 */
	public static final String PRINTER_PDF = "PDF";

	/**
	 * プリンタ　EMF出力
	 */
	public static final String PRINTER_EMF = "EMF";

	/**
	 * 演算モード(多倍精度十進浮動小数点型演算)
	 */
	public static final int TABAISEIDO_10SHIN_FUDO_SHOSUTEN = 1;

	/**
	 * ログオブジェクト
	 */
	protected Log logger = LogFactory.getLog(getClass());

	/**
	 * 印刷結果
	 */
	private byte[] result;

	/**
	 * 印刷セット
	 */
	private PrintSet printSet = new PrintSet();

	/**
	 * コンストラクタ
	 */
	public VrwPrintCreator() {
	}

	/**
	 * コンストラクタ
	 * @param print 印刷インターフェイス
	 */
	public VrwPrintCreator(Print print) {
		setPrint(print);
	}

	/**
	 * コンストラクタ
	 * @param printSet 印刷セット
	 */
	public VrwPrintCreator(PrintSet printSet) {
		setPrintSet(printSet);
	}

	/**
	 * 印刷結果を取得する。
	 * PDF・EMF印刷時にバイナリを返す。
	 * @return 印刷結果
	 */
	public byte[] getResult() {
		return result;
	}

	/**
	 * 印刷インターフェイスを設定する。
	 * @param print 印刷インターフェイス
	 */
	public void setPrint(Print print) {
		this.printSet.clearPrint();
		addPrint(print);
	}

	/**
	 * 印刷インターフェイスを追加する。
	 * @param print 印刷インターフェイス
	 */
	public void addPrint(Print print) {
		this.printSet.addPrint(print);
	}

	/**
	 * 印刷セットを設定する。
	 * @param printSet 印刷セット
	 */
	public void setPrintSet(PrintSet printSet) {
		this.printSet = printSet;
	}

	/**
	 * 印刷セットをクリアする。
	 */
	public void clearPrint() {
		this.printSet.clearPrint();
		result = null;
	}

	/**
	 * PDF印刷を実行する。
	 */
	public final void executePdfPrint() throws PrintException {
		executePrint(PRINTER_PDF);
	}

	/**
	 * PDF印刷を実行する。(演算モード指定)
	 */
	public final void executePdfPrint(int calcMode) throws PrintException {
		executePrint(PRINTER_PDF, -1, calcMode);
	}

	/**
	 * EMF印刷を実行する。
	 */
	public final void executeEmfPrint() throws PrintException {
		executePrint(PRINTER_EMF);
	}

	/**
	 * 印刷を実行する。
	 * @param printer プリンタ名
	 */
	public final void executePrint(String printer) throws PrintException {
		executePrint(printer, -1, -1);
	}

	/**
	 * 印刷を実行する。
	 * @param printer プリンタ名
	 * @param tray トレイ
	 */
	public final void executePrint(String printer, int tray, int calcMode) throws PrintException {
		if (printSet.getPrintSize() == 0){
			return;
		}

		// バイナリ出力の判定
		boolean binaryOut = printer.equals(PRINTER_PDF) || printer.equals(PRINTER_EMF);

		ClassLoader cl = Vrw32.class.getClassLoader();
		logger.debug("svf-path:" + cl.getResource("jp/co/fit/vfreport/Vrw32.class"));
		logger.debug("prop-path:" + cl.getResource("vfreport.properties"));

		// SVFオブジェクトの作成
		Svf svf = null;
		try {
			svf = new SvfVrwEx();
		}
		catch (IOException x) {
			throw new PrintException(x);
		}

		// 印刷の実行
		try {
			svfAssert(svf.VrInit());

			// 演算モードのセット
			if (calcMode >= 0) {
				svfAssert(svf.VrSetCalcMode(calcMode));
			}

			ByteArrayOutputStream out = null;
			boolean printSuccess = false;
			try {
				svfAssert(svf.VrSetPrinter("", printer));

				// 印刷準備
				Print[] arrayPrint = printSet.getArrayPrint();
				for (int i = 0; i < arrayPrint.length; i++) {
					if (arrayPrint[i].hasPrintData()) {
						preparePrint(svf, arrayPrint[i]);
					}
				}

				// トレイのセット
				if (tray >= 0) {
					svfAssert(svf.VrSetTray(tray));
				}
				// バイナリ出力準備
				if (binaryOut) {
					out = new ByteArrayOutputStream();
					svfAssert(svf.VrSetSpoolFileStream(out));
				}
				// EMF出力準備
				if (printer.equals(PRINTER_EMF)) {
					svfAssert(svf.VrComout("/{SPLM 3}/"));
				}

				printSuccess = true;
			}
			finally {
				svfAssert(svf.VrQuit());

				// バイナリの取り出し
				if (binaryOut && printSuccess) {
					result = out.toByteArray();
					out.close();
				}
			}
		}
		catch (IOException x) {
			throw new PrintException(x);
		}
		finally {
			svf.close();
		}
	}

	/**
	 * 印刷インターフェイスを処理する
	 * @param svf SVFオブジェクト
	 * @param print 印刷インターフェイス
	 */
	private void preparePrint(Svf svf, Print print) throws PrintException, IOException {
		String formFile = print.getFormFile();
		int outputMode = print.getOutputMode();

		// 印刷準備
		svfAssert(svf.VrSetForm(formFile, outputMode));
		print.preparePrint(svf);

		// 印刷実行
		if (outputMode == Print.OUTPUT_MODE_RANDOM) {
			svfAssert(svf.VrEndPage());
		}
		else if (outputMode == Print.OUTPUT_MODE_REPORT_WRITER || outputMode == Print.OUTPUT_MODE_REPORT_WRITER_SORT) {
			svfAssert(svf.VrPrint());
		}
	}

	/**
	 * SVF APIの呼び出し結果をチェックする。
	 * エラーの場合は例外をスローする。
	 * @param returnCode 呼び出し結果
	 */
	protected static void svfAssert(int returnCode) throws PrintException {
		if (returnCode < 0) {
			throw new PrintException("SVFエラー " + returnCode);
		}
	}

}
