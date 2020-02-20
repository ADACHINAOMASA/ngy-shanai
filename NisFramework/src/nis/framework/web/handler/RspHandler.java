package nis.framework.web.handler;

import java.io.File;
import java.util.Properties;
import java.util.Set;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import nis.framework.ejb.logic.ServiceContext;
import nis.framework.web.response.ServiceResponseWrapper;

import org.apache.wink.server.handlers.HandlersChain;
import org.apache.wink.server.handlers.MessageContext;
import org.apache.wink.server.handlers.ResponseHandler;

public class RspHandler implements ResponseHandler {

	@Override
	public void init(Properties props) {
	}

	// TODO: SUCCESSで戻り値nullの場合、angular側では無視される
	//		 コンテキストステータスをエラーにしてあれば問題ないが、設定忘れ防止を付けるべきか

	// TODO: angular外からのリクエストも全てラップされるので、もう少し汎用的になるように考えたい
	//		 例）画像ファイルをサービス経由で返したい時等

	@Override
	public void handleResponse(MessageContext context, HandlersChain chain)
			throws Throwable {
//		HttpServletRequest request = context.getAttribute(HttpServletRequest.class);
		Object responseEntity = context.getResponseEntity();
		if (responseEntity == null) {
			context.setResponseEntity(Response.ok(wrapResponseEntity(null)).build());
			context.setResponseMediaType(MediaType.APPLICATION_JSON_TYPE);
    		context.setResponseStatusCode(HttpServletResponse.SC_OK);
		}
		// 暫定対処
		else if (responseEntity instanceof File) {
			ResponseBuilder response = Response.ok(responseEntity);
			String headerVal = "attachment; filename=" + ((File)responseEntity).getName();
			response.header("Content-Disposition", headerVal);
			response.header("Cache-Control", "no-cache");
			response.header("Expires", "-1");
			context.setResponseEntity(response.build());
		}
		else if (responseEntity instanceof Response) {
	    	Response response = (Response)responseEntity;
	    	if (response.getStatus() == HttpServletResponse.SC_OK) {
	    		context.setResponseEntity(Response.ok(wrapResponseEntity(response.getEntity())).build());
	    	}
	    }
		else if (responseEntity instanceof byte[]) {
			byte[] bytes = (byte[])responseEntity;
			context.setResponseEntity(wrapBytes(bytes));
		}
 	    else {
	    	context.setResponseEntity(wrapResponseEntity(responseEntity));
	    }
		chain.doChain(context);
	}

	public ServiceResponseWrapper wrapResponseEntity(Object entity) {
		ServiceContext svContext = (ServiceContext) resolveByCdi(ServiceContext.class);
		ServiceResponseWrapper wrapper = new ServiceResponseWrapper(entity);
		wrapper.setAlerts(svContext.getAlerts());
		wrapper.setStatus(svContext.getStatus());
		return wrapper;
	}

	public ServiceResponseWrapper wrapBytes(byte[] bytes) {
		// byte型だとデフォルトでエンコードが走ってしまうのでintに変える
		// 値としては数字が渡せればいいだけ
		int[] data = new int[bytes.length];
		int i = 0;
		for (byte b : bytes) {
			data[i++] = b;
		}
		ServiceContext svContext = (ServiceContext) resolveByCdi(ServiceContext.class);
		ServiceResponseWrapper wrapper = new ServiceResponseWrapper(data);
		wrapper.setAlerts(svContext.getAlerts());
		wrapper.setStatus(svContext.getStatus());
		wrapper.setByteData(true);
		return wrapper;
	}

	public Object resolveByCdi(@SuppressWarnings("rawtypes") Class logic) {
		BeanManager bm = getBm();
		Set<Bean<?>> beans = bm.getBeans(logic);
//        for (Bean<?> bean : beans) {
//            System.out.println(bean.getBeanClass().getName());
//        }
		Bean<?> bean = bm.resolve(beans);
		CreationalContext<?> cc = bm.createCreationalContext(bean);
		return bm.getReference(bean, logic, cc);
	}

	private BeanManager getBm() {
		try {
			Context context = new InitialContext();
			return (BeanManager) context.lookup("java:comp/BeanManager");
		} catch (javax.naming.NamingException e) {
	    	System.out.println("BeanManager��������܂���ł����B");
			//throw new IllegalArgumentException(e);
		}
		return null;
	}

}
