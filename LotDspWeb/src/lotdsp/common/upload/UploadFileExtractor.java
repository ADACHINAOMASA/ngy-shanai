package lotdsp.common.upload;

import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.wink.common.model.multipart.InMultiPart;
import org.apache.wink.common.model.multipart.InPart;

import lotdsp.common.util.IOUtil;

public class UploadFileExtractor {

	public synchronized static UploadFileInfo extract(InMultiPart inMP) {
		UploadFileInfo file = new UploadFileInfo();
		if (inMP.hasNext()) {
			InPart part = inMP.next();
			MultivaluedMap<String, String> heades = part.getHeaders();
			String CDHeader = heades.getFirst("Content-Disposition");
			InputStream is = part.getInputStream();

			String fileName = "";
			Pattern p = Pattern.compile("filename=\".*\"");
			Matcher m = p.matcher(CDHeader);
			if (m.find()) {
				String name = m.group();
				fileName = name.substring(name.indexOf("\"") + 1, name.length() - 1);
			}

			file.setFileName(fileName);
			file.setFileBytes(IOUtil.toByte(is));
		}

		return file;
	}

	public synchronized static UploadFileInfo extract(InMultiPart inMP, String uploadFileName) {
		UploadFileInfo file = new UploadFileInfo();
		if (inMP.hasNext()) {
			InPart part = inMP.next();
			InputStream is = part.getInputStream();
			file.setFileName(uploadFileName);
			file.setFileBytes(IOUtil.toByte(is));
		}

		return file;
	}

}
