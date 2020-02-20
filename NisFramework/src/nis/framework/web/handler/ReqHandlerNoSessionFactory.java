package nis.framework.web.handler;

import java.util.Arrays;
import java.util.List;

import org.apache.wink.server.handlers.HandlersFactory;
import org.apache.wink.server.handlers.ResponseHandler;

public class ReqHandlerNoSessionFactory extends HandlersFactory {

	public ReqHandlerNoSessionFactory() {
	}

	@Override
	public List<? extends ResponseHandler> getResponseHandlers() {
		return Arrays.asList(new RspHandler());
	}

}
