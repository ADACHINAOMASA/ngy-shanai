package lotdsp.common.excel;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import nis.framework.properties.ExcelProperties;

public abstract class AbstractExcel extends ExcelBase {
	
	protected String templatePath = null;
	
	protected Workbook workbook = null;
	
	protected boolean isResouce;

	/**
	 * @param classic true:excel2003,false:excel2007
	 */
	public AbstractExcel(boolean classic) {
		if (classic) {
			workbook = new HSSFWorkbook();
		} else {
			workbook = new XSSFWorkbook();
		}
	}

	public AbstractExcel() {
		this(false);// デフォルトとしては2007～
	}

	public AbstractExcel(String templatePath, boolean isResource) {
		this.templatePath = templatePath;
		this.isResouce = isResource;
	}

	public AbstractExcel(String templatePath) {
		this(templatePath, false);
	}

	protected Workbook loadTempate() throws IOException, InvalidFormatException {
		return loadTempate(templatePath, isResouce);
	}

	protected Workbook loadTempate(String path, boolean isResource) throws IOException, InvalidFormatException {
		InputStream in = null;
		try {
			if (isResource) {
				in = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
			} else {

				String filePath = null;

				if (templatePath.contains(":/")) {
					filePath = templatePath;
				} else {
					String rootDir = ExcelProperties.getExcelTemplateRootDir();
					if (!rootDir.endsWith("/")) {
						rootDir += "/";
					}
					filePath = rootDir + templatePath;
				}
				in = new FileInputStream(filePath);

			}

			// excel ～2003
			if (path.endsWith(ExcelType.EXCEL_2003.extension())) {
				return new HSSFWorkbook(in);
			}
			// excel 2007 macro～
			else if (path.endsWith(ExcelType.EXCEL_2007_MACRO.extension())) {
				return WorkbookFactory.create(OPCPackage.open(in));
			} else {
				return new XSSFWorkbook(in);// 2007～
			}
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException x) {
				}
			}
		}
	}

	protected byte[] toByte() throws IOException {
		ByteArrayOutputStream os = null;
		try {
			os = new ByteArrayOutputStream();
			workbook.write(os);
			os.flush();
			return os.toByteArray();
		} finally {
			try {
				if (os != null) {
					os.close();
				}
			} catch (IOException x) {
			}
		}
	}

	@Override
	protected byte[] executeCreate() throws ExcelException {
		try {
			if (workbook == null) {
				workbook = loadTempate(templatePath, isResouce);
			}
			setData(workbook);
			return toByte();
		} catch (IOException x) {
			throw new ExcelException(x);
		} catch (InvalidFormatException e) {
			throw new ExcelException(e);
		}
	}

	protected abstract void setData(Workbook wb);

}