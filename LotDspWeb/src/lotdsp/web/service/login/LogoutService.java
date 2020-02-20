package lotdsp.web.service.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("/logout")
public class LogoutService {

	@Context
	private UriInfo uriInfo;

	@Context
	private HttpServletRequest request;

	@POST
	public void logout(){
        HttpSession session = request.getSession(false);
        if (session != null) {
        	session.invalidate();
        }
    }
}
