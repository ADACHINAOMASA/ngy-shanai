
package ngyshanai.web.service.passwordhenko;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import ngyshanai.application.LogicExecutor;
import ngyshanai.common.msg.passwordhenko.PasswordHenkoInfo;
import ngyshanai.domain.logic.passwordhenko.PasswordHenkoLogic;

@Path("/passwordhenko")
public class PasswordHenkoService {

	@EJB
	private LogicExecutor executor;

	@POST
	@Path("/changePassword")
	@Consumes(MediaType.APPLICATION_JSON)
	public PasswordHenkoInfo changePassword(PasswordHenkoInfo in) {
		return executor.execute(PasswordHenkoLogic.class,in);
	}

}
