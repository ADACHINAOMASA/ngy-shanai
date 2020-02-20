/*
 * $Id: PoiCell.java,v 1.1 2013/04/30 05:44:38 kengo-nagase Exp $
 */
package nis.framework.old.excel.poi.ss;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;

//import org.apache.poi.xssf.usermodel.XSSFRichTextString;

public class PoiCell {

	protected Workbook workbook;
	protected Sheet sheet;
	protected Row row;
	protected Cell cell;

	protected PoiCell(Workbook workbook, Sheet sheet, Row row, Cell cell) {
		this.workbook = workbook;
		this.sheet = sheet;
		this.row = row;
		this.cell = cell;
	}

	public Cell getCell() {
		return cell;
	}

	public int index() {
		return cell.getColumnIndex();
	}

	public void remove() {
		row.removeCell(cell);
	}

	public void setString(String value) {
		CreationHelper createHelper = workbook.getCreationHelper();
		cell.setCellValue(createHelper.createRichTextString(value));
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
		CreationHelper createHelper = workbook.getCreationHelper();
		cell.setCellValue(createHelper.createRichTextString(""));
	}

	public PoiCellStyle getCellStyle() {
		return new PoiCellStyle(workbook, cell.getCellStyle());
	}

	public void setCellStyle(PoiCellStyle style) {
		cell.setCellStyle(style.getCellStyle());
	}

	public PoiCellStyle newCellStyle() {
		CellStyle newstyle = workbook.createCellStyle();
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
		return cell.getCellType() == Cell.CELL_TYPE_BLANK;
	}

	@SuppressWarnings("deprecation")
	public boolean isBooleanCell() {
		return cell.getCellType() == Cell.CELL_TYPE_BOOLEAN;
	}

	@SuppressWarnings("deprecation")
	public boolean isErrorCell() {
		return cell.getCellType() == Cell.CELL_TYPE_ERROR;
	}

	@SuppressWarnings("deprecation")
	public boolean isFormulaCell() {
		return cell.getCellType() == Cell.CELL_TYPE_FORMULA;
	}

	@SuppressWarnings("deprecation")
	public boolean isNumericCell() {
		return cell.getCellType() == Cell.CELL_TYPE_NUMERIC;
	}

	@SuppressWarnings("deprecation")
	public boolean isStringCell() {
		return cell.getCellType() == Cell.CELL_TYPE_STRING;
	}

	@SuppressWarnings("deprecation")
	public void copy(PoiCell from) {
		Cell fromCell = from.getCell();

		cell.setCellStyle(fromCell.getCellStyle());
		cell.setCellType(fromCell.getCellType());

		switch (fromCell.getCellType()) {
			case Cell.CELL_TYPE_BLANK:
				cell.setCellValue((RichTextString) null);
				break;

			case Cell.CELL_TYPE_BOOLEAN:
				cell.setCellValue(fromCell.getBooleanCellValue());
				break;

			case Cell.CELL_TYPE_ERROR:
				cell.setCellErrorValue(fromCell.getErrorCellValue());
				break;

			case Cell.CELL_TYPE_FORMULA:
				cell.setCellFormula(fromCell.getCellFormula());
				break;

			case Cell.CELL_TYPE_NUMERIC:
				cell.setCellValue(fromCell.getNumericCellValue());
				break;

			case Cell.CELL_TYPE_STRING:
				CreationHelper createHelper = workbook.getCreationHelper();
				cell.setCellValue(createHelper.createRichTextString(fromCell.getRichStringCellValue().getString()));
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
