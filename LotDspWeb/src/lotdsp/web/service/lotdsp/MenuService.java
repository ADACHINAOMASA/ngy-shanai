package lotdsp.web.service.lotdsp;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import lotdsp.application.LogicExecutor;
import lotdsp.common.lotdsp.CommonInfo;
import lotdsp.domain.logic.menu.SearchMenuParamsLogic;
import lotdsp.domain.logic.menu.SearchMenuLogic;

@Path("/lotdsp/menu")
public class MenuService {

	@EJB
	private LogicExecutor executor;

	@Context
	private HttpServletRequest request;

	@POST
	@Path("/searchMenu")
	@Consumes(MediaType.APPLICATION_JSON)
	public CommonInfo searchMenu(CommonInfo in) {
		String ip =  request.getRemoteAddr();
		in.setIp(ip);
		return executor.execute(SearchMenuLogic.class, in);
	}

	@POST
	@Path("/searchMenuParams")
	@Consumes(MediaType.APPLICATION_JSON)
	public CommonInfo searchMenuParams(CommonInfo in) {
		String ip =  request.getRemoteAddr();
		in.setIp(ip);
		return executor.execute(SearchMenuParamsLogic.class, in);
	}

}
