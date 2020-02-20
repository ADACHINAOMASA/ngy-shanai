/*
 * $Id: PoiBook.java,v 1.3 2014/02/06 06:51:24 Takuya-Kobayashi Exp $
 */
package nis.framework.old.excel.poi;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class PoiBook {

	protected HSSFWorkbook workbook;

	public PoiBook(HSSFWorkbook workbook) {
		setHssf(workbook);
	}

	public PoiBook() {
		this(new HSSFWorkbook());
	}

	public PoiBook(InputStream is) throws IOException {
		this(new HSSFWorkbook(new POIFSFileSystem(is)));
	}

	public PoiBook(File file) throws IOException {
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			setHssf(new HSSFWorkbook(new POIFSFileSystem(is)));
		}
		finally {
			if (is != null) {
				try {
					is.close();
				}
				catch (IOException x) {
				}
			}
		}
	}

	public PoiBook(String fileName) throws IOException {
		this(new File(fileName));
	}

	public HSSFWorkbook getHssf() {
		return workbook;
	}

	protected void setHssf(HSSFWorkbook workbook) {
		this.workbook = workbook;
	}

	public PoiSheet newSheet() {
		return new PoiSheet(workbook, workbook.createSheet());
	}

	public PoiSheet newSheet(String name) {
		return new PoiSheet(workbook, workbook.createSheet(name));
	}

	public PoiSheet getSheet(int i) {
		HSSFSheet sheet = workbook.getSheetAt(i);
		if (sheet != null) {
			return new PoiSheet(workbook, sheet);
		}
		return null;
	}

	public PoiSheet getSheet(String name) {
		HSSFSheet sheet = workbook.getSheet(name);
		if (sheet != null) {
			return new PoiSheet(workbook, sheet);
		}
		return null;
	}

	public List<PoiSheet> getSheetList() {
		List<PoiSheet> l = new ArrayList<PoiSheet>();
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			l.add(getSheet(i));
		}
		return l;
	}

	public int getSheetSize() {
		return workbook.getNumberOfSheets();
	}

	public void setForceFormulaRecalculation(boolean flag) {
		for (PoiSheet sheet : getSheetList()) {
			sheet.setForceFormulaRecalculation(flag);
		}
	}

	public void write(OutputStream os) throws IOException {
		HSSFFormulaEvaluator.evaluateAllFormulaCells(workbook);
		workbook.write(os);
		os.flush();
	}

	public byte[] toByteArray() throws IOException {
		ByteArrayOutputStream os = null;
		try {
			os = new ByteArrayOutputStream();
			write(os);
			return os.toByteArray();
		}
		finally {
			try {
				os.close();
			}
			catch (IOException x) {
			}
		}
	}

}
