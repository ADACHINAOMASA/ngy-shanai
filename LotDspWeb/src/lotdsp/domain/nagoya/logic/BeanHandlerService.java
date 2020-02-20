/*
 * BeanHandlerService.java
 *
 * Created on 2006/01/25, 11:20
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package lotdsp.domain.nagoya.logic;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.RowProcessor;

/**
 *
 * @author manabu-katou
 */
public class BeanHandlerService implements ResultSetHandler {

    private Class type = null;
    private RowProcessor convert = BasicRowProcessor.instance();

    /** Creates a new instance of BeanHandlerService */
    public BeanHandlerService(Class type) {
        this.type = type;
    }

    public Object handle(ResultSet rs) throws SQLException {
        return this.convert.toBean(rs, this.type);
    }

}
