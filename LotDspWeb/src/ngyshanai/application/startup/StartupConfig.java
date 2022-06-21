package ngyshanai.application.startup;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import nis.framework.util.ConverUtilsSupport;


@Singleton
@Startup
public class StartupConfig {
	@PostConstruct
	public void postConstruct() {
		ConverUtilsSupport.create().register();
	}

}
