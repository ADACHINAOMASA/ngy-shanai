package ngyshanai.web.service;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ngyshanai.application.startup.OptionsContainerCreator;
import nis.framework.web.option.Options;
import nis.framework.web.option.OptionsContainer;

@Path("/options")
public class OptionsService {

	// TODO:上書き用のセッションスコープのコンテナも作っておく？

	@Inject
	private OptionsContainer optionsContainer;

	@Inject
	private OptionsContainerCreator optionsContainerCreator;

	@GET
	@Path("/refresh")
	public Collection<Options> getOptions(){
		// TODO:無制限公開は微妙なのでパスワードでも設定する？
		optionsContainerCreator.refreshOptions();
		return optionsContainer.getOptions();
    }

	@GET
	@Path("{optionsClass}")
	@Produces(MediaType.APPLICATION_JSON)
	public Options getOptions(@PathParam("optionsClass") String optionsClass, @DefaultValue("false") @QueryParam("refresh") boolean refresh){
		/*
		 * 決めないといけない事が多々ある
		 * とりあえずオプション生成自体の仕組みとしては、アノテーションを使用して作成する
		 */
		return optionsContainer.getOptions(optionsClass);
    }

}
