package lotdsp.domain.logic.hososhiyosho.test;





/*
 * CommonUtility.java
 *
 * Created on 2006/06/26, 14:38
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */



import java.util.*;
import java.sql.*;
import java.util.Date;
import java.text.*;

/**
 *
 * @author Hirohiko-Matsushita
 */
public class CommonUtility {
    
    /**
     * Creates CMSchViewInfo new instance of CommonUtility 
     */
    public CommonUtility() {
    }
    
    /**
     * システム時刻を返す
     */
    public static String GetSystemTime() {        
        Calendar now = Calendar.getInstance();  //Calendarｵﾌﾞｼﾞｪｸﾄ(日時ﾃﾞｰﾀ）を取得
        int y = now.get(Calendar.YEAR);    //年ﾃﾞｰﾀを取得
        int m = now.get(Calendar.MONTH);   //月ﾃﾞｰﾀを取得
        int d = now.get(Calendar.DATE);    //日ﾃﾞｰﾀを取得
        int h = now.get(Calendar.HOUR_OF_DAY);   //時間ﾃﾞｰﾀを取得(24時間制)
        int min = now.get(Calendar.MINUTE);    //分ﾃﾞｰﾀを取得
        int sec = now.get(Calendar.SECOND);    //秒ﾃﾞｰﾀを取得
        int msec = now.get(Calendar.MILLISECOND);  //ﾐﾘ秒ﾃﾞｰﾀを取得
        int ampm = now.get(Calendar.AM_PM);  //午前／午後の別。0／1 の数値で返す
        int aph = now.get(Calendar.HOUR);    //時間ﾃﾞｰﾀを取得(12時間制)
        int w = now.get(Calendar.DAY_OF_WEEK);   //1(日)～7(土)の数値を返す
        int w_m = now.get(Calendar.DAY_OF_WEEK_IN_MONTH); //月の何度目の曜日か
        int wm = now.get(Calendar.WEEK_OF_MONTH);  //月の何週目か
        int wy = now.get(Calendar.WEEK_OF_YEAR);   //年の何週目か
        int dy = now.get(Calendar.DAY_OF_YEAR);    //年の何日目か
        return y+"年"+(m+1)+"月"+d+"日 "+h+"時"+min+"分"+sec+"秒"+msec+"ミリ秒";
    }
    
    /** 
     * 日付チェック
     * 文字列(YYYYMMDD)が日付として有効かチェックする。
     * @param    String  入力文字列 YYYYMMDD
     * @return   boolean  有効 true 無効 false 
     **/
    public static boolean DateChk(String myDate) {
      //入力文字列が８桁以外の場合エラー
      if (myDate.length() != 8)
         {return false;}
      for (int i=0;i < myDate.length();i++) {
           char charData = myDate.charAt(i);   
           if ((charData < '0') || (charData > '9')) {
              return false;
          }
      }
      int intYear;
      int intMonth;
      int intDay; 
      if (myDate.length() > 3)
         { intYear = java.lang.Integer.parseInt(myDate.substring(0,4));}
      else
         { intYear = 0;}   
      if (myDate.length() > 5)   
         { intMonth = java.lang.Integer.parseInt(myDate.substring(4,6));}
      else
         { intMonth = 0;}  
      if (myDate.length() == 8)   
         { intDay = java.lang.Integer.parseInt(myDate.substring(6,8));}
      else   
         { intDay = 0;}
      Calendar cal = new GregorianCalendar();
      cal.setLenient( false );
      cal.set( intYear , intMonth-1 , intDay );

      try {
           java.util.Date ud = cal.getTime();
          } catch (IllegalArgumentException iae) {
              return false;
          }
      return true;
    }
    
    /** 
     * 時間チェック
     * 文字列(HHmmss)が時間として有効かチェックする。
     * @param    String  入力文字列 HHmmss
     * @return   boolean  有効 true 無効 false 
     */    
    public static boolean TimeChk(String myDate) {
      //入力文字列が６桁以外の場合エラー
      if (myDate.length() != 6)
         {return false;}
      for (int i=0;i < myDate.length();i++) {
           char charData = myDate.charAt(i);   
           if ((charData < '0') || (charData > '9')) {
              return false;
          }
      }
      int intH = 0;
      int intM = 0;
      int intS = 0; 
      if (myDate.length() > 1) {
          intH = java.lang.Integer.parseInt(myDate.substring(0,2));
          if (!(intH >= 0 & intH <= 23)) {
              return false;
          }
      }   
      if (myDate.length() > 3) {
          intM = java.lang.Integer.parseInt(myDate.substring(2,4));
          if (!(intM >= 0 & intM <= 59)) {
              return false;
          }
      }  
      if (myDate.length() == 6) {
          intS = java.lang.Integer.parseInt(myDate.substring(4,6));
          if (!(intS >= 0 & intS <= 59)) {
              return false;
          }          
      }
      return true;
    }
    
