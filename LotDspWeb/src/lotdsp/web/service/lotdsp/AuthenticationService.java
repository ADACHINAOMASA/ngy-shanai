package lotdsp.web.service.lotdsp;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import lotdsp.application.LogicExecutor;
import lotdsp.common.lotdsp.AuthenticationInfo;
import lotdsp.domain.logic.authentication.IPAuthenticateLogic;

@Path("/lotdsp/authentication")
public class AuthenticationService {

	@EJB
	private LogicExecutor executor;

	@Context
	private HttpServletRequest request;

	@GET
	@Path("/auth")
	public AuthenticationInfo auth(@Context UriInfo uriInfo) {
		AuthenticationInfo in = new AuthenticationInfo();
		String ip = request.getRemoteAddr();
		in.setIp(ip);
		String mode = request.getParameter("mode");
		in.setMode(mode);

		return executor.execute(IPAuthenticateLogic.class, in);
	}

}
