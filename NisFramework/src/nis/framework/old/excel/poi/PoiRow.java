/*
 * $Id: PoiRow.java,v 1.3 2014/02/06 06:51:24 Takuya-Kobayashi Exp $
 */
package nis.framework.old.excel.poi;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFCellUtil;

@SuppressWarnings("deprecation")
public class PoiRow {

	protected HSSFWorkbook workbook;
	protected HSSFSheet sheet;
	protected HSSFRow row;
	protected Map<String, PoiNamedCell> namedMap;

	protected PoiRow(HSSFWorkbook workbook, HSSFSheet sheet, HSSFRow row) {
		this.workbook = workbook;
		this.sheet = sheet;
		this.row = row;
		initNamedMap();
	}

	public HSSFRow getHssf() {
		return row;
	}

	public int index() {
		return row.getRowNum();
	}

	public void remove() {
		sheet.removeRow(row);
	}

	public void clear() {
		for (int i = 0; i < row.getLastCellNum(); i++) {
			HSSFCell cell = row.getCell(i);
			if (cell != null) {
				new PoiCell(workbook, sheet, row, cell).clear();
			}
		}
	}

	public PoiCell getCell(int i) {
		return new PoiCell(workbook, sheet, row, HSSFCellUtil.getCell(row, i));
	}

	public PoiNamedCell getNamedCell(String name) {
		return namedMap.get(name);
	}

	public void copy(PoiRow from) {
		HSSFRow fromRow = from.getHssf();
		row.setHeightInPoints(fromRow.getHeightInPoints());
		row.setHeight(fromRow.getHeight());
		for (int i = 0; i < row.getLastCellNum() || i < fromRow.getLastCellNum(); i++) {
			getCell(i).copy(from.getCell(i));
		}
		initNamedMap();
	}

	private void initNamedMap() {
		namedMap = new HashMap<String, PoiNamedCell>();
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

}
