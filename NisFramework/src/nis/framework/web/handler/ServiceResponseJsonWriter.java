package nis.framework.web.handler;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import nis.framework.web.response.ServiceResponseWrapper;

import com.fasterxml.jackson.databind.ObjectMapper;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class ServiceResponseJsonWriter implements MessageBodyWriter<ServiceResponseWrapper> {

	@Inject
	private ObjectMapper objectMapper;

	@Override
	public long getSize(ServiceResponseWrapper object, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return -1;
	}

	@Override
	public boolean isWriteable(Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return true;
	}

	@Override
	public void writeTo(ServiceResponseWrapper object, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders,
			OutputStream entityStream) throws IOException {
		objectMapper.writeValue(entityStream, object);
	}

}
