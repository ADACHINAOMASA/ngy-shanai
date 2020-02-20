/*
 * $Id: PoiFont.java,v 1.1 2013/04/30 05:44:38 kengo-nagase Exp $
 */
package nis.framework.old.excel.poi.ss;

import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;

public class PoiFont {

	protected Workbook workbook;
	protected Font font;

	protected PoiFont(Workbook workbook, Font font) {
		this.workbook = workbook;
		this.font = font;
	}

	public Font getFont() {
		return font;
	}

	@SuppressWarnings("deprecation")
	public void copy(PoiFont from) {
		Font fromFont = from.getFont();

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
