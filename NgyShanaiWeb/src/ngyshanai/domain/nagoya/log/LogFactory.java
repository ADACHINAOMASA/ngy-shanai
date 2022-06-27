/*
 *	Nippon Light Metal Co.Ltd Lotdsp 2006
 *	LogFactory.java
 *	履歴：	2006.10.17	Hirohiko-Matsushita	新規作成
 *
 */

package ngyshanai.domain.nagoya.log;

/**
 * Logオブジェクト生成クラス。
 * @author Hirohiko-Matsushita
 */
public class LogFactory {

	/**
	 * コンストラクタ
	 */
	private LogFactory() {
	}

	/**
	 * ログオブジェクトを返す。
	 */
	public static Log getLog(String name) {
		return new Log(name);
	}

	/**
	 * ログオブジェクトを返す。
	 */
	public static Log getLog(Class class_) {
		return new Log(class_);
	}
}
