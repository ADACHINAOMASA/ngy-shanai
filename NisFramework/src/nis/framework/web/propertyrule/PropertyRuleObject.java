package nis.framework.web.propertyrule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * <p>
 * プロパティルールオブジェクト<br>
 * オブジェクト＝クラス
 * </p>
 * @author Kengo-Nagase
 *
 */
public class PropertyRuleObject {

	/**
	 * ルール名
	 */
	private String name;

	/**
	 * メンバ
	 */
	private List<PropertyRuleMember> members = new ArrayList<PropertyRuleMember>();

	/**
	 *
	 * @param name
	 */
	public PropertyRuleObject(String name) {
		this.name = name;
	}

	/**
	 * メンバ追加
	 * @param member
	 */
	public void addMember(PropertyRuleMember member) {
		members.add(member);
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
	 * membersを取得します。
	 * @return members
	 */
	public List<PropertyRuleMember> getMembers() {
	    return members;
	}

	/**
	 * membersを設定します。
	 * @param members members
	 */
	public void setMembers(List<PropertyRuleMember> members) {
	    this.members = members;
	}

	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		for (PropertyRuleMember member : members) {
			map.put(member.getName(), member.toMap());
		}
		return map;
	}

}
