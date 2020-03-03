package lotdsp.web.service.search;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import lotdsp.application.LogicExecutor;
import lotdsp.common.lotdsp.CommonInfo;
import lotdsp.common.msg.search.SearchInfo;
import lotdsp.domain.logic.search.SearchActionLogic;

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
