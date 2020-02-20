
package lotdsp.web.service.passwordhenko;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import lotdsp.application.LogicExecutor;
import lotdsp.common.msg.passwordhenko.PasswordHenkoInfo;
import lotdsp.domain.logic.passwordhenko.PasswordHenkoLogic;

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
