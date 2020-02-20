package lotdsp.web.service.hososhiyosho;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lotdsp.application.LogicExecutor;
import lotdsp.common.msg.CommonExcelInfo;
import lotdsp.common.msg.hososhiyosho.HosoShiyoshoHeaderInfo;
import lotdsp.common.msg.hososhiyosho.HosoShiyoshoInfo;
import lotdsp.common.msg.hososhiyosho.WindowOptionsInfo;
import lotdsp.domain.logic.hososhiyosho.HosoShiyoshoOutputExcelLogic;
import lotdsp.domain.logic.hososhiyosho.HosoShiyoshoSearchLogic;
import lotdsp.domain.logic.hososhiyosho.HosoShiyoshoSoapClientLogic;
import nis.framework.properties.EnvProperties;
import nis.framework.util.StringUtil;

@Path("/hososhiyosho")
public class HosoShiyoshoService {

	@EJB
	private LogicExecutor executor;
	
	@POST
	@Path("/soapClient")
	@Consumes(MediaType.APPLICATION_JSON)
	public HosoShiyoshoInfo soapClient(HosoShiyoshoHeaderInfo in) {
		in.setGenba(false);
		return executor.execute(HosoShiyoshoSoapClientLogic.class, in);
	}
	

	@POST
	@Path("/search")
	@Consumes(MediaType.APPLICATION_JSON)
	public HosoShiyoshoInfo searchStaff(HosoShiyoshoHeaderInfo in) {
		in.setGenba(false);
		return executor.execute(HosoShiyoshoSearchLogic.class, in);
	}
	
	@POST
	@Path("/searchGenba")
	@Consumes(MediaType.APPLICATION_JSON)
	public HosoShiyoshoInfo searchGenba(HosoShiyoshoHeaderInfo in) {
		in.setGenba(true);
		return executor.execute(HosoShiyoshoSearchLogic.class, in);
	}
	

	@POST
	@Path("/outputExcel")
	@Consumes(MediaType.APPLICATION_JSON)
	public byte[] outputExcel(CommonExcelInfo info) {
		CommonExcelInfo out = executor.execute(HosoShiyoshoOutputExcelLogic.class, info);
		if (out == null) {
			return null;
		}
		return out.getData();
	}

	@GET
	@Path("/genba/windowOptions")
	@Produces(MediaType.APPLICATION_JSON)
	public WindowOptionsInfo getGenbaWindowOpenOptionsInfo() {
		WindowOptionsInfo info=new WindowOptionsInfo();

		EnvProperties.reload();
		
		info.setFullscreen(StringUtil.nvl(EnvProperties.get("genba.window.options.fullscreen"),"no"));
		info.setToolbar(StringUtil.nvl(EnvProperties.get("genba.window.options.toolbar"),"yes"));
		info.setLocation(StringUtil.nvl(EnvProperties.get("genba.window.options.location"),"yes"));
		info.setMenubar(StringUtil.nvl(EnvProperties.get("genba.window.options.menubar"),"yes"));
		info.setScrollbars(StringUtil.nvl(EnvProperties.get("genba.window.options.scrollbars"),"yes"));
		info.setResizable(StringUtil.nvl(EnvProperties.get("genba.window.options.resizable"),"yes"));
		info.setTop(Integer.valueOf(StringUtil.nvl(EnvProperties.get("genba.window.options.top"),"0")));
		info.setLeft(Integer.valueOf(StringUtil.nvl(EnvProperties.get("genba.window.options.left"),"0")));
		info.setWidth(Integer.valueOf(StringUtil.nvl(EnvProperties.get("genba.window.options.width"),"1000")));
		info.setHeight(Integer.valueOf(StringUtil.nvl(EnvProperties.get("genba.window.options.height"),"700")));
								
		return info;
	}
	
	
	
}
