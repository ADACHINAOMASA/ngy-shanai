/*
 * $Id: PoiRow.java,v 1.1 2013/04/30 05:44:38 kengo-nagase Exp $
 */
package nis.framework.old.excel.poi.ss;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellUtil;

public class PoiRow {

	protected Workbook workbook;
	protected Sheet sheet;
	protected Row row;
	protected Map<String, PoiNamedCell> namedMap;

	protected PoiRow(Workbook workbook, Sheet sheet, Row row) {
		this.workbook = workbook;
		this.sheet = sheet;
		this.row = row;
		initNamedMap();
	}

	public Row getRow() {
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
			Cell cell = row.getCell(i);
			if (cell != null) {
				new PoiCell(workbook, sheet, row, cell).clear();
			}
		}
	}

	public PoiCell getCell(int i) {
		return new PoiCell(workbook, sheet, row, CellUtil.getCell(row, i));
	}

	public PoiNamedCell getNamedCell(String name) {
		return namedMap.get(name);
	}

	public void copy(PoiRow from) {
		Row fromRow = from.getRow();
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

}
