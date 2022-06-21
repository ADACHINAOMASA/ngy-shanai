package ngyshanai.common.excel;

public enum ExcelType {

	EXCEL_2003(1, ".xls", "エクセル2003以前"), 
	EXCEL_2007(2, ".xlsx", "エクセル2007以降"), 
	EXCEL_2007_MACRO(3, ".xlsm","エクセル2007以降 マクロ有効");

	private int id;
	private String ext;
	private String comment;

	ExcelType(int id, String ext, String comment) {
		this.id = id;
		this.ext = ext;
		this.comment = comment;
	}

	public int id() {
		return id;
	}

	public String extension() {
		return ext;
	}

	public String comment() {
		return comment;
	}

	public static boolean isExcel2003(int id) {
		return (EXCEL_2003.id == id);
	}

	public static boolean isExcel2007(int id) {
		return (EXCEL_2007.id == id);
	}

	public static boolean isExcel2007Macro(int id) {
		return (EXCEL_2007_MACRO.id == id);
	}

	public static String extensionOf(int id) {
		for (ExcelType t : values()) {
			if (t.id() == id) {
				return t.extension();
			}
		}
		return EXCEL_2003.extension();
	}

}
