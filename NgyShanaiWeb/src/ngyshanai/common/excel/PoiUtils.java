package ngyshanai.common.excel;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.ClientAnchor.AnchorType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFPrintSetup;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public final class PoiUtils {

	private PoiUtils() {
	}

	// Print
	// ------------------------------------------------------------
	public static void allColumnOnOnePage(Sheet sheet) {
		sheet.setFitToPage(true);
		XSSFPrintSetup ps = (XSSFPrintSetup) sheet.getPrintSetup();
		ps.setFitWidth((short) 1);
		ps.setFitHeight((short) 0);
	}

	public static void fitToOnePage(Sheet sheet) {
		sheet.setFitToPage(true);
		XSSFPrintSetup ps = (XSSFPrintSetup) sheet.getPrintSetup();
		ps.setFitHeight((short) 1);
		ps.setFitWidth((short) 1);
	}

	public static void allRowOnOnePage(Sheet sheet) {
		sheet.setFitToPage(true);
		XSSFPrintSetup ps = (XSSFPrintSetup) sheet.getPrintSetup();
		ps.setFitWidth((short) 0);
		ps.setFitHeight((short) 1);
	}

	public static void setScale(Sheet sheet, int scale) {
		XSSFPrintSetup ps = (XSSFPrintSetup) sheet.getPrintSetup();
		ps.setScale((short) scale);
	}

	public static void setActiveCell(Sheet sheet, int row, int col) {
		sheet.setActiveCell(new CellAddress(row, col));
	}

	public static void setDefaultActiveCell(Sheet sheet) {
		setActiveCell(sheet, 0, 0);
	}

	// image
	// -----------------------------------------------------------------------------------------------------------------

	public static Picture insertPNGImageAt(final Row row, final int colIndex, final byte[] imageData) {
		return insertImageAt(row.getSheet(), row.getRowNum(), colIndex, imageData, Workbook.PICTURE_TYPE_PNG, 1.0);
	}

	public static Picture insertImageAt(final Sheet sheet, final int row, final int col, final byte[] imageData,
			final int imageType, final double ratio) {
		if (sheet == null) {
			throw new IllegalArgumentException("sheet is null");
		}
		final Workbook book = sheet.getWorkbook();
		final Drawing patriarch = sheet.createDrawingPatriarch();

		final ClientAnchor anchor = patriarch.createAnchor(XSSFShape.EMU_PER_PIXEL * 9, XSSFShape.EMU_PER_PIXEL * 3,
				XSSFShape.EMU_PER_PIXEL * -9, XSSFShape.EMU_PER_PIXEL * -3, col, row, col + 1, row + 2);
		anchor.setAnchorType(AnchorType.MOVE_AND_RESIZE);
		Picture pic = patriarch.createPicture(anchor, book.addPicture(imageData, imageType));
		//pic.resize();
		return pic;
	}

	/**
	 * 数式を再計算 Excel Ver 2003
	 * 
	 * @param sheet
	 */
	public static void recalFormula(Sheet sheet) {
		org.apache.poi.hssf.usermodel.HSSFSheet hssfSheet = (org.apache.poi.hssf.usermodel.HSSFSheet) sheet;
		hssfSheet.setForceFormulaRecalculation(true);
	}

	/**
	 * 数式を再計算 Excel Ver 2007
	 * 
	 * @param sheet
	 */
	public static void recalculateFormula(final Sheet sheet) {
		XSSFFormulaEvaluator.evaluateAllFormulaCells((XSSFWorkbook) sheet.getWorkbook());
	}

	// Sheet
	// --------------------------------------------------------------------------------------------------//

	public static Sheet cloneSheet(Workbook wb, int sheetNo, String sheetName) {
		Sheet sheet = wb.cloneSheet(sheetNo);
		setSheetName(wb, sheet, sheetName);
		return sheet;
	}

	public static Sheet getSheet(Workbook wb, int sheetNo, String newSheetName) {
		wb.setSheetName(sheetNo, newSheetName);
		Sheet sheet = wb.getSheetAt(sheetNo);
		return sheet;
	}

	public static Sheet getSheet(Workbook wb, int sheetNo) {
		return wb.getSheetAt(sheetNo);
	}

	public static void setSheetName(Workbook wb, Sheet sheet, String sheeName) {
		wb.setSheetName(wb.getSheetIndex(sheet), sheeName);
	}

	// ROW-----------------------------------------------------------------------------------------------//
	
	public static void mergeRowCol(Sheet sheet, ExcelRow firstRow, ExcelRow lastRow, ExcelColumn firstCol, ExcelColumn lastCol) {
		sheet.addMergedRegion(new CellRangeAddress(firstRow.ordinal(), lastRow.ordinal(), firstCol.ordinal(), lastCol.ordinal()));
	}
	
	public static void mergeRowCol(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol) {
		sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
	}

	public static void mergeCol(Row row, int firstCol, int lastCol) {
		row.getSheet().addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), firstCol, lastCol));
	}

	public static void removeRow(Sheet sheet, int rowNo) {
		sheet.removeRow(sheet.getRow(rowNo));
	}

	public static void deleteRow(Sheet sheet, int rowNo) {
		sheet.removeRow(sheet.getRow(rowNo));
		for (int currentRow = (rowNo + 1); currentRow <= sheet.getLastRowNum() + 1; currentRow++) {
			sheet.shiftRows(currentRow, currentRow, -1, true, false);
		}
	}

	public static void deleteRow(Row row) {
		deleteRow(row.getSheet(), row.getRowNum());
	}

	public static Row getRow(Sheet sheet, ExcelRow row) {
		return getRow(sheet, row.ordinal());
	}
	
	public static Row getRow(Sheet sheet, int row) {
		if (sheet.getRow(row) == null) {
			return sheet.createRow(row);
		}
		return sheet.getRow(row);
	}

	public static Row shiftToNextRow(Row currentRow) {
		Sheet sheet = currentRow.getSheet();
		sheet.shiftRows(currentRow.getRowNum(), currentRow.getRowNum(), 1, true, false);
		return getRow(sheet, currentRow.getRowNum() + 1);
	}

	public static void copyToNextRow(Row currentRow) {
		copyRow(currentRow.getSheet(), currentRow.getRowNum(), currentRow.getSheet(), currentRow.getRowNum() + 1, true);
	}

	public static void copyRow(Row sourceRow, Row destRow) {
		copyRow(sourceRow.getSheet(), sourceRow.getRowNum(), destRow.getSheet(), destRow.getRowNum(), true);
	}

	public static void copyRow(Sheet sourceSheet, int sourceRowNum, int destRowNum, boolean shiftDestRowIfExist) {
		copyRow(sourceSheet, sourceRowNum, sourceSheet, destRowNum, shiftDestRowIfExist);
	}

	public static void copyRow(Sheet sourceSheet, int sourceRowNum, Sheet destSheet, int destRowNum) {
		copyRow(sourceSheet, sourceRowNum, destSheet, destRowNum, true);
	}
	
	public static void copyRow(Sheet sourceSheet, ExcelRow sourceRow, Sheet destSheet, int destRowNum) {
		copyRow(sourceSheet, sourceRow.ordinal(), destSheet, destRowNum, true);
	}

	public static void copyRow(Sheet sourceSheet, int sourceRowNum, Sheet destSheet, int destRowNum,
			boolean shiftDestRowIfExist) {
		Row sourceRow = sourceSheet.getRow(sourceRowNum);
		Row destRow = destSheet.getRow(destRowNum);

		// If the row exist in destination, push down all rows by 1 else create
		// new row
		if (shiftDestRowIfExist) {
			if (destRow != null) {
				destSheet.shiftRows(destRowNum, destSheet.getLastRowNum(), 1, true, false);
			}
			destRow = destSheet.createRow(destRowNum);
		} else {
			destRow = destSheet.createRow(destRowNum);
		}

		destRow.setHeightInPoints(sourceRow.getHeightInPoints());
		destRow.setHeight(sourceRow.getHeight());

		for (int i = 0; i < sourceRow.getLastCellNum(); i++) {
			Cell oldCell = sourceRow.getCell(i);
			Cell newCell = destRow.createCell(i);

			if (oldCell == null) {
				newCell = null;
				continue;
			}

			destSheet.setColumnWidth(i, sourceSheet.getColumnWidth(i));
			destSheet.setDefaultColumnStyle(i, sourceSheet.getColumnStyle(i));

			copyCell(oldCell, newCell);
		}

		// If there are are any merged regions in the source row, copy to new
		// row
		for (int i = 0; i < sourceSheet.getNumMergedRegions(); i++) {
			CellRangeAddress cellRangeAddress = sourceSheet.getMergedRegion(i);
			if (cellRangeAddress.getFirstRow() == sourceRow.getRowNum()) {
				CellRangeAddress newCellRangeAddress = new CellRangeAddress(destRow.getRowNum(),
						(destRow.getRowNum() + (cellRangeAddress.getLastRow() - cellRangeAddress.getFirstRow())),
						cellRangeAddress.getFirstColumn(), cellRangeAddress.getLastColumn());
				destSheet.addMergedRegion(newCellRangeAddress);
			}
		}
	}

	// Cell
	// ---------------------------------------------------------------------------------------------//
	public static void copyCell(Sheet sheet, int currentRow, int fromCol, int toCol) {
		Row row = getRow(sheet, currentRow);
		copyCell(row, fromCol, toCol);
	}

	public static void copyCell(Row currentRow, int fromCol, int toCol) {
		Cell from = getCell(currentRow, fromCol);
		Cell to = getCell(currentRow, toCol);
		copyCell(from, to);
	}

	@SuppressWarnings("deprecation")
	public static void copyCell(Cell from, Cell to) {
		if (from == null || to == null) {
			throw new NullPointerException("Cell cannot be null.");
		}
		to.setCellStyle(from.getCellStyle());
		to.setCellType(from.getCellTypeEnum());

		CellStyle newCellStyle = from.getSheet().getWorkbook().createCellStyle();
		newCellStyle.cloneStyleFrom(from.getCellStyle());
		to.setCellStyle(newCellStyle);

		if (to.getCellComment() != null) {
			to.setCellComment(from.getCellComment());
		}

		if (from.getHyperlink() != null) {
			to.setHyperlink(from.getHyperlink());
		}

		to.setCellType(from.getCellType());

		switch (from.getCellTypeEnum()) {
		case STRING:
			to.setCellValue(from.getRichStringCellValue());
			break;
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(from)) {
				to.setCellValue(from.getDateCellValue());
			} else {
				to.setCellValue(from.getNumericCellValue());
			}
			break;
		case BOOLEAN:
			to.setCellValue(from.getBooleanCellValue());
			break;
		case FORMULA:
			to.setCellFormula(from.getCellFormula());
			if (to.getCellFormula() == null || "".equals(to.getCellFormula())) {
				// ignore
			} else {
				String format = to.getCellFormula().replace(String.valueOf(from.getRow().getRowNum() + 1),
						String.valueOf(to.getRow().getRowNum() + 1));
				to.setCellFormula(format);
			}
			break;
		case ERROR:
			to.setCellErrorValue(from.getErrorCellValue());
			break;
		case BLANK:
			break;
		default:
		}
	}

	public static Cell getCell(Sheet sheet, int row, int col) {
		return getCell(getRow(sheet, row), col);
	}

	public static Cell getCell(Row row, int col) {
		if (row.getCell(col) == null) {
			return row.createCell(col);
		}
		return row.getCell(col);
	}

	public static Cell getCell(Row row, ExcelColumn col) {
		return getCell(row, col.ordinal());
	}

	// CellValue Getter
	// -----------------------------------------------------------------------
	@SuppressWarnings({ "deprecation" })
	public static Object getCellValue(Cell cell) {
		if (cell == null) {
			return null;
		}
		Object obj = null;
		switch (cell.getCellTypeEnum()) {
		case STRING:
			obj = cell.getRichStringCellValue();
			break;
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				obj = cell.getDateCellValue();
			} else {
				obj = cell.getNumericCellValue();
			}
			break;
		case BOOLEAN:
			obj = cell.getBooleanCellValue();
			break;
		case FORMULA:
			obj = cell.getCellFormula();
			break;
		case ERROR:
			obj = cell.getErrorCellValue();
			break;
		case BLANK:
			break;
		default:
		}
		return obj;
	}

	// CellValue Setter
	// -----------------------------------------------------------------------
	public static void setCellValue(final Cell cell, final String value) {
		if (value != null) {
			cell.setCellValue(value);
		}
	}

	public static void setCellValue(Cell cell, BigDecimal value) {
		if (value != null) {
			cell.setCellValue(value.doubleValue());
		}
	}

	public static void setCellValue(Cell cell, BigDecimal value, CellStyle style) {
		if (value != null) {
			cell.setCellValue(value.doubleValue());
			cell.setCellStyle(style);
		}
	}

	public static void setCellValue(Cell cell, Date value) {
		if (value != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			cell.setCellValue(calendar);
		}
	}

	public static void setCellValue(Cell cell, Object value) {
		if (value != null) {
			if (value instanceof String) {
				setCellValue(cell, (String) value);
			}
			if (value instanceof BigDecimal) {
				setCellValue(cell, (BigDecimal) value);
			}
			if (value instanceof Date) {
				setCellValue(cell, (Date) value);
			}
		}
	}

	public static void setCellValue(Sheet sheet, ExcelRow row, ExcelColumn col, Object value) {
		setCellValue(sheet, row.ordinal(), col, value);
	}

	public static void setCellValue(Sheet sheet, int rowIndex, ExcelColumn col, Object value) {

		if (value != null) {
			Row row = getRow(sheet, rowIndex);

			Cell cell = getCell(row, col);

			if (value instanceof String) {
				setCellValue(cell, (String) value);
			}
			if (value instanceof BigDecimal) {
				setCellValue(cell, (BigDecimal) value);
			}
			if (value instanceof Date) {
				setCellValue(cell, (Date) value);
			}
		}
	}

	public static void setCellValue(Row row, ExcelColumn col, Object value) {

		if (value != null) {
			Cell cell = getCell(row, col);

			if (value instanceof String) {
				setCellValue(cell, (String) value);
			}
			if (value instanceof BigDecimal) {
				setCellValue(cell, (BigDecimal) value);
			}
			if (value instanceof Date) {
				setCellValue(cell, (Date) value);
			}
		}
	}

	public static void setCellValue(Row row, ExcelColumn col, Object value, CellStyle style) {
		if (value != null) {
			Cell cell = getCell(row, col);

			if (value instanceof String) {
				setCellValue(cell, (String) value);
			}
			if (value instanceof BigDecimal) {
				setCellValue(cell, (BigDecimal) value);
			}
			if (value instanceof Date) {
				setCellValue(cell, (Date) value);
			}

			cell.setCellStyle(style);
		}
	}

	// CellStyle
	// ---------------------------------------------------------------------------
	public static void setCellStyle(Row row, int col, CellStyle style) {
		Cell cell = getCell(row, col);
		cell.setCellStyle(style);
	}

	// CellFormula
	// ---------------------------------------------------------------------------
	public static void setCellFormula(Row row, ExcelColumn col, String formula) {
		setCellFormula(row, col.ordinal(), formula);
	}

	public static void setCellFormula(Row row, int col, String formula) {
		Cell cell = getCell(row, col);
		cell.setCellFormula(formula);
	}

}
