package ngyshanai.web.service.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

@Path("/isLoggedIn")
public class IsLoggedInService {

	@Context
	private HttpServletRequest request;

	@GET
	public String isLoggedIn(){
		HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("login") == null) {
        	return "false";
        }
        else {
        	return "true";
        }
    }
}