    /**
     * 数値チェック
     *  項目が半角0-9,ｶﾝﾏ,ｺﾛﾝか判断する
     *
     * @param   strNum  入力パラメータ
     * @return  boolean  有効 true 無効 false
     **/         
    public static boolean IsNum(String strInString) {
      if(strInString == null) return false;
      if(strInString.equals("")) return false;
      //文字列の長さ分繰り返し
      int intChk;
      intChk = 0;
      for (int i = 0; i < strInString.length(); i++)
          {
           char c  =  strInString.charAt(i);
           char c1 =  '0';
           char c2 =  '9';
           char c3 =  '.';
           char c4 =  ',';
           if (c < c1 || c > c2 || c == c3 || c == c4 ) 
              {intChk = intChk + 1;}
           }
      if (intChk == 0)
         {return true;}
      else        
         {return false;}      
    } 

    /**
     * ZEROチェック
     *  項目がNULL,0か判断する
     *
     * @param   strNum  入力パラメータ
     * @return  boolean  有効 true 無効 false
     **/         
    public static boolean IsZero(String strNum) { 
      if(strNum == null) return false;
      if(IsNum(strNum) && Double.parseDouble(strNum) == 0.0) return false;
      return true;
    }

    /**
     * NULL値、数値以外の場合0.0に変換
     *  項目がNULL値の場合0.0を戻す
     *
     * @param   strValue 入力パラメータ
     * @return  double   数値
    **/         
    public static double NullToZero(String strValue) { 
      if(strValue != null && !strValue.equals("")) {
          if(IsNum(strValue)) return Double.parseDouble(strValue);
      }
      return 0.0;
    }
    
    /** 
     * 半角英数字チェック
     * 文字列が'A'-'Z','CMSchViewInfo'-'z','0'-'9'であるかどうかチェックする
     * @param   String  入力文字列
     * @return  boolean 有効 true 無効 false
     */
    public static boolean AlpChk(String myStr) {
      for (int i=0;i < myStr.length();i++) {
          char charData = myStr.charAt(i);   
          if (((charData < 'A' )|| (charData > 'Z' )) && ((charData < 'a' ) 
             || ( charData > 'z')) && ((charData < '0' ) || ( charData > '9'))) {
              return false;
            }
        }
        return true;
    }
  
    /** 
     * 数値フォーマット(数値をカンマ付きに編集)を行う
     *
     * @param  String  編集前数値
     * @return String  編集後数値
     */
    public static String NumFormat(String strNum) { 
        //戻り値格納用(編集後数値)
        String strNewNum = "";
        //マイナスフラグ(-(マイナス)記号存在有無)
        boolean blnMinus = false;
        if (strNum == null || strNum.length() == 0) {
            strNewNum = " ";
        }
        else {
            if (strNum.substring(0, 1).equals("-")) {
                strNum = strNum.substring(1, strNum.length());
                blnMinus = true;
            }
            //４桁目、７桁目、１０桁目にカンマを挿入する
            for (int i = 0; i < strNum.length(); i++) {
                if (((strNum.length() - i) == 4) || ((strNum.length() - i) == 7) || ((strNum.length() - i) == 10)) {
                     strNewNum += strNum.substring(i, i + 1) + ",";
                } else {
                    strNewNum += strNum.substring(i, i + 1);
                }
            }
        }
        //マイナス判定
        if (blnMinus == true) {
            return "-" + strNewNum;
        } else {
            return strNewNum;
        }
    }
    
    /**
     * 入力された文字がカタカナかを判定する。
     *
     * @param value - 判定する文字列
     * @return true:全角・半角カタカナ・全角空白・空白　false:不正文字列
     */
    public static boolean isKatakanaString(String value){
        boolean returnValue = false;
        for(int i=0;i<value.length();i++){
            char code = value.charAt(i);
            //カタカナと全角空白・半角空白ならばOK
            /*
            if(code >= 0x30a1 && code <= 0x30fe){ //全角カナ
                returnValue = true;
            }else if(code == 0x3000 ||
             */ 
            if(code == 0x20){ //半角空白・全角空白
                returnValue = true;
            }else if(code >= 0xff61 && code <= 0xff9f){ //半角カナ
                returnValue = true;
            }else{
                returnValue = false;
                return returnValue;
            }
        }
        return returnValue;
    }

