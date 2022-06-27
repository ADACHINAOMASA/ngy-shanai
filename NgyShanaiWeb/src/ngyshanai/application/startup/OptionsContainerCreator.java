package ngyshanai.application.startup;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import nis.framework.web.option.KbnEnum;
import nis.framework.web.option.Options;
import nis.framework.web.option.OptionsContainer;
import nis.framework.web.option.SelectOptions;
import nis.framework.web.option.SelectOptionsFactory;

import org.apache.commons.lang3.StringUtils;
import org.scannotation.AnnotationDB;
import org.scannotation.ClasspathUrlFinder;

import ngyshanai.application.LogicExecutor;


@Singleton
@Startup
public class OptionsContainerCreator {

	@Inject
	private OptionsContainer container;

	@EJB
	private LogicExecutor executor;

	private Map<String, SelectOptionsCreator> creatorMap = new HashMap<String, SelectOptionsCreator>();

	@PostConstruct
	public void postConstruct() {
		createCreatorMap();
		refreshOptions();
	}

	@SuppressWarnings({ "unchecked" })
	private void createCreatorMap() {
		try {
			Map<String, SelectOptionsCreator> creatorMap = new HashMap<String, SelectOptionsCreator>();
			AnnotationDB db = new AnnotationDB();
			URL url = ClasspathUrlFinder.findClassBase(OptionsContainerCreator.class);
			db.scanArchives(new URL(URLDecoder.decode(url.toString(), "utf-8")));
			Set<Class<?>> classes = new HashSet<Class<?>>();
			for (String className : db.getAnnotationIndex().get(SelectOptions.class.getName())) {
				classes.add(Class.forName(className));
			}
			for (final Class<?> selectOptionsClass : classes) {
				if (SelectOptionsFactory.class.isAssignableFrom(selectOptionsClass)) {
					creatorMap.put(getSelectOptionsKey(selectOptionsClass),
							new ClassSelectOptionsCreator((Class<SelectOptionsFactory>)selectOptionsClass));
				}
				if (Enum.class.isAssignableFrom(selectOptionsClass) && KbnEnum.class.isAssignableFrom(selectOptionsClass)) {
					creatorMap.put(getSelectOptionsKey(selectOptionsClass),
							new KbnSelectOptionsCreator((Class<Enum<? extends KbnEnum>>)selectOptionsClass));
				}
				for (final Method method : selectOptionsClass.getMethods()){
					if (method.isAnnotationPresent(SelectOptions.class)) {
						creatorMap.put(getSelectOptionsKey(method),
								new MethodSelectOptionsCreator(method));
					}
				}
			}
			this.creatorMap = creatorMap;
		}catch(Exception e) {
			e.printStackTrace();
			throw new IllegalStateException(e);
		}
	}

	public void refreshOptions() {
		for (String key : creatorMap.keySet()) {
			refreshOptions(key);
		}
	}

	public void refreshOptions(String key) {
		container.putOptions(createOptions(key));
	}

	private Options createOptions(String key) {
		return new Options(key, creatorMap.get(key).create(executor));
	}

	private String getSelectOptionsKey(Class<?> clazz) {
		String key = clazz.getAnnotation(SelectOptions.class).value();
		if (StringUtils.isEmpty(key)) {
			key = StringUtils.uncapitalize(clazz.getSimpleName());
		}
		return key;
	}

	private String getSelectOptionsKey(Method method) {
		String key = method.getAnnotation(SelectOptions.class).value();
		if (StringUtils.isEmpty(key)) {
			key = method.getName();
		}
		return key;
	}

}
