package nis.framework.web.handler;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class JsonRequestReader implements MessageBodyReader<Object>{

	@Inject
	private ObjectMapper objectMapper;

	@Override
	public boolean isReadable(Class<?> arg0, Type arg1, Annotation[] arg2,
			MediaType arg3) {
		return true;
	}

	@Override
	public Object readFrom(Class<Object> arg0, Type arg1, Annotation[] arg2,
			MediaType arg3, MultivaluedMap<String, String> arg4,
			InputStream arg5) throws IOException {

		return objectMapper.readValue(arg5, arg0);
	}

}
