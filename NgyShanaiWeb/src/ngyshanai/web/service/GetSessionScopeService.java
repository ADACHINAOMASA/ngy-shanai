package ngyshanai.web.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import nis.framework.web.WebConst;
import nis.framework.web.user.UserProfile;

@Path("/getSessionScope")
public class GetSessionScopeService {

	@Context
	private HttpServletRequest request;

	@GET
	@Path("/userProfile")
	@Produces(MediaType.APPLICATION_JSON)
	public UserProfile getUserProfile() {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return null;
		}
		return (UserProfile) session.getAttribute(WebConst.ObjectKey.USER_PROFILE);
	}

}
