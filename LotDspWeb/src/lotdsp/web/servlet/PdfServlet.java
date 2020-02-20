package lotdsp.web.servlet;



import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nis.framework.model.PrintResultMessage;
import nis.framework.properties.EnvProperties;
import nis.framework.util.StringUtil;
import nis.framework.web.WebConst;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import lotdsp.common.util.ContentType;
import lotdsp.common.util.ServletUtil;


public class PdfServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected Log logger = LogFactory.getLog(getClass());

	protected final static String DEFAULT_CHARACTER_ENCODING = "Windows-31J";

	protected final static String DIRECT_PRINT_EXT=".svfwdp";
	protected final static String PDF_EXT=".pdf";


	public PdfServlet() {
	}

	private String getModelFileName(PrintResultMessage model){
		if(model.getResultType()==PrintResultMessage.WDP){
			if(StringUtil.nvl(model.getPdfName()).startsWith(DIRECT_PRINT_EXT)){
				return model.getPdfName();
			}
			return model.getPdfName().concat(DIRECT_PRINT_EXT);
		}
		if(model.getResultType()==PrintResultMessage.PDF){
			if(StringUtil.nvl(model.getPdfName()).startsWith(PDF_EXT)){
				return model.getPdfName();
			}
			return model.getPdfName().concat(PDF_EXT);
		}
		return model.getPdfName();
	}

	public void service(HttpServletRequest request, HttpServletResponse response) {

		debug(request,true);

		response.reset();
		response.setDateHeader("Last-Modified", Calendar.getInstance().getTime().getTime());
		response.setDateHeader("Expires", -1);

		String path=StringUtil.nvl(request.getServletPath()).replace("/", "");
		PrintResultMessage model = (PrintResultMessage) request.getSession().getAttribute(WebConst.ObjectKey.PDF_INFORMATION);
		if(model == null){
			readContentFromFile(request, response);
		}else{
			String modelPdfName=getModelFileName(model);
			logger.info("【model="+modelPdfName+",uri path="+path+"】");
			if(StringUtil.isEqual(modelPdfName,path)){
				readContentFromModel(model, request, response);
			}
		}

		request.getSession().removeAttribute(WebConst.ObjectKey.PDF_INFORMATION);
	}

	protected void readContentFromModel(PrintResultMessage model,HttpServletRequest request, HttpServletResponse response){
		byte[] pdf = model.getResult();
		int contentType = model.getResultType();

		if (contentType == PrintResultMessage.NONE) {
			response.setContentType(ContentType.HTML_SHIFT_JIS.getMimeType());
			try {
				PrintWriter out = response.getWriter();
				out.println("<html lang=\"ja\">");
				out.println("<head><title>印刷完了</title>");
				out.println("</head><body>");
				out.println("<h1>印刷完了</h1>");
				out.println("</body></html>");
			}
			catch (IOException e) {
				logger.error(e);
			}
		}
		else {
			if (contentType == PrintResultMessage.PDF) {
				response.setContentType(ContentType.PDF.getMimeType());
			}else if (contentType == PrintResultMessage.WDD) {
				response.setContentType(ContentType.WDD.getMimeType());
			}else if (contentType == PrintResultMessage.WDP) {
				response.setContentType(ContentType.WDP.getMimeType());
			}

			try {
				logger.info("    ... data length="+pdf.length);
				ServletUtil.write(pdf, response);
			}
			catch (IOException e) {
				logger.error(e);
			}
			finally {
				if(model!=null) {
					model.setResult(null);
					model=null;
				}
			}
		}
	}

	protected void readContentFromFile(HttpServletRequest request, HttpServletResponse response) {
		String fileName = ServletUtil.getLastOfURL(request);
		String filePath = FilenameUtils.concat(getRootPath(), fileName);
		logger.info("file path =" + filePath+" (key="+WebConst.ObjectKey.PDF_INFORMATION+" not found in sessionid="+request.getSession().getId()+")");

		File file = new File(filePath);
		if (file.exists()) {
			try {
				response.setHeader("Content-Disposition", "inline; filename="+fileName);
				response.setContentType(ContentType.PDF.getMimeType());
				ServletUtil.write(file, response);
			}
			catch (IOException e) {
				logger.error(e);
			}
		} else {
			logger.error("File Not Found:" + file.getAbsolutePath());
		}
	}

	protected String getRootPath() {
		return EnvProperties.getPdfRootDir();
	}

	protected void debug(HttpServletRequest request,boolean flag){
		if(flag){
			logger.info("Session ID="+request.getSession().getId());
			logger.info("HttpServletRequest URI="+request.getRequestURI().toString());
			logger.info("HttpServletRequest full context path="+ServletUtil.getFullContextPath(request));
		}
	}

}
