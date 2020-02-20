package lotdsp.domain.logic.hososhiyosho;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import lotdsp.common.msg.hososhiyosho.HosoShiyoshoImageInfo;
import lotdsp.common.msg.hososhiyosho.HosoShiyoshoInfo;
import lotdsp.common.util.StringUtil;
import nis.framework.properties.EnvProperties;


public class HosoShiyoshoDAO  {
    
    private String DELIMETER = "\n";
        
    public HosoShiyoshoInfo getPkgSpecData(String pkgSpecNo,boolean isGenba) {
    	
    	EnvProperties.reload();
    	
    	//MySQL接続パラメータ
    	String DRIVE_NAME = EnvProperties.getDriverName(); 
    	String URL = EnvProperties.getConnectionUrl(); 
    	String USER = EnvProperties.getUser(); 
    	String PASSWORD = EnvProperties.getPassword();
        
    	HosoShiyoshoInfo sb1 = new HosoShiyoshoInfo();
    	
        try {

            if (pkgSpecNo.toString().length() >= 6) {
            	
                Class.forName(DRIVE_NAME); 
                
                Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 

                System.out.println("driver version=" + conn.getMetaData().getDriverVersion()); 
            	
            	//画像取得時のキャッシュを無効にするためのパタメータに使用
            	setGTime(sb1, isGenba);
                
                // ３．SQL ステートメント・オブジェクトの作成
                Statement stmt = conn.createStatement();
                // ４．SQL ステートメントの発行
                String sql = "select * from dat_pkgspec where IsRecency = 1 AND PkgSpecNo = '" + pkgSpecNo.toString().trim().substring(0, 6) + "';";
                //ResultSet rs = stmt.executeQuery("select imageCount from dat_pkgspec where PkgSpecNo = " + pkgSpecNo.getValue().toString());
                ResultSet rs = stmt.executeQuery(sql);
                rs.last();
                int rowCount = rs.getRow();
                rs.first();
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");

                if (rowCount != 0) {
                    sb1.setHitCount(rowCount);
                    sb1.setPkgSpecNo(rs.getString("PkgSpecNo"));        
                    sb1.setHistoryNo(rs.getInt("HistoryNo"));
                    sb1.setModifyCount(rs.getInt("ModifyCount"));
                    sb1.setImageCount(rs.getInt("ImageFileCount"));
                    sb1.setIssueDate(sdf1.format(rs.getDate("IssueDate")));
                    sb1.setInfoMessage(rs.getString("InfoMessage"));
                    //画像ファイル属性の取得
                    sql = ""
                    + "SELECT * FROM dat_pkgspec_imgfile "
                    + "WHERE "
                    + "    PkgSpecNo = '" + rs.getString("PkgSpecNo") + "' "
                    + "AND HistoryNo = " + String.valueOf(rs.getInt("HistoryNo")) + " "
                    + "AND ModifyCount = " + String.valueOf(rs.getInt("ModifyCount")) + ";";
                    ResultSet rs_img = stmt.executeQuery(sql);
                    rs_img.last();
                    rowCount = rs_img.getRow();
                    rs_img.first();
                    String w_fileFormat = "";
                    String w_rotatedAngle = "";
                    String w_imgWidth = "";
                    String w_imgHeight = "";
                    String w_defWidth = "";
                    String w_defHeight = "";                    
                    String w_setWidth = "";
                    String w_setHeight = "";
                    String w_resolution = "";
                    String inefeed = "";
                    int w_viewWidth  = sb1.getViewWidth();
                    int w_viewHeight = sb1.getViewHeight();

                    if (rowCount != 0) {
                        for (int i=0;i<rowCount;i++) {
                            if (rowCount > (i + 1)) {
                                inefeed = "\n";
                            } else {
                                inefeed = "";
                            }
                            w_fileFormat += rs_img.getString("FileFormat") + inefeed;
                            w_rotatedAngle += String.valueOf(rs_img.getInt("RotatedAngle")) + inefeed;
                            w_imgWidth += String.valueOf(rs_img.getInt("Width")) + inefeed;
                            w_imgHeight += String.valueOf(rs_img.getInt("Height")) + inefeed;
                            switch (rs_img.getInt("RotatedAngle")) {
                                case   0:
                                case 180:
                                    //ビュー高さを基準に1倍
                                    w_defWidth += String.valueOf((rs_img.getInt("Width")*w_viewHeight/rs_img.getInt("Height"))-5) + inefeed;
                                    w_defHeight += String.valueOf(w_viewHeight-5) + inefeed;
                                    //ビュー幅を基準に１倍
                                    w_setWidth += String.valueOf(w_viewWidth-5) + inefeed;
                                    w_setHeight += String.valueOf((rs_img.getInt("Height")*w_viewWidth/rs_img.getInt("Width"))-5) + inefeed;
                                    break;
                                case  90:
                                case 270:
                                    w_defWidth += String.valueOf((rs_img.getInt("Width")*w_viewWidth/rs_img.getInt("Height"))-5) + inefeed;
                                    w_defHeight += String.valueOf(w_viewWidth-5) + inefeed;
                                    w_setWidth += String.valueOf((rs_img.getInt("Width")*w_viewWidth/rs_img.getInt("Height"))-5) + inefeed;
                                    w_setHeight += String.valueOf(w_viewWidth-5) + inefeed;
                                    break;
                            }
                            w_resolution += String.valueOf(rs_img.getInt("Resolution")) + inefeed;
                            rs_img.next();
                        }
                        sb1.setFileFormat(w_fileFormat);
                        sb1.setRotatedAngle(w_rotatedAngle);
                        sb1.setImgWidth(w_imgWidth);
                        sb1.setImgHeight(w_imgHeight);
                        sb1.setDefWidth(w_defWidth);
                        sb1.setDefHeight(w_defHeight);
                        sb1.setSetWidth(w_setWidth);
                        sb1.setSetHeight(w_setHeight);
                        sb1.setResolution(w_resolution);

                        rs_img.close();
                    }
                    sb1.setReturnMessage("");
                } else {
                    sb1.setReturnMessage("データがありません");
                    sb1.setHitCount(0);
                    sb1.setPkgSpecNo("");        
                    sb1.setHistoryNo(0);
                    sb1.setModifyCount(0);
                    sb1.setImageCount(0);
                    sb1.setIssueDate("");
                    sb1.setInfoMessage("");
                }

                // ６．データベースのクローズ
                rs.close();
                stmt.close();
                conn.close();

                //String sql = "UPDATE sample SET jusho = ? WHERE id = 1"; 

                //PreparedStatement pst = conn.prepareStatement(sql); 

                //String str = "－"; 
                //StringReader reader = new StringReader(str); 
                //pst.setCharacterStream(1, reader, str.length()); 

                //pst.executeUpdate(); 

                // ６．データベースのクローズ
                conn.close();                
            } else {
                if (pkgSpecNo.toString().length() == 0) {
                    sb1.setReturnMessage("包装仕様書№を指定して下さい");                    
                } else {
                    sb1.setReturnMessage("包装仕様書№の桁数が足りません");                    
                }
                sb1.setHitCount(0);
                sb1.setPkgSpecNo("");        
                sb1.setHistoryNo(0);
                sb1.setModifyCount(0);
                sb1.setImageCount(0);
                sb1.setIssueDate("");
                sb1.setInfoMessage("");
            }
            
        } catch (SQLException e1) {
    		e1.printStackTrace();
            System.out.println("SQLException: " + e1.getMessage());
            System.out.println("    SQLState: " + e1.getSQLState());
            System.out.println(" VendorError: " + e1.getErrorCode());
        } catch (Exception e2) {
        	e2.printStackTrace();
            System.out.println("Exception: " + e2.getMessage());
        }
        
    	//改行コード変換
    	setComments(sb1);
        
        //画像情報を作成
        createImageInfo(sb1,isGenba);
        
        return sb1;
    }
    

    /*
    public Map<String, String> getPkgSpecData(String pkgSpecNo, String viewWidth, String viewHeight) {
        HashMap<String, String> map = new HashMap<String, String>();
        try {
            if (pkgSpecNo.toString().length() >= 6) {

                String w_no = "";
                if (pkgSpecNo.toString().length() > 6) {
                    w_no = pkgSpecNo.toString().trim().substring(0,6);
                } else {
                    w_no = pkgSpecNo.toString().trim();
                }
                
            	//MySQL接続パラメータ
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
                //String sql = "select * from dat_pkgspec where IsRecency = 1 AND PkgSpecNo = '" + pkgSpecNo.toString().trim().substring(0, 6) + "';";
                String sql = "select * from dat_pkgspec where IsRecency = 1 AND PkgSpecNo = '" + w_no + "';";
                //ResultSet rs = stmt.executeQuery("select imageCount from dat_pkgspec where PkgSpecNo = " + pkgSpecNo.getValue().toString());
                ResultSet rs = stmt.executeQuery(sql);
                rs.last();
                int rowCount = rs.getRow();
                rs.first();
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");

                if (rowCount != 0) {
                    //戻り値
                    map.put("hitCount",   String.valueOf(rowCount));
                    map.put("pkgSpecNo",  rs.getString("PkgSpecNo"));
                    map.put("historyNo",  String.valueOf(rs.getInt("HistoryNo")));
                    map.put("modifyCount",String.valueOf(rs.getInt("ModifyCount")));
                    map.put("imageCount", String.valueOf(rs.getInt("ImageFileCount")));
                    map.put("issueDate",  sdf1.format(rs.getDate("IssueDate")));
                    map.put("infoMessage",rs.getString("InfoMessage"));
                    //画像ファイル属性の取得
                    sql = ""
                    + "SELECT * FROM dat_pkgspec_imgfile "
                    + "WHERE "
                    + "    PkgSpecNo = '" + rs.getString("PkgSpecNo") + "' "
                    + "AND HistoryNo = " + String.valueOf(rs.getInt("HistoryNo")) + " "
                    + "AND ModifyCount = " + String.valueOf(rs.getInt("ModifyCount")) + ";";
                    ResultSet rs_img = stmt.executeQuery(sql);
                    rs_img.last();
                    rowCount = rs_img.getRow();
                    rs_img.first();
                    String w_fileFormat = "";
                    String w_rotatedAngle = "";
                    String w_imgWidth = "";
                    String w_imgHeight = "";
                    String w_defWidth = "";
                    String w_defHeight = "";
                    String w_setWidth = "";
                    String w_setHeight = "";
                    String w_resolution = "";
                    String inefeed = "";
                    int w_viewWidth  = Integer.parseInt(viewWidth);
                    int w_viewHeight = Integer.parseInt(viewHeight);

                    if (rowCount != 0) {
                        for (int i=0;i<rowCount;i++) {
                            if (rowCount > (i + 1)) {
                                inefeed = "\n";
                            } else {
                                inefeed = "";
                            }
                            w_fileFormat += rs_img.getString("FileFormat") + inefeed;
                            w_rotatedAngle += String.valueOf(rs_img.getInt("RotatedAngle")) + inefeed;
                            w_imgWidth += String.valueOf(rs_img.getInt("Width")) + inefeed;
                            w_imgHeight += String.valueOf(rs_img.getInt("Height")) + inefeed;
                            switch (rs_img.getInt("RotatedAngle")) {
                                case   0:
                                case 180:
                                    //ビュー高さを基準に1倍
                                    w_defWidth += String.valueOf((rs_img.getInt("Width")*w_viewHeight/rs_img.getInt("Height"))-5) + inefeed;
                                    w_defHeight += String.valueOf(w_viewHeight-5) + inefeed;
                                    //ビュー幅を基準に１倍
                                    w_setWidth += String.valueOf(w_viewWidth-5) + inefeed;
                                    w_setHeight += String.valueOf((rs_img.getInt("Height")*w_viewWidth/rs_img.getInt("Width"))-5) + inefeed;
                                    break;
                                case  90:
                                case 270:
                                    w_defWidth += String.valueOf((rs_img.getInt("Width")*w_viewWidth/rs_img.getInt("Height"))-5) + inefeed;
                                    w_defHeight += String.valueOf(w_viewWidth-5) + inefeed;
                                    w_setWidth += String.valueOf((rs_img.getInt("Width")*w_viewWidth/rs_img.getInt("Height"))-5) + inefeed;
                                    w_setHeight += String.valueOf(w_viewWidth-5) + inefeed;
                                    break;
                            }
                            w_resolution += String.valueOf(rs_img.getInt("Resolution")) + inefeed;
                            rs_img.next();
                        }
                        map.put("fileFormat",  String.valueOf(w_fileFormat));
                        map.put("rotatedAngle", String.valueOf(w_rotatedAngle));
                        map.put("imgWidth", String.valueOf(w_imgWidth));
                        map.put("imgHeight", String.valueOf(w_imgHeight));
                        map.put("defWidth", String.valueOf(w_defWidth));
                        map.put("defHeight", String.valueOf(w_defHeight));
                        map.put("setWidth", String.valueOf(w_setWidth));
                        map.put("setHeight", String.valueOf(w_setHeight));
                        map.put("resolution", String.valueOf(w_resolution));

                        rs_img.close();
                    }
                    map.put("returnMessage", "");
                } else {
                    map.put("returnMessage", "データがありません");                   
                    map.put("hitCount",   String.valueOf(0));
                    map.put("pkgSpecNo",  "");
                    map.put("historyNo",  String.valueOf(0));
                    map.put("modifyCount",String.valueOf(0));
                    map.put("imageCount", String.valueOf(0));
                    map.put("issueDate",  "");
                    map.put("infoMessage","");
                    
                }

                // ６．データベースのクローズ
                rs.close();
                stmt.close();
                conn.close();

                //String sql = "UPDATE sample SET jusho = ? WHERE id = 1"; 

                //PreparedStatement pst = conn.prepareStatement(sql); 

                //String str = "－"; 
                //StringReader reader = new StringReader(str); 
                //pst.setCharacterStream(1, reader, str.length()); 

                //pst.executeUpdate(); 

                // ６．データベースのクローズ
                conn.close();                
            } else {
                if (pkgSpecNo.toString().length() == 0) {
                    map.put("returnMessage", "包装仕様書№を指定して下さい");                 
                } else {
                    map.put("returnMessage", "包装仕様書№の桁数が足りません");                  
                }               
                map.put("hitCount",   String.valueOf(0));
                map.put("pkgSpecNo",  "");
                map.put("historyNo",  String.valueOf(0));
                map.put("modifyCount",String.valueOf(0));
                map.put("imageCount", String.valueOf(0));
                map.put("issueDate",  "");
                map.put("infoMessage","");
                
            }
        } catch (SQLException e1) {
                System.out.println("SQLException: " + e1.getMessage());
                System.out.println("    SQLState: " + e1.getSQLState());
                System.out.println(" VendorError: " + e1.getErrorCode());
                return null;
        } catch (Exception e2) {
                System.out.println("Exception: " + e2.getMessage());
                return null;
        }
        
        return map;
    }
  */

    public HosoShiyoshoInfo getPkgSpecData(String pkgSpecNo, String viewWidth, String viewHeight,boolean isGenba) {
        //HashMap<String, String> map = new HashMap<String, String>();
    	
    	HosoShiyoshoInfo sb1 = new HosoShiyoshoInfo();
    	
    	try {
    		
            if (pkgSpecNo.toString().length() >= 6) {
            	
            	//画像取得時のキャッシュを無効にするためのパタメータに使用
            	setGTime(sb1, isGenba);

                String w_no = "";
                if (pkgSpecNo.toString().length() > 6) {
                    w_no = pkgSpecNo.toString().trim().substring(0,6);
                } else {
                    w_no = pkgSpecNo.toString().trim();
                }
                
            	//MySQL接続パラメータ
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
                //String sql = "select * from dat_pkgspec where IsRecency = 1 AND PkgSpecNo = '" + pkgSpecNo.toString().trim().substring(0, 6) + "';";
                String sql = "select * from dat_pkgspec where IsRecency = 1 AND PkgSpecNo = '" + w_no + "';";
                //ResultSet rs = stmt.executeQuery("select imageCount from dat_pkgspec where PkgSpecNo = " + pkgSpecNo.getValue().toString());
                ResultSet rs = stmt.executeQuery(sql);
                rs.last();
                int rowCount = rs.getRow();
                rs.first();
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");

                if (rowCount != 0) {
                    //戻り値
                	/*
                    map.put("hitCount",   String.valueOf(rowCount));
                    map.put("pkgSpecNo",  rs.getString("PkgSpecNo"));
                    map.put("historyNo",  String.valueOf(rs.getInt("HistoryNo")));
                    map.put("modifyCount",String.valueOf(rs.getInt("ModifyCount")));
                    map.put("imageCount", String.valueOf(rs.getInt("ImageFileCount")));
                    map.put("issueDate",  sdf1.format(rs.getDate("IssueDate")));
                    map.put("infoMessage",rs.getString("InfoMessage"));
                    */
                	
                    sb1.setHitCount(rowCount);
                    sb1.setPkgSpecNo(rs.getString("PkgSpecNo"));        
                    sb1.setHistoryNo(rs.getInt("HistoryNo"));
                    sb1.setModifyCount(rs.getInt("ModifyCount"));
                    sb1.setImageCount(rs.getInt("ImageFileCount"));
                    sb1.setIssueDate(sdf1.format(rs.getDate("IssueDate")));
                    sb1.setInfoMessage(rs.getString("InfoMessage"));
                                        
                    //画像ファイル属性の取得
                    sql = ""
                    + "SELECT * FROM dat_pkgspec_imgfile "
                    + "WHERE "
                    + "    PkgSpecNo = '" + rs.getString("PkgSpecNo") + "' "
                    + "AND HistoryNo = " + String.valueOf(rs.getInt("HistoryNo")) + " "
                    + "AND ModifyCount = " + String.valueOf(rs.getInt("ModifyCount")) + ";";
                    ResultSet rs_img = stmt.executeQuery(sql);
                    rs_img.last();
                    rowCount = rs_img.getRow();
                    rs_img.first();
                    String w_fileFormat = "";
                    String w_rotatedAngle = "";
                    String w_imgWidth = "";
                    String w_imgHeight = "";
                    String w_defWidth = "";
                    String w_defHeight = "";
                    String w_setWidth = "";
                    String w_setHeight = "";
                    String w_resolution = "";
                    String inefeed = "";
                    int w_viewWidth  = Integer.parseInt(viewWidth);
                    int w_viewHeight = Integer.parseInt(viewHeight);

                    if (rowCount != 0) {
                        for (int i=0;i<rowCount;i++) {
                            if (rowCount > (i + 1)) {
                                inefeed = "\n";
                            } else {
                                inefeed = "";
                            }
                            w_fileFormat += rs_img.getString("FileFormat") + inefeed;
                            w_rotatedAngle += String.valueOf(rs_img.getInt("RotatedAngle")) + inefeed;
                            w_imgWidth += String.valueOf(rs_img.getInt("Width")) + inefeed;
                            w_imgHeight += String.valueOf(rs_img.getInt("Height")) + inefeed;
                            switch (rs_img.getInt("RotatedAngle")) {
                                case   0:
                                case 180:
                                    //ビュー高さを基準に1倍
                                    w_defWidth += String.valueOf((rs_img.getInt("Width")*w_viewHeight/rs_img.getInt("Height"))-5) + inefeed;
                                    w_defHeight += String.valueOf(w_viewHeight-5) + inefeed;
                                    //ビュー幅を基準に１倍
                                    w_setWidth += String.valueOf(w_viewWidth-5) + inefeed;
                                    w_setHeight += String.valueOf((rs_img.getInt("Height")*w_viewWidth/rs_img.getInt("Width"))-5) + inefeed;
                                    break;
                                case  90:
                                case 270:
                                    w_defWidth += String.valueOf((rs_img.getInt("Width")*w_viewWidth/rs_img.getInt("Height"))-5) + inefeed;
                                    w_defHeight += String.valueOf(w_viewWidth-5) + inefeed;
                                    w_setWidth += String.valueOf((rs_img.getInt("Width")*w_viewWidth/rs_img.getInt("Height"))-5) + inefeed;
                                    w_setHeight += String.valueOf(w_viewWidth-5) + inefeed;
                                    break;
                            }
                            w_resolution += String.valueOf(rs_img.getInt("Resolution")) + inefeed;
                            rs_img.next();
                        }
                        /*
                        map.put("fileFormat",  String.valueOf(w_fileFormat));
                        map.put("rotatedAngle", String.valueOf(w_rotatedAngle));
                        map.put("imgWidth", String.valueOf(w_imgWidth));
                        map.put("imgHeight", String.valueOf(w_imgHeight));
                        map.put("defWidth", String.valueOf(w_defWidth));
                        map.put("defHeight", String.valueOf(w_defHeight));
                        map.put("setWidth", String.valueOf(w_setWidth));
                        map.put("setHeight", String.valueOf(w_setHeight));
                        map.put("resolution", String.valueOf(w_resolution));
                        */
                        sb1.setFileFormat(w_fileFormat);
                        sb1.setRotatedAngle(w_rotatedAngle);
                        sb1.setImgWidth(w_imgWidth);
                        sb1.setImgHeight(w_imgHeight);
                        sb1.setDefWidth(w_defWidth);
                        sb1.setDefHeight(w_defHeight);
                        sb1.setSetWidth(w_setWidth);
                        sb1.setSetHeight(w_setHeight);
                        sb1.setResolution(w_resolution);
                   
                        rs_img.close();
                    }
                    
                    //map.put("returnMessage", "");
                    sb1.setReturnMessage("");
                    
                } else {
                	/*
                    map.put("returnMessage", "データがありません");                   
                    map.put("hitCount",   String.valueOf(0));
                    map.put("pkgSpecNo",  "");
                    map.put("historyNo",  String.valueOf(0));
                    map.put("modifyCount",String.valueOf(0));
                    map.put("imageCount", String.valueOf(0));
                    map.put("issueDate",  "");
                    map.put("infoMessage","");
                    */
                    sb1.setReturnMessage("データがありません");
                    sb1.setHitCount(0);
                    sb1.setPkgSpecNo("");        
                    sb1.setHistoryNo(0);
                    sb1.setModifyCount(0);
                    sb1.setImageCount(0);
                    sb1.setIssueDate("");
                    sb1.setInfoMessage("");
                    
                }

                // ６．データベースのクローズ
                rs.close();
                stmt.close();
                conn.close();

                //String sql = "UPDATE sample SET jusho = ? WHERE id = 1"; 

                //PreparedStatement pst = conn.prepareStatement(sql); 

                //String str = "－"; 
                //StringReader reader = new StringReader(str); 
                //pst.setCharacterStream(1, reader, str.length()); 

                //pst.executeUpdate(); 

                // ６．データベースのクローズ
                conn.close();                
            } else {
                if (pkgSpecNo.toString().length() == 0) {
                    //map.put("returnMessage", "包装仕様書№を指定して下さい");       
                    sb1.setReturnMessage("包装仕様書№を指定して下さい");                    
                } else {
                    //map.put("returnMessage", "包装仕様書№の桁数が足りません");        
                    sb1.setReturnMessage("包装仕様書№の桁数が足りません");                    
                }   
                /*
                map.put("hitCount",   String.valueOf(0));
                map.put("pkgSpecNo",  "");
                map.put("historyNo",  String.valueOf(0));
                map.put("modifyCount",String.valueOf(0));
                map.put("imageCount", String.valueOf(0));
                map.put("issueDate",  "");
                map.put("infoMessage","");
                */
                sb1.setHitCount(0);
                sb1.setPkgSpecNo("");        
                sb1.setHistoryNo(0);
                sb1.setModifyCount(0);
                sb1.setImageCount(0);
                sb1.setIssueDate("");
                sb1.setInfoMessage("");
                
            }
        } catch (SQLException e1) {
                System.out.println("SQLException: " + e1.getMessage());
                System.out.println("    SQLState: " + e1.getSQLState());
                System.out.println(" VendorError: " + e1.getErrorCode());
                return null;
        } catch (Exception e2) {
                System.out.println("Exception: " + e2.getMessage());
                return null;
        }
        
    	//コメント作成（改行コード変換）
    	setComments(sb1);
    	
        //画像情報を作成
        createImageInfo(sb1,isGenba);
        
       // return map;
    	return sb1;
    }
    
    private void setComments(HosoShiyoshoInfo sb1) {
    	String[] strArr = StringUtil.nvl(sb1.getInfoMessage()).split("\n");
    	for(String s:strArr) {
    		sb1.getComments().add(s);
    	}
    }
    
    private void setGTime(HosoShiyoshoInfo sb1,boolean isGenba) {
    	if(isGenba) {
            java.util.Date date = new java.util.Date();
            sb1.setGetTime(String.valueOf(date.getTime()));
    	}
    }
        
    private void createImageInfo(HosoShiyoshoInfo info , boolean isGenba) {
    	
    	List<HosoShiyoshoImageInfo> images = new ArrayList<HosoShiyoshoImageInfo>();
    	info.setImages(images);
    	
    	if(StringUtil.hasNoValue(info.getPkgSpecNo())) {
    		return;
    	}
    	    	
    	String[] fileFormatArr   = splitDefault(info.getFileFormat());
    	String[] rotatedAngleArr = splitDefault(info.getRotatedAngle());
    	String[] resolutionArr   = splitDefault(info.getResolution());
    	String[] imgWidthArr     = splitDefault(info.getImgWidth());
    	String[] imgHeightArr    = splitDefault(info.getImgHeight());
    	String[] setWidthArr     = splitDefault(info.getSetWidth());
    	String[] setHeightArr    = splitDefault(info.getSetHeight());
    	String[] defWidthArr     = splitDefault(info.getDefWidth());
    	String[] defHeightArr    = splitDefault(info.getDefHeight());
    	
		String no       = info.getPkgSpecNo();
		int hist        = info.getHistoryNo();
		int mod         = info.getModifyCount(); 
		int imgcount    = info.getImageCount();
		
		String baseURL = EnvProperties.getImageServerUrl()+ no + "&hist=" + hist + "&mod=" + mod ;
		
		for (int i = 1; i <= imgcount; i++) {
				
			HosoShiyoshoImageInfo imageInfo=new HosoShiyoshoImageInfo();
			
			imageInfo.setSeq(i);
			
			imageInfo.setPkgSpecNo(info.getPkgSpecNo());
			
			imageInfo.setFileFormat(getString(fileFormatArr, i - 1));
			
			imageInfo.setRotatedAngle(getInt(rotatedAngleArr, i - 1));
			
			imageInfo.setResolution(getInt(resolutionArr, i - 1));
			
			imageInfo.setImgWidth(getInt(imgWidthArr, i - 1));
			imageInfo.setImgHeight(getInt(imgHeightArr, i - 1));
			
			imageInfo.setSetWidth(getInt(setWidthArr, i - 1));
			imageInfo.setSetHeight(getInt(setHeightArr, i - 1));
			
			imageInfo.setDefWidth(getInt(defWidthArr, i - 1));
			imageInfo.setDefHeight(getInt(defHeightArr, i - 1));
			
			//ベースURL
			imageInfo.setImgBaseUrl(baseURL + "&seq=" + i );
			
			//サムネル
			imageInfo.setThumbnailSrc(imageInfo.getImgBaseUrl() + "&thumb=1");
			
			//Img
			imageInfo.setImgSrc1(createImgUrl(imageInfo.getImgBaseUrl(),imageInfo.getImgWidth(),imageInfo.getImgHeight()));//綺麗
			imageInfo.setImgSrc2(imageInfo.getImgSrc1() + "&q=1");//粗い
			
			//SetImg	
			imageInfo.setSetImgSrc1(createImgUrl(imageInfo.getImgBaseUrl(),imageInfo.getSetWidth(),imageInfo.getSetHeight()));//綺麗
			imageInfo.setSetImgSrc2(imageInfo.getSetImgSrc1() + "&q=1");//粗い
			
			//DefImg			
			imageInfo.setDefImgSrc1(createImgUrl(imageInfo.getImgBaseUrl(),imageInfo.getDefWidth(),imageInfo.getDefHeight()));//綺麗
			imageInfo.setDefImgSrc2(imageInfo.getDefImgSrc1() + "&q=1");//粗い

			
			//現場の場合
			if(isGenba) {
				String gtime = "&gtime="+info.getGetTime();
				
				//サムネル
				imageInfo.setThumbnailSrc(imageInfo.getThumbnailSrc() + gtime);
				
				//Img
				imageInfo.setImgSrc1(imageInfo.getImgSrc1() + gtime);//綺麗
				imageInfo.setImgSrc2(imageInfo.getImgSrc2() + gtime);//粗い
				
				//SetImg	
				imageInfo.setSetImgSrc1(imageInfo.getSetImgSrc1()+gtime);//綺麗
				imageInfo.setSetImgSrc2(imageInfo.getSetImgSrc2()+gtime);//粗い
				
				//DefImg			
				imageInfo.setDefImgSrc1(imageInfo.getDefImgSrc1()+gtime);//綺麗
				imageInfo.setDefImgSrc2(imageInfo.getDefImgSrc2()+gtime);//粗い
			}			
						
			//★デフォルト画像
			imageInfo.setImgSrc(imageInfo.getSetImgSrc1());
			
			info.getImages().add(imageInfo);
		}
		
    }
	
    private String createImgUrl(String baseUrl,int w,int h) {
    	String url=baseUrl + "&w=" + w + "&h=" + h;
    	return url;
    }
        
    private String[] splitDefault(String str) {
    	return StringUtil.nvl(str).split(DELIMETER);
    }
    
	private int getInt(String[] strArr,int index){
		if(strArr==null || strArr.length==0){
			return 0;
		}
		if(index < 0){
			return 0;
		}
		if(index >= strArr.length){
			return 0;
		}
		return Integer.valueOf(strArr[index]);
	}
	
	private String getString(String[] strArr,int index){
		if(strArr==null || strArr.length==0){
			return "";
		}
		if(index < 0){
			return "";
		}
		if(index >= strArr.length){
			return "";
		}
		return strArr[index];
	}
    
}
