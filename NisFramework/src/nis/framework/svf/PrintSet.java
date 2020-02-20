package nis.framework.svf;

import java.util.ArrayList;

/**
 * 印刷セット
 */
public class PrintSet {

	/**
	 * 印刷リスト
	 */
	private ArrayList<Print> array = new ArrayList<Print>();

	/**
	 * コンストラクタ
	 */
	public PrintSet() {
	}

	/**
	 * 印刷インターフェイスを追加する。
	 * @param print 印刷インターフェイス
	 */
	public void addPrint(Print print) {
		array.add(print);
	}

	/**
	 * 全ての印刷インターフェイスを返す。
	 * @return 印刷インターフェイス
	 */
	public Print[] getArrayPrint() {
		return array.toArray(new Print[0]);
	}

	/**
	 * 印刷セットをクリアする。
	 */
	public void clearPrint() {
		array.clear();
	}

	/**
	 * 印刷インターフェイスの件数を返す。
	 * @return 件数
	 */
	public int getPrintSize() {
		return array.size();
	}

}
