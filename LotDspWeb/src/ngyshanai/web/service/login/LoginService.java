package ngyshanai.web.service.login;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Providers;

import ngyshanai.application.LogicExecutor;
import ngyshanai.common.msg.login.LoginInfo;
import ngyshanai.domain.logic.login.LoginLogic;
import nis.framework.ejb.logic.ServiceContext;
import nis.framework.web.WebConst;
import nis.framework.web.session.NisSessionContext;
import nis.framework.web.user.UserProfile;

@Path("/login")
public class LoginService {

	@Context
	private ServletContext sc;

	@Context
	private UriInfo uriInfo;

	@Context
	private HttpServletRequest request;

	@Context
	private Providers providers;

	@Inject
	private ServiceContext svContext;

	@Inject
	private NisSessionContext snContext;

	@EJB
	private LogicExecutor executor;

	@POST
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response loginToJSON(@FormParam("id") String id,@FormParam("password") String password) {
		LoginInfo inInfo = new LoginInfo();
		inInfo.setId(id);
		inInfo.setPassword(password);
		
		UserProfile out = (UserProfile) executor.execute(LoginLogic.class,inInfo);
		if (svContext.isStatusError()) {
			return null;
		}
		
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		session = request.getSession(true);
		session.setAttribute(WebConst.ObjectKey.USER_PROFILE, out);
		snContext.setUserProfile(out);
		session.setAttribute("login", "true");

		return Response.ok(out).
		// cookie(new NewCookie("JSESSIONID", sessionId) ).
				build();
	}
	
}
