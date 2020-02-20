/*
 * $Id: PoiSheet.java,v 1.5 2014/02/06 06:51:24 Takuya-Kobayashi Exp $
 */
package nis.framework.old.excel.poi;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFCellUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

@SuppressWarnings("deprecation")
public class PoiSheet {

	protected HSSFWorkbook workbook;
	protected HSSFSheet sheet;
	protected PoiNamedCell namedCell;
	protected Map<String, PoiNamedCell> namedMap;

	protected PoiSheet(HSSFWorkbook workbook, HSSFSheet sheet) {
		this.workbook = workbook;
		this.sheet = sheet;
		initNamedMap();
	}

	public HSSFSheet getHssf() {
		return sheet;
	}

	public int index() {
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			if (sheet == workbook.getSheetAt(i)) {
				return i;
			}
		}
		return -1;
	}

	public void remove() {
		workbook.removeSheetAt(index());
	}

	public void rename(String name) {
		workbook.setSheetName(index(), name);
	}

	public String getName() {
		return workbook.getSheetName(index());
	}

	public PoiRow getRow(int i) {
		return new PoiRow(workbook, sheet, HSSFCellUtil.getRow(i, sheet));
	}

	public PoiRow newRow(int i) {
		sheet.shiftRows(i, sheet.getLastRowNum()+1, 1, true, false);
		return getRow(i);
	}
	
	public void removeRow(int i){
		getRow(i).remove();
		sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
	}

	public PoiNamedCell getNamedCell(String name) {
		return namedMap.get(name);
	}

	public void mergeRegion(int firstRow, int lastRow, int firstCol, int lastCol) {
		sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
	}

	public void setForceFormulaRecalculation(boolean flag) {
		sheet.setForceFormulaRecalculation(flag);
	}

	@Override
	public PoiSheet clone() {
		return new PoiSheet(workbook, workbook.cloneSheet(index()));
	}

	private void initNamedMap() {
		namedMap = new HashMap<String, PoiNamedCell>();

		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			initNamedMap(sheet.getRow(i));
		}
	}

	private void initNamedMap(HSSFRow row) {
		if (row == null) {
			return;
		}
		for (int i = 0; i < row.getLastCellNum(); i++) {
			initNamedMap(row, row.getCell(i));
		}
	}

	private void initNamedMap(HSSFRow row, HSSFCell cell) {
		if (cell == null || cell.getCellType() != HSSFCell.CELL_TYPE_STRING) {
			return;
		}
		if (PoiNamedCell.isNamedCell(cell)) {
			PoiNamedCell namedCell = new PoiNamedCell(workbook, sheet, row, cell);
			namedMap.put(namedCell.getName(), namedCell);
		}
	}

	public void areaCopy(int copyStartRowIndex, int copyEndRowIndex, int pasteStartRowIndex){
		areaCopy(this, copyStartRowIndex, copyEndRowIndex, pasteStartRowIndex);
    }
	
	public void areaCopy(PoiSheet copyPoiSheet, int copyStartRowIndex, int copyEndRowIndex, int pasteStartRowIndex){
		Sheet copySheet = copyPoiSheet.getHssf();
        Row row = null, row2 = null;
        Cell cell = null, cell2 = null;
        CellStyle cellstyle = null;
        int x = copySheet.getNumMergedRegions();
        short height = 0;
        for(int i = copyStartRowIndex; i < copyEndRowIndex; i++){
            row = copySheet.getRow(i);
            height = row.getHeight();
            if (row != null){
                row2 = sheet.createRow(pasteStartRowIndex + i);
                row2.setHeight(height);
                for(int j = 0; j < row.getLastCellNum(); j++){
                    cell = row.getCell(j);
                    if(cell != null){
                        cell2 = row2.createCell(j);
                        cellstyle = cell.getCellStyle();
                        cell2.setCellStyle(cellstyle);
                        switch(cell.getCellType()) {
                            case Cell.CELL_TYPE_STRING:
                                cell2.setCellValue(cell.getRichStringCellValue());
                            break;
                            case Cell.CELL_TYPE_NUMERIC:
                                if(DateUtil.isCellDateFormatted(cell)) {
                                    cell2.setCellValue(cell.getDateCellValue());
                                }else{
                                    cell2.setCellValue(cell.getNumericCellValue());
                                }
                            break;
                            case Cell.CELL_TYPE_FORMULA:
                                cell2.setCellFormula(cell.getCellFormula());
                            break;
                            case Cell.CELL_TYPE_BOOLEAN:
                                cell2.setCellValue(cell.getBooleanCellValue());
                            break;
                        }
                    }
                }
            }
        }
        CellRangeAddress cra = null;
        for(int i = 0; i < x; i++){
            cra = copySheet.getMergedRegion(i).copy();
            if (cra.getFirstRow() > copyStartRowIndex){
                cra.setFirstRow(cra.getFirstRow() + pasteStartRowIndex);
                cra.setLastRow(cra.getLastRow() + pasteStartRowIndex);
                sheet.addMergedRegion(cra);
            }
        }
    }
	
}
