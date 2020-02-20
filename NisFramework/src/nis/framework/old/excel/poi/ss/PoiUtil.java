package nis.framework.old.excel.poi.ss;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;

public class PoiUtil {

	public static CellStyle createNumStyle(PoiBook book) {
		DataFormat format = book.getXssfWorkbook().createDataFormat();

		CellStyle numStyle = book.getXssfWorkbook().createCellStyle();
		numStyle.setDataFormat(format.getFormat("#,##0;[RED]-#,##0"));
		return numStyle;
	}

}
