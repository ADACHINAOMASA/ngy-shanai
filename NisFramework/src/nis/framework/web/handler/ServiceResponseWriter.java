package nis.framework.web.handler;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import nis.framework.web.response.ServiceResponseWrapper;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;

@Provider
@Produces("text/html")
public class ServiceResponseWriter implements MessageBodyWriter<ServiceResponseWrapper>{

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
		writer.write(createResponseHtml(arg0));
		writer.flush();
	}

	private String createResponseHtml(ServiceResponseWrapper response) {
		StringBuffer sb = new StringBuffer();
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.configure(Feature.INDENT_OUTPUT, true);
			sb.append("<html>");
			sb.append("<header>");
			sb.append("<meta charset='utf-8'>");
			sb.append("</header>");
			sb.append("<body>");
			sb.append("<pre>" + mapper.writeValueAsString(response) + "</pre>");
			sb.append("</body>");
			sb.append("</html>");
		} catch (Exception e) {
			sb.append("toJSON FAILED");
			sb.append("STATUS:" + response.getStatus());
		}
		return sb.toString();
	}

}
