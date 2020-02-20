package nis.framework.web.handler;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wink.server.handlers.HandlersChain;
import org.apache.wink.server.handlers.MessageContext;
import org.apache.wink.server.handlers.RequestHandler;

import nis.framework.util.RequestUriAuthorizeUtil;

public class SessionCheckRequestHandler implements RequestHandler {

	private Log log = LogFactory.getLog(ReqHandler.class);

	@Override
	public void init(Properties props) {
	}

	@Override
	public void handleRequest(MessageContext context, HandlersChain chain)
			throws Throwable {

		log.info(context.getUriInfo().getPath());

		HttpServletRequest request = context.getAttribute(HttpServletRequest.class);
		HttpSession session = request.getSession(false);
		UriInfo uriInfo = context.getUriInfo();
		
		//2017-10-04 追加　※許可URIはapp.propertiesに定義すること。
		if(RequestUriAuthorizeUtil.isPermitted(uriInfo)){
    		chain.doChain(context);
    		return;
		}
		
//		if(context.getUriInfo().getPath().contains("schapp") || 
//					context.getUriInfo().getPath().contains("icasapp")) {
//    		chain.doChain(context);
//    		return;
//		}
				
    	if (uriInfo.getRequestUri().getPath().equals(uriInfo.getBaseUri().getPath())
    			|| uriInfo.getRequestUri().getPath().endsWith("/login")
    			|| uriInfo.getRequestUri().getPath().endsWith("/logout")
    			|| uriInfo.getRequestUri().getPath().endsWith("/isLoggedIn")
    			
    			) {
    		chain.doChain(context);
    		return;
    	}
    	if (session == null || session.getAttribute("login") == null) {
    		// TODO:401は認証失敗 セッションタイムアウトとしても意味は通るが、区別するべきか
    		context.setResponseEntity(Response.status(401).build());
    		return;
//    		throw new SessionTimeoutException();
    	}

		chain.doChain(context);
	}

}
