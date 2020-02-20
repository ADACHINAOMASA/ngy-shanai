package nis.framework.web.handler;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wink.server.handlers.HandlersChain;
import org.apache.wink.server.handlers.MessageContext;
import org.apache.wink.server.handlers.RequestHandler;

public class ReqHandler implements RequestHandler {

	private Log log = LogFactory.getLog(ReqHandler.class);

	@Override
	public void init(Properties props) {
	}

	@Override
	public void handleRequest(MessageContext context, HandlersChain chain)
			throws Throwable {

		//TODO:ログ出力
		//		アクセス先のURL等
		log.info(context.getUriInfo().getPath());

		//Response rsp = Response.ok("handle �C���^�[�Z�v�g").type(MediaType.TEXT_PLAIN).build();
		//context.setResponseEntity(rsp);
		//context.setResponseEntity("handle �C���^�[�Z�v�g�Q");

//		for (Entry<String, Object> ent : context.getAttributes().entrySet()) {
//			System.out.println(ent.getKey() + " : " + ent.getValue());
//		}

//		UriInfo uriInfo = context.getUriInfo();
//		HttpServletRequest request = context.getAttribute(HttpServletRequest.class);
//		request.getSession().setAttribute("jsptest", "test");
//		URI uri = uriInfo.getBaseUriBuilder().path("/index.jsp").build();
//		Response rsp = Response.seeOther(uri).build(); // ���N�G�X�g�X�R�[�v���ς��H
//		context.setResponseEntity(rsp);

//		context.setResponseEntity(rsp.getEntity()); // �����ēn���K�v�͂Ȃ�
//		context.setResponseStatusCode(rsp.getStatus());
//		MultivaluedMap<String, Object> mp = rsp.getMetadata();
//		for (String key : mp.keySet()) {
//			System.out.println(key + ":" + mp.get(key));
//		}
		chain.doChain(context);
	}

}
