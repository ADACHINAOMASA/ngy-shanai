package lotdsp.web.service;

import java.util.Collection;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import lotdsp.application.startup.OptionsContainerCreator;
import nis.framework.properties.EnvProperties;
import nis.framework.properties.ExcelProperties;
import nis.framework.properties.MailProperties;
import nis.framework.properties.SvfProperties;
import nis.framework.web.option.Options;
import nis.framework.web.option.OptionsContainer;

@Path("/combo")
public class ComboService {

	@Inject
	private OptionsContainer optionsContainer;
	
	@EJB
	private OptionsContainerCreator creator;

	@GET
	@Path("/refreshAll")
	public  Collection<Options>  refreshAll() {
		
		EnvProperties.reload();
		ExcelProperties.reload();
		MailProperties.reload();
		SvfProperties.reload();
		
		creator.refreshOptions();
		
		return optionsContainer.getOptions();
	}

}
