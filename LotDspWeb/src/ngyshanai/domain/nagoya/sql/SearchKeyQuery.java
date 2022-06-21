/*
 * SearchKeyQuery.java
 *
 * Created on 2006/12/28, 18:49
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package ngyshanai.domain.nagoya.sql;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author Hirohiko-Matsushita
 */
public class SearchKeyQuery extends ParameterQuery  {

    String wherePhrase;
    Map map = new HashMap();

    /** Creates a new instance of SearchKeyQuery */
    public SearchKeyQuery(Map params) {
        this.map = params;
        initParameters();
        initWherePhrase();
    }

    /**
     * 実行するSQL文を返す。
     */
    public String getSQL() {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT * FROM ( ");
        sql.append("SELECT ");
        sql.append("       D1.LINKKEY, ");
        sql.append("       D2.JUNO, ");
        sql.append("       D2.LTNO, ");
        sql.append("       D2.CYNO, ");
        sql.append("       D1.KNNO, ");
        sql.append("       D1.FRTDTM, ");
        sql.append("       TO_DATE(NULL) KSD, ");
        sql.append("       D2.CYUZO_YY, ");
        sql.append("       '2' TBLORDER ");
        sql.append("FROM ");
        sql.append("       DAT_JDF_FIX D1, ");
        sql.append("       DAT_JDF_COM2 D2 ");
        sql.append("WHERE ");
        sql.append("       D1.LINKKEY = D2.LINKKEY ");
        sql.append("UNION ALL ");
        sql.append("SELECT ");
        sql.append("       D1.LINKKEY, ");
        sql.append("       D1.JUNO, ");
        sql.append("       D1.LTNO, ");
        sql.append("       D1.CYNO, ");
        sql.append("       D2.KNNO, ");
        sql.append("       D3.FRTDTM, ");
        sql.append("       D3.KSD, ");
        sql.append("       D1.CYUZO_YY, ");
        sql.append("       '1' TBLORDER ");
        sql.append("FROM ");
        sql.append("       DAT_JDF_COM D1, ");
        sql.append("       DAT_LOT_COM D2, ");
        sql.append("       DAT_LOT_FIX D3 ");
        sql.append("WHERE ");
//      sql.append("       D1.LINKKEY = D2.LINKKEY(+) ");
        sql.append(wherePhrase);
        sql.append("AND    D1.LINKKEY = D3.LINKKEY(+) ");
        sql.append("UNION ALL ");
        sql.append("SELECT ");
        sql.append("       D1.LINKKEY, ");
        sql.append("       D1.JUNO, ");
        sql.append("       D1.LTNO, ");
        sql.append("       D1.CYNO, ");
        sql.append("       D2.KNNO, ");
        sql.append("       D3.FRTDTM, ");
        sql.append("       D3.KSD, ");
        sql.append("       D1.CYUZO_YY, ");
        sql.append("       '3' TBLORDER ");
        sql.append("FROM ");
        sql.append("       DAT_R_JDF_COM D1, ");
        sql.append("       DAT_R_LOT_COM D2, ");
        sql.append("       DAT_R_LOT_FIX D3 ");
        sql.append("WHERE ");
//      sql.append("       D1.LINKKEY = D2.LINKKEY(+) ");
        sql.append(wherePhrase);
        sql.append("AND    D1.LINKKEY = D3.LINKKEY(+) ");
	// 2014/09/25 追加 ▼
        sql.append("UNION ALL ");
        sql.append("SELECT ");
        sql.append("       D1.LINKKEY, ");
        sql.append("       D1.JUNO, ");
        sql.append("       D1.LTNO, ");
        sql.append("       D1.CYNO, ");
        sql.append("       D2.KNNO, ");
        sql.append("       D3.FRTDTM, ");
        sql.append("       D3.KSD, ");
        sql.append("       D1.CYUZO_YY, ");
        sql.append("       '4' TBLORDER ");
        sql.append("FROM ");
        sql.append("       DAT_S_JDF_COM D1, ");
        sql.append("       DAT_S_LOT_COM D2, ");
        sql.append("       DAT_S_LOT_FIX D3 ");
        sql.append("WHERE ");
        sql.append(wherePhrase);
        sql.append("AND    D1.TOROKUDATE = D2.TOROKUDATE(+) ");
        sql.append("AND    D1.LINKKEY = D3.LINKKEY(+) ");
        sql.append("AND    D1.TOROKUDATE = D3.TOROKUDATE(+) ");
	// 2014/09/25 追加 ▲

	// 2016/04/25 追加 ▼
        sql.append("UNION ALL ");
        sql.append("SELECT ");
        sql.append("       D1.LINKKEY, ");
        sql.append("       D1.JUNO, ");
        sql.append("       D1.LTNO, ");
        sql.append("       D1.CYNO, ");
        sql.append("       D2.KNNO, ");
        sql.append("       D3.FRTDTM, ");
        sql.append("       D3.KSD, ");
        sql.append("       D1.CYUZO_YY, ");
        sql.append("       '5' TBLORDER ");
        sql.append("FROM ");
        sql.append("       DAT_L_JDF_COM D1, ");
        sql.append("       DAT_L_LOT_COM D2, ");
        sql.append("       DAT_L_LOT_FIX D3 ");
        sql.append("WHERE ");
        sql.append(wherePhrase);
        sql.append("AND    TRUNC(D1.TOROKUDATE) = TRUNC(D2.TOROKUDATE(+)) ");
        sql.append("AND    D1.LINKKEY = D3.LINKKEY(+) ");
        sql.append("AND    TRUNC(D1.TOROKUDATE) = TRUNC(D3.TOROKUDATE(+)) ");
	// 2016/04/25 追加 ▲

        sql.append(") ");
        sql.append("WHERE ");
        sql.append(getParameterString(false));
        sql.append("ORDER BY ");
        sql.append("       FRTDTM DESC,KSD DESC");

	//System.out.println("@@:"+sql.toString());
	return sql.toString();
    }

    /**
     * パラメータを初期化する。
     */
    private void initParameters() {
        if(map.containsKey("LINKKEY")) {
            if(!((map.get("LINKKEY") == null) || (map.get("LINKKEY").equals("")))) {
                addParameter("LINKKEY = ?", map.get("LINKKEY").toString());
            }
        }
        if(map.containsKey("LTNO")) {
            if(!((map.get("LTNO") == null) || (map.get("LTNO").equals("")))) {
                addParameter("LTNO = ?", map.get("LTNO").toString());
            }
        }
        if(map.containsKey("CYNO")) {
            if(!((map.get("CYNO") == null) || (map.get("CYNO").equals("")))) {
                addParameter("CYNO = ?", map.get("CYNO").toString());
            }
        }
        if(map.containsKey("KNNO")) {
            if(!((map.get("KNNO") == null) || (map.get("KNNO").equals("")))) {
                addParameter("KNNO = ?", map.get("KNNO").toString());
            }
        }
        if(map.containsKey("JUNO")) {
            if(!((map.get("JUNO") == null) || (map.get("JUNO").equals("")))) {
                addParameter("JUNO = ?", map.get("JUNO").toString());
            }
        }
	// 2014/09/25 追加
        if(map.containsKey("CYUZO_YY")) {
            if(!((map.get("CYUZO_YY") == null))) {
                addParameter("CYUZO_YY = ?", map.get("CYUZO_YY").toString());
            }
        }

   	// 2020/09/10 追加
        if(map.containsKey("TBLORDER")) {
            if(!((map.get("TBLORDER") == null))) {
                addParameter("TBLORDER = ?", map.get("TBLORDER").toString());
            }
        }
    }

    /**
     * SQL WHERE句を初期化する。
     * 検査番号のみの指定の場合、DAT_LOT_COMを外部結合すると
     * 検索時間が掛かる為、検索条件でWHERE句を切替える
     */
    private void initWherePhrase () {
        if ((map.size() == 1) && (map.containsKey("KNNO"))) {
            if(!((map.get("KNNO") == null) || (map.get("KNNO").equals("")))) {
                wherePhrase = "       D1.LINKKEY = D2.LINKKEY ";
            } else {
                wherePhrase = "       D1.LINKKEY = D2.LINKKEY(+) ";
            }
        } else {
                wherePhrase = "       D1.LINKKEY = D2.LINKKEY(+) ";
        }
    }

}
