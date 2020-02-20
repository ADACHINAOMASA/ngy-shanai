package lotdsp.web.service;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import lotdsp.application.LogicExecutor;
import lotdsp.common.msg.hososhiyosho.WindowOptionsInfo;
import lotdsp.common.util.ServletUtil;
import nis.framework.properties.EnvProperties;
import nis.framework.properties.SvfProperties;
import nis.framework.properties.SvfWebClientProperties;
import nis.framework.util.StringUtil;
import nis.framework.web.session.NisSessionContext;

/**
 * @author Leang-Say
 */
@Path("/common")
public class CommonService {

	protected Log logger = LogFactory.getLog(getClass());

	@Context
	private HttpServletRequest request;

	@Inject
	private NisSessionContext nisSession;

	@EJB
	private LogicExecutor executor;

	@GET
	@Path("/httpRequestInfo")
	public Map<String, String> getHttpRequestInfoMap(@Context UriInfo uriInfo) {
		return ServletUtil.getRequestInfoMap(request);
	}

	@GET
	@Path("/svfWebClientObjectInfo")
	public Map<String, String> getSvfWebClientObjectInfo(@Context UriInfo uriInfo) {

		Map<String, String> map = new HashMap<String, String>();

		map.put("id", StringUtil.nvl(SvfWebClientProperties.getId()));
		map.put("classId", StringUtil.nvl(SvfWebClientProperties.getClassId()));
		map.put("codeBase", StringUtil.nvl(SvfWebClientProperties.getCodeBase()));
		map.put("raw", StringUtil.nvl(SvfWebClientProperties.getRaw()));
		map.put("printer", StringUtil.nvl(SvfProperties.getSvfDirectPrintPrinterName()));

		map.put("docName", "");
		map.put("endUrl", "");
		map.put("errorUrl", "");
		map.put("cancelUrl", "");
		map.put("context", StringUtil.nvl(ServletUtil.getFullContextPath(request)));
		map.put("userId", StringUtil.nvl(nisSession.getUserProfile().getUserId()));
		map.put("userName", StringUtil.nvl(nisSession.getUserProfile().getUserName()));

		return map;
	}

	@GET
	@Path("/getWindowOptionsInfo")
	@Produces(MediaType.APPLICATION_JSON)
	public WindowOptionsInfo getWindowOptionsInfo() {
		WindowOptionsInfo info=new WindowOptionsInfo();

		EnvProperties.reload();
		
		info.setFullscreen(StringUtil.nvl(EnvProperties.get("window.options.fullscreen"),"no"));
		info.setToolbar(StringUtil.nvl(EnvProperties.get("window.options.toolbar"),"yes"));
		info.setLocation(StringUtil.nvl(EnvProperties.get("window.options.location"),"yes"));
		info.setMenubar(StringUtil.nvl(EnvProperties.get("window.options.menubar"),"yes"));
		info.setScrollbars(StringUtil.nvl(EnvProperties.get("window.options.scrollbars"),"yes"));
		info.setResizable(StringUtil.nvl(EnvProperties.get("window.options.resizable"),"yes"));
		info.setTop(Integer.valueOf(StringUtil.nvl(EnvProperties.get("window.options.top"),"0")));
		info.setLeft(Integer.valueOf(StringUtil.nvl(EnvProperties.get("window.options.left"),"0")));
		info.setWidth(Integer.valueOf(StringUtil.nvl(EnvProperties.get("window.options.width"),"1000")));
		info.setHeight(Integer.valueOf(StringUtil.nvl(EnvProperties.get("window.options.height"),"700")));
								
		return info;
	}
}
