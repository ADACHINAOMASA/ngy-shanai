package nis.framework.web.propertyrule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * <p>
 * プロパティルールメンバ<br>
 * メンバ＝フィールド、プロパティ<br>
 * メンバに対するルール群を保持
 * </p>
 * @author Kengo-Nagase
 *
 */
public class PropertyRuleMember {

	/**
	 * 名前
	 */
	private String name;

	/**
	 * ルール群
	 */
	private List<PropertyRule> rules = new ArrayList<PropertyRule>();

	/**
	 *
	 * @param name
	 */
	public PropertyRuleMember(String name) {
		this.name = name;
	}

	/**
	 * ルール追加
	 * @param rule
	 */
	public void addRule(PropertyRule rule) {
		rules.add(rule);
	}

	/**
	 * 入力クラスルールを持っているか
	 * @return
	 */
	public boolean hasInputClassRule () {
		for (PropertyRule rule : rules) {
			if (rule.getType() == PropertyRuleType.inputclass) {
				return true;
			}
		}
		return false;
	}

	/**
	 * nameを取得します。
	 * @return name
	 */
	public String getName() {
	    return name;
	}

	/**
	 * nameを設定します。
	 * @param name name
	 */
	public void setName(String name) {
	    this.name = name;
	}

	/**
	 * rulesを取得します。
	 * @return rules
	 */
	public List<PropertyRule> getRules() {
	    return rules;
	}

	/**
	 * rulesを設定します。
	 * @param rules rules
	 */
	public void setRules(List<PropertyRule> rules) {
	    this.rules = rules;
	}

	public Map<String, String> toMap() {
		Map<String, String> map = new HashMap<String, String>();
		for (PropertyRule rule : rules) {
			map.put(rule.getType().name(), rule.getValue());
		}
		return map;
	}

}
