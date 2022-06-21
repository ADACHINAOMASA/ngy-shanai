package ngyshanai;

import java.net.URL;
import java.net.URLDecoder;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.Path;
import javax.ws.rs.core.Application;

import nis.framework.web.handler.JsonRequestReader;
import nis.framework.web.handler.ServiceResponseJsonWriter;
import nis.framework.web.handler.ServiceResponseTextWriter;

import org.scannotation.AnnotationDB;
import org.scannotation.ClasspathUrlFinder;

public class AppConfig extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		/**
		 * Pathアノテーションが付いているクラスを自動で追加する
		 * （本来なら何もしなくても勝手に読みこまれるのだが、設定の問題かWASの問題か上手くいかいないので、
		 * アノテーションから取得し直して追加する）
		 */
		classes.addAll(searchPathClasses());

		classes.add(ServiceResponseJsonWriter.class);
		classes.add(ServiceResponseTextWriter.class);
		classes.add(JsonRequestReader.class);

		return classes;
	}

	/**
	 * Pathアノテーションの設定されているクラスを探す
	 * @return
	 */
	private Set<Class<?>> searchPathClasses() {
		try {
			AnnotationDB db = new AnnotationDB();
			URL url = ClasspathUrlFinder.findClassBase(AppConfig.class);
			db.scanArchives(new URL(URLDecoder.decode(url.toString(), "utf-8")));
			Set<Class<?>> classes = new HashSet<Class<?>>();
			for (String className : db.getAnnotationIndex().get(Path.class.getName())) {
				classes.add(Class.forName(className));
			}
			return classes;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public Set<Object> getSingletons() {
		Set<Object> s = new HashSet<Object>();
		return s;
	}

}
