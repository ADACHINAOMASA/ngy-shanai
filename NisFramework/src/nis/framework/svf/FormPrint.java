package nis.framework.svf;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jp.co.fit.vfreport.Svf;
import nis.framework.properties.SvfProperties;


/**
 * 印刷基底クラス
 */
public abstract class FormPrint implements Print {

	/**
	 * ログオブジェクト
	 */
	protected Log logger = LogFactory.getLog(this.getClass());

//	/**
//	 * プロパティアダプター
//	 */
//	protected static final PropertyAdapter adapter = new PropertyAdapter() {
//		protected String getPropertyName() {
//			return SvfProperties.getFileName();
//		}
//	};

	/**
	 * 様式ファイル名
	 */
	private String formFile;

	/**
	 * 出力モード
	 */
	private int outputMode;

	/**
	 * コンストラクタ
	 * @param formFile 様式ファイル名
	 * @param outputMode 出力モード
	 */
	protected FormPrint(String formFile, int outputMode) {
		//this.formFile = adapter.getString(SvfProperties.Kies.SVF_FORM_ROOT_DIR.getKey(),true) + "/" + formFile;
		this.formFile = SvfProperties.getSvfFormRootDir() + "/" + formFile;
		
		this.outputMode = outputMode;
	}

	/**
	 * 様式ファイル名を返す。
	 * @return 様式ファイル名
	 */
	public String getFormFile() {
		return formFile;
	}

	/**
	 * 出力モードを返す。
	 * @return 出力モード
	 */
	public int getOutputMode() {
		return outputMode;
	}

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
	public abstract void preparePrint(Svf svf) throws PrintException, IOException;

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
