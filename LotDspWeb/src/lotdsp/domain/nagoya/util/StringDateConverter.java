/*
 * StringDateConverter.java
 *
 * Created on 2006/04/27, 18:54
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package lotdsp.domain.nagoya.util;
/**
*
* @author Hirohiko-Matsushita
*/
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringDateConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value != null){
			return toDateFormat(value, "yyyyMMddHHmmss", "HH:mm");
		}
		return null;
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) {
               String str = toDateFormat((String)value, "yyyyMMddHHmmss", "HH:mm:ss");
		return str.toString();
	}

       private String toDateFormat(String dateStr, String fmtStrFrom, String fmtStrTo) {

           //文字列 → 日付変換用のパターンをセット
           SimpleDateFormat sdfFrom = new SimpleDateFormat(fmtStrFrom);

           //日付 → 文字列変換用のパターンをセット
           SimpleDateFormat sdfTo   = new SimpleDateFormat(fmtStrTo);

           String str = null;
           try {
               //日付を厳密にチェックする（11/31等の日付はParseExceptionに）
               sdfFrom.setLenient(false);

               //日付変換の実行（String → DATE）
               Date date = sdfFrom.parse(dateStr);

               //日付変換の実行（DATE → String）
               str = sdfTo.format(date);
           } catch(ParseException e) {
               e.printStackTrace();
               return null;
           }

           return str;
       }
}
