/*
 * $Id: PoiCellStyle.java,v 1.1 2013/04/30 05:44:34 kengo-nagase Exp $
 */
package nis.framework.old.excel.poi;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class PoiCellStyle {

	protected HSSFWorkbook workbook;
	protected HSSFCellStyle style;

	protected PoiCellStyle(HSSFWorkbook workbook, HSSFCellStyle style) {
		this.workbook = workbook;
		this.style = style;
	}

	public HSSFCellStyle getHssf() {
		return style;
	}

	public PoiFont getFont() {
		return new PoiFont(workbook, workbook.getFontAt(style.getFontIndex()));
	}

	public void setFont(PoiFont font) {
		style.setFont(font.getHssf());
	}

	public void copy(PoiCellStyle from) {
		HSSFCellStyle fromStyle = from.getHssf();
		style.cloneStyleFrom(fromStyle);
	}

	@Override
	public PoiCellStyle clone() {
		PoiCellStyle clone = new PoiCellStyle(workbook, workbook.createCellStyle());
		clone.copy(this);
		return clone;
	}

}
