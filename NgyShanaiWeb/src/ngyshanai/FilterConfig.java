package ngyshanai;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class FilterConfig extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		@SuppressWarnings("unused")
		Set<Class<?>> classes = new HashSet<Class<?>>();
		return super.getClasses();
	}

}
