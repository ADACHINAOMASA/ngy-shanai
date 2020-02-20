package nis.framework.web.option;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

/**
 *
 * <p>
 * オプションコンテナ<br>
 * アプリケーションスコープ
 * </p>
 * @author Kengo-Nagase
 *
 */
@ApplicationScoped
public class OptionsContainer implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * コンテナ
	 */
	private Map<String, Options> container = new HashMap<String, Options>();

	/**
	 * オプション一覧取得
	 * @return
	 */
	public Collection<Options> getOptions() {
		return container.values();
	}

	/**
	 * オプション設定
	 * @param options
	 */
	public void putOptions(Options options) {
		container.put(options.getKey(), options);
	}

	/**
	 * オプション取得
	 * @param key
	 * @return
	 */
	public Options getOptions(String key) {
		return container.get(key);
	}

	/**
	 * 存在するか
	 * @param key
	 * @return
	 */
	public boolean contains(String key) {
		return container.containsKey(key);
	}

	/**
	 * 存在しないか
	 * @param key
	 * @return
	 */
	public boolean notContains(String key) {
		return !contains(key);
	}
}