     /** カンマ削除(数値からカンマを取り除く)を行う
     ** 
     ** @param  String  編集前数値
     **
     ** @return String  編集後数値
     */
    public static String DeleteComma(String strNum) { 

      //トークン格納用
      String strNextToken = "";

      //戻り値格納用(編集後数値)
      String strNewNum = "";

      StringTokenizer st = new StringTokenizer(strNum, ",");

      //トークンが存在する間ループし変数にトークンを格納
      while (st.hasMoreTokens()) {
        strNextToken = st.nextToken();
        strNewNum += strNextToken;
      }

      return strNewNum;

    }
    
    /** カンマ削除(数値からカンマを取り除く)を行う
     ** 
     ** @param  String  編集前数値
     **
     ** @return String  編集後数値
     */
    public static String DeletePeriod(String strNum) { 

      //トークン格納用
      String strNextToken = "";

      //戻り値格納用(編集後数値)
      String strNewNum = "";

      StringTokenizer st = new StringTokenizer(strNum, ".");

      //トークンが存在する間ループし変数にトークンを格納
      while (st.hasMoreTokens()) {
        strNextToken = st.nextToken();
        strNewNum += strNextToken;
      }

      return strNewNum;

    }    
    
    public static String GetJpnDateTime(Date date) {
        Calendar cal1 = Calendar.getInstance();  //オブジェクトの生成
        cal1.setTime(date);

        int year = cal1.get(Calendar.YEAR);        //現在の年を取得
        int month = cal1.get(Calendar.MONTH) + 1;  //現在の月を取得
        int day = cal1.get(Calendar.DATE);         //現在の日を取得
        int hour = cal1.get(Calendar.HOUR_OF_DAY); //現在の時を取得
        int minute = cal1.get(Calendar.MINUTE);    //現在の分を取得
        int second = cal1.get(Calendar.SECOND);    //現在の秒を取得

        StringBuffer dow = new StringBuffer();
        switch (cal1.get(Calendar.DAY_OF_WEEK)) {  //現在の曜日を取得
          case Calendar.SUNDAY: dow.append("日"); break;
          case Calendar.MONDAY: dow.append("月"); break;
          case Calendar.TUESDAY: dow.append("火"); break;
          case Calendar.WEDNESDAY: dow.append("水"); break;
          case Calendar.THURSDAY: dow.append("木"); break;
          case Calendar.FRIDAY: dow.append("金"); break;
          case Calendar.SATURDAY: dow.append("土"); break;
        }

        //現在の年、月、日、曜日、時、分、秒を表示
        return "" + year + "年" + month + "月" + day + "日(" + dow + ") " + hour + "時" + minute + "分" + second + "秒";
    }
    
    /** 
     * Style属性をMapに格納
     * @param    String   Style属性の文字列
     * @return   Map      Style属性をキー、値で格納       
     */    
    public static Map GetStyleMap(String styleAttr) {
        Map map = new HashMap();
        String key = null;
        String[] strAry1 = styleAttr.split(";");
        for (int i=0; i<strAry1.length; i++) {
            String[] strAry2 = strAry1[i].split(":");
            map.put(strAry2[0].trim(), strAry2[1].trim());
        }
        return map;
    }
    
    /** 
     * 指定したStyle属性を格納
     * @param    String   Style属性の文字列
     *           String   Style属性名
     *           String   Unit（入力した単位を値から除去）
     * @return   String   Style属性をキー、値で格納       
     */    
    public static String GetStyleValue(String styleAttr, String styleName, String unitName1, String unitName2) {
        
        String rs = null;
        if(unitName1 != null && !unitName1.equals("")) {
            rs = GetStyleMap(styleAttr).get(styleName).toString().split(unitName1)[0];
        } else {
            rs = GetStyleMap(styleAttr).get(styleName).toString();
        }
        if(unitName2 != null && !unitName2.equals("")) rs = rs.split(unitName2)[0]; 
        return rs;
    }
    
