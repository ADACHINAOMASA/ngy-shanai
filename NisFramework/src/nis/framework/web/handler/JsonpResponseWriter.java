package nis.framework.web.handler;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import nis.framework.web.response.ServiceResponseWrapper;

import org.codehaus.jackson.map.ObjectMapper;

@Provider
@Produces("application/javascript")
public class JsonpResponseWriter implements MessageBodyWriter<ServiceResponseWrapper>{

	@Context
	private UriInfo uriInfo;

	@Override
	public long getSize(ServiceResponseWrapper arg0, Class<?> arg1, Type arg2,
			Annotation[] arg3, MediaType arg4) {
		return -1;
	}

	@Override
	public boolean isWriteable(Class<?> arg0, Type arg1, Annotation[] arg2,
			MediaType arg3) {
		return arg0.isAssignableFrom(ServiceResponseWrapper.class);
	}

	@Override
	public void writeTo(ServiceResponseWrapper arg0, Class<?> arg1, Type arg2,
			Annotation[] arg3, MediaType arg4,
			MultivaluedMap<String, Object> arg5, OutputStream arg6)
			throws IOException {
		OutputStreamWriter writer = new OutputStreamWriter(arg6, "utf-8");
		writer.write(createResponse(arg0));
		writer.flush();
	}

	private String createResponse(ServiceResponseWrapper response) {
		String callback = uriInfo.getQueryParameters().get("callback").get(0);
		ObjectMapper mapper = new ObjectMapper();
		try {
			return callback + "(" + mapper.writeValueAsString(response.getEntity()) + ")";
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

}
