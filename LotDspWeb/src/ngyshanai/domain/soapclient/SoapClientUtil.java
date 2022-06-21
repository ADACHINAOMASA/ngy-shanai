package ngyshanai.domain.soapclient;

public class SoapClientUtil {
	
	public static String getAuthInfo(String termID, String userID, String useKbn) {
		return termID + "," + userID + "," + useKbn;
	}

}
