/*
 * $Id: PoiNamedCell.java,v 1.1 2013/04/30 05:44:38 kengo-nagase Exp $
 */
package nis.framework.old.excel.poi.ss;

import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class PoiNamedCell extends PoiCell {

	//protected static final String PREFIX = "$named(";
	//protected static final String SUFFIX = ")";
	//protected static final Pattern PATTERN = Pattern.compile("\\$named\\(.+\\)");
	
	protected static final String PREFIX = "{/";
	protected static final String SUFFIX = "/}";
	protected static final Pattern PATTERN = Pattern.compile("\\{/.+\\/}");

	protected String name;

	protected PoiNamedCell(Workbook workbook, Sheet sheet, Row row, Cell cell) {
		this(workbook, sheet, row, cell, getCellName(cell));
	}

	protected PoiNamedCell(Workbook workbook, Sheet sheet, Row row, Cell cell, String name) {
		super(workbook, sheet, row, cell);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	protected static boolean isNamedCell(Cell cell) {
		String value = cell.getRichStringCellValue().getString();
		if (value == null) {
			return false;
		}
		return PATTERN.matcher(value.trim()).matches();
	}

	protected static String getCellName(Cell cell) {
		String value = cell.getRichStringCellValue().getString();
		if (value == null) {
			return null;
		}
		return value.substring(PREFIX.length(), value.length() - SUFFIX.length()).trim();
	}

}
