/*
 * $Id: PoiSheet.java,v 1.2 2013/05/10 09:33:34 kengo-nagase Exp $
 */
package nis.framework.old.excel.poi.ss;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;

public class PoiSheet {

	protected Workbook workbook;
	protected Sheet sheet;
	protected PoiNamedCell namedCell;
	protected Map<String, PoiNamedCell> namedMap;

	protected PoiSheet(Workbook workbook, Sheet sheet) {
		this.workbook = workbook;
		this.sheet = sheet;
		initNamedMap();
	}

	public Sheet getSheet() {
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
		return new PoiRow(workbook, sheet, CellUtil.getRow(i, sheet));
	}

	public PoiRow newRow(int i) {
		sheet.shiftRows(i, sheet.getLastRowNum()+1, 1, true, false);
		return getRow(i);
	}

	public PoiNamedCell getNamedCell(String name) {
		return namedMap.get(name);
	}

	/* �Z���̌��� */
	public void mergeRegion(int firstRow, int lastRow, int firstCol, int lastCol) {
		mergeRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
	}
	
	/* �Z���̌��� */
	public void mergeRegion(CellRangeAddress region) {
		sheet.addMergedRegion(region);
	}

	
	// �w��͈̗͂�
	public void setColumnsWidth(int firstColIndex, int lastColIndex, int width) {
		for (int i = firstColIndex; i <= lastColIndex; i++) {
			sheet.setColumnWidth(i,width);
		}
	}

//	public void setForceFormulaRecalculation(boolean flag) {
//		sheet.setForceFormulaRecalculation(flag);
//	}

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

	private void initNamedMap(Row row) {
		if (row == null) {
			return;
		}
		for (int i = 0; i < row.getLastCellNum(); i++) {
			initNamedMap(row, row.getCell(i));
		}
	}
	
	@SuppressWarnings("deprecation")
	private void initNamedMap(Row row, Cell cell) {
		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_STRING) {
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
	@SuppressWarnings("deprecation")
	public void areaCopy(PoiSheet copyPoiSheet, int copyStartRowIndex, int copyEndRowIndex, int pasteStartRowIndex){
		Sheet copySheet = copyPoiSheet.getSheet();
        Row row = null, row2 = null;
        Cell cell = null, cell2 = null;
        CellStyle cellstyle = null;
        int x = copySheet.getNumMergedRegions();
        short height = 0;
        for(int i = copyStartRowIndex; i < copyEndRowIndex; i++){
            row = copySheet.getRow(i);
            if (row != null){
            	height = row.getHeight();
                row2 = sheet.createRow(pasteStartRowIndex + i);
                row2.setHeight(height);
                for(int j = 0; j < row.getLastCellNum(); j++){
                    cell = row.getCell(j);
                    if(cell != null){
                        cell2 = row2.createCell(j);
                        cellstyle = cell.getCellStyle();
                        if(copyPoiSheet.workbook.equals(workbook)){
                        	cell2.setCellStyle(cellstyle);
                        }else{
//                        	cell2.getCellStyle().cloneStyleFrom(cellstyle);
                        }
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
