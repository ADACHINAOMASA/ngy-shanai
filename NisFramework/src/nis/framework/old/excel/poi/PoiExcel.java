/*
 * $Id: PoiExcel.java,v 1.1 2013/04/30 05:44:34 kengo-nagase Exp $
 */
package nis.framework.old.excel.poi;

import java.io.IOException;

import nis.framework.old.excel.ExcelBase;
import nis.framework.old.excel.ExcelException;





public abstract class PoiExcel extends ExcelBase {

	protected String fileName;

	protected PoiExcel(String fileName) {
		this.fileName = fileName;
	}

	@Override
	protected byte[] executeCreate() throws ExcelException {
		try {
			String rootDir = adapter.getString("excel.template.rootdir");
			if (!rootDir.endsWith("/")) {
				rootDir += "/";
			}
			PoiBook book = new PoiBook(rootDir + fileName);
			setParameters(book);
			if (isForceFormulaRecalculation()) {
				book.setForceFormulaRecalculation(true);
			}
			return book.toByteArray();
		}
		catch (ExcelException x) {
			throw x;
		}
		catch (IOException x) {
			throw new ExcelException(x);
		}
	}

	protected boolean isForceFormulaRecalculation() {
		return true;
	}

	protected abstract void setParameters(PoiBook book) throws ExcelException;

}
