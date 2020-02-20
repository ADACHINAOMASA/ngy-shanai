package lotdsp.common.util;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

public class UriInfoSupport {
	
	private UriInfo uriInfo;

	private UriInfoSupport(UriInfo uriInfo) {
		this.uriInfo = uriInfo;
		Assertions.assertArgumentNotNull(uriInfo, "uriInfo is null object.");
	}

	public static UriInfoSupport uriInfo(UriInfo uriInfo) {
		return new UriInfoSupport(uriInfo);
	}

	public UriInfo getUriInfo() {
		return uriInfo;
	}

	public String valueOf(String key) {
		MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
		return queryParams.getFirst(key);
	}

}
