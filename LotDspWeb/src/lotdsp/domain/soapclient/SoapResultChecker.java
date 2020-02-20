package lotdsp.domain.soapclient;

import java.util.Map;

import lotdsp.common.lotdsp.ErrorInfo;
import lotdsp.domain.logic.hososhiyosho.test.CommonMessage;

public class SoapResultChecker {

	public synchronized static boolean check(int result, String setsubi, ErrorInfo info) {

		Map<?, ?> map = CommonMessage.getSoapMessage(setsubi, result);

		if (map.get("msgStr") == null) {
			return true;
		} else {
			info.setErrorMessage(map.get("msgStr").toString());
			return false;
		}
		
	}

}