    /** 
     * 指定した文字列から指定した範囲を抜き出す
     * substring(x,y)を使用した場合にxからyの範囲に文字がない（null）とエラーになるため
     * @param    String   文字列
     *           int      開始位置
     *           int      終了位置
     * @return   String   抜き出した文字列
     */
    public static String ExtractString(String myStr, int idx1, int idx2) {
        String wkStr = null;
        int beginIndex = 0;
        int endIndex = 0;
        if(myStr != null) {
            int sLen = myStr.length();
            if(idx1 <= sLen) {
                beginIndex = idx1 - 1;
            } else {
                return null;
            }
            if(idx2 <= sLen) {
                endIndex = idx2;
            } else {
                endIndex = sLen;
            }
            wkStr = myStr.substring(beginIndex, endIndex);
        }
        return wkStr;
    }
    
    /**
     * フォーマット指定した文字型から日付型へ変換
     */
    public static Date GetDateFormat(String strDate, String strFormat) {
       String strYear = "";
       String strMonth = "";
       String strDay = "";
       String strHour = "";
       String strMinute = "";
       String strSecond = "";
       for(int i=0;i<strFormat.length();i++) {
            switch (strFormat.charAt(i)) {
                case 'y': //年
                    strYear = strYear + strDate.charAt(i);
                    break;
                case 'M': //月
                    strMonth = strMonth + strDate.charAt(i);
                    break;
                case 'd': //日
                    strDay = strDay + strDate.charAt(i);
                    break;
                case 'h': //時
                case 'H': //24時間
                    strHour = strHour + strDate.charAt(i);
                    break;
                case 'm': //分
                    strMinute = strMinute + strDate.charAt(i);
                    break;
                case 's': //秒
                    strSecond = strSecond + strDate.charAt(i);
                    break;
                case 'E': //曜日
                case 'D': //その年の何日目か
                default:
                    break;
            }
        }
        Calendar calendar = Calendar.getInstance();
        Date today = new Date();
        calendar.setTime(today);
        int intYear = 0;
        int intMonth = 0;
        int intDay = 0;
        int intHour = 0;
        int intMinute = 0;
        int intSecond = 0;
        
        try {
            if(strYear.equals("")) {
                intYear = calendar.YEAR;
            } else {
                intYear = Integer.parseInt(strYear, 10);
            }
            if(strMonth.equals("")) {
                intMonth = calendar.MONTH;
            } else {
                intMonth = Integer.parseInt(strMonth, 10) - 1;
            }
            if(strDay.equals("")) {
                intDay = calendar.DAY_OF_MONTH;
            } else {
                intDay = Integer.parseInt(strDay, 10);
            }
            if(strHour.equals("")) {
                intHour = calendar.HOUR_OF_DAY;
            } else {
                intHour = Integer.parseInt(strHour, 10);
            }
            if(strMinute.equals("")) {
                intMinute = calendar.MINUTE;
            } else {
                intMinute = Integer.parseInt(strMinute, 10);
            }
            if(strSecond.equals("")) {
                intSecond = calendar.SECOND;
            } else {
                intSecond = Integer.parseInt(strSecond, 10);
            }

            calendar.set(intYear, intMonth, intDay, intHour, intMinute, intSecond);
            return calendar.getTime();            
        } catch(Exception e) {
            return null;
        }
    }
    
    /**
     * 日付型をフォーマット指定した文字列へ変換
     */
    public static String GetStrFormat(Date dt, String strFormat) {
        if(dt == null) return "";
        SimpleDateFormat dateFormat = new SimpleDateFormat(strFormat);
        return dateFormat.format(dt);
    }
    
    /** 
     * 現場暦を太陽暦に変更する
     * @param    String   現場暦（月日時分：YYYYMMDDHHMMSS）
     * @return   String   太陽暦（月日時分：YYYYMMDDHHMMSS）
     */    
    public static String GetSolarCalendar(String siteTime) {
        if(siteTime.length() == 14) {
            String solarDate = siteTime.substring(0, 8);
            String strTime = siteTime.substring(8, 14);
            if(Integer.parseInt(strTime, 10) >= 0 && Integer.parseInt(strTime, 10) <= 65900) {
                solarDate = GetLateDay(siteTime.substring(0, 8), 1);
            }
            String x = siteTime.substring(8, 14);
            return solarDate + siteTime.substring(8, 14);
        } else {
            return null;     
        }
    }
    
