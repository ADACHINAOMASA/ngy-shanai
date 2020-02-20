package nis.framework.svf;

import java.io.IOException;

import jp.co.fit.vfreport.Svf;


/**
 * 印刷インターフェイス
 */
public interface Print {

	/**
	 * 出力モード　ランダムモード
	 */
	public static final int OUTPUT_MODE_RANDOM = 1;

	/**
	 * 出力モード　レポートライターモード (ソートなし)
	 */
	public static final int OUTPUT_MODE_REPORT_WRITER = 4;

	/**
	 * 出力モード　レポートライターモード (ソートあり)
	 */
	public static final int OUTPUT_MODE_REPORT_WRITER_SORT = 5;

	/**
	 * 様式ファイル名を返す。
	 * @return 様式ファイル名
	 */
	public String getFormFile();

	/**
	 * 出力モードを返す。
	 * @return 出力モード
	 */
	public int getOutputMode();

	/**
	 * 印刷するデータが存在するか判定して結果を返す
	 * @return 印刷するデータが存在するとき真を返す
	 */
	public boolean hasPrintData();

	/**
	 * 印刷実行前の諸設定を行なう。
	 * 以下のSVF APIは呼び出す必要はありません。
	 * <ul>
	 * <li>初期化および後処理 - VrInit, VrQuit
	 * <li>プリンタの設定 - VrSetPrinter
	 * <li>様式ファイルの設定 - VrSetForm
	 * <li>印刷実行 - VrEndPage, VrPrint
	 * <li>PDF印刷のためのスプール設定 - VrSetSpoolFileStream
	 * </ul>
	 * @param svf SVFオブジェクト
	 */
	public void preparePrint(Svf svf) throws PrintException, IOException;

}
