package nis.framework.svf;

import java.io.IOException;

import jp.co.fit.vfreport.Svf;
import nis.framework.properties.SvfProperties;

/**
 * クエリー使用の印刷基底クラス
 */
public class QueryPrint extends FormPrint {

	/**
	 * クエリーファイル名
	 */
	private String queryFile;

	/**
	 * 抽出条件式
	 */
	private String condition;

	/**
	 * 抽出条件モード
	 */
	private int conditionMode;

	/**
	 * コンストラクタ
	 * @param formFile 様式ファイル名
	 * @param queryFile クエリーファイル名
	 * @param outputMode 出力モード
	 */
	protected QueryPrint(String formFile, String queryFile, int outputMode) {
		super(formFile, outputMode);
		//this.queryFile = adapter.getString(SvfProperties.Kies.SVF_QUERY_ROOT_DIR.getKey(),true) + "/" + queryFile;
		this.queryFile = SvfProperties.getSvfQueryRootDir()+ "/" + queryFile;
		
	}

	/**
	 * 印刷実行前の諸設定を行なう。
	 * @param svf SVFオブジェクト
	 */
	public void preparePrint(Svf svf) throws PrintException, IOException {
		//svfAssert(svf.VrSetQuery(adapter.getString(SvfProperties.Kies.SVF_CONNECTION_STRING.getKey(),true), queryFile, 0));
		svfAssert(svf.VrSetQuery(SvfProperties.getSvfConnectionString(), queryFile, 0));
		
		if (condition != null) {
			svfAssert(svf.VrCondition(condition, conditionMode));
		}
		svfAssert(svf.VrReport());
	}

	/**
	 * 印刷するデータが存在するか判定して結果を返す
	 * @return 印刷するデータが存在するとき真を返す
	 */
	public boolean hasPrintData() {
		return queryFile != null;
	}

	/**
	 * 抽出条件を設定する。
	 * @param condition 抽出条件式
	 * @param conditionMode 抽出条件モード (詳細はSVF APIリファレンス参照)
	 */
	protected void setCondition(String condition, int conditionMode) {
		this.condition = condition;
		this.conditionMode = conditionMode;
	}

}
