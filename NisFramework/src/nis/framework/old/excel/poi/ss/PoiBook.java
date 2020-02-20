/*
 * $Id: PoiBook.java,v 1.2 2013/05/22 08:06:06 Takuya-Kobayashi Exp $
 */
package nis.framework.old.excel.poi.ss;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PoiBook {

    /** Excel2007�̃t�@�C������ */
    public static final String XSSF_SUFFIX = ".xlsx";

    /** Excel2007�̃t�@�C������ */
    public static final String XSSF_SUFFIX_2 = ".xlsm";

    /** Excel2003�ȑO�̃t�@�C������ */
    public static final String HSSF_SUFFIX = ".xls";

    /** �R�����g�̃v���t�B�b�N�X */
    public static final String COMMENT_PREFIX = "-";

    /** �����Ώۂ̃u�b�N */
	protected Workbook workbook;
	protected XSSFWorkbook xssfWorkbook;
	protected HSSFWorkbook hssfWorkbook;

    /** �V�[�g���̈ꗗ */
    private List<String> sheetNames = new ArrayList<String>();

    /**
     * �R���X�g���N�^
     * @param workbook �����Ώۂ̃u�b�N
	 */
	public PoiBook(Workbook workbook) {
        this.workbook = workbook;
        // �V�[�g�������
        int numOfSheets = workbook.getNumberOfSheets();
        for ( int sheetCnt = 0; sheetCnt < numOfSheets; sheetCnt++) {
            String sheetName = workbook.getSheetName( sheetCnt);
            sheetNames.add( sheetName);
        }
	}

    /**
     * �R���X�g���N�^
	 */
	public PoiBook() {
		this(new HSSFWorkbook());
	}

    /**
     * �R���X�g���N�^
     * @param inputStream �����Ώۂ̃u�b�N
	 */
	public PoiBook(InputStream is) throws Exception {

		this(WorkbookFactory.create(is));
	}

    /**
     * �R���X�g���N�^
    * @param fileName �t�@�C���p�X
    * @throws IOException �t�@�C���̓ǂݍ��݂Ɏ��s�����ꍇ
	 */
	public PoiBook(String fileName) throws Exception {
        if ( fileName.endsWith(XSSF_SUFFIX) || fileName.endsWith(XSSF_SUFFIX_2)) {
            // XSSF�`��
            FileInputStream stream = new FileInputStream(fileName);
            xssfWorkbook = new XSSFWorkbook(stream);
        	this.workbook = xssfWorkbook;
            stream.close();

        } else {
            // HSSF�`��
            FileInputStream stream = new FileInputStream(fileName);
            POIFSFileSystem fs = new POIFSFileSystem(stream);
            hssfWorkbook = new HSSFWorkbook(fs);
        	this.workbook = hssfWorkbook;
            stream.close();
        }

        // �V�[�g�������
        int numOfSheets = workbook.getNumberOfSheets();
        for ( int sheetCnt = 0; sheetCnt < numOfSheets; sheetCnt++) {
            String sheetName = workbook.getSheetName(sheetCnt);
            sheetNames.add(sheetName);
        }
	}

    /**
     * �R���X�g���N�^
    * @param file �t�@�C��
    * @throws IOException �t�@�C���̓ǂݍ��݂Ɏ��s�����ꍇ
	 */
	public PoiBook(File file) throws Exception {
		this(file.getAbsolutePath());
	}

	public Workbook getWorkbook() {
		return workbook;
	}

	public XSSFWorkbook getXssfWorkbook() {
		return xssfWorkbook;
	}

	public HSSFWorkbook getHssfWorkbook() {
		return hssfWorkbook;
	}

//	protected void setWorkbook(Workbook workbook) {
//		this.workbook = workbook;
//	}

	public PoiSheet newSheet() {
		return new PoiSheet(workbook, workbook.createSheet());
	}

	public PoiSheet newSheet(String name) {
		return new PoiSheet(workbook, workbook.createSheet(name));
	}

	public PoiSheet getSheet(int i) {
		Sheet sheet = workbook.getSheetAt(i);
		if (sheet != null) {
			return new PoiSheet(workbook, sheet);
		}
		return null;
	}

	public PoiSheet getSheet(String name) {
		Sheet sheet = workbook.getSheet(name);
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

    /**
     * �u�b�N�Ɋ܂܂��V�[�g���̈ꗗ�擾
     *
     * @return
     */
    public List<String> getSheetNames() {
        return sheetNames;
    }

    public void setForceFormulaRecalculation(boolean flag) {
//		for (PoiSheet sheet : getSheetList()) {
//			sheet.setForceFormulaRecalculation(flag);
//		}
	}

	@SuppressWarnings("deprecation")
	public void write(OutputStream os) throws IOException {
		try{
			CreationHelper createHelper = workbook.getCreationHelper();
			FormulaEvaluator formulaEvaluator= createHelper.createFormulaEvaluator();
			for(int i=0; i<workbook.getNumberOfSheets();i++){
				Sheet sheet = workbook.getSheetAt(i);
				for(Row row : sheet){
					for(Cell cell : row){
						formulaEvaluator.evaluateFormulaCell(cell);
					}
				}
			}
		}catch(Exception e){
		}
//		FormulaEvaluator.evaluateAllFormulaCells(workbook);
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