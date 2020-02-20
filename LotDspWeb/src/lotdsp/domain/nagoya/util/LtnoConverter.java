/*
 * LtnoConverter.java
 *
 * Created on 2006/06/06, 20:01
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package lotdsp.domain.nagoya.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Hirohiko-Matsushita
 */
public class LtnoConverter implements Converter {

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
            if(value != null){
            }
            return null;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
            Integer a_value = (Integer)value;
            if (a_value.intValue() == 0) {
                return value.toString();
            } else if (a_value.intValue() == 1) {
                return "点検板";
            } else if (a_value.intValue() == 2) {
                return "返り材";
            }
            return null;
    }
}
