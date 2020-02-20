/*
 * $Id: PoiCellStyle.java,v 1.1 2013/04/30 05:44:38 kengo-nagase Exp $
 */
package nis.framework.old.excel.poi.ss;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;

public class PoiCellStyle {

	protected Workbook workbook;
	protected CellStyle style;

	protected PoiCellStyle(Workbook workbook, CellStyle style) {
		this.workbook = workbook;
		this.style = style;
	}

	public CellStyle getCellStyle() {
		return style;
	}

	public PoiFont getFont() {
		return new PoiFont(workbook, workbook.getFontAt(style.getFontIndex()));
	}

	public void setFont(PoiFont font) {
		style.setFont(font.getFont());
	}

	public void copy(PoiCellStyle from) {
		CellStyle fromStyle = from.getCellStyle();
		style.cloneStyleFrom(fromStyle);
	}

	@Override
	public PoiCellStyle clone() {
		PoiCellStyle clone = new PoiCellStyle(workbook, workbook.createCellStyle());
		clone.copy(this);
		return clone;
	}

}
