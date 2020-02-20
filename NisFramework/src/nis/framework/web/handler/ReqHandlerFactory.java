package nis.framework.web.handler;

import java.util.Arrays;
import java.util.List;

import org.apache.wink.server.handlers.HandlersFactory;
import org.apache.wink.server.handlers.RequestHandler;
import org.apache.wink.server.handlers.ResponseHandler;

public class ReqHandlerFactory extends HandlersFactory {

	public ReqHandlerFactory() {
	}

	@Override
	public List<? extends RequestHandler> getRequestHandlers() {
		return Arrays.asList(new SessionCheckRequestHandler());
	}

	@Override
	public List<? extends ResponseHandler> getResponseHandlers() {
		return Arrays.asList(new RspHandler());
	}

}
