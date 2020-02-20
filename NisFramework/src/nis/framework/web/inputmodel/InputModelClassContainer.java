package nis.framework.web.inputmodel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

/**
 * InputModelClassコンテナ
 * <p>
 *  アプリケーションスコープで保持<br>
 * </p>
 * @author Kengo-Nagase
 *
 */
@ApplicationScoped
public class InputModelClassContainer implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンテナ本体
	 */
	private Map<String, InputModelClass> container = new HashMap<String, InputModelClass>();

	/**
	 * 追加
	 * @param clazz
	 */
	public void put(InputModelClass clazz) {
		container.put(clazz.getName(), clazz);
	}

	/**
	 * 取得
	 * @param key
	 * @return
	 */
	public InputModelClass get(String key) {
		if (!contains(key)) {
			throw new IllegalArgumentException("存在しないInputModelを指定しました:" + key);
		}
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
