/*
 * $Id: PoiExcel.java,v 1.2 2013/05/10 09:33:34 kengo-nagase Exp $
 */
package nis.framework.old.excel.poi.ss;

import nis.framework.old.excel.ExcelBase;
import nis.framework.old.excel.ExcelException;

public abstract class PoiExcel extends ExcelBase {

	private String fileName;

	private String rootDir;

	protected PoiExcel(String fileName) {
		this.fileName = fileName;
	}

	protected PoiExcel(String fileName, String rootDir) {
		this.fileName = fileName;
		this.rootDir = rootDir;
	}

	@Override
	protected byte[] executeCreate() throws ExcelException {
		try {
			if(rootDir == null){
				rootDir = adapter.getString("excel.template.rootdir");
			}
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
		catch (Exception x) {
			throw new ExcelException(x);
		}
	}

	protected boolean isForceFormulaRecalculation() {
		return true;
	}

	protected abstract void setParameters(PoiBook book) throws ExcelException;

}
