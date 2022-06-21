package ngyshanai.web.service.search;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import ngyshanai.application.LogicExecutor;
import ngyshanai.common.lotdsp.CommonInfo;
import ngyshanai.common.msg.search.SearchInfo;
import ngyshanai.domain.logic.search.SearchActionLogic;

@Path("/lotdsp/search")
public class SearchActionService {

	@EJB
	private LogicExecutor executor;

	@Context
	private HttpServletRequest request;

	@POST
	@Path("/searchAction")
	public CommonInfo search(SearchInfo searchInfo) {
		CommonInfo in = new CommonInfo();
		String ip =  request.getRemoteAddr();
		in.setIp(ip);
		return executor.execute(SearchActionLogic.class, in, searchInfo);
	}

}
