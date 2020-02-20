/*
 * $Id: PoiNamedCell.java,v 1.3 2014/02/06 06:51:24 Takuya-Kobayashi Exp $
 */
package nis.framework.old.excel.poi;

import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class PoiNamedCell extends PoiCell {

	//protected static final String PREFIX = "$named(";
	//protected static final String SUFFIX = ")";
	//protected static final Pattern PATTERN = Pattern.compile("\\$named\\(.+\\)");
	
	protected static final String PREFIX = "{/";
	protected static final String SUFFIX = "/}";
	protected static final Pattern PATTERN = Pattern.compile("\\{/.+\\/}");

	protected String name;

	protected PoiNamedCell(HSSFWorkbook workbook, HSSFSheet sheet, HSSFRow row, HSSFCell cell) {
		this(workbook, sheet, row, cell, getCellName(cell));
	}

	protected PoiNamedCell(HSSFWorkbook workbook, HSSFSheet sheet, HSSFRow row, HSSFCell cell, String name) {
		super(workbook, sheet, row, cell);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	protected static boolean isNamedCell(HSSFCell cell) {
		String value = cell.getRichStringCellValue().getString();
		if (value == null) {
			return false;
		}
		return PATTERN.matcher(value.trim()).matches();
	}

	protected static String getCellName(HSSFCell cell) {
		String value = cell.getRichStringCellValue().getString();
		if (value == null) {
			return null;
		}
		return value.substring(PREFIX.length(), value.length() - SUFFIX.length()).trim();
	}

}
