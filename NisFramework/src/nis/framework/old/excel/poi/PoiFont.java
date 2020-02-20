/*
 * $Id: PoiFont.java,v 1.1 2013/04/30 05:44:34 kengo-nagase Exp $
 */
package nis.framework.old.excel.poi;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class PoiFont {

	protected HSSFWorkbook workbook;
	protected HSSFFont font;

	protected PoiFont(HSSFWorkbook workbook, HSSFFont font) {
		this.workbook = workbook;
		this.font = font;
	}

	public HSSFFont getHssf() {
		return font;
	}

	@SuppressWarnings("deprecation")
	public void copy(PoiFont from) {
		HSSFFont fromFont = from.getHssf();

		font.setBoldweight(fromFont.getBoldweight());
		font.setColor(fromFont.getColor());
		font.setFontHeight(fromFont.getFontHeight());
		font.setFontHeightInPoints(fromFont.getFontHeightInPoints());
		font.setFontName(fromFont.getFontName());
		font.setItalic(fromFont.getItalic());
		font.setStrikeout(fromFont.getStrikeout());
		font.setTypeOffset(fromFont.getTypeOffset());
		font.setUnderline(fromFont.getUnderline());
	}

	@Override
	public PoiFont clone() {
		PoiFont clone = new PoiFont(workbook, workbook.createFont());
		clone.copy(this);
		return clone;
	}

}
