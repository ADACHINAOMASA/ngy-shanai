package lotdsp.web.service.lotdsp;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import lotdsp.application.LogicExecutor;
import lotdsp.common.lotdsp.CommonInfo;
import lotdsp.common.lotdsp.MenuInfo;
import lotdsp.domain.logic.menu.SearchMenuLogic;
import lotdsp.domain.logic.menu.SearchMenuParamsLogic;
import nis.framework.properties.EnvProperties;

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

	@GET
	@Path("/getMenuInfo")
	@Produces(MediaType.APPLICATION_JSON)
	public MenuInfo getMenuInfo() {
		MenuInfo info=new MenuInfo();
		EnvProperties.reload();
		info.setSaihikiateUrl(EnvProperties.get("menu.lot.saihikiate.url"));
		return info;
	}

	@GET
	@Path("/getSaiTehaiLink")
	@Produces(MediaType.APPLICATION_JSON)
	public MenuInfo getSaiTehaiLink() {
		MenuInfo info=new MenuInfo();
		EnvProperties.reload();
		info.setSaitehaiUrl(EnvProperties.get("menu.lot.saitehai.url"));
		return info;
	}

}
