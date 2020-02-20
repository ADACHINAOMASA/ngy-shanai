/*
 *	Nippon Light Metal Co.Ltd Lotdsp 2006
 *	Log.java
 *	履歴：	2006.10.17	Hirohiko-Matsushita	新規作成
 *
 */

package lotdsp.domain.nagoya.log;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/**
 * ログ出力ユーティリティクラス。
 * Jakarta commons-loggingを使うと、IBMのログクラスが優先的に使われる問題に対処。
 * また、各プロジェクトがJakarta等のjarに依存しないようにする。
 * @author Toshiyuki-Ichikawa
 */
public class Log {

	/**
	 * log4jのログオブジェクト
	 */
	private Logger logger;

	/**
	 * コンストラクタ
	 */
	Log(String name) {
		logger = Logger.getLogger(name);
	}

	/**
	 * コンストラクタ
	 */
	Log(Class class_) {
		logger = Logger.getLogger(class_);
	}

// Loggerのラッパーメソッド (commons-loggingのLogインターフェイスと同じ内容)

	public void trace(Object message) {
		logger.debug(message);
	}

	public void trace(Object message, Throwable t) {
		logger.debug(message, t);
	}

	public void debug(Object message) {
		logger.debug(message);
	}

	public void debug(Object message, Throwable t) {
		logger.debug(message, t);
	}

	public void info(Object message) {
		logger.info(message);
	}

	public void info(Object message, Throwable t) {
		logger.info(message, t);
	}

	public void warn(Object message) {
		logger.warn(message);
	}

	public void warn(Object message, Throwable t) {
		logger.warn(message, t);
	}

	public void error(Object message) {
		logger.error(message);
	}

	public void error(Object message, Throwable t) {
		logger.error(message, t);
	}

	public void fatal(Object message) {
		logger.fatal(message);
	}

	public void fatal(Object message, Throwable t) {
		logger.fatal(message, t);
	}

	public boolean isTraceEnabled() {
		return logger.isEnabledFor(Priority.DEBUG);
	}

	public boolean isDebugEnabled() {
		return logger.isEnabledFor(Priority.DEBUG);
	}

	public boolean isInfoEnabled() {
		return logger.isEnabledFor(Priority.INFO);
	}

	public boolean isWarnEnabled() {
		return logger.isEnabledFor(Priority.WARN);
	}

	public boolean isErrorEnabled() {
		return logger.isEnabledFor(Priority.ERROR);
	}

	public boolean isFatalEnabled() {
		return logger.isEnabledFor(Priority.FATAL);
	}

// 追加メソッド

	public void trace(Throwable t) {
		logger.debug(t.getMessage(), t);
	}

	public void debug(Throwable t) {
		logger.debug(t.getMessage(), t);
	}

	public void info(Throwable t) {
		logger.info(t.getMessage(), t);
	}

	public void warn(Throwable t) {
		logger.warn(t.getMessage(), t);
	}

	public void error(Throwable t) {
		logger.error(t.getMessage(), t);
	}

	public void fatal(Throwable t) {
		logger.fatal(t.getMessage(), t);
	}
}

