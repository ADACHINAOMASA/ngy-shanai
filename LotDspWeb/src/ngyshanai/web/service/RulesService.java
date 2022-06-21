package ngyshanai.web.service;

import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import nis.framework.web.propertyrule.PropertyRuleContainer;

@Path("/rules")
public class RulesService {

	@Inject
	private PropertyRuleContainer container;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> getRules(@QueryParam("rules") String...rules){
		return container.getRule(rules);
    }

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> getAllRules(){
		return container.getAllRule();
    }
}