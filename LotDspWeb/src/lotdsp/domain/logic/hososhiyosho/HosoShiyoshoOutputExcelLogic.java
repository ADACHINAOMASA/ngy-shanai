package lotdsp.domain.logic.hososhiyosho;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.inject.Inject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;

import lotdsp.common.msg.CommonExcelInfo;
import nis.framework.ejb.logic.Logic;
import nis.framework.ejb.logic.ServiceContext;
import nis.framework.properties.EnvProperties;
import nis.framework.properties.ExcelProperties;
import nis.framework.util.StringUtil;

public class HosoShiyoshoOutputExcelLogic {

	@Inject
	protected ServiceContext svContext;
	
	private String ERROR_MSG="エクセルを出力する際、予期せぬエラーが発生しました。エラー内容：";

	@Logic
	public CommonExcelInfo execute(CommonExcelInfo in) {

		createaExcelData(in);
		
		return in;
	}
	
	
	private CommonExcelInfo createaExcelData(CommonExcelInfo in) {
        try {
        	
        	EnvProperties.reload();
        	
            String DRIVE_NAME = EnvProperties.getDriverName(); 
            String URL = EnvProperties.getConnectionUrl(); 
            String USER = EnvProperties.getUser(); 
            String PASSWORD = EnvProperties.getPassword();
            
            Class.forName(DRIVE_NAME); 
            
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 

            System.out.println("driver version=" + conn.getMetaData().getDriverVersion()); 

            // ３．SQL ステートメント・オブジェクトの作成
            Statement stmt = conn.createStatement();
            // ４．SQL ステートメントの発行
            String sql = "SELECT d1.PkgSpecNo,d1.HistoryNo,d1.ModifyCount,d1.OldPkgSpecNo,d1.DlvSpecNo,d1.PkgTypeCd,m1.PkgTypeName,d1.ProShapeCd,m2.ProShapeName,d1.PkgSpecCd,m3.PkgSpecName,d1.Title,d1.IssueDate,d1.AppName,d1.RepSizeT,d1.RepSizeW,d1.RepSizeL,d1.SpecTypeCd,m4.SpecTypeName,d1.InfoMessage,d1.IsRecency,d1.IsActive,d1.RegId,d1.RegDate FROM dat_pkgspec d1 LEFT JOIN mas_pkgtype m1 ON d1.PkgTypeCd = m1.PkgTypeCd LEFT JOIN mas_proshape m2 ON d1.ProShapeCd = m2.ProShapeCd LEFT JOIN mas_pkgspec m3 ON d1.PkgSpecCd = m3.PkgSpecCd LEFT JOIN mas_spectype m4 ON d1.SpecTypeCd = m4.SpecTypeCd WHERE d1.IsRecency = 1 AND d1.IsActive = 0 ORDER BY d1.PkgSpecNo, d1.HistoryNo";
            ResultSet rs = stmt.executeQuery(sql);
            rs.last();
            int rowCount = rs.getRow();
            rs.first();
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");

            if (rowCount != 0) {
                try {

                    HSSFWorkbook wb = loadExcelTempate();
                    
                    if(wb == null) {
                    	return null;
                    }
                    
                    //シートを読み込みます。
                    HSSFSheet sheet = wb.getSheet("PkgSpecData");
             
                    //
                    String cellValue;
                    String[][] colItem = {{"包装仕様書№","PkgSpecNo","String"},{"版数","HistoryNo","int"},{"旧包装仕様書№","OldPkgSpecNo","String"},{"納入仕様書№","DlvSpecNo","String"},{"梱包形態","PkgTypeName","String"},{"製品仕様","ProShapeName","String"},{"梱包指定別","PkgSpecName","String"},{"タイトル","Title","String"},{"発行日","IssueDate","Date"},{"用途名","AppName","String"},{"代表サイズＴ","RepSizeT","String"},{"代表サイズＷ","RepSizeW","String"},{"代表サイズＬ","RepSizeL","String"},{"仕様種別","SpecTypeName","String"},{"コメント","InfoMessage","String"}};
                    /**
                     * 罫線の設定
                     * BORDER_DASH_DOT dash-dot border
                     * BORDER_DASH_DOT_DOT dash-dot-dot border
                     * BORDER_DASHED dash border
                     * BORDER_DOTTED dot borderhair-line border
                     * BORDER_DOUBLE double-line border
                     * BORDER_HAIR hair-line border
                     * BORDER_MEDIUM Medium border
                     * BORDER_MEDIUM_DASH_DOT medium dash-dot border
                     * BORDER_MEDIUM_DASH_DOT_DOT medium dash-dot-dot border
                     * BORDER_MEDIUM_DASHED Medium dashed border
                     * BORDER_NONE No border
                     * BORDER_SLANTED_DASH_DOT slanted dash-dot border
                     * BORDER_THICK Thick border
                     * BORDER_THIN Thin border 
                     **/
                    HSSFCellStyle headerStyle = wb.createCellStyle();
                                   
                    headerStyle.setBorderTop(BorderStyle.THIN);
                    headerStyle.setBorderBottom(BorderStyle.THIN);
                    headerStyle.setBorderLeft(BorderStyle.THIN);
                    headerStyle.setBorderRight(BorderStyle.THIN);
                    
                    /*
                    headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
                    headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                    headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                    headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
                    */
                    
                    //フォントサイズの設定
                    HSSFFont font = wb.createFont();
                    //font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);  
                    font.setBold(false);
                    
                    font.setFontHeightInPoints((short)9);  
                    font.setColor(HSSFFont.COLOR_NORMAL);
                    font.setFontName("ＭＳ Ｐゴシック");
                    headerStyle.setFont(font);
                    //色
                    headerStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
                    /**
                     * 塗りつぶしパターン
                     * NO_FILL No background
                     * SOLID_FOREGROUND Solidly filled
                     * FINE_DOTS Small fine dots
                     * ALT_BARS Wide dots 
                     * SPARSE_DOTS Sparse dots 
                     * THICK_HORZ_BANDS Thick horizontal bands 
                     * THICK_VERT_BANDS Thick vertical bands 
                     * THICK_BACKWARD_DIAG Thick backward facing diagonals 
                     * THICK_FORWARD_DIAG Thick forward facing diagonals 
                     * BIG_SPOTS Large spots 
                     * BRICKS Brick-like layout 
                     * THIN_HORZ_BANDS Thin horizontal bands 
                     * THIN_VERT_BANDS Thin vertical bands 
                     * THIN_BACKWARD_DIAG Thin backward diagonal 
                     * THIN_FORWARD_DIAG Thin forward diagonal 
                     * SQUARES Squares 
                     * DIAMONDS Diamonds 
                     **/
                    //headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                    headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

                    HSSFCellStyle cellStyle = wb.createCellStyle();
                    
                    cellStyle.setBorderTop(BorderStyle.THIN);
                    cellStyle.setBorderBottom(BorderStyle.THIN);
                    cellStyle.setBorderLeft(BorderStyle.THIN);
                    cellStyle.setBorderRight(BorderStyle.THIN);
                    
                    /*
                    cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
                    cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                    cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                    cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
                    */
                    
                    cellStyle.setFont(font);
                    cellStyle.setFillForegroundColor(HSSFColor.BLACK.index);
                    //cellStyle.setFillPattern(HSSFCellStyle.NO_FILL);
                    cellStyle.setFillPattern(FillPatternType.NO_FILL);
                    
                    //見出し作成
                    HSSFRow rowHeader = sheet.createRow(0);
                    for (int i=0; i<colItem.length; i++) {    
                        HSSFCell cell = rowHeader.createCell(i);

                        //cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                        cell.setCellValue(colItem[i][0]);
                        cell.setCellStyle(headerStyle);
                    }
                    
                    //データ作成
                    for (int i=1; i<=rowCount; i++) {
                    	HSSFRow rowData = sheet.createRow(i);
                    	
                        for (int j=0; j<colItem.length; j++) {
                            
                            HSSFCell cell = rowData.createCell(j);

                            //cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                                                      
                            
                            if (colItem[j][2] == "String") {
                                cellValue = rs.getString(colItem[j][1]);
                                
                            } else if (colItem[j][2] == "int") {
                                cellValue = String.valueOf(rs.getInt(colItem[j][1]));
                            } else if (colItem[j][2] == "Date") {
                                cellValue = sdf1.format(rs.getDate(colItem[j][1]));  
                            } else {
                                cellValue = rs.getObject(colItem[j][1]).toString();
                            }
                            cell.setCellValue(cellValue);
                            cell.setCellStyle(cellStyle);
                        }
                        
                        rs.next();
                    }
                    

                   in.setData(toByte(wb));
                   
                   wb=null;
                    
                } catch (Exception e) {
                    e.printStackTrace();
                    
                    svContext.getAlerts().addDanger(ERROR_MSG+e.getMessage());
                    
                } finally {
                    rs.close();
                    stmt.close();
                    conn.close();
                }
            }
            
        } catch (SQLException e1) {
            System.out.println("SQLException: " + e1.getMessage());
            System.out.println("SQLState: " + e1.getSQLState());
            System.out.println("VendorError: " + e1.getErrorCode());
            svContext.getAlerts().addDanger(ERROR_MSG+e1.getMessage()); 
        } catch (Exception e2) {
            System.out.println("Exception: " + e2.getMessage());
            svContext.getAlerts().addDanger(ERROR_MSG+e2.getMessage());
        }
        
        return in;
    }
	
	protected HSSFWorkbook loadExcelTempate() throws IOException {
		InputStream in = null;
		try {
			ExcelProperties.reload();

			String rootDir = ExcelProperties.getExcelTemplateRootDir();
			
			if(StringUtil.hasNoValue(rootDir) || StringUtil.hasNoValue(ExcelProperties.getHosoShiyoshoTemplate())) {
				svContext.getAlerts().addDanger("Excelテンプレートがありません。");
				return null;
			}
						
			if (!rootDir.endsWith("/")) {
				rootDir += "/";
			}
			in = new FileInputStream(rootDir + ExcelProperties.getHosoShiyoshoTemplate());
			return new HSSFWorkbook(in);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException x) {
					//ignore
				}
			}
		}
	}
	
	
	private byte[] toByte(HSSFWorkbook wb) throws IOException {
		ByteArrayOutputStream os = null;
		try {
			os = new ByteArrayOutputStream();
			wb.write(os);
			os.flush();
			return os.toByteArray();
		} finally {
			try {
				if (os != null) {
					os.close();
				}
			} catch (IOException x) {
				//ignore
			}
		}
	}
	
	    
}
