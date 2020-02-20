package lotdsp.web.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import lotdsp.common.util.ServletUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class FileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected Log logger = LogFactory.getLog(getClass());

	private final static String CHARACTER_ENCODING = "Windows-31J";

	public FileServlet() {
		super();
	}

	@Override
	protected void service(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding(CHARACTER_ENCODING);

		String contentDisposition = getContentDisposition();
		String name = request.getParameter("n");
		if (name != null) {
			contentDisposition += "; filename=\""+ new String(name.getBytes("MS932"), "ISO-8859-1");
		}
		response.setHeader("content-disposition", contentDisposition);

		String fileName = ServletUtil.getFileName(request);

		response.setContentType(getContentType(fileName));

		String filePath = getImageRootPath() + fileName;
		logger.info("filePath=" + filePath);

		if(filePath.toLowerCase().startsWith("smb:")){
			jcifs.Config.setProperty("jcifs.resolveOrder", "DNS");
			SmbFile file = new SmbFile(filePath);

			if (file.exists()) {
				response.setContentLength((int) file.length());

				InputStream is = null;
				OutputStream os = response.getOutputStream();

				try {
					is = new SmbFileInputStream(file);

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
			} else {
				logger.error("File Not Found : " + filePath);
			}
		}
		else{
			File file = new File(filePath);
			if (file.exists()) {
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
			} else {
				logger.error("File Not Found:" + filePath);
			}
		}

	}

	protected abstract String getContentType(String param);

	protected abstract String getContentDisposition();

	protected abstract String getImageRootPath();

}
