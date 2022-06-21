
package ngyshanai.web.service.usertoroku;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import ngyshanai.application.LogicExecutor;
import ngyshanai.common.msg.usertoroku.UserTorokuInfo;
import ngyshanai.domain.logic.usertoroku.UserTorokuLogic;

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
