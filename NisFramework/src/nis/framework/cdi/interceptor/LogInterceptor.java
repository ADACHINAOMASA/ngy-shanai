package nis.framework.cdi.interceptor;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.commons.logging.Log;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;

@Interceptor
@LogIntercept
public class LogInterceptor {

	@Inject
	private Log log;

	@AroundInvoke
	public Object intercept(InvocationContext ic) throws Exception {
		long time = 0;
		if (log.isInfoEnabled()) {
			log.info("START:" + getTarget(ic));
			time = System.currentTimeMillis();
		}
		if (log.isInfoEnabled()) {
			int cnt = 1;
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(Feature.INDENT_OUTPUT, true);
			for (Object parameter : ic.getParameters()) {
				if (parameter == null) {
					log.info("PARAM" + cnt + ":null");
				}
				else {
					log.info("PARAM" + cnt + ":\n" + parameter.getClass().getName() + "\n" + mapper.writeValueAsString(parameter));
				}
				cnt++;
			}
		}

		Object result = ic.proceed();

		if (log.isInfoEnabled()) {
			StringBuffer sb = new StringBuffer();
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(Feature.INDENT_OUTPUT, true);
			sb.append(result != null ? "\n" + result.getClass().getName() + "\n" + mapper.writeValueAsString(result) : "null");

			String retStr = sb.toString();
			if (retStr.length() > 5000) {
				retStr = retStr.substring(0, 5000) + "\n... total length " + retStr.length();
			}
			log.info("RETURN:" + retStr);
		}

		if (log.isInfoEnabled()) {
			log.info("END  :" + getTarget(ic));
			log.info("execution time: " + (System.currentTimeMillis() - time));
		}

		return result;
	}

	private String getTarget(InvocationContext ic) {
		return ic.getTarget().getClass().getName() + "." + ic.getMethod().getName();
	}
}
