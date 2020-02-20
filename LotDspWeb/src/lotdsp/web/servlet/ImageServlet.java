package lotdsp.web.servlet;

import lotdsp.common.util.ContentType;
import nis.framework.properties.EnvProperties;

public class ImageServlet extends FileServlet {

	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_CONTENT_TYPE = ContentType.JPEG.getMimeType();

	@Override
	protected String getContentType(String param) {
		String contentType = ContentType.contentTypeOf(param);
		if (contentType == null) {
			return DEFAULT_CONTENT_TYPE;
		}
		logger.info("param="+param+" contentType="+contentType);
		return contentType;
	}

	@Override
	protected String getContentDisposition() {
		return "inline";
	}

	@Override
	protected String getImageRootPath() {
		return EnvProperties.getImageRootDir();
	}

}
