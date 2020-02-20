package lotdsp.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Leang-Say
 */
public class ServletUtil {

	protected static Log logger = LogFactory.getLog(ServletUtil.class);

	private ServletUtil() {
	}

	public static Map<String,String> getRequestInfoMap(HttpServletRequest request) {
		Map<String,String> map=new HashMap<String,String>();
		
		map.put("schema",request.getScheme());
		map.put("hostName",request.getServerName());
		map.put("port",String.valueOf(request.getServerPort()));
		map.put("contextPath",request.getContextPath());
		map.put("fullContextPath",getFullContextPath(request));
		map.put("sessionId",request.getSession().getId());

		return map;
	}

	public static String getLastOfURL(HttpServletRequest request) {
		String[] strArr = request.getRequestURI().toString().split("/");
		return strArr[strArr.length-1];
	}
	
	public static String getFileName(HttpServletRequest request) {
		return request.getRequestURI().substring(request.getContextPath().length()+ request.getServletPath().length());
	}

	public static String getFullContextPath(HttpServletRequest request) {
		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}

	public static String getFullContextPath(HttpServletRequest request,String replaceHostName) {
		return "http://" + replaceHostName + ":" + request.getServerPort() + request.getContextPath();
	}

	public synchronized static void write(byte[] data,HttpServletResponse response) throws IOException {
		try{
			OutputStream os = response.getOutputStream();
			os.write(data);
			os.flush();
			os.close();
		}catch(Exception e){
			throw new IllegalStateException(e);
		}
	}

	public synchronized static void write(File file,HttpServletResponse response) throws IOException {
		response.setContentLength((int) file.length());
		InputStream is = null;
		OutputStream os = response.getOutputStream();
		try {
			is = new FileInputStream(file);
			byte[] bf = new byte[4096];
			int len;
			while ((len = is.read(bf)) > 0) {
				os.write(bf, 0, len);
			}
			os.flush();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException x) {
				}
			}
		}
	}

}
