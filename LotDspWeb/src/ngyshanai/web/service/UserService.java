package ngyshanai.web.service;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nis.framework.web.session.NisSessionContext;
import nis.framework.web.user.UserProfile;

@Path("/user")
public class UserService {

	@Inject
	private NisSessionContext snContext;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public UserProfile getUserProfile(){
		return snContext.getUserProfile();
    }

}
