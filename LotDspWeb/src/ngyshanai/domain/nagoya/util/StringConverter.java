/*
 * StringConverter.java
 *
 * Created on 2006/07/06, 12:57
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package ngyshanai.domain.nagoya.util;

/**
 *
 * @author Hirohiko-Matsushita
 */
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class StringConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value != null){
			return value;
		}
		return null;
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return value.toString();
	}
}