   /**
    * 太陽暦から現場暦へ変換
    */ 
    public static Date changeSolerToSite(Date dt) {    
        //Date today = new Date();
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("HHmm");
        String startTime = dateFormat.format(dt);
        if(startTime.compareTo("0000") >= 0  && startTime.compareTo("0659") <= 0) {
            // Calenderクラスを取得
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dt);
            // 1日を減算（24時間前の日時）
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            return calendar.getTime();
        } else {
            return dt;           
        }
    }

   /**
    * 現場暦から太陽暦へ変換
    */ 
    public static Date changeSiteToSoler(Date dt) {    
        Date today = new Date();
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("HHmm");
        String startTime = dateFormat.format(today);
        if(startTime.compareTo("0000") >= 0  && startTime.compareTo("0659") <= 0) {
            // Calenderクラスを取得
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dt);
            // 1日を減算（24時間前の日時）
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            return calendar.getTime();
        } else {
            return dt;           
        }
    }
    
    /**
     * 文字変換 < > & " をHTML用に変換する(改行は<BR>タグで置き換え)
     *
     * @param  String  入力文字列
     *
     * @return String  変換後入力文字列
     */
    public final String ChgStr(String str) {
        String Instr = str;
        int capacity = Instr.length();
        if (capacity == 0) {
            return "";
        }
        StringBuffer buf = new StringBuffer(capacity + 500);
        char c;
        for (int i = 0; i < capacity; i++) {
            c = Instr.charAt(i);
            if (!Character.isIdentifierIgnorable(c)) {
              switch (c) {
                  case '<': buf.append("&lt;"); break;//<
                  case '>': buf.append("&gt;"); break;//>
                  case '&': buf.append("&amp;"); break;//&
                  case '"': buf.append("&quot;"); break;//"
                  case '\n': buf.append("<BR>"); break;//改行;
                  default : buf.append(c);
              }
            }
        }
        return new String(buf);
    }

    /** 
     * 指定された日付のX日後を取得する
     * @param  String  入力日付 YYYYMMDD
     * @param  int     指定日   X日
     * @return String  出力日付 YYYYMMDD
     */
    public static String GetLateDay(String StartDay, int date) { 
        int intStartYY = java.lang.Integer.parseInt(StartDay.substring(0,4));
        int intStartMM = java.lang.Integer.parseInt(StartDay.substring(4,6));
        int intStartDD = java.lang.Integer.parseInt(StartDay.substring(6,8));
        GregorianCalendar gc = new GregorianCalendar(intStartYY,intStartMM -1 ,intStartDD);
        gc.add(GregorianCalendar.DATE, date);
        int intTodayY = gc.get(GregorianCalendar.YEAR);
        int intTodayM = gc.get(GregorianCalendar.MONTH) + 1;
        int intTodayD = gc.get(GregorianCalendar.DAY_OF_MONTH);
        String strSdateYYYY = String.valueOf(intTodayY);
        String strSdateMM;
        if (intTodayM < 10)
            {strSdateMM = "0" + String.valueOf(intTodayM);}
        else
            {strSdateMM = String.valueOf(intTodayM);}
        String strSdateDD; 
        if (intTodayD < 10)
            {strSdateDD = "0" + String.valueOf(intTodayD);}
        else
            {strSdateDD = String.valueOf(intTodayD);}  
        return strSdateYYYY + strSdateMM + strSdateDD;
    }
    
    /** 
     * 指定年月の末日を取得する
     * @param  String  入力年月 YYYYMM
     * @return String  指定年月の末日 DD
     */
    public String GetLastDay(String StartDay) {
        String SysYmd = StartDay;
        int intStartYY = java.lang.Integer.parseInt(SysYmd.substring(0,4));
        int intStartMM = java.lang.Integer.parseInt(SysYmd.substring(4,6));
        int intStartDD = 1;
        GregorianCalendar gc = new GregorianCalendar(intStartYY,intStartMM -1 ,intStartDD);
        //末日
        int intTodayD = gc.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        String strSdateDD; 
        if (intTodayD < 10)
            {strSdateDD = "0" + String.valueOf(intTodayD);}
        else
            {strSdateDD = String.valueOf(intTodayD);}
        return strSdateDD;
    }

    /** 日付変換(YYYYMMDD → YYYY/MM/DD)を行う
     * 
     * @param  String  変換前日付
     *
     * @return String  変換後日付
     */
    public String ChangeYmd1(String strYmd) { 
        if (strYmd == null || strYmd.length() != 8)
            return " ";
        else
            return strYmd.substring(0, 4) + "/" + strYmd.substring(4, 6) + "/" + strYmd.substring(6, 8);
    }

    /** 日付変換(YYYYMMDD → YYYY年MM月DD日)を行う
     * 
     * @param  String  変換前日付
     *
     * @return String  変換後日付
     */
    public String ChangeYmd2(String strYmd) { 
        if (strYmd == null || strYmd.length() != 8)
            return " ";
        else
            return strYmd.substring(0, 4) + "年" + strYmd.substring(4, 6) + "月" + strYmd.substring(6, 8) + "日";
    }
          
    /**
     * シングルクォーテーションをSQL用に追加する
     *
     * @param  String  入力文字列
     *
     * @return String  変換後入力文字列
     */
    public String ChgQuot(String inBuff){
        String strData = "";
        if (inBuff != null && inBuff.length() != 0){
            for (int i=0;i < inBuff.length();i++) {
                strData = strData + inBuff.substring(i,i+1);
                if (inBuff.substring(i,i+1).equals("\'")) {
                    strData = strData + "\'";
                }
            }
        }
        return  strData;
    }
    
    /**
     *  入力パラメータをダブルクォートで囲む
     *
     *  @param    input  入力パラメータ(編集前文字列)
     *  @return          編集後文字列
     */ 
    public String DQuote(String input) {
        String editdata;
        editdata = "";
        editdata += "\"" + input + "\"" ;
        return editdata;
    }
    
    /**  GetDayメソッド
     *  システム日付を取得する
     *  @return String               システム日付 YYYYMMDD
     */
    public String GetDay() {     
        Calendar calendar = new GregorianCalendar();
        Date trialtime = new Date();
        calendar.setTime(trialtime); 
        int intTodayY = calendar.get(Calendar.YEAR);
        int intTodayM = calendar.get(Calendar.MONTH) + 1;
        int intTodayD = calendar.get(Calendar.DAY_OF_MONTH);
        String strSdayY = Integer.toString(intTodayY);
        String strSdayM;
        if (intTodayM < 10)
           {strSdayM = "0" + Integer.toString(intTodayM);}
        else   
           {strSdayM = Integer.toString(intTodayM);}
        String strSdayD; 
        if (intTodayD < 10)
           {strSdayD = "0" + Integer.toString(intTodayD);}
        else   
           {strSdayD = Integer.toString(intTodayD);}  
        return strSdayY + strSdayM + strSdayD;
    }
    
    /**  GetTime2メソッド
     *  システム時刻を取得する
     *  @return String               システム時刻 HHMMSSMS(1/100秒)  
     */
    public String GetTime2() {     
        Calendar calendar = new GregorianCalendar();
        Date trialtime = new Date();
        calendar.setTime(trialtime); 
        int todayHH = calendar.get(Calendar.HOUR_OF_DAY);   
        int todayMM = calendar.get(Calendar.MINUTE);
        int todaySS = calendar.get(Calendar.SECOND);
        int todayMS = calendar.get(Calendar.MILLISECOND);
        String SdayHH;
        if (todayHH < 10)
           {SdayHH = "0" + Integer.toString(todayHH);}
        else   
           {SdayHH = Integer.toString(todayHH);}
        String SdayMM;
        if (todayMM < 10)
           {SdayMM = "0" + Integer.toString(todayMM);}
        else   
           {SdayMM = Integer.toString(todayMM);}
        String SdaySS;
        if (todaySS < 10)
           {SdaySS = "0" + Integer.toString(todaySS);}
        else   
           {SdaySS = Integer.toString(todaySS);} 
        String SdayMS;
        //1/1000→1/100
        SdayMS = Integer.toString(todayMS);
        SdayMS = SdayMS.substring(0,2);
        return SdayHH + SdayMM + SdaySS + SdayMS;
    }
    
    /**
     ** 文字列チェック関数（全角文字のチェック）
     **
     ** @param  String  入力文字列
     **
     ** @return ブール型(全て全角の場合true)
     */
    public boolean ChkZen(String inBuff){
        if (inBuff == null || inBuff.length() == 0) {return  true;}
        byte[]  bytData;
        String  strChar;
        boolean flgSingle = false;
        int     intX;
        for(int i=0 ; i < inBuff.length() ; i++ ){
            try{
                strChar = inBuff.substring(i,i+1);
                bytData = strChar.getBytes("SJIS");
                for(int j=0 ; j < bytData.length ; j++){
                    if( bytData[j] < 0 ){
                        intX = 256 + bytData[j];
                        //System.out.print("マイナス");
                    } else {
                        intX = bytData[j];
                        //System.out.print("プラス");
                    }
                    //System.out.print(Integer.toHexString(intX) + ":");
                }
                if( bytData.length == 2 ){
                    //flgSingle = true;
                    //System.out.println("２バイト文字 = " + strChar);
                } else {
                    flgSingle = true;
                    //System.out.println("１バイト文字 = " + strChar);
                }
            } catch(Exception e){
                flgSingle = false;
                //System.out.println("コード変換エラー");
            }
        }
        if(flgSingle)
            return  false;
        else
            return  true;
    }
    
    /**
     *  文字列の長さを求める
     *
     *  @param    input  入力パラメータ(編集前文字列)
     *  @return          文字列の長さ
     */
    public int StrLength(String strinput){
        int intLen = 0;
        strinput = GetSjisFromUni(strinput);
        try {
            byte[] bytstr = strinput.getBytes("SJIS");
            intLen = bytstr.length;
        } catch( Exception e )
            {return  0;}
        return intLen;
    }

    /**
     ** EUCをUNICODEへ変換
     **
     ** @param  String  入力文字列
     **
     ** @return String  変換後入力文字列
     */
    public  String GetUniFromEuc(String str) {
        try { 
            return new String(str.getBytes("8859_1"), "EUC_JP");
            //return new String(str.getBytes("8859_1"), "JISAutoDetect");
        } catch (java.io.UnsupportedEncodingException e) {
            return "";
        }
    }
    
    /**
     ** SJISをUNICODEへ変換
     **
     ** @param  String  入力文字列
     **
     ** @return String  変換後入力文字列
     */
    public  String GetSjisFromUni(String str) {
        try { 
            return new String(str.getBytes("8859_1"), "JISAutoDetect");
        } catch (java.io.UnsupportedEncodingException e) {
            return "";
        }
    }
    
    /** 
     ** 指定日の曜日を算出する
     ** @param   String    指定日(YYYYMMDD)
     ** @return  String    日(1)～土(7)
     **/
    public final String DayOfWeek(String strDate) {
        String strYYYY = strDate.substring(0,4);
        String strMM = strDate.substring(4,6);
        String strDD = strDate.substring(6,8);
        String strDay = "";
        GregorianCalendar gc = new GregorianCalendar();
        gc.set(Integer.parseInt(strYYYY), Integer.parseInt(strMM)-1, Integer.parseInt(strDD));
        int intTodayW = gc.get(Calendar.DAY_OF_WEEK);
        switch(intTodayW) {
            case Calendar.SUNDAY:     strDay = "1"; break;
            case Calendar.MONDAY:     strDay = "2"; break;
            case Calendar.TUESDAY:    strDay = "3"; break;
            case Calendar.WEDNESDAY:  strDay = "4"; break;
            case Calendar.THURSDAY:   strDay = "5"; break;
            case Calendar.FRIDAY:     strDay = "6"; break;
            case Calendar.SATURDAY:   strDay = "7"; break;
        }
        return  strDay;
    }
    
    /**  Addspaceメソッド
      *  文字列に空白を追加する
      *  @param  String               空白を追加したい文字列
      *  @param  int                  文字列の最大長
      *  @return String               空白追加後の文字列
      */
      public String Addspace(String strIndata,int intDatalen) {

        int intWk;    // 追加する空白の数
        StringBuffer buff = new StringBuffer();

        if (strIndata == null || strIndata.length() == 0) {
          strIndata = "";
        }
        buff.append(strIndata);
        //半角追加
        intWk = intDatalen - strIndata.length();
        for (int i = 0; i < intWk; i++)
           {buff.append(" ");}

        return buff.toString(); 

      }   
    /**
     *  DBコネクションを取得する
     *  @return    con      Connection    コネクション;
     */
    public Connection GetConnection() throws Exception {
        try {
            Connection con = null;
            //thinドライバー
            Class.forName ("oracle.jdbc.driver.OracleDriver");
            //ORACLE_DBコネクション設定**************************************************
            String url = "jdbc:oracle:thin:@192.168.0.1:1521:ORCL";
            String user = "user";
            String password = "pass";
            //***************************************************************************
            //DBコネクションCOMMIT設定***************************************************
            con = DriverManager.getConnection(url, user, password);
            con.setAutoCommit(false);
            //***************************************************************************
            return(con);
        } catch(SQLException e) {
            throw e;
        } catch(Exception e) {
          throw e;
        }
    }
    
    //コードバリエーションチェック（半角数字のみ）『数字範囲内であればtrueを返す』
    public boolean CodeCheck(char inputChar) {
        //戻り値を設定する
        boolean hantei = false;
        //チェックする文字を定義する（文字定義数１０文字）
        char[] hankakuSujiTBL = {'0','1','2','3','4','5','6','7','8','9'};
        //サーチフラグを定義する。
        int serchFlg = 0;
        //取得した文字が対象定義内に存在するかをサーチする。
        for(int i= 0 ; i < 10 ; i++){
            //見つかった場合フラグをオン
            if(inputChar == hankakuSujiTBL[i]){
                serchFlg = 1;
                break;
            }
        }
        //見つかった場合
        if(serchFlg == 1){
            hantei = true;
        }
        //判定結果を返す
        return(hantei);
    }

    /**
     *  文字列中の文字列を任意の文字列に変換する
     *  @param    firstString    String      文字列;
     *  @param    taisyoString   String      対象文字列;
     *  @param    henkanString   String      変換文字列;
     *  @return   afterString    String      変換後の文字列;
     */
    public static String isReplace(String firstString ,String taisyoString,String henkanString) throws Exception {
        try{
            //文字列がnullの場合、そのまま返す
            if(firstString == null){
                return firstString;
            }
            //文字列が""の場合、そのまま返す
            if(firstString.equals("")){
                return firstString;
            }
            //対象文字列がnullの場合、そのまま返す
            if(taisyoString == null){
                return firstString;
            }
            //対象文字列が""の場合、そのまま返す
            if(taisyoString.equals("")){
                return firstString;
            }
            //文字列の整形を行う
            String afterString = "";
            //対象文字列を判断しCRLFなどの改行コードを考慮してインプリメントするポインタ数を制御する。
            int plusPoint = taisyoString.length();
            int startPoint = 0;
            int endPoint   = firstString.indexOf(taisyoString, startPoint);
            //文字列に対象文字列がない場合、そのままの文字列を戻す
            //文字列に対象文字列がある間、以下の処理を繰り返す
            while (endPoint != -1){
                //文字列から対象文字列を元に検索行い変換文字列に置換する。
                afterString = afterString + firstString .substring(startPoint, endPoint) + henkanString;
                startPoint  = endPoint + plusPoint;
                endPoint    = firstString .indexOf(taisyoString, startPoint);
            }
            afterString = afterString + firstString .substring(startPoint);
            return afterString;
        } catch (Exception e) {
          throw e;
        }
    }
    
    /** 渡された文字列をタブで分解を行う
     * 
     *  @param    line_str        String            1行分のバッファ
     *  @param    java.util.Vector                  タブで切り取られた配列
     */
    public java.util.Vector Token(String line_str) throws Exception{
        char strPickUp;
        char[] InputRec = line_str.toCharArray();
        String strBuffer = "";
        java.util.Vector VecFileVec = new Vector();
        int cnt = 0;
        try {
            for(int intPoint = 0 ; intPoint < InputRec.length ; intPoint++ ){
                strPickUp = InputRec[intPoint];
                switch( strPickUp ){
                    case '\t':
                        //タブを発見したらバッファをベクターへ
                        VecFileVec.add(cnt, strBuffer.trim());
                        strBuffer = "";
                        cnt++;
                        break;
                    default:
                        //タブ以外の文字を取り出したらバッファへ
                        strBuffer = strBuffer + strPickUp;
                        //break;
                }//switch
            }//for
            //残りのバッファをベクターへ
            VecFileVec.add(cnt, strBuffer.trim());
            return VecFileVec;
        } catch (Exception err) {
          throw err;
        }
    }
    
    /** 文字列の右側の全角空白を取り除く
     * 
     *  @param    inBuff            String          入力文字列
     *  @return   inBuff(整形後)    String          変換後入力文字列
     */
    public String RightTrim(String inBuff){
        String strData = "";
        int intcnt = 0;
        for (int i=inBuff.length(); i > 0 ; i--){
            //後ろから探して最初に出現する全角空白以外の文字の出現位置を特定する
            strData = inBuff.substring(i - 1,i);
            if (!strData.equals("　")){
                intcnt = i;
                break;
            }
        }
        return inBuff.substring(0,intcnt);
    }
    
}