package nis.framework.web.propertyrule;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import nis.framework.dictionary.InputModel;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 * プロパティルール生成者<br>
 * クラスやフィールドのアノテーションを解析し、ルールオブジェクトを生成する
 * </p>
 * @author Kengo-Nagase
 *
 */
public class PropertyRuleCreator {

	/**
	 * ルールオブジェクト生成
	 * InputModelアノテーションの付与されたクラスからルールオブジェクトを作る
	 * ルール名はInputModelのvalueが設定されていればそれを、
	 * されていなければクラス名
	 * @param clazz InputModelアノテーションの付与されたクラス
	 * @return
	 */
	public static PropertyRuleObject createObject(Class<?> clazz) {
		if (!clazz.isAnnotationPresent(InputModel.class)) {
			throw new IllegalArgumentException("InputModelでないクラス");
		}
		String name = clazz.getAnnotation(InputModel.class).value();
		if (StringUtils.isEmpty(name)) {
			name = clazz.getSimpleName();
		}
		return createObject(clazz, name);
	}

	/**
	 * ルールオブジェクト生成
	 * InputModelアノテーションの付与されたクラスからルールオブジェクトを作る
	 * @param clazz InputModelアノテーションの付与されたクラス
	 * @param name ルール名
	 * @return
	 */
	public static PropertyRuleObject createObject(Class<?> clazz, String name) {
		if (!clazz.isAnnotationPresent(InputModel.class)) {
			throw new IllegalArgumentException("InputModelでないクラス");
		}
		PropertyRuleObject obj = new PropertyRuleObject(name);
		for (Field field : clazz.getDeclaredFields()) {
			// TODO:生成の対象としないものを指定できるように
			if (true) {
				obj.addMember(createMember(field));
			}
		}
		return obj;
	}

	/**
	 * メンバー生成
	 * @param field
	 * @return
	 */
	public static PropertyRuleMember createMember(Field field) {
		return createMember(field, field.getName());
	}

	/**
	 * メンバー生成
	 * @param field
	 * @param name
	 * @return
	 */
	public static PropertyRuleMember createMember(Field field, String name) {
		PropertyRuleMember member = new PropertyRuleMember(name);
		for (Annotation anno : field.getAnnotations()) {
			// TODO:このアノテーションがNIS独自定義のアノテーションである事を判別させる？
			PropertyRule rule = PropertyRuleFactory.get().create(anno, field);
			if (rule == null) {
				continue;
			}
			member.addRule(rule);
		}
		// InputClass（画面上入力クラス）が設定されていない場合、プロパティのクラスを使用する
		if (!member.hasInputClassRule()) {
			member.addRule(
					PropertyRuleFactory.get().create(field.getType()));
		}

		return member;
	}

}
