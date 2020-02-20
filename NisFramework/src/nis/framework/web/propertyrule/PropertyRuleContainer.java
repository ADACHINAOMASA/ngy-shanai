package nis.framework.web.propertyrule;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

/**
 *
 * <p>
 * プロパティルールコンテナ<br>
 * アプリケーションスコープでプロパティルールを管理
 * </p>
 * @author Kengo-Nagase
 *
 */
@ApplicationScoped
public class PropertyRuleContainer implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * コンテナ
	 */
	private Map<String, PropertyRuleObject> container = new HashMap<String, PropertyRuleObject>();

	/**
	 * ルールオブジェクト追加
	 * @param obj
	 */
	public void putRuleObject(PropertyRuleObject obj) {
		container.put(obj.getName(), obj);
	}

	/**
	 * ルールオブジェクト取得
	 * @param key
	 * @return
	 */
	public PropertyRuleObject getRuleObject(String key) {
		if (!contains(key)) {
			throw new IllegalArgumentException("存在しないルールを指定しました:" + key);
		}
		return container.get(key);
	}

	/**
	 * 複数のルールオブジェクトを取得
	 * @param keys
	 * @return
	 */
	public Map<String, Object> getRule(String...keys) {
		Map<String, Object> rules = new HashMap<String, Object>();
		for (String key : keys) {
			rules.put(key, getRuleObject(key).toMap());
		}
		return rules;
	}

	/**
	 * 全てのルールオブジェクトを取得
	 * @return
	 */
	public Map<String, Object> getAllRule() {
		Map<String, Object> rules = new HashMap<String, Object>();
		for (String key : container.keySet()) {
			rules.put(key, getRuleObject(key).toMap());
		}
		return rules;
	}

	/**
	 * 指定したルールが存在するか
	 * @param key
	 * @return
	 */
	public boolean contains(String key) {
		return container.containsKey(key);
	}

	/**
	 * 指定したルールが存在しないか
	 * @param key
	 * @return
	 */
	public boolean notContains(String key) {
		return !contains(key);
	}
}
