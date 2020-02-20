package nis.framework.svf.vrw;


import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * SVF 動作拡張クラス
 */
public class SvfVrwEx extends SvfVrwAdapter {

	/**
	 * ログオブジェクト
	 */
	private Log logger = LogFactory.getLog(getClass());

	/**
	 * コンストラクタ
	 */
	public SvfVrwEx() throws IOException {
		super();
	}

	/**
	 * オーバーライド
	 */
	public int VrrOut(String fieldName, double data) throws IOException {
		int ret = super.VrrOut(fieldName, data);
		if (ret < 0) {
			logger.debug("帳票フィールドが見つかりません: " + fieldName);
		}
		return ret;
	}

	/**
	 * オーバーライド
	 */
	public int VrsOut(String fieldName, String data) throws IOException {
		// nullチェックを追加。
		// オリジナルの VrsOut では、dataがnullの場合に例外が起きて不便なため。
		if (data == null) {
			super.VrsOut(fieldName, "");
			return 0;
		}
		else {
			int ret = super.VrsOut(fieldName, charCheck(data));
			if (ret < 0) {
				logger.debug("帳票フィールドが見つかりません: " + fieldName);
			}
			return ret;
		}
	}

	/**
	 * オーバーライド
	 */
	public int VrsOutn(String fieldName, int index, String data) throws IOException {
		// nullチェックを追加。
		// オリジナルの VrsOutn では、dataがnullの場合に例外が起きて不便なため。
		if (data == null) {
			super.VrsOutn(fieldName, index, "");
			return 0;
		}
		else {
			int ret = super.VrsOutn(fieldName, index, charCheck(data));
			if (ret < 0) {
				logger.debug("帳票フィールドが見つかりません: " + fieldName);
			}
			return ret;
		}
	}

	/**
	 * 文字チェック
	 * @param value
	 * @return
	 */
	private String charCheck(String value) {
		try {
			String str = new String(value.getBytes("CP943"), "Windows-31J");
			return str;
		}
		catch (UnsupportedEncodingException x) {
			return "";
		}
	}

}
