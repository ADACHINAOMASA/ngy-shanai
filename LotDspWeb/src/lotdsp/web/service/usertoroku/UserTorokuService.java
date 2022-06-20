
package lotdsp.web.service.usertoroku;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import lotdsp.application.LogicExecutor;
import lotdsp.common.msg.usertoroku.UserTorokuInfo;
import lotdsp.domain.logic.usertoroku.UserTorokuLogic;

@Path("/usertoroku")
public class UserTorokuService {

	@EJB
	private LogicExecutor executor;

	@POST
	@Path("/toroku")
	public boolean userToroku(UserTorokuInfo in) {
		return executor.execute(UserTorokuLogic.class,in);
	}

}
