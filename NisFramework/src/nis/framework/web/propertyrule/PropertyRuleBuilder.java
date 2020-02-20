package nis.framework.web.propertyrule;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * プロパティルール組立者<br>
 * 手動でプロパティルールを生成する時に使用
 * </p>
 * @author Kengo-Nagase
 *
 */
public class PropertyRuleBuilder {

	/* 手動追加例 */

//	container.putRuleObject(PropertyRuleBuilder
//	.obj("JuchuInfo")
//		.member("juchuNo").rule(PropertyRuleBuildHelper.code(8))
//		.member("hikiaiNo").rule(PropertyRuleBuildHelper.code(8))
//		.member("shodanJokyoKbn").rule(PropertyRuleBuildHelper.code(2))
//		.member("eigyoTantoshaCd").rule(PropertyRuleBuildHelper.code(5))
//		.member("torihikisakiCd").rule(PropertyRuleBuildHelper.code(8))
//		.member("mitsumoriNo").rule(PropertyRuleBuildHelper.code(5))
//		.member("rank").rule(PropertyRuleBuildHelper.code(1))
//		.member("hanbaiKbn").rule(PropertyRuleBuildHelper.code(2))
//		.member("kenshuTsuki").rule(PropertyRuleBuildHelper.code(6))
//		.member("hanbaiKeiroKbn").rule(PropertyRuleBuildHelper.code(2))
//		.member("hanbaiKeiroKbnBiko").rule(PropertyRuleBuildHelper.string(200, true, false))
//	.build()
//	);

	/**
	 * ルール名
	 */
	private String name;

	/**
	 * メンバ(プロパティ)
	 */
	private Map<String, PropertyRuleMember> members = new LinkedHashMap<String, PropertyRuleMember>();

	/**
	 * 現在選択中のメンバ
	 */
	private PropertyRuleMember currentCursorMember;

	/**
	 *
	 * @param name
	 */
	private PropertyRuleBuilder(String name) {
		this.name = name;
	}

	/**
	 * 新規ルール作成
	 * @param name ルール名
	 * @return ビルダ
	 */
	public static PropertyRuleBuilder obj(String name) {
		return new PropertyRuleBuilder(name);
	}

	/**
	 * メンバー選択
	 * 存在しない場合は新規にメンバーを追加し、それを選択中にする
	 * @param name メンバー名
	 * @return ビルダ
	 */
	public PropertyRuleBuilder member(String name) {
		PropertyRuleMember member = members.get(name);
		if (member == null) {
			member = new PropertyRuleMember(name);
			members.put(name, member);
		}
		currentCursorMember = member;
		return this;
	}

	/**
	 * 現在選択中のメンバに対してルールを追加
	 * @param rules
	 * @return ビルダ
	 */
	public PropertyRuleBuilder rule(List<PropertyRule> rules) {
		return rule(rules.toArray(new PropertyRule[]{}));
	}

	/**
	 * 現在選択中のメンバに対してルールを追加
	 * @param rules
	 * @return ビルダ
	 */
	public PropertyRuleBuilder rule(PropertyRule...rules) {
		if (currentCursorMember == null) {
			throw new IllegalStateException("メンバーが選択されていません。");
		}
		for (PropertyRule rule : rules) {
			currentCursorMember.addRule(rule);
		}
		return this;
	}

	/**
	 * 現在のビルダ内の情報よりルールオブジェクトを組立
	 * @return ルールオブジェクト
	 */
	public PropertyRuleObject build() {
		PropertyRuleObject obj = new PropertyRuleObject(name);
		for (PropertyRuleMember member : members.values()) {
			obj.addMember(member);
		}
		return obj;
	}
}
