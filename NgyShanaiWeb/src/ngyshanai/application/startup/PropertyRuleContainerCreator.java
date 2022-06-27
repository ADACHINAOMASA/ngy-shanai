package ngyshanai.application.startup;


import java.net.URL;
import java.net.URLDecoder;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import nis.framework.dictionary.InputModel;
import nis.framework.web.inputmodel.InputModelClassContainer;
import nis.framework.web.inputmodel.InputModelCreator;
import nis.framework.web.propertyrule.PropertyRuleContainer;
import nis.framework.web.propertyrule.PropertyRuleCreator;

import org.scannotation.AnnotationDB;
import org.scannotation.ClasspathUrlFinder;

@Singleton
@Startup
public class PropertyRuleContainerCreator {

	// TODO:後でリネーム Dictionary初期読み込み関係のスタートアップとする
	// TODO:ルールの後にモデルを追加したので少々チグハグに、統合するべきか
	//      ルールについて、手での追加を自由に行えるように、あえてInputModelとは切り離した情報とする

	@Inject
	private PropertyRuleContainer ruleContainer;

	@Inject
	private InputModelClassContainer modelCcontainer;

	@PostConstruct
	public void postConstruct() {
		createFromDictionary();
		createFromBuilder();
	}

	private void createFromDictionary() {
		try {
			AnnotationDB db = new AnnotationDB();
			URL url = ClasspathUrlFinder.findClassBase(PropertyRuleContainerCreator.class);

			db.scanArchives(new URL(URLDecoder.decode(url.toString(), "utf-8")));
			Set<Class<?>> classes = new HashSet<Class<?>>();
			for (String className : db.getAnnotationIndex().get(InputModel.class.getName())) {
				classes.add(Class.forName(className));
			}
			for (final Class<?> dictionaryClass : classes) {
				ruleContainer.putRuleObject(PropertyRuleCreator.createObject(dictionaryClass));
				modelCcontainer.put(InputModelCreator.create(dictionaryClass));
			}
		}catch(Exception e) {
			throw new IllegalStateException(e);
		}
	}

	private void createFromBuilder() {

		/* Dictionaryからでなく、Builder経由でルールを追加する事も可能 */

//		container.putRuleObject(PropertyRuleBuilder
//		.obj("JuchuInfo")
//			.member("juchuNo").rule(PropertyRuleBuildHelper.code(8))
//			.member("hikiaiNo").rule(PropertyRuleBuildHelper.code(8))
//			.member("shodanJokyoKbn").rule(PropertyRuleBuildHelper.code(2))
//			.member("eigyoTantoshaCd").rule(PropertyRuleBuildHelper.code(5))
//			.member("torihikisakiCd").rule(PropertyRuleBuildHelper.code(8))
//			.member("mitsumoriNo").rule(PropertyRuleBuildHelper.code(5))
//			.member("rank").rule(PropertyRuleBuildHelper.code(1))
//			.member("hanbaiKbn").rule(PropertyRuleBuildHelper.code(2))
//			.member("kenshuTsuki").rule(PropertyRuleBuildHelper.code(6))
//			.member("hanbaiKeiroKbn").rule(PropertyRuleBuildHelper.code(2))
//			.member("hanbaiKeiroKbnBiko").rule(PropertyRuleBuildHelper.string(200, true, false))
//		.build()
//	);
	}

}
