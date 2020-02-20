/*
 * $Id: PoiCell.java,v 1.3 2014/02/06 06:51:24 Takuya-Kobayashi Exp $
 */
package nis.framework.old.excel.poi;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellReference;

public class PoiCell {

	protected HSSFWorkbook workbook;
	protected HSSFSheet sheet;
	protected HSSFRow row;
	protected HSSFCell cell;

	protected PoiCell(HSSFWorkbook workbook, HSSFSheet sheet, HSSFRow row, HSSFCell cell) {
		this.workbook = workbook;
		this.sheet = sheet;
		this.row = row;
		this.cell = cell;
	}

	public HSSFCell getHssf() {
		return cell;
	}

	public int index() {
		return cell.getColumnIndex();
	}

	public void remove() {
		row.removeCell(cell);
	}

	public void setString(String value) {
		cell.setCellValue(new HSSFRichTextString(value));
	}

	public void setBigDecimal(BigDecimal value) {
		if (value == null) {
//			clear();
		}
		else {
			cell.setCellValue(value.doubleValue());
		}
	}

	public void setDate(Date value) {
		if (value == null) {
//			clear();
		}
		else {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			cell.setCellValue(calendar);
		}
	}

	public void setFormula(String formula) {
		cell.setCellFormula(formula);
	}

	public String getString() {
		return cell.getRichStringCellValue().getString();
	}

	public BigDecimal getBigDecimal() {
		return new BigDecimal(cell.getNumericCellValue());
	}

	public Date getDate() {
		return cell.getDateCellValue();
	}

	public String getFormula() {
		return cell.getCellFormula();
	}

	public void clear() {
		cell.setCellValue(new HSSFRichTextString());
	}

	public PoiCellStyle getCellStyle() {
		return new PoiCellStyle(workbook, cell.getCellStyle());
	}

	public void setCellStyle(PoiCellStyle style) {
		cell.setCellStyle(style.getHssf());
	}

	public PoiCellStyle newCellStyle() {
		HSSFCellStyle newstyle = workbook.createCellStyle();
		newstyle.cloneStyleFrom(cell.getCellStyle());
		cell.setCellStyle(newstyle);
		return new PoiCellStyle(workbook, newstyle);
	}

	public int getWidth() {
		return sheet.getColumnWidth(index());
	}

	public void setWidth(int width) {
		sheet.setColumnWidth(index(), width);
	}

	public boolean isHidden() {
		return sheet.isColumnHidden(index());
	}

	public void setHidden(boolean hidden) {
		sheet.setColumnHidden(index(), hidden);
	}

	@SuppressWarnings("deprecation")
	public boolean isBlankCell() {
		return cell.getCellType() == HSSFCell.CELL_TYPE_BLANK;
	}
	@SuppressWarnings("deprecation")
	public boolean isBooleanCell() {
		return cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN;
	}
	@SuppressWarnings("deprecation")
	public boolean isErrorCell() {
		return cell.getCellType() == HSSFCell.CELL_TYPE_ERROR;
	}
	@SuppressWarnings("deprecation")
	public boolean isFormulaCell() {
		return cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA;
	}
	@SuppressWarnings("deprecation")
	public boolean isNumericCell() {
		return cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC;
	}
	@SuppressWarnings("deprecation")
	public boolean isStringCell() {
		return cell.getCellType() == HSSFCell.CELL_TYPE_STRING;
	}
	@SuppressWarnings("deprecation")
	public void copy(PoiCell from) {
		HSSFCell fromCell = from.getHssf();

		cell.setCellStyle(fromCell.getCellStyle());
		cell.setCellType(fromCell.getCellType());

		switch (fromCell.getCellType()) {
			case HSSFCell.CELL_TYPE_BLANK:
				cell.setCellValue((HSSFRichTextString) null);
				break;

			case HSSFCell.CELL_TYPE_BOOLEAN:
				cell.setCellValue(fromCell.getBooleanCellValue());
				break;

			case HSSFCell.CELL_TYPE_ERROR:
				cell.setCellErrorValue(fromCell.getErrorCellValue());
				break;

			case HSSFCell.CELL_TYPE_FORMULA:
				cell.setCellFormula(fromCell.getCellFormula());
				break;

			case HSSFCell.CELL_TYPE_NUMERIC:
				cell.setCellValue(fromCell.getNumericCellValue());
				break;

			case HSSFCell.CELL_TYPE_STRING:
				cell.setCellValue(new HSSFRichTextString(fromCell.getRichStringCellValue().getString()));
				break;
		}
	}

	public String getAddress() {
		return new CellReference(row.getRowNum(), cell.getColumnIndex(), false, false).formatAsString();
	}

	public PoiRow getRow() {
		return new PoiRow(workbook, sheet, row);
	}

}
