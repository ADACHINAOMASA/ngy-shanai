package nis.framework.util;

import javax.ws.rs.core.UriInfo;

import nis.framework.properties.AppProperties;

/**
 * @author Leang-Say
 */
public class RequestUriAuthorizeUtil {

	private static final int MAX_URI = Integer.valueOf(nvl(AppProperties.get("authorized.uri-max"), "1"));
	
	private static final String URI_KEY = "authorized.uri";

	/**
	 * 許可されているURIかをチェック。
	 * @param uriInfo
	 * @return
	 */
	public synchronized static boolean isPermitted(UriInfo uriInfo) {
		
		if (uriInfo == null || uriInfo.getRequestUri() == null) {
			return false;
		}
				
		return isPermitted(uriInfo.getRequestUri().getPath());
	}

	/**
	 * 許可されているURIかをチェック。
	 * @param reqUri
	 * @return
	 */
	public synchronized static boolean isPermitted(String reqUri) {
		if (reqUri == null || "".equals(reqUri.trim())) {
			return false;
		}
		String authorizedUri = getAuthorizedUri();
				
		if (authorizedUri == null || "".equals(authorizedUri.trim())) {
			return false;
		}
		String[] uriArr = authorizedUri.split(",");
		if (uriArr == null || uriArr.length == 0) {
			return false;
		}
		for (String uri : uriArr) {
			if (uri == null || "".equals(uri.trim())) {
				continue;
			}
			
			String permittedUri = uri.replace("*", "");
						
			//部分一致
			if(uri.endsWith("*")) {
				if (reqUri.contains(permittedUri)) {
					return true;
				}
			}
			//後一致
			else {
				if (reqUri.endsWith(permittedUri)) {
					return true;
				}
			}
			
		}
		
		return false;
	}

	private static String getAuthorizedUri() {
		
		String authorizedUri = nvl(AppProperties.get(URI_KEY));
				
		for (int i = 0; i < MAX_URI; i++) {
			
			// 1から開始
			int index = i+1;
			
			//パターン１：　authorized.uri+index
			String val = nvl(AppProperties.get(URI_KEY + index));
			
			//パターン2：　authorized.uri[index]
			if ("".equals(val)) {
				val = nvl(AppProperties.get(URI_KEY + "[" + index + "]"));
			}
			
			if (!"".equals(val)) {
				if ("".equals(authorizedUri)) {
					authorizedUri = val;
				} else {
					authorizedUri = authorizedUri + "," + val;
				}
			}
			
		}
		
		return authorizedUri;
	}

	private static String nvl(String val) {
		return nvl(val, "");
	}

	private static String nvl(String val, String val1) {
		return val == null ? val1 : val.trim();
	}

}
