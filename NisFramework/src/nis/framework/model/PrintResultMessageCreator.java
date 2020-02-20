package nis.framework.model;

import javax.servlet.http.HttpServletRequest;

import nis.framework.web.WebConst;

public class PrintResultMessageCreator {

	public synchronized static PrintResultMessage createPrintMessage(byte[] data, String fileName, int type) {
		PrintResultMessage msg = new PrintResultMessage();
		msg.setResult(data);
		msg.setPdfName(fileName);

		if (PrintResultMessage.WDP == type) {
			msg.setResultType(PrintResultMessage.WDP);
		} else if (PrintResultMessage.WDD == type) {
			msg.setResultType(PrintResultMessage.WDD);
		} else if (PrintResultMessage.PDF == type) {
			msg.setResultType(PrintResultMessage.PDF);
		} else {
			msg.setResultType(PrintResultMessage.NONE);
		}

		return msg;
	}

	public synchronized static PrintResultMessage savePrintResultMessage(byte[] data, String fileName, HttpServletRequest request) {
		return createPrintMessage(data, fileName, PrintResultMessage.PDF, request);
	}
	
	public synchronized static PrintResultMessage createPrintMessage(byte[] data, String fileName, int type,HttpServletRequest request) {
		PrintResultMessage msg = createPrintMessage(data, fileName, type);
		// PDFまたはWDPをセッションに保持
		request.getSession().setAttribute(WebConst.ObjectKey.PDF_INFORMATION, msg);
		return msg;
	}

}
