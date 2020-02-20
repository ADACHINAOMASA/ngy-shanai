package lotdsp.web.service;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import nis.framework.web.inputmodel.InputModelClassContainer;

@Path("/model")
public class ModelService {

	@Inject
	private InputModelClassContainer container;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Object createNewModel(@QueryParam("model") String model) {
		return container.get(model).newInstance();
	}

}