package nis.framework.web.handler;

import java.util.Properties;

import org.apache.wink.server.handlers.HandlersChain;
import org.apache.wink.server.handlers.MessageContext;
import org.apache.wink.server.handlers.RequestHandler;

public class ReqHandlerSub implements RequestHandler {

	@Override
	public void init(Properties props) {
	}

	@Override
	public void handleRequest(MessageContext context, HandlersChain chain)
			throws Throwable {
		//throw new IllegalArgumentException();
		chain.doChain(context);
	}

}
