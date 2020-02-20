package nis.framework.properties.support;

public class StrConcator {
	
	public static String nvl(String val, String sub) {
		return (val == null || val.trim().equals("") ? sub : val);
	}
	
	public static String concat(String val1, String val2,String delemeter) {
		return nvl(val1,"").toLowerCase().concat(delemeter).concat(nvl(val2,"").toLowerCase());
	}

	public static String concat(String val1, String val2) {
		return nvl(val1,"").toLowerCase().concat("_").concat(nvl(val2,"").toLowerCase());
	}
	
}
