/*
 * LotDspService.java
 *
 * Created on 2006/01/25, 10:23
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package lotdsp.domain.nagoya.sql;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;
import javax.sql.DataSource;

import org.apache.tuscany.sca.data.collection.NotFoundException;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.object.MappingSqlQuery;

import lotdsp.domain.nagoya.bean.CFInfoBean;
import lotdsp.domain.nagoya.bean.CFInfoBoxBean;
import lotdsp.domain.nagoya.bean.CladInfoBean;
import lotdsp.domain.nagoya.bean.CladInfoBoxBean;
import lotdsp.domain.nagoya.bean.IcasBean;
import lotdsp.domain.nagoya.bean.IcasBoxBean;
import lotdsp.domain.nagoya.bean.LotDspUserBean;
import lotdsp.domain.nagoya.bean.SearchKeyBean;
import lotdsp.domain.nagoya.bean.StaffCommonBean;
import lotdsp.domain.nagoya.bean.StaffManufactureBean;
import lotdsp.domain.nagoya.bean.StaffProgressBean;
import lotdsp.domain.nagoya.bean.StaffProgressBoxBean;
import lotdsp.domain.nagoya.bean.StaffProgressJBoxBean;
import lotdsp.domain.nagoya.bean.StaffProgressSBoxBean;
import lotdsp.domain.nagoya.bean.StaffQualityBean;
import lotdsp.domain.nagoya.bean.StaffQualityBoxBean;
import lotdsp.domain.nagoya.bean.StaffTestBean;
import lotdsp.domain.nagoya.logic.BeanHandlerService;
import lotdsp.domain.nagoya.logic.LotProgInfoBean;
import lotdsp.domain.nagoya.logic.LotSchCboBean;
import nis.framework.properties.EnvProperties;
/**
 *
 * @author manabu-katou
 */
public class LotDspService extends JdbcDaoSupport implements Serializable {

    private static final String LTNO_ONLY = " LTNO = ? ";
    private static final String KNNO_ONLY = " KNNO = ? ";
    private static final String LTNO_KNNO_BOTH = " LTNO = ? AND KNNO = ? ";
    private static final String LTNO_CYNO_BOTH = " LTNO = ? AND CYNO = ? ";

    /**
     * プロパティ sql_where の値を保持。
     */
    //private String sql_where = null;

    /**
     * 実行するSQL文を返す。
     * ※未使用
     */
    public String getSQL() {
        StringBuffer sql = new StringBuffer();
	/*
        sql.append("SELECT * FROM ( ");
        sql.append("SELECT ");
	sql.append("	D1.LINKKEY,LTNO,CYNO,KNNO,JUNO,JBCD,BUCODE,D1.YOTOC YOTOC,YOTONAME,CHIKUCD,JUJSNO, ");
	sql.append("	ENOKI,JUA,JUB,JUX,JUY,JUZ,JUW,LTA,LTB,LTX,LTY,LTZ,RYOW,M2.ONAME,TO_DATE(null) KSD,FRTDTM, ");
        sql.append("    NVL((SELECT M1.TNNAME FROM MAS_TORINO M1 WHERE D1.TOKUI = M1.TNCD AND M1.KBN IN ('1', '2')), NULL) TOKUI, ");
	sql.append("	NVL((SELECT M1.TNNAME FROM MAS_TORINO M1 WHERE D1.NONYU = M1.TNCD AND M1.KBN IN ('1', '3')), NULL) NONYU ");
	sql.append("FROM ");
	sql.append("	DAT_JDF_COM D1, ");
	sql.append("	DAT_LOT_COM D2, ");
	sql.append("	DAT_LOT_FIX D3, ");
	sql.append("	MAS_YOTO M1, ");
	sql.append("	MAS_OKURI M2 ");
	sql.append("WHERE ");
	sql.append("	D1.LINKKEY = D2.LINKKEY ");
	sql.append("AND	D1.LINKKEY = D3.LINKKEY ");
	sql.append("AND	D1.MASYOTOC = M1.YOTOC(+) ");
	sql.append("AND	D1.ON3N = M2.OKUCD(+) ");
        sql.append("UNION ALL ");
	sql.append("SELECT ");
	sql.append("    D1.LINKKEY,D2.LTNO,D2.CYNO,D1.KNNO,D2.JUNO,");
	sql.append("    D2.JBCD,D2.BUCODE,D2.YOTOC,M1.YOTONAME,D2.CHIKUCD,D1.JUJSNO,D2.ENOKI,");
	sql.append("    D2.JUA,D2.JUB,D2.JUX,D2.JUY,D2.JUZ,D2.JUW,D2.LTA,D2.LTB,D2.LTX,D2.LTY,D2.LTZ,D2.RYOW,");
	sql.append("    M2.ONAME,TO_DATE(null) KSD,D1.FRTDTM,");
	sql.append("    NVL((SELECT M1.TNNAME FROM MAS_TORINO M1 WHERE D2.TOKUI = M1.TNCD AND M1.KBN IN ('1', '2')), NULL) TOKUI,");
	sql.append("    NVL((SELECT M1.TNNAME FROM MAS_TORINO M1 WHERE D2.NONYU = M1.TNCD AND M1.KBN IN ('1', '3')), NULL) NONYU ");
	sql.append("FROM ");
	sql.append("    DAT_JDF_FIX D1, ");
	sql.append("    DAT_JDF_COM2 D2, ");
	sql.append("    MAS_YOTO M1, ");
	sql.append("    MAS_OKURI M2 ");
	sql.append("WHERE ");
	sql.append("    D1.LINKKEY = D2.LINKKEY ");
	sql.append("AND	D2.MASYOTOC = M1.YOTOC(+) ");
	sql.append("AND	D2.ON3N = M2.OKUCD(+) ");
        sql.append("UNION ALL ");
	sql.append("SELECT ");
	sql.append("	D1.LINKKEY,LTNO,CYNO,KNNO,JUNO,JBCD,BUCODE,D1.YOTOC YOTOC,YOTONAME,CHIKUCD,JUJSNO, ");
	sql.append("	ENOKI,JUA,JUB,JUX,JUY,JUZ,JUW,LTA,LTB,LTX,LTY,LTZ,RYOW,M2.ONAME,KSD,FRTDTM, ");
        sql.append("       NVL((SELECT M1.TNNAME FROM MAS_TORINO M1 WHERE D1.TOKUI = M1.TNCD AND M1.KBN IN ('1', '2')), NULL) TOKUI, ");
	sql.append("	NVL((SELECT M1.TNNAME FROM MAS_TORINO M1 WHERE D1.NONYU = M1.TNCD AND M1.KBN IN ('1', '3')), NULL) NONYU ");
	sql.append("FROM ");
	sql.append("	DAT_R_JDF_COM D1, ");
	sql.append("	DAT_R_LOT_COM D2, ");
	sql.append("	DAT_R_LOT_FIX D3, ");
	sql.append("	MAS_YOTO M1, ");
	sql.append("	MAS_OKURI M2 ");
	sql.append("WHERE ");
	sql.append("	D1.LINKKEY = D2.LINKKEY ");
	sql.append("AND	D1.LINKKEY = D3.LINKKEY ");
	sql.append("AND	D1.MASYOTOC = M1.YOTOC(+) ");
	sql.append("AND	D1.ON3N = M2.OKUCD(+) ");
	// 2014/09/25 ▼
        sql.append("UNION ALL ");
	sql.append("SELECT ");
	sql.append("	D1.LINKKEY,LTNO,CYNO,KNNO,JUNO,JBCD,BUCODE,D1.YOTOC YOTOC,YOTONAME,CHIKUCD,JUJSNO, ");
	sql.append("	ENOKI,JUA,JUB,JUX,JUY,JUZ,JUW,LTA,LTB,LTX,LTY,LTZ,RYOW,M2.ONAME,KSD,FRTDTM, ");
        sql.append("       NVL((SELECT M1.TNNAME FROM MAS_TORINO M1 WHERE D1.TOKUI = M1.TNCD AND M1.KBN IN ('1', '2')), NULL) TOKUI, ");
	sql.append("	NVL((SELECT M1.TNNAME FROM MAS_TORINO M1 WHERE D1.NONYU = M1.TNCD AND M1.KBN IN ('1', '3')), NULL) NONYU ");
	sql.append("FROM ");
	sql.append("	DAT_S_JDF_COM D1, ");
	sql.append("	DAT_S_LOT_COM D2, ");
	sql.append("	DAT_S_LOT_FIX D3, ");
	sql.append("	MAS_YOTO M1, ");
	sql.append("	MAS_OKURI M2 ");
	sql.append("WHERE ");
	sql.append("	D1.LINKKEY = D2.LINKKEY ");
	sql.append("AND	D1.LINKKEY = D3.LINKKEY ");
	sql.append("AND	D1.MASYOTOC = M1.YOTOC(+) ");
	sql.append("AND	D1.ON3N = M2.OKUCD(+) ");
	// 2014/09/25 ▲
        sql.append(") ");
        sql.append("WHERE ");
	sql.append("	LTNO = ? ");
	sql.append("AND	KNNO = ? ");
        sql.append("ORDER BY ");
        sql.append("       FRTDTM DESC,KSD DESC");
	*/
        return sql.toString();
    }


    // ロット問合せユーザーSQL
    private static final String LOTDSP_USERS_QUERY =
        "SELECT " +
	"     * " +
	"FROM " +
	"     MAS_LOTDSP_USERS " +
	"WHERE " +
	"     USER_ID = ? " +
	"     AND YUKO_KBN = '1' ";


    //ｽﾀｯﾌ版ﾛｯﾄ情報SQL　共有
    private static final String STAFF_COMMON_QUERY =
        "SELECT * FROM ( " +
        "SELECT " +
	"	 '1' TBLORDER,D1.LINKKEY,LTNO,DECODE(CYNO,'0000000000',NULL,CYNO) CYNO,DECODE(KNNO,'0000000000',NULL,KNNO) KNNO,JUNO,JBCD,BUCODE,D1.YOTOC YOTOC,YOTONAME,CHIKUCD,JUJSNO,TANTOSHA_MEI, " +
	"	ENOKI,JUA,JUB,JUX,JUY,JUZ,JUW,LTA,LTB,LTX,LTY,LTZ,RYOW,M2.ONAME,TO_DATE(null) KSD,JUNO202,FRTDTM, " +
        "       NVL((SELECT M1.TNNAME FROM MAS_TORINO M1 WHERE D1.TOKUI = M1.TNCD AND M1.KBN IN ('1', '2')), NULL) TOKUI, " +
	"	NVL((SELECT M1.TNNAME FROM MAS_TORINO M1 WHERE D1.NONYU = M1.TNCD AND M1.KBN IN ('1', '3')), NULL) NONYU, " +
	"	D1.ENTDATE EDTM,SYSDATE, " +
//      "       CASE WHEN D3.AKNO = 1 THEN 'PITRUN後' ELSE NULL END DATAKBN, " +
	"       CASE WHEN D3.AKNO = 1 AND D1.PRJ >= 111 THEN '本PIT中' WHEN D3.AKNO <> 1 THEN 'HOT後' ELSE NULL END DATAKBN, " +
	"	D1.JUP JUP, " +
        "       CASE WHEN D1.JCD105 = '0' THEN '１本口' WHEN D1.JCD105 = '1' THEN '圧別親' WHEN D1.JCD105 = '2' THEN '切別親' WHEN D1.JCD105 = '3' THEN '子カード'  WHEN D1.JCD105 = '9' THEN '抱き合せ前' END JCD105, " +  // 2009/04/22 追加
	"	D1.SLAB_TEHAIBI YMARK, " +
	"	CASE WHEN D1.NOKI3_RENBAN = '0000  ' THEN NULL ELSE D1.NOKI3_RENBAN END SHIKENNO, " +
	"	CASE WHEN D3.KARIKON_FLG = 1 THEN 'ｶﾘｺﾝ' WHEN D3.KARIKON_FLG = 2 THEN 'Pｵｷﾊﾞ' ELSE '' END KARIKON, " +
        "	DECODE(NVL(D1.G2,'0'), " +
        "	'0','------------', " +
        "	'1','1:事前約束品', " +
        "	'2','2:顧客指定品', " +
        "	'3','3:短納期品', " +
        "	'4','4:当月売上', " +
        "	'5','5:顧客在庫品', " +
        "	'6','6:看板品', " +
        "	'C','C:中間素材', " +
        "	'D','D:HOTダミー', " +
        "	'K','K:皮材', " +
        "	'G','G:技革品', " +
        "	'S','S:予備資材', " +
        "	'Y','Y:原価見積', " +
        "	'Z','Z:マスターロット', " +
        "	'') NOKIFUGO, " +
	"	D1.CYUZO_YY, " +
	"	D1.NONYUNO " +
	"FROM " +
	"	DAT_JDF_COM D1, " +
	"	DAT_LOT_COM D2, " +
	"	DAT_LOT_FIX D3, " +
	"	MAS_YOTO M1, " +
	"	MAS_OKURI M2, " +
	"	MAS_TANTOSHA M3 " +
	"WHERE " +
	"	D1.LINKKEY = D2.LINKKEY(+) " +
	"AND	D1.LINKKEY = D3.LINKKEY(+) " +
	"AND	D1.MASYOTOC = M1.YOTOC(+) " +
	"AND	D1.ON3N = M2.OKUCD(+) " +
	"AND	TO_CHAR(JUJSNO) = M3.TANTOSHA_CD(+) " +
        "UNION ALL " +
        "SELECT " +
        "        '2' TBLORDER,D1.LINKKEY,D2.LTNO,DECODE(D2.CYNO,'0000000000',NULL,D2.CYNO) CYNO,DECODE(D1.KNNO,'0000000000',NULL,D1.KNNO) KNNO,D2.JUNO,D2.JBCD,D2.BUCODE,D2.YOTOC,M1.YOTONAME,D2.CHIKUCD,TO_NUMBER(D1.JUJSNO),TANTOSHA_MEI, " +
        "       D2.ENOKI,D2.JUA,D2.JUB,D2.JUX,D2.JUY,D2.JUZ,D2.JUW,D2.LTA,D2.LTB,D2.LTX,D2.LTY,D2.LTZ,D2.RYOW,M2.ONAME,TO_DATE(NULL) KSD,JUNO202,D1.FRTDTM, " +
        "       NVL((SELECT M1.TNNAME FROM MAS_TORINO M1 WHERE D2.TOKUI = M1.TNCD AND M1.KBN IN ('1', '2')), NULL) TOKUI, " +
        "       NVL((SELECT M1.TNNAME FROM MAS_TORINO M1 WHERE D2.NONYU = M1.TNCD AND M1.KBN IN ('1', '3')), NULL) NONYU, " +
        "       D2.ENTDATE EDTM,SYSDATE, " +
//      "       '火延前' DATAKBN, " +
        "       CASE WHEN NVL(D2.PRJ,0) = 0 THEN 'PIT前' WHEN D2.PRJ >= 111 THEN '仮PIT中' ELSE NULL END DATAKBN, " +
        "       D2.JUP JUP, " +
        "       CASE WHEN D2.JCD105 = '0' THEN '１本口' WHEN D2.JCD105 = '1' THEN '圧別親' WHEN D2.JCD105 = '2' THEN '切別親' WHEN D2.JCD105 = '3' THEN '子カード'  WHEN D2.JCD105 = '9' THEN '抱き合せ前' END JCD105, " +  // 2009/04/22 追加
	"	D2.SLAB_TEHAIBI YMARK, " +
	"	CASE WHEN D2.NOKI3_RENBAN = '0000  ' THEN NULL ELSE D2.NOKI3_RENBAN END SHIKENNO, " +
	"	'' KARIKON, " +
        "	DECODE(NVL(D2.G2,'0'), " +
        "	'0','------------', " +
        "	'1','1:事前約束品', " +
        "	'2','2:顧客指定品', " +
        "	'3','3:短納期品', " +
        "	'4','4:当月売上', " +
        "	'5','5:顧客在庫品', " +
        "	'6','6:看板品', " +
        "	'C','C:中間素材', " +
        "	'D','D:HOTダミー', " +
        "	'K','K:皮材', " +
        "	'G','G:技革品', " +
        "	'S','S:予備資材', " +
        "	'Y','Y:原価見積', " +
        "	'Z','Z:マスターロット', " +
        "	'') NOKIFUGO, " +
	"	D2.CYUZO_YY, " +
	"	D2.NONYUNO " +
	"FROM  " +
        "       DAT_JDF_FIX D1, " +
        "       DAT_JDF_COM2 D2, " +
        "       MAS_YOTO M1, " +
        "       MAS_OKURI M2, " +
    	"	     MAS_TANTOSHA M3 " +
        "WHERE  " +
        "       D1.LINKKEY = D2.LINKKEY " +
        "AND    D2.MASYOTOC = M1.YOTOC(+) " +
        "AND    D2.ON3N = M2.OKUCD(+) " +
    	"AND	TO_CHAR(D1.JUJSNO) = M3.TANTOSHA_CD(+) " +
        "UNION ALL " +
	"SELECT " +
	"	 '3' TBLORDER,D1.LINKKEY,LTNO,DECODE(CYNO,'0000000000',NULL,CYNO) CYNO,DECODE(KNNO,'0000000000',NULL,KNNO) KNNO,JUNO,JBCD,BUCODE,D1.YOTOC YOTOC,YOTONAME,CHIKUCD,JUJSNO,TANTOSHA_MEI, " +
	"	ENOKI,JUA,JUB,JUX,JUY,JUZ,JUW,LTA,LTB,LTX,LTY,LTZ,RYOW,M2.ONAME,KSD,JUNO202,FRTDTM, " +
        "       NVL((SELECT M1.TNNAME FROM MAS_TORINO M1 WHERE D1.TOKUI = M1.TNCD AND M1.KBN IN ('1', '2')), NULL) TOKUI, " +
	"	NVL((SELECT M1.TNNAME FROM MAS_TORINO M1 WHERE D1.NONYU = M1.TNCD AND M1.KBN IN ('1', '3')), NULL) NONYU, " +
	"	D1.ENTDATE EDTM,SYSDATE, " +
        "	CASE WHEN D3.KSD is not null THEN '倉入' ELSE '' END DATAKBN, " +
        "	D1.JUP JUP, " +
        "       CASE WHEN D1.JCD105 = '0' THEN '１本口' WHEN D1.JCD105 = '1' THEN '圧別親' WHEN D1.JCD105 = '2' THEN '切別親' WHEN D1.JCD105 = '3' THEN '子カード'  WHEN D1.JCD105 = '9' THEN '抱き合せ前' END JCD105, " +  // 2009/04/22 追加
	"	D1.SLAB_TEHAIBI YMARK, " +
	"	CASE WHEN D1.NOKI3_RENBAN = '0000  ' THEN NULL ELSE D1.NOKI3_RENBAN END SHIKENNO, " +
	"	CASE WHEN D3.KARIKON_FLG = 1 THEN 'ｶﾘｺﾝ' WHEN D3.KARIKON_FLG = 2 THEN 'Pｵｷﾊﾞ' ELSE '' END KARIKON, " +
        "	DECODE(NVL(D1.G2,'0'), " +
        "	'0','------------', " +
        "	'1','1:事前約束品', " +
        "	'2','2:顧客指定品', " +
        "	'3','3:短納期品', " +
        "	'4','4:当月売上', " +
        "	'5','5:顧客在庫品', " +
        "	'6','6:看板品', " +
        "	'C','C:中間素材', " +
        "	'D','D:HOTダミー', " +
        "	'K','K:皮材', " +
        "	'G','G:技革品', " +
        "	'S','S:予備資材', " +
        "	'Y','Y:原価見積', " +
        "	'Z','Z:マスターロット', " +
        "	'') NOKIFUGO, " +
	"	D1.CYUZO_YY, " +
	"	D1.NONYUNO " +
	"FROM " +
	"	DAT_R_JDF_COM D1, " +
	"	DAT_R_LOT_COM D2, " +
	"	DAT_R_LOT_FIX D3, " +
	"	MAS_YOTO M1, " +
	"	MAS_OKURI M2, " +
	"	MAS_TANTOSHA M3 " +
	"WHERE " +
	"	D1.LINKKEY = D2.LINKKEY(+) " +
	"AND	D1.LINKKEY = D3.LINKKEY(+) " +
	"AND	D1.MASYOTOC = M1.YOTOC(+) " +
	"AND	D1.ON3N = M2.OKUCD(+) " +
	"AND	TO_CHAR(JUJSNO) = M3.TANTOSHA_CD(+) " +
	// 2014/09/25 追加 ▼
        "UNION ALL " +
	"SELECT " +
	"	 '4' TBLORDER,D1.LINKKEY,LTNO,DECODE(CYNO,'0000000000',NULL,CYNO) CYNO,DECODE(KNNO,'0000000000',NULL,KNNO) KNNO,JUNO,JBCD,BUCODE,D1.YOTOC YOTOC,YOTONAME,CHIKUCD,JUJSNO,TANTOSHA_MEI, " +
	"	ENOKI,JUA,JUB,JUX,JUY,JUZ,JUW,LTA,LTB,LTX,LTY,LTZ,RYOW,M2.ONAME,KSD,JUNO202,FRTDTM, " +
        "       NVL((SELECT M1.TNNAME FROM MAS_TORINO M1 WHERE D1.TOKUI = M1.TNCD AND M1.KBN IN ('1', '2')), NULL) TOKUI, " +
	"	NVL((SELECT M1.TNNAME FROM MAS_TORINO M1 WHERE D1.NONYU = M1.TNCD AND M1.KBN IN ('1', '3')), NULL) NONYU, " +
	"	D1.ENTDATE EDTM,SYSDATE, " +
        "	'倉入' DATAKBN, " +
	"	D1.JUP JUP, " +
        "       CASE WHEN D1.JCD105 = '0' THEN '１本口' WHEN D1.JCD105 = '1' THEN '圧別親' WHEN D1.JCD105 = '2' THEN '切別親' WHEN D1.JCD105 = '3' THEN '子カード'  WHEN D1.JCD105 = '9' THEN '抱き合せ前' END JCD105, " +
	"	D1.SLAB_TEHAIBI YMARK, " +
	"	CASE WHEN D1.NOKI3_RENBAN = '0000  ' THEN NULL ELSE D1.NOKI3_RENBAN END SHIKENNO, " +
	"	CASE WHEN D3.KARIKON_FLG = 1 THEN 'ｶﾘｺﾝ' WHEN D3.KARIKON_FLG = 2 THEN 'Pｵｷﾊﾞ' ELSE '' END KARIKON, " +
        "	DECODE(NVL(D1.G2,'0'), " +
        "	'0','------------', " +
        "	'1','1:事前約束品', " +
        "	'2','2:顧客指定品', " +
        "	'3','3:短納期品', " +
        "	'4','4:当月売上', " +
        "	'5','5:顧客在庫品', " +
        "	'6','6:看板品', " +
        "	'C','C:中間素材', " +
        "	'D','D:HOTダミー', " +
        "	'K','K:皮材', " +
        "	'G','G:技革品', " +
        "	'S','S:予備資材', " +
        "	'Y','Y:原価見積', " +
        "	'Z','Z:マスターロット', " +
        "	'') NOKIFUGO, " +
	"	D1.CYUZO_YY, " +
	"	D1.NONYUNO " +
        "FROM " +
	"	DAT_S_JDF_COM D1, " +
	"	DAT_S_LOT_COM D2, " +
	"	DAT_S_LOT_FIX D3, " +
	"	MAS_YOTO M1, " +
	"	MAS_OKURI M2, " +
	"	MAS_TANTOSHA M3 " +
	"WHERE " +
	"	D1.LINKKEY = D2.LINKKEY(+) " +
	"AND	D1.TOROKUDATE = D2.TOROKUDATE(+) " +
	"AND	D1.LINKKEY = D3.LINKKEY(+) " +
	"AND	D1.TOROKUDATE = D3.TOROKUDATE(+) " +
	"AND	D1.MASYOTOC = M1.YOTOC(+) " +
	"AND	D1.ON3N = M2.OKUCD(+) " +
	"AND	TO_CHAR(JUJSNO) = M3.TANTOSHA_CD(+) " +
	// 2014/09/25 追加 ▲
	// 2016/04/24 追加 ▼
        "UNION ALL " +
	"SELECT " +
	"	 '5' TBLORDER,D1.LINKKEY,LTNO,DECODE(CYNO,'0000000000',NULL,CYNO) CYNO,DECODE(KNNO,'0000000000',NULL,KNNO) KNNO,JUNO,JBCD,BUCODE,D1.YOTOC YOTOC,YOTONAME,CHIKUCD,JUJSNO,TANTOSHA_MEI, " +
	"	ENOKI,JUA,JUB,JUX,JUY,JUZ,JUW,LTA,LTB,LTX,LTY,LTZ,RYOW,M2.ONAME,KSD,JUNO202,FRTDTM, " +
        "       NVL((SELECT M1.TNNAME FROM MAS_TORINO M1 WHERE D1.TOKUI = M1.TNCD AND M1.KBN IN ('1', '2')), NULL) TOKUI, " +
	"	NVL((SELECT M1.TNNAME FROM MAS_TORINO M1 WHERE D1.NONYU = M1.TNCD AND M1.KBN IN ('1', '3')), NULL) NONYU, " +
	"	D1.ENTDATE EDTM,SYSDATE, " +
        "	'仕損' DATAKBN, " +
	"	D1.JUP JUP, " +
        "       CASE WHEN D1.JCD105 = '0' THEN '１本口' WHEN D1.JCD105 = '1' THEN '圧別親' WHEN D1.JCD105 = '2' THEN '切別親' WHEN D1.JCD105 = '3' THEN '子カード'  WHEN D1.JCD105 = '9' THEN '抱き合せ前' END JCD105, " +
	"	D1.SLAB_TEHAIBI YMARK, " +
	"	CASE WHEN D1.NOKI3_RENBAN = '0000  ' THEN NULL ELSE D1.NOKI3_RENBAN END SHIKENNO, " +
	"	CASE WHEN D3.KARIKON_FLG = 1 THEN 'ｶﾘｺﾝ' WHEN D3.KARIKON_FLG = 2 THEN 'Pｵｷﾊﾞ' ELSE '' END KARIKON, " +
        "	DECODE(NVL(D1.G2,'0'), " +
        "	'0','------------', " +
        "	'1','1:事前約束品', " +
        "	'2','2:顧客指定品', " +
        "	'3','3:短納期品', " +
        "	'4','4:当月売上', " +
        "	'5','5:顧客在庫品', " +
        "	'6','6:看板品', " +
        "	'C','C:中間素材', " +
        "	'D','D:HOTダミー', " +
        "	'K','K:皮材', " +
        "	'G','G:技革品', " +
        "	'S','S:予備資材', " +
        "	'Y','Y:原価見積', " +
        "	'Z','Z:マスターロット', " +
        "	'') NOKIFUGO, " +
	"	D1.CYUZO_YY, " +
	"	D1.NONYUNO " +
        "FROM " +
	"	DAT_L_JDF_COM D1, " +
	"	DAT_L_LOT_COM D2, " +
	"	DAT_L_LOT_FIX D3, " +
	"	MAS_YOTO M1, " +
	"	MAS_OKURI M2, " +
	"	MAS_TANTOSHA M3 " +
	"WHERE " +
	"	D1.LINKKEY = D2.LINKKEY(+) " +
	"AND	TRUNC(D1.TOROKUDATE) = TRUNC(D2.TOROKUDATE(+)) " +
	"AND	D1.LINKKEY = D3.LINKKEY(+) " +
	"AND	TRUNC(D1.TOROKUDATE) = TRUNC(D3.TOROKUDATE(+)) " +
	"AND	D1.MASYOTOC = M1.YOTOC(+) " +
	"AND	D1.ON3N = M2.OKUCD(+) " +
	"AND	TO_CHAR(JUJSNO) = M3.TANTOSHA_CD(+) " +
	// 2016/04/24 追加 ▲
        ") " +
        "WHERE " +
        "       LINKKEY = ? " +
        "       AND CYUZO_YY = ? " + // ADD
        "       AND TBLORDER = ? " +
        "ORDER BY " +
        "       FRTDTM DESC,KSD DESC";


    //ｽﾀｯﾌ版ﾛｯﾄ情報SQL　進度情報
    private static final String STAFF_PROGRESS_QUERY =
        "SELECT * FROM ( " +
	"SELECT " +
	"	'1' TBLORDER,D1.LINKKEY,HIJYU,TANW,SW, " +
        "       DECODE(SW,0,0,RYOW/SW*100) SBD, " +
        "       DECODE(SW,0,0,KRAW/SW*100) KBD, " +
        "       DECODE(RYOW,0,0,KRAW/RYOW*100) SRAITO, " +
        "       TO_CHAR(HOHDT,'YYYY/MM/DD') HOHDT,TO_CHAR(HOKDT,'YYYY/MM/DD') HOKDT,KKN,NOKIYMD,SAITEHAILTNO,SAITEHAICYUNO, " +
	"	NVL(SAILINKKEY, '')SAILINKKEY,TO_CHAR(MSNO1,'FM0000') || TO_CHAR(MSNOK,'FM00') MSNOK,TO_CHAR(MSNO1,'FM0000') || TO_CHAR(MSNOH,'FM00') MSNOH,TO_CHAR(MSNO1,'FM0000') || TO_CHAR(MSNOS,'FM00') MSNOS,D5.MODDATE, " +
        "       DECODE(JCD112,'0',NULL,'1','約束','2','特急','3','外行','4','外戻') JCD112, " + // 2016/11/17 区分追加
        "       DECODE(HIKIKBN,'0',NULL,'A','Aｲﾝﾌﾟｯﾄ済','B','Bｲﾝﾌﾟｯﾄ済','H','引当済') HIKIKBN, " +
        "       DECODE(HIKICODE,'0',NULL,HIKICODE) HIKICODE, " +
        "       (SELECT COUNT(*) FROM DAT_IJYO_SYOCHI@HIN_LINK D2 WHERE D1.LINKKEY = D2.SAILINKKEY(+)) SAI_COUNT, " +
        "	DECODE(GAI_SETUBI1,'0000',NULL,DECODE(GAI_SETUBI1,NULL,NULL,'1')) GAI_IDX1, " +
        "	DECODE(GAI_SETUBI1,'0000',NULL,GAI_SETUBI1) GAI_SETUBI1, " +
        "	DECODE(GAI_SETUBI1,'0000',NULL,(SELECT M1.RYAKUNAMEJ FROM MAS_GAIOKR M1 WHERE D2.GAI_OKURI1 = M1.CODE AND M1.MSKBN = 'O')) GAI_OKURI1, " +
        "	DECODE(GAI_SETUBI1,'0000',NULL,(SELECT M1.RYAKUNAMEJ FROM MAS_GAIOKR M1 WHERE D2.GAI_CD1 = M1.CODE AND M1.MSKBN = 'G')) GAI_CD1, " +
        "	GAI_TODAY1, " +
        "	DECODE(GAI_SETUBI2,'0000',NULL,DECODE(GAI_SETUBI2,NULL,NULL,'2')) GAI_IDX2, " +
        "	DECODE(GAI_SETUBI2,'0000',NULL,GAI_SETUBI2) GAI_SETUBI2, " +
        "	DECODE(GAI_SETUBI2,'0000',NULL,(SELECT M1.RYAKUNAMEJ FROM MAS_GAIOKR M1 WHERE D2.GAI_OKURI2 = M1.CODE AND M1.MSKBN = 'O')) GAI_OKURI2, " +
        "	DECODE(GAI_SETUBI2,'0000',NULL,(SELECT M1.RYAKUNAMEJ FROM MAS_GAIOKR M1 WHERE D2.GAI_CD2 = M1.CODE AND M1.MSKBN = 'G')) GAI_CD2, " +
        "	GAI_TODAY2, " +
        "	DECODE(GAI_SETUBI3,'0000',NULL,DECODE(GAI_SETUBI3,NULL,NULL,'3')) GAI_IDX3, " +
        "	DECODE(GAI_SETUBI3,'0000',NULL,GAI_SETUBI3) GAI_SETUBI3, " +
        "	DECODE(GAI_SETUBI3,'0000',NULL,(SELECT M1.RYAKUNAMEJ FROM MAS_GAIOKR M1 WHERE D2.GAI_OKURI3 = M1.CODE AND M1.MSKBN = 'O')) GAI_OKURI3, " +
        "	DECODE(GAI_SETUBI3,'0000',NULL,(SELECT M1.RYAKUNAMEJ FROM MAS_GAIOKR M1 WHERE D2.GAI_CD3 = M1.CODE AND M1.MSKBN = 'G')) GAI_CD3, " +
        "	GAI_TODAY3 " +
	"	,JCD202 " + // 2012-10-08 追加
        "FROM " +
	"	DAT_JDF_COM D1, " +
	"	DAT_LOT_FIX D2, " +
	"	DAT_LOT_COM_KINS D3, " +
	"	DAT_IJYO_KANRI@HIN_LINK D4, " +
	"	DAT_IJYO_SYOCHI@HIN_LINK D5 " +
	"WHERE " +
        "       D1.LINKKEY = D2.LINKKEY " +
	"AND	D1.LINKKEY = D3.LINKKEY " +
	"AND	D1.LINKKEY = D4.LINKKEY(+) " +
	"AND	D4.INPUTSEQ = D5.INPUTSEQ(+) " +
	"AND	D1.JYOTAI = 1 " +
        "UNION ALL " +
	"SELECT " +
	"	'2' TBLORDER,D1.LINKKEY,D2.HIJYU,D2.TANW,D2.SW, " +
        "       DECODE(D2.SW,0,0,D2.RYOW/D2.SW*100) SBD, " +
	"       0 KBD, 0 SRAITO, TO_CHAR(D1.HOHDT,'YYYY/MM/DD') HOHDT,TO_CHAR(D1.HOKDT,'YYYY/MM/DD') HOKDT,0 KKN,D2.NOKIYMD, " +
	"	NULL SAITEHAILTNO,NULL SAITEHAICYUNO, '' SAILINKKEY, " +
	"	TO_CHAR(D2.MSNO1,'FM0000') || TO_CHAR(D2.MSNOK,'FM00') MSNOK, " +
	"	TO_CHAR(D2.MSNO1,'FM0000') || TO_CHAR(D2.MSNOH,'FM00') MSNOH, " +
	"	TO_CHAR(D2.MSNO1,'FM0000') || TO_CHAR(D2.MSNOS,'FM00') MSNOS,TO_DATE(NULL) MODDATE, " +
	"       DECODE(D2.JCD112,'0',NULL,'1','約束','2','特急','3','外行','4','外戻') JCD112, " + // 2016/11/17 区分追加
	"       NULL HIKIKBN, NULL HIKICODE, 0 SAI_COUNT, " +
	"	NULL GAI_IDX1, NULL GAI_SETUBI1, NULL GAI_OKURI1, NULL GAI_CD1, TO_DATE(NULL) GAI_TODAY1, " +
	"	NULL GAI_IDX2, NULL GAI_SETUBI2, NULL GAI_OKURI2, NULL GAI_CD2, TO_DATE(NULL) GAI_TODAY2, " +
	"	NULL GAI_IDX3, NULL GAI_SETUBI3, NULL GAI_OKURI3, NULL GAI_CD3, TO_DATE(NULL) GAI_TODAY3 " +
	"	,JCD202 " + // 2012-10-08 追加
	"FROM " +
	"	DAT_JDF_FIX D1, " +
	"	DAT_JDF_COM2 D2 " +
	"WHERE " +
	"       D1.LINKKEY = D2.LINKKEY " +
        "UNION ALL " +
        "SELECT " +
	"	'3' TBLORDER,D1.LINKKEY,HIJYU,TANW,SW, " +
        "       DECODE(SW,0,0,RYOW/SW*100) SBD, " +
        "       DECODE(SW,0,0,KRAW/SW*100) KBD, " +
        "       DECODE(RYOW,0,0,KRAW/RYOW*100) SRAITO, " +
        "       TO_CHAR(HOHDT,'YYYY/MM/DD') HOHDT,TO_CHAR(HOKDT,'YYYY/MM/DD') HOKDT,KKN,NOKIYMD,SAITEHAILTNO,SAITEHAICYUNO, " +
	"	NVL(SAILINKKEY,'') SAILINKKEY,TO_CHAR(MSNO1,'FM0000') || TO_CHAR(MSNOK,'FM00') MSNOK,TO_CHAR(MSNO1,'FM0000') || TO_CHAR(MSNOH,'FM00') MSNOH,TO_CHAR(MSNO1,'FM0000') || TO_CHAR(MSNOS,'FM00') MSNOS,D5.MODDATE, " +
        "       DECODE(JCD112,'0',NULL,'1','約束','2','特急','3','外行','4','外戻') JCD112, " + // 2016/11/17 区分追加
        "       DECODE(HIKIKBN,'0',NULL,'A','Aｲﾝﾌﾟｯﾄ済','B','Bｲﾝﾌﾟｯﾄ済','H','引当済') HIKIKBN, " +
        "       DECODE(HIKICODE,'0',NULL,HIKICODE) HIKICODE, " +
        "       (SELECT COUNT(*) FROM DAT_IJYO_SYOCHI@HIN_LINK D2 WHERE D1.LINKKEY = D2.SAILINKKEY(+)) SAI_COUNT, " +
        "	DECODE(GAI_SETUBI1,'0000',NULL,DECODE(GAI_SETUBI1,NULL,NULL,'1')) GAI_IDX1, " +
        "	DECODE(GAI_SETUBI1,'0000',NULL,GAI_SETUBI1) GAI_SETUBI1, " +
        "	DECODE(GAI_SETUBI1,'0000',NULL,(SELECT M1.RYAKUNAMEJ FROM MAS_GAIOKR M1 WHERE D2.GAI_OKURI1 = M1.CODE AND M1.MSKBN = 'O')) GAI_OKURI1, " +
        "	DECODE(GAI_SETUBI1,'0000',NULL,(SELECT M1.RYAKUNAMEJ FROM MAS_GAIOKR M1 WHERE D2.GAI_CD1 = M1.CODE AND M1.MSKBN = 'G')) GAI_CD1, " +
        "	GAI_TODAY1, " +
        "	DECODE(GAI_SETUBI2,'0000',NULL,DECODE(GAI_SETUBI2,NULL,NULL,'2')) GAI_IDX2, " +
        "	DECODE(GAI_SETUBI2,'0000',NULL,GAI_SETUBI2) GAI_SETUBI2, " +
        "	DECODE(GAI_SETUBI2,'0000',NULL,(SELECT M1.RYAKUNAMEJ FROM MAS_GAIOKR M1 WHERE D2.GAI_OKURI2 = M1.CODE AND M1.MSKBN = 'O')) GAI_OKURI2, " +
        "	DECODE(GAI_SETUBI2,'0000',NULL,(SELECT M1.RYAKUNAMEJ FROM MAS_GAIOKR M1 WHERE D2.GAI_CD2 = M1.CODE AND M1.MSKBN = 'G')) GAI_CD2, " +
        "	GAI_TODAY2, " +
        "	DECODE(GAI_SETUBI3,'0000',NULL,DECODE(GAI_SETUBI3,NULL,NULL,'3')) GAI_IDX3, " +
        "	DECODE(GAI_SETUBI3,'0000',NULL,GAI_SETUBI3) GAI_SETUBI3, " +
        "	DECODE(GAI_SETUBI3,'0000',NULL,(SELECT M1.RYAKUNAMEJ FROM MAS_GAIOKR M1 WHERE D2.GAI_OKURI3 = M1.CODE AND M1.MSKBN = 'O')) GAI_OKURI3, " +
        "	DECODE(GAI_SETUBI3,'0000',NULL,(SELECT M1.RYAKUNAMEJ FROM MAS_GAIOKR M1 WHERE D2.GAI_CD3 = M1.CODE AND M1.MSKBN = 'G')) GAI_CD3, " +
        "	GAI_TODAY3 " +
	"	,JCD202 " + // 2012-10-08 追加
        "FROM " +
	"	DAT_R_JDF_COM D1, " +
	"	DAT_R_LOT_FIX D2, " +
	"	DAT_R_LOT_COM_KINS D3, " +
	"	DAT_IJYO_KANRI@HIN_LINK D4, " +
	"	DAT_IJYO_SYOCHI@HIN_LINK D5 " +
	"WHERE " +
        "       D1.LINKKEY = D2.LINKKEY " +
	"AND	D1.LINKKEY = D3.LINKKEY " +
	"AND	D1.LINKKEY = D4.LINKKEY(+) " +
	"AND	D4.INPUTSEQ = D5.INPUTSEQ(+) " +
	"AND	D1.JYOTAI = 2 " + // 完了：状態=2
	// 2014/09/25 追加 ▼
	"UNION ALL " +
        "SELECT " +
	"	'4' TBLORDER,D1.LINKKEY,HIJYU,TANW,SW, " +
        "       DECODE(SW,0,0,RYOW/SW*100) SBD, " +
        "       DECODE(SW,0,0,KRAW/SW*100) KBD, " +
        "       DECODE(RYOW,0,0,KRAW/RYOW*100) SRAITO, " +
        "       TO_CHAR(HOHDT,'YYYY/MM/DD') HOHDT,TO_CHAR(HOKDT,'YYYY/MM/DD') HOKDT,KKN,NOKIYMD,SAITEHAILTNO,SAITEHAICYUNO, " +
	"	NVL(SAILINKKEY,'') SAILINKKEY,TO_CHAR(MSNO1,'FM0000') || TO_CHAR(MSNOK,'FM00') MSNOK,TO_CHAR(MSNO1,'FM0000') || TO_CHAR(MSNOH,'FM00') MSNOH,TO_CHAR(MSNO1,'FM0000') || TO_CHAR(MSNOS,'FM00') MSNOS,D5.MODDATE, " +
        "       DECODE(JCD112,'0',NULL,'1','約束','2','特急','3','外行','4','外戻') JCD112, " + // 2016/11/17 区分追加
        "       DECODE(HIKIKBN,'0',NULL,'A','Aｲﾝﾌﾟｯﾄ済','B','Bｲﾝﾌﾟｯﾄ済','H','引当済') HIKIKBN, " +
        "       DECODE(HIKICODE,'0',NULL,HIKICODE) HIKICODE, " +
        "       (SELECT COUNT(*) FROM DAT_IJYO_SYOCHI@HIN_LINK D2 WHERE D1.LINKKEY = D2.SAILINKKEY(+)) SAI_COUNT, " +
        "	DECODE(GAI_SETUBI1,'0000',NULL,DECODE(GAI_SETUBI1,NULL,NULL,'1')) GAI_IDX1, " +
        "	DECODE(GAI_SETUBI1,'0000',NULL,GAI_SETUBI1) GAI_SETUBI1, " +
        "	DECODE(GAI_SETUBI1,'0000',NULL,(SELECT M1.RYAKUNAMEJ FROM MAS_GAIOKR M1 WHERE D2.GAI_OKURI1 = M1.CODE AND M1.MSKBN = 'O')) GAI_OKURI1, " +
        "	DECODE(GAI_SETUBI1,'0000',NULL,(SELECT M1.RYAKUNAMEJ FROM MAS_GAIOKR M1 WHERE D2.GAI_CD1 = M1.CODE AND M1.MSKBN = 'G')) GAI_CD1, " +
        "	GAI_TODAY1, " +
        "	DECODE(GAI_SETUBI2,'0000',NULL,DECODE(GAI_SETUBI2,NULL,NULL,'2')) GAI_IDX2, " +
        "	DECODE(GAI_SETUBI2,'0000',NULL,GAI_SETUBI2) GAI_SETUBI2, " +
        "	DECODE(GAI_SETUBI2,'0000',NULL,(SELECT M1.RYAKUNAMEJ FROM MAS_GAIOKR M1 WHERE D2.GAI_OKURI2 = M1.CODE AND M1.MSKBN = 'O')) GAI_OKURI2, " +
        "	DECODE(GAI_SETUBI2,'0000',NULL,(SELECT M1.RYAKUNAMEJ FROM MAS_GAIOKR M1 WHERE D2.GAI_CD2 = M1.CODE AND M1.MSKBN = 'G')) GAI_CD2, " +
        "	GAI_TODAY2, " +
        "	DECODE(GAI_SETUBI3,'0000',NULL,DECODE(GAI_SETUBI3,NULL,NULL,'3')) GAI_IDX3, " +
        "	DECODE(GAI_SETUBI3,'0000',NULL,GAI_SETUBI3) GAI_SETUBI3, " +
        "	DECODE(GAI_SETUBI3,'0000',NULL,(SELECT M1.RYAKUNAMEJ FROM MAS_GAIOKR M1 WHERE D2.GAI_OKURI3 = M1.CODE AND M1.MSKBN = 'O')) GAI_OKURI3, " +
        "	DECODE(GAI_SETUBI3,'0000',NULL,(SELECT M1.RYAKUNAMEJ FROM MAS_GAIOKR M1 WHERE D2.GAI_CD3 = M1.CODE AND M1.MSKBN = 'G')) GAI_CD3, " +
        "	GAI_TODAY3 " +
	"	,JCD202 " +
        "FROM " +
	"	DAT_S_JDF_COM D1, " +
	"	DAT_S_LOT_FIX D2, " +
	"	DAT_S_LOT_COM_KINS D3, " +
	"	DAT_IJYO_KANRI@HIN_LINK D4, " +
	"	DAT_IJYO_SYOCHI@HIN_LINK D5 " +
	"WHERE " +
        "       D1.LINKKEY = D2.LINKKEY " +
	"AND	D1.TOROKUDATE = D2.TOROKUDATE " +
	"AND	D1.LINKKEY = D3.LINKKEY " +
	"AND	D1.TOROKUDATE = D3.TOROKUDATE " +
	"AND	D1.LINKKEY = D4.LINKKEY(+) " +
	//"AND	D1.CYUZO_YY = D4.CYUZO_YY(+) " +
	"AND	D4.INPUTSEQ = D5.INPUTSEQ(+) " +
	// 2014/09/25 追加 ▲

	// 2016/04/24 追加 ▼
        "UNION ALL " +
        "SELECT " +
	"	'5' TBLORDER,D1.LINKKEY,HIJYU,TANW,SW, " +
        "       DECODE(SW,0,0,RYOW/SW*100) SBD, " +
        "       DECODE(SW,0,0,KRAW/SW*100) KBD, " +
        "       DECODE(RYOW,0,0,KRAW/RYOW*100) SRAITO, " +
        "       TO_CHAR(HOHDT,'YYYY/MM/DD') HOHDT,TO_CHAR(HOKDT,'YYYY/MM/DD') HOKDT,KKN,NOKIYMD,SAITEHAILTNO,SAITEHAICYUNO, " +
	"	NVL(SAILINKKEY,'') SAILINKKEY,TO_CHAR(MSNO1,'FM0000') || TO_CHAR(MSNOK,'FM00') MSNOK,TO_CHAR(MSNO1,'FM0000') || TO_CHAR(MSNOH,'FM00') MSNOH,TO_CHAR(MSNO1,'FM0000') || TO_CHAR(MSNOS,'FM00') MSNOS,D5.MODDATE, " +
        "       DECODE(JCD112,'0',NULL,'1','約束','2','特急','3','外行','4','外戻') JCD112, " + // 2016/11/17 区分追加
        "       DECODE(HIKIKBN,'0',NULL,'A','Aｲﾝﾌﾟｯﾄ済','B','Bｲﾝﾌﾟｯﾄ済','H','引当済') HIKIKBN, " +
        "       DECODE(HIKICODE,'0',NULL,HIKICODE) HIKICODE, " +
        "       (SELECT COUNT(*) FROM DAT_IJYO_SYOCHI@HIN_LINK D2 WHERE D1.LINKKEY = D2.SAILINKKEY(+)) SAI_COUNT, " +
        "	DECODE(GAI_SETUBI1,'0000',NULL,DECODE(GAI_SETUBI1,NULL,NULL,'1')) GAI_IDX1, " +
        "	DECODE(GAI_SETUBI1,'0000',NULL,GAI_SETUBI1) GAI_SETUBI1, " +
        "	DECODE(GAI_SETUBI1,'0000',NULL,(SELECT M1.RYAKUNAMEJ FROM MAS_GAIOKR M1 WHERE D2.GAI_OKURI1 = M1.CODE AND M1.MSKBN = 'O')) GAI_OKURI1, " +
        "	DECODE(GAI_SETUBI1,'0000',NULL,(SELECT M1.RYAKUNAMEJ FROM MAS_GAIOKR M1 WHERE D2.GAI_CD1 = M1.CODE AND M1.MSKBN = 'G')) GAI_CD1, " +
        "	GAI_TODAY1, " +
        "	DECODE(GAI_SETUBI2,'0000',NULL,DECODE(GAI_SETUBI2,NULL,NULL,'2')) GAI_IDX2, " +
        "	DECODE(GAI_SETUBI2,'0000',NULL,GAI_SETUBI2) GAI_SETUBI2, " +
        "	DECODE(GAI_SETUBI2,'0000',NULL,(SELECT M1.RYAKUNAMEJ FROM MAS_GAIOKR M1 WHERE D2.GAI_OKURI2 = M1.CODE AND M1.MSKBN = 'O')) GAI_OKURI2, " +
        "	DECODE(GAI_SETUBI2,'0000',NULL,(SELECT M1.RYAKUNAMEJ FROM MAS_GAIOKR M1 WHERE D2.GAI_CD2 = M1.CODE AND M1.MSKBN = 'G')) GAI_CD2, " +
        "	GAI_TODAY2, " +
        "	DECODE(GAI_SETUBI3,'0000',NULL,DECODE(GAI_SETUBI3,NULL,NULL,'3')) GAI_IDX3, " +
        "	DECODE(GAI_SETUBI3,'0000',NULL,GAI_SETUBI3) GAI_SETUBI3, " +
        "	DECODE(GAI_SETUBI3,'0000',NULL,(SELECT M1.RYAKUNAMEJ FROM MAS_GAIOKR M1 WHERE D2.GAI_OKURI3 = M1.CODE AND M1.MSKBN = 'O')) GAI_OKURI3, " +
        "	DECODE(GAI_SETUBI3,'0000',NULL,(SELECT M1.RYAKUNAMEJ FROM MAS_GAIOKR M1 WHERE D2.GAI_CD3 = M1.CODE AND M1.MSKBN = 'G')) GAI_CD3, " +
        "	GAI_TODAY3 " +
	"	,JCD202 " +
        "FROM " +
	"	DAT_L_JDF_COM D1, " +
	"	DAT_L_LOT_FIX D2, " +
	"	DAT_L_LOT_COM_KINS D3, " +
	"	DAT_IJYO_KANRI@HIN_LINK D4, " +
	"	DAT_IJYO_SYOCHI@HIN_LINK D5 " +
	"WHERE " +
        "       D1.LINKKEY = D2.LINKKEY " +
	"AND	D1.LINKKEY = D3.LINKKEY " +
	"AND	D1.LINKKEY = D4.LINKKEY(+) " +
	"AND	D4.INPUTSEQ = D5.INPUTSEQ(+) " +
	"AND	D1.JYOTAI = 3 " +
	// 2016/04/24 追加 ▲

        ") " +
        "WHERE " +
	"       LINKKEY = ? " +
	"       AND TBLORDER = ? " +
        "ORDER BY " +
	"       MODDATE DESC";


    //ｽﾀｯﾌ版ﾛｯﾄ情報SQL　進度情報 設備BOX
    private static final String STAFF_PROGRESS_BOX_QUERY =
        "SELECT * FROM ( " +
	"SELECT " +
	//"	D1.LINKKEY,D1.BOXNO,SBSM,NVL(SBMMDD,SBYMD) SBMMDD,SBMMDDC,SBX,SBY,SBZ,D1.SH_SBW SBW,REPLACE(SBPAT,'0','') SBPAT,SBYKY,SBTTY,SBMH, " +
	"	 '1' TBLORDER,D1.LINKKEY,D1.BOXNO,SBSM,NVL(SBMMDD,SBYMD) SBMMDD,SBMMDDC,SBX,SBY,SBZ,TRUNC(DECODE(SBSM,'KEN',SH_RYOW,SH_SBW)) SBW,REPLACE(SBPAT,'0','') SBPAT,SBYKY,SBTTY,SBMH, " +
        "       JBSCD,JBSDTM,JBEDTM,JBEDTC,JBSDATE, " +
        "	(CASE " +
        "	 WHEN JBSM IN('ANI','ANF') THEN JBSM || TRIM(TO_CHAR(SUBSTR(TO_CHAR(JBSCD,'000'),3,2),'99')) " +
        "	 ELSE JBSM END " +
        "	) JBSM, " +
        "       JBEDATE, " +
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBX,'9999990.000')) JBX, " +
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBY,'9999990.00')) JBY, " +
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBZ,'9999990.0')) JBZ, " +
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBRW,'9999990.0')) JBRW, " +
        "       DECODE(JBSM,NULL,NULL,JBPAT) JBPAT, " +
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBRMH,'9999990.0')) JBRMH, " +
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBBN,'0')) JBBN, " +
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(TAIRYUT,'9999990')) TAIRYUT " +
	"       ,SBK,TRUNC(DECODE(SBSM,'KEN',SH_RYOW,SH_SBW)) SH_SBW " + // 2012-10-08 追加
	"       ,D4.CYUZO_YY " + //2014/09/25 追加
	"FROM " +
	"	DAT_JDF_COM_SBOX D1, " +
	"	DAT_LOT_COM_JBOX D2, " +
	"	DAT_LOT_COM_JBOXEX D3, " +
	"	DAT_JDF_COM D4 " +
	"WHERE " +
	"	D1.LINKKEY = D2.LINKKEY(+) " +
	"AND	D1.BOXNO = D2.BOXNO(+) " +
	"AND	D1.LINKKEY = D3.LINKKEY(+) " +
	"AND	D1.BOXNO = D3.BOXNO(+) " +
	"AND	D1.LINKKEY = D4.LINKKEY " +
	"AND	D4.JYOTAI = 1 " + //状態
        "UNION ALL " +
        "SELECT " +
        "	 '2' TBLORDER,LINKKEY,BOXNO,SBSM,NVL(SBMMDD,SBYMD) SBMMDD,SBMMDDC,SBX,SBY,SBZ,SH_SBW SBW,REPLACE(SBPAT,'0','') SBPAT,SBYKY,SBTTY,SBMH, " +
        "	TO_NUMBER(NULL) JBSCD,TO_DATE(NULL) JBSDTM,TO_DATE(NULL) JBEDTM,TO_NUMBER(NULL) JBEDTC,TO_DATE(NULL) JBSDATE, " +
        "	NULL JBSM, TO_DATE(NULL) JBEDATE, NULL JBX, NULL JBY, NULL JBZ, NULL JBRW, NULL JBPAT, NULL JBRMH, NULL JBBN, NULL TAIRYUT " +
        "       ,SBK,SH_SBW " + // 2012-10-08 追加
	"       ,0 " + //2014/09/25 追加
	"FROM " +
        "	DAT_JDF_COM_SBOX2 " +
        "UNION ALL " +
	"SELECT " +
		//"	D1.LINKKEY,D1.BOXNO,SBSM,NVL(SBMMDD,SBYMD) SBMMDD,SBMMDDC,SBX,SBY,SBZ,D1.SH_SBW SBW,REPLACE(SBPAT,'0','') SBPAT,SBYKY,SBTTY,SBMH, " +
	"	 '3' TBLORDER,D1.LINKKEY,D1.BOXNO,SBSM,NVL(SBMMDD,SBYMD) SBMMDD,SBMMDDC,SBX,SBY,SBZ,TRUNC(DECODE(SBSM,'KEN',SH_RYOW,SH_SBW)) SBW,REPLACE(SBPAT,'0','') SBPAT,SBYKY,SBTTY,SBMH, " +
        "       JBSCD,JBSDTM,JBEDTM,JBEDTC,JBSDATE, " +
        "	(CASE " +
        "	 WHEN JBSM IN('ANI','ANF') THEN JBSM || TRIM(TO_CHAR(SUBSTR(TO_CHAR(JBSCD,'000'),3,2),'99')) " +
        "	 ELSE JBSM END " +
        "	) JBSM, " +
        "       DECODE(JBSM,NULL,TO_DATE(NULL),JBEDATE) JBEDATE, " +
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBX,'9999990.000')) JBX, " +
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBY,'9999990.00')) JBY, " +
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBZ,'9999990.0')) JBZ, " +
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBRW,'9999990.0')) JBRW, " +
        "       DECODE(JBSM,NULL,NULL,JBPAT) JBPAT, " +
//        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBRMH,'9999990.0')) JBRMH, " +
        "       DECODE(JBSM,NULL,NULL,'KEN',TO_CHAR(JBRMH - D5.MODN,'9999990.0'),TO_CHAR(JBRMH,'9999990.0')) JBRMH, " + //2015/10/5 修正
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBBN,'0')) JBBN, " +
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(TAIRYUT,'9999990')) TAIRYUT " +
	"       ,SBK,TRUNC(DECODE(SBSM,'KEN',SH_RYOW,SH_SBW)) SH_SBW " + // 2012-10-08 追加
	"       ,D4.CYUZO_YY " + //2014/09/25 追加
	"FROM " +
	"	DAT_R_JDF_COM_SBOX D1, " +
	"	DAT_R_LOT_COM_JBOX D2, " +
	"	DAT_R_LOT_COM_JBOXEX D3, " +
	"	DAT_R_JDF_COM D4, " +
	"	DAT_R_LOT_COM_KINS D5 " +
	"WHERE " +
	"	D1.LINKKEY = D2.LINKKEY(+) " +
	"AND	D1.BOXNO = D2.BOXNO(+) " +
	"AND	D1.LINKKEY = D3.LINKKEY(+) " +
	"AND	D1.BOXNO = D3.BOXNO(+) " +
	"AND	D1.LINKKEY = D4.LINKKEY " +
	"AND	D4.JYOTAI = 2 " + //状態
	"AND	D1.LINKKEY = D5.LINKKEY(+) " +

	// 2014/09/25 追加 ▼
	"UNION ALL " +
	"SELECT " +
	"	 '4' TBLORDER,D1.LINKKEY,D1.BOXNO,SBSM,NVL(SBMMDD,SBYMD) SBMMDD,SBMMDDC,SBX,SBY,SBZ,TRUNC(DECODE(SBSM,'KEN',SH_RYOW,SH_SBW)) SBW,REPLACE(SBPAT,'0','') SBPAT,SBYKY,SBTTY,SBMH, " +
        "       JBSCD,JBSDTM,JBEDTM,JBEDTC,JBSDATE, " +
        "	(CASE " +
        "	 WHEN JBSM IN('ANI','ANF') THEN JBSM || TRIM(TO_CHAR(SUBSTR(TO_CHAR(JBSCD,'000'),3,2),'99')) " +
        "	 ELSE JBSM END " +
        "	) JBSM, " +
        "       DECODE(JBSM,NULL,TO_DATE(NULL),JBEDATE) JBEDATE, " +
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBX,'9999990.000')) JBX, " +
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBY,'9999990.00')) JBY, " +
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBZ,'9999990.0')) JBZ, " +
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBRW,'9999990.0')) JBRW, " +
        "       DECODE(JBSM,NULL,NULL,JBPAT) JBPAT, " +
//        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBRMH,'9999990.0')) JBRMH, " +
        "       DECODE(JBSM,NULL,NULL,'KEN',TO_CHAR(JBRMH - D5.MODN,'9999990.0'),TO_CHAR(JBRMH,'9999990.0')) JBRMH, " + //2015/10/5 修正
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBBN,'0')) JBBN, " +
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(TAIRYUT,'9999990')) TAIRYUT " +
	"       ,SBK,TRUNC(DECODE(SBSM,'KEN',SH_RYOW,SH_SBW)) SH_SBW " +
	"       ,D4.CYUZO_YY " + //2014/09/25 追加
	"FROM " +
	"	DAT_S_JDF_COM_SBOX D1, " +
	"	DAT_S_LOT_COM_JBOX D2, " +
	"	DAT_S_LOT_COM_JBOXEX D3, " +
	"	DAT_S_JDF_COM D4, " +
	"	DAT_R_LOT_COM_KINS D5 " +
	"WHERE " +
	"	D1.LINKKEY = D2.LINKKEY(+) " +
	"AND	D1.BOXNO = D2.BOXNO(+) " +
	"AND	D1.TOROKUDATE = D2.TOROKUDATE(+) " +
	"AND	D1.LINKKEY = D3.LINKKEY(+) " +
	"AND	D1.BOXNO = D3.BOXNO(+) " +
	"AND	D1.TOROKUDATE = D3.TOROKUDATE(+) " +
	"AND	D1.LINKKEY = D4.LINKKEY " +
	"AND	D1.TOROKUDATE = D4.TOROKUDATE(+) " +
	// 2014/09/25 追加 ▲

	// 2016/04/24 追加 ▼
	"UNION ALL " +
	"SELECT " +
	"	 '5' TBLORDER,D1.LINKKEY,D1.BOXNO,SBSM,NVL(SBMMDD,SBYMD) SBMMDD,SBMMDDC,SBX,SBY,SBZ,TRUNC(DECODE(SBSM,'KEN',SH_RYOW,SH_SBW)) SBW,REPLACE(SBPAT,'0','') SBPAT,SBYKY,SBTTY,SBMH, " +
        "       JBSCD,JBSDTM,JBEDTM,JBEDTC,JBSDATE, " +
        "	(CASE " +
        "	 WHEN JBSM IN('ANI','ANF') THEN JBSM || TRIM(TO_CHAR(SUBSTR(TO_CHAR(JBSCD,'000'),3,2),'99')) " +
        "	 ELSE JBSM END " +
        "	) JBSM, " +
        "       DECODE(JBSM,NULL,TO_DATE(NULL),JBEDATE) JBEDATE, " +
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBX,'9999990.000')) JBX, " +
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBY,'9999990.00')) JBY, " +
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBZ,'9999990.0')) JBZ, " +
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBRW,'9999990.0')) JBRW, " +
        "       DECODE(JBSM,NULL,NULL,JBPAT) JBPAT, " +
        "       DECODE(JBSM,NULL,NULL,'KEN',TO_CHAR(JBRMH - D5.MODN,'9999990.0'),TO_CHAR(JBRMH,'9999990.0')) JBRMH, " +
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBBN,'0')) JBBN, " +
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(TAIRYUT,'9999990')) TAIRYUT " +
	"       ,SBK,TRUNC(DECODE(SBSM,'KEN',SH_RYOW,SH_SBW)) SH_SBW " +
	"       ,D4.CYUZO_YY " +
	"FROM " +
	"	DAT_L_JDF_COM_SBOX D1, " +
	"	DAT_L_LOT_COM_JBOX D2, " +
	"	DAT_L_LOT_COM_JBOXEX D3, " +
	"	DAT_L_JDF_COM D4, " +
	"	DAT_L_LOT_COM_KINS D5 " +
	"WHERE " +
	"	D1.LINKKEY = D2.LINKKEY(+) " +
	"AND	D1.BOXNO = D2.BOXNO(+) " +
	"AND	TRUNC(D1.TOROKUDATE) = TRUNC(D2.TOROKUDATE(+)) " +
	"AND	D1.LINKKEY = D3.LINKKEY(+) " +
	"AND	D1.BOXNO = D3.BOXNO(+) " +
	"AND	TRUNC(D1.TOROKUDATE) = TRUNC(D3.TOROKUDATE(+)) " +
	"AND	D1.LINKKEY = D4.LINKKEY " +
	"AND	TRUNC(D1.TOROKUDATE) = TRUNC(D4.TOROKUDATE(+)) " +
	"AND	D4.JYOTAI = 3 " +
	"AND	D1.LINKKEY = D5.LINKKEY(+) " +
        "AND	TRUNC(D1.TOROKUDATE) = TRUNC(D4.TOROKUDATE(+)) " +
	") " +
        "WHERE " +
	"       LINKKEY = ? " +
	"       AND CYUZO_YY = ? " +
	"       AND TBLORDER = ? " +
        "ORDER BY " +
	"       BOXNO";
	// 2016/04/24 追加 ▲

    // ｽﾀｯﾌ版ﾛｯﾄ情報SQL　進度情報 設計設備BOX
    // 2014/09/25 修正
    private static final String STAFF_PROGRESS_SBOX_QUERY =
        "SELECT * FROM ( " +
	"SELECT " +
	"    '1' TBLORDER,D1.LINKKEY,D1.BOXNO,D1.SBSM,NVL(D1.SBMMDD,SBYMD) SBMMDD,D1.SBMMDDC,D1.SBX,D1.SBY,D1.SBZ,D1.SH_SBW SBW,REPLACE(D1.SBPAT,'0','') SBPAT,D1.SBYKY,D1.SBTTY,D1.SBMH,D2.CYUZO_YY " +
	"FROM " +
	"    DAT_JDF_COM_SBOX D1 ,DAT_JDF_COM D2 " +
	"WHERE " +
	"    D1.LINKKEY = D2.LINKKEY(+) " +
	"    AND D2.JYOTAI = 1 " +
        "UNION ALL " +
        "SELECT " +
        "    '2' TBLORDER,D1.LINKKEY,D1.BOXNO,D1.SBSM,NVL(D1.SBMMDD,SBYMD) SBMMDD,D1.SBMMDDC,D1.SBX,D1.SBY,D1.SBZ,D1.SH_SBW SBW,REPLACE(D1.SBPAT,'0','') SBPAT,D1.SBYKY,D1.SBTTY,D1.SBMH,D2.CYUZO_YY " +
        "FROM  " +
        "    DAT_JDF_COM_SBOX2 D1 ,DAT_JDF_COM2 D2 " +
	"WHERE " +
	"    D1.LINKKEY = D2.LINKKEY(+) " +
	"UNION ALL " +
	"SELECT " +
	"    '3' TBLORDER,D1.LINKKEY,D1.BOXNO,D1.SBSM,NVL(D1.SBMMDD,SBYMD) SBMMDD,D1.SBMMDDC,D1.SBX,D1.SBY,D1.SBZ,D1.SH_SBW SBW,REPLACE(D1.SBPAT,'0','') SBPAT,D1.SBYKY,D1.SBTTY,D1.SBMH,D2.CYUZO_YY " +
	"FROM " +
	"    DAT_R_JDF_COM_SBOX D1 ,DAT_R_JDF_COM D2 " +
	"WHERE " +
	"    D1.LINKKEY = D2.LINKKEY(+) " +
	"    AND D2.JYOTAI = 2 " +
        "UNION ALL " +
	"SELECT " +
	"    '4' TBLORDER,D1.LINKKEY,D1.BOXNO,SBSM,NVL(D1.SBMMDD,SBYMD) SBMMDD,D1.SBMMDDC,D1.SBX,D1.SBY,D1.SBZ,D1.SH_SBW SBW,REPLACE(D1.SBPAT,'0','') SBPAT,D1.SBYKY,D1.SBTTY,D1.SBMH,D2.CYUZO_YY " +
	"FROM " +
	"    DAT_S_JDF_COM_SBOX D1 ,DAT_S_JDF_COM D2 " +
	"WHERE " +
	"    D1.LINKKEY = D2.LINKKEY(+) " +
	"    AND D1.TOROKUDATE = D2.TOROKUDATE(+) " +
	// 2016/04/24 追加 ▼
        "UNION ALL " +
	"SELECT " +
	"    '5' TBLORDER,D1.LINKKEY,D1.BOXNO,SBSM,NVL(D1.SBMMDD,SBYMD) SBMMDD,D1.SBMMDDC,D1.SBX,D1.SBY,D1.SBZ,D1.SH_SBW SBW,REPLACE(D1.SBPAT,'0','') SBPAT,D1.SBYKY,D1.SBTTY,D1.SBMH,D2.CYUZO_YY " +
	"FROM " +
	"    DAT_L_JDF_COM_SBOX D1 ,DAT_L_JDF_COM D2 " +
	"WHERE " +
	"    D1.LINKKEY = D2.LINKKEY(+) " +
	"    AND TRUNC(D1.TOROKUDATE) = TRUNC(D2.TOROKUDATE(+)) " +
	// 2016/04/24 追加 ▲

        ") " +
        "WHERE " +
	"    LINKKEY = ? " +
	"    AND CYUZO_YY = ? " +
	"    AND TBLORDER = ? " +
        "ORDER BY " +
	"    BOXNO";


    //ｽﾀｯﾌ版ﾛｯﾄ情報SQL　進度情報　実績設備BOX
    private static final String STAFF_PROGRESS_JBOX_QUERY =
        "SELECT " +
        "        TBLORDER,JBSCD,JBSDTM,JBEDTM,JBEDTC,JBSDATE,JBSM,JBEDATE,JBX,JBY,JBZ,JBRW,JBPAT,JBRMH,JBBN,TAIRYUT,JBFO,JBBO,JBGOOD,CYUZO_YY " +
        "FROM ( " +
	"SELECT " +
	"	'1' TBLORDER,D1.LINKKEY,D1.BOXNO, " +
        "       JBSCD,JBSDTM,JBEDTM,JBEDTC,JBSDATE, " +
        "	(CASE " +
        "	 WHEN JBSM IN('ANI','ANF') THEN JBSM || TRIM(TO_CHAR(SUBSTR(TO_CHAR(JBSCD,'000'),3,2),'99')) " +
        // 2012-10-01 設備追加-start-
	"	 WHEN JBSCD = 211 THEN 'PSW1' " +
	"	 WHEN JBSCD = 212 THEN 'PSW2' " +
	// 2012-10-01 設備追加-end-
	"	 ELSE JBSM END " +
        "	) JBSM, " +
        "       JBEDATE, " +
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBX,'9999990.000')) JBX, " +
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBY,'9999990.00')) JBY, " +
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBZ,'9999990.0')) JBZ, " +
//        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBRW,'9999990.0')) JBRW, " +
        "       CASE WHEN JBSM is NULL THEN NULL WHEN JBSM = 'KEN' THEN TO_CHAR(JBRW - D4.MODW,'9999990.0') ELSE TO_CHAR(JBRW,'9999990.0') END JBRW, " + //2015/10/6 修正
        "       DECODE(JBSM,NULL,NULL,JBPAT) JBPAT, " +
//        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBRMH,'9999990.0')) JBRMH, " +
        "       CASE WHEN JBSM is NULL THEN NULL WHEN JBSM = 'KEN' THEN TO_CHAR(JBRMH - D4.MODN,'9999990.0') ELSE TO_CHAR(JBRMH,'9999990.0') END JBRMH, " + //2015/10/6 修正
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBBN,'0')) JBBN, " +
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(TAIRYUT,'9999990')) TAIRYUT, " +
	"	DECODE(JBSM,NULL,NULL,TO_CHAR(DECODE(round(JBFO,0),NULL,NULL,0,NULL,round(JBFO,0)))) JBFO, " + //2013_01_28追加
	"	DECODE(JBSM,NULL,NULL,TO_CHAR(DECODE(round(JBBO,0),NULL,NULL,0,NULL,round(JBBO,0)))) JBBO, " + //2013_01_28追加
	"       DECODE(D1.JBGOOD,NULL,NULL,TO_CHAR(DECODE(round(D1.JBGOOD,0),NULL,NULL,0,NULL,round(D1.JBGOOD,0)))) JBGOOD, " + // 2014/10/29追加
	"       D3.CYUZO_YY " +
	"FROM " +
	"	DAT_LOT_COM_JBOX D1, " +
	"	DAT_LOT_COM_JBOXEX D2, " +
	"	DAT_JDF_COM D3, " +
	"	DAT_LOT_COM_KINS D4 " +

	"WHERE " +
	"	    D1.LINKKEY = D2.LINKKEY(+) " +
	"       AND D1.BOXNO = D2.BOXNO(+) " +
	"       AND D1.LINKKEY = D3.LINKKEY(+) " +
	"       AND D3.JYOTAI = 1 " + //状態
	"	AND D1.LINKKEY = D4.LINKKEY(+) " +

        "UNION ALL " +
        "SELECT " +
        "	'2' TBLORDER,LINKKEY,TO_NUMBER(NULL) BOXNO,TO_NUMBER(NULL) JBSCD,TO_DATE(NULL) JBSDTM,TO_DATE(NULL) JBEDTM,TO_NUMBER(NULL) JBEDTC, " +
        "	TO_DATE(NULL) JBSDATE,NULL JBSM,TO_DATE(NULL) JBEDATE,NULL JBX,NULL JBY,NULL JBZ, NULL JBRW,NULL JBPAT,NULL JBRMH,NULL JBBN,NULL TAIRYUT " +
	"	,NULL JBFO, NULL JBBO ,NULL JBGOOD" +
	"	,CYUZO_YY " +
        "FROM " +
        "	DAT_JDF_COM2 " +
        "WHERE " +
        "	EXISTS (SELECT * FROM DAT_JDF_COM2 WHERE LINKKEY = ? ) " +
        "       AND LINKKEY = ?  " +
        "UNION ALL " +
	"SELECT " +
	"	'3' TBLORDER,D1.LINKKEY,D1.BOXNO, " +
        "       JBSCD,JBSDTM,JBEDTM,JBEDTC,JBSDATE, " +
        "	(CASE " +
        "	 WHEN JBSM IN('ANI','ANF') THEN JBSM || TRIM(TO_CHAR(SUBSTR(TO_CHAR(JBSCD,'000'),3,2),'99')) " +
        // 2012-10-01 設備追加-start-
	"	 WHEN JBSCD = 211 THEN 'PSW1' " +
	"	 WHEN JBSCD = 212 THEN 'PSW2' " +
	// 2012-10-01 設備追加-end-
	"	 ELSE JBSM END " +
        "	) JBSM, " +
        "       JBEDATE, " +
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBX,'9999990.000')) JBX, " +
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBY,'9999990.00')) JBY, " +
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBZ,'9999990.0')) JBZ, " +
//        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBRW,'9999990.0')) JBRW, " +
        "       CASE WHEN JBSM is NULL THEN NULL WHEN JBSM = 'KEN' THEN TO_CHAR(JBRW - D4.MODW,'9999990.0') ELSE TO_CHAR(JBRW,'9999990.0') END JBRW, " + // 2015/10/6 修正
        "       DECODE(JBSM,NULL,NULL,JBPAT) JBPAT, " +
//        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBRMH,'9999990.0')) JBRMH, " +
        "       CASE WHEN JBSM is NULL THEN NULL WHEN JBSM = 'KEN' THEN TO_CHAR(JBRMH - D4.MODN,'9999990.0') ELSE TO_CHAR(JBRMH,'9999990.0') END JBRMH, " + // 2015/10/6 修正
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBBN,'0')) JBBN, " +
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(TAIRYUT,'9999990')) TAIRYUT, " +
	"	DECODE(JBSM,NULL,NULL,TO_CHAR(DECODE(round(JBFO,0),NULL,NULL,0,NULL,round(JBFO,0)))) JBFO, " + //2013_01_28追加
	"	DECODE(JBSM,NULL,NULL,TO_CHAR(DECODE(round(JBBO,0),NULL,NULL,0,NULL,round(JBBO,0)))) JBBO, " + //2013_01_28追加
	"       DECODE(D1.JBGOOD,NULL,NULL,TO_CHAR(DECODE(round(D1.JBGOOD,0),NULL,NULL,0,NULL,round(D1.JBGOOD,0)))) JBGOOD, " + // 2014/10/29追加
	"       D3.CYUZO_YY " +
	"FROM " +
	"	DAT_R_LOT_COM_JBOX D1, " +
	"	DAT_R_LOT_COM_JBOXEX D2, " +
	"	DAT_R_JDF_COM D3, " +
	"	DAT_R_LOT_COM_KINS D4 " +
	"WHERE " +
	"	D1.LINKKEY = D2.LINKKEY(+) " +
	"       AND D1.BOXNO = D2.BOXNO(+) " +
	"       AND D1.LINKKEY = D3.LINKKEY(+) " +
	"       AND D3.JYOTAI = 2 " + //状態
	//"       AND D1.TOROKUDATE = D3.TOROKUDATE(+) " +
	"       AND D1.LINKKEY = D4.LINKKEY(+) " +
	// 2014/09/25 追加 ▼
        "UNION ALL " +
	"SELECT " +
	"	'4' TBLORDER,D1.LINKKEY,D1.BOXNO, " +
        "       JBSCD,JBSDTM,JBEDTM,JBEDTC,JBSDATE, " +
        "	(CASE " +
        "	 WHEN JBSM IN('ANI','ANF') THEN JBSM || TRIM(TO_CHAR(SUBSTR(TO_CHAR(JBSCD,'000'),3,2),'99')) " +
	"	 WHEN JBSCD = 211 THEN 'PSW1' " +
	"	 WHEN JBSCD = 212 THEN 'PSW2' " +
	"	 ELSE JBSM END " +
        "	) JBSM, " +
        "       JBEDATE, " +
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBX,'9999990.000')) JBX, " +
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBY,'9999990.00')) JBY, " +
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBZ,'9999990.0')) JBZ, " +
//        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBRW,'9999990.0')) JBRW, " +
        "       CASE WHEN JBSM is NULL THEN NULL WHEN JBSM = 'KEN' THEN TO_CHAR(JBRW - D4.MODW,'9999990.0') ELSE TO_CHAR(JBRW,'9999990.0') END JBRW, " +  //2015/10/6 修正
        "       DECODE(JBSM,NULL,NULL,JBPAT) JBPAT, " +
//        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBRMH,'9999990.0')) JBRMH, " +
        "       CASE WHEN JBSM is NULL THEN NULL WHEN JBSM = 'KEN' THEN TO_CHAR(JBRW - D4.MODN,'9999990.0') ELSE TO_CHAR(JBRMH,'9999990.0') END JBRMH, " +  //2015/10/6修正
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBBN,'0')) JBBN, " +
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(TAIRYUT,'9999990')) TAIRYUT, " +
	"	DECODE(JBSM,NULL,NULL,TO_CHAR(DECODE(round(JBFO,0),NULL,NULL,0,NULL,round(JBFO,0)))) JBFO, " +
	"	DECODE(JBSM,NULL,NULL,TO_CHAR(DECODE(round(JBBO,0),NULL,NULL,0,NULL,round(JBBO,0)))) JBBO, " +
	"       DECODE(D1.JBGOOD,NULL,NULL,TO_CHAR(DECODE(round(D1.JBGOOD,0),NULL,NULL,0,NULL,round(D1.JBGOOD,0)))) JBGOOD, " + // 2014/10/29追加
        "       D3.CYUZO_YY " +
	"FROM " +
	"	DAT_S_LOT_COM_JBOX D1, " +
	"	DAT_S_LOT_COM_JBOXEX D2, " +
	"	DAT_S_JDF_COM D3," +
	"	DAT_S_LOT_COM_KINS D4 " +

	"WHERE " +
	"	    D1.LINKKEY = D2.LINKKEY(+) " +
	"       AND D1.TOROKUDATE = D2.TOROKUDATE(+) " +
	"       AND D1.BOXNO = D2.BOXNO(+) " +
	"       AND D1.TOROKUDATE = D2.TOROKUDATE(+) " +
	"       AND D1.LINKKEY = D3.LINKKEY(+) " +
	"       AND D1.TOROKUDATE = D3.TOROKUDATE(+) " +
	// 2014/09/25 追加 ▲
	"       AND D1.LINKKEY = D4.LINKKEY(+) " +
	// 2016/04/24 追加 ▼
        "UNION ALL " +
	"SELECT " +
	"	'5' TBLORDER,D1.LINKKEY,D1.BOXNO, " +
        "       JBSCD,JBSDTM,JBEDTM,JBEDTC,JBSDATE, " +
        "	(CASE " +
        "	 WHEN JBSM IN('ANI','ANF') THEN JBSM || TRIM(TO_CHAR(SUBSTR(TO_CHAR(JBSCD,'000'),3,2),'99')) " +
	"	 WHEN JBSCD = 211 THEN 'PSW1' " +
	"	 WHEN JBSCD = 212 THEN 'PSW2' " +
	"	 ELSE JBSM END " +
        "	) JBSM, " +
        "       JBEDATE, " +
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBX,'9999990.000')) JBX, " +
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBY,'9999990.00')) JBY, " +
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBZ,'9999990.0')) JBZ, " +
        "       CASE WHEN JBSM is NULL THEN NULL WHEN JBSM = 'KEN' THEN TO_CHAR(JBRW - D4.MODW,'9999990.0') ELSE TO_CHAR(JBRW,'9999990.0') END JBRW, " +
        "       DECODE(JBSM,NULL,NULL,JBPAT) JBPAT, " +
        "       CASE WHEN JBSM is NULL THEN NULL WHEN JBSM = 'KEN' THEN TO_CHAR(JBRW - D4.MODN,'9999990.0') ELSE TO_CHAR(JBRMH,'9999990.0') END JBRMH, " +
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(JBBN,'0')) JBBN, " +
        "       DECODE(JBSM,NULL,NULL,TO_CHAR(TAIRYUT,'9999990')) TAIRYUT, " +
	"	DECODE(JBSM,NULL,NULL,TO_CHAR(DECODE(round(JBFO,0),NULL,NULL,0,NULL,round(JBFO,0)))) JBFO, " +
	"	DECODE(JBSM,NULL,NULL,TO_CHAR(DECODE(round(JBBO,0),NULL,NULL,0,NULL,round(JBBO,0)))) JBBO, " +
	"       DECODE(D1.JBGOOD,NULL,NULL,TO_CHAR(DECODE(round(D1.JBGOOD,0),NULL,NULL,0,NULL,round(D1.JBGOOD,0)))) JBGOOD, " +
        "       D3.CYUZO_YY " +
	"FROM " +
	"	DAT_L_LOT_COM_JBOX D1, " +
	"	DAT_L_LOT_COM_JBOXEX D2, " +
	"	DAT_L_JDF_COM D3," +
	"	DAT_L_LOT_COM_KINS D4 " +

	"WHERE " +
	"	    D1.LINKKEY = D2.LINKKEY(+) " +
	"       AND TRUNC(D1.TOROKUDATE) = TRUNC(D2.TOROKUDATE(+)) " +
	"       AND D1.BOXNO = D2.BOXNO(+) " +
	"       AND TRUNC(D1.TOROKUDATE) = TRUNC(D2.TOROKUDATE(+)) " +
	"       AND D1.LINKKEY = D3.LINKKEY(+) " +
	"       AND TRUNC(D1.TOROKUDATE) = TRUNC(D3.TOROKUDATE(+)) " +
	"       AND D1.LINKKEY = D4.LINKKEY(+) " +
	// 2016/04/24 追加 ▲
	") " +
        "WHERE " +
	"       LINKKEY = ? " +
	"    AND CYUZO_YY = ? " +
	"    AND TBLORDER = ? " +
        "ORDER BY " +
	"       BOXNO";


    //ｽﾀｯﾌ版ﾛｯﾄ情報SQL　製造情報
    private static final String STAFF_MANUFACTURE_QUERY =
        "SELECT * FROM ( " +
	"SELECT " +
        "       '1' TBLORDER,D1.LINKKEY,SKIGO,SX,SY,SZ,STW,JUP,SW,H_MENS,MENW,PITJ,PIT,PRJ,ATJ,HOTJ,CROSS1,HBCOD,F1X,FX,HY,HZ, " +
        "       JCD202,H_IATMP1,H_IATIME1, " +
        "       DECODE(SUBSTR(D1.LTB,1,2),'H3',H_ATMP,H_STMP) H_TMP, " +
        "       DECODE(SUBSTR(D1.LTB,1,2),'H3',H_ATIME,H_STIME) H_TIME, " +
        "       H_STMP,H_ATMP,H_STIME,H_ATIME,YTMP,YTIME,ZTMP2,ZTIME2, " +
        "       JSWW,JSWDTM,JMW,JMDTM,JMX, " +
        "       JCW,JCDTM,JPJ,JPRB,JPRJ,JPDT,JPDT2,JPO2,JPDT3,JPO3,JPDT4,HPI2,HPI5,ROUND(NVL(HPI5-HPI2,0)*1440) HOT_TIME, " +
        "       D3.JHFTMP,D3.JHF1X,D3.JHF1TMP,D3.HOTPC,D5.HCID,D3.JBUW JBUW_HOT,D3.JBLW JBLW_HOT,D3.JBUB JBUB_HOT,D3.JBLB JBLB_HOT, " +
        "       D4.JBUW JBUW_CM,D4.JBLW JBLW_CM,NVL(D4.JBUB,0) JBUB_CM,NVL(D4.JBLB,0) JBLB_CM,D4.JBLN JBLN_CM, " +
        "       D7.JBEDTM JBEDTM_HOT,D7.JBEDTC JBEDTC_HOT, " +
        "       D8.JBEDTM JBEDTM_CM,D8.JBEDTC JBEDTC_CM, " +
        //"     D9.JBFO JBFO_SIAGE,D9.JBBO JBBO_SIAGE,D9.JBNX JBNX_SIAGE,D9.JBNR JBNR_SIAGE,D9.JBEDTM JBEDTM_SIAGE,D9.JBEDTC JBEDTC_SIAGE, " +
        "       D9.JBFO JBFO_SIAGE,D9.JBBO JBBO_SIAGE,D1.SPX SPX_SIAGE,D1.SPNAV SPNAV_SIAGE,D9.JBEDTM JBEDTM_SIAGE,D9.JBEDTC JBEDTC_SIAGE, " +
        "       D10.JBSDATE JBSDATE_CM,D10.JBEDATE JBEDATE_CM,D10.TAIRYUT TAIRYUT_CM, " +
        "       D11.JBSDATE JBSDATE_SIAGE,D11.JBEDATE JBEDATE_SIAGE,D11.TAIRYUT TAIRYUT_SIAGE, " +
        "       CASE WHEN H_IATMP1 > 0 OR H_IATIME1 > 0 THEN NVL(D12.JBSDATE, NVL(D13.JBSDATE,'')) ELSE null END JBSDATE_ANI, " +
        "       CASE WHEN H_IATMP1 > 0 OR H_IATIME1 > 0 THEN NVL(D12.JBEDATE, NVL(D13.JBEDATE,'')) ELSE null END JBEDATE_ANI, " +
        "       CASE WHEN DECODE(SUBSTR(D1.LTB,1,2),'H3',H_ATMP,H_STMP) > 0 OR DECODE(SUBSTR(D1.LTB,1,2),'H3',H_ATIME,H_STIME) > 0 THEN NVL(D14.JBSDATE, NVL(D15.JBSDATE,'')) ELSE null END JBSDATE_ANF, " +
        "       CASE WHEN DECODE(SUBSTR(D1.LTB,1,2),'H3',H_ATMP,H_STMP) > 0 OR DECODE(SUBSTR(D1.LTB,1,2),'H3',H_ATIME,H_STIME) > 0 THEN NVL(D14.JBEDATE, NVL(D15.JBEDATE,'')) ELSE null END JBEDATE_ANF, " +
// @S 2011/02/25 MOD
//        "       DECODE(SUBSTR(D1.LTB,1,1),'T',NVL(D14.JBSDATE, NVL(D15.JBSDATE,'')),'') JBSDATE_JIKO, DECODE(SUBSTR(D1.LTB,1,1),'T',NVL(D14.JBEDATE, NVL(D15.JBEDATE,'')),'') JBEDATE_JIKO, " +
//        "       NVL(D16.JBSDATE, '') JBSDATE_QF,NVL(D16.JBEDATE, '') JBEDATE_QF, " +
        "       DECODE(SUBSTR(D1.LTB,1,1),'T',NVL(D14.JBSDATE, ''),'') JBSDATE_JIKO, " +
		"       DECODE(SUBSTR(D1.LTB,1,1),'T',NVL(D14.JBEDATE, ''),'') JBEDATE_JIKO, " +
    	"       DECODE(SUBSTR(D1.LTB,1,1),'T',NVL(D16.JBSDATE,NVL(D15.JBSDATE, '')),'') JBSDATE_QF, " +
		"       DECODE(SUBSTR(D1.LTB,1,1),'T',NVL(D16.JBEDATE,NVL(D15.JBEDATE, '')),'') JBEDATE_QF, " +
// @E 2011/02/25
        "       PITBI, ROUND(DECODE(NVL(SH_HZ*FX,0),0,0,DECODE(NVL(F1X,0),0,0,SH_HZ*FX/F1X)),1) F1Z, " +
        "       ROUND(NVL(JPDT4-JPDT1,0)*1440) PIT_TIME,JPATJ, " +
        "       ROUND(NVL(D10.JBEDATE-D10.JBSDATE,0)*1440) CM_TIME, " +
        "       ROUND(NVL(D11.JBEDATE-D11.JBSDATE,0)*1440) SIAGE_TIME " +
        "FROM " +
        "       DAT_JDF_COM D1, " +
        "       DAT_LOT_COM D2, " +
        "       DAT_LOT_COM_HOT D3, " +
        "       DAT_LOT_COM_CM D4, " +
        "       DAT_LOT_FIX D5, " +
        "       DAT_LOT_FIXEX D6, " +
        "       DAT_LOT_COM_JBOX D7, " +
        "       DAT_LOT_COM_JBOX D8, " +
        "       DAT_LOT_COM_JBOX D9, " +
        "       DAT_LOT_COM_JBOXEX D10, " +
        "       DAT_LOT_COM_JBOXEX D11, " +
        "       DAT_LOT_COM_JBOXEX D12, " +
        "       DAT_LOT_COM_JBOXEX D13, " +
        "       DAT_LOT_COM_JBOXEX D14, " +
        "       DAT_LOT_COM_JBOXEX D15, " +
        "       DAT_LOT_COM_JBOXEX D16, " +
        "       (SELECT LINKKEY, MAX(BOXNO) BOXNO FROM DAT_LOT_COM_JBOX WHERE JBSM = 'QF' GROUP BY LINKKEY, BOXNO) D17 " +
        "WHERE " +
        "	D1.LINKKEY = D2.LINKKEY " +
	"AND	D1.LINKKEY = D3.LINKKEY(+) " +
	"AND	D6.LINKKEY = D4.LINKKEY(+) " +
	"AND	D1.LINKKEY = D5.LINKKEY " +
	"AND	D1.LINKKEY = D6.LINKKEY " +
	"AND	D6.CMJ_BOXNO = D4.BOXNO(+) " +
	"AND	D6.LINKKEY = D7.LINKKEY(+) " +
	"AND	D6.HOT_BOXNO = D7.BOXNO(+) " +
	"AND	D6.LINKKEY = D8.LINKKEY(+) " +
	"AND	D6.CMJ_BOXNO = D8.BOXNO(+) " +
	"AND	D6.LINKKEY = D9.LINKKEY(+) " +
	"AND	D6.SIAGEJ_BOXNO = D9.BOXNO(+) " +
	"AND	D6.LINKKEY = D10.LINKKEY(+) " +
	"AND	D6.CMJ_BOXNO = D10.BOXNO(+) " +
	"AND	D6.LINKKEY = D11.LINKKEY(+) " +
	"AND	D6.SIAGEJ_BOXNO = D11.BOXNO(+) " +
	"AND	D6.LINKKEY = D12.LINKKEY(+) " +
	"AND	D6.ANI_JBOXNO = D12.BOXNO(+) " +
	"AND	D6.LINKKEY = D13.LINKKEY(+) " +
	"AND	D6.CAI_JBOXNO = D13.BOXNO(+) " +
	"AND	D6.LINKKEY = D14.LINKKEY(+) " +
	"AND	D6.ANF_JBOXNO = D14.BOXNO(+) " +
	"AND	D6.LINKKEY = D15.LINKKEY(+) " +
	"AND	D6.CAF_JBOXNO = D15.BOXNO(+) " +
	"AND	D1.LINKKEY = D17.LINKKEY(+) " +
	"AND	D17.LINKKEY = D16.LINKKEY(+) " +
	"AND	D17.BOXNO = D16.BOXNO(+) " +
	"AND	D1.JYOTAI = 1 " + //状態
        "UNION ALL " +
        "SELECT " +
        "      '2' TBLORDER,D1.LINKKEY,D2.SKIGO,D2.SX,D2.SY,D2.SZ,D2.STW,D2.JUP,D2.SW,D2.H_MENS,D2.MENW, " +
        "       D2.PITJ,D2.PIT,D2.PRJ,D2.ATJ,D2.HOTJ,D2.CROSS1,D2.HBCOD,D2.F1X,D2.FX,D2.HY,D2.HZ, " +
        "       D2.JCD202,D2.H_IATMP1,D2.H_IATIME1, " +
        "       DECODE(SUBSTR(D2.LTB,1,2),'H3',D2.H_ATMP,D2.H_STMP) H_TMP, " +
        "       DECODE(SUBSTR(D2.LTB,1,2),'H3',D2.H_ATIME,D2.H_STIME) H_TIME, " +
        "       D2.H_STMP,D2.H_ATMP,D2.H_STIME,D2.H_ATIME,D2.YTMP,D2.YTIME,D2.ZTMP2,D2.ZTIME2, " +
        "       0 JSWW,TO_DATE(NULL) JSWDTM,0 JMW,TO_DATE(NULL) JMDTM,0 JMX,0 JCW,TO_DATE(NULL) JCDTM, " +
        "       NULL JPJ,0 JPRB,0 JPRJ,TO_DATE(NULL) JPDT,TO_DATE(NULL) JPDT2,0 JPO2,TO_DATE(NULL) JPDT3,0 JPO3, " +
        "       TO_DATE(NULL) JPDT4,TO_DATE(NULL) HPI2,TO_DATE(NULL) HPI5, " +
        "       0 HOT_TIME,0 JHFTMP,0 JHF1X,0 JHF1TMP,0 HOTPC,NULL HCID, " +
        "       0 JBUW_HOT,0 JBLW_HOT,0 JBUB_HOT,0 JBLB_HOT,0 JBUW_CM,0 JBLW_CM,0 JBUB_CM,0 JBLB_CM,0 JBLN_CM, " +
        "       TO_DATE(NULL) JBEDTM_HOT,TO_NUMBER(NULL) JBEDTC_HOT,TO_DATE(NULL) JBEDTM_CM,TO_NUMBER(NULL) JBEDTC_CM, " +
        "       0 JBFO_SIAGE,0 JBBO_SIAGE, " +
        "       D2.SPX SPX_SIAGE,D2.SPNAV SPNAV_SIAGE,TO_DATE(NULL) JBEDTM_SIAGE,TO_NUMBER(NULL) JBEDTC_SIAGE, " +
        "       TO_DATE(NULL) JBSDATE_CM,TO_DATE(NULL) JBEDATE_CM,0 TAIRYUT_CM, " +
        "       TO_DATE(NULL) JBSDATE_SIAGE,TO_DATE(NULL) JBEDATE_SIAGE,0 TAIRYUT_SIAGE, " +
        "       TO_DATE(NULL) JBSDATE_ANI,TO_DATE(NULL) JBEDATE_ANI,TO_DATE(NULL) JBSDATE_ANF,TO_DATE(NULL) JBEDATE_ANF, " +
        "       TO_DATE(NULL) JBSDATE_JIKO,TO_DATE(NULL) JBEDATE_JIKO,TO_DATE(NULL) JBSDATE_QF,TO_DATE(NULL) JBEDATE_QF, " +
        "       D2.PITBI,ROUND(DECODE(NVL(D2.SH_HZ*D2.FX,0),0,0,DECODE(NVL(D2.F1X,0),0,0,D2.SH_HZ*D2.FX/D2.F1X)),1) F1Z, " +
        "       0 PIT_TIME,TO_NUMBER(NULL) JPATJ,0 CM_TIME,0 SIAGE_TIME " +
        "FROM " +
        "	DAT_JDF_FIX D1, " +
        "	DAT_JDF_COM2 D2 " +
        "WHERE " +
        "	D1.LINKKEY = D2.LINKKEY " +
        "UNION ALL " +
	"SELECT " +
        "       '3' TBLORDER,D1.LINKKEY,SKIGO,SX,SY,SZ,STW,JUP,SW,H_MENS,MENW,PITJ,PIT,PRJ,ATJ,HOTJ,CROSS1,HBCOD,F1X,FX,HY,HZ, " +
        "       JCD202,H_IATMP1,H_IATIME1, " +
        "       DECODE(SUBSTR(D1.LTB,1,2),'H3',H_ATMP,H_STMP) H_TMP, " +
        "       DECODE(SUBSTR(D1.LTB,1,2),'H3',H_ATIME,H_STIME) H_TIME, " +
        "       H_STMP,H_ATMP,H_STIME,H_ATIME,YTMP,YTIME,ZTMP2,ZTIME2, " +
        "       JSWW,JSWDTM,JMW,JMDTM,JMX, " +
        "       JCW,JCDTM,JPJ,JPRB,JPRJ,JPDT,JPDT2,JPO2,JPDT3,JPO3,JPDT4,HPI2,HPI5,ROUND(NVL(HPI5-HPI2,0)*1440) HOT_TIME, " +
        "       D3.JHFTMP,D3.JHF1X,D3.JHF1TMP,D3.HOTPC,D5.HCID,D3.JBUW JBUW_HOT,D3.JBLW JBLW_HOT,D3.JBUB JBUB_HOT,D3.JBLB JBLB_HOT, " +
        "       D4.JBUW JBUW_CM,D4.JBLW JBLW_CM,NVL(D4.JBUB,0) JBUB_CM,NVL(D4.JBLB,0) JBLB_CM,D4.JBLN JBLN_CM, " +
        "       D7.JBEDTM JBEDTM_HOT,D7.JBEDTC JBEDTC_HOT, " +
        "       D8.JBEDTM JBEDTM_CM,D8.JBEDTC JBEDTC_CM, " +
        "       D9.JBFO JBFO_SIAGE,D9.JBBO JBBO_SIAGE,D1.SPX SPX_SIAGE,D1.SPNAV SPNAV_SIAGE,D9.JBEDTM JBEDTM_SIAGE,D9.JBEDTC JBEDTC_SIAGE, " +
        "       D10.JBSDATE JBSDATE_CM,D10.JBEDATE JBEDATE_CM,D10.TAIRYUT TAIRYUT_CM, " +
        "       D11.JBSDATE JBSDATE_SIAGE,D11.JBEDATE JBEDATE_SIAGE,D11.TAIRYUT TAIRYUT_SIAGE, " +
        "       CASE WHEN H_IATMP1 > 0 OR H_IATIME1 > 0 THEN NVL(D12.JBSDATE, NVL(D13.JBSDATE,'')) ELSE null END JBSDATE_ANI, " +
        "       CASE WHEN H_IATMP1 > 0 OR H_IATIME1 > 0 THEN NVL(D12.JBEDATE, NVL(D13.JBEDATE,'')) ELSE null END JBEDATE_ANI, " +
        "       CASE WHEN DECODE(SUBSTR(D1.LTB,1,2),'H3',H_ATMP,H_STMP) > 0 OR DECODE(SUBSTR(D1.LTB,1,2),'H3',H_ATIME,H_STIME) > 0 THEN NVL(D14.JBSDATE, NVL(D15.JBSDATE,'')) ELSE null END JBSDATE_ANF, " +
        "       CASE WHEN DECODE(SUBSTR(D1.LTB,1,2),'H3',H_ATMP,H_STMP) > 0 OR DECODE(SUBSTR(D1.LTB,1,2),'H3',H_ATIME,H_STIME) > 0 THEN NVL(D14.JBEDATE, NVL(D15.JBEDATE,'')) ELSE null END JBEDATE_ANF, " +
// @S 2011/02/25 MOD
//        "       DECODE(SUBSTR(D1.LTB,1,1),'T',NVL(D14.JBSDATE, NVL(D15.JBSDATE,'')),'') JBSDATE_JIKO, DECODE(SUBSTR(D1.LTB,1,1),'T',NVL(D14.JBEDATE, NVL(D15.JBEDATE,'')),'') JBEDATE_JIKO, " +
//        "       NVL(D16.JBSDATE, '') JBSDATE_QF,NVL(D16.JBEDATE, '') JBEDATE_QF, " +
		"       DECODE(SUBSTR(D1.LTB,1,1),'T',NVL(D14.JBSDATE, ''),'') JBSDATE_JIKO, " +
		"       DECODE(SUBSTR(D1.LTB,1,1),'T',NVL(D14.JBEDATE, ''),'') JBEDATE_JIKO, " +
		"       DECODE(SUBSTR(D1.LTB,1,1),'T',NVL(D16.JBSDATE,NVL(D15.JBSDATE, '')),'') JBSDATE_QF, " +
		"       DECODE(SUBSTR(D1.LTB,1,1),'T',NVL(D16.JBEDATE,NVL(D15.JBEDATE, '')),'') JBEDATE_QF, " +
// @E 2011/02/25 MOD
		"       PITBI, ROUND(DECODE(NVL(SH_HZ*FX,0),0,0,DECODE(NVL(F1X,0),0,0,SH_HZ*FX/F1X)),1) F1Z, " +
        "       ROUND(NVL(JPDT4-JPDT1,0)*1440) PIT_TIME,JPATJ, " +
        "       ROUND(NVL(D10.JBEDATE-D10.JBSDATE,0)*1440) CM_TIME, " +
        "       ROUND(NVL(D11.JBEDATE-D11.JBSDATE,0)*1440) SIAGE_TIME " +
        "FROM " +
        "       DAT_R_JDF_COM D1, " +
        "       DAT_R_LOT_COM D2, " +
        "       DAT_R_LOT_COM_HOT D3, " +
        "       DAT_R_LOT_COM_CM D4, " +
        "       DAT_R_LOT_FIX D5, " +
        "       DAT_R_LOT_FIXEX D6, " +
        "       DAT_R_LOT_COM_JBOX D7, " +
        "       DAT_R_LOT_COM_JBOX D8, " +
        "       DAT_R_LOT_COM_JBOX D9, " +
        "       DAT_R_LOT_COM_JBOXEX D10, " +
        "       DAT_R_LOT_COM_JBOXEX D11, " +
        "       DAT_R_LOT_COM_JBOXEX D12, " +
        "       DAT_R_LOT_COM_JBOXEX D13, " +
        "       DAT_R_LOT_COM_JBOXEX D14, " +
        "       DAT_R_LOT_COM_JBOXEX D15, " +
        "       DAT_R_LOT_COM_JBOXEX D16, " +
        "       (SELECT LINKKEY, MAX(BOXNO) BOXNO FROM DAT_R_LOT_COM_JBOX WHERE JBSM = 'QF' GROUP BY LINKKEY, BOXNO) D17 " +
        "WHERE " +
        "	D1.LINKKEY = D2.LINKKEY " +
	"AND	D1.LINKKEY = D3.LINKKEY(+) " +
	"AND	D6.LINKKEY = D4.LINKKEY(+) " +
	"AND	D1.LINKKEY = D5.LINKKEY " +
	"AND	D1.LINKKEY = D6.LINKKEY " +
	"AND	D6.CMJ_BOXNO = D4.BOXNO(+) " +
	"AND	D6.LINKKEY = D7.LINKKEY(+) " +
	"AND	D6.HOT_BOXNO = D7.BOXNO(+) " +
	"AND	D6.LINKKEY = D8.LINKKEY(+) " +
	"AND	D6.CMJ_BOXNO = D8.BOXNO(+) " +
	"AND	D6.LINKKEY = D9.LINKKEY(+) " +
	"AND	D6.SIAGEJ_BOXNO = D9.BOXNO(+) " +
	"AND	D6.LINKKEY = D10.LINKKEY(+) " +
	"AND	D6.CMJ_BOXNO = D10.BOXNO(+) " +
	"AND	D6.LINKKEY = D11.LINKKEY(+) " +
	"AND	D6.SIAGEJ_BOXNO = D11.BOXNO(+) " +
	"AND	D6.LINKKEY = D12.LINKKEY(+) " +
	"AND	D6.ANI_JBOXNO = D12.BOXNO(+) " +
	"AND	D6.LINKKEY = D13.LINKKEY(+) " +
	"AND	D6.CAI_JBOXNO = D13.BOXNO(+) " +
	"AND	D6.LINKKEY = D14.LINKKEY(+) " +
	"AND	D6.ANF_JBOXNO = D14.BOXNO(+) " +
	"AND	D6.LINKKEY = D15.LINKKEY(+) " +
	"AND	D6.CAF_JBOXNO = D15.BOXNO(+) " +
	"AND	D1.LINKKEY = D17.LINKKEY(+) " +
	"AND	D17.LINKKEY = D16.LINKKEY(+) " +
	"AND	D17.BOXNO = D16.BOXNO(+) " +
	"AND	D1.JYOTAI = 2 " + //状態
	// 2014/09/25 追加 ▼
        "UNION ALL " +
	"SELECT " +
        "       '4' TBLORDER,D1.LINKKEY,SKIGO,SX,SY,SZ,STW,JUP,SW,H_MENS,MENW,PITJ,PIT,PRJ,ATJ,HOTJ,CROSS1,HBCOD,F1X,FX,HY,HZ, " +
        "       JCD202,H_IATMP1,H_IATIME1, " +
        "       DECODE(SUBSTR(D1.LTB,1,2),'H3',H_ATMP,H_STMP) H_TMP, " +
        "       DECODE(SUBSTR(D1.LTB,1,2),'H3',H_ATIME,H_STIME) H_TIME, " +
        "       H_STMP,H_ATMP,H_STIME,H_ATIME,YTMP,YTIME,ZTMP2,ZTIME2, " +
        "       JSWW,JSWDTM,JMW,JMDTM,JMX, " +
        "       JCW,JCDTM,JPJ,JPRB,JPRJ,JPDT,JPDT2,JPO2,JPDT3,JPO3,JPDT4,HPI2,HPI5,ROUND(NVL(HPI5-HPI2,0)*1440) HOT_TIME, " +
        "       D3.JHFTMP,D3.JHF1X,D3.JHF1TMP,D3.HOTPC,D5.HCID,D3.JBUW JBUW_HOT,D3.JBLW JBLW_HOT,D3.JBUB JBUB_HOT,D3.JBLB JBLB_HOT, " +
        "       D4.JBUW JBUW_CM,D4.JBLW JBLW_CM,NVL(D4.JBUB,0) JBUB_CM,NVL(D4.JBLB,0) JBLB_CM,D4.JBLN JBLN_CM, " +
        "       D7.JBEDTM JBEDTM_HOT,D7.JBEDTC JBEDTC_HOT, " +
        "       D8.JBEDTM JBEDTM_CM,D8.JBEDTC JBEDTC_CM, " +
        "       D9.JBFO JBFO_SIAGE,D9.JBBO JBBO_SIAGE,D1.SPX SPX_SIAGE,D1.SPNAV SPNAV_SIAGE,D9.JBEDTM JBEDTM_SIAGE,D9.JBEDTC JBEDTC_SIAGE, " +
        "       D10.JBSDATE JBSDATE_CM,D10.JBEDATE JBEDATE_CM,D10.TAIRYUT TAIRYUT_CM, " +
        "       D11.JBSDATE JBSDATE_SIAGE,D11.JBEDATE JBEDATE_SIAGE,D11.TAIRYUT TAIRYUT_SIAGE, " +
        "       CASE WHEN H_IATMP1 > 0 OR H_IATIME1 > 0 THEN NVL(D12.JBSDATE, NVL(D13.JBSDATE,'')) ELSE null END JBSDATE_ANI, " +
        "       CASE WHEN H_IATMP1 > 0 OR H_IATIME1 > 0 THEN NVL(D12.JBEDATE, NVL(D13.JBEDATE,'')) ELSE null END JBEDATE_ANI, " +
        "       CASE WHEN DECODE(SUBSTR(D1.LTB,1,2),'H3',H_ATMP,H_STMP) > 0 OR DECODE(SUBSTR(D1.LTB,1,2),'H3',H_ATIME,H_STIME) > 0 THEN NVL(D14.JBSDATE, NVL(D15.JBSDATE,'')) ELSE null END JBSDATE_ANF, " +
        "       CASE WHEN DECODE(SUBSTR(D1.LTB,1,2),'H3',H_ATMP,H_STMP) > 0 OR DECODE(SUBSTR(D1.LTB,1,2),'H3',H_ATIME,H_STIME) > 0 THEN NVL(D14.JBEDATE, NVL(D15.JBEDATE,'')) ELSE null END JBEDATE_ANF, " +
	"       DECODE(SUBSTR(D1.LTB,1,1),'T',NVL(D14.JBSDATE, ''),'') JBSDATE_JIKO, " +
	"       DECODE(SUBSTR(D1.LTB,1,1),'T',NVL(D14.JBEDATE, ''),'') JBEDATE_JIKO, " +
	"       DECODE(SUBSTR(D1.LTB,1,1),'T',NVL(D16.JBSDATE,NVL(D15.JBSDATE, '')),'') JBSDATE_QF, " +
	"       DECODE(SUBSTR(D1.LTB,1,1),'T',NVL(D16.JBEDATE,NVL(D15.JBEDATE, '')),'') JBEDATE_QF, " +
	"       PITBI, ROUND(DECODE(NVL(SH_HZ*FX,0),0,0,DECODE(NVL(F1X,0),0,0,SH_HZ*FX/F1X)),1) F1Z, " +
        "       ROUND(NVL(JPDT4-JPDT1,0)*1440) PIT_TIME,JPATJ, " +
        "       ROUND(NVL(D10.JBEDATE-D10.JBSDATE,0)*1440) CM_TIME, " +
        "       ROUND(NVL(D11.JBEDATE-D11.JBSDATE,0)*1440) SIAGE_TIME " +
        "FROM " +
        "       DAT_S_JDF_COM D1, " +
        "       DAT_S_LOT_COM D2, " +
        "       DAT_S_LOT_COM_HOT D3, " +
        "       DAT_S_LOT_COM_CM D4, " +
        "       DAT_S_LOT_FIX D5, " +
        "       DAT_S_LOT_FIXEX D6, " +
        "       DAT_S_LOT_COM_JBOX D7, " +
        "       DAT_S_LOT_COM_JBOX D8, " +
        "       DAT_S_LOT_COM_JBOX D9, " +
        "       DAT_S_LOT_COM_JBOXEX D10, " +
        "       DAT_S_LOT_COM_JBOXEX D11, " +
        "       DAT_S_LOT_COM_JBOXEX D12, " +
        "       DAT_S_LOT_COM_JBOXEX D13, " +
        "       DAT_S_LOT_COM_JBOXEX D14, " +
        "       DAT_S_LOT_COM_JBOXEX D15, " +
        "       DAT_S_LOT_COM_JBOXEX D16, " +
        "       (SELECT LINKKEY, MAX(BOXNO) BOXNO, TOROKUDATE FROM DAT_S_LOT_COM_JBOX WHERE JBSM = 'QF' GROUP BY LINKKEY, BOXNO, TOROKUDATE) D17 " +
        "WHERE " +
        "	D1.LINKKEY = D2.LINKKEY " +
	"AND	D1.TOROKUDATE = D2.TOROKUDATE(+) " +
	"AND	D1.LINKKEY = D3.LINKKEY(+) " +
	"AND	D1.TOROKUDATE = D3.TOROKUDATE(+) " +
	"AND	D6.LINKKEY = D4.LINKKEY(+) " +
	"AND	D6.TOROKUDATE = D4.TOROKUDATE(+) " +
	"AND	D1.LINKKEY = D5.LINKKEY " +
	"AND	D1.TOROKUDATE = D5.TOROKUDATE(+) " +
	"AND	D1.LINKKEY = D6.LINKKEY " +
	"AND	D1.TOROKUDATE = D6.TOROKUDATE(+) " +
	"AND	D6.CMJ_BOXNO = D4.BOXNO(+) " +
	"AND	D6.TOROKUDATE = D4.TOROKUDATE(+) " +
	"AND	D6.LINKKEY = D7.LINKKEY(+) " +
	"AND	D6.HOT_BOXNO = D7.BOXNO(+) " +
	"AND	D6.TOROKUDATE = D7.TOROKUDATE(+) " +
	"AND	D6.LINKKEY = D8.LINKKEY(+) " +
	"AND	D6.CMJ_BOXNO = D8.BOXNO(+) " +
	"AND	D6.TOROKUDATE = D8.TOROKUDATE(+) " +
	"AND	D6.LINKKEY = D9.LINKKEY(+) " +
	"AND	D6.SIAGEJ_BOXNO = D9.BOXNO(+) " +
	"AND	D6.TOROKUDATE = D9.TOROKUDATE(+) " +
	"AND	D6.LINKKEY = D10.LINKKEY(+) " +
	"AND	D6.CMJ_BOXNO = D10.BOXNO(+) " +
	"AND	D6.TOROKUDATE = D10.TOROKUDATE(+) " +
	"AND	D6.LINKKEY = D11.LINKKEY(+) " +
	"AND	D6.SIAGEJ_BOXNO = D11.BOXNO(+) " +
	"AND	D6.TOROKUDATE = D11.TOROKUDATE(+) " +
	"AND	D6.LINKKEY = D12.LINKKEY(+) " +
	"AND	D6.ANI_JBOXNO = D12.BOXNO(+) " +
	"AND	D6.TOROKUDATE = D12.TOROKUDATE(+) " +
	"AND	D6.LINKKEY = D13.LINKKEY(+) " +
	"AND	D6.CAI_JBOXNO = D13.BOXNO(+) " +
	"AND	D6.TOROKUDATE = D13.TOROKUDATE(+) " +
	"AND	D6.LINKKEY = D14.LINKKEY(+) " +
	"AND	D6.ANF_JBOXNO = D14.BOXNO(+) " +
	"AND	D6.TOROKUDATE = D14.TOROKUDATE(+) " +
	"AND	D6.LINKKEY = D15.LINKKEY(+) " +
	"AND	D6.CAF_JBOXNO = D15.BOXNO(+) " +
	"AND	D6.TOROKUDATE = D15.TOROKUDATE(+) " +
	"AND	D1.LINKKEY = D17.LINKKEY(+) " +
	"AND	D1.TOROKUDATE = D17.TOROKUDATE(+) " +
	"AND	D17.LINKKEY = D16.LINKKEY(+) " +
	"AND	D17.BOXNO = D16.BOXNO(+) " +
	"AND	D17.TOROKUDATE = D16.TOROKUDATE(+) " +
	// 2014/09/25 追加 ▲

	// 2016/04/24 追加 ▼
        "UNION ALL " +
	"SELECT " +
        "       '5' TBLORDER,D1.LINKKEY,SKIGO,SX,SY,SZ,STW,JUP,SW,H_MENS,MENW,PITJ,PIT,PRJ,ATJ,HOTJ,CROSS1,HBCOD,F1X,FX,HY,HZ, " +
        "       JCD202,H_IATMP1,H_IATIME1, " +
        "       DECODE(SUBSTR(D1.LTB,1,2),'H3',H_ATMP,H_STMP) H_TMP, " +
        "       DECODE(SUBSTR(D1.LTB,1,2),'H3',H_ATIME,H_STIME) H_TIME, " +
        "       H_STMP,H_ATMP,H_STIME,H_ATIME,YTMP,YTIME,ZTMP2,ZTIME2, " +
        "       JSWW,JSWDTM,JMW,JMDTM,JMX, " +
        "       JCW,JCDTM,JPJ,JPRB,JPRJ,JPDT,JPDT2,JPO2,JPDT3,JPO3,JPDT4,HPI2,HPI5,ROUND(NVL(HPI5-HPI2,0)*1440) HOT_TIME, " +
        "       D3.JHFTMP,D3.JHF1X,D3.JHF1TMP,D3.HOTPC,D5.HCID,D3.JBUW JBUW_HOT,D3.JBLW JBLW_HOT,D3.JBUB JBUB_HOT,D3.JBLB JBLB_HOT, " +
        "       D4.JBUW JBUW_CM,D4.JBLW JBLW_CM,NVL(D4.JBUB,0) JBUB_CM,NVL(D4.JBLB,0) JBLB_CM,D4.JBLN JBLN_CM, " +
        "       D7.JBEDTM JBEDTM_HOT,D7.JBEDTC JBEDTC_HOT, " +
        "       D8.JBEDTM JBEDTM_CM,D8.JBEDTC JBEDTC_CM, " +
        "       D9.JBFO JBFO_SIAGE,D9.JBBO JBBO_SIAGE,D1.SPX SPX_SIAGE,D1.SPNAV SPNAV_SIAGE,D9.JBEDTM JBEDTM_SIAGE,D9.JBEDTC JBEDTC_SIAGE, " +
        "       D10.JBSDATE JBSDATE_CM,D10.JBEDATE JBEDATE_CM,D10.TAIRYUT TAIRYUT_CM, " +
        "       D11.JBSDATE JBSDATE_SIAGE,D11.JBEDATE JBEDATE_SIAGE,D11.TAIRYUT TAIRYUT_SIAGE, " +
        "       CASE WHEN H_IATMP1 > 0 OR H_IATIME1 > 0 THEN NVL(D12.JBSDATE, NVL(D13.JBSDATE,'')) ELSE null END JBSDATE_ANI, " +
        "       CASE WHEN H_IATMP1 > 0 OR H_IATIME1 > 0 THEN NVL(D12.JBEDATE, NVL(D13.JBEDATE,'')) ELSE null END JBEDATE_ANI, " +
        "       CASE WHEN DECODE(SUBSTR(D1.LTB,1,2),'H3',H_ATMP,H_STMP) > 0 OR DECODE(SUBSTR(D1.LTB,1,2),'H3',H_ATIME,H_STIME) > 0 THEN NVL(D14.JBSDATE, NVL(D15.JBSDATE,'')) ELSE null END JBSDATE_ANF, " +
        "       CASE WHEN DECODE(SUBSTR(D1.LTB,1,2),'H3',H_ATMP,H_STMP) > 0 OR DECODE(SUBSTR(D1.LTB,1,2),'H3',H_ATIME,H_STIME) > 0 THEN NVL(D14.JBEDATE, NVL(D15.JBEDATE,'')) ELSE null END JBEDATE_ANF, " +
	"       DECODE(SUBSTR(D1.LTB,1,1),'T',NVL(D14.JBSDATE, ''),'') JBSDATE_JIKO, " +
	"       DECODE(SUBSTR(D1.LTB,1,1),'T',NVL(D14.JBEDATE, ''),'') JBEDATE_JIKO, " +
	"       DECODE(SUBSTR(D1.LTB,1,1),'T',NVL(D16.JBSDATE,NVL(D15.JBSDATE, '')),'') JBSDATE_QF, " +
	"       DECODE(SUBSTR(D1.LTB,1,1),'T',NVL(D16.JBEDATE,NVL(D15.JBEDATE, '')),'') JBEDATE_QF, " +
	"       PITBI, ROUND(DECODE(NVL(SH_HZ*FX,0),0,0,DECODE(NVL(F1X,0),0,0,SH_HZ*FX/F1X)),1) F1Z, " +
        "       ROUND(NVL(JPDT4-JPDT1,0)*1440) PIT_TIME,JPATJ, " +
        "       ROUND(NVL(D10.JBEDATE-D10.JBSDATE,0)*1440) CM_TIME, " +
        "       ROUND(NVL(D11.JBEDATE-D11.JBSDATE,0)*1440) SIAGE_TIME " +
        "FROM " +
        "       DAT_L_JDF_COM D1, " +
        "       DAT_L_LOT_COM D2, " +
        "       DAT_L_LOT_COM_HOT D3, " +
        "       DAT_L_LOT_COM_CM D4, " +
        "       DAT_L_LOT_FIX D5, " +
        "       DAT_L_LOT_FIXEX D6, " +
        "       DAT_L_LOT_COM_JBOX D7, " +
        "       DAT_L_LOT_COM_JBOX D8, " +
        "       DAT_L_LOT_COM_JBOX D9, " +
        "       DAT_L_LOT_COM_JBOXEX D10, " +
        "       DAT_L_LOT_COM_JBOXEX D11, " +
        "       DAT_L_LOT_COM_JBOXEX D12, " +
        "       DAT_L_LOT_COM_JBOXEX D13, " +
        "       DAT_L_LOT_COM_JBOXEX D14, " +
        "       DAT_L_LOT_COM_JBOXEX D15, " +
        "       DAT_L_LOT_COM_JBOXEX D16, " +
        "       (SELECT LINKKEY, MAX(BOXNO) BOXNO, TOROKUDATE FROM DAT_L_LOT_COM_JBOX WHERE JBSM = 'QF' GROUP BY LINKKEY, BOXNO, TOROKUDATE) D17 " +
        "WHERE " +
        "	D1.LINKKEY = D2.LINKKEY " +
	"AND	TRUNC(D1.TOROKUDATE) = TRUNC(D2.TOROKUDATE(+)) " +
	"AND	D1.LINKKEY = D3.LINKKEY(+) " +
	"AND	TRUNC(D1.TOROKUDATE) = TRUNC(D3.TOROKUDATE(+)) " +
	"AND	D6.LINKKEY = D4.LINKKEY(+) " +
	"AND	TRUNC(D6.TOROKUDATE) = TRUNC(D4.TOROKUDATE(+)) " +
	"AND	D1.LINKKEY = D5.LINKKEY " +
	"AND	TRUNC(D1.TOROKUDATE) = TRUNC(D5.TOROKUDATE(+)) " +
	"AND	D1.LINKKEY = D6.LINKKEY(+) " +
	"AND	TRUNC(D1.TOROKUDATE) = TRUNC(D6.TOROKUDATE(+)) " +
	"AND	D6.CMJ_BOXNO = D4.BOXNO(+) " +
	"AND	TRUNC(D6.TOROKUDATE) = TRUNC(D4.TOROKUDATE(+)) " +
	"AND	D6.LINKKEY = D7.LINKKEY(+) " +
	"AND	D6.HOT_BOXNO = D7.BOXNO(+) " +
	"AND	TRUNC(D6.TOROKUDATE) = TRUNC(D7.TOROKUDATE(+)) " +
	"AND	D6.LINKKEY = D8.LINKKEY(+) " +
	"AND	D6.CMJ_BOXNO = D8.BOXNO(+) " +
	"AND	TRUNC(D6.TOROKUDATE) = TRUNC(D8.TOROKUDATE(+)) " +
	"AND	D6.LINKKEY = D9.LINKKEY(+) " +
	"AND	D6.SIAGEJ_BOXNO = D9.BOXNO(+) " +
	"AND	TRUNC(D6.TOROKUDATE) = TRUNC(D9.TOROKUDATE(+)) " +
	"AND	D6.LINKKEY = D10.LINKKEY(+) " +
	"AND	D6.CMJ_BOXNO = D10.BOXNO(+) " +
	"AND	TRUNC(D6.TOROKUDATE) = TRUNC(D10.TOROKUDATE(+)) " +
	"AND	D6.LINKKEY = D11.LINKKEY(+) " +
	"AND	D6.SIAGEJ_BOXNO = D11.BOXNO(+) " +
	"AND	TRUNC(D6.TOROKUDATE) = TRUNC(D11.TOROKUDATE(+)) " +
	"AND	D6.LINKKEY = D12.LINKKEY(+) " +
	"AND	D6.ANI_JBOXNO = D12.BOXNO(+) " +
	"AND	TRUNC(D6.TOROKUDATE) = TRUNC(D12.TOROKUDATE(+)) " +
	"AND	D6.LINKKEY = D13.LINKKEY(+) " +
	"AND	D6.CAI_JBOXNO = D13.BOXNO(+) " +
	"AND	TRUNC(D6.TOROKUDATE) = TRUNC(D13.TOROKUDATE(+)) " +
	"AND	D6.LINKKEY = D14.LINKKEY(+) " +
	"AND	D6.ANF_JBOXNO = D14.BOXNO(+) " +
	"AND	TRUNC(D6.TOROKUDATE) = TRUNC(D14.TOROKUDATE(+)) " +
	"AND	D6.LINKKEY = D15.LINKKEY(+) " +
	"AND	D6.CAF_JBOXNO = D15.BOXNO(+) " +
	"AND	TRUNC(D6.TOROKUDATE) = TRUNC(D15.TOROKUDATE(+)) " +
	"AND	D1.LINKKEY = D17.LINKKEY(+) " +
	"AND	TRUNC(D1.TOROKUDATE) = TRUNC(D17.TOROKUDATE(+)) " +
	"AND	D17.LINKKEY = D16.LINKKEY(+) " +
	"AND	D17.BOXNO = D16.BOXNO(+) " +
	"AND	TRUNC(D17.TOROKUDATE) = TRUNC(D16.TOROKUDATE(+)) " +
	// 2016/04/24 追加 ▲

        ") " +
        "WHERE " +
	"       LINKKEY = ? " +
    "      AND TBLORDER = ? "
	;


    //ｽﾀｯﾌ版ﾛｯﾄ情報SQL　試験情報
    private static final String STAFF_TEST_QUERY =
        "SELECT * FROM ( " +
	"SELECT " +
	"	 '1' TBLORDER, D1.LINKKEY,QCCOD01,QCCOD02,QCCOD03,QCCOD04,QCCOD05,QCCOD06,QCCOD07,QCCOD08,QCCOD09, " +
        "	QCCOD10,QCCOD11,QCCOD15,KHARI3,KHARI1,KHARI2,KNOBI3,KNOBI1,KNOBI2,KTAI3,KTAI1,KTAI2, " +
        "       KOSAXS01,KOSAXS02,KOSAXS03,KOSAXS04,KOSAXS05,KOSAXS06,KOSAYS01,KOSAYS02,KOSAYS03, " +
        "	KOSAYS04,KOSAYS05,KOSAYS06,KOSAZS01,KOSAZS02,KOSAZS03,KOSAZS04,KOSAZS05,KOSAZS06, " +
        "       KHAX,KHIN,KHAV,J_KHAX,J_KHIN,J_KHAV,KNAX,KNIN,KNAV,J_KNAX,J_KNIN,J_KNAV,KTAX,KTIN, " +
        "       KTAV,J_KTAX,J_KTIN,J_KTAV,KXAX,KXIN,KXAV,KYAX,KYIN,KYAV,KZAX,KZIN,KZAV, " +
        "       TRUNC(KEI_CU,6) KEI_CU, " +
        "       TRUNC(KEI_SI,6) KEI_SI, " +
        "       TRUNC(KEI_FE,6) KEI_FE, " +
        "       TRUNC(KEI_MN,6) KEI_MN, " +
        "       TRUNC(KEI_MG,6) KEI_MG, " +
        "       TRUNC(KEI_ZN,6) KEI_ZN, " +
        "       TRUNC(KEI_CR,6) KEI_CR, " +
        "       TRUNC(KEI_TI,6) KEI_TI, " +
        "       TRUNC(KEI_B,6) KEI_B, " +
        "       TRUNC(KEI_V,6) KEI_V, " +
        "       TRUNC(KEI_GA,6) KEI_GA, " +
        "       TRUNC(KEI_ZR,6) KEI_ZR, " +
        "       TRUNC(KEI_BE,6) KEI_BE, " +
        "       TRUNC(KEI_NA,6) KEI_NA, " +
        "       TRUNC(KEI_CA,6) KEI_CA, " +
        "       TRUNC(KEI_NI,6) KEI_NI, " +
        "       TRUNC(KEI_PB,6) KEI_PB, " +
        "       TRUNC(KEI_BI,6) KEI_BI, " +
        "       TRUNC(KEI_CD,6) KEI_CD, " +
        "       TRUNC(KEI_SN,6) KEI_SN, " +
        "       TRUNC(KEI_LI,6) KEI_LI, " +
        "       TRUNC(KEI_MO,6) KEI_MO, " +
        "       TRUNC(KEI_AG,6) KEI_AG, " +
        "       TRUNC(KEI_P,6) KEI_P, " +
        "       TRUNC(KEI_OTHER,6) KEI_OTHER, " +
        "       TRUNC(KEI_AL,6) KEI_AL " +
        "FROM " +
	"	DAT_JDF_COM D1, " +
	"	DAT_LOT_COM_KINS D2, " +
	"	DAT_SLB_COM D3, " +
	"	VIEW_TSB D4 " +
	"WHERE " +
	"	D1.LINKKEY = D2.LINKKEY " +
	"AND	D1.CYNO = D3.CN(+) " +
	"AND	D1.CYUZO_YY = D3.CYUZO_YY(+) " + // 追加 2014/09/24
	"AND 	D3.CNB = D4.CYUBAN(+) " +
	"AND	D3.CYUZO_YY = D4.CYUZO_YY(+) " + // 追加 2014/09/24
	"AND	D1.JYOTAI = 1 " +
        "UNION ALL " +

	/* 2017/10/27
	"SELECT " +
	"	D1.LINKKEY,QCCOD01,QCCOD02,QCCOD03,QCCOD04,QCCOD05,QCCOD06,QCCOD07,QCCOD08,QCCOD09, " +
        "	QCCOD10,QCCOD11,QCCOD15,KHARI3,KHARI1,KHARI2,KNOBI3,KNOBI1,KNOBI2,KTAI3,KTAI1,KTAI2, " +
        "       KOSAXS01,KOSAXS02,KOSAXS03,KOSAXS04,KOSAXS05,KOSAXS06,KOSAYS01,KOSAYS02,KOSAYS03, " +
        "	KOSAYS04,KOSAYS05,KOSAYS06,KOSAZS01,KOSAZS02,KOSAZS03,KOSAZS04,KOSAZS05,KOSAZS06, " +
        "       KHAX,KHIN,KHAV,J_KHAX,J_KHIN,J_KHAV,KNAX,KNIN,KNAV,J_KNAX,J_KNIN,J_KNAV,KTAX,KTIN, " +
        "       KTAV,J_KTAX,J_KTIN,J_KTAV,KXAX,KXIN,KXAV,KYAX,KYIN,KYAV,KZAX,KZIN,KZAV, " +
        "       TRUNC(KEI_CU,6) KEI_CU, " +
        "       TRUNC(KEI_SI,6) KEI_SI, " +
        "       TRUNC(KEI_FE,6) KEI_FE, " +
        "       TRUNC(KEI_MN,6) KEI_MN, " +
        "       TRUNC(KEI_MG,6) KEI_MG, " +
        "       TRUNC(KEI_ZN,6) KEI_ZN, " +
        "       TRUNC(KEI_CR,6) KEI_CR, " +
        "       TRUNC(KEI_TI,6) KEI_TI, " +
        "       TRUNC(KEI_B,6) KEI_B, " +
        "       TRUNC(KEI_V,6) KEI_V, " +
        "       TRUNC(KEI_GA,6) KEI_GA, " +
        "       TRUNC(KEI_ZR,6) KEI_ZR, " +
        "       TRUNC(KEI_BE,6) KEI_BE, " +
        "       TRUNC(KEI_NA,6) KEI_NA, " +
        "       TRUNC(KEI_CA,6) KEI_CA, " +
        "       TRUNC(KEI_NI,6) KEI_NI, " +
        "       TRUNC(KEI_PB,6) KEI_PB, " +
        "       TRUNC(KEI_BI,6) KEI_BI, " +
        "       TRUNC(KEI_CD,6) KEI_CD, " +
        "       TRUNC(KEI_SN,6) KEI_SN, " +
        "       TRUNC(KEI_LI,6) KEI_LI, " +
        "       TRUNC(KEI_MO,6) KEI_MO, " +
        "       TRUNC(KEI_AG,6) KEI_AG, " +
        "       TRUNC(KEI_P,6) KEI_P, " +
        "       TRUNC(KEI_OTHER,6) KEI_OTHER, " +
        "       TRUNC(KEI_AL,6) KEI_AL " +
        "FROM " +
	"	DAT_JDF_COM2 D1, " +
	//"	DAT_LOT_COM_KINS D2, " +
	"	DAT_SLB_COM2 D3 " +
	//"	VIEW_TSB D4 " +
	"WHERE " +
	"	D1.LINKKEY = D2.LINKKEY " +
	"AND	D1.CYNO = D3.CN(+) " +
	"AND	D1.CYUZO_YY = D3.CYUZO_YY(+) " +
	"AND 	D3.CNB = D4.CYUBAN(+) " +
	"AND	D3.CYUZO_YY = D4.CYUZO_YY(+) " +
	"AND	D1.JYOTAI = 1 " +
        "UNION ALL " +
	*/

	"SELECT " +
	"	 '2' TBLORDER, LINKKEY,QCCOD01,QCCOD02,QCCOD03,QCCOD04,QCCOD05,QCCOD06,QCCOD07,QCCOD08,QCCOD09,QCCOD10,QCCOD11,QCCOD15, " +
        "	KHARI3,KHARI1,KHARI2,KNOBI3,KNOBI1,KNOBI2,KTAI3,KTAI1,KTAI2, " +
        "       KOSAXS01,KOSAXS02,KOSAXS03,KOSAXS04,KOSAXS05,KOSAXS06,KOSAYS01,KOSAYS02,KOSAYS03, " +
        "	KOSAYS04,KOSAYS05,KOSAYS06,KOSAZS01,KOSAZS02,KOSAZS03,KOSAZS04,KOSAZS05,KOSAZS06, " +
        "       TO_NUMBER(NULL) KHAX,TO_NUMBER(NULL) KHIN,TO_NUMBER(NULL) KHAV,TO_NUMBER(NULL) J_KHAX,TO_NUMBER(NULL) J_KHIN,TO_NUMBER(NULL) J_KHAV, " +
        "	TO_NUMBER(NULL) KNAX,TO_NUMBER(NULL) KNIN,TO_NUMBER(NULL) KNAV,TO_NUMBER(NULL) J_KNAX,TO_NUMBER(NULL) J_KNIN,TO_NUMBER(NULL) J_KNAV, " +
        "	TO_NUMBER(NULL) KTAX,TO_NUMBER(NULL) KTIN,TO_NUMBER(NULL) KTAV,TO_NUMBER(NULL) J_KTAX,TO_NUMBER(NULL) J_KTIN,TO_NUMBER(NULL) J_KTAV, " +
        "	TO_NUMBER(NULL) KXAX,TO_NUMBER(NULL) KXIN,TO_NUMBER(NULL) KXAV, " +
        "	TO_NUMBER(NULL) KYAX,TO_NUMBER(NULL) KYIN,TO_NUMBER(NULL) KYAV, " +
        "	TO_NUMBER(NULL) KZAX,TO_NUMBER(NULL) KZIN,TO_NUMBER(NULL) KZAV, " +
        "       TO_NUMBER(NULL) KEI_CU, " +
        "       TO_NUMBER(NULL) KEI_SI, " +
        "       TO_NUMBER(NULL) KEI_FE, " +
        "       TO_NUMBER(NULL) KEI_MN, " +
        "       TO_NUMBER(NULL) KEI_MG, " +
        "       TO_NUMBER(NULL) KEI_ZN, " +
        "       TO_NUMBER(NULL) KEI_CR, " +
        "       TO_NUMBER(NULL) KEI_TI, " +
        "       TO_NUMBER(NULL) KEI_B, " +
        "       TO_NUMBER(NULL) KEI_V, " +
        "       TO_NUMBER(NULL) KEI_GA, " +
        "       TO_NUMBER(NULL) KEI_ZR, " +
        "       TO_NUMBER(NULL) KEI_BE, " +
        "       TO_NUMBER(NULL) KEI_NA, " +
        "       TO_NUMBER(NULL) KEI_CA, " +
        "       TO_NUMBER(NULL) KEI_NI, " +
        "       TO_NUMBER(NULL) KEI_PB, " +
        "       TO_NUMBER(NULL) KEI_BI, " +
        "       TO_NUMBER(NULL) KEI_CD, " +
        "       TO_NUMBER(NULL) KEI_SN, " +
        "       TO_NUMBER(NULL) KEI_LI, " +
        "       TO_NUMBER(NULL) KEI_MO, " +
        "       TO_NUMBER(NULL) KEI_AG, " +
        "       TO_NUMBER(NULL) KEI_P, " +
        "       TO_NUMBER(NULL) KEI_OTHER, " +
        "       TO_NUMBER(NULL) KEI_AL " +
        "FROM " +
	"	DAT_JDF_COM2 " +
        "WHERE " +
        "	EXISTS (SELECT * FROM DAT_JDF_COM2 WHERE LINKKEY = ? ) " +
        "AND	LINKKEY = ?  " +
        /*2020/09/14 削除
        "UNION ALL " +
	"SELECT " +
	"	D1.LINKKEY,QCCOD01,QCCOD02,QCCOD03,QCCOD04,QCCOD05,QCCOD06,QCCOD07,QCCOD08,QCCOD09, " +
        "	QCCOD10,QCCOD11,QCCOD15,KHARI3,KHARI1,KHARI2,KNOBI3,KNOBI1,KNOBI2,KTAI3,KTAI1,KTAI2, " +
        "       KOSAXS01,KOSAXS02,KOSAXS03,KOSAXS04,KOSAXS05,KOSAXS06,KOSAYS01,KOSAYS02,KOSAYS03, " +
        "	KOSAYS04,KOSAYS05,KOSAYS06,KOSAZS01,KOSAZS02,KOSAZS03,KOSAZS04,KOSAZS05,KOSAZS06, " +
        "       KHAX,KHIN,KHAV,J_KHAX,J_KHIN,J_KHAV,KNAX,KNIN,KNAV,J_KNAX,J_KNIN,J_KNAV,KTAX,KTIN, " +
        "       KTAV,J_KTAX,J_KTIN,J_KTAV,KXAX,KXIN,KXAV,KYAX,KYIN,KYAV,KZAX,KZIN,KZAV, " +
        "       TRUNC(KEI_CU,6) KEI_CU, " +
        "       TRUNC(KEI_SI,6) KEI_SI, " +
        "       TRUNC(KEI_FE,6) KEI_FE, " +
        "       TRUNC(KEI_MN,6) KEI_MN, " +
        "       TRUNC(KEI_MG,6) KEI_MG, " +
        "       TRUNC(KEI_ZN,6) KEI_ZN, " +
        "       TRUNC(KEI_CR,6) KEI_CR, " +
        "       TRUNC(KEI_TI,6) KEI_TI, " +
        "       TRUNC(KEI_B,6) KEI_B, " +
        "       TRUNC(KEI_V,6) KEI_V, " +
        "       TRUNC(KEI_GA,6) KEI_GA, " +
        "       TRUNC(KEI_ZR,6) KEI_ZR, " +
        "       TRUNC(KEI_BE,6) KEI_BE, " +
        "       TRUNC(KEI_NA,6) KEI_NA, " +
        "       TRUNC(KEI_CA,6) KEI_CA, " +
        "       TRUNC(KEI_NI,6) KEI_NI, " +
        "       TRUNC(KEI_PB,6) KEI_PB, " +
        "       TRUNC(KEI_BI,6) KEI_BI, " +
        "       TRUNC(KEI_CD,6) KEI_CD, " +
        "       TRUNC(KEI_SN,6) KEI_SN, " +
        "       TRUNC(KEI_LI,6) KEI_LI, " +
        "       TRUNC(KEI_MO,6) KEI_MO, " +
        "       TRUNC(KEI_AG,6) KEI_AG, " +
        "       TRUNC(KEI_P,6) KEI_P, " +
        "       TRUNC(KEI_OTHER,6) KEI_OTHER, " +
        "       TRUNC(KEI_AL,6) KEI_AL " +
        "FROM " +
	"	DAT_JDF_COM D1, " +
	"	DAT_LOT_COM_KINS D2, " +
	"	DAT_SLB_COM D3, " +
	"	VIEW_TSB D4 " +
	"WHERE " +
	"	D1.LINKKEY = D2.LINKKEY " +
	"AND	D1.CYNO = D3.CN(+) " +
	"AND	D1.CYUZO_YY = D3.CYUZO_YY(+) " + // 追加 2014/9/24
	"AND 	D3.CNB = D4.CYUBAN(+) " +
	"AND 	D3.CYUZO_YY = D4.CYUZO_YY(+) " + // 追加 2014/9/24
	"AND 	D1.JYOTAI = 1 " +
	*/
        "UNION ALL " +
	"SELECT " +
	"	 '3' TBLORDER, D1.LINKKEY,QCCOD01,QCCOD02,QCCOD03,QCCOD04,QCCOD05,QCCOD06,QCCOD07,QCCOD08,QCCOD09, " +
        "	QCCOD10,QCCOD11,QCCOD15,KHARI3,KHARI1,KHARI2,KNOBI3,KNOBI1,KNOBI2,KTAI3,KTAI1,KTAI2, " +
        "       KOSAXS01,KOSAXS02,KOSAXS03,KOSAXS04,KOSAXS05,KOSAXS06,KOSAYS01,KOSAYS02,KOSAYS03, " +
        "	KOSAYS04,KOSAYS05,KOSAYS06,KOSAZS01,KOSAZS02,KOSAZS03,KOSAZS04,KOSAZS05,KOSAZS06, " +
        "       KHAX,KHIN,KHAV,J_KHAX,J_KHIN,J_KHAV,KNAX,KNIN,KNAV,J_KNAX,J_KNIN,J_KNAV,KTAX,KTIN, " +
        "       KTAV,J_KTAX,J_KTIN,J_KTAV,KXAX,KXIN,KXAV,KYAX,KYIN,KYAV,KZAX,KZIN,KZAV, " +
        "       TRUNC(KEI_CU,6) KEI_CU, " +
        "       TRUNC(KEI_SI,6) KEI_SI, " +
        "       TRUNC(KEI_FE,6) KEI_FE, " +
        "       TRUNC(KEI_MN,6) KEI_MN, " +
        "       TRUNC(KEI_MG,6) KEI_MG, " +
        "       TRUNC(KEI_ZN,6) KEI_ZN, " +
        "       TRUNC(KEI_CR,6) KEI_CR, " +
        "       TRUNC(KEI_TI,6) KEI_TI, " +
        "       TRUNC(KEI_B,6) KEI_B, " +
        "       TRUNC(KEI_V,6) KEI_V, " +
        "       TRUNC(KEI_GA,6) KEI_GA, " +
        "       TRUNC(KEI_ZR,6) KEI_ZR, " +
        "       TRUNC(KEI_BE,6) KEI_BE, " +
        "       TRUNC(KEI_NA,6) KEI_NA, " +
        "       TRUNC(KEI_CA,6) KEI_CA, " +
        "       TRUNC(KEI_NI,6) KEI_NI, " +
        "       TRUNC(KEI_PB,6) KEI_PB, " +
        "       TRUNC(KEI_BI,6) KEI_BI, " +
        "       TRUNC(KEI_CD,6) KEI_CD, " +
        "       TRUNC(KEI_SN,6) KEI_SN, " +
        "       TRUNC(KEI_LI,6) KEI_LI, " +
        "       TRUNC(KEI_MO,6) KEI_MO, " +
        "       TRUNC(KEI_AG,6) KEI_AG, " +
        "       TRUNC(KEI_P,6) KEI_P, " +
        "       TRUNC(KEI_OTHER,6) KEI_OTHER, " +
        "       TRUNC(KEI_AL,6) KEI_AL " +
        "FROM " +
	"	DAT_R_JDF_COM D1, " +
	"	DAT_R_LOT_COM_KINS D2, " +
	"	DAT_R_SLB_COM D3, " +
	"	VIEW_TSB D4 " +
	"WHERE " +
	"	D1.LINKKEY = D2.LINKKEY " +
	"AND	D1.CYNO = D3.CN(+) " +
	"AND	D1.CYUZO_YY = D3.CYUZO_YY(+) " + // 追加 2014/9/24
	"AND 	D3.CNB = D4.CYUBAN(+) " +
	"AND 	D3.CYUZO_YY = D4.CYUZO_YY(+) " + // 追加 2014/9/24
	"AND 	D1.JYOTAI = 2 " +
	// 2014/09/25 追加 ▼
        "UNION ALL " +
	"SELECT " +
	"	 '4' TBLORDER, D1.LINKKEY,QCCOD01,QCCOD02,QCCOD03,QCCOD04,QCCOD05,QCCOD06,QCCOD07,QCCOD08,QCCOD09, " +
        "	QCCOD10,QCCOD11,QCCOD15,KHARI3,KHARI1,KHARI2,KNOBI3,KNOBI1,KNOBI2,KTAI3,KTAI1,KTAI2, " +
        "       KOSAXS01,KOSAXS02,KOSAXS03,KOSAXS04,KOSAXS05,KOSAXS06,KOSAYS01,KOSAYS02,KOSAYS03, " +
        "	KOSAYS04,KOSAYS05,KOSAYS06,KOSAZS01,KOSAZS02,KOSAZS03,KOSAZS04,KOSAZS05,KOSAZS06, " +
        "       KHAX,KHIN,KHAV,J_KHAX,J_KHIN,J_KHAV,KNAX,KNIN,KNAV,J_KNAX,J_KNIN,J_KNAV,KTAX,KTIN, " +
        "       KTAV,J_KTAX,J_KTIN,J_KTAV,KXAX,KXIN,KXAV,KYAX,KYIN,KYAV,KZAX,KZIN,KZAV, " +
        "       TRUNC(KEI_CU,6) KEI_CU, " +
        "       TRUNC(KEI_SI,6) KEI_SI, " +
        "       TRUNC(KEI_FE,6) KEI_FE, " +
        "       TRUNC(KEI_MN,6) KEI_MN, " +
        "       TRUNC(KEI_MG,6) KEI_MG, " +
        "       TRUNC(KEI_ZN,6) KEI_ZN, " +
        "       TRUNC(KEI_CR,6) KEI_CR, " +
        "       TRUNC(KEI_TI,6) KEI_TI, " +
        "       TRUNC(KEI_B,6) KEI_B, " +
        "       TRUNC(KEI_V,6) KEI_V, " +
        "       TRUNC(KEI_GA,6) KEI_GA, " +
        "       TRUNC(KEI_ZR,6) KEI_ZR, " +
        "       TRUNC(KEI_BE,6) KEI_BE, " +
        "       TRUNC(KEI_NA,6) KEI_NA, " +
        "       TRUNC(KEI_CA,6) KEI_CA, " +
        "       TRUNC(KEI_NI,6) KEI_NI, " +
        "       TRUNC(KEI_PB,6) KEI_PB, " +
        "       TRUNC(KEI_BI,6) KEI_BI, " +
        "       TRUNC(KEI_CD,6) KEI_CD, " +
        "       TRUNC(KEI_SN,6) KEI_SN, " +
        "       TRUNC(KEI_LI,6) KEI_LI, " +
        "       TRUNC(KEI_MO,6) KEI_MO, " +
        "       TRUNC(KEI_AG,6) KEI_AG, " +
        "       TRUNC(KEI_P,6) KEI_P, " +
        "       TRUNC(KEI_OTHER,6) KEI_OTHER, " +
        "       TRUNC(KEI_AL,6) KEI_AL " +
        "FROM " +
	"	DAT_S_JDF_COM D1, " +
	"	DAT_S_LOT_COM_KINS D2, " +
	"	DAT_S_SLB_COM D3, " +
	"	VIEW_TSB D4 " +
	"WHERE " +
	"	D1.LINKKEY = D2.LINKKEY " +
	"AND	D1.TOROKUDATE = D2.TOROKUDATE(+) " +
	"AND	D1.CYNO = D3.CN(+) " +
	"AND	D1.CYUZO_YY = D3.CYUZO_YY(+) " + // 追加 2014/9/24
	"AND 	D3.CNB = D4.CYUBAN(+) " +
	"AND 	D3.CYUZO_YY = D4.CYUZO_YY(+) " + // 追加 2014/9/24
	// 2014/09/25 追加 ▲

	// 2016/04/24 追加 ▼
	        "UNION ALL " +
	"SELECT " +
	"	 '5' TBLORDER, D1.LINKKEY,QCCOD01,QCCOD02,QCCOD03,QCCOD04,QCCOD05,QCCOD06,QCCOD07,QCCOD08,QCCOD09, " +
        "	QCCOD10,QCCOD11,QCCOD15,KHARI3,KHARI1,KHARI2,KNOBI3,KNOBI1,KNOBI2,KTAI3,KTAI1,KTAI2, " +
        "       KOSAXS01,KOSAXS02,KOSAXS03,KOSAXS04,KOSAXS05,KOSAXS06,KOSAYS01,KOSAYS02,KOSAYS03, " +
        "	KOSAYS04,KOSAYS05,KOSAYS06,KOSAZS01,KOSAZS02,KOSAZS03,KOSAZS04,KOSAZS05,KOSAZS06, " +
        "       KHAX,KHIN,KHAV,J_KHAX,J_KHIN,J_KHAV,KNAX,KNIN,KNAV,J_KNAX,J_KNIN,J_KNAV,KTAX,KTIN, " +
        "       KTAV,J_KTAX,J_KTIN,J_KTAV,KXAX,KXIN,KXAV,KYAX,KYIN,KYAV,KZAX,KZIN,KZAV, " +
        "       TRUNC(KEI_CU,6) KEI_CU, " +
        "       TRUNC(KEI_SI,6) KEI_SI, " +
        "       TRUNC(KEI_FE,6) KEI_FE, " +
        "       TRUNC(KEI_MN,6) KEI_MN, " +
        "       TRUNC(KEI_MG,6) KEI_MG, " +
        "       TRUNC(KEI_ZN,6) KEI_ZN, " +
        "       TRUNC(KEI_CR,6) KEI_CR, " +
        "       TRUNC(KEI_TI,6) KEI_TI, " +
        "       TRUNC(KEI_B,6) KEI_B, " +
        "       TRUNC(KEI_V,6) KEI_V, " +
        "       TRUNC(KEI_GA,6) KEI_GA, " +
        "       TRUNC(KEI_ZR,6) KEI_ZR, " +
        "       TRUNC(KEI_BE,6) KEI_BE, " +
        "       TRUNC(KEI_NA,6) KEI_NA, " +
        "       TRUNC(KEI_CA,6) KEI_CA, " +
        "       TRUNC(KEI_NI,6) KEI_NI, " +
        "       TRUNC(KEI_PB,6) KEI_PB, " +
        "       TRUNC(KEI_BI,6) KEI_BI, " +
        "       TRUNC(KEI_CD,6) KEI_CD, " +
        "       TRUNC(KEI_SN,6) KEI_SN, " +
        "       TRUNC(KEI_LI,6) KEI_LI, " +
        "       TRUNC(KEI_MO,6) KEI_MO, " +
        "       TRUNC(KEI_AG,6) KEI_AG, " +
        "       TRUNC(KEI_P,6) KEI_P, " +
        "       TRUNC(KEI_OTHER,6) KEI_OTHER, " +
        "       TRUNC(KEI_AL,6) KEI_AL " +
        "FROM " +
	"	DAT_L_JDF_COM D1, " +
	"	DAT_L_LOT_COM_KINS D2, " +
	"	DAT_L_SLB_COM D3, " +
	"	VIEW_TSB D4 " +
	"WHERE " +
	"	D1.LINKKEY = D2.LINKKEY " +
	"AND	TRUNC(D1.TOROKUDATE) = TRUNC(D2.TOROKUDATE(+)) " +
	"AND	D1.CYNO = D3.CN(+) " +
	"AND	D1.CYUZO_YY = D3.CYUZO_YY(+) " +
	"AND 	D3.CNB = D4.CYUBAN(+) " +
	"AND 	D3.CYUZO_YY = D4.CYUZO_YY(+) " +
	// 2016/04/24 追加 ▲

        ") " +
        "WHERE " +
	"       LINKKEY = ?" +
	"       AND TBLORDER = ? "
        ;


    //ｽﾀｯﾌ版ﾛｯﾄ情報SQL　品質情報
    private static final String STAFF_QUALITY_QUERY =
        "SELECT * FROM ( " +
	"SELECT " +
	"	'0' SELCHECK,LINKKEY,D1.INPUTSEQ,JYURYO1,JYURYO2,HABA1,HABA2,HABA3,TAKECD, " +
        "       DECODE(HABAKBN1,'1','OS','2','DS','3','ｾﾝﾀｰ','') HABAKBN1, " +
        "       DECODE(HABAKBN2,'1','OS','2','DS','3','ｾﾝﾀｰ','') HABAKBN2, " +
        "       DECODE(HABAKBN3,'1','OS','2','DS','') HABAKBN3, " +
        "       DECODE(TAKEKBN1,'1','頭 ','2','中 ','3','尾 ','4','上 ','5','中 ','6','下 ','') || " +
        "       DECODE(TAKEKBN2,'1','頭 ','2','中 ','3','尾 ','4','上 ','5','中 ','6','下 ','') || " +
        "       DECODE(TAKEKBN3,'1','頭 ','2','中 ','3','尾 ','4','上 ','5','中 ','6','下 ','') TAKEKBN, " +
	"       DECODE(MENKBN1,'1','上 ','2','下 ','') || " +
	"       DECODE(MENKBN2,'1','上 ','2','下 ','') MENKBN, " +
        "       NVL(TAKE3,0) || ' / ' || NVL(TAKE2,0) || DECODE(TAKECD,'R',' 巻','D',' ㎜','M',' ㎜','S',' 枚','') TAKE, " +
        "	PITCH,TAKE1, " +
        "	KOCYORYOKU,TAIRYOKU,NOBI,HOKOSEI,DECODE(MAGEKBN,'1','正常','2','異常','') MAGEKBN,ERICHSEN,CLADRITSU,LCHI, " +
        "	D1.ENTDATE,D1.SETSUBICD,D1.SETSUBIBUN,D1.IJYCD, " +
        "       D4.HSETSUBICD,D4.SYOCHICD,D4.HOSOKUCD,D4.SSYOCHICD,D4.DAIKOCD,SAITEHAILTNO,SAITEHAICYUNO,NVL(SAILINKKEY,'') SAILINKKEY, " +
        "       COMMENTMOJI,SYOCHINAIYO,HOSOKU, " +
        "       HSETSUBIMEI,IJYONAIYOJP,SSYOCHI,TANTOSYAJP,SETSUBIMEI,HBUNRUI " +
	"FROM " +
	"	DAT_IJYO_KANRI@HIN_LINK D1, " +
	"	DAT_IJYO_COMM@HIN_LINK D2, " +
	"	DAT_IJYO_INS@HIN_LINK D3, " +
	"	DAT_IJYO_SYOCHI@HIN_LINK D4, " +
	"	MAS_IJYO_SYOCHI@HIN_LINK M1, " +
	"	MAS_IJYO_HSETSUBI@HIN_LINK M2, " +
	"	MAS_IJYO_NAIYO@HIN_LINK M3, " +
	"	MAS_IJYO_SSYOCHI@HIN_LINK M4, " +
	"	MAS_IJYO_TANTOSYA@HIN_LINK M5, " +
	"	MAS_SETSUBI@HIN_LINK M6, " +
        "       MAS_IJYO_HOSOKU@HIN_LINK M7, " +
        "       MAS_IJYO_BUNRUI@HIN_LINK M8 " +
	"WHERE " +
	"	D1.INPUTSEQ = D2.INPUTSEQ " +
	"AND	D1.INPUTSEQ = D3.INPUTSEQ(+) " +
	"AND	D1.INPUTSEQ = D4.INPUTSEQ(+) " +
	"AND	D4.SYOCHICD = M1.SYOCHICD(+) " +
	"AND	D4.HSETSUBICD = M2.HSETSUBICD(+) " +
	"AND	D1.IJYCD = M3.IJYCD(+) " +
	"AND	D1.SETSUBIBUN = M3.SETSUBIBUN(+) " +
	"AND	D4.SSYOCHICD = M4.SSYOCHICD(+) " +
	"AND	D4.DAIKOCD = M5.TANTOSYACD(+) " +
	"AND	D1.SETSUBICD = M6.SETSUBICD(+) " +
        "AND	D4.HOSOKUCD = M7.HOSOKUCD(+) " +
        "AND	D4.HBUNRUICD = M8.HBUNRUICD(+) " +
        ") " +
        "WHERE " +
	"       LINKKEY = ? " +
        "ORDER BY " +
	"       ENTDATE DESC";


    //ICAS版ﾛｯﾄ情報SQL
    // @2011/01/21 保留区分(退避)を追加
    // @2015/02/16 試験者名(JUNO202)追加
    private static final String ICAS_QUERY =
        "SELECT * FROM ( " +
        "SELECT " +
        "   '1' TBLORDER,'2' LOT_FLAG,D1.LINKKEY,LTNO,DECODE(KNNO,'0000000000',NULL,KNNO) KNNO,DECODE(CYNO,'0000000000',NULL,CYNO) CYNO,JUNO,ZAIC02,JCD201, " +
	"   DECODE(JUNO202,'000000000',NULL,JUNO202) JUNO202, " +
        "   DECODE(SUBSTR(JCD201,1,1),'1','ｲﾀ','2','ｻｰｸﾙ','3','ｺｲﾙ') KJYO, " +
        "   DECODE(SLAB_TEHAIBI,NULL,NULL,'Y(' || DECODE(TO_CHAR(SLAB_TEHAIBI,'MM'),'10','X','11','Y','12','Z',SUBSTR(TO_CHAR(SLAB_TEHAIBI,'MM'),2,1)) || TO_CHAR(SLAB_TEHAIBI,'DD') || ')') TEHAI, " +
        "   DECODE(E_MARK,'E','E') EMARK, " +
        "   DECODE(JCD117,'1','P',DECODE(M_MARK,'M','M',DECODE(GPKBN,'1','@',' '))) YOTEI1, " +
        "   DECODE(JCD117,'1',TO_CHAR(D7.SBYMD,'MM/DD'),TO_CHAR(PITBI,'MM/DD')) YOTEI, " +
        "   DECODE(SLAB_TEHAIBI,NULL,NULL,'Y(' || DECODE(TO_CHAR(SLAB_TEHAIBI,'MM'),'10','X','11','Y','12','Z',SUBSTR(TO_CHAR(SLAB_TEHAIBI,'MM'),2,1)) || TO_CHAR(SLAB_TEHAIBI,'DD') || ')') || " +
        "   DECODE(E_MARK,'E','E') || " +
        "   DECODE(JCD117,'1','P',DECODE(M_MARK,'M','M',DECODE(GPKBN,'1','@',' '))) || " +
        "   DECODE(JCD117,'1',TO_CHAR(D7.SBYMD,'MM/DD'),TO_CHAR(PITBI,'MM/DD')) PITY, " +
        "   DECODE(KNOKI,NULL,TO_CHAR(ENOKI,'MM/DD'),TO_CHAR(ENOKI,'MM/DD') || '(' || TO_CHAR(KNOKI,'MM/DD') || ')') NOKI, " +
        "   JUA,JUB,JUX,JUY,JUZ,JUW,JUN JUMH,LTA,LTB,LTX,LTY,LTZ,TANW,HIJYU,SKIGO,JUP,PITJ,HOTJ,SSKW SEIZO,SH_RYOW KURA,STW SLAB,MENW SCA, " +
        "   DECODE(SLB_KBN,'1',TO_CHAR(SLB_YUKOY,'99999'),NULL) YUKOY, " +
        "   D7.SBMMDD SBMMDD_HOT,  " +
        "   D8.SBMMDD SBMMDD_SGNO_1, " +
        "   CASE " +
        "       WHEN D8.SBMMDD = D7.SBMMDD THEN '0.0' " +
        "       WHEN (D8.SBMMDDC < D7.SBMMDDC) AND (D7.SBMMDDC - D8.SBMMDDC = 2) THEN TO_CHAR(NVL(D8.SBMMDD-1-D7.SBMMDD,0),'9990.0') " +
        "       ELSE TO_CHAR(NVL(D8.SBMMDD-D7.SBMMDD,0),'9990.0') " +
        "   END ROLW, " +
        "   D9.SBMMDD SBMMDD_BENO_1, " +
        "   CASE " +
        "       WHEN D9.SBMMDD = D8.SBMMDD THEN '0.0' " +
        "       WHEN (D9.SBMMDDC < D8.SBMMDDC) AND (D8.SBMMDDC - D9.SBMMDDC = 2) THEN TO_CHAR(NVL(D9.SBMMDD-1-D8.SBMMDD,0),'9990.0') " +
        "      ELSE TO_CHAR(NVL(D9.SBMMDD-D8.SBMMDD,0),'9990.0') " +
        "   END SAGW, " +
        "   D10.SBMMDD SBMMDD_BENO, " +
        "   CASE " +
        "       WHEN D10.SBMMDD = D9.SBMMDD THEN '0.0' " +
        "       WHEN (D10.SBMMDDC < D9.SBMMDDC) AND (D9.SBMMDDC - D10.SBMMDDC = 2) THEN TO_CHAR(NVL(D10.SBMMDD-1-D9.SBMMDD,0),'9990.0') " +
        "       ELSE TO_CHAR(NVL(D10.SBMMDD-D9.SBMMDD,0),'9990.0') " +
        "   END KSW, " +
        "   '0.0' OTW, " +
        //"       '' OTW, " +
        "   SUBSTR((SELECT M1.TNNAME FROM MAS_TORINO M1 WHERE D1.TOKUI = M1.TNCD AND M1.KBN IN ('1', '2')),1,15) TOKUI, " +
        "   SUBSTR(M1.ONAME,1,15) OKURI, " +
        "   SUBSTR((SELECT M1.TNNAME FROM MAS_TORINO M1 WHERE D1.NONYU = M1.TNCD AND M1.KBN IN ('1', '3')),1,15) NONNA, " +
        "   JCD105,SGNO,BENO,PRJ, " +
        "   D1.YOTOC YOTOC,SUBSTR(M2.YOTONAME,1,11) YOTON, " +
	// 2012-10-04 VC情報表示のため追加
	"   D1.VCVCD OMOTE,D1.VCVCDB URA,D1.VCREV HANTEN,DECODE(TO_CHAR(D1.AMCLNO),'0','',TO_CHAR(D1.AMCLNO)) PA,DECODE(TO_CHAR(D1.ALCLR),'0','',TO_CHAR(D1.ALCLR)) CLR,D1.ALMEN MEN,D1.ALTUYA TUYA,D1.ALFUKO FUKO, " +
        "   DECODE(JCD202,'44','BF-5','----') ROLL,JCD202, " +
        "   (CASE " +
        "       WHEN HOTCL_NO IS NULL OR HOTCL_NO = '00000' THEN '     ' " +
        "       WHEN SUBSTR(HOTCL_NO,2,1) NOT IN('A','B','C','D','E','F','S') THEN '     ' " +
        "       WHEN NOT (SUBSTR(HOTCL_NO,3,3) >= '000' AND SUBSTR(HOTCL_NO,3,3) <= '999') THEN '     ' " +
        "       ELSE HOTCL_NO END " +
        "   ) HPC, " +
        "   DECODE(NVL(G2,'0'), " +
	// 2014/07/28 納期符号追加対応 Y.Moriyama
	/*
        "       '0','------------', " +
        "       '1','ｼﾞｾﾞﾝﾔｸｿｸﾋﾝ ', " +
        "       '2','ｺｷﾔｸﾉｳｷﾋﾝ   ', " +
        "       '3','ﾉｳｷﾁﾖｳｾｲﾋﾝ  ', " +
        "       '4','ｴｲｷﾞﾖｳﾉｳｷﾋﾝ ', " +
        "       '5','ｶﾝﾊﾞﾝﾋﾝ     ', " +
	*/
        "       '0','------------', " +
        "       '1','1:ｼﾞｾﾞﾝﾔｸｿｸﾋﾝ ', " +
        "       '2','2:ｺｷﾔｸｼﾃｲﾋﾝ   ', " +
        "       '3','3:ﾀﾝﾉｳｷﾋﾝ     ', " +
        "       '4','4:ﾄｳｹﾞﾂｳﾘｱｹﾞ  ', " +
        "       '5','5:ｺｷﾔｸｻﾞｲｺﾋﾝ  ', " +
        "       '6','6:ｶﾝﾊﾞﾝﾋﾝ     ', " +
        "       'C','C:ﾁﾕｳｶﾝｿｻﾞｲ ', " +
        "       'D','D:HOTﾀﾞﾐｰ   ', " +
        "       'K','K:ｶﾜｻﾞｲ     ', " +
        "       'G','G:ｷﾞｶｸﾋﾝ    ', " +
        "       'S','S:ﾖﾋﾞｼｻﾞｲ   ', " +
        "       'Y','Y:ｹﾞﾝｶﾐﾂﾓﾘ  ', " +
        "       'Z','Z:ﾏｽﾀｰﾛﾂﾄ   ', " +
        "   '') G2, " +
        "   DECODE(D11.JBSM,'KEN','ﾘﾖｳﾋﾝ','-----') RYOU, " +
        "   DECODE(D11.JBSM,'KEN',TO_CHAR(CASE WHEN JBRW > 0 AND MODW > 0 THEN JBRW - MODW ELSE JBRW END,'9999'),'-----') RYOW2, " +
        "   DECODE(D11.JBSM,'KEN',TO_CHAR(CASE WHEN JBRMH > 0 AND MODN > 0 THEN JBRMH - MODN ELSE JBRMH END,'9999'),'-----') RYON, " +
        "   JCD112,JCD114,JCD118,NOKIYMD,PITMAG_FLG,BUCODE,JCD106,SIJI,JCD101,TO_CHAR(NOKI3_RENBAN,'FM9000') NOKI3_RENBAN,HEN_LN, " +
        "   DECODE(JSWDTM,NULL,'00/00',TO_CHAR(JSWDTM,'MM/DD')) SSWDT, " +
        "   JSWW SSWW, " +
        "   DECODE(JMDTM,NULL,'00/00',TO_CHAR(JMDTM,'MM/DD')) SCADT, " +
        "   JMW SCAW, " +
        "   DECODE(D2.A11,'K','ﾌﾞ','--') BU, " +
        "   DECODE(D2.A11,'K',DECODE(D11.JBSM,'KEN',DECODE(NVL(D1.SW,0),0,'-----',TO_CHAR(NVL(D11.JBRW,0)*100/D1.SW,'9990.0')),'-----'),'-----') BR, " +
        "   DECODE(D2.A11,'K','ﾀｲ','--') TA, " +
        "   DECODE(D2.A11,'K',DECODE(D11.JBSM,'KEN',DECODE(NVL(D1.SH_RYOW,0),0,'-----',TO_CHAR(NVL(D11.JBRW,0)*100/D1.SH_RYOW,'9990.0')),'-----'),'-----') TR, " +
        "   DECODE(NVL(D11.JBRW,0),0,'-----',D11.JBRW) RYOW, " +
        "   DECODE(JCD114,'1','ﾁﾖｸ ','----')|| DECODE(JCD118,'1','ﾏﾙﾖ ','----') || NVL(SCHEDSM,'----') YOJYO, " +
        "   CASE " +
        "       WHEN D10.SBMMDD = D7.SBMMDD THEN '0.0' " +
        "       WHEN (D10.SBMMDDC < D7.SBMMDDC) AND (D7.SBMMDDC - D10.SBMMDDC = 2) THEN TO_CHAR(NVL(D10.SBMMDD-1-D7.SBMMDD,0),'9990.0') " +
        "       ELSE TO_CHAR(NVL(D10.SBMMDD-D7.SBMMDD,0),'9990.0') " +
        "   END ALDT, " +
        //"       CASE WHEN D10.SBMMDDC < D7.SBMMDDC THEN TO_CHAR(NVL(D10.SBMMDD-1-D7.SBMMDD,0),'9990.0') ELSE TO_CHAR(NVL(D10.SBMMDD-D7.SBMMDD,0),'9990.0') END ALDT, " +
        "   CASE WHEN JCD112 IN ('1','2','3','4') THEN NVL(TO_CHAR(NOKIYMD,'YY/MM/DD'),'--------') ELSE '--------' END SYDT, " + // 2017/4/27 3,4区分を追加
        "   '----------' MAGMSG, " +
        "   TO_CHAR(SYSDATE,'HH24:MI') HHMM, " +
        "   NVL(JBCD,'----') JBCD, " +
        "   BUCODE b, " +
//	"   DECODE(KARIKON_FLG, '1', 'ｶﾘｺﾝ', '') KARIKON_FLG, " +
	"   DECODE(KARIKON_FLG, '1', 'ｶﾘｺﾝ', '2' , 'Pﾀﾃﾔ', '') KARIKON_FLG, " +
        "   SCHEDSM, " +
        "   CHOSEI, " +
        "   NVL(JHF1X,0) HOTZ,  " +
        "   NVL(JHFTMP,0) HT1, " +
        "   NVL(JHF1TMP,0) HT2, " +
        "   HOTCL_NO, " +
        "   DECODE(NVL(D7.SBZ,0),0,DECODE(NVL(HOTPC,0),0,'------',TO_CHAR(HOTPC,'990.00')),'------') HOTPC, " +
        "   HIKICODE,HIKIKBN, " +
        "   KSTR,KSHC,MODW,MODN, " +
        "   DECODE(JCDTM,NULL,'00/00',TO_CHAR(JCDTM,'MM/DD')) CASDT, " +
        "   JCW CASW, " +
        "   DECODE(JPDT,NULL,'00/00',TO_CHAR(JPDT,'MM/DD')) PITDT, " +
        "   JPRB PTR, " +
        "   DECODE(NVL(JPRJ,0),0,DECODE(NVL(PRJ,0),0,'   ',PRJ),JPRJ) PTJ, " +
        "   KSD, " +
        "   TRIM((SELECT M1.SETSUBIMEI FROM MAS_SETSUBI M1 WHERE D6.CSCD1 = M1.SETSUBICD AND M1.SETSUBICDSEQ = 0) || ' ' || " +
        "   (SELECT M1.SETSUBIMEI FROM MAS_SETSUBI M1 WHERE D6.CSCD2 = M1.SETSUBICD AND M1.SETSUBICDSEQ = 0) || ' ' || " +
        "   (SELECT M1.SETSUBIMEI FROM MAS_SETSUBI M1 WHERE D6.CSCD3 = M1.SETSUBICD AND M1.SETSUBICDSEQ = 0) || ' ' || " +
        "   (SELECT M1.SETSUBIMEI FROM MAS_SETSUBI M1 WHERE D6.CSCD4 = M1.SETSUBICD AND M1.SETSUBICDSEQ = 0) " +
        "   ) SKSBSM, " +
//        "       DECODE(KSD,NULL,NULL,'( ' || TO_CHAR(KSD,'YY/MM/DD') || ' ｶﾝﾘｮｳ )') SKSBSM, " +
        "   SAI1_FRMDTM, " +
        "   DECODE(SAI1_FRMDTM,NULL,'00/00 00:00',TO_CHAR(SAI1_FRMDTM,'MM/DD HH24:MI')) SYUDTM, " +
        "   FRTDTM, " +
        "   DECODE(FRTDTM,NULL,'00/00',TO_CHAR(FRTDTM,'MM/DD')) ADDDT, " +
        "   DECODE(EDTM,NULL,'00/00 00:00',TO_CHAR(EDTM,'MM/DD HH24:MI')) MNTDTM, " +
        "   TNKBN,TNSEQ,TNWGT,AKNO,LO,HOKBN,KC5,CFST,P,SOKOIKIKBN " +
	"   , HOKBN_SAVE "   + // @2011/01/21 保留区分(退避)を追加
	"   , GIJUTSU_HOKBN "+
/* --- テスト用 --- */
/*
	"   , '' JRFLG " +   // @2012/12/10 Y.MORIYAMA 厚板徐冷情報追加
	"   , TO_CHAR(NULL) JRSTDT "  +
	"   , TO_CHAR(NULL) JREDDT "  +
	"   , TO_NUMBER(NULL) JRINT " +
	"   , TO_NUMBER(NULL) JRTMP " +
 */
 /* 本番用 */
	"   , D6.JRFLG " +   // @2012/12/10 Y.MORIYAMA 厚板徐冷情報追加
	"   , TO_CHAR(D6.JRSTDT,'YY/MM/DD') JRSTDT " +
	"   , TO_CHAR(D6.JREDDT,'YY/MM/DD') JREDDT " +
	"   , D6.JRINT " +
	"   , D6.JRTMP " +
/**/
        "FROM " +
        "   DAT_JDF_COM D1, " +
        "   DAT_LOT_COM D2, " +
        "   DAT_LOT_FIXEX D3, " +
        "   DAT_LOT_COM_HOT D4, " +
        "   DAT_LOT_COM_KINS D5, " +
        "   DAT_LOT_FIX D6, " +
        "   DAT_JDF_COM_SBOX D7, " +
        "   DAT_JDF_COM_SBOX D8, " +
        "   DAT_JDF_COM_SBOX D9, " +
        "   DAT_JDF_COM_SBOX D10, " +
        "   DAT_LOT_COM_JBOX D11, " +
        "   DAT_R_SLB_COM D12, " +
        "   MAS_OKURI M1, " +
        "   MAS_YOTO M2 " +
        "WHERE " +
        "       D1.LINKKEY = D2.LINKKEY(+) " +
        "AND	D1.LINKKEY = D3.LINKKEY(+) " +
        "AND	D3.LINKKEY = D4.LINKKEY(+) " +
        "AND	D3.HOT_BOXNO = D4.BOXNO(+) " +
        "AND	D1.LINKKEY = D5.LINKKEY(+) " +
        "AND	D1.LINKKEY = D6.LINKKEY(+) " +
        "AND	D1.LINKKEY = D7.LINKKEY(+) " +
        "AND	D7.BOXNO = 1 " +
        "AND	D1.LINKKEY = D8.LINKKEY(+) " +
        "AND	D1.SGNO - 1 = D8.BOXNO(+) " +
        "AND	D1.LINKKEY = D9.LINKKEY(+) " +
        "AND	D1.BENO - 1 = D9.BOXNO(+) " +
        "AND	D1.LINKKEY = D10.LINKKEY(+) " +
        "AND	D1.BENO = D10.BOXNO(+) " +
        "AND	D1.ON3N = M1.OKUCD(+) " +
        "AND	D1.MASYOTOC = M2.YOTOC(+) " +
        "AND	D1.LINKKEY = D11.LINKKEY(+) " +
        "AND	D1.BENO = D11.BOXNO(+) " +
        "AND	D1.CYNO = D12.CN(+) " +
	"AND	D1.CYUZO_YY = D12.CYUZO_YY(+) " + // 追加 2014/9/24
	"AND	D1.JYOTAI = 1 " +
        "UNION ALL " +
        "SELECT " +
        "   '2' TBLORDER,'2' LOT_FLAG,D2.LINKKEY,D1.LTNO, " +
        "   DECODE(D2.KNNO,'0000000000',NULL,D2.KNNO) KNNO, " +
        "   DECODE(D1.CYNO,'0000000000',NULL,D1.CYNO) CYNO, " +
        "   D1.JUNO,D1.ZAIC02,D1.JCD201, " +
	"   DECODE(JUNO202,'000000000',NULL,JUNO202) JUNO202, " +
        "   DECODE(SUBSTR(D1.JCD201,1,1),'1','ｲﾀ','2','ｻｰｸﾙ','3','ｺｲﾙ') KJYO, " +
        "   DECODE(D1.SLAB_TEHAIBI,NULL,NULL,'Y(' || DECODE(TO_CHAR(D1.SLAB_TEHAIBI,'MM'),'10','X','11','Y','12','Z',SUBSTR(TO_CHAR(D1.SLAB_TEHAIBI,'MM'),2,1)) || TO_CHAR(D1.SLAB_TEHAIBI,'DD') || ')') TEHAI, " +
        "   ' ' EMARK, " +
        "   DECODE(D1.JCD117,'1','P',DECODE(GPKBN,'1','@',' ')) YOTEI1, " +
        "   DECODE(D1.JCD117,'1',TO_CHAR(D7.SBYMD,'MM/DD'),TO_CHAR(D1.PITBI,'MM/DD')) YOTEI, " +
        "   DECODE(D1.SLAB_TEHAIBI,NULL,NULL,'Y(' || DECODE(TO_CHAR(D1.SLAB_TEHAIBI,'MM'),'10','X','11','Y','12','Z',SUBSTR(TO_CHAR(D1.SLAB_TEHAIBI,'MM'),2,1)) || TO_CHAR(D1.SLAB_TEHAIBI,'DD') || ')') || " +
        "   DECODE(E_MARK,'E','E', ' ') || " +
        "   DECODE(D1.JCD117,'1','P',DECODE(GPKBN,'1','@',' ')) || " +
        "   DECODE(D1.JCD117,'1',TO_CHAR(D7.SBYMD,'MM/DD'),TO_CHAR(D1.PITBI,'MM/DD')) PITY, " +
        "   DECODE(D1.KNOKI,NULL,TO_CHAR(D1.ENOKI,'MM/DD'),TO_CHAR(D1.ENOKI,'MM/DD') || '(' || TO_CHAR(D1.KNOKI,'MM/DD') || ')') NOKI, " +
        "   D1.JUA,D1.JUB,D1.JUX,D1.JUY,D1.JUZ,D1.JUW,D1.JUN JUMH,D1.LTA,D1.LTB,D1.LTX,D1.LTY,D1.LTZ, " +
        "   D1.TANW,D1.HIJYU,D1.SKIGO,D1.JUP,D1.PITJ,D1.HOTJ,D1.SSKW SEIZO,D1.SH_RYOW KURA,D1.STW SLAB,D1.MENW SCA, " +
        "   DECODE(D1.SLB_KBN,'1',TO_CHAR(D1.SLB_YUKOY,'99999'),NULL) YUKOY, " +
        "   D7.SBMMDD SBMMDD_HOT,D8.SBMMDD SBMMDD_SGNO_1, " +
        "   CASE " +
        "       WHEN D8.SBMMDD = D7.SBMMDD THEN '0.0' " +
  	"       WHEN (D8.SBMMDDC < D7.SBMMDDC) AND (D7.SBMMDDC - D8.SBMMDDC = 2) THEN TO_CHAR(NVL(D8.SBMMDD-1-D7.SBMMDD,0),'9990.0') " +
  	"       ELSE TO_CHAR(NVL(D8.SBMMDD-D7.SBMMDD,0),'9990.0') " +
  	"   END ROLW, " +
  	"   D9.SBMMDD SBMMDD_BENO_1, " +
  	"   CASE " +
  	"   WHEN D9.SBMMDD = D8.SBMMDD THEN '0.0' " +
        "   WHEN (D9.SBMMDDC < D8.SBMMDDC) AND (D8.SBMMDDC - D9.SBMMDDC = 2) THEN TO_CHAR(NVL(D9.SBMMDD-1-D8.SBMMDD,0),'9990.0') " +
        "   ELSE TO_CHAR(NVL(D9.SBMMDD-D8.SBMMDD,0),'9990.0') " +
        "   END SAGW, " +
  	"   D10.SBMMDD SBMMDD_BENO, " +
  	"   CASE " +
  	"   WHEN D10.SBMMDD = D9.SBMMDD THEN '0.0' " +
  	"   WHEN (D10.SBMMDDC < D9.SBMMDDC) AND (D9.SBMMDDC - D10.SBMMDDC = 2) THEN TO_CHAR(NVL(D10.SBMMDD-1-D9.SBMMDD,0),'9990.0') " +
  	"   ELSE TO_CHAR(NVL(D10.SBMMDD-D9.SBMMDD,0),'9990.0') " +
    	"   END KSW, " +
        "   '0.0' OTW, " +
        "   SUBSTR((SELECT M1.TNNAME FROM MAS_TORINO M1 WHERE D1.TOKUI = M1.TNCD AND M1.KBN IN ('1', '2')),1,15) TOKUI, " +
        "   SUBSTR(M1.ONAME,1,15) OKURI, " +
        "   SUBSTR((SELECT M1.TNNAME FROM MAS_TORINO M1 WHERE D1.NONYU = M1.TNCD AND M1.KBN IN ('1', '3')),1,15) NONNA, " +
        // 2012-10-04 VC情報表示のため追加 -start-
	"   D1.JCD105,D1.SGNO,D1.BENO,D1.PRJ,D1.YOTOC YOTOC,SUBSTR(M2.YOTONAME,1,11) YOTON, " +
	"   D1.VCVCD OMOTE,D1.VCVCDB URA,D1.VCREV HANTEN,DECODE(TO_CHAR(D1.AMCLNO),'0','',TO_CHAR(D1.AMCLNO)) PA,DECODE(TO_CHAR(D1.ALCLR),'0','',TO_CHAR(D1.ALCLR)) CLR,D1.ALMEN MEN,D1.ALTUYA TUYA,D1.ALFUKO FUKO, " +
	"   DECODE(JCD202,'44','BF-5','----') ROLL,D1.JCD202, " +
	// 2012-10-04 VC情報表示のため追加 -end-
	// 2014/07/28 納期符号追加対応 Y.Moriyama
	//"   '     '   HPC, DECODE(NVL(D1.G2,'0'),'0','------------','1','ｼﾞｾﾞﾝﾔｸｿｸﾋﾝ ','2','ｺｷﾔｸﾉｳｷﾋﾝ   ','3','ﾉｳｷﾁﾖｳｾｲﾋﾝ  ','4','ｴｲｷﾞﾖｳﾉｳｷﾋﾝ ','5','ｶﾝﾊﾞﾝﾋﾝ     ','') G2, " +
	"   '     '   HPC, " +
        "   DECODE(NVL(D1.G2,'0'), " +
        "       '0','------------', " +
        "       '1','1:ｼﾞｾﾞﾝﾔｸｿｸﾋﾝ ', " +
        "       '2','2:ｺｷﾔｸｼﾃｲﾋﾝ   ', " +
        "       '3','3:ﾀﾝﾉｳｷﾋﾝ     ', " +
        "       '4','4:ﾄｳｹﾞﾂｳﾘｱｹﾞ  ', " +
        "       '5','5:ｺｷﾔｸｻﾞｲｺﾋﾝ  ', " +
        "       '6','6:ｶﾝﾊﾞﾝﾋﾝ     ', " +
        "       'C','C:ﾁﾕｳｶﾝｿｻﾞｲ ', " +
        "       'D','D:HOTﾀﾞﾐｰ   ', " +
        "       'K','K:ｶﾜｻﾞｲ     ', " +
        "       'G','G:ｷﾞｶｸﾋﾝ    ', " +
        "       'S','S:ﾖﾋﾞｼｻﾞｲ   ', " +
        "       'Y','Y:ｹﾞﾝｶﾐﾂﾓﾘ  ', " +
        "       'Z','Z:ﾏｽﾀｰﾛﾂﾄ   ', " +
        "   '') G2, " +
	"   '-----' RYOU,'-----' RYOW2,'-----' RYON,D1.JCD112,D1.JCD114,D1.JCD118,D1.NOKIYMD,D1.PITMAG_FLG,D1.BUCODE,D1.JCD106, " +
        "   D1.SIJI,D1.JCD101,TO_CHAR(D1.NOKI3_RENBAN,'FM9000') NOKI3_RENBAN,D1.HEN_LN,'00/00'  SSWDT,0 SSWW,'00/00' SCADT, 0 SCAW, " +
        "   '--' BU,'-----' BR,'--' TA,'-----' TR,'-----' RYOW, " +
        "   DECODE(D1.JCD114,'1','ﾁﾖｸ ','----')|| DECODE(D1.JCD118,'1','ﾏﾙﾖ ','----') || '----' YOJYO, " +
        "   CASE " +
        "       WHEN D10.SBMMDD = D7.SBMMDD THEN '0.0' " +
        "       WHEN (D10.SBMMDDC < D7.SBMMDDC) AND (D7.SBMMDDC - D10.SBMMDDC = 2) THEN TO_CHAR(NVL(D10.SBMMDD-1-D7.SBMMDD,0),'9990.0') " +
        "       ELSE TO_CHAR(NVL(D10.SBMMDD-D7.SBMMDD,0),'9990.0') " +
        "   END ALDT, " +
        "   CASE WHEN D1.JCD112 IN ('1','2','3','4') THEN NVL(TO_CHAR(D1.NOKIYMD,'YY/MM/DD'),'--------') ELSE '--------' END SYDT, " +
        "   '----------' MAGMSG,TO_CHAR(SYSDATE,'HH24:MI') HHMM,NVL(D1.JBCD,'----') JBCD,D1.BUCODE b, '' KARIKON_FLG, '' SCHEDSM,0 CHOSEI, " +
        "   0 HOTZ,0 HT1,0 HT2,'' HOTCL_NO,'------' HOTPC,0 HIKICODE,' ' HIKIKBN,'' KSTR,'' KSHC,0 MODW,0 MODN, " +
        "   '00/00' CASDT,0 CASW,'00/00' PITDT,0 PTR,DECODE(NVL(D1.PRJ,0), 0, '   ', D1.PRJ) PTJ,TO_DATE(NULL) KSD,'' SKSBSM, " +
        "   D2.SAI1_FRMDTM,DECODE(D2.SAI1_FRMDTM,NULL,'00/00 00:00',TO_CHAR(D2.SAI1_FRMDTM,'MM/DD HH24:MI')) SYUDTM,D2.FRTDTM, " +
        "   DECODE(D2.FRTDTM,NULL,'00/00',TO_CHAR(D2.FRTDTM,'MM/DD')) ADDDT,'00/00 00:00' MNTDTM, " +
        "   '' TNKBN,0 TNSEQ,0 TNWGT,TO_NUMBER(NULL) AKNO,'' LO,'' HOKBN,'' KC5,0 CFST,D1.P,D2.SOKOIKIKBN " +
	"   , '' HOKBN_SAVE " + // @2011/01/21 保留区分(退避)を追加
	"   , '' GIJUTSU_HOKBN " +
/* --- テスト用 --- */
/*
	"   , '' JRFLG " +   // @2012/12/10 Y.MORIYAMA 厚板徐冷情報追加
	"   , TO_CHAR(NULL) JRSTDT "  +
	"   , TO_CHAR(NULL) JREDDT "  +
	"   , TO_NUMBER(NULL) JRINT " +
	"   , TO_NUMBER(NULL) JRTMP " +
*/
/* 本番用 */
	"   , '' JRFLG " +   // @2012/12/10 Y.MORIYAMA 厚板徐冷情報追加
	"   , TO_CHAR(NULL) JRSTDT "  +
	"   , TO_CHAR(NULL) JREDDT "  +
	"   , TO_NUMBER(NULL) JRINT " +
	"   , TO_NUMBER(NULL) JRTMP " +
/**/
        "FROM " +
  	"   DAT_JDF_COM2 D1, " +
  	"   DAT_JDF_FIX D2, " +
  	"   DAT_JDF_COM_SBOX2 D7, " +
  	"   DAT_JDF_COM_SBOX2 D8, " +
  	"   DAT_JDF_COM_SBOX2 D9, " +
  	"   DAT_JDF_COM_SBOX2 D10, " +
  	"   MAS_OKURI M1, " +
  	"   MAS_YOTO M2 " +
        "WHERE " +
        "       D1.LINKKEY = D2.LINKKEY " +
        "AND	D1.LINKKEY = D7.LINKKEY(+) " +
        "AND	D7.BOXNO = 1 " +
        "AND	D1.LINKKEY = D8.LINKKEY(+) " +
        "AND	D1.SGNO - 1 = D8.BOXNO(+) " +
        "AND	D1.LINKKEY = D9.LINKKEY(+) " +
        "AND	D1.BENO - 1 = D9.BOXNO(+) " +
        "AND	D1.LINKKEY = D10.LINKKEY(+) " +
        "AND	D1.BENO = D10.BOXNO(+) " +
        "AND	D1.ON3N = M1.OKUCD(+) " +
        "AND	D1.MASYOTOC = M2.YOTOC(+) " +
        "UNION ALL " +
        "SELECT " +
        "   '3' TBLORDER,'1' LOT_FLAG,D1.LINKKEY,LTNO,DECODE(D2.KNNO,'0000000000',NULL,D2.KNNO) KNNO,DECODE(CYNO,'0000000000',NULL,CYNO) CYNO,JUNO,ZAIC02,JCD201, " +
	"   DECODE(JUNO202,'000000000',NULL,JUNO202) JUNO202, " +
        "   DECODE(SUBSTR(JCD201,1,1),'1','ｲﾀ','2','ｻｰｸﾙ','3','ｺｲﾙ') KJYO, " +
        "   DECODE(SLAB_TEHAIBI,NULL,NULL,'Y(' || DECODE(TO_CHAR(SLAB_TEHAIBI,'MM'),'10','X','11','Y','12','Z',SUBSTR(TO_CHAR(SLAB_TEHAIBI,'MM'),2,1)) || TO_CHAR(SLAB_TEHAIBI,'DD') || ')') TEHAI, " +
        "   DECODE(E_MARK,'E','E') EMARK, " +
        "   DECODE(JCD117,'1','P',DECODE(M_MARK,'M','M',DECODE(GPKBN,'1','@',' '))) YOTEI1, " +
        "   DECODE(JCD117,'1',TO_CHAR(D7.SBYMD,'MM/DD'),TO_CHAR(PITBI,'MM/DD')) YOTEI, " +
        "   DECODE(SLAB_TEHAIBI,NULL,NULL,'Y(' || DECODE(TO_CHAR(SLAB_TEHAIBI,'MM'),'10','X','11','Y','12','Z',SUBSTR(TO_CHAR(SLAB_TEHAIBI,'MM'),2,1)) || TO_CHAR(SLAB_TEHAIBI,'DD') || ')') || " +
        "   DECODE(E_MARK,'E','E') || " +
        "   DECODE(JCD117,'1','P',DECODE(M_MARK,'M','M',DECODE(GPKBN,'1','@',' '))) || " +
        "   DECODE(JCD117,'1',TO_CHAR(D7.SBYMD,'MM/DD'),TO_CHAR(PITBI,'MM/DD')) PITY, " +
        "   DECODE(KNOKI,NULL,TO_CHAR(ENOKI,'MM/DD'),TO_CHAR(ENOKI,'MM/DD') || '(' || TO_CHAR(KNOKI,'MM/DD') || ')') NOKI, " +
        "   JUA,JUB,JUX,JUY,JUZ,JUW,JUN JUMH,LTA,LTB,LTX,LTY,LTZ,TANW,HIJYU,SKIGO,JUP,PITJ,HOTJ,SSKW SEIZO,SH_RYOW KURA,STW SLAB,MENW SCA, " +
        "   DECODE(SLB_KBN,'1',TO_CHAR(SLB_YUKOY,'99999'),NULL) YUKOY, " +
        "   D7.SBMMDD SBMMDD_HOT,  " +
        "   D8.SBMMDD SBMMDD_SGNO_1, " +
        "   CASE " +
        "       WHEN D8.SBMMDD = D7.SBMMDD THEN '0.0' " +
        "       WHEN (D8.SBMMDDC < D7.SBMMDDC) AND (D7.SBMMDDC - D8.SBMMDDC = 2) THEN TO_CHAR(NVL(D8.SBMMDD-1-D7.SBMMDD,0),'9990.0') " +
        "       ELSE TO_CHAR(NVL(D8.SBMMDD-D7.SBMMDD,0),'9990.0') " +
        "   END ROLW, " +
        //"       '' ROLW, " +
        "   D9.SBMMDD SBMMDD_BENO_1, " +
        "   CASE " +
        "       WHEN D9.SBMMDD = D8.SBMMDD THEN '0.0' " +
        "       WHEN (D9.SBMMDDC < D8.SBMMDDC) AND (D8.SBMMDDC - D9.SBMMDDC = 2) THEN TO_CHAR(NVL(D9.SBMMDD-1-D8.SBMMDD,0),'9990.0') " +
        "       ELSE TO_CHAR(NVL(D9.SBMMDD-D8.SBMMDD,0),'9990.0') " +
        "   END SAGW, " +
        //"       '' SAGW, " +
        "   D10.SBMMDD SBMMDD_BENO, " +
        "   CASE " +
        "       WHEN D10.SBMMDD = D9.SBMMDD THEN '0.0' " +
        "       WHEN (D10.SBMMDDC < D9.SBMMDDC) AND (D9.SBMMDDC - D10.SBMMDDC = 2) THEN TO_CHAR(NVL(D10.SBMMDD-1-D9.SBMMDD,0),'9990.0') " +
        "       ELSE TO_CHAR(NVL(D10.SBMMDD-D9.SBMMDD,0),'9990.0') " +
        "   END KSW, " +
        //"       '' KSW, " +
        "   '0.0' OTW, " +
        //"       '' OTW, " +
        "   SUBSTR((SELECT M1.TNNAME FROM MAS_TORINO M1 WHERE D1.TOKUI = M1.TNCD AND M1.KBN IN ('1', '2')),1,15) TOKUI, " +
        "   SUBSTR(M1.ONAME,1,15) OKURI, " +
        "   SUBSTR((SELECT M1.TNNAME FROM MAS_TORINO M1 WHERE D1.NONYU = M1.TNCD AND M1.KBN IN ('1', '3')),1,15) NONNA, " +
        "   JCD105,SGNO,BENO,PRJ, " +
        "   D1.YOTOC YOTOC,SUBSTR(M2.YOTONAME,1,11) YOTON, " +
	// 2012-10-04 VC情報表示のため追加
	"   D1.VCVCD OMOTE,D1.VCVCDB URA,D1.VCREV HANTEN,DECODE(TO_CHAR(D1.AMCLNO),'0','',TO_CHAR(D1.AMCLNO)) PA,DECODE(TO_CHAR(D1.ALCLR),'0','',TO_CHAR(D1.ALCLR)) CLR,D1.ALMEN MEN,D1.ALTUYA TUYA,D1.ALFUKO FUKO, " +
        "   DECODE(JCD202,'44','BF-5','----') ROLL,JCD202, " +
        "   (CASE " +
        "       WHEN HOTCL_NO IS NULL OR HOTCL_NO = '00000' THEN '     ' " +
        "       WHEN SUBSTR(HOTCL_NO,2,1) NOT IN('A','B','C','D','E','F','S') THEN '     ' " +
        "       WHEN NOT (SUBSTR(HOTCL_NO,3,3) >= '000' AND SUBSTR(HOTCL_NO,3,3) <= '999') THEN '     ' " +
        "       ELSE HOTCL_NO END " +
        "   ) HPC, " +
        //"       '     ' HPC, " +
        "   DECODE(NVL(G2,'0'), " +
	// 2014/07/28 納期符号追加対応 Y.Moriyama
	/*
	"   '0','------------', " +
        "   '1','ｼﾞｾﾞﾝﾔｸｿｸﾋﾝ ', " +
        "   '2','ｺｷﾔｸﾉｳｷﾋﾝ   ', " +
        "   '3','ﾉｳｷﾁﾖｳｾｲﾋﾝ  ', " +
        "   '4','ｴｲｷﾞﾖｳﾉｳｷﾋﾝ ', " +
        "   '5','ｶﾝﾊﾞﾝﾋﾝ     ', " +
	*/
        "   '0','------------', " +
        "   '1','1:ｼﾞｾﾞﾝﾔｸｿｸﾋﾝ ', " +
        "   '2','2:ｺｷﾔｸｼﾃｲﾋﾝ   ', " +
        "   '3','3:ﾀﾝﾉｳｷﾋﾝ     ', " +
        "   '4','4:ﾄｳｹﾞﾂｳﾘｱｹﾞ  ', " +
        "   '5','5:ｺｷﾔｸｻﾞｲｺﾋﾝ  ', " +
        "   '6','6:ｶﾝﾊﾞﾝﾋﾝ     ', " +
        "   'C','C:ﾁﾕｳｶﾝｿｻﾞｲ ', " +
        "   'D','D:HOTﾀﾞﾐｰ   ', " +
        "   'K','K:ｶﾜｻﾞｲ     ', " +
        "   'G','G:ｷﾞｶｸﾋﾝ    ', " +
        "   'S','S:ﾖﾋﾞｼｻﾞｲ   ', " +
        "   'Y','Y:ｹﾞﾝｶﾐﾂﾓﾘ  ', " +
        "   'Z','Z:ﾏｽﾀｰﾛﾂﾄ   ', " +
        "   '') G2, " +
        "   DECODE(D11.JBSM,'KEN','ﾘﾖｳﾋﾝ','-----') RYOU, " +
        "   DECODE(D11.JBSM,'KEN',TO_CHAR(CASE WHEN JBRW > 0 AND MODW > 0 THEN JBRW - MODW ELSE JBRW END,'9999'),'-----') RYOW2, " +
        "   DECODE(D11.JBSM,'KEN',TO_CHAR(CASE WHEN JBRMH > 0 AND MODN > 0 THEN JBRMH - MODN ELSE JBRMH END,'9999'),'-----') RYON, " +
        "   JCD112,JCD114,JCD118,TO_DATE(NULL) NOKIYMD,PITMAG_FLG,BUCODE,JCD106,SIJI,JCD101,TO_CHAR(NOKI3_RENBAN,'FM9000') NOKI3_RENBAN,HEN_LN, " +
        "   DECODE(JSWDTM,NULL,'00/00',TO_CHAR(JSWDTM,'MM/DD')) SSWDT, " +
        "   JSWW SSWW, " +
        "   DECODE(JMDTM,NULL,'00/00',TO_CHAR(JMDTM,'MM/DD')) SCADT, " +
        "   JMW SCAW, " +
        "   DECODE(D2.A11,'K','ﾌﾞ','--') BU, " +
        "   DECODE(D2.A11,'K',DECODE(D11.JBSM,'KEN',DECODE(NVL(D1.SW,0),0,'-----',TO_CHAR(NVL(D11.JBRW,0)*100/D1.SW,'9990.0')),'-----'),'-----') BR, " +
        "   DECODE(D2.A11,'K','ﾀｲ','--') TA, " +
        "   DECODE(D2.A11,'K',DECODE(D11.JBSM,'KEN',DECODE(NVL(D1.SH_RYOW,0),0,'-----',TO_CHAR(NVL(D11.JBRW,0)*100/D1.SH_RYOW,'9990.0')),'-----'),'-----') TR, " +
        "   DECODE(NVL(D11.JBRW,0),0,'-----',D11.JBRW) RYOW, " +
        "   DECODE(JCD114,'1','ﾁﾖｸ ','----')|| DECODE(JCD118,'1','ﾏﾙﾖ ','----') || '----' YOJYO, " +
        "   CASE " +
        "       WHEN D10.SBMMDD = D7.SBMMDD THEN '0.0' " +
        "       WHEN (D10.SBMMDDC < D7.SBMMDDC) AND (D7.SBMMDDC - D10.SBMMDDC = 2) THEN TO_CHAR(NVL(D10.SBMMDD-1-D7.SBMMDD,0),'9990.0') " +
        "       ELSE TO_CHAR(NVL(D10.SBMMDD-D7.SBMMDD,0),'9990.0') " +
        "   END ALDT, " +
        //"       CASE WHEN D10.SBMMDDC < D7.SBMMDDC THEN TO_CHAR(NVL(D10.SBMMDD-1-D7.SBMMDD,0),'9990.0') ELSE TO_CHAR(NVL(D10.SBMMDD-D7.SBMMDD,0),'9990.0') END ALDT, " +
        //"       '' ALDT, " +
        "   CASE WHEN JCD112 IN ('1','2','3','4') THEN NVL(TO_CHAR(NOKIYMD,'YY/MM/DD'),'--------') ELSE '--------' END SYDT, " +
        //"       TO_DATE(NULL) SYDT, " +
        "   '----------' MAGMSG, " +
        "   TO_CHAR(SYSDATE,'HH24:MI') HHMM, " +
        "   NVL(JBCD,'----') JBCD, " +
        "   BUCODE b, " +
	"   KARIKON_FLG, " +
        "   SCHEDSM, " +
        "   CHOSEI, " +
        "   NVL(JHF1X,0) HOTZ,  " +
        "   NVL(JHFTMP,0) HT1, " +
        "   NVL(JHF1TMP,0) HT2, " +
        "   HOTCL_NO, " +
        "   DECODE(NVL(D7.SBZ,0),0,DECODE(NVL(HOTPC,0),0,'------',TO_CHAR(HOTPC,'990.00')),'------') HOTPC, " +
        "   HIKICODE,HIKIKBN, " +
        "   KSTR,KSHC,MODW,MODN, " +
        "   DECODE(JCDTM,NULL,'00/00',TO_CHAR(JCDTM,'MM/DD')) CASDT, " +
        "   JCW CASW, " +
        "   DECODE(JPDT,NULL,'00/00',TO_CHAR(JPDT,'MM/DD')) PITDT, " +
        "   JPRB PTR, " +
        "   DECODE(NVL(JPRJ,0),0,DECODE(NVL(PRJ,0),0,'   ',PRJ),JPRJ) PTJ, " +
        "   KSD, " +
        "   DECODE(KSD,NULL,NULL,'( ' || TO_CHAR(KSD,'YY/MM/DD') || ' ｶﾝﾘｮｳ )') SKSBSM, " +
        "   SAI1_FRMDTM, " +
        "   DECODE(SAI1_FRMDTM,NULL,'00/00 00:00',TO_CHAR(SAI1_FRMDTM,'MM/DD HH24:MI')) SYUDTM, " +
        "   FRTDTM, " +
        "   DECODE(FRTDTM,NULL,'00/00',TO_CHAR(FRTDTM,'MM/DD')) ADDDT, " +
        "   DECODE(EDTM,NULL,'00/00 00:00',TO_CHAR(EDTM,'MM/DD HH24:MI')) MNTDTM, " +
        "   TNKBN,TNSEQ,TNWGT,AKNO,LO,HOKBN,KC5,CFST,P,SOKOIKIKBN " +
	"   , HOKBN_SAVE "    +  // @2011/01/21 保留区分(退避)を追加
	"   , GIJUTSU_HOKBN " +
/* ---- テスト用 ---- */
/*
	"   , '' JRFLG " +   // @2012/12/10 Y.MORIYAMA 厚板徐冷情報追加
	"   , TO_CHAR(NULL) JRSTDT "  +
	"   , TO_CHAR(NULL) JREDDT "  +
	"   , TO_NUMBER(NULL) JRINT " +
	"   , TO_NUMBER(NULL) JRTMP " +
*/
/* 本番用 */
	"   , D6.JRFLG " +  // @2012/12/10 Y.MORIYAMA 厚板徐冷情報追加
	"   , TO_CHAR(D6.JRSTDT,'YY/MM/DD') JRSTDT " +
	"   , TO_CHAR(D6.JREDDT,'YY/MM/DD') JREDDT " +
	"   , D6.JRINT " +
	"   , D6.JRTMP " +
/**/
        "FROM " +
        "   DAT_R_JDF_COM D1, " +
        "   DAT_R_LOT_COM D2, " +
        "   DAT_R_LOT_FIXEX D3, " +
        "   DAT_R_LOT_COM_HOT D4, " +
        "   DAT_R_LOT_COM_KINS D5, " +
        "   DAT_R_LOT_FIX D6, " +
        "   DAT_R_JDF_COM_SBOX D7, " +
        "   DAT_R_JDF_COM_SBOX D8, " +
        "   DAT_R_JDF_COM_SBOX D9, " +
        "   DAT_R_JDF_COM_SBOX D10, " +
        "   DAT_R_LOT_COM_JBOX D11, " +
        "   DAT_R_SLB_COM D12, " +
        "   MAS_OKURI M1, " +
        "   MAS_YOTO M2 " +
        "WHERE " +
        "       D1.LINKKEY = D2.LINKKEY(+) " +
        "AND	D1.LINKKEY = D3.LINKKEY(+) " +
        "AND	D3.LINKKEY = D4.LINKKEY(+) " +
        "AND	D3.HOT_BOXNO = D4.BOXNO(+) " +
        "AND	D1.LINKKEY = D5.LINKKEY(+) " +
        "AND	D1.LINKKEY = D6.LINKKEY(+) " +
        "AND	D1.LINKKEY = D7.LINKKEY(+) " +
        "AND	D7.BOXNO = 1 " +
        "AND	D1.LINKKEY = D8.LINKKEY(+) " +
        "AND	D1.SGNO - 1 = D8.BOXNO(+) " +
        "AND	D1.LINKKEY = D9.LINKKEY(+) " +
        "AND	D1.BENO - 1 = D9.BOXNO(+) " +
        "AND	D1.LINKKEY = D10.LINKKEY(+) " +
        "AND	D1.BENO = D10.BOXNO(+) " +
        "AND	D1.ON3N = M1.OKUCD(+) " +
        "AND	D1.MASYOTOC = M2.YOTOC(+) " +
        "AND	D1.LINKKEY = D11.LINKKEY(+) " +
        "AND	D1.BENO = D11.BOXNO(+) " +
        "AND	D1.CYNO = D12.CN(+) " +
	"AND	D1.CYUZO_YY = D12.CYUZO_YY(+) " + // 追加 2014/09/
	"AND	D1.JYOTAI = 2 " +
	// 2014/09/25 追加 ▼
        "UNION ALL " +
        "SELECT " +
        "   '4' TBLORDER,'1' LOT_FLAG,D1.LINKKEY,LTNO,DECODE(D2.KNNO,'0000000000',NULL,D2.KNNO) KNNO,DECODE(CYNO,'0000000000',NULL,CYNO) CYNO,JUNO,ZAIC02,JCD201, " +
	"   DECODE(JUNO202,'000000000',NULL,JUNO202) JUNO202, " +
        "   DECODE(SUBSTR(JCD201,1,1),'1','ｲﾀ','2','ｻｰｸﾙ','3','ｺｲﾙ') KJYO, " +
        "   DECODE(SLAB_TEHAIBI,NULL,NULL,'Y(' || DECODE(TO_CHAR(SLAB_TEHAIBI,'MM'),'10','X','11','Y','12','Z',SUBSTR(TO_CHAR(SLAB_TEHAIBI,'MM'),2,1)) || TO_CHAR(SLAB_TEHAIBI,'DD') || ')') TEHAI, " +
        "   DECODE(E_MARK,'E','E') EMARK, " +
        "   DECODE(JCD117,'1','P',DECODE(M_MARK,'M','M',DECODE(GPKBN,'1','@',' '))) YOTEI1, " +
        "   DECODE(JCD117,'1',TO_CHAR(D7.SBYMD,'MM/DD'),TO_CHAR(PITBI,'MM/DD')) YOTEI, " +
        "   DECODE(SLAB_TEHAIBI,NULL,NULL,'Y(' || DECODE(TO_CHAR(SLAB_TEHAIBI,'MM'),'10','X','11','Y','12','Z',SUBSTR(TO_CHAR(SLAB_TEHAIBI,'MM'),2,1)) || TO_CHAR(SLAB_TEHAIBI,'DD') || ')') || " +
        "   DECODE(E_MARK,'E','E') || " +
        "   DECODE(JCD117,'1','P',DECODE(M_MARK,'M','M',DECODE(GPKBN,'1','@',' '))) || " +
        "   DECODE(JCD117,'1',TO_CHAR(D7.SBYMD,'MM/DD'),TO_CHAR(PITBI,'MM/DD')) PITY, " +
        "   DECODE(KNOKI,NULL,TO_CHAR(ENOKI,'MM/DD'),TO_CHAR(ENOKI,'MM/DD') || '(' || TO_CHAR(KNOKI,'MM/DD') || ')') NOKI, " +
        "   JUA,JUB,JUX,JUY,JUZ,JUW,JUN JUMH,LTA,LTB,LTX,LTY,LTZ,TANW,HIJYU,SKIGO,JUP,PITJ,HOTJ,SSKW SEIZO,SH_RYOW KURA,STW SLAB,MENW SCA, " +
        "   DECODE(SLB_KBN,'1',TO_CHAR(SLB_YUKOY,'99999'),NULL) YUKOY, " +
        "   D7.SBMMDD SBMMDD_HOT,  " +
        "   D8.SBMMDD SBMMDD_SGNO_1, " +
        "   CASE " +
        "       WHEN D8.SBMMDD = D7.SBMMDD THEN '0.0' " +
        "       WHEN (D8.SBMMDDC < D7.SBMMDDC) AND (D7.SBMMDDC - D8.SBMMDDC = 2) THEN TO_CHAR(NVL(D8.SBMMDD-1-D7.SBMMDD,0),'9990.0') " +
        "       ELSE TO_CHAR(NVL(D8.SBMMDD-D7.SBMMDD,0),'9990.0') " +
        "   END ROLW, " +
        "   D9.SBMMDD SBMMDD_BENO_1, " +
        "   CASE " +
        "       WHEN D9.SBMMDD = D8.SBMMDD THEN '0.0' " +
        "       WHEN (D9.SBMMDDC < D8.SBMMDDC) AND (D8.SBMMDDC - D9.SBMMDDC = 2) THEN TO_CHAR(NVL(D9.SBMMDD-1-D8.SBMMDD,0),'9990.0') " +
        "       ELSE TO_CHAR(NVL(D9.SBMMDD-D8.SBMMDD,0),'9990.0') " +
        "   END SAGW, " +
        "   D10.SBMMDD SBMMDD_BENO, " +
        "   CASE " +
        "       WHEN D10.SBMMDD = D9.SBMMDD THEN '0.0' " +
        "       WHEN (D10.SBMMDDC < D9.SBMMDDC) AND (D9.SBMMDDC - D10.SBMMDDC = 2) THEN TO_CHAR(NVL(D10.SBMMDD-1-D9.SBMMDD,0),'9990.0') " +
        "       ELSE TO_CHAR(NVL(D10.SBMMDD-D9.SBMMDD,0),'9990.0') " +
        "   END KSW, " +
        "   '0.0' OTW, " +
        "   SUBSTR((SELECT M1.TNNAME FROM MAS_TORINO M1 WHERE D1.TOKUI = M1.TNCD AND M1.KBN IN ('1', '2')),1,15) TOKUI, " +
        "   SUBSTR(M1.ONAME,1,15) OKURI, " +
        "   SUBSTR((SELECT M1.TNNAME FROM MAS_TORINO M1 WHERE D1.NONYU = M1.TNCD AND M1.KBN IN ('1', '3')),1,15) NONNA, " +
        "   JCD105,SGNO,BENO,PRJ, " +
        "   D1.YOTOC YOTOC,SUBSTR(M2.YOTONAME,1,11) YOTON, " +
	"   D1.VCVCD OMOTE,D1.VCVCDB URA,D1.VCREV HANTEN,DECODE(TO_CHAR(D1.AMCLNO),'0','',TO_CHAR(D1.AMCLNO)) PA,DECODE(TO_CHAR(D1.ALCLR),'0','',TO_CHAR(D1.ALCLR)) CLR,D1.ALMEN MEN,D1.ALTUYA TUYA,D1.ALFUKO FUKO, " +
        "   DECODE(JCD202,'44','BF-5','----') ROLL,JCD202, " +
        "   (CASE " +
        "       WHEN HOTCL_NO IS NULL OR HOTCL_NO = '00000' THEN '     ' " +
        "       WHEN SUBSTR(HOTCL_NO,2,1) NOT IN('A','B','C','D','E','F','S') THEN '     ' " +
        "       WHEN NOT (SUBSTR(HOTCL_NO,3,3) >= '000' AND SUBSTR(HOTCL_NO,3,3) <= '999') THEN '     ' " +
        "       ELSE HOTCL_NO END " +
        "   ) HPC, " +
        "   DECODE(NVL(G2,'0'), " +
        "   '0','------------', " +
        "   '1','1:ｼﾞｾﾞﾝﾔｸｿｸﾋﾝ ', " +
        "   '2','2:ｺｷﾔｸｼﾃｲﾋﾝ   ', " +
        "   '3','3:ﾀﾝﾉｳｷﾋﾝ     ', " +
        "   '4','4:ﾄｳｹﾞﾂｳﾘｱｹﾞ  ', " +
        "   '5','5:ｺｷﾔｸｻﾞｲｺﾋﾝ  ', " +
        "   '6','6:ｶﾝﾊﾞﾝﾋﾝ     ', " +
        "   'C','C:ﾁﾕｳｶﾝｿｻﾞｲ ', " +
        "   'D','D:HOTﾀﾞﾐｰ   ', " +
        "   'K','K:ｶﾜｻﾞｲ     ', " +
        "   'G','G:ｷﾞｶｸﾋﾝ    ', " +
        "   'S','S:ﾖﾋﾞｼｻﾞｲ   ', " +
        "   'Y','Y:ｹﾞﾝｶﾐﾂﾓﾘ  ', " +
        "   'Z','Z:ﾏｽﾀｰﾛﾂﾄ   ', " +
        "   '') G2, " +
        "   DECODE(D11.JBSM,'KEN','ﾘﾖｳﾋﾝ','-----') RYOU, " +
        "   DECODE(D11.JBSM,'KEN',TO_CHAR(CASE WHEN JBRW > 0 AND MODW > 0 THEN JBRW - MODW ELSE JBRW END,'9999'),'-----') RYOW2, " +
        "   DECODE(D11.JBSM,'KEN',TO_CHAR(CASE WHEN JBRMH > 0 AND MODN > 0 THEN JBRMH - MODN ELSE JBRMH END,'9999'),'-----') RYON, " +
        "   JCD112,JCD114,JCD118,TO_DATE(NULL) NOKIYMD,PITMAG_FLG,BUCODE,JCD106,SIJI,JCD101,TO_CHAR(NOKI3_RENBAN,'FM9000') NOKI3_RENBAN,HEN_LN, " +
        "   DECODE(JSWDTM,NULL,'00/00',TO_CHAR(JSWDTM,'MM/DD')) SSWDT, " +
        "   JSWW SSWW, " +
        "   DECODE(JMDTM,NULL,'00/00',TO_CHAR(JMDTM,'MM/DD')) SCADT, " +
        "   JMW SCAW, " +
        "   DECODE(D2.A11,'K','ﾌﾞ','--') BU, " +
        "   DECODE(D2.A11,'K',DECODE(D11.JBSM,'KEN',DECODE(NVL(D1.SW,0),0,'-----',TO_CHAR(NVL(D11.JBRW,0)*100/D1.SW,'9990.0')),'-----'),'-----') BR, " +
        "   DECODE(D2.A11,'K','ﾀｲ','--') TA, " +
        "   DECODE(D2.A11,'K',DECODE(D11.JBSM,'KEN',DECODE(NVL(D1.SH_RYOW,0),0,'-----',TO_CHAR(NVL(D11.JBRW,0)*100/D1.SH_RYOW,'9990.0')),'-----'),'-----') TR, " +
        "   DECODE(NVL(D11.JBRW,0),0,'-----',D11.JBRW) RYOW, " +
        "   DECODE(JCD114,'1','ﾁﾖｸ ','----')|| DECODE(JCD118,'1','ﾏﾙﾖ ','----') || '----' YOJYO, " +
        "   CASE " +
        "       WHEN D10.SBMMDD = D7.SBMMDD THEN '0.0' " +
        "       WHEN (D10.SBMMDDC < D7.SBMMDDC) AND (D7.SBMMDDC - D10.SBMMDDC = 2) THEN TO_CHAR(NVL(D10.SBMMDD-1-D7.SBMMDD,0),'9990.0') " +
        "       ELSE TO_CHAR(NVL(D10.SBMMDD-D7.SBMMDD,0),'9990.0') " +
        "   END ALDT, " +
        "   CASE WHEN JCD112 IN ('1','2','3','4') THEN NVL(TO_CHAR(NOKIYMD,'YY/MM/DD'),'--------') ELSE '--------' END SYDT, " +
        "   '----------' MAGMSG, " +
        "   TO_CHAR(SYSDATE,'HH24:MI') HHMM, " +
        "   NVL(JBCD,'----') JBCD, " +
        "   BUCODE b, " +
	"   KARIKON_FLG, " +
        "   SCHEDSM, " +
        "   CHOSEI, " +
        "   NVL(JHF1X,0) HOTZ,  " +
        "   NVL(JHFTMP,0) HT1, " +
        "   NVL(JHF1TMP,0) HT2, " +
        "   HOTCL_NO, " +
        "   DECODE(NVL(D7.SBZ,0),0,DECODE(NVL(HOTPC,0),0,'------',TO_CHAR(HOTPC,'990.00')),'------') HOTPC, " +
        "   HIKICODE,HIKIKBN, " +
        "   KSTR,KSHC,MODW,MODN, " +
        "   DECODE(JCDTM,NULL,'00/00',TO_CHAR(JCDTM,'MM/DD')) CASDT, " +
        "   JCW CASW, " +
        "   DECODE(JPDT,NULL,'00/00',TO_CHAR(JPDT,'MM/DD')) PITDT, " +
        "   JPRB PTR, " +
        "   DECODE(NVL(JPRJ,0),0,DECODE(NVL(PRJ,0),0,'   ',PRJ),JPRJ) PTJ, " +
        "   KSD, " +
        "   DECODE(KSD,NULL,NULL,'( ' || TO_CHAR(KSD,'YY/MM/DD') || ' ｶﾝﾘｮｳ )') SKSBSM, " +
        "   SAI1_FRMDTM, " +
        "   DECODE(SAI1_FRMDTM,NULL,'00/00 00:00',TO_CHAR(SAI1_FRMDTM,'MM/DD HH24:MI')) SYUDTM, " +
        "   FRTDTM, " +
        "   DECODE(FRTDTM,NULL,'00/00',TO_CHAR(FRTDTM,'MM/DD')) ADDDT, " +
        "   DECODE(EDTM,NULL,'00/00 00:00',TO_CHAR(EDTM,'MM/DD HH24:MI')) MNTDTM, " +
        "   TNKBN,TNSEQ,TNWGT,AKNO,LO,HOKBN,KC5,CFST,P,SOKOIKIKBN " +
	"   , HOKBN_SAVE "    +
	"   , GIJUTSU_HOKBN " +
	"   , D6.JRFLG " +
	"   , TO_CHAR(D6.JRSTDT,'YY/MM/DD') JRSTDT " +
	"   , TO_CHAR(D6.JREDDT,'YY/MM/DD') JREDDT " +
	"   , D6.JRINT " +
	"   , D6.JRTMP " +
        "FROM " +
        "   DAT_S_JDF_COM D1, " +
        "   DAT_S_LOT_COM D2, " +
        "   DAT_S_LOT_FIXEX D3, " +
        "   DAT_S_LOT_COM_HOT D4, " +
        "   DAT_S_LOT_COM_KINS D5, " +
        "   DAT_S_LOT_FIX D6, " +
        "   DAT_S_JDF_COM_SBOX D7, " +
        "   DAT_S_JDF_COM_SBOX D8, " +
        "   DAT_S_JDF_COM_SBOX D9, " +
        "   DAT_S_JDF_COM_SBOX D10, " +
        "   DAT_S_LOT_COM_JBOX D11, " +
        "   DAT_S_SLB_COM D12, " +
        "   MAS_OKURI M1, " +
        "   MAS_YOTO M2 " +
        "WHERE " +
        "       D1.LINKKEY = D2.LINKKEY(+) " +
        "AND	D1.TOROKUDATE = D2.TOROKUDATE(+) " +
        "AND	D1.LINKKEY = D3.LINKKEY(+) " +
        "AND	D1.TOROKUDATE = D3.TOROKUDATE(+) " +
        "AND	D3.LINKKEY = D4.LINKKEY(+) " +
        "AND	D3.HOT_BOXNO = D4.BOXNO(+) " +
	"AND	D3.TOROKUDATE = D4.TOROKUDATE(+) " +
        "AND	D1.LINKKEY = D5.LINKKEY(+) " +
	"AND	D1.TOROKUDATE = D5.TOROKUDATE(+) " +
        "AND	D1.LINKKEY = D6.LINKKEY(+) " +
	"AND	D1.TOROKUDATE = D6.TOROKUDATE(+) " +
        "AND	D1.LINKKEY = D7.LINKKEY(+) " +
	"AND	D1.TOROKUDATE = D7.TOROKUDATE(+) " +
	"AND	D7.BOXNO = 1 " +
	"AND	D1.LINKKEY = D8.LINKKEY(+) " +
	"AND	D1.TOROKUDATE = D8.TOROKUDATE(+) " +
        "AND	D1.SGNO - 1 = D8.BOXNO(+) " +
        "AND	D1.LINKKEY = D9.LINKKEY(+) " +
	"AND	D1.TOROKUDATE = D9.TOROKUDATE(+) " +
	"AND	D1.BENO - 1 = D9.BOXNO(+) " +
        "AND	D1.LINKKEY = D10.LINKKEY(+) " +
	"AND	D1.TOROKUDATE = D10.TOROKUDATE(+) " +
        "AND	D1.BENO = D10.BOXNO(+) " +
        "AND	D1.ON3N = M1.OKUCD(+) " +
        "AND	D1.MASYOTOC = M2.YOTOC(+) " +
        "AND	D1.LINKKEY = D11.LINKKEY(+) " +
	"AND	D1.TOROKUDATE = D11.TOROKUDATE(+) " +
        "AND	D1.BENO = D11.BOXNO(+) " +
        "AND	D1.CYNO = D12.CN(+) " +
	"AND	D1.CYUZO_YY = D12.CYUZO_YY(+) " +
	// 2014/09/25 追加 ▲

	// 2016/04/24 追加 ▼
	        "UNION ALL " +
        "SELECT " +
        "   '5' TBLORDER,'1' LOT_FLAG,D1.LINKKEY,LTNO,DECODE(D2.KNNO,'0000000000',NULL,D2.KNNO) KNNO,DECODE(CYNO,'0000000000',NULL,CYNO) CYNO,JUNO,ZAIC02,JCD201, " +
	"   DECODE(JUNO202,'000000000',NULL,JUNO202) JUNO202, " +
        "   DECODE(SUBSTR(JCD201,1,1),'1','ｲﾀ','2','ｻｰｸﾙ','3','ｺｲﾙ') KJYO, " +
        "   DECODE(SLAB_TEHAIBI,NULL,NULL,'Y(' || DECODE(TO_CHAR(SLAB_TEHAIBI,'MM'),'10','X','11','Y','12','Z',SUBSTR(TO_CHAR(SLAB_TEHAIBI,'MM'),2,1)) || TO_CHAR(SLAB_TEHAIBI,'DD') || ')') TEHAI, " +
        "   DECODE(E_MARK,'E','E') EMARK, " +
        "   DECODE(JCD117,'1','P',DECODE(M_MARK,'M','M',DECODE(GPKBN,'1','@',' '))) YOTEI1, " +
        "   DECODE(JCD117,'1',TO_CHAR(D7.SBYMD,'MM/DD'),TO_CHAR(PITBI,'MM/DD')) YOTEI, " +
        "   DECODE(SLAB_TEHAIBI,NULL,NULL,'Y(' || DECODE(TO_CHAR(SLAB_TEHAIBI,'MM'),'10','X','11','Y','12','Z',SUBSTR(TO_CHAR(SLAB_TEHAIBI,'MM'),2,1)) || TO_CHAR(SLAB_TEHAIBI,'DD') || ')') || " +
        "   DECODE(E_MARK,'E','E') || " +
        "   DECODE(JCD117,'1','P',DECODE(M_MARK,'M','M',DECODE(GPKBN,'1','@',' '))) || " +
        "   DECODE(JCD117,'1',TO_CHAR(D7.SBYMD,'MM/DD'),TO_CHAR(PITBI,'MM/DD')) PITY, " +
        "   DECODE(KNOKI,NULL,TO_CHAR(ENOKI,'MM/DD'),TO_CHAR(ENOKI,'MM/DD') || '(' || TO_CHAR(KNOKI,'MM/DD') || ')') NOKI, " +
        "   JUA,JUB,JUX,JUY,JUZ,JUW,JUN JUMH,LTA,LTB,LTX,LTY,LTZ,TANW,HIJYU,SKIGO,JUP,PITJ,HOTJ,SSKW SEIZO,SH_RYOW KURA,STW SLAB,MENW SCA, " +
        "   DECODE(SLB_KBN,'1',TO_CHAR(SLB_YUKOY,'99999'),NULL) YUKOY, " +
        "   D7.SBMMDD SBMMDD_HOT,  " +
        "   D8.SBMMDD SBMMDD_SGNO_1, " +
        "   CASE " +
        "       WHEN D8.SBMMDD = D7.SBMMDD THEN '0.0' " +
        "       WHEN (D8.SBMMDDC < D7.SBMMDDC) AND (D7.SBMMDDC - D8.SBMMDDC = 2) THEN TO_CHAR(NVL(D8.SBMMDD-1-D7.SBMMDD,0),'9990.0') " +
        "       ELSE TO_CHAR(NVL(D8.SBMMDD-D7.SBMMDD,0),'9990.0') " +
        "   END ROLW, " +
        "   D9.SBMMDD SBMMDD_BENO_1, " +
        "   CASE " +
        "       WHEN D9.SBMMDD = D8.SBMMDD THEN '0.0' " +
        "       WHEN (D9.SBMMDDC < D8.SBMMDDC) AND (D8.SBMMDDC - D9.SBMMDDC = 2) THEN TO_CHAR(NVL(D9.SBMMDD-1-D8.SBMMDD,0),'9990.0') " +
        "       ELSE TO_CHAR(NVL(D9.SBMMDD-D8.SBMMDD,0),'9990.0') " +
        "   END SAGW, " +
        "   D10.SBMMDD SBMMDD_BENO, " +
        "   CASE " +
        "       WHEN D10.SBMMDD = D9.SBMMDD THEN '0.0' " +
        "       WHEN (D10.SBMMDDC < D9.SBMMDDC) AND (D9.SBMMDDC - D10.SBMMDDC = 2) THEN TO_CHAR(NVL(D10.SBMMDD-1-D9.SBMMDD,0),'9990.0') " +
        "       ELSE TO_CHAR(NVL(D10.SBMMDD-D9.SBMMDD,0),'9990.0') " +
        "   END KSW, " +
        "   '0.0' OTW, " +
        "   SUBSTR((SELECT M1.TNNAME FROM MAS_TORINO M1 WHERE D1.TOKUI = M1.TNCD AND M1.KBN IN ('1', '2')),1,15) TOKUI, " +
        "   SUBSTR(M1.ONAME,1,15) OKURI, " +
        "   SUBSTR((SELECT M1.TNNAME FROM MAS_TORINO M1 WHERE D1.NONYU = M1.TNCD AND M1.KBN IN ('1', '3')),1,15) NONNA, " +
        "   JCD105,SGNO,BENO,PRJ, " +
        "   D1.YOTOC YOTOC,SUBSTR(M2.YOTONAME,1,11) YOTON, " +
	"   D1.VCVCD OMOTE,D1.VCVCDB URA,D1.VCREV HANTEN,DECODE(TO_CHAR(D1.AMCLNO),'0','',TO_CHAR(D1.AMCLNO)) PA,DECODE(TO_CHAR(D1.ALCLR),'0','',TO_CHAR(D1.ALCLR)) CLR,D1.ALMEN MEN,D1.ALTUYA TUYA,D1.ALFUKO FUKO, " +
        "   DECODE(JCD202,'44','BF-5','----') ROLL,JCD202, " +
        "   (CASE " +
        "       WHEN HOTCL_NO IS NULL OR HOTCL_NO = '00000' THEN '     ' " +
        "       WHEN SUBSTR(HOTCL_NO,2,1) NOT IN('A','B','C','D','E','F','S') THEN '     ' " +
        "       WHEN NOT (SUBSTR(HOTCL_NO,3,3) >= '000' AND SUBSTR(HOTCL_NO,3,3) <= '999') THEN '     ' " +
        "       ELSE HOTCL_NO END " +
        "   ) HPC, " +
        "   DECODE(NVL(G2,'0'), " +
        "   '0','------------', " +
        "   '1','1:ｼﾞｾﾞﾝﾔｸｿｸﾋﾝ ', " +
        "   '2','2:ｺｷﾔｸｼﾃｲﾋﾝ   ', " +
        "   '3','3:ﾀﾝﾉｳｷﾋﾝ     ', " +
        "   '4','4:ﾄｳｹﾞﾂｳﾘｱｹﾞ  ', " +
        "   '5','5:ｺｷﾔｸｻﾞｲｺﾋﾝ  ', " +
        "   '6','6:ｶﾝﾊﾞﾝﾋﾝ     ', " +
        "   'C','C:ﾁﾕｳｶﾝｿｻﾞｲ ', " +
        "   'D','D:HOTﾀﾞﾐｰ   ', " +
        "   'K','K:ｶﾜｻﾞｲ     ', " +
        "   'G','G:ｷﾞｶｸﾋﾝ    ', " +
        "   'S','S:ﾖﾋﾞｼｻﾞｲ   ', " +
        "   'Y','Y:ｹﾞﾝｶﾐﾂﾓﾘ  ', " +
        "   'Z','Z:ﾏｽﾀｰﾛﾂﾄ   ', " +
        "   '') G2, " +
        "   DECODE(D11.JBSM,'KEN','ﾘﾖｳﾋﾝ','-----') RYOU, " +
        "   DECODE(D11.JBSM,'KEN',TO_CHAR(CASE WHEN JBRW > 0 AND MODW > 0 THEN JBRW - MODW ELSE JBRW END,'9999'),'-----') RYOW2, " +
        "   DECODE(D11.JBSM,'KEN',TO_CHAR(CASE WHEN JBRMH > 0 AND MODN > 0 THEN JBRMH - MODN ELSE JBRMH END,'9999'),'-----') RYON, " +
        "   JCD112,JCD114,JCD118,TO_DATE(NULL) NOKIYMD,PITMAG_FLG,BUCODE,JCD106,SIJI,JCD101,TO_CHAR(NOKI3_RENBAN,'FM9000') NOKI3_RENBAN,HEN_LN, " +
        "   DECODE(JSWDTM,NULL,'00/00',TO_CHAR(JSWDTM,'MM/DD')) SSWDT, " +
        "   JSWW SSWW, " +
        "   DECODE(JMDTM,NULL,'00/00',TO_CHAR(JMDTM,'MM/DD')) SCADT, " +
        "   JMW SCAW, " +
        "   DECODE(D2.A11,'K','ﾌﾞ','--') BU, " +
        "   DECODE(D2.A11,'K',DECODE(D11.JBSM,'KEN',DECODE(NVL(D1.SW,0),0,'-----',TO_CHAR(NVL(D11.JBRW,0)*100/D1.SW,'9990.0')),'-----'),'-----') BR, " +
        "   DECODE(D2.A11,'K','ﾀｲ','--') TA, " +
        "   DECODE(D2.A11,'K',DECODE(D11.JBSM,'KEN',DECODE(NVL(D1.SH_RYOW,0),0,'-----',TO_CHAR(NVL(D11.JBRW,0)*100/D1.SH_RYOW,'9990.0')),'-----'),'-----') TR, " +
        "   DECODE(NVL(D11.JBRW,0),0,'-----',D11.JBRW) RYOW, " +
        "   DECODE(JCD114,'1','ﾁﾖｸ ','----')|| DECODE(JCD118,'1','ﾏﾙﾖ ','----') || '----' YOJYO, " +
        "   CASE " +
        "       WHEN D10.SBMMDD = D7.SBMMDD THEN '0.0' " +
        "       WHEN (D10.SBMMDDC < D7.SBMMDDC) AND (D7.SBMMDDC - D10.SBMMDDC = 2) THEN TO_CHAR(NVL(D10.SBMMDD-1-D7.SBMMDD,0),'9990.0') " +
        "       ELSE TO_CHAR(NVL(D10.SBMMDD-D7.SBMMDD,0),'9990.0') " +
        "   END ALDT, " +
        "   CASE WHEN JCD112 IN ('1','2','3','4') THEN NVL(TO_CHAR(NOKIYMD,'YY/MM/DD'),'--------') ELSE '--------' END SYDT, " +
        "   '----------' MAGMSG, " +
        "   TO_CHAR(SYSDATE,'HH24:MI') HHMM, " +
        "   NVL(JBCD,'----') JBCD, " +
        "   BUCODE b, " +
	"   KARIKON_FLG, " +
        "   SCHEDSM, " +
        "   CHOSEI, " +
        "   NVL(JHF1X,0) HOTZ,  " +
        "   NVL(JHFTMP,0) HT1, " +
        "   NVL(JHF1TMP,0) HT2, " +
        "   HOTCL_NO, " +
        "   DECODE(NVL(D7.SBZ,0),0,DECODE(NVL(HOTPC,0),0,'------',TO_CHAR(HOTPC,'990.00')),'------') HOTPC, " +
        "   HIKICODE,HIKIKBN, " +
        "   KSTR,KSHC,MODW,MODN, " +
        "   DECODE(JCDTM,NULL,'00/00',TO_CHAR(JCDTM,'MM/DD')) CASDT, " +
        "   JCW CASW, " +
        "   DECODE(JPDT,NULL,'00/00',TO_CHAR(JPDT,'MM/DD')) PITDT, " +
        "   JPRB PTR, " +
        "   DECODE(NVL(JPRJ,0),0,DECODE(NVL(PRJ,0),0,'   ',PRJ),JPRJ) PTJ, " +
        "   KSD, " +
        "   DECODE(KSD,NULL,NULL,'( ' || TO_CHAR(KSD,'YY/MM/DD') || ' ｶﾝﾘｮｳ )') SKSBSM, " +
        "   SAI1_FRMDTM, " +
        "   DECODE(SAI1_FRMDTM,NULL,'00/00 00:00',TO_CHAR(SAI1_FRMDTM,'MM/DD HH24:MI')) SYUDTM, " +
        "   FRTDTM, " +
        "   DECODE(FRTDTM,NULL,'00/00',TO_CHAR(FRTDTM,'MM/DD')) ADDDT, " +
        "   DECODE(EDTM,NULL,'00/00 00:00',TO_CHAR(EDTM,'MM/DD HH24:MI')) MNTDTM, " +
        "   TNKBN,TNSEQ,TNWGT,AKNO,LO,HOKBN,KC5,CFST,P,SOKOIKIKBN " +
	"   , HOKBN_SAVE "    +
	"   , GIJUTSU_HOKBN " +
	"   , D6.JRFLG " +
	"   , TO_CHAR(D6.JRSTDT,'YY/MM/DD') JRSTDT " +
	"   , TO_CHAR(D6.JREDDT,'YY/MM/DD') JREDDT " +
	"   , D6.JRINT " +
	"   , D6.JRTMP " +
        "FROM " +
        "   DAT_L_JDF_COM D1, " +
        "   DAT_L_LOT_COM D2, " +
        "   DAT_L_LOT_FIXEX D3, " +
        "   DAT_L_LOT_COM_HOT D4, " +
        "   DAT_L_LOT_COM_KINS D5, " +
        "   DAT_L_LOT_FIX D6, " +
        "   DAT_L_JDF_COM_SBOX D7, " +
        "   DAT_L_JDF_COM_SBOX D8, " +
        "   DAT_L_JDF_COM_SBOX D9, " +
        "   DAT_L_JDF_COM_SBOX D10, " +
        "   DAT_L_LOT_COM_JBOX D11, " +
        "   DAT_L_SLB_COM D12, " +
        "   MAS_OKURI M1, " +
        "   MAS_YOTO M2 " +
        "WHERE " +
        "       D1.LINKKEY = D2.LINKKEY(+) " +
        "AND	TRUNC(D1.TOROKUDATE) = TRUNC(D2.TOROKUDATE(+)) " +
        "AND	D1.LINKKEY = D3.LINKKEY(+) " +
        "AND	TRUNC(D1.TOROKUDATE) = TRUNC(D3.TOROKUDATE(+)) " +
        "AND	D3.LINKKEY = D4.LINKKEY(+) " +
        "AND	D3.HOT_BOXNO = D4.BOXNO(+) " +
	"AND	TRUNC(D3.TOROKUDATE) = TRUNC(D4.TOROKUDATE(+)) " +
        "AND	D1.LINKKEY = D5.LINKKEY(+) " +
	"AND	TRUNC(D1.TOROKUDATE) = TRUNC(D5.TOROKUDATE(+)) " +
        "AND	D1.LINKKEY = D6.LINKKEY(+) " +
	"AND	TRUNC(D1.TOROKUDATE) = TRUNC(D6.TOROKUDATE(+)) " +
        "AND	D1.LINKKEY = D7.LINKKEY(+) " +
	"AND	TRUNC(D1.TOROKUDATE) = TRUNC(D7.TOROKUDATE(+)) " +
	"AND	D7.BOXNO = 1 " +
	"AND	D1.LINKKEY = D8.LINKKEY(+) " +
	"AND	TRUNC(D1.TOROKUDATE) = TRUNC(D8.TOROKUDATE(+)) " +
        "AND	D1.SGNO - 1 = D8.BOXNO(+) " +
        "AND	D1.LINKKEY = D9.LINKKEY(+) " +
	"AND	TRUNC(D1.TOROKUDATE) = TRUNC(D9.TOROKUDATE(+)) " +
	"AND	D1.BENO - 1 = D9.BOXNO(+) " +
        "AND	D1.LINKKEY = D10.LINKKEY(+) " +
	"AND	TRUNC(D1.TOROKUDATE) = TRUNC(D10.TOROKUDATE(+)) " +
        "AND	D1.BENO = D10.BOXNO(+) " +
        "AND	D1.ON3N = M1.OKUCD(+) " +
        "AND	D1.MASYOTOC = M2.YOTOC(+) " +
        "AND	D1.LINKKEY = D11.LINKKEY(+) " +
	"AND	TRUNC(D1.TOROKUDATE) = TRUNC(D11.TOROKUDATE(+)) " +
        "AND	D1.BENO = D11.BOXNO(+) " +
        "AND	D1.CYNO = D12.CN(+) " +
	"AND	D1.CYUZO_YY = D12.CYUZO_YY(+) " +
	// 2016/04/24 追加 ▲
        ") " +
        "WHERE " +
        "       LINKKEY = ? " +
    	"       AND TBLORDER = ? " +
        "ORDER BY " +
        "       FRTDTM DESC,KSD DESC";


    //ICAS版ﾛｯﾄ情報SQL 設備BOX
    private static final String ICAS_BOX_QUERY =
        "SELECT * FROM ( " +
	"SELECT " +
		"	'1' TBLORDER, " +
        "	D1.LINKKEY, " +
        "	D1.BOXNO, " +
        "	SBK, " +
        "	SBYMD, " +
        "	SBMMDD, " +
        "	SBMMDDC, " +
        "	TO_CHAR(DECODE(JCD112,'0',NVL(SBMMDD,SBYMD),SBYMD),'MM,DD') || DECODE(DECODE(JCD112,'0',NVL(SBMMDD,SBYMD),SBYMD),NULL,NULL,'.' || DECODE(JCD112,'0',DECODE(SBMMDDC,0,SBYMDC,SBMMDDC),SBYMDC)) STARTDT, " +
        "	SBSM, " +
        "	SBX, " +
        "	SBY, " +
        "	SBZ, " +
        "	(DECODE(SUBSTR(SBSM,1,2),'L-',DECODE(INSTR(SBPAT,'ﾚ',1),0,NULL,'ﾚ')) || " +
        "        DECODE(NVL(INSTR(SBPAT,'ｻ',1),0) + NVL(INSTR(SBPAT,'ｾ',1),0),0,NULL,'@')) SBPAT, " +
        "	SBMH, " +
        "	SH_SBW, " +
        "	TRUNC(DECODE(SBSM,'KEN',SH_RYOW,SH_SBW)) SBW, " +
        "	DECODE(JBSM,NULL,'S','J') PROC_KBN, " +
        "	A11, " +
        "	(CASE " +
	"	 WHEN JBSM = 'VC' THEN DECODE(SUBSTR(JBPAT,3,1),'1','HVC','2','LVC','3','SVC','4','TVC','8','LSVC','9','FVC','VC') " +
        "	 WHEN JBSM IN('ANI','ANF') THEN JBSM || TRIM(TO_CHAR(SUBSTR(TO_CHAR(JBSCD,'000'),3,2),'99')) " +
        "	 WHEN JBSCD IN(330,331) THEN 'QF1' " +
        "	 WHEN JBSCD = 332 THEN 'QF2' " +
	// 2012-09-28 設備追加-start-
	"	 WHEN JBSCD = 211 THEN 'PSW1' " +
	"	 WHEN JBSCD = 212 THEN 'PSW2' " +
	// 2012-09-28 設備追加-end-
        "	 ELSE NVL(JBSM,SBSM) END " +
        "	) JBSM, " +
//        "	TRUNC(DECODE(D2.JBSM,NULL,DECODE(SBSM,'KEN',SH_RYOW,SH_SBW),JBRW),0) JBRW, " +
        "	TRUNC(CASE WHEN JBSM is NULL THEN DECODE(SBSM,'KEN',SH_RYOW,SH_SBW) WHEN JBSM = 'KEN' THEN JBRW - D5.MODW ELSE JBRW END) JBRW, " + //2015/10/5 修正
//        "	JBRMH, " +
	"	TRUNC(CASE WHEN JBSM = 'KEN' THEN JBRMH - D5.MODN ELSE JBRMH END) JBRMH, " + //2015/10/5 修正
        "	DECODE(D2.JBSM,NULL,SBPAT,JBPAT) JBPAT, " +
        "	JBEDTM, " +
        "	JBEDTC, " +
        "	DECODE(D2.JBSM,NULL, " +
        "	TO_CHAR(DECODE(JCD112,'0',NVL(SBMMDD,SBYMD),SBYMD),'Y,MM,DD') || DECODE(DECODE(JCD112,'0',NVL(SBMMDD,SBYMD),SBYMD),NULL,NULL,'.' || DECODE(JCD112,'0',DECODE(SBMMDDC,0,SBYMDC,SBMMDDC),SBYMDC)), " +
        "	TO_CHAR(JBEDTM,'Y,MM,DD') || '.' || JBEDTC " +
        "	) ENDDT, " +
        "	DECODE(D2.JBSM,NULL,SBX,JBX) JBX, " +
        "	DECODE(D2.JBSM,NULL,SBY,JBY) JBY, " +
        "	DECODE(D2.JBSM,NULL,SBZ,JBZ) JBZ, " +
        "	DECODE(D2.JBSM,NULL,TO_NUMBER(NULL),JBBN) JBBN, " +
        "	D3.CYUZO_YY " +
        "FROM " +
	"	DAT_JDF_COM_SBOX D1, " +
	"	DAT_LOT_COM_JBOX D2, " +
	"	DAT_JDF_COM D3, " +
	"	DAT_LOT_COM D4, " +
	"	DAT_LOT_COM_KINS D5 " +
	"WHERE " +
	"	D1.LINKKEY = D2.LINKKEY(+) " +
	"AND	D1.BOXNO = D2.BOXNO(+) " +
	"AND	D1.LINKKEY = D3.LINKKEY " +
	"AND	D1.LINKKEY = D4.LINKKEY(+) " +
	"AND	D3.JYOTAI = 1 " +
	"AND	D1.LINKKEY = D5.LINKKEY " +
        "UNION ALL " +
	"SELECT " +
	"	'2' TBLORDER, " +
	"	D1.LINKKEY, " +
	"	D1.BOXNO, " +
	"	SBK, " +
	"	SBYMD, " +
	"	SBMMDD, " +
	"	SBMMDDC, " +
	"	TO_CHAR(DECODE(JCD112,'0',NVL(SBMMDD,SBYMD),SBYMD),'MM,DD') || DECODE(DECODE(JCD112,'0',NVL(SBMMDD,SBYMD),SBYMD),NULL,NULL,'.' || DECODE(JCD112,'0',DECODE(SBMMDDC,0,SBYMDC,SBMMDDC),SBYMDC)) STARTDT, " +
	"	SBSM, " +
	"	SBX, " +
	"	SBY, " +
	"	SBZ, " +
	"	(DECODE(SUBSTR(SBSM,1,2),'L-',DECODE(INSTR(SBPAT,'ﾚ',1),0,NULL,'ﾚ')) || " +
	"        DECODE(NVL(INSTR(SBPAT,'ｻ',1),0) + NVL(INSTR(SBPAT,'ｾ',1),0),0,NULL,'@')) SBPAT, " +
	"	SBMH, " +
	"	SH_SBW, " +
	"	TRUNC(DECODE(SBSM,'KEN',SH_RYOW,SH_SBW)) SBW, " + //2012-10-11 数値切り上げ
	"	'S' PROC_KBN, " +
	"	'' A11, " +
	"	SBSM JBSM, " +
	"	TRUNC(DECODE(SBSM,'KEN',SH_RYOW,SH_SBW)) JBRW, " + //2012-10-11 数値切り上げ
	"	0 JBRMH, " +
	"	SBPAT JBPAT, " +
	"	TO_DATE(NULL) JBEDTM, " +
	"	TO_NUMBER(NULL) JBEDTC, " +
	"	TO_CHAR(DECODE(JCD112,'0',NVL(SBMMDD,SBYMD),SBYMD),'Y,MM,DD') || DECODE(DECODE(JCD112,'0',NVL(SBMMDD,SBYMD),SBYMD),NULL,NULL,'.' || DECODE(JCD112,'0',DECODE(SBMMDDC,0,SBYMDC,SBMMDDC),SBYMDC)) ENDDT, " +
	"	SBX JBX, " +
	"	SBY JBY, " +
	"	SBZ JBZ, " +
	"	TO_NUMBER(NULL) JBBN, " +
	"	D2.CYUZO_YY " +
	"FROM " +
	"	DAT_JDF_COM_SBOX2 D1, " +
	"	DAT_JDF_COM2 D2 " +
	"WHERE " +
	"	D1.LINKKEY = D2.LINKKEY " +
	"UNION ALL " +
	"SELECT " +
		"	'3' TBLORDER, " +
        "	D1.LINKKEY, " +
        "	D1.BOXNO, " +
        "	SBK, " +
        "	SBYMD, " +
        "	SBMMDD, " +
        "	SBMMDDC, " +
        "	TO_CHAR(DECODE(JCD112,'0',NVL(SBMMDD,SBYMD),SBYMD),'MM,DD') || DECODE(DECODE(JCD112,'0',NVL(SBMMDD,SBYMD),SBYMD),NULL,NULL,'.' || DECODE(JCD112,'0',DECODE(SBMMDDC,0,SBYMDC,SBMMDDC),SBYMDC)) STARTDT, " +
        "	SBSM, " +
        "	SBX, " +
        "	SBY, " +
        "	SBZ, " +
//        "	(DECODE(SUBSTR(SBSM,1,2),'L-',DECODE(INSTR(SBPAT,'ﾚ',1),0,NULL,'ﾚ')) || " +
//        "        DECODE(NVL(INSTR(SBPAT,'ｻ',1),0) + NVL(INSTR(SBPAT,'ｾ',1),0),0,NULL,'@')) SBPAT, " +
        "       '' SBPAT, " +
        "	SBMH, " +
        "	SH_SBW, " +
        "	TRUNC(DECODE(SBSM,'KEN',SH_RYOW,SH_SBW)) SBW, " + //2012-10-11 数値切り上げ
        "	DECODE(JBSM,NULL,'S','J') PROC_KBN, " +
        "	A11, " +
        "	(CASE " +
        //"	 WHEN JBSM = 'VC' THEN DECODE(SUBSTR(JBPAT,3,1),'1','HVC','2','LVC','3','SVC','4','TVC','9','FVC','VC') " + //test
	"	 WHEN JBSM = 'VC' THEN DECODE(SUBSTR(JBPAT,3,1),'1','HVC','2','LVC','3','SVC','4','TVC','8','LSVC','9','FVC','VC') " +
        "	 WHEN JBSM IN('ANI','ANF') THEN JBSM || TRIM(TO_CHAR(SUBSTR(TO_CHAR(JBSCD,'000'),3,2),'99')) " +
        "	 WHEN JBSCD IN(330,331) THEN 'QF1' " +
        "	 WHEN JBSCD = 332 THEN 'QF2' " +
	// 2012-09-28 設備追加-start-
	"	 WHEN JBSCD = 211 THEN 'PSW1' " +
	"	 WHEN JBSCD = 212 THEN 'PSW2' " +
	// 2012-09-28 設備追加-end-
        "	 ELSE NVL(JBSM,SBSM) END " +
        "	) JBSM, " +
//        "	TRUNC(DECODE(D2.JBSM,NULL,DECODE(SBSM,'KEN',SH_RYOW,SH_SBW),JBRW)) JBRW, " + //2012-10-11 数値切り上げ
        "	TRUNC(CASE WHEN JBSM is NULL THEN DECODE(SBSM,'KEN',SH_RYOW,SH_SBW) WHEN JBSM = 'KEN' THEN JBRW - D5.MODW ELSE JBRW END) JBRW, " + //2015/10/5 修正
//        "	DECODE(D2.JBSM,NULL,SBMH,JBRMH) JBRMH, " +
//        "	JBRMH, " +
	"	TRUNC(CASE WHEN JBSM = 'KEN' THEN JBRMH - D5.MODN ELSE JBRMH END) JBRMH, " + //2015/10/5 修正
        "	DECODE(D2.JBSM,NULL,SBPAT,JBPAT) JBPAT, " +
        "	JBEDTM, " +
        "	JBEDTC, " +
        "	DECODE(D2.JBSM,NULL, " +
        "	TO_CHAR(DECODE(JCD112,'0',NVL(SBMMDD,SBYMD),SBYMD),'Y,MM,DD') || DECODE(DECODE(JCD112,'0',NVL(SBMMDD,SBYMD),SBYMD),NULL,NULL,'.' || DECODE(JCD112,'0',DECODE(SBMMDDC,0,SBYMDC,SBMMDDC),SBYMDC)), " +
        "	TO_CHAR(JBEDTM,'Y,MM,DD') || '.' || JBEDTC " +
        "	) ENDDT, " +
        "	DECODE(D2.JBSM,NULL,SBX,JBX) JBX, " +
        "	DECODE(D2.JBSM,NULL,SBY,JBY) JBY, " +
        "	DECODE(D2.JBSM,NULL,SBZ,JBZ) JBZ, " +
        "	DECODE(D2.JBSM,NULL,TO_NUMBER(NULL),JBBN) JBBN, " +
	"	D3.CYUZO_YY " +
        "FROM " +
	"	DAT_R_JDF_COM_SBOX D1, " +
	"	DAT_R_LOT_COM_JBOX D2, " +
	"	DAT_R_JDF_COM D3, " +
	"	DAT_R_LOT_COM D4, " +
	"	DAT_R_LOT_COM_KINS D5 " +
	"WHERE " +
	"	D1.LINKKEY = D2.LINKKEY(+) " +
	"AND	D1.BOXNO = D2.BOXNO(+) " +
	"AND	D1.LINKKEY = D3.LINKKEY " +
	"AND	D1.LINKKEY = D4.LINKKEY(+) " +
	"AND	D3.JYOTAI = 2 " +
	"AND	D1.LINKKEY = D5.LINKKEY(+) " +
	// 2014/09/25 追加 ▼
	"UNION ALL " +
	"SELECT " +
		"	'4' TBLORDER, " +
        "	D1.LINKKEY, " +
        "	D1.BOXNO, " +
        "	SBK, " +
        "	SBYMD, " +
        "	SBMMDD, " +
        "	SBMMDDC, " +
        "	TO_CHAR(DECODE(JCD112,'0',NVL(SBMMDD,SBYMD),SBYMD),'MM,DD') || DECODE(DECODE(JCD112,'0',NVL(SBMMDD,SBYMD),SBYMD),NULL,NULL,'.' || DECODE(JCD112,'0',DECODE(SBMMDDC,0,SBYMDC,SBMMDDC),SBYMDC)) STARTDT, " +
        "	SBSM, " +
        "	SBX, " +
        "	SBY, " +
        "	SBZ, " +
        "       '' SBPAT, " +
        "	SBMH, " +
        "	SH_SBW, " +
        "	TRUNC(DECODE(SBSM,'KEN',SH_RYOW,SH_SBW)) SBW, " +
        "	DECODE(JBSM,NULL,'S','J') PROC_KBN, " +
        "	A11, " +
        "	(CASE " +
	"	 WHEN JBSM = 'VC' THEN DECODE(SUBSTR(JBPAT,3,1),'1','HVC','2','LVC','3','SVC','4','TVC','8','LSVC','9','FVC','VC') " +
        "	 WHEN JBSM IN('ANI','ANF') THEN JBSM || TRIM(TO_CHAR(SUBSTR(TO_CHAR(JBSCD,'000'),3,2),'99')) " +
        "	 WHEN JBSCD IN(330,331) THEN 'QF1' " +
        "	 WHEN JBSCD = 332 THEN 'QF2' " +
	"	 WHEN JBSCD = 211 THEN 'PSW1' " +
	"	 WHEN JBSCD = 212 THEN 'PSW2' " +
        "	 ELSE NVL(JBSM,SBSM) END " +
        "	) JBSM, " +
//        "	TRUNC(DECODE(D2.JBSM,NULL,DECODE(SBSM,'KEN',SH_RYOW,SH_SBW),JBRW)) JBRW, " +
        "	TRUNC(CASE WHEN JBSM is NULL THEN DECODE(SBSM,'KEN',SH_RYOW,SH_SBW) WHEN JBSM = 'KEN' THEN JBRW - D5.MODW ELSE JBRW END) JBRW, " +  //2015/10/5 修正
//        "	JBRMH, " +
	"	TRUNC(CASE WHEN JBSM = 'KEN' THEN JBRMH - D5.MODN ELSE JBRMH END) JBRMH, " + //2015/10/5 修正
        "	DECODE(D2.JBSM,NULL,SBPAT,JBPAT) JBPAT, " +
        "	JBEDTM, " +
        "	JBEDTC, " +
        "	DECODE(D2.JBSM,NULL, " +
        "	TO_CHAR(DECODE(JCD112,'0',NVL(SBMMDD,SBYMD),SBYMD),'Y,MM,DD') || DECODE(DECODE(JCD112,'0',NVL(SBMMDD,SBYMD),SBYMD),NULL,NULL,'.' || DECODE(JCD112,'0',DECODE(SBMMDDC,0,SBYMDC,SBMMDDC),SBYMDC)), " +
        "	TO_CHAR(JBEDTM,'Y,MM,DD') || '.' || JBEDTC " +
        "	) ENDDT, " +
        "	DECODE(D2.JBSM,NULL,SBX,JBX) JBX, " +
        "	DECODE(D2.JBSM,NULL,SBY,JBY) JBY, " +
        "	DECODE(D2.JBSM,NULL,SBZ,JBZ) JBZ, " +
        "	DECODE(D2.JBSM,NULL,TO_NUMBER(NULL),JBBN) JBBN, " +
        "       D3.CYUZO_YY " +
        "FROM " +
	"	DAT_S_JDF_COM_SBOX D1, " +
	"	DAT_S_LOT_COM_JBOX D2, " +
	"	DAT_S_JDF_COM D3, " +
	"	DAT_S_LOT_COM D4, " +
	"	DAT_S_LOT_COM_KINS D5 " +
	"WHERE " +
	"	D1.LINKKEY = D2.LINKKEY(+) " +
	"AND	D1.BOXNO = D2.BOXNO(+) " +
	"AND	D1.TOROKUDATE = D2.TOROKUDATE(+) " +
	"AND	D1.LINKKEY = D3.LINKKEY " +
	"AND	D1.TOROKUDATE = D3.TOROKUDATE(+) " +
	"AND	D1.LINKKEY = D4.LINKKEY(+) " +
	"AND	D1.TOROKUDATE = D4.TOROKUDATE(+) " +
	"AND	D1.LINKKEY = D5.LINKKEY(+) " +
	"AND	D1.TOROKUDATE = D5.TOROKUDATE(+) " +
	// 2014/09/25 追加 ▲
	// 2016/04/24 追加 ▼
		"UNION ALL " +
	"SELECT " +
		"	'5' TBLORDER, " +
        "	D1.LINKKEY, " +
        "	D1.BOXNO, " +
        "	SBK, " +
        "	SBYMD, " +
        "	SBMMDD, " +
        "	SBMMDDC, " +
        "	TO_CHAR(DECODE(JCD112,'0',NVL(SBMMDD,SBYMD),SBYMD),'MM,DD') || DECODE(DECODE(JCD112,'0',NVL(SBMMDD,SBYMD),SBYMD),NULL,NULL,'.' || DECODE(JCD112,'0',DECODE(SBMMDDC,0,SBYMDC,SBMMDDC),SBYMDC)) STARTDT, " +
        "	SBSM, " +
        "	SBX, " +
        "	SBY, " +
        "	SBZ, " +
        "       '' SBPAT, " +
        "	SBMH, " +
        "	SH_SBW, " +
        "	TRUNC(DECODE(SBSM,'KEN',SH_RYOW,SH_SBW)) SBW, " +
        "	DECODE(JBSM,NULL,'S','J') PROC_KBN, " +
        "	A11, " +
        "	(CASE " +
	"	 WHEN JBSM = 'VC' THEN DECODE(SUBSTR(JBPAT,3,1),'1','HVC','2','LVC','3','SVC','4','TVC','8','LSVC','9','FVC','VC') " +
        "	 WHEN JBSM IN('ANI','ANF') THEN JBSM || TRIM(TO_CHAR(SUBSTR(TO_CHAR(JBSCD,'000'),3,2),'99')) " +
        "	 WHEN JBSCD IN(330,331) THEN 'QF1' " +
        "	 WHEN JBSCD = 332 THEN 'QF2' " +
	"	 WHEN JBSCD = 211 THEN 'PSW1' " +
	"	 WHEN JBSCD = 212 THEN 'PSW2' " +
        "	 ELSE NVL(JBSM,SBSM) END " +
        "	) JBSM, " +
        "	TRUNC(CASE WHEN JBSM is NULL THEN DECODE(SBSM,'KEN',SH_RYOW,SH_SBW) WHEN JBSM = 'KEN' THEN JBRW - D5.MODW ELSE JBRW END) JBRW, " +
	"	TRUNC(CASE WHEN JBSM = 'KEN' THEN JBRMH - D5.MODN ELSE JBRMH END) JBRMH, " +
        "	DECODE(D2.JBSM,NULL,SBPAT,JBPAT) JBPAT, " +
        "	JBEDTM, " +
        "	JBEDTC, " +
        "	DECODE(D2.JBSM,NULL, " +
        "	TO_CHAR(DECODE(JCD112,'0',NVL(SBMMDD,SBYMD),SBYMD),'Y,MM,DD') || DECODE(DECODE(JCD112,'0',NVL(SBMMDD,SBYMD),SBYMD),NULL,NULL,'.' || DECODE(JCD112,'0',DECODE(SBMMDDC,0,SBYMDC,SBMMDDC),SBYMDC)), " +
        "	TO_CHAR(JBEDTM,'Y,MM,DD') || '.' || JBEDTC " +
        "	) ENDDT, " +
        "	DECODE(D2.JBSM,NULL,SBX,JBX) JBX, " +
        "	DECODE(D2.JBSM,NULL,SBY,JBY) JBY, " +
        "	DECODE(D2.JBSM,NULL,SBZ,JBZ) JBZ, " +
        "	DECODE(D2.JBSM,NULL,TO_NUMBER(NULL),JBBN) JBBN, " +
        "       D3.CYUZO_YY " +
        "FROM " +
	"	DAT_L_JDF_COM_SBOX D1, " +
	"	DAT_L_LOT_COM_JBOX D2, " +
	"	DAT_L_JDF_COM D3, " +
	"	DAT_L_LOT_COM D4, " +
	"	DAT_L_LOT_COM_KINS D5 " +
	"WHERE " +
	"	D1.LINKKEY = D2.LINKKEY(+) " +
	"AND	D1.BOXNO = D2.BOXNO(+) " +
	"AND	TRUNC(D1.TOROKUDATE) = TRUNC(D2.TOROKUDATE(+)) " +
	"AND	D1.LINKKEY = D3.LINKKEY " +
	"AND	TRUNC(D1.TOROKUDATE) = TRUNC(D3.TOROKUDATE(+)) " +
	"AND	D1.LINKKEY = D4.LINKKEY(+) " +
	"AND	TRUNC(D1.TOROKUDATE) = TRUNC(D4.TOROKUDATE(+)) " +
	"AND	D1.LINKKEY = D5.LINKKEY(+) " +
	"AND	TRUNC(D1.TOROKUDATE) = TRUNC(D5.TOROKUDATE(+)) " +
	// 2016/04/24 追加 ▲

        ") " +
        "WHERE " +
	"       LINKKEY = ? " +
	"       AND CYUZO_YY = ? " +
	"       AND TBLORDER = ? " +
        "ORDER BY " +
	"       BOXNO";


    //TEST_SQL
    private static final String TEST_QUERY =
        "select * from " +
        "(" +
        " select d1.linkkey linkkey, to_date(null) ksd, ltno, cyno, knno, juno, jbcd, bucode, d1.yotoc yotoc, yotoname, jua, jub, jux, juy, juz," +
        " lta, ltb, ltx, lty, ltz, skigo, jup, tanw, hijyu, sw, substr(noki3_renban, 2, 3) noki3_renban, nokiymd, m2.tnname tokui," +
        " oname, m3.tnname nonyu, chikucd, jujsno, enoki, knoki, jcdtm, jcw, jswdtm, jsww, jmdtm, jmw, jpdt, jprb, jprj, edtm" +
        " from DAT_JDF_COM d1, DAT_LOT_COM d2, DAT_LOT_FIX d3, MAS_YOTO m1, MAS_TORINO m2, MAS_TORINO m3, MAS_OKURI m4" +
        " where d1.linkkey = d2.linkkey" +
        " and d1.linkkey = d3.linkkey" +
        " and d1.masyotoc = m1.yotoc(+)" +
        " and d1.tokui = m2.tncd(+)" +
        " and d1.nonyu = m3.tncd(+)" +
        " and d1.on3n = m4.okucd(+)" +
        " and m2.kbn in ('1', '2')" +
        " and m3.kbn in ('1', '3')" +
	" and d1.jyotai = 1" +
        " UNION ALL" +
        " select d1.linkkey linkkey, ksd, ltno, cyno, knno, juno, jbcd, bucode, d1.yotoc yotoc, yotoname, jua, jub, jux, juy, juz," +
        " lta, ltb, ltx, lty, ltz, skigo, jup, tanw, hijyu, sw, substr(noki3_renban, 2, 3) noki3_renban, nokiymd, m2.tnname tokui," +
        " oname, m3.tnname nonyu, chikucd, jujsno, enoki, knoki, jcdtm, jcw, jswdtm, jsww, jmdtm, jmw, jpdt, jprb, jprj, edtm" +
        " from DAT_R_JDF_COM d1, DAT_R_LOT_COM d2, DAT_R_LOT_FIX d3, MAS_YOTO m1, MAS_TORINO m2, MAS_TORINO m3, MAS_OKURI m4" +
        " where d1.linkkey = d2.linkkey" +
        " and d1.linkkey = d3.linkkey" +
        " and d1.masyotoc = m1.yotoc(+)" +
        " and d1.tokui = m2.tncd(+)" +
        " and d1.nonyu = m3.tncd(+)" +
        " and d1.on3n = m4.okucd(+)" +
        " and m2.kbn in ('1', '2')" +
        " and m3.kbn in ('1', '3')" +
	" and d1.jyotai = 2" +
	// 2014/09/30 追加 ▼
        " UNION ALL" +
        " select d1.linkkey linkkey, ksd, ltno, cyno, knno, juno, jbcd, bucode, d1.yotoc yotoc, yotoname, jua, jub, jux, juy, juz," +
        " lta, ltb, ltx, lty, ltz, skigo, jup, tanw, hijyu, sw, substr(noki3_renban, 2, 3) noki3_renban, nokiymd, m2.tnname tokui," +
        " oname, m3.tnname nonyu, chikucd, jujsno, enoki, knoki, jcdtm, jcw, jswdtm, jsww, jmdtm, jmw, jpdt, jprb, jprj, edtm" +
        " from DAT_S_JDF_COM d1, DAT_S_LOT_COM d2, DAT_S_LOT_FIX d3, MAS_YOTO m1, MAS_TORINO m2, MAS_TORINO m3, MAS_OKURI m4" +
        " where d1.linkkey = d2.linkkey" +
        " and d1.torokudate = d2.torokudate" +
	" and d1.linkkey = d3.linkkey" +
        " and d1.torokudate = d3.torokudate" +
        " and d1.masyotoc = m1.yotoc(+)" +
        " and d1.tokui = m2.tncd(+)" +
        " and d1.nonyu = m3.tncd(+)" +
        " and d1.on3n = m4.okucd(+)" +
        " and m2.kbn in ('1', '2')" +
        " and m3.kbn in ('1', '3')" +
	// 2014/09/30 追加 ▲
	// 2016/04/24 追加 ▼
        " UNION ALL" +
        " select d1.linkkey linkkey, ksd, ltno, cyno, knno, juno, jbcd, bucode, d1.yotoc yotoc, yotoname, jua, jub, jux, juy, juz," +
        " lta, ltb, ltx, lty, ltz, skigo, jup, tanw, hijyu, sw, substr(noki3_renban, 2, 3) noki3_renban, nokiymd, m2.tnname tokui," +
        " oname, m3.tnname nonyu, chikucd, jujsno, enoki, knoki, jcdtm, jcw, jswdtm, jsww, jmdtm, jmw, jpdt, jprb, jprj, edtm" +
        " from DAT_L_JDF_COM d1, DAT_L_LOT_COM d2, DAT_L_LOT_FIX d3, MAS_YOTO m1, MAS_TORINO m2, MAS_TORINO m3, MAS_OKURI m4" +
        " where d1.linkkey = d2.linkkey" +
        " and TRUNC(d1.torokudate) = TRUNC(d2.torokudate)" +
	" and d1.linkkey = d3.linkkey" +
        " and TRUNC(d1.torokudate) = TRUNC(d3.torokudate)" +
        " and d1.masyotoc = m1.yotoc(+)" +
        " and d1.tokui = m2.tncd(+)" +
        " and d1.nonyu = m3.tncd(+)" +
        " and d1.on3n = m4.okucd(+)" +
        " and m2.kbn in ('1', '2')" +
        " and m3.kbn in ('1', '3')" +
	" and d1.jyotai = 3" +
	// 2016/04/24 追加 ▲
        " )" +
        " where lta = ?" +
        " and ksd >= to_date(?)" +
        " and ksd <= to_date(?)" +
        " order by ltno asc";

    //TEST_SQL
    private static final String LTNO_CBO_QUERY =
        "select distinct ltno from " +
        "(" +
        " select ltno, lta, to_date(null) ksd from DAT_JDF_COM d1, DAT_LOT_COM d2, DAT_LOT_FIX d3 " +
        " where d1.linkkey = d2.linkkey" +
        " and d1.linkkey = d3.linkkey" +
	" and d1.jyotai = 1" +
        " union all" +
        " select ltno, lta, ksd from DAT_R_JDF_COM d1, DAT_R_LOT_COM d2, DAT_R_LOT_FIX d3 " +
        " where d1.linkkey = d2.linkkey" +
        " and d1.linkkey = d3.linkkey" +
	" and d1.jyotai = 2" +
	// 2014/09/30 追加 ▼
        " union all" +
        " select ltno, lta, ksd from DAT_S_JDF_COM d1, DAT_S_LOT_COM d2, DAT_S_LOT_FIX d3 " +
        " where d1.linkkey = d2.linkkey" +
        " and d1.torokudate = d2.torokudate" +
	" and d1.linkkey = d3.linkkey" +
        " and d1.torokudate = d3.torokudate" +
	// 2014/09/30 追加 ▲
	// 2016/04/24 追加 ▼
        " union all" +
        " select ltno, lta, ksd from DAT_L_JDF_COM d1, DAT_L_LOT_COM d2, DAT_L_LOT_FIX d3 " +
        " where d1.linkkey = d2.linkkey" +
        " and TRUNC(d1.torokudate) = TRUNC(d2.torokudate)" +
	" and d1.linkkey = d3.linkkey" +
        " and TRUNC(d1.torokudate) = TRUNC(d3.torokudate)" +
	// 2016/04/24 追加 ▲
        " )" +
        " where lta = ?" +
        " and ksd >= to_date(?)" +
        " and ksd <= to_date(?)" +
        " order by ltno asc";


    private static final String CYNO_CBO_QUERY =
        "select distinct cyno from " +
        "(" +
        " select cyno, lta, to_date(null) ksd from DAT_JDF_COM d1, DAT_LOT_COM d2, DAT_LOT_FIX d3 " +
        " where d1.linkkey = d2.linkkey" +
        " and d1.linkkey = d3.linkkey" +
	" and d1.jyotai = 1" +
        " union all" +
        " select cyno, lta, ksd from DAT_R_JDF_COM d1, DAT_R_LOT_COM d2, DAT_R_LOT_FIX d3 " +
        " where d1.linkkey = d2.linkkey" +
        " and d1.linkkey = d3.linkkey" +
	" and d1.jyotai = 2" +
	// 2014/09/30 追加 ▼
        " union all" +
        " select cyno, lta, ksd from DAT_S_JDF_COM d1, DAT_R_LOT_COM d2, DAT_S_LOT_FIX d3 " +
        " where d1.linkkey = d2.linkkey" +
        " and d1.torokudate = d2.torokudate" +
        " and d1.linkkey = d3.linkkey" +
        " and d1.torokudate = d3.torokudate" +
	// 2014/09/30 追加 ▲
	// 2016/04/24 追加 ▼
        " union all" +
        " select cyno, lta, ksd from DAT_L_JDF_COM d1, DAT_L_LOT_COM d2, DAT_L_LOT_FIX d3 " +
        " where d1.linkkey = d2.linkkey" +
        " and TRUNC(d1.torokudate) = TRUNC(d2.torokudate)" +
        " and d1.linkkey = d3.linkkey" +
        " and TRUNC(d1.torokudate) = TRUNC(d3.torokudate)" +
	// 2016/04/24 追加 ▲
        " )" +
        " where lta = ?" +
        " and ksd >= to_date(?)" +
        " and ksd <= to_date(?)" +
        " order by cyno asc";


    private static final String KNNO_CBO_QUERY =
        "select distinct knno from " +
        "(" +
        " select knno, lta, to_date(null) ksd from DAT_JDF_COM d1, DAT_LOT_COM d2, DAT_LOT_FIX d3 " +
        " where d1.linkkey = d2.linkkey" +
        " and d1.linkkey = d3.linkkey" +
	" and d1.jyotai = 1" +
        " union all" +
        " select knno, lta, ksd from DAT_R_JDF_COM d1, DAT_R_LOT_COM d2, DAT_R_LOT_FIX d3 " +
        " where d1.linkkey = d2.linkkey" +
        " and d1.linkkey = d3.linkkey" +
	" and d1.jyotai = 2" +
	// 2014/09/30 追加 ▼
	" union all" +
        " select knno, lta, ksd from DAT_S_JDF_COM d1, DAT_S_LOT_COM d2, DAT_S_LOT_FIX d3 " +
        " where d1.linkkey = d2.linkkey" +
        " and d1.torokudate = d2.torokudate" +
        " and d1.linkkey = d3.linkkey" +
        " and d1.torokudate = d3.torokudate" +
	// 2014/09/30 追加 ▲
	// 2016/04/24 追加 ▼
	" union all" +
        " select knno, lta, ksd from DAT_L_JDF_COM d1, DAT_L_LOT_COM d2, DAT_L_LOT_FIX d3 " +
        " where d1.linkkey = d2.linkkey" +
        " and TRUNC(d1.torokudate) = TRUNC(d2.torokudate)" +
        " and d1.linkkey = d3.linkkey" +
        " and TRUNC(d1.torokudate) = TRUNC(d3.torokudate)" +
	// 2016/04/24 追加 ▲
        " )" +
        " where lta = ?" +
        " and ksd >= to_date(?)" +
        " and ksd <= to_date(?)" +
        " order by knno asc";


    private static final String JUA_CBO_QUERY =
        "select distinct jua from " +
        "(" +
        " select jua, lta, to_date(null) ksd from DAT_JDF_COM d1, DAT_LOT_COM d2, DAT_LOT_FIX d3 " +
        " where d1.linkkey = d2.linkkey" +
        " and d1.linkkey = d3.linkkey" +
	" and d1.jyotai = 1" +
        " union all" +
        " select jua, lta, ksd from DAT_R_JDF_COM d1, DAT_R_LOT_COM d2, DAT_R_LOT_FIX d3 " +
        " where d1.linkkey = d2.linkkey" +
        " and d1.linkkey = d3.linkkey" +
	" and d1.jyotai = 2" +
	// 2014/09/30 追加 ▼
        " union all" +
        " select jua, lta, ksd from DAT_S_JDF_COM d1, DAT_S_LOT_COM d2, DAT_S_LOT_FIX d3 " +
        " where d1.linkkey = d2.linkkey" +
        " and d1.torokudate = d2.torokudate" +
        " and d1.linkkey = d3.linkkey" +
        " and d1.torokudate = d3.torokudate" +
	// 2014/09/30 追加 ▲
	// 2016/04/24 追加 ▼
        " union all" +
        " select jua, lta, ksd from DAT_L_JDF_COM d1, DAT_L_LOT_COM d2, DAT_L_LOT_FIX d3 " +
        " where d1.linkkey = d2.linkkey" +
        " and TRUNC(d1.torokudate) = TRUNC(d2.torokudate)" +
        " and d1.linkkey = d3.linkkey" +
        " and TRUNC(d1.torokudate) = TRUNC(d3.torokudate)" +
	// 2016/04/24 追加 ▲
        " )" +
        " where lta = ?" +
        " and ksd >= to_date(?)" +
        " and ksd <= to_date(?)" +
        " order by jua asc";


    private static final String LTX_CBO_QUERY =
        "select distinct ltx from " +
        "(" +
        " select ltx, lta, to_date(null) ksd from DAT_JDF_COM d1, DAT_LOT_COM d2, DAT_LOT_FIX d3 " +
        " where d1.linkkey = d2.linkkey" +
        " and d1.linkkey = d3.linkkey" +
	" and d1.jyotai = 1 " +
        " union all" +
        " select ltx, lta, ksd from DAT_R_JDF_COM d1, DAT_R_LOT_COM d2, DAT_R_LOT_FIX d3 " +
        " where d1.linkkey = d2.linkkey" +
        " and d1.linkkey = d3.linkkey" +
	" and d1.jyotai = 2 " +
	// 2014/09/30 追加 ▼
        " union all" +
        " select ltx, lta, ksd from DAT_S_JDF_COM d1, DAT_S_LOT_COM d2, DAT_S_LOT_FIX d3 " +
        " where d1.linkkey = d2.linkkey" +
        " and d1.torokudate = d2.torokudate" +
        " and d1.linkkey = d3.linkkey" +
        " and d1.torokudate = d3.torokudate" +
	// 2014/09/30 追加 ▲
	// 2016/04/24 追加 ▼
        " union all" +
        " select ltx, lta, ksd from DAT_L_JDF_COM d1, DAT_L_LOT_COM d2, DAT_L_LOT_FIX d3 " +
        " where d1.linkkey = d2.linkkey" +
        " and TRUNC(d1.torokudate) = TRUNC(d2.torokudate)" +
        " and d1.linkkey = d3.linkkey" +
        " and TRUNC(d1.torokudate) = TRUNC(d3.torokudate)" +
	// 2016/04/24 追加 ▲
        " )" +
        " where lta = ?" +
        " and ksd >= to_date(?)" +
        " and ksd <= to_date(?)" +
        " order by ltx asc";


    /**
     * クラッド芯材情報
     * 2011/07/05 add
     */
    public static String getCladShinzaiQuery() {
        StringBuilder bld = new StringBuilder();

        bld.append(" SELECT * ");
        bld.append(" FROM ( ");
        bld.append(    " SELECT ");
        bld.append(        " '1' TBLORDER ");
        bld.append(      " , KR_PAT ");
        bld.append(      " , H_MENS ");
        bld.append(      " , JMW ");
        bld.append(      " , JMX ");
        bld.append(      " , JMDTM ");
        bld.append(      " , RPAD(PITJ, 2, ' ') PITJ ");
        bld.append(      " , PIT ");
        bld.append(      " , PRJ ");
        bld.append(      " , PITBI ");
        bld.append(      " , RPAD(JPJ, 2, ' ') JPJ ");
        bld.append(      " , JPRB ");
        bld.append(      " , JPRJ ");
        bld.append(      " , JPDT ");
        bld.append(      " , D1.LINKKEY ");
        bld.append(    " FROM ");
        bld.append(        " DAT_JDF_COM D1 ");
        bld.append(      " , DAT_LOT_COM D2 ");
        bld.append(      " , DAT_LOT_COM_HOT D3 ");
        bld.append(      " , DAT_LOT_COM_CM D4 ");
        bld.append(      " , DAT_LOT_FIX D5 ");
        bld.append(      " , DAT_LOT_FIXEX D6 ");
        bld.append(      " , DAT_LOT_COM_JBOX D7 ");
        bld.append(      " , DAT_LOT_COM_JBOX D8 ");
        bld.append(      " , DAT_LOT_COM_JBOX D9 ");
        bld.append(      " , DAT_LOT_COM_JBOXEX D10 ");
        bld.append(      " , DAT_LOT_COM_JBOXEX D11 ");
        bld.append(      " , DAT_LOT_COM_JBOXEX D12 ");
        bld.append(      " , DAT_LOT_COM_JBOXEX D13 ");
        bld.append(      " , DAT_LOT_COM_JBOXEX D14 ");
        bld.append(      " , DAT_LOT_COM_JBOXEX D15 ");
        bld.append(      " , DAT_LOT_COM_JBOXEX D16 ");
        bld.append(      " , ( SELECT ");
        bld.append(              " LINKKEY ");
        bld.append(            " , MAX(BOXNO) BOXNO ");
        bld.append(          " FROM ");
        bld.append(              " DAT_LOT_COM_JBOX ");
        bld.append(          " WHERE ");
        bld.append(              " JBSM = 'QF' ");
        bld.append(          " GROUP BY ");
        bld.append(              " LINKKEY ");
        bld.append(            " , BOXNO ) D17 ");
        bld.append(    " WHERE ");
        bld.append(        " D1.LINKKEY = D2.LINKKEY ");
        bld.append(    " AND ");
        bld.append(        " D1.LINKKEY = D3.LINKKEY(+) ");
        bld.append(    " AND ");
        bld.append(        " D6.LINKKEY = D4.LINKKEY(+) ");
        bld.append(    " AND ");
        bld.append(        " D1.LINKKEY = D5.LINKKEY ");
        bld.append(    " AND ");
        bld.append(        " D1.LINKKEY = D6.LINKKEY ");
        bld.append(    " AND ");
        bld.append(        " D6.CMJ_BOXNO = D4.BOXNO(+) ");
        bld.append(    " AND ");
        bld.append(        " D6.LINKKEY = D7.LINKKEY(+) ");
        bld.append(    " AND ");
        bld.append(        " D6.HOT_BOXNO = D7.BOXNO(+) ");
        bld.append(    " AND ");
        bld.append(        " D6.LINKKEY = D8.LINKKEY(+) ");
        bld.append(    " AND ");
        bld.append(        " D6.CMJ_BOXNO = D8.BOXNO(+) ");
        bld.append(    " AND ");
        bld.append(        " D6.LINKKEY = D9.LINKKEY(+) ");
        bld.append(    " AND ");
        bld.append(        " D6.SIAGEJ_BOXNO = D9.BOXNO(+) ");
        bld.append(    " AND ");
        bld.append(        " D6.LINKKEY = D10.LINKKEY(+) ");
        bld.append(    " AND ");
        bld.append(        " D6.CMJ_BOXNO = D10.BOXNO(+) ");
        bld.append(    " AND ");
        bld.append(        " D6.LINKKEY = D11.LINKKEY(+) ");
        bld.append(    " AND ");
        bld.append(        " D6.SIAGEJ_BOXNO = D11.BOXNO(+) ");
        bld.append(    " AND ");
        bld.append(        " D6.LINKKEY = D12.LINKKEY(+) ");
        bld.append(    " AND ");
        bld.append(        " D6.ANI_JBOXNO = D12.BOXNO(+) ");
        bld.append(    " AND ");
        bld.append(        " D6.LINKKEY = D13.LINKKEY(+) ");
        bld.append(    " AND ");
        bld.append(        " D6.CAI_JBOXNO = D13.BOXNO(+) ");
        bld.append(    " AND ");
        bld.append(        " D6.LINKKEY = D14.LINKKEY(+) ");
        bld.append(    " AND ");
        bld.append(        " D6.ANF_JBOXNO = D14.BOXNO(+) ");
        bld.append(    " AND ");
        bld.append(        " D6.LINKKEY = D15.LINKKEY(+) ");
        bld.append(    " AND ");
        bld.append(        " D6.CAF_JBOXNO = D15.BOXNO(+) ");
        bld.append(    " AND ");
        bld.append(        " D1.LINKKEY = D17.LINKKEY(+) ");
        bld.append(    " AND ");
        bld.append(        " D17.LINKKEY = D16.LINKKEY(+) ");
        bld.append(    " AND ");
        bld.append(        " D17.BOXNO = D16.BOXNO(+) ");
        bld.append(    " AND ");
        bld.append(        " D1.JYOTAI = 1 ");
        bld.append(  " UNION ALL ");

		// ▼2017/10/27
        bld.append(    " SELECT ");
        bld.append(        " '2' TBLORDER ");
        bld.append(      " , KR_PAT ");
        bld.append(      " , H_MENS ");
        bld.append(      " , NULL ");
        bld.append(      " , NULL ");
        bld.append(      " , NULL ");
        bld.append(      " , RPAD(PITJ, 2, ' ') PITJ ");
        bld.append(      " , PIT ");
        bld.append(      " , PRJ ");
        bld.append(      " , PITBI ");
        bld.append(      " , NULL ");
        bld.append(      " , NULL ");
        bld.append(      " , NULL ");
        bld.append(      " , NULL ");
        bld.append(      " , D1.LINKKEY ");
        bld.append(    " FROM ");
        bld.append(        " DAT_JDF_COM2 D1 ");
        bld.append(      " , DAT_JDF_FIX D2 ");
        bld.append(      " , DAT_JDF_FIXEX D3 ");
        bld.append(    " WHERE ");
        bld.append(        " D1.LINKKEY = D2.LINKKEY(+) AND");
        bld.append(        " D1.LINKKEY = D3.LINKKEY(+) AND");
        bld.append(        " D1.JYOTAI = 0 ");
        bld.append(  " UNION ALL ");
		// ▲2017/10/27

        bld.append(    " SELECT ");
        bld.append(        " '2' TBLORDER ");
        bld.append(      " , D2.KR_PAT ");
        bld.append(      " , D2.H_MENS ");
        bld.append(      " , 0 JMW ");
        bld.append(      " , 0 JMX ");
        bld.append(      " , TO_DATE(NULL) JMDTM ");
        bld.append(      " , RPAD(D2.PITJ, 2, ' ') PITJ ");
        bld.append(      " , D2.PIT ");
        bld.append(      " , D2.PRJ ");
        bld.append(      " , D2.PITBI ");
        bld.append(      " , '  ' JPJ ");
        bld.append(      " , 0 JPRB ");
        bld.append(      " , 0 JPRJ ");
        bld.append(      " , TO_DATE(NULL) JPDT ");
        bld.append(      " , D1.LINKKEY ");
        bld.append(    " FROM ");
        bld.append(        " DAT_JDF_FIX D1 ");
        bld.append(      " , DAT_JDF_COM2 D2 ");
        bld.append(    " WHERE ");
        bld.append(        " D1.LINKKEY = D2.LINKKEY ");
        bld.append(  " UNION ALL ");
        bld.append(    " SELECT ");
        bld.append(      " '3' TBLORDER ");
        bld.append(      " , KR_PAT ");
        bld.append(      " , H_MENS ");
        bld.append(      " , JMW ");
        bld.append(      " , JMX ");
        bld.append(      " , JMDTM ");
        bld.append(      " , RPAD(PITJ, 2, ' ') PITJ ");
        bld.append(      " , PIT ");
        bld.append(      " , PRJ ");
        bld.append(      " , PITBI ");
        bld.append(      " , RPAD(JPJ, 2, ' ') JPJ ");
        bld.append(      " , JPRB ");
        bld.append(      " , JPRJ ");
        bld.append(      " , JPDT ");
        bld.append(      " , D1.LINKKEY ");
        bld.append(    " FROM ");
        bld.append(        " DAT_R_JDF_COM D1 ");
        bld.append(      " , DAT_R_LOT_COM D2 ");
        bld.append(      " , DAT_R_LOT_COM_HOT D3 ");
        bld.append(      " , DAT_R_LOT_COM_CM D4 ");
        bld.append(      " , DAT_R_LOT_FIX D5 ");
        bld.append(      " , DAT_R_LOT_FIXEX D6 ");
        bld.append(      " , DAT_R_LOT_COM_JBOX D7 ");
        bld.append(      " , DAT_R_LOT_COM_JBOX D8 ");
        bld.append(      " , DAT_R_LOT_COM_JBOX D9 ");
        bld.append(      " , DAT_R_LOT_COM_JBOXEX D10 ");
        bld.append(      " , DAT_R_LOT_COM_JBOXEX D11 ");
        bld.append(      " , DAT_R_LOT_COM_JBOXEX D12 ");
        bld.append(      " , DAT_R_LOT_COM_JBOXEX D13 ");
        bld.append(      " , DAT_R_LOT_COM_JBOXEX D14 ");
        bld.append(      " , DAT_R_LOT_COM_JBOXEX D15 ");
        bld.append(      " , DAT_R_LOT_COM_JBOXEX D16 ");
        bld.append(      " , ( SELECT ");
        bld.append(              " LINKKEY ");
        bld.append(            " , MAX(BOXNO) BOXNO ");
        bld.append(          " FROM ");
        bld.append(              " DAT_R_LOT_COM_JBOX ");
        bld.append(          " WHERE ");
        bld.append(              " JBSM = 'QF' ");
        bld.append(          " GROUP BY ");
        bld.append(              " LINKKEY ");
        bld.append(            " , BOXNO ) D17 ");
        bld.append(    " WHERE ");
        bld.append(        " D1.LINKKEY = D2.LINKKEY ");
        bld.append(    " AND ");
        bld.append(        " D1.LINKKEY = D3.LINKKEY(+) ");
        bld.append(    " AND ");
        bld.append(        " D6.LINKKEY = D4.LINKKEY(+) ");
        bld.append(    " AND ");
        bld.append(        " D1.LINKKEY = D5.LINKKEY ");
        bld.append(    " AND ");
        bld.append(        " D1.LINKKEY = D6.LINKKEY ");
        bld.append(    " AND ");
        bld.append(        " D6.CMJ_BOXNO = D4.BOXNO(+) ");
        bld.append(    " AND ");
        bld.append(        " D6.LINKKEY = D7.LINKKEY(+) ");
        bld.append(    " AND ");
        bld.append(        " D6.HOT_BOXNO = D7.BOXNO(+) ");
        bld.append(    " AND ");
        bld.append(        " D6.LINKKEY = D8.LINKKEY(+) ");
        bld.append(    " AND ");
        bld.append(        " D6.CMJ_BOXNO = D8.BOXNO(+) ");
        bld.append(    " AND ");
        bld.append(        " D6.LINKKEY = D9.LINKKEY(+) ");
        bld.append(    " AND ");
        bld.append(        " D6.SIAGEJ_BOXNO = D9.BOXNO(+) ");
        bld.append(    " AND ");
        bld.append(        " D6.LINKKEY = D10.LINKKEY(+) ");
        bld.append(    " AND ");
        bld.append(        " D6.CMJ_BOXNO = D10.BOXNO(+) ");
        bld.append(    " AND ");
        bld.append(        " D6.LINKKEY = D11.LINKKEY(+) ");
        bld.append(    " AND ");
        bld.append(        " D6.SIAGEJ_BOXNO = D11.BOXNO(+) ");
        bld.append(    " AND ");
        bld.append(        " D6.LINKKEY = D12.LINKKEY(+) ");
        bld.append(    " AND ");
        bld.append(        " D6.ANI_JBOXNO = D12.BOXNO(+) ");
        bld.append(    " AND ");
        bld.append(        " D6.LINKKEY = D13.LINKKEY(+) ");
        bld.append(    " AND ");
        bld.append(        " D6.CAI_JBOXNO = D13.BOXNO(+) ");
        bld.append(    " AND ");
        bld.append(        " D6.LINKKEY = D14.LINKKEY(+) ");
        bld.append(    " AND ");
        bld.append(        " D6.ANF_JBOXNO = D14.BOXNO(+) ");
        bld.append(    " AND ");
        bld.append(        " D6.LINKKEY = D15.LINKKEY(+) ");
        bld.append(    " AND ");
        bld.append(        " D6.CAF_JBOXNO = D15.BOXNO(+) ");
        bld.append(    " AND ");
        bld.append(        " D1.LINKKEY = D17.LINKKEY(+) ");
        bld.append(    " AND ");
        bld.append(        " D17.LINKKEY = D16.LINKKEY(+) ");
        bld.append(    " AND ");
        bld.append(        " D17.BOXNO = D16.BOXNO(+) ");
        bld.append(    " AND ");
        bld.append(        " D1.JYOTAI = 2 ");
	// 2014/09/25 追加 ▼
	bld.append(  " UNION ALL ");
        bld.append(    " SELECT ");
        bld.append(        " '4' TBLORDER ");
        bld.append(      " , KR_PAT ");
        bld.append(      " , H_MENS ");
        bld.append(      " , JMW ");
        bld.append(      " , JMX ");
        bld.append(      " , JMDTM ");
        bld.append(      " , RPAD(PITJ, 2, ' ') PITJ ");
        bld.append(      " , PIT ");
        bld.append(      " , PRJ ");
        bld.append(      " , PITBI ");
        bld.append(      " , RPAD(JPJ, 2, ' ') JPJ ");
        bld.append(      " , JPRB ");
        bld.append(      " , JPRJ ");
        bld.append(      " , JPDT ");
        bld.append(      " , D1.LINKKEY ");
        bld.append(    " FROM ");
        bld.append(        " DAT_S_JDF_COM D1 ");
        bld.append(      " , DAT_S_LOT_COM D2 ");
        bld.append(      " , DAT_S_LOT_COM_HOT D3 ");
        bld.append(      " , DAT_S_LOT_COM_CM D4 ");
        bld.append(      " , DAT_S_LOT_FIX D5 ");
        bld.append(      " , DAT_S_LOT_FIXEX D6 ");
        bld.append(      " , DAT_S_LOT_COM_JBOX D7 ");
        bld.append(      " , DAT_S_LOT_COM_JBOX D8 ");
        bld.append(      " , DAT_S_LOT_COM_JBOX D9 ");
        bld.append(      " , DAT_S_LOT_COM_JBOXEX D10 ");
        bld.append(      " , DAT_S_LOT_COM_JBOXEX D11 ");
        bld.append(      " , DAT_S_LOT_COM_JBOXEX D12 ");
        bld.append(      " , DAT_S_LOT_COM_JBOXEX D13 ");
        bld.append(      " , DAT_S_LOT_COM_JBOXEX D14 ");
        bld.append(      " , DAT_S_LOT_COM_JBOXEX D15 ");
        bld.append(      " , DAT_S_LOT_COM_JBOXEX D16 ");
        bld.append(      " , ( SELECT ");
        bld.append(              " LINKKEY ");
	bld.append(             ", TOROKUDATE ");
        bld.append(            " , MAX(BOXNO) BOXNO ");
        bld.append(          " FROM ");
        bld.append(              " DAT_S_LOT_COM_JBOX ");
        bld.append(          " WHERE ");
        bld.append(              " JBSM = 'QF' ");
        bld.append(          " GROUP BY ");
        bld.append(              " LINKKEY ");
	bld.append(             ", TOROKUDATE ");
        bld.append(            " , BOXNO ) D17 ");
        bld.append(    " WHERE ");
        bld.append(        " D1.LINKKEY = D2.LINKKEY ");
        bld.append(    " AND D1.TOROKUDATE = D2.TOROKUDATE(+) ");
        bld.append(    " AND D1.LINKKEY = D3.LINKKEY(+) ");
        bld.append(    " AND D1.TOROKUDATE = D3.TOROKUDATE(+) ");
        bld.append(    " AND D6.LINKKEY = D4.LINKKEY(+) ");
        bld.append(    " AND D6.TOROKUDATE = D4.TOROKUDATE(+) ");
        bld.append(    " AND D1.LINKKEY = D5.LINKKEY ");
        bld.append(    " AND D1.TOROKUDATE = D5.TOROKUDATE(+) ");
        bld.append(    " AND D1.LINKKEY = D6.LINKKEY ");
        bld.append(    " AND D1.TOROKUDATE = D6.TOROKUDATE(+) ");
        bld.append(    " AND D6.CMJ_BOXNO = D4.BOXNO(+) ");
        bld.append(    " AND D6.TOROKUDATE = D4.TOROKUDATE(+) ");
        bld.append(    " AND D6.LINKKEY = D7.LINKKEY(+) ");
	bld.append(    " AND D6.HOT_BOXNO = D7.BOXNO(+) ");
        bld.append(    " AND D6.TOROKUDATE = D7.TOROKUDATE(+) ");
        bld.append(    " AND D6.LINKKEY = D8.LINKKEY(+) ");
        bld.append(    " AND D6.TOROKUDATE = D8.TOROKUDATE(+) ");
	bld.append(    " AND D6.CMJ_BOXNO = D8.BOXNO(+) ");
        bld.append(    " AND D6.LINKKEY = D9.LINKKEY(+) ");
        bld.append(    " AND D6.TOROKUDATE = D9.TOROKUDATE(+) ");
	bld.append(    " AND D6.SIAGEJ_BOXNO = D9.BOXNO(+) ");
	bld.append(    " AND D6.LINKKEY = D10.LINKKEY(+) ");
        bld.append(    " AND D6.TOROKUDATE = D10.TOROKUDATE(+) ");
        bld.append(    " AND D6.CMJ_BOXNO = D10.BOXNO(+) ");
        bld.append(    " AND D6.LINKKEY = D11.LINKKEY(+) ");
        bld.append(    " AND D6.TOROKUDATE = D11.TOROKUDATE(+) ");
	bld.append(    " AND D6.SIAGEJ_BOXNO = D11.BOXNO(+) ");
        bld.append(    " AND D6.LINKKEY = D12.LINKKEY(+) ");
        bld.append(    " AND D6.TOROKUDATE = D12.TOROKUDATE(+) ");
	bld.append(    " AND D6.ANI_JBOXNO = D12.BOXNO(+) ");
        bld.append(    " AND D6.LINKKEY = D13.LINKKEY(+) ");
        bld.append(    " AND D6.TOROKUDATE = D13.TOROKUDATE(+) ");
	bld.append(    " AND D6.CAI_JBOXNO = D13.BOXNO(+) ");
	bld.append(    " AND D6.LINKKEY = D14.LINKKEY(+) ");
        bld.append(    " AND D6.TOROKUDATE = D14.TOROKUDATE(+) ");
        bld.append(    " AND D6.ANF_JBOXNO = D14.BOXNO(+) ");
	bld.append(    " AND D6.LINKKEY = D15.LINKKEY(+) ");
        bld.append(    " AND D6.TOROKUDATE = D15.TOROKUDATE(+) ");
        bld.append(    " AND D6.CAF_JBOXNO = D15.BOXNO(+) ");
	bld.append(    " AND D1.LINKKEY = D17.LINKKEY(+) ");
        bld.append(    " AND D1.TOROKUDATE = D17.TOROKUDATE(+) ");
	bld.append(    " AND D17.LINKKEY = D16.LINKKEY(+) ");
        bld.append(    " AND D17.TOROKUDATE = D16.TOROKUDATE(+) ");
        bld.append(    " AND D17.BOXNO = D16.BOXNO(+) ");
	// 2014/09/25 追加 ▲

	// 2016/04/24 追加 ▼
	bld.append(  " UNION ALL ");
        bld.append(    " SELECT ");
        bld.append(        " '5' TBLORDER ");
        bld.append(      " , KR_PAT ");
        bld.append(      " , H_MENS ");
        bld.append(      " , JMW ");
        bld.append(      " , JMX ");
        bld.append(      " , JMDTM ");
        bld.append(      " , RPAD(PITJ, 2, ' ') PITJ ");
        bld.append(      " , PIT ");
        bld.append(      " , PRJ ");
        bld.append(      " , PITBI ");
        bld.append(      " , RPAD(JPJ, 2, ' ') JPJ ");
        bld.append(      " , JPRB ");
        bld.append(      " , JPRJ ");
        bld.append(      " , JPDT ");
        bld.append(      " , D1.LINKKEY ");
        bld.append(    " FROM ");
        bld.append(        " DAT_L_JDF_COM D1 ");
        bld.append(      " , DAT_L_LOT_COM D2 ");
        bld.append(      " , DAT_L_LOT_COM_HOT D3 ");
        bld.append(      " , DAT_L_LOT_COM_CM D4 ");
        bld.append(      " , DAT_L_LOT_FIX D5 ");
        bld.append(      " , DAT_L_LOT_FIXEX D6 ");
        bld.append(      " , DAT_L_LOT_COM_JBOX D7 ");
        bld.append(      " , DAT_L_LOT_COM_JBOX D8 ");
        bld.append(      " , DAT_L_LOT_COM_JBOX D9 ");
        bld.append(      " , DAT_L_LOT_COM_JBOXEX D10 ");
        bld.append(      " , DAT_L_LOT_COM_JBOXEX D11 ");
        bld.append(      " , DAT_L_LOT_COM_JBOXEX D12 ");
        bld.append(      " , DAT_L_LOT_COM_JBOXEX D13 ");
        bld.append(      " , DAT_L_LOT_COM_JBOXEX D14 ");
        bld.append(      " , DAT_L_LOT_COM_JBOXEX D15 ");
        bld.append(      " , DAT_L_LOT_COM_JBOXEX D16 ");
        bld.append(      " , ( SELECT ");
        bld.append(              " LINKKEY ");
	bld.append(             ", TOROKUDATE ");
        bld.append(            " , MAX(BOXNO) BOXNO ");
        bld.append(          " FROM ");
        bld.append(              " DAT_L_LOT_COM_JBOX ");
        bld.append(          " WHERE ");
        bld.append(              " JBSM = 'QF' ");
        bld.append(          " GROUP BY ");
        bld.append(              " LINKKEY ");
	bld.append(             ", TOROKUDATE ");
        bld.append(            " , BOXNO ) D17 ");
        bld.append(    " WHERE ");
        bld.append(        " D1.LINKKEY = D2.LINKKEY ");
        bld.append(    " AND TRUNC(D1.TOROKUDATE) = TRUNC(D2.TOROKUDATE(+)) ");
        bld.append(    " AND D1.LINKKEY = D3.LINKKEY(+) ");
        bld.append(    " AND TRUNC(D1.TOROKUDATE) = TRUNC(D3.TOROKUDATE(+)) ");
        bld.append(    " AND D6.LINKKEY = D4.LINKKEY(+) ");
        bld.append(    " AND TRUNC(D6.TOROKUDATE) = TRUNC(D4.TOROKUDATE(+)) ");
        bld.append(    " AND D1.LINKKEY = D5.LINKKEY ");
        bld.append(    " AND TRUNC(D1.TOROKUDATE) = TRUNC(D5.TOROKUDATE(+)) ");
        bld.append(    " AND D1.LINKKEY = D6.LINKKEY ");
        bld.append(    " AND TRUNC(D1.TOROKUDATE) = TRUNC(D6.TOROKUDATE(+)) ");
        bld.append(    " AND D6.CMJ_BOXNO = D4.BOXNO(+) ");
        bld.append(    " AND TRUNC(D6.TOROKUDATE) = TRUNC(D4.TOROKUDATE(+)) ");
        bld.append(    " AND D6.LINKKEY = D7.LINKKEY(+) ");
	bld.append(    " AND D6.HOT_BOXNO = D7.BOXNO(+) ");
        bld.append(    " AND TRUNC(D6.TOROKUDATE) = TRUNC(D7.TOROKUDATE(+)) ");
        bld.append(    " AND D6.LINKKEY = D8.LINKKEY(+) ");
        bld.append(    " AND TRUNC(D6.TOROKUDATE) = TRUNC(D8.TOROKUDATE(+)) ");
	bld.append(    " AND D6.CMJ_BOXNO = D8.BOXNO(+) ");
        bld.append(    " AND D6.LINKKEY = D9.LINKKEY(+) ");
        bld.append(    " AND TRUNC(D6.TOROKUDATE) = TRUNC(D9.TOROKUDATE(+)) ");
	bld.append(    " AND D6.SIAGEJ_BOXNO = D9.BOXNO(+) ");
	bld.append(    " AND D6.LINKKEY = D10.LINKKEY(+) ");
        bld.append(    " AND TRUNC(D6.TOROKUDATE) = TRUNC(D10.TOROKUDATE(+)) ");
        bld.append(    " AND D6.CMJ_BOXNO = D10.BOXNO(+) ");
        bld.append(    " AND D6.LINKKEY = D11.LINKKEY(+) ");
        bld.append(    " AND TRUNC(D6.TOROKUDATE) = TRUNC(D11.TOROKUDATE(+)) ");
	bld.append(    " AND D6.SIAGEJ_BOXNO = D11.BOXNO(+) ");
        bld.append(    " AND D6.LINKKEY = D12.LINKKEY(+) ");
        bld.append(    " AND TRUNC(D6.TOROKUDATE) = TRUNC(D12.TOROKUDATE(+)) ");
	bld.append(    " AND D6.ANI_JBOXNO = D12.BOXNO(+) ");
        bld.append(    " AND D6.LINKKEY = D13.LINKKEY(+) ");
        bld.append(    " AND TRUNC(D6.TOROKUDATE) = TRUNC(D13.TOROKUDATE(+)) ");
	bld.append(    " AND D6.CAI_JBOXNO = D13.BOXNO(+) ");
	bld.append(    " AND D6.LINKKEY = D14.LINKKEY(+) ");
        bld.append(    " AND TRUNC(D6.TOROKUDATE) = TRUNC(D14.TOROKUDATE(+)) ");
        bld.append(    " AND D6.ANF_JBOXNO = D14.BOXNO(+) ");
	bld.append(    " AND D6.LINKKEY = D15.LINKKEY(+) ");
        bld.append(    " AND TRUNC(D6.TOROKUDATE) = TRUNC(D15.TOROKUDATE(+)) ");
        bld.append(    " AND D6.CAF_JBOXNO = D15.BOXNO(+) ");
	bld.append(    " AND D1.LINKKEY = D17.LINKKEY(+) ");
        bld.append(    " AND TRUNC(D1.TOROKUDATE) = TRUNC(D17.TOROKUDATE(+)) ");
	bld.append(    " AND D17.LINKKEY = D16.LINKKEY(+) ");
        bld.append(    " AND TRUNC(D17.TOROKUDATE) = TRUNC(D16.TOROKUDATE(+)) ");
        bld.append(    " AND D17.BOXNO = D16.BOXNO(+) ");
	// 2016/04/24 追加 ▲

	bld.append(    " ) ");
        bld.append(" WHERE ");
        bld.append(    " LINKKEY = ? ");
        bld.append(    " AND TBLORDER = ? ");

        return bld.toString();
    }

    /**
     * クラッド皮材情報
     * 2011/07/05 add
     */
    public static String getCladKawazaiQuery() {

        StringBuilder bld = new StringBuilder();
        bld.append(" SELECT * ");
        bld.append(" FROM ( ");
        bld.append(" SELECT ");
        bld.append(" '1' TBLORDER ");
        bld.append(" , Z1.LINKKEY ");
        bld.append(" , J1.KR_CYNO1 KR_CYNO ");
        bld.append(" , S1.A LTA");
        bld.append(" , RPAD(L1.JPJ, 2, ' ') JPJ ");
        bld.append(" , L1.JPRB ");
        bld.append(" , L1.JPRJ ");
        bld.append(" , L1.JPDT ");
        bld.append(" , L1.JPDT2 ");
        bld.append(" , L1.JPO2 ");
        bld.append(" , L1.JPDT3 ");
        bld.append(" , L1.JPO3 ");
        bld.append(" , L1.JPDT4 ");
        bld.append(" FROM ");
        bld.append(" DAT_JDF_COM J1 ");
        bld.append(" , DAT_LOT_FIX L1 ");
        bld.append(" , DAT_JDF_COM Z1 ");
        bld.append(" , (SELECT * FROM DAT_SLB_COM2 UNION ALL SELECT * FROM DAT_R_SLB_COM2) S1");
        bld.append(" WHERE ");
        bld.append(" J1.KR_CYNO1 = Z1.CYNO(+)");
        bld.append(" AND J1.CYUZO_YY BETWEEN NVL(Z1.CYUZO_YY,1) -1 AND NVL(Z1.CYUZO_YY,9998) + 1");
        bld.append(" AND J1.KR_CYNO1 = S1.CN");
        bld.append(" AND J1.CYUZO_YY BETWEEN S1.CYUZO_YY -1 AND S1.CYUZO_YY + 1");
        bld.append(" AND Z1.LINKKEY = L1.LINKKEY ");
        bld.append(" AND J1.JYOTAI = 1 ");
        bld.append(" AND J1.LINKKEY = ?");
        bld.append("        ");
        bld.append(" UNION ALL ");
        bld.append("        ");
        bld.append(" SELECT ");
        bld.append(" '2' TBLORDER ");
        bld.append(" , J1.LINKKEY ");
        bld.append(" , J1.KR_CYNO1 KR_CYNO ");
        bld.append(" , J1.LTA ");
        bld.append(" , NULL ");
        bld.append(" , NULL ");
        bld.append(" , NULL ");
        bld.append(" , NULL ");
        bld.append(" , NULL ");
        bld.append(" , NULL ");
        bld.append(" , NULL ");
        bld.append(" , NULL ");
        bld.append(" , NULL ");
        bld.append(" FROM ");
        bld.append(" DAT_JDF_COM2 J1 ");
        bld.append(" WHERE ");
        bld.append("  J1.JYOTAI = 0 ");
        bld.append("        ");
        bld.append(" UNION ALL ");
        bld.append("        ");
        bld.append(" SELECT ");
        bld.append(" '1' TBLORDER ");
        bld.append(" , Z2.LINKKEY ");
        bld.append(" , J2.KR_CYNO2 KR_CYNO ");
        bld.append(" , S2.A LTA");
        bld.append(" , RPAD(L2.JPJ, 2, ' ') JPJ ");
        bld.append(" , L2.JPRB ");
        bld.append(" , L2.JPRJ ");
        bld.append(" , L2.JPDT ");
        bld.append(" , L2.JPDT2 ");
        bld.append(" , L2.JPO2 ");
        bld.append(" , L2.JPDT3 ");
        bld.append(" , L2.JPO3 ");
        bld.append(" , L2.JPDT4 ");
        bld.append(" FROM ");
        bld.append(" DAT_JDF_COM J2 ");
        bld.append(" , DAT_LOT_FIX L2 ");
        bld.append(" , DAT_JDF_COM Z2 ");
        bld.append(" , (SELECT * FROM DAT_SLB_COM2 UNION ALL SELECT * FROM DAT_R_SLB_COM2) S2");
        bld.append(" WHERE ");
        bld.append(" J2.KR_CYNO2 = Z2.CYNO(+)");
        bld.append(" AND J2.CYUZO_YY BETWEEN NVL(Z2.CYUZO_YY,1) -1 AND NVL(Z2.CYUZO_YY,9998) + 1");
        bld.append(" AND J2.KR_CYNO2 = S2.CN");
        bld.append(" AND J2.CYUZO_YY BETWEEN S2.CYUZO_YY -1 AND S2.CYUZO_YY + 1");
        bld.append(" AND Z2.LINKKEY = L2.LINKKEY ");
        bld.append(" AND J2.JYOTAI = 1 ");
        bld.append(" AND J2.LINKKEY = ?");
        bld.append("        ");
        bld.append(" UNION ALL ");
        bld.append("        ");
        bld.append(" SELECT ");
        bld.append(" '1' TBLORDER ");
        bld.append(" , Z3.LINKKEY ");
        bld.append(" , J3.KR_CYNO3 KR_CYNO ");
        bld.append(" , S3.A LTA");
        bld.append(" , RPAD(L3.JPJ, 2, ' ') JPJ ");
        bld.append(" , L3.JPRB ");
        bld.append(" , L3.JPRJ ");
        bld.append(" , L3.JPDT ");
        bld.append(" , L3.JPDT2 ");
        bld.append(" , L3.JPO2 ");
        bld.append(" , L3.JPDT3 ");
        bld.append(" , L3.JPO3 ");
        bld.append(" , L3.JPDT4 ");
        bld.append(" FROM ");
        bld.append(" DAT_JDF_COM J3 ");
        bld.append(" , DAT_LOT_FIX L3 ");
        bld.append(" , DAT_JDF_COM Z3  ");
        bld.append(" , (SELECT * FROM DAT_SLB_COM2 UNION ALL SELECT * FROM DAT_R_SLB_COM2) S3");
        bld.append(" WHERE ");
        bld.append("  J3.KR_CYNO3 = Z3.CYNO(+)");
        bld.append(" AND J3.CYUZO_YY BETWEEN NVL(Z3.CYUZO_YY,1) -1 AND NVL(Z3.CYUZO_YY,9998) + 1");
        bld.append(" AND J3.KR_CYNO3 = S3.CN");
        bld.append(" AND J3.CYUZO_YY BETWEEN S3.CYUZO_YY -1 AND S3.CYUZO_YY + 1");
        bld.append(" AND Z3.LINKKEY = L3.LINKKEY ");
        bld.append(" AND J3.JYOTAI = 1 ");
        bld.append(" AND J3.LINKKEY = ?");
        bld.append("        ");
        bld.append(" UNION ALL ");
        bld.append("        ");
        bld.append(" SELECT ");
        bld.append(" '1' TBLORDER ");
        bld.append(" , Z4.LINKKEY ");
        bld.append(" , J4.KR_CYNO4 KR_CYNO ");
        bld.append(" , S4.A LTA");
        bld.append(" , RPAD(L4.JPJ, 2, ' ') JPJ ");
        bld.append(" , L4.JPRB ");
        bld.append(" , L4.JPRJ ");
        bld.append(" , L4.JPDT ");
        bld.append(" , L4.JPDT2 ");
        bld.append(" , L4.JPO2 ");
        bld.append(" , L4.JPDT3 ");
        bld.append(" , L4.JPO3 ");
        bld.append(" , L4.JPDT4 ");
        bld.append(" FROM ");
        bld.append(" DAT_JDF_COM J4 ");
        bld.append(" , DAT_LOT_FIX L4 ");
        bld.append(" , DAT_JDF_COM Z4 ");
        bld.append(" , (SELECT * FROM DAT_SLB_COM2 UNION ALL SELECT * FROM DAT_R_SLB_COM2) S4");
        bld.append(" WHERE ");
        bld.append("  J4.KR_CYNO4 = Z4.CYNO(+)");
        bld.append(" AND J4.CYUZO_YY BETWEEN NVL(Z4.CYUZO_YY,1) -1 AND NVL(Z4.CYUZO_YY,9998) + 1");
        bld.append(" AND J4.KR_CYNO4 = S4.CN");
        bld.append(" AND J4.CYUZO_YY BETWEEN S4.CYUZO_YY -1 AND S4.CYUZO_YY + 1");
        bld.append(" AND Z4.LINKKEY = L4.LINKKEY ");
        bld.append(" AND J4.JYOTAI = 1 ");
        bld.append(" AND J4.LINKKEY = ?");
        bld.append("        ");
        bld.append(" UNION ALL ");
        bld.append("        ");
        bld.append(" SELECT ");
        bld.append(" '1' TBLORDER ");
        bld.append(" , Z5.LINKKEY ");
        bld.append(" , J5.KR_CYNO5 KR_CYNO ");
        bld.append(" , S5.A LTA");
        bld.append(" , RPAD(L5.JPJ, 2, ' ') JPJ ");
        bld.append(" , L5.JPRB ");
        bld.append(" , L5.JPRJ ");
        bld.append(" , L5.JPDT ");
        bld.append(" , L5.JPDT2 ");
        bld.append(" , L5.JPO2 ");
        bld.append(" , L5.JPDT3 ");
        bld.append(" , L5.JPO3 ");
        bld.append(" , L5.JPDT4 ");
        bld.append(" FROM ");
        bld.append(" DAT_JDF_COM J5 ");
        bld.append(" , DAT_LOT_FIX L5 ");
        bld.append(" , DAT_JDF_COM Z5 ");
        bld.append(" , (SELECT * FROM DAT_SLB_COM2 UNION ALL SELECT * FROM DAT_R_SLB_COM2) S5");
        bld.append(" WHERE ");
        bld.append("     J5.KR_CYNO5 = Z5.CYNO(+)");
        bld.append(" AND J5.CYUZO_YY BETWEEN NVL(Z5.CYUZO_YY,1) -1 AND NVL(Z5.CYUZO_YY,9998) + 1");
        bld.append(" AND J5.KR_CYNO5 = S5.CN");
        bld.append(" AND J5.CYUZO_YY BETWEEN S5.CYUZO_YY -1 AND S5.CYUZO_YY + 1");
        bld.append(" AND Z5.LINKKEY = L5.LINKKEY ");
        bld.append(" AND J5.JYOTAI = 1 ");
        bld.append(" AND J5.LINKKEY = ?");
        bld.append("        ");
        bld.append(" UNION ALL ");
        bld.append("        ");
        bld.append(" SELECT ");
        bld.append(" '1' TBLORDER ");
        bld.append(" , Z6.LINKKEY ");
        bld.append(" , J6.KR_CYNO6 KR_CYNO ");
        bld.append(" , S6.A LTA");
        bld.append(" , RPAD(L6.JPJ, 2, ' ') JPJ ");
        bld.append(" , L6.JPRB ");
        bld.append(" , L6.JPRJ ");
        bld.append(" , L6.JPDT ");
        bld.append(" , L6.JPDT2 ");
        bld.append(" , L6.JPO2 ");
        bld.append(" , L6.JPDT3 ");
        bld.append(" , L6.JPO3 ");
        bld.append(" , L6.JPDT4 ");
        bld.append(" FROM ");
        bld.append(" DAT_JDF_COM J6 ");
        bld.append(" , DAT_LOT_FIX L6 ");
        bld.append(" , DAT_JDF_COM Z6 ");
        bld.append(" , (SELECT * FROM DAT_SLB_COM2 UNION ALL SELECT * FROM DAT_R_SLB_COM2) S6");
        bld.append(" WHERE ");
        bld.append("     J6.KR_CYNO6 = Z6.CYNO(+)");
        bld.append(" AND J6.CYUZO_YY BETWEEN NVL(Z6.CYUZO_YY,1) -1 AND NVL(Z6.CYUZO_YY,9998) + 1");
        bld.append(" AND J6.KR_CYNO6 = S6.CN");
        bld.append(" AND J6.CYUZO_YY BETWEEN S6.CYUZO_YY -1 AND S6.CYUZO_YY + 1");
        bld.append(" AND Z6.LINKKEY = L6.LINKKEY ");
        bld.append(" AND J6.JYOTAI = 1 ");
        bld.append(" AND J6.LINKKEY = ?");
        bld.append("        ");
        bld.append(" UNION ALL ");
        bld.append("        ");
        bld.append(" SELECT ");
        bld.append(" '1' TBLORDER ");
        bld.append(" , Z7.LINKKEY ");
        bld.append(" , J7.KR_CYNO7 KR_CYNO ");
        bld.append(" , S7.A LTA");
        bld.append(" , RPAD(L7.JPJ, 2, ' ') JPJ ");
        bld.append(" , L7.JPRB ");
        bld.append(" , L7.JPRJ ");
        bld.append(" , L7.JPDT ");
        bld.append(" , L7.JPDT2 ");
        bld.append(" , L7.JPO2 ");
        bld.append(" , L7.JPDT3 ");
        bld.append(" , L7.JPO3 ");
        bld.append(" , L7.JPDT4 ");
        bld.append(" FROM ");
        bld.append(" DAT_JDF_COM J7 ");
        bld.append(" , DAT_LOT_FIX L7 ");
        bld.append(" , DAT_JDF_COM Z7 ");
        bld.append(" , (SELECT * FROM DAT_SLB_COM2 UNION ALL SELECT * FROM DAT_R_SLB_COM2) S7");
        bld.append(" WHERE ");
        bld.append("      J7.KR_CYNO7 = Z7.CYNO(+)");
        bld.append(" AND J7.CYUZO_YY BETWEEN NVL(Z7.CYUZO_YY,1) -1 AND NVL(Z7.CYUZO_YY,9998) + 1");
        bld.append(" AND J7.KR_CYNO7 = S7.CN");
        bld.append(" AND J7.CYUZO_YY BETWEEN S7.CYUZO_YY -1 AND S7.CYUZO_YY + 1");
        bld.append(" AND Z7.LINKKEY = L7.LINKKEY ");
        bld.append(" AND J7.JYOTAI = 1 ");
        bld.append(" AND J7.LINKKEY = ?");
        bld.append("        ");
        bld.append(" UNION ALL ");
        bld.append("        ");
        bld.append(" SELECT ");
        bld.append(" '1' TBLORDER ");
        bld.append(" , Z8.LINKKEY ");
        bld.append(" , J8.KR_CYNO8 KR_CYNO ");
        bld.append(" , S8.A LTA");
        bld.append(" , RPAD(L8.JPJ, 2, ' ') JPJ ");
        bld.append(" , L8.JPRB ");
        bld.append(" , L8.JPRJ ");
        bld.append(" , L8.JPDT ");
        bld.append(" , L8.JPDT2 ");
        bld.append(" , L8.JPO2 ");
        bld.append(" , L8.JPDT3 ");
        bld.append(" , L8.JPO3 ");
        bld.append(" , L8.JPDT4 ");
        bld.append(" FROM ");
        bld.append(" DAT_JDF_COM J8 ");
        bld.append(" , DAT_LOT_FIX L8 ");
        bld.append(" , DAT_JDF_COM Z8 ");
        bld.append(" , (SELECT * FROM DAT_SLB_COM2 UNION ALL SELECT * FROM DAT_R_SLB_COM2) S8");
        bld.append(" WHERE ");
        bld.append(" J8.KR_CYNO8 = Z8.CYNO(+)");
        bld.append(" AND J8.CYUZO_YY BETWEEN NVL(Z8.CYUZO_YY,1) -1 AND NVL(Z8.CYUZO_YY,9998) + 1");
        bld.append(" AND J8.KR_CYNO8 = S8.CN");
        bld.append(" AND J8.CYUZO_YY BETWEEN S8.CYUZO_YY -1 AND S8.CYUZO_YY + 1");
        bld.append(" AND Z8.LINKKEY = L8.LINKKEY ");
        bld.append(" AND J8.JYOTAI = 1 ");
        bld.append(" AND J8.LINKKEY = ?");
        bld.append(" ) ");
        bld.append(" WHERE ");
        bld.append(" KR_CYNO <> '0000000000' ");
        bld.append(" AND TBLORDER = ? ");
        bld.append(" ORDER BY ");
        bld.append(" KR_CYNO ");

        return bld.toString();
    }

    /**
     * 徐冷共通情報
     * 2012/12/14 add
     */
    public static String getCFCommonQuery() {
        StringBuilder bld = new StringBuilder();
	bld.append("SELECT * FROM ( ");
	bld.append("SELECT  ");
	bld.append("		'1' TBLORDER, ");
	bld.append("		D1.LINKKEY, ");
	bld.append("            CASE WHEN H_IATMP1 = 0 THEN NULL ELSE H_IATMP1 END H_IATMP1, ");
	bld.append("            CASE WHEN H_IATMP2 = 0 THEN NULL ELSE H_IATMP2 END H_IATMP2, ");
	bld.append("            CASE WHEN H_IATMP3 = 0 THEN NULL ELSE H_IATMP3 END H_IATMP3, ");
	bld.append("            CASE WHEN H_IATMP4 = 0 THEN NULL ELSE H_IATMP4 END H_IATMP4, ");
	bld.append("            CASE WHEN H_IATIME1 = 0 THEN NULL ELSE H_IATIME1 END H_IATIME1, ");
	bld.append("            CASE WHEN H_IATIME2 = 0 THEN NULL ELSE H_IATIME2 END H_IATIME2, ");
	bld.append("            CASE WHEN H_IATIME3 = 0 THEN NULL ELSE H_IATIME3 END H_IATIME3, ");
	bld.append("            CASE WHEN H_IATIME4 = 0 THEN NULL ELSE H_IATIME4 END H_IATIME4, ");
	bld.append("            CASE WHEN H_STMP = 0 THEN NULL ELSE H_STMP END H_STMP, ");
	bld.append("            CASE WHEN H_STIME = 0 THEN NULL ELSE H_STIME END H_STIME, ");
	bld.append("            CASE WHEN H_ATMP = 0 THEN NULL ELSE H_ATMP END H_ATMP, ");
	bld.append("            CASE WHEN H_ATIME = 0 THEN NULL ELSE H_ATIME END H_ATIME, ");
	bld.append("		DECODE(YRO,'00','',YRO) YRO, ");
	bld.append("            CASE WHEN YTMP = 0 THEN NULL ELSE YTMP END YTMP, ");
	bld.append("            CASE WHEN YTIME = 0 THEN NULL ELSE YTIME END YTIME, ");
	bld.append("		DECODE(YSPD,'0000000000',NULL,YSPD) YSPD, ");
	bld.append("            CASE WHEN ZTMP2 = 0 THEN NULL ELSE ZTMP2 END ZTMP2, ");
	bld.append("            CASE WHEN ZTIME2 = 0 THEN NULL ELSE ZTIME2 END ZTIME2, ");
	bld.append("            CASE WHEN ZTMP4 = 0 THEN NULL ELSE ZTMP4 END ZTMP4, ");
	bld.append("            CASE WHEN ZTIME4 = 0 THEN NULL ELSE ZTIME4 END ZTIME4, ");
	bld.append("            CASE WHEN KHARI1 = 0 THEN NULL ELSE KHARI1 END KHARI1, ");
	bld.append("            CASE WHEN KHARI2 = 0 THEN NULL ELSE KHARI2 END KHARI2, ");
	bld.append("            CASE WHEN KHARI3 = 0 THEN NULL ELSE KHARI3 END KHARI3, ");
	bld.append("            CASE WHEN KTAI1 = 0 THEN NULL ELSE KTAI1 END KTAI1, ");
	bld.append("            CASE WHEN KTAI2 = 0 THEN NULL ELSE KTAI2 END KTAI2, ");
	bld.append("            CASE WHEN KTAI3 = 0 THEN NULL ELSE KTAI3 END KTAI3, ");
	bld.append("            CASE WHEN KNOBI1 = 0 THEN NULL ELSE KNOBI1 END KNOBI1, ");
	bld.append("            CASE WHEN KNOBI2 = 0 THEN NULL ELSE KNOBI2 END KNOBI2, ");
	bld.append("            CASE WHEN KNOBI3 = 0 THEN NULL ELSE KNOBI3 END KNOBI3, ");
	bld.append("		DECODE(M_IACOD,' ','', M_IACOD) M_IACOD, ");
	bld.append("            CASE WHEN M_IAX1 = 0 THEN NULL ELSE M_IAX1 END M_IAX1, ");
	bld.append("            CASE WHEN M_IAX2 = 0 THEN NULL ELSE M_IAX2 END M_IAX2, ");
	bld.append("            CASE WHEN M_IAX3 = 0 THEN NULL ELSE M_IAX3 END M_IAX3, ");
	bld.append("            CASE WHEN M_IAX4 = 0 THEN NULL ELSE M_IAX4 END M_IAX4, ");
	bld.append("            CASE WHEN M_IART1 = 0 THEN NULL ELSE M_IART1 END M_IART1, ");
	bld.append("            CASE WHEN M_IART2 = 0 THEN NULL ELSE M_IART2 END M_IART2, ");
	bld.append("            CASE WHEN M_IART3 = 0 THEN NULL ELSE M_IART3 END M_IART3, ");
	bld.append("            CASE WHEN M_IART4 = 0 THEN NULL ELSE M_IART4 END M_IART4, ");
	bld.append("            CASE WHEN M_STMP = 0 THEN NULL ELSE M_STMP END M_STMP, ");
	bld.append("            CASE WHEN M_STIM = 0 THEN NULL ELSE M_STIM END M_STIM, ");
	bld.append("            CASE WHEN M_ATMP = 0 THEN NULL ELSE M_ATMP END M_ATMP, ");
	bld.append("            CASE WHEN M_ATIM = 0 THEN NULL ELSE M_ATIM END M_ATIM, ");
	bld.append("            CASE WHEN M_IATMP1 = 0 THEN NULL ELSE M_IATMP1 END M_IATMP1, ");
	bld.append("            CASE WHEN M_IATMP2 = 0 THEN NULL ELSE M_IATMP2 END M_IATMP2, ");
	bld.append("            CASE WHEN M_IATMP3 = 0 THEN NULL ELSE M_IATMP3 END M_IATMP3, ");
	bld.append("            CASE WHEN M_IATMP4 = 0 THEN NULL ELSE M_IATMP4 END M_IATMP4, ");
	bld.append("            CASE WHEN M_IATIM1 = 0 THEN NULL ELSE M_IATIM1 END M_IATIM1, ");
	bld.append("            CASE WHEN M_IATIM2 = 0 THEN NULL ELSE M_IATIM2 END M_IATIM2, ");
	bld.append("            CASE WHEN M_IATIM3 = 0 THEN NULL ELSE M_IATIM3 END M_IATIM3, ");
	bld.append("            CASE WHEN M_IATIM4 = 0 THEN NULL ELSE M_IATIM4 END M_IATIM4, ");
	bld.append("		DECODE(SHODON_FLG1,'0','','1','1 CAI指定','2','2 CAI優先','3','3 バッチ指定','4','4 バッチ優先',SHODON_FLG1) SHODON_FLG1, ");
	bld.append("		DECODE(SHODON_FLG2,'0','','1','1 CAF指定','2','2 CAF優先','3','3 バッチ指定','4','4 バッチ優先',SHODON_FLG2) SHODON_FLG2, ");
	bld.append("            CASE WHEN KHAX = 0 THEN NULL ELSE KHAX END KHAX, ");
	bld.append("            CASE WHEN KHIN = 0 THEN NULL ELSE KHIN END KHIN, ");
	bld.append("            CASE WHEN KHAV = 0 THEN NULL ELSE KHAV END KHAV, ");
	bld.append("            CASE WHEN KNAX = 0 THEN NULL ELSE KNAX END KNAX, ");
	bld.append("            CASE WHEN KNIN = 0 THEN NULL ELSE KNIN END KNIN, ");
	bld.append("            CASE WHEN KNAV = 0 THEN NULL ELSE KNAV END KNAV, ");
	bld.append("            CASE WHEN KTAX = 0 THEN NULL ELSE KTAX END KTAX, ");
	bld.append("            CASE WHEN KTIN = 0 THEN NULL ELSE KTIN END KTIN, ");
	bld.append("            CASE WHEN KTAV = 0 THEN NULL ELSE KTAV END KTAV, ");
	bld.append("            CASE WHEN J_KHAX = 0 THEN NULL ELSE J_KHAX END J_KHAX, ");
	bld.append("            CASE WHEN J_KHIN = 0 THEN NULL ELSE J_KHIN END J_KHIN, ");
	bld.append("            CASE WHEN J_KHAV = 0 THEN NULL ELSE J_KHAV END J_KHAV, ");
	bld.append("            CASE WHEN J_KNAX = 0 THEN NULL ELSE J_KNAX END J_KNAX, ");
	bld.append("            CASE WHEN J_KNIN = 0 THEN NULL ELSE J_KNIN END J_KNIN, ");
	bld.append("            CASE WHEN J_KNAV = 0 THEN NULL ELSE J_KNAV END J_KNAV, ");
	bld.append("            CASE WHEN J_KTAX = 0 THEN NULL ELSE J_KTAX END J_KTAX, ");
	bld.append("            CASE WHEN J_KTIN = 0 THEN NULL ELSE J_KTIN END J_KTIN, ");
	bld.append("            CASE WHEN J_KTAV = 0 THEN NULL ELSE J_KTAV END J_KTAV, ");
	bld.append("		DECODE(PLT1,'0','',PLT1) PLT1,DECODE(PLT2,'0','',PLT2) PLT2,DECODE(PLT3,'0','',PLT3) PLT3,DECODE(PLT4,'0','',PLT4) PLT4, ");
	bld.append("		DECODE(PKBN,'0','','1','中','2','高','') PKBN, ");
	bld.append("		DECODE(OPLT_AFLG,'0','',OPLT_AFLG) OPLT_AFLG, ");
	bld.append("		DECODE(OPLT_APLT,'0','','1','1X2','2','4X8','3','5X10','4','4000','') OPLT_APLT, ");
	bld.append("		DECODE(OPLT_ALOT1,'0000000','',OPLT_ALOT1) OPLT_ALOT1,DECODE(OPLT_ALOT2,'0000000','',OPLT_ALOT2) OPLT_ALOT2, ");
	bld.append("		DECODE(OPLT_ALOT3,'0000000','',OPLT_ALOT3) OPLT_ALOT3,DECODE(OPLT_ALOT4,'0000000','',OPLT_ALOT4) OPLT_ALOT4, ");
	bld.append("		DECODE(OPLT_NOKBN1,'0','','1','1X2','2','4X8','3','5X10','4','4000','') OPLT_NOKBN1, ");
	bld.append("		DECODE(OPLT_NO1,'000000','',OPLT_NO1) OPLT_NO1, ");
	bld.append("		DECODE(OPLT_NOKBN2,'0','','1','1X2','2','4X8','3','5X10','4','4000','') OPLT_NOKBN2, ");
	bld.append("		DECODE(OPLT_NO2,'000000','',OPLT_NO2) OPLT_NO2, ");
	bld.append("		DECODE(OPLT_NOKBN3,'0','','1','1X2','2','4X8','3','5X10','4','4000','') OPLT_NOKBN3, ");
	bld.append("		DECODE(OPLT_NO3,'000000','',OPLT_NO3) OPLT_NO3, ");
	bld.append("		DECODE(OPLT_NOKBN4,'0','','1','1X2','2','4X8','3','5X10','4','4000','') OPLT_NOKBN4, ");
	bld.append("		DECODE(OPLT_NO4,'000000','',OPLT_NO4) OPLT_NO4, ");
/* ---テスト---- */
/*
	bld.append("		'' JRFLG, ");
	bld.append("		TO_CHAR(NULL) JRSTDT, ");
	bld.append("		TO_CHAR(NULL) JREDDT, ");
	bld.append("            NULL JRINT, ");
	bld.append("            NULL JRTMP ");
*/
/*  本番用 */
	bld.append("		DECODE(JRFLG,'0','',JRFLG) JRFLG, ");
	bld.append("		TO_CHAR(JRSTDT,'YY/MM/DD HH24:MI') JRSTDT, ");
	bld.append("		TO_CHAR(JREDDT,'YY/MM/DD HH24:MI') JREDDT, ");
	bld.append("            CASE WHEN JRINT = 0 THEN NULL ELSE ROUND(JRINT/60,2) END JRINT, ");
	bld.append("            CASE WHEN JRTMP = 0 THEN NULL ELSE JRTMP END JRTMP ");
/**/
	bld.append("	FROM ");
	bld.append("			DAT_JDF_COM D1, ");
	bld.append("			DAT_LOT_COM_KINS D2, ");
	bld.append("			DAT_LOT_FIX D3 ");
	bld.append("	WHERE ");
	bld.append("			D1.LINKKEY = D2.LINKKEY(+) ");
	bld.append("		AND	D1.LINKKEY = D3.LINKKEY(+) ");
	bld.append("    	AND	D1.JYOTAI = 1 ");
	bld.append("	UNION ALL ");

	// ▼2017/10/27 ADD-S
	bld.append("SELECT ");
	bld.append("		'2' TBLORDER, ");
	bld.append("       D1.LINKKEY");
	bld.append("      ,CASE WHEN H_IATMP1 = 0 THEN NULL ELSE H_IATMP1 END H_IATMP1");
	bld.append("      ,CASE WHEN H_IATMP2 = 0 THEN NULL ELSE H_IATMP2 END H_IATMP2");
	bld.append("      ,CASE WHEN H_IATMP3 = 0 THEN NULL ELSE H_IATMP3 END H_IATMP3");
	bld.append("      ,CASE WHEN H_IATMP4 = 0 THEN NULL ELSE H_IATMP4 END H_IATMP4");
	bld.append("      ,CASE WHEN H_IATIME1 = 0 THEN NULL ELSE H_IATIME1 END H_IATIME1");
	bld.append("      ,CASE WHEN H_IATIME2 = 0 THEN NULL ELSE H_IATIME2 END H_IATIME2");
	bld.append("      ,CASE WHEN H_IATIME3 = 0 THEN NULL ELSE H_IATIME3 END H_IATIME3");
	bld.append("      ,CASE WHEN H_IATIME4 = 0 THEN NULL ELSE H_IATIME4 END H_IATIME4");
	bld.append("      ,CASE WHEN H_STMP = 0 THEN NULL ELSE H_STMP END H_STMP");
	bld.append("      ,CASE WHEN H_STIME = 0 THEN NULL ELSE H_STIME END H_STIME");
	bld.append("      ,CASE WHEN H_ATMP = 0 THEN NULL ELSE H_ATMP END H_ATMP");
	bld.append("      ,CASE WHEN H_ATIME = 0 THEN NULL ELSE H_ATIME END H_ATIME");
	bld.append("      ,DECODE(YRO, '00', '', YRO) YRO");
	bld.append("      ,CASE WHEN YTMP = 0 THEN NULL ELSE YTMP END YTMP");
	bld.append("      ,CASE WHEN YTIME = 0 THEN NULL ELSE YTIME END YTIME");
	bld.append("      ,DECODE(YSPD, '0000000000', NULL, YSPD) YSPD");
	bld.append("      ,CASE WHEN ZTMP2 = 0 THEN NULL ELSE ZTMP2 END ZTMP2");
	bld.append("      ,CASE WHEN ZTIME2 = 0 THEN NULL ELSE ZTIME2 END ZTIME2");
	bld.append("      ,CASE WHEN ZTMP4 = 0 THEN NULL ELSE ZTMP4 END ZTMP4");
	bld.append("      ,CASE WHEN ZTIME4 = 0 THEN NULL ELSE ZTIME4 END ZTIME4");
	bld.append("      ,CASE WHEN KHARI1 = 0 THEN NULL ELSE KHARI1 END KHARI1");
	bld.append("      ,CASE WHEN KHARI2 = 0 THEN NULL ELSE KHARI2 END KHARI2");
	bld.append("      ,CASE WHEN KHARI3 = 0 THEN NULL ELSE KHARI3 END KHARI3");
	bld.append("      ,CASE WHEN KTAI1 = 0 THEN NULL ELSE KTAI1 END KTAI1");
	bld.append("      ,CASE WHEN KTAI2 = 0 THEN NULL ELSE KTAI2 END KTAI2");
	bld.append("      ,CASE WHEN KTAI3 = 0 THEN NULL ELSE KTAI3 END KTAI3");
	bld.append("      ,CASE WHEN KNOBI1 = 0 THEN NULL ELSE KNOBI1 END KNOBI1");
	bld.append("      ,CASE WHEN KNOBI2 = 0 THEN NULL ELSE KNOBI2 END KNOBI2");
	bld.append("      ,CASE WHEN KNOBI3 = 0 THEN NULL ELSE KNOBI3 END KNOBI3");
	bld.append("      ,NULL"); //IACOD 商品マスタ指定情報
	bld.append("      ,CASE WHEN M_IAX1 = 0 THEN NULL ELSE M_IAX1 END M_IAX1");
	bld.append("      ,CASE WHEN M_IAX2 = 0 THEN NULL ELSE M_IAX2 END M_IAX2");
	bld.append("      ,CASE WHEN M_IAX3 = 0 THEN NULL ELSE M_IAX3 END M_IAX3");
	bld.append("      ,CASE WHEN M_IAX4 = 0 THEN NULL ELSE M_IAX4 END M_IAX4");
	bld.append("      ,CASE WHEN M_IART1 = 0 THEN NULL ELSE M_IART1 END M_IART1");
	bld.append("      ,CASE WHEN M_IART2 = 0 THEN NULL ELSE M_IART2 END M_IART2");
	bld.append("      ,CASE WHEN M_IART3 = 0 THEN NULL ELSE M_IART3 END M_IART3");
	bld.append("      ,CASE WHEN M_IART4 = 0 THEN NULL ELSE M_IART4 END M_IART4");
	bld.append("      ,CASE WHEN M_STMP = 0 THEN NULL ELSE M_STMP END M_STMP");
	bld.append("      ,CASE WHEN M_STIM = 0 THEN NULL ELSE M_STIM END M_STIM");
	bld.append("      ,CASE WHEN M_ATMP = 0 THEN NULL ELSE M_ATMP END M_ATMP");
	bld.append("      ,CASE WHEN M_ATIM = 0 THEN NULL ELSE M_ATIM END M_ATIM");
	bld.append("      ,CASE WHEN M_IATMP1 = 0 THEN NULL ELSE M_IATMP1 END M_IATMP1");
	bld.append("      ,CASE WHEN M_IATMP2 = 0 THEN NULL ELSE M_IATMP2 END M_IATMP2");
	bld.append("      ,CASE WHEN M_IATMP3 = 0 THEN NULL ELSE M_IATMP3 END M_IATMP3");
	bld.append("      ,CASE WHEN M_IATMP4 = 0 THEN NULL ELSE M_IATMP4 END M_IATMP4");
	bld.append("      ,CASE WHEN M_IATIM1 = 0 THEN NULL ELSE M_IATIM1 END M_IATIM1");
	bld.append("      ,CASE WHEN M_IATIM2 = 0 THEN NULL ELSE M_IATIM2 END M_IATIM2");
	bld.append("      ,CASE WHEN M_IATIM3 = 0 THEN NULL ELSE M_IATIM3 END M_IATIM3");
	bld.append("      ,CASE WHEN M_IATIM4 = 0 THEN NULL ELSE M_IATIM4 END M_IATIM4");
	bld.append("      ,DECODE(SHODON_FLG1, '0', '', '1', '1 CAI指定', '2', '2 CAI優先', '3', '3 バッチ指定', '4', '4 バッチ優先', SHODON_FLG1) SHODON_FLG1");
	bld.append("      ,DECODE(SHODON_FLG2, '0', '', '1', '1 CAF指定', '2', '2 CAF優先', '3', '3 バッチ指定', '4', '4 バッチ優先', SHODON_FLG2) SHODON_FLG2");
	bld.append("      ,NULL");
	bld.append("      ,NULL");
	bld.append("      ,NULL");
	bld.append("      ,NULL");
	bld.append("      ,NULL");
	bld.append("      ,NULL");
	bld.append("      ,NULL");
	bld.append("      ,NULL");
	bld.append("      ,NULL");
	bld.append("      ,NULL");
	bld.append("      ,NULL");
	bld.append("      ,NULL");
	bld.append("      ,NULL");
	bld.append("      ,NULL");
	bld.append("      ,NULL");
	bld.append("      ,NULL");
	bld.append("      ,NULL");
	bld.append("      ,NULL");
	bld.append("      ,NULL");
	bld.append("      ,NULL");
	bld.append("      ,NULL");
	bld.append("      ,NULL");
	bld.append("      ,NULL");
	bld.append("      ,NULL");
	bld.append("      ,NULL");
	bld.append("      ,NULL");
	bld.append("      ,NULL");
	bld.append("      ,NULL");
	bld.append("      ,NULL");
	bld.append("      ,NULL");
	bld.append("      ,NULL");
	bld.append("      ,NULL");
	bld.append("      ,NULL");
	bld.append("      ,NULL");
	bld.append("      ,NULL");
	bld.append("      ,NULL");
	bld.append("      ,NULL");
	bld.append("      ,NULL");
	bld.append("      ,NULL");
	bld.append("      ,NULL");
	bld.append("      ,NULL");
	bld.append("      ,NULL");
	bld.append("  FROM");
	bld.append(" DAT_JDF_COM2 D1");
	bld.append("      ,DAT_JDF_FIX D2");
	bld.append(" WHERE");
	bld.append(" D1.LINKKEY = D2.LINKKEY(+) AND");
	bld.append(" D1.JYOTAI = 0");
	bld.append(" UNION ALL ");
	// ▲2017/10/27 ADD-E

	bld.append("	SELECT ");
	bld.append("		'3' TBLORDER, ");
	bld.append("		D1.LINKKEY, ");
	bld.append("            CASE WHEN H_IATMP1 = 0 THEN NULL ELSE H_IATMP1 END H_IATMP1, ");
	bld.append("            CASE WHEN H_IATMP2 = 0 THEN NULL ELSE H_IATMP2 END H_IATMP2, ");
	bld.append("            CASE WHEN H_IATMP3 = 0 THEN NULL ELSE H_IATMP3 END H_IATMP3, ");
	bld.append("            CASE WHEN H_IATMP4 = 0 THEN NULL ELSE H_IATMP4 END H_IATMP4, ");
	bld.append("            CASE WHEN H_IATIME1 = 0 THEN NULL ELSE H_IATIME1 END H_IATIME1, ");
	bld.append("            CASE WHEN H_IATIME2 = 0 THEN NULL ELSE H_IATIME2 END H_IATIME2, ");
	bld.append("            CASE WHEN H_IATIME3 = 0 THEN NULL ELSE H_IATIME3 END H_IATIME3, ");
	bld.append("            CASE WHEN H_IATIME4 = 0 THEN NULL ELSE H_IATIME4 END H_IATIME4, ");
	bld.append("            CASE WHEN H_STMP = 0 THEN NULL ELSE H_STMP END H_STMP, ");
	bld.append("            CASE WHEN H_STIME = 0 THEN NULL ELSE H_STIME END H_STIME, ");
	bld.append("            CASE WHEN H_ATMP = 0 THEN NULL ELSE H_ATMP END H_ATMP, ");
	bld.append("            CASE WHEN H_ATIME = 0 THEN NULL ELSE H_ATIME END H_ATIME, ");
	bld.append("		DECODE(YRO,'00','',YRO) YRO, ");
	bld.append("            CASE WHEN YTMP = 0 THEN NULL ELSE YTMP END YTMP, ");
	bld.append("            CASE WHEN YTIME = 0 THEN NULL ELSE YTIME END YTIME, ");
	bld.append("		DECODE(YSPD,'0000000000',NULL,YSPD) YSPD, ");
	bld.append("            CASE WHEN ZTMP2 = 0 THEN NULL ELSE ZTMP2 END ZTMP2, ");
	bld.append("            CASE WHEN ZTIME2 = 0 THEN NULL ELSE ZTIME2 END ZTIME2, ");
	bld.append("            CASE WHEN ZTMP4 = 0 THEN NULL ELSE ZTMP4 END ZTMP4, ");
	bld.append("            CASE WHEN ZTIME4 = 0 THEN NULL ELSE ZTIME4 END ZTIME4, ");
	bld.append("            CASE WHEN KHARI1 = 0 THEN NULL ELSE KHARI1 END KHARI1, ");
	bld.append("            CASE WHEN KHARI2 = 0 THEN NULL ELSE KHARI2 END KHARI2, ");
	bld.append("            CASE WHEN KHARI3 = 0 THEN NULL ELSE KHARI3 END KHARI3, ");
	bld.append("            CASE WHEN KTAI1 = 0 THEN NULL ELSE KTAI1 END KTAI1, ");
	bld.append("            CASE WHEN KTAI2 = 0 THEN NULL ELSE KTAI2 END KTAI2, ");
	bld.append("            CASE WHEN KTAI3 = 0 THEN NULL ELSE KTAI3 END KTAI3, ");
	bld.append("            CASE WHEN KNOBI1 = 0 THEN NULL ELSE KNOBI1 END KNOBI1, ");
	bld.append("            CASE WHEN KNOBI2 = 0 THEN NULL ELSE KNOBI2 END KNOBI2, ");
	bld.append("            CASE WHEN KNOBI3 = 0 THEN NULL ELSE KNOBI3 END KNOBI3, ");
	bld.append("		DECODE(M_IACOD,' ','', M_IACOD) M_IACOD, ");
	bld.append("            CASE WHEN M_IAX1 = 0 THEN NULL ELSE M_IAX1 END M_IAX1, ");
	bld.append("            CASE WHEN M_IAX2 = 0 THEN NULL ELSE M_IAX2 END M_IAX2, ");
	bld.append("            CASE WHEN M_IAX3 = 0 THEN NULL ELSE M_IAX3 END M_IAX3, ");
	bld.append("            CASE WHEN M_IAX4 = 0 THEN NULL ELSE M_IAX4 END M_IAX4, ");
	bld.append("            CASE WHEN M_IART1 = 0 THEN NULL ELSE M_IART1 END M_IART1, ");
	bld.append("            CASE WHEN M_IART2 = 0 THEN NULL ELSE M_IART2 END M_IART2, ");
	bld.append("            CASE WHEN M_IART3 = 0 THEN NULL ELSE M_IART3 END M_IART3, ");
	bld.append("            CASE WHEN M_IART4 = 0 THEN NULL ELSE M_IART4 END M_IART4, ");
	bld.append("            CASE WHEN M_STMP = 0 THEN NULL ELSE M_STMP END M_STMP, ");
	bld.append("            CASE WHEN M_STIM = 0 THEN NULL ELSE M_STIM END M_STIM, ");
	bld.append("            CASE WHEN M_ATMP = 0 THEN NULL ELSE M_ATMP END M_ATMP, ");
	bld.append("            CASE WHEN M_ATIM = 0 THEN NULL ELSE M_ATIM END M_ATIM, ");
	bld.append("            CASE WHEN M_IATMP1 = 0 THEN NULL ELSE M_IATMP1 END M_IATMP1, ");
	bld.append("            CASE WHEN M_IATMP2 = 0 THEN NULL ELSE M_IATMP2 END M_IATMP2, ");
	bld.append("            CASE WHEN M_IATMP3 = 0 THEN NULL ELSE M_IATMP3 END M_IATMP3, ");
	bld.append("            CASE WHEN M_IATMP4 = 0 THEN NULL ELSE M_IATMP4 END M_IATMP4, ");
	bld.append("            CASE WHEN M_IATIM1 = 0 THEN NULL ELSE M_IATIM1 END M_IATIM1, ");
	bld.append("            CASE WHEN M_IATIM2 = 0 THEN NULL ELSE M_IATIM2 END M_IATIM2, ");
	bld.append("            CASE WHEN M_IATIM3 = 0 THEN NULL ELSE M_IATIM3 END M_IATIM3, ");
	bld.append("            CASE WHEN M_IATIM4 = 0 THEN NULL ELSE M_IATIM4 END M_IATIM4, ");
	bld.append("		DECODE(SHODON_FLG1,'0','','1','1 CAI指定','2','2 CAI優先','3','3 バッチ指定','4','4 バッチ優先',SHODON_FLG1) SHODON_FLG1, ");
	bld.append("		DECODE(SHODON_FLG2,'0','','1','1 CAF指定','2','2 CAF優先','3','3 バッチ指定','4','4 バッチ優先',SHODON_FLG2) SHODON_FLG2, ");
	bld.append("            CASE WHEN KHAX = 0 THEN NULL ELSE KHAX END KHAX, ");
	bld.append("            CASE WHEN KHIN = 0 THEN NULL ELSE KHIN END KHIN, ");
	bld.append("            CASE WHEN KHAV = 0 THEN NULL ELSE KHAV END KHAV, ");
	bld.append("            CASE WHEN KNAX = 0 THEN NULL ELSE KNAX END KNAX, ");
	bld.append("            CASE WHEN KNIN = 0 THEN NULL ELSE KNIN END KNIN, ");
	bld.append("            CASE WHEN KNAV = 0 THEN NULL ELSE KNAV END KNAV, ");
	bld.append("            CASE WHEN KTAX = 0 THEN NULL ELSE KTAX END KTAX, ");
	bld.append("            CASE WHEN KTIN = 0 THEN NULL ELSE KTIN END KTIN, ");
	bld.append("            CASE WHEN KTAV = 0 THEN NULL ELSE KTAV END KTAV, ");
	bld.append("            CASE WHEN J_KHAX = 0 THEN NULL ELSE J_KHAX END J_KHAX, ");
	bld.append("            CASE WHEN J_KHIN = 0 THEN NULL ELSE J_KHIN END J_KHIN, ");
	bld.append("            CASE WHEN J_KHAV = 0 THEN NULL ELSE J_KHAV END J_KHAV, ");
	bld.append("            CASE WHEN J_KNAX = 0 THEN NULL ELSE J_KNAX END J_KNAX, ");
	bld.append("            CASE WHEN J_KNIN = 0 THEN NULL ELSE J_KNIN END J_KNIN, ");
	bld.append("            CASE WHEN J_KNAV = 0 THEN NULL ELSE J_KNAV END J_KNAV, ");
	bld.append("            CASE WHEN J_KTAX = 0 THEN NULL ELSE J_KTAX END J_KTAX, ");
	bld.append("            CASE WHEN J_KTIN = 0 THEN NULL ELSE J_KTIN END J_KTIN, ");
	bld.append("            CASE WHEN J_KTAV = 0 THEN NULL ELSE J_KTAV END J_KTAV, ");
	bld.append("		DECODE(PLT1,'0','',PLT1) PLT1,DECODE(PLT2,'0','',PLT2) PLT2,DECODE(PLT3,'0','',PLT3) PLT3,DECODE(PLT4,'0','',PLT4) PLT4, ");
	bld.append("		DECODE(PKBN,'0','','1','中','2','高','') PKBN, ");
	bld.append("		DECODE(OPLT_AFLG,'0','',OPLT_AFLG) OPLT_AFLG, ");
	bld.append("		DECODE(OPLT_APLT,'0','','1','1X2','2','4X8','3','5X10','4','4000','') OPLT_APLT, ");
	bld.append("		DECODE(OPLT_ALOT1,'0000000','',OPLT_ALOT1) OPLT_ALOT1,DECODE(OPLT_ALOT2,'0000000','',OPLT_ALOT2) OPLT_ALOT2, ");
	bld.append("		DECODE(OPLT_ALOT3,'0000000','',OPLT_ALOT3) OPLT_ALOT3,DECODE(OPLT_ALOT4,'0000000','',OPLT_ALOT4) OPLT_ALOT4, ");
	bld.append("		DECODE(OPLT_NOKBN1,'0','','1','1X2','2','4X8','3','5X10','4','4000','') OPLT_NOKBN1, ");
	bld.append("		DECODE(OPLT_NO1,'000000','',OPLT_NO1) OPLT_NO1, ");
	bld.append("		DECODE(OPLT_NOKBN2,'0','','1','1X2','2','4X8','3','5X10','4','4000','') OPLT_NOKBN2, ");
	bld.append("		DECODE(OPLT_NO2,'000000','',OPLT_NO2) OPLT_NO2, ");
	bld.append("		DECODE(OPLT_NOKBN3,'0','','1','1X2','2','4X8','3','5X10','4','4000','') OPLT_NOKBN3, ");
	bld.append("		DECODE(OPLT_NO3,'000000','',OPLT_NO3) OPLT_NO3, ");
	bld.append("		DECODE(OPLT_NOKBN4,'0','','1','1X2','2','4X8','3','5X10','4','4000','') OPLT_NOKBN4, ");
	bld.append("		DECODE(OPLT_NO4,'000000','',OPLT_NO4) OPLT_NO4, ");
/* ---テスト---- */
/*
	bld.append("		'' JRFLG, ");
	bld.append("		TO_CHAR(NULL) JRSTDT, ");
	bld.append("		TO_CHAR(NULL) JREDDT, ");
	bld.append("            NULL JRINT, ");
	bld.append("            NULL JRTMP ");
*/
/*  本番用 */
	bld.append("		DECODE(JRFLG,'0','',JRFLG) JRFLG, ");
	bld.append("		TO_CHAR(JRSTDT,'YY/MM/DD HH24:MI') JRSTDT, ");
	bld.append("		TO_CHAR(JREDDT,'YY/MM/DD HH24:MI') JREDDT, ");
	bld.append("            CASE WHEN JRINT = 0 THEN NULL ELSE ROUND(JRINT/60,2) END JRINT, ");
	bld.append("            CASE WHEN JRTMP = 0 THEN NULL ELSE JRTMP END JRTMP ");
/**/
	bld.append("	FROM ");
	bld.append("			DAT_R_JDF_COM D1, ");
	bld.append("			DAT_R_LOT_COM_KINS D2, ");
	bld.append("			DAT_R_LOT_FIX D3 ");
	bld.append("	WHERE ");
	bld.append("			D1.LINKKEY = D2.LINKKEY(+) ");
	bld.append("    	AND	D1.LINKKEY = D3.LINKKEY(+) ");
	bld.append("    	AND	D1.JYOTAI = 2 ");
	// 2014/09/25 追加 ▼
	bld.append("	UNION ALL ");
	bld.append("	SELECT ");
	bld.append("		'4' TBLORDER, ");
	bld.append("		D1.LINKKEY, ");
	bld.append("            CASE WHEN H_IATMP1 = 0 THEN NULL ELSE H_IATMP1 END H_IATMP1, ");
	bld.append("            CASE WHEN H_IATMP2 = 0 THEN NULL ELSE H_IATMP2 END H_IATMP2, ");
	bld.append("            CASE WHEN H_IATMP3 = 0 THEN NULL ELSE H_IATMP3 END H_IATMP3, ");
	bld.append("            CASE WHEN H_IATMP4 = 0 THEN NULL ELSE H_IATMP4 END H_IATMP4, ");
	bld.append("            CASE WHEN H_IATIME1 = 0 THEN NULL ELSE H_IATIME1 END H_IATIME1, ");
	bld.append("            CASE WHEN H_IATIME2 = 0 THEN NULL ELSE H_IATIME2 END H_IATIME2, ");
	bld.append("            CASE WHEN H_IATIME3 = 0 THEN NULL ELSE H_IATIME3 END H_IATIME3, ");
	bld.append("            CASE WHEN H_IATIME4 = 0 THEN NULL ELSE H_IATIME4 END H_IATIME4, ");
	bld.append("            CASE WHEN H_STMP = 0 THEN NULL ELSE H_STMP END H_STMP, ");
	bld.append("            CASE WHEN H_STIME = 0 THEN NULL ELSE H_STIME END H_STIME, ");
	bld.append("            CASE WHEN H_ATMP = 0 THEN NULL ELSE H_ATMP END H_ATMP, ");
	bld.append("            CASE WHEN H_ATIME = 0 THEN NULL ELSE H_ATIME END H_ATIME, ");
	bld.append("		DECODE(YRO,'00','',YRO) YRO, ");
	bld.append("            CASE WHEN YTMP = 0 THEN NULL ELSE YTMP END YTMP, ");
	bld.append("            CASE WHEN YTIME = 0 THEN NULL ELSE YTIME END YTIME, ");
	bld.append("		DECODE(YSPD,'0000000000',NULL,YSPD) YSPD, ");
	bld.append("            CASE WHEN ZTMP2 = 0 THEN NULL ELSE ZTMP2 END ZTMP2, ");
	bld.append("            CASE WHEN ZTIME2 = 0 THEN NULL ELSE ZTIME2 END ZTIME2, ");
	bld.append("            CASE WHEN ZTMP4 = 0 THEN NULL ELSE ZTMP4 END ZTMP4, ");
	bld.append("            CASE WHEN ZTIME4 = 0 THEN NULL ELSE ZTIME4 END ZTIME4, ");
	bld.append("            CASE WHEN KHARI1 = 0 THEN NULL ELSE KHARI1 END KHARI1, ");
	bld.append("            CASE WHEN KHARI2 = 0 THEN NULL ELSE KHARI2 END KHARI2, ");
	bld.append("            CASE WHEN KHARI3 = 0 THEN NULL ELSE KHARI3 END KHARI3, ");
	bld.append("            CASE WHEN KTAI1 = 0 THEN NULL ELSE KTAI1 END KTAI1, ");
	bld.append("            CASE WHEN KTAI2 = 0 THEN NULL ELSE KTAI2 END KTAI2, ");
	bld.append("            CASE WHEN KTAI3 = 0 THEN NULL ELSE KTAI3 END KTAI3, ");
	bld.append("            CASE WHEN KNOBI1 = 0 THEN NULL ELSE KNOBI1 END KNOBI1, ");
	bld.append("            CASE WHEN KNOBI2 = 0 THEN NULL ELSE KNOBI2 END KNOBI2, ");
	bld.append("            CASE WHEN KNOBI3 = 0 THEN NULL ELSE KNOBI3 END KNOBI3, ");
	bld.append("		DECODE(M_IACOD,' ','', M_IACOD) M_IACOD, ");
	bld.append("            CASE WHEN M_IAX1 = 0 THEN NULL ELSE M_IAX1 END M_IAX1, ");
	bld.append("            CASE WHEN M_IAX2 = 0 THEN NULL ELSE M_IAX2 END M_IAX2, ");
	bld.append("            CASE WHEN M_IAX3 = 0 THEN NULL ELSE M_IAX3 END M_IAX3, ");
	bld.append("            CASE WHEN M_IAX4 = 0 THEN NULL ELSE M_IAX4 END M_IAX4, ");
	bld.append("            CASE WHEN M_IART1 = 0 THEN NULL ELSE M_IART1 END M_IART1, ");
	bld.append("            CASE WHEN M_IART2 = 0 THEN NULL ELSE M_IART2 END M_IART2, ");
	bld.append("            CASE WHEN M_IART3 = 0 THEN NULL ELSE M_IART3 END M_IART3, ");
	bld.append("            CASE WHEN M_IART4 = 0 THEN NULL ELSE M_IART4 END M_IART4, ");
	bld.append("            CASE WHEN M_STMP = 0 THEN NULL ELSE M_STMP END M_STMP, ");
	bld.append("            CASE WHEN M_STIM = 0 THEN NULL ELSE M_STIM END M_STIM, ");
	bld.append("            CASE WHEN M_ATMP = 0 THEN NULL ELSE M_ATMP END M_ATMP, ");
	bld.append("            CASE WHEN M_ATIM = 0 THEN NULL ELSE M_ATIM END M_ATIM, ");
	bld.append("            CASE WHEN M_IATMP1 = 0 THEN NULL ELSE M_IATMP1 END M_IATMP1, ");
	bld.append("            CASE WHEN M_IATMP2 = 0 THEN NULL ELSE M_IATMP2 END M_IATMP2, ");
	bld.append("            CASE WHEN M_IATMP3 = 0 THEN NULL ELSE M_IATMP3 END M_IATMP3, ");
	bld.append("            CASE WHEN M_IATMP4 = 0 THEN NULL ELSE M_IATMP4 END M_IATMP4, ");
	bld.append("            CASE WHEN M_IATIM1 = 0 THEN NULL ELSE M_IATIM1 END M_IATIM1, ");
	bld.append("            CASE WHEN M_IATIM2 = 0 THEN NULL ELSE M_IATIM2 END M_IATIM2, ");
	bld.append("            CASE WHEN M_IATIM3 = 0 THEN NULL ELSE M_IATIM3 END M_IATIM3, ");
	bld.append("            CASE WHEN M_IATIM4 = 0 THEN NULL ELSE M_IATIM4 END M_IATIM4, ");
	bld.append("		DECODE(SHODON_FLG1,'0','','1','1 CAI指定','2','2 CAI優先','3','3 バッチ指定','4','4 バッチ優先',SHODON_FLG1) SHODON_FLG1, ");
	bld.append("		DECODE(SHODON_FLG2,'0','','1','1 CAF指定','2','2 CAF優先','3','3 バッチ指定','4','4 バッチ優先',SHODON_FLG2) SHODON_FLG2, ");
	bld.append("            CASE WHEN KHAX = 0 THEN NULL ELSE KHAX END KHAX, ");
	bld.append("            CASE WHEN KHIN = 0 THEN NULL ELSE KHIN END KHIN, ");
	bld.append("            CASE WHEN KHAV = 0 THEN NULL ELSE KHAV END KHAV, ");
	bld.append("            CASE WHEN KNAX = 0 THEN NULL ELSE KNAX END KNAX, ");
	bld.append("            CASE WHEN KNIN = 0 THEN NULL ELSE KNIN END KNIN, ");
	bld.append("            CASE WHEN KNAV = 0 THEN NULL ELSE KNAV END KNAV, ");
	bld.append("            CASE WHEN KTAX = 0 THEN NULL ELSE KTAX END KTAX, ");
	bld.append("            CASE WHEN KTIN = 0 THEN NULL ELSE KTIN END KTIN, ");
	bld.append("            CASE WHEN KTAV = 0 THEN NULL ELSE KTAV END KTAV, ");
	bld.append("            CASE WHEN J_KHAX = 0 THEN NULL ELSE J_KHAX END J_KHAX, ");
	bld.append("            CASE WHEN J_KHIN = 0 THEN NULL ELSE J_KHIN END J_KHIN, ");
	bld.append("            CASE WHEN J_KHAV = 0 THEN NULL ELSE J_KHAV END J_KHAV, ");
	bld.append("            CASE WHEN J_KNAX = 0 THEN NULL ELSE J_KNAX END J_KNAX, ");
	bld.append("            CASE WHEN J_KNIN = 0 THEN NULL ELSE J_KNIN END J_KNIN, ");
	bld.append("            CASE WHEN J_KNAV = 0 THEN NULL ELSE J_KNAV END J_KNAV, ");
	bld.append("            CASE WHEN J_KTAX = 0 THEN NULL ELSE J_KTAX END J_KTAX, ");
	bld.append("            CASE WHEN J_KTIN = 0 THEN NULL ELSE J_KTIN END J_KTIN, ");
	bld.append("            CASE WHEN J_KTAV = 0 THEN NULL ELSE J_KTAV END J_KTAV, ");
	bld.append("		DECODE(PLT1,'0','',PLT1) PLT1,DECODE(PLT2,'0','',PLT2) PLT2,DECODE(PLT3,'0','',PLT3) PLT3,DECODE(PLT4,'0','',PLT4) PLT4, ");
	bld.append("		DECODE(PKBN,'0','','1','中','2','高','') PKBN, ");
	bld.append("		DECODE(OPLT_AFLG,'0','',OPLT_AFLG) OPLT_AFLG, ");
	bld.append("		DECODE(OPLT_APLT,'0','','1','1X2','2','4X8','3','5X10','4','4000','') OPLT_APLT, ");
	bld.append("		DECODE(OPLT_ALOT1,'0000000','',OPLT_ALOT1) OPLT_ALOT1,DECODE(OPLT_ALOT2,'0000000','',OPLT_ALOT2) OPLT_ALOT2, ");
	bld.append("		DECODE(OPLT_ALOT3,'0000000','',OPLT_ALOT3) OPLT_ALOT3,DECODE(OPLT_ALOT4,'0000000','',OPLT_ALOT4) OPLT_ALOT4, ");
	bld.append("		DECODE(OPLT_NOKBN1,'0','','1','1X2','2','4X8','3','5X10','4','4000','') OPLT_NOKBN1, ");
	bld.append("		DECODE(OPLT_NO1,'000000','',OPLT_NO1) OPLT_NO1, ");
	bld.append("		DECODE(OPLT_NOKBN2,'0','','1','1X2','2','4X8','3','5X10','4','4000','') OPLT_NOKBN2, ");
	bld.append("		DECODE(OPLT_NO2,'000000','',OPLT_NO2) OPLT_NO2, ");
	bld.append("		DECODE(OPLT_NOKBN3,'0','','1','1X2','2','4X8','3','5X10','4','4000','') OPLT_NOKBN3, ");
	bld.append("		DECODE(OPLT_NO3,'000000','',OPLT_NO3) OPLT_NO3, ");
	bld.append("		DECODE(OPLT_NOKBN4,'0','','1','1X2','2','4X8','3','5X10','4','4000','') OPLT_NOKBN4, ");
	bld.append("		DECODE(OPLT_NO4,'000000','',OPLT_NO4) OPLT_NO4, ");
	bld.append("		DECODE(JRFLG,'0','',JRFLG) JRFLG, ");
	bld.append("		TO_CHAR(JRSTDT,'YY/MM/DD HH24:MI') JRSTDT, ");
	bld.append("		TO_CHAR(JREDDT,'YY/MM/DD HH24:MI') JREDDT, ");
	bld.append("            CASE WHEN JRINT = 0 THEN NULL ELSE ROUND(JRINT/60,2) END JRINT, ");
	bld.append("            CASE WHEN JRTMP = 0 THEN NULL ELSE JRTMP END JRTMP ");
	bld.append("	FROM ");
	bld.append("			DAT_S_JDF_COM D1, ");
	bld.append("			DAT_S_LOT_COM_KINS D2, ");
	bld.append("			DAT_S_LOT_FIX D3 ");
	bld.append("	WHERE ");
	bld.append("			D1.LINKKEY = D2.LINKKEY(+) ");
	bld.append("		AND	D1.TOROKUDATE = D2.TOROKUDATE(+) ");
	bld.append("    	AND	D1.LINKKEY = D3.LINKKEY(+) ");
	bld.append("		AND	D1.TOROKUDATE = D3.TOROKUDATE(+) ");
	// 2014/09/25 追加 ▲

	// 2016/04/24 追加 ▼
	bld.append("	UNION ALL ");
	bld.append("	SELECT ");
	bld.append("		'5' TBLORDER, ");
	bld.append("		D1.LINKKEY, ");
	bld.append("            CASE WHEN H_IATMP1 = 0 THEN NULL ELSE H_IATMP1 END H_IATMP1, ");
	bld.append("            CASE WHEN H_IATMP2 = 0 THEN NULL ELSE H_IATMP2 END H_IATMP2, ");
	bld.append("            CASE WHEN H_IATMP3 = 0 THEN NULL ELSE H_IATMP3 END H_IATMP3, ");
	bld.append("            CASE WHEN H_IATMP4 = 0 THEN NULL ELSE H_IATMP4 END H_IATMP4, ");
	bld.append("            CASE WHEN H_IATIME1 = 0 THEN NULL ELSE H_IATIME1 END H_IATIME1, ");
	bld.append("            CASE WHEN H_IATIME2 = 0 THEN NULL ELSE H_IATIME2 END H_IATIME2, ");
	bld.append("            CASE WHEN H_IATIME3 = 0 THEN NULL ELSE H_IATIME3 END H_IATIME3, ");
	bld.append("            CASE WHEN H_IATIME4 = 0 THEN NULL ELSE H_IATIME4 END H_IATIME4, ");
	bld.append("            CASE WHEN H_STMP = 0 THEN NULL ELSE H_STMP END H_STMP, ");
	bld.append("            CASE WHEN H_STIME = 0 THEN NULL ELSE H_STIME END H_STIME, ");
	bld.append("            CASE WHEN H_ATMP = 0 THEN NULL ELSE H_ATMP END H_ATMP, ");
	bld.append("            CASE WHEN H_ATIME = 0 THEN NULL ELSE H_ATIME END H_ATIME, ");
	bld.append("		DECODE(YRO,'00','',YRO) YRO, ");
	bld.append("            CASE WHEN YTMP = 0 THEN NULL ELSE YTMP END YTMP, ");
	bld.append("            CASE WHEN YTIME = 0 THEN NULL ELSE YTIME END YTIME, ");
	bld.append("		DECODE(YSPD,'0000000000',NULL,YSPD) YSPD, ");
	bld.append("            CASE WHEN ZTMP2 = 0 THEN NULL ELSE ZTMP2 END ZTMP2, ");
	bld.append("            CASE WHEN ZTIME2 = 0 THEN NULL ELSE ZTIME2 END ZTIME2, ");
	bld.append("            CASE WHEN ZTMP4 = 0 THEN NULL ELSE ZTMP4 END ZTMP4, ");
	bld.append("            CASE WHEN ZTIME4 = 0 THEN NULL ELSE ZTIME4 END ZTIME4, ");
	bld.append("            CASE WHEN KHARI1 = 0 THEN NULL ELSE KHARI1 END KHARI1, ");
	bld.append("            CASE WHEN KHARI2 = 0 THEN NULL ELSE KHARI2 END KHARI2, ");
	bld.append("            CASE WHEN KHARI3 = 0 THEN NULL ELSE KHARI3 END KHARI3, ");
	bld.append("            CASE WHEN KTAI1 = 0 THEN NULL ELSE KTAI1 END KTAI1, ");
	bld.append("            CASE WHEN KTAI2 = 0 THEN NULL ELSE KTAI2 END KTAI2, ");
	bld.append("            CASE WHEN KTAI3 = 0 THEN NULL ELSE KTAI3 END KTAI3, ");
	bld.append("            CASE WHEN KNOBI1 = 0 THEN NULL ELSE KNOBI1 END KNOBI1, ");
	bld.append("            CASE WHEN KNOBI2 = 0 THEN NULL ELSE KNOBI2 END KNOBI2, ");
	bld.append("            CASE WHEN KNOBI3 = 0 THEN NULL ELSE KNOBI3 END KNOBI3, ");
	bld.append("		DECODE(M_IACOD,' ','', M_IACOD) M_IACOD, ");
	bld.append("            CASE WHEN M_IAX1 = 0 THEN NULL ELSE M_IAX1 END M_IAX1, ");
	bld.append("            CASE WHEN M_IAX2 = 0 THEN NULL ELSE M_IAX2 END M_IAX2, ");
	bld.append("            CASE WHEN M_IAX3 = 0 THEN NULL ELSE M_IAX3 END M_IAX3, ");
	bld.append("            CASE WHEN M_IAX4 = 0 THEN NULL ELSE M_IAX4 END M_IAX4, ");
	bld.append("            CASE WHEN M_IART1 = 0 THEN NULL ELSE M_IART1 END M_IART1, ");
	bld.append("            CASE WHEN M_IART2 = 0 THEN NULL ELSE M_IART2 END M_IART2, ");
	bld.append("            CASE WHEN M_IART3 = 0 THEN NULL ELSE M_IART3 END M_IART3, ");
	bld.append("            CASE WHEN M_IART4 = 0 THEN NULL ELSE M_IART4 END M_IART4, ");
	bld.append("            CASE WHEN M_STMP = 0 THEN NULL ELSE M_STMP END M_STMP, ");
	bld.append("            CASE WHEN M_STIM = 0 THEN NULL ELSE M_STIM END M_STIM, ");
	bld.append("            CASE WHEN M_ATMP = 0 THEN NULL ELSE M_ATMP END M_ATMP, ");
	bld.append("            CASE WHEN M_ATIM = 0 THEN NULL ELSE M_ATIM END M_ATIM, ");
	bld.append("            CASE WHEN M_IATMP1 = 0 THEN NULL ELSE M_IATMP1 END M_IATMP1, ");
	bld.append("            CASE WHEN M_IATMP2 = 0 THEN NULL ELSE M_IATMP2 END M_IATMP2, ");
	bld.append("            CASE WHEN M_IATMP3 = 0 THEN NULL ELSE M_IATMP3 END M_IATMP3, ");
	bld.append("            CASE WHEN M_IATMP4 = 0 THEN NULL ELSE M_IATMP4 END M_IATMP4, ");
	bld.append("            CASE WHEN M_IATIM1 = 0 THEN NULL ELSE M_IATIM1 END M_IATIM1, ");
	bld.append("            CASE WHEN M_IATIM2 = 0 THEN NULL ELSE M_IATIM2 END M_IATIM2, ");
	bld.append("            CASE WHEN M_IATIM3 = 0 THEN NULL ELSE M_IATIM3 END M_IATIM3, ");
	bld.append("            CASE WHEN M_IATIM4 = 0 THEN NULL ELSE M_IATIM4 END M_IATIM4, ");
	bld.append("		DECODE(SHODON_FLG1,'0','','1','1 CAI指定','2','2 CAI優先','3','3 バッチ指定','4','4 バッチ優先',SHODON_FLG1) SHODON_FLG1, ");
	bld.append("		DECODE(SHODON_FLG2,'0','','1','1 CAF指定','2','2 CAF優先','3','3 バッチ指定','4','4 バッチ優先',SHODON_FLG2) SHODON_FLG2, ");
	bld.append("            CASE WHEN KHAX = 0 THEN NULL ELSE KHAX END KHAX, ");
	bld.append("            CASE WHEN KHIN = 0 THEN NULL ELSE KHIN END KHIN, ");
	bld.append("            CASE WHEN KHAV = 0 THEN NULL ELSE KHAV END KHAV, ");
	bld.append("            CASE WHEN KNAX = 0 THEN NULL ELSE KNAX END KNAX, ");
	bld.append("            CASE WHEN KNIN = 0 THEN NULL ELSE KNIN END KNIN, ");
	bld.append("            CASE WHEN KNAV = 0 THEN NULL ELSE KNAV END KNAV, ");
	bld.append("            CASE WHEN KTAX = 0 THEN NULL ELSE KTAX END KTAX, ");
	bld.append("            CASE WHEN KTIN = 0 THEN NULL ELSE KTIN END KTIN, ");
	bld.append("            CASE WHEN KTAV = 0 THEN NULL ELSE KTAV END KTAV, ");
	bld.append("            CASE WHEN J_KHAX = 0 THEN NULL ELSE J_KHAX END J_KHAX, ");
	bld.append("            CASE WHEN J_KHIN = 0 THEN NULL ELSE J_KHIN END J_KHIN, ");
	bld.append("            CASE WHEN J_KHAV = 0 THEN NULL ELSE J_KHAV END J_KHAV, ");
	bld.append("            CASE WHEN J_KNAX = 0 THEN NULL ELSE J_KNAX END J_KNAX, ");
	bld.append("            CASE WHEN J_KNIN = 0 THEN NULL ELSE J_KNIN END J_KNIN, ");
	bld.append("            CASE WHEN J_KNAV = 0 THEN NULL ELSE J_KNAV END J_KNAV, ");
	bld.append("            CASE WHEN J_KTAX = 0 THEN NULL ELSE J_KTAX END J_KTAX, ");
	bld.append("            CASE WHEN J_KTIN = 0 THEN NULL ELSE J_KTIN END J_KTIN, ");
	bld.append("            CASE WHEN J_KTAV = 0 THEN NULL ELSE J_KTAV END J_KTAV, ");
	bld.append("		DECODE(PLT1,'0','',PLT1) PLT1,DECODE(PLT2,'0','',PLT2) PLT2,DECODE(PLT3,'0','',PLT3) PLT3,DECODE(PLT4,'0','',PLT4) PLT4, ");
	bld.append("		DECODE(PKBN,'0','','1','中','2','高','') PKBN, ");
	bld.append("		DECODE(OPLT_AFLG,'0','',OPLT_AFLG) OPLT_AFLG, ");
	bld.append("		DECODE(OPLT_APLT,'0','','1','1X2','2','4X8','3','5X10','4','4000','') OPLT_APLT, ");
	bld.append("		DECODE(OPLT_ALOT1,'0000000','',OPLT_ALOT1) OPLT_ALOT1,DECODE(OPLT_ALOT2,'0000000','',OPLT_ALOT2) OPLT_ALOT2, ");
	bld.append("		DECODE(OPLT_ALOT3,'0000000','',OPLT_ALOT3) OPLT_ALOT3,DECODE(OPLT_ALOT4,'0000000','',OPLT_ALOT4) OPLT_ALOT4, ");
	bld.append("		DECODE(OPLT_NOKBN1,'0','','1','1X2','2','4X8','3','5X10','4','4000','') OPLT_NOKBN1, ");
	bld.append("		DECODE(OPLT_NO1,'000000','',OPLT_NO1) OPLT_NO1, ");
	bld.append("		DECODE(OPLT_NOKBN2,'0','','1','1X2','2','4X8','3','5X10','4','4000','') OPLT_NOKBN2, ");
	bld.append("		DECODE(OPLT_NO2,'000000','',OPLT_NO2) OPLT_NO2, ");
	bld.append("		DECODE(OPLT_NOKBN3,'0','','1','1X2','2','4X8','3','5X10','4','4000','') OPLT_NOKBN3, ");
	bld.append("		DECODE(OPLT_NO3,'000000','',OPLT_NO3) OPLT_NO3, ");
	bld.append("		DECODE(OPLT_NOKBN4,'0','','1','1X2','2','4X8','3','5X10','4','4000','') OPLT_NOKBN4, ");
	bld.append("		DECODE(OPLT_NO4,'000000','',OPLT_NO4) OPLT_NO4, ");
	bld.append("		DECODE(JRFLG,'0','',JRFLG) JRFLG, ");
	bld.append("		TO_CHAR(JRSTDT,'YY/MM/DD HH24:MI') JRSTDT, ");
	bld.append("		TO_CHAR(JREDDT,'YY/MM/DD HH24:MI') JREDDT, ");
	bld.append("            CASE WHEN JRINT = 0 THEN NULL ELSE ROUND(JRINT/60,2) END JRINT, ");
	bld.append("            CASE WHEN JRTMP = 0 THEN NULL ELSE JRTMP END JRTMP ");
	bld.append("	FROM ");
	bld.append("			DAT_L_JDF_COM D1, ");
	bld.append("			DAT_L_LOT_COM_KINS D2, ");
	bld.append("			DAT_L_LOT_FIX D3 ");
	bld.append("	WHERE ");
	bld.append("			D1.LINKKEY = D2.LINKKEY(+) ");
	bld.append("		AND	TRUNC(D1.TOROKUDATE) = TRUNC(D2.TOROKUDATE(+)) ");
	bld.append("    	AND	D1.LINKKEY = D3.LINKKEY(+) ");
	bld.append("		AND	TRUNC(D1.TOROKUDATE) = TRUNC(D3.TOROKUDATE(+)) ");
	// 2016/04/24 追加 ▲


	bld.append(") ");
	bld.append("WHERE  ");
	bld.append("	LINKKEY = ? ");
	bld.append("	AND TBLORDER = ? ");
        return bld.toString();
    }

    /**
     * 徐冷　焼鈍情報
     * 2012/12/14 add
     */
    public static String getCFShodonQuery() {
        StringBuilder bld = new StringBuilder();
	bld.append("SELECT * FROM ");
	bld.append("( ");
	bld.append("	SELECT  ");
	// DAT_LOT_COM_JBOX
	bld.append("		'1' TBLORDER, ");
	bld.append("		D1.LINKKEY, ");
	bld.append("		D1.BOXNO, ");
	bld.append("		JBSM, ");
	bld.append("            CASE WHEN JBX = 0 THEN NULL ELSE JBX END JBX, ");
	bld.append("            CASE WHEN JBY = 0 THEN NULL ELSE JBY END JBY, ");
	bld.append("            CASE WHEN JBZ = 0 THEN NULL ELSE JBZ END JBZ, ");
	bld.append("            CASE WHEN JBRW = 0 THEN NULL ELSE JBRW END JBRW, ");
	bld.append("            CASE WHEN JBRMH = 0 THEN NULL ELSE JBRMH END JBRMH, ");
/*
	bld.append("		TO_CHAR(JBSDTM,'MM/DD HH24:MI') JBSDTM, ");
	bld.append("		TO_CHAR(JBEDTM,'MM/DD HH24:MI') JBEDTM, ");
*/
	bld.append("		TO_CHAR(D3.JBSDATE,'YY/MM/DD HH24:MI') JBSDTM, ");
	bld.append("		TO_CHAR(D3.JBEDATE,'YY/MM/DD HH24:MI') JBEDTM, ");
	bld.append("            CASE WHEN JBINT = 0 THEN NULL ELSE ROUND(JBINT/60,2) END JBINT, ");
	// DAT_LOT_COM_CF
	bld.append("            CASE WHEN JBRNO = 0 THEN NULL ELSE JBRNO END JBRNO, ");
	bld.append("            CASE WHEN JBRJN = 0 THEN NULL ELSE JBRJN END JBRJN, ");
	bld.append("		JBRKB, ");
	bld.append("            CASE WHEN JBRTMP = 0 THEN NULL ELSE JBRTMP END JBRTMP, ");
	bld.append("		D4.CYUZO_YY ");
	bld.append("	FROM ");
	bld.append("		DAT_LOT_COM_JBOX D1, ");
	bld.append("		DAT_LOT_COM_CF D2, ");
	bld.append("		DAT_LOT_COM_JBOXEX D3, ");
	bld.append("		DAT_JDF_COM D4 "); // 2014/09/30 追加
	bld.append("	WHERE ");
	bld.append("		    D1.LINKKEY = D2.LINKKEY(+) ");
	bld.append("		AND D1.BOXNO   = D2.BOXNO(+) ");
	bld.append("		AND D1.LINKKEY = D3.LINKKEY(+) ");
	bld.append("		AND D1.BOXNO   = D3.BOXNO(+) ");
	bld.append("		AND D1.LINKKEY = D4.LINKKEY(+) ");
	bld.append("		AND D4.JYOTAI = 1 ");

	bld.append("	UNION ALL ");

	// 2017/10/27 ADD-START
	/*
	bld.append("	SELECT  ");
	bld.append("		D1.LINKKEY, ");
	bld.append("		D1.BOXNO, ");
	bld.append("		NULL, ");
	bld.append("		NULL, ");
	bld.append("		NULL, ");
	bld.append("		NULL, ");
	bld.append("		NULL, ");
	bld.append("		NULL, ");
	bld.append("		NULL, ");
	bld.append("		NULL, ");
	bld.append("		NULL, ");
	bld.append("		NULL, ");
	bld.append("		NULL, ");
	bld.append("		NULL, ");
	bld.append("		NULL, ");
	bld.append("		D3.CYUZO_YY, ");
	bld.append("	FROM ");
	bld.append("		DAT_JDF_COM_SBOX D1, ");
	bld.append("		DAT_JDF_COM_SBOXEX D2, ");
	bld.append("		DAT_JDF_COM D3 ");
	bld.append("	WHERE ");
	bld.append("		    D1.LINKKEY = D2.LINKKEY(+) ");
	bld.append("		AND D1.BOXNO   = D2.BOXNO(+) ");
	bld.append("		AND D1.LINKKEY = D3.LINKKEY(+) ");
	bld.append("		AND D3.JYOTAI = 1 ");
	bld.append("	UNION ALL ");
	*/
	// 2017/10/27 ADD-END

	bld.append("	SELECT ");
	bld.append("		'3' TBLORDER, ");
	bld.append("		D1.LINKKEY, ");
	bld.append("		D1.BOXNO, ");
	bld.append("		JBSM, ");
	bld.append("            CASE WHEN JBX = 0 THEN NULL ELSE JBX END JBX, ");
	bld.append("            CASE WHEN JBY = 0 THEN NULL ELSE JBY END JBY, ");
	bld.append("            CASE WHEN JBZ = 0 THEN NULL ELSE JBZ END JBZ, ");
	bld.append("            CASE WHEN JBRW = 0 THEN NULL ELSE JBRW END JBRW, ");
	bld.append("            CASE WHEN JBRMH = 0 THEN NULL ELSE JBRMH END JBRMH, ");
/*
	bld.append("		TO_CHAR(JBSDTM,'MM/DD HH24:MI') JBSDTM, ");
	bld.append("		TO_CHAR(JBEDTM,'MM/DD HH24:MI') JBEDTM, ");
*/
	bld.append("		TO_CHAR(D3.JBSDATE,'YY/MM/DD HH24:MI') JBSDTM, ");
	bld.append("		TO_CHAR(D3.JBEDATE,'YY/MM/DD HH24:MI') JBEDTM, ");
	bld.append("            CASE WHEN JBINT = 0 THEN NULL ELSE ROUND(JBINT/60,2) END JBINT, ");
	// DAT_R_LOT_COM_CF
	bld.append("            CASE WHEN JBRNO = 0 THEN NULL ELSE JBRNO END JBRNO, ");
	bld.append("            CASE WHEN JBRJN = 0 THEN NULL ELSE JBRJN END JBRJN, ");
	bld.append("		JBRKB, ");
	bld.append("            CASE WHEN JBRTMP = 0 THEN NULL ELSE JBRTMP END JBRTMP, ");
	bld.append("		D4.CYUZO_YY ");
	bld.append("	FROM ");
	bld.append("		DAT_R_LOT_COM_JBOX D1, ");
	bld.append("		DAT_R_LOT_COM_CF D2, ");
	bld.append("		DAT_R_LOT_COM_JBOXEX D3, "); // 2014/09/25 修正
	bld.append("		DAT_R_JDF_COM D4 "); // 2014/09/30 追加
	bld.append("	WHERE ");
	bld.append("		    D1.LINKKEY = D2.LINKKEY(+) ");
	bld.append("		AND D1.BOXNO   = D2.BOXNO(+) ");
	bld.append("		AND D1.LINKKEY = D3.LINKKEY(+) ");
	bld.append("		AND D1.BOXNO   = D3.BOXNO(+) ");
	bld.append("		AND D1.LINKKEY = D4.LINKKEY(+) ");
	bld.append("		AND D4.JYOTAI = 2 ");
	// 2014/09/25 追加 ▼
	bld.append("	UNION ALL ");
	bld.append("	SELECT ");
	bld.append("		'4' TBLORDER, ");
	bld.append("		D1.LINKKEY, ");
	bld.append("		D1.BOXNO, ");
	bld.append("		JBSM, ");
	bld.append("            CASE WHEN JBX = 0 THEN NULL ELSE JBX END JBX, ");
	bld.append("            CASE WHEN JBY = 0 THEN NULL ELSE JBY END JBY, ");
	bld.append("            CASE WHEN JBZ = 0 THEN NULL ELSE JBZ END JBZ, ");
	bld.append("            CASE WHEN JBRW = 0 THEN NULL ELSE JBRW END JBRW, ");
	bld.append("            CASE WHEN JBRMH = 0 THEN NULL ELSE JBRMH END JBRMH, ");
	bld.append("		TO_CHAR(D3.JBSDATE,'YY/MM/DD HH24:MI') JBSDTM, ");
	bld.append("		TO_CHAR(D3.JBEDATE,'YY/MM/DD HH24:MI') JBEDTM, ");
	bld.append("            CASE WHEN JBINT = 0 THEN NULL ELSE ROUND(JBINT/60,2) END JBINT, ");
	bld.append("            CASE WHEN JBRNO = 0 THEN NULL ELSE JBRNO END JBRNO, ");
	bld.append("            CASE WHEN JBRJN = 0 THEN NULL ELSE JBRJN END JBRJN, ");
	bld.append("		JBRKB, ");
	bld.append("            CASE WHEN JBRTMP = 0 THEN NULL ELSE JBRTMP END JBRTMP, ");
	bld.append("		D4.CYUZO_YY ");
	bld.append("	FROM ");
	bld.append("		DAT_S_LOT_COM_JBOX D1, ");
	bld.append("		DAT_S_LOT_COM_CF D2, ");
	bld.append("		DAT_S_LOT_COM_JBOXEX D3, ");
	bld.append("		DAT_S_JDF_COM D4 "); // 2014/09/30 追加
	bld.append("	WHERE ");
	bld.append("		    D1.LINKKEY = D2.LINKKEY(+) ");
	bld.append("		AND D1.TOROKUDATE = D2.TOROKUDATE(+) ");
	bld.append("		AND D1.BOXNO = D2.BOXNO(+) ");
	bld.append("		AND D1.LINKKEY = D3.LINKKEY(+) ");
	bld.append("		AND D1.TOROKUDATE = D3.TOROKUDATE(+) ");
	bld.append("		AND D1.BOXNO = D3.BOXNO(+) ");
	bld.append("		AND D1.LINKKEY = D4.LINKKEY(+) ");
	bld.append("		AND D1.TOROKUDATE = D4.TOROKUDATE(+) ");
	// 2014/09/25 追加 ▲

	// 2016/04/24 追加 ▼
	bld.append("	UNION ALL ");
	bld.append("	SELECT ");
	bld.append("		'5' TBLORDER, ");
	bld.append("		D1.LINKKEY, ");
	bld.append("		D1.BOXNO, ");
	bld.append("		JBSM, ");
	bld.append("            CASE WHEN JBX = 0 THEN NULL ELSE JBX END JBX, ");
	bld.append("            CASE WHEN JBY = 0 THEN NULL ELSE JBY END JBY, ");
	bld.append("            CASE WHEN JBZ = 0 THEN NULL ELSE JBZ END JBZ, ");
	bld.append("            CASE WHEN JBRW = 0 THEN NULL ELSE JBRW END JBRW, ");
	bld.append("            CASE WHEN JBRMH = 0 THEN NULL ELSE JBRMH END JBRMH, ");
	bld.append("		TO_CHAR(D3.JBSDATE,'YY/MM/DD HH24:MI') JBSDTM, ");
	bld.append("		TO_CHAR(D3.JBEDATE,'YY/MM/DD HH24:MI') JBEDTM, ");
	bld.append("            CASE WHEN JBINT = 0 THEN NULL ELSE ROUND(JBINT/60,2) END JBINT, ");
	bld.append("            CASE WHEN JBRNO = 0 THEN NULL ELSE JBRNO END JBRNO, ");
	bld.append("            CASE WHEN JBRJN = 0 THEN NULL ELSE JBRJN END JBRJN, ");
	bld.append("		JBRKB, ");
	bld.append("            CASE WHEN JBRTMP = 0 THEN NULL ELSE JBRTMP END JBRTMP, ");
	bld.append("		D4.CYUZO_YY ");
	bld.append("	FROM ");
	bld.append("		DAT_L_LOT_COM_JBOX D1, ");
	bld.append("		DAT_L_LOT_COM_CF D2, ");
	bld.append("		DAT_L_LOT_COM_JBOXEX D3, ");
	bld.append("		DAT_L_JDF_COM D4 ");
	bld.append("	WHERE ");
	bld.append("		    D1.LINKKEY = D2.LINKKEY(+) ");
	bld.append("		AND TRUNC(D1.TOROKUDATE) = TRUNC(D2.TOROKUDATE(+)) ");
	bld.append("		AND D1.BOXNO = D2.BOXNO(+) ");
	bld.append("		AND D1.LINKKEY = D3.LINKKEY(+) ");
	bld.append("		AND TRUNC(D1.TOROKUDATE) = TRUNC(D3.TOROKUDATE(+)) ");
	bld.append("		AND D1.BOXNO = D3.BOXNO(+) ");
	bld.append("		AND D1.LINKKEY = D4.LINKKEY(+) ");
	bld.append("		AND TRUNC(D1.TOROKUDATE) = TRUNC(D4.TOROKUDATE(+)) ");
	// 2016/04/24 追加 ▲
	bld.append(") ");
	bld.append("WHERE ");
	bld.append("	    LINKKEY = ? ");
	bld.append("	AND CYUZO_YY = ? ");
	bld.append("	AND JBSM IN ('ANI','ANF','CAL','QF') ");
	bld.append("	AND TBLORDER = ? ");
	bld.append("ORDER BY BOXNO ");

        return bld.toString();
    }

    public LotDspService() {
    }

    /*
     *  DB設定
     */
    public DriverManagerDataSource ConnectDataSource(){
    	// データソースへの接続
    	DriverManagerDataSource dataSource = new DriverManagerDataSource();
    	//プロパティファイルから読み込み
    	EnvProperties.reload();
    	dataSource.setDriverClassName(EnvProperties.get("lotdsp.driver.class.name")); //推奨
    	dataSource.setUrl(EnvProperties.get("lotdsp.datasource"));
	  	dataSource.setUsername(EnvProperties.get("lotdsp.database.user"));
	  	dataSource.setPassword(EnvProperties.get("lotdsp.database.password"));

//	  dataSource.setUrl( "jdbc:oracle:thin:@nlmfangynavd:1521:OPNWDR1"); // ***本番***
//	  dataSource.setUrl( "jdbc:oracle:thin:@nlmfangydev1:1521:OPNWDD1"); //TEST
//	  dataSource.setUrl( "jdbc:oracle:thin:@nlmfangysysd:1521:OPNMDR1"); // 公開DB
//	  dataSource.setUrl( "jdbc:oracle:thin:@nlmfangydev1:1521:OPNMDD1");

	  	return dataSource;
	}

    ///検索キー（LINKKEY）取得
    private class FindSearchKeyQuery extends MappingSqlQuery {
        public FindSearchKeyQuery(DataSource ds, Map params, SearchKeyQuery common) {
            //super(ds, STAFF_COMMON_QUERY);
            //super(ds, getSQL());
            super(ds, common.getSQL());

            if(params.containsKey("LINKKEY")) {
                if(!((params.get("LINKKEY") == null) || (params.get("LINKKEY").equals("")))) {
                    super.declareParameter(new SqlParameter("LINKKEY", Types.VARCHAR));
                }
            }

            if(params.containsKey("LTNO")) {
                if(!((params.get("LTNO") == null) || (params.get("LTNO").equals("")))) {
                    super.declareParameter(new SqlParameter("LTNO", Types.VARCHAR));
                }
            }

            if(params.containsKey("CYNO")) {
                if(!((params.get("CYNO") == null) || (params.get("CYNO").equals("")))) {
                    super.declareParameter(new SqlParameter("CYNO", Types.VARCHAR));
                }
            }

            if(params.containsKey("KNNO")) {
                if(!((params.get("KNNO") == null) || (params.get("KNNO").equals("")))) {
                    super.declareParameter(new SqlParameter("KNNO", Types.VARCHAR));
                }
            }

            if(params.containsKey("JUNO")) {
                if(!((params.get("JUNO") == null) || (params.get("JUNO").equals("")))) {
                    super.declareParameter(new SqlParameter("JUNO", Types.VARCHAR));
                }
            }

            if(params.containsKey("CYUZO_YY")) {
                if(!((params.get("CYUZO_YY") == null))) {
                    super.declareParameter(new SqlParameter("CYUZO_YY", Types.DECIMAL));
                }
            }

            compile();

        }
        public Object mapRow(ResultSet rs, int rowNumber) throws SQLException {
            SearchKeyBean result = (SearchKeyBean)new BeanHandlerService(SearchKeyBean.class).handle(rs);
            return result;
        }
    }

    // ロット問合せユーザーSQL
    private class FindLotDspUsersQuery extends MappingSqlQuery {
        public FindLotDspUsersQuery(DataSource ds) {
            super(ds, LOTDSP_USERS_QUERY);
            super.declareParameter(new SqlParameter("USER_ID", Types.VARCHAR));
            compile();
        }
        public Object mapRow(ResultSet rs, int rowNumber) throws SQLException {
            LotDspUserBean result = (LotDspUserBean)new BeanHandlerService(LotDspUserBean.class).handle(rs);
            return result;
        }
    }

    ///ｽﾀｯﾌ版ﾛｯﾄ情報SQL実行　共有
    private class FindStaffCommonQuery extends MappingSqlQuery {
        public FindStaffCommonQuery(DataSource ds) {
            super(ds, STAFF_COMMON_QUERY);
            super.declareParameter(new SqlParameter("LINKKEY", Types.VARCHAR));
	    super.declareParameter(new SqlParameter("CYUZO_YY", Types.DECIMAL)); //ADD
	    super.declareParameter(new SqlParameter("TBLORDER", Types.VARCHAR));
	    //System.out.println(STAFF_COMMON_QUERY);
            compile();
        }
        public Object mapRow(ResultSet rs, int rowNumber) throws SQLException {
            StaffCommonBean result = (StaffCommonBean)new BeanHandlerService(StaffCommonBean.class).handle(rs);
            return result;
        }
    }

    ///ｽﾀｯﾌ版ﾛｯﾄ情報SQL実行　進度情報
    private class FindStaffProgressQuery extends MappingSqlQuery {
        public FindStaffProgressQuery(DataSource ds) {
            super(ds, STAFF_PROGRESS_QUERY);
            super.declareParameter(new SqlParameter("LINKKEY", Types.VARCHAR));
            super.declareParameter(new SqlParameter("TBLORDER", Types.VARCHAR));
            compile();
        }
        public Object mapRow(ResultSet rs, int rowNumber) throws SQLException {
            StaffProgressBean result = (StaffProgressBean)new BeanHandlerService(StaffProgressBean.class).handle(rs);
            return result;
        }
    }

    ///ｽﾀｯﾌ版ﾛｯﾄ情報SQL実行　進度情報 設備BOX
    private class FindStaffProgressBoxQuery extends MappingSqlQuery {
        public FindStaffProgressBoxQuery(DataSource ds) {
            super(ds, STAFF_PROGRESS_BOX_QUERY);
            super.declareParameter(new SqlParameter("LINKKEY", Types.VARCHAR));
            super.declareParameter(new SqlParameter("CYUZO_YY", Types.DECIMAL));
            super.declareParameter(new SqlParameter("TBLORDER", Types.VARCHAR));
            compile();
        }
        public Object mapRow(ResultSet rs, int rowNumber) throws SQLException {
            StaffProgressBoxBean result = (StaffProgressBoxBean)new BeanHandlerService(StaffProgressBoxBean.class).handle(rs);
            return result;
        }
    }

    ///ｽﾀｯﾌ版ﾛｯﾄ情報SQL実行　進度情報 設計設備BOX
    private class FindStaffProgressSBoxQuery extends MappingSqlQuery {
        public FindStaffProgressSBoxQuery(DataSource ds) {
            super(ds, STAFF_PROGRESS_SBOX_QUERY);
            super.declareParameter(new SqlParameter("LINKKEY", Types.VARCHAR));
            super.declareParameter(new SqlParameter("CYUZO_YY", Types.DECIMAL));
            super.declareParameter(new SqlParameter("TBLORDER", Types.VARCHAR));
            compile();
        }
        public Object mapRow(ResultSet rs, int rowNumber) throws SQLException {
            StaffProgressSBoxBean result = (StaffProgressSBoxBean)new BeanHandlerService(StaffProgressSBoxBean.class).handle(rs);
            return result;
        }
    }

    ///ｽﾀｯﾌ版ﾛｯﾄ情報SQL実行　進度情報 実績設備BOX
    private class FindStaffProgressJBoxQuery extends MappingSqlQuery {
        public FindStaffProgressJBoxQuery(DataSource ds) {
            super(ds, STAFF_PROGRESS_JBOX_QUERY);
            super.declareParameter(new SqlParameter("LINKKEY1", Types.VARCHAR));
            super.declareParameter(new SqlParameter("LINKKEY2", Types.VARCHAR));
            super.declareParameter(new SqlParameter("LINKKEY3", Types.VARCHAR));
            super.declareParameter(new SqlParameter("CYUZO_YY", Types.DECIMAL));
            super.declareParameter(new SqlParameter("TBLORDER", Types.VARCHAR));
	    //System.out.println("@SQL(進度情報 実績):" + STAFF_PROGRESS_JBOX_QUERY);
            compile();
        }
        public Object mapRow(ResultSet rs, int rowNumber) throws SQLException {
            StaffProgressJBoxBean result = (StaffProgressJBoxBean)new BeanHandlerService(StaffProgressJBoxBean.class).handle(rs);
            return result;
        }
    }
    ///ｽﾀｯﾌ版ﾛｯﾄ情報SQL実行　製造情報
    private class FindStaffManufactureQuery extends MappingSqlQuery {
        public FindStaffManufactureQuery(DataSource ds) {
            super(ds, STAFF_MANUFACTURE_QUERY);
            super.declareParameter(new SqlParameter("LINKKEY", Types.VARCHAR));
            super.declareParameter(new SqlParameter("TBLORDER", Types.VARCHAR));
            compile();
        }
        public Object mapRow(ResultSet rs, int rowNumber) throws SQLException {
            StaffManufactureBean result = (StaffManufactureBean)new BeanHandlerService(StaffManufactureBean.class).handle(rs);
            return result;
        }
    }

    ///ｽﾀｯﾌ版ﾛｯﾄ情報SQL実行　試験情報
    private class FindStaffTestQuery extends MappingSqlQuery {
        public FindStaffTestQuery(DataSource ds) {
            super(ds, STAFF_TEST_QUERY);
            super.declareParameter(new SqlParameter("LINKKEY1", Types.VARCHAR));
            super.declareParameter(new SqlParameter("LINKKEY2", Types.VARCHAR));
            super.declareParameter(new SqlParameter("LINKKEY3", Types.VARCHAR));
            super.declareParameter(new SqlParameter("TBLORDER", Types.VARCHAR));
            compile();
        }
        public Object mapRow(ResultSet rs, int rowNumber) throws SQLException {
            StaffTestBean result = (StaffTestBean)new BeanHandlerService(StaffTestBean.class).handle(rs);
            return result;
        }
    }

    ///ｽﾀｯﾌ版ﾛｯﾄ情報SQL実行　品質情報
    private class FindStaffQualityQuery extends MappingSqlQuery {
        public FindStaffQualityQuery(DataSource ds) {
            super(ds, STAFF_QUALITY_QUERY);
            super.declareParameter(new SqlParameter("LINKKEY", Types.VARCHAR));
            compile();
        }
        public Object mapRow(ResultSet rs, int rowNumber) throws SQLException {
            StaffQualityBoxBean result = (StaffQualityBoxBean)new BeanHandlerService(StaffQualityBoxBean.class).handle(rs);
            return result;
        }
    }

    ///ICAS版ﾛｯﾄ情報SQL実行
    private class FindIcasQuery extends MappingSqlQuery {
        public FindIcasQuery(DataSource ds) {
            super(ds, ICAS_QUERY);
            super.declareParameter(new SqlParameter("LINKKEY", Types.VARCHAR));
            super.declareParameter(new SqlParameter("TBLORDER", Types.VARCHAR));
	    //System.out.println("ICAS_SQL:"+ICAS_QUERY);
            compile();
        }
        public Object mapRow(ResultSet rs, int rowNumber) throws SQLException {
            IcasBean result = (IcasBean)new BeanHandlerService(IcasBean.class).handle(rs);
            return result;
        }
    }

    ///ICAS版ﾛｯﾄ情報SQL実行　設備BOX
    private class FindIcasBoxQuery extends MappingSqlQuery {
        public FindIcasBoxQuery(DataSource ds) {
            super(ds, ICAS_BOX_QUERY);
            super.declareParameter(new SqlParameter("LINKKEY", Types.VARCHAR));
            super.declareParameter(new SqlParameter("CYUZO_YY", Types.DECIMAL));
            super.declareParameter(new SqlParameter("TBLORDER", Types.VARCHAR));
	    //System.out.println("ICAS設備:"+ICAS_BOX_QUERY);
            compile();
        }
        public Object mapRow(ResultSet rs, int rowNumber) throws SQLException {
            IcasBoxBean result = (IcasBoxBean)new BeanHandlerService(IcasBoxBean.class).handle(rs);
            return result;
        }
    }

    //ロット情報ＳＱＬ実行
    private class FindLtnoCboQuery extends MappingSqlQuery {
        public FindLtnoCboQuery(DataSource ds) {
            super(ds, LTNO_CBO_QUERY);
            super.declareParameter(new SqlParameter("lta", Types.VARCHAR));
            super.declareParameter(new SqlParameter("ksd1", Types.VARCHAR));
            super.declareParameter(new SqlParameter("ksd2", Types.VARCHAR));
            compile();
        }
        public Object mapRow(ResultSet rs, int rowNumber) throws SQLException {
            LotSchCboBean result = (LotSchCboBean)new BeanHandlerService(LotSchCboBean.class).handle(rs);
            return result;
      }
    }

    //ロット情報ＳＱＬ実行
    private class FindCynoCboQuery extends MappingSqlQuery {
        public FindCynoCboQuery(DataSource ds) {
            super(ds, CYNO_CBO_QUERY);
            super.declareParameter(new SqlParameter("lta", Types.VARCHAR));
            super.declareParameter(new SqlParameter("ksd1", Types.VARCHAR));
            super.declareParameter(new SqlParameter("ksd2", Types.VARCHAR));
            compile();
        }
        public Object mapRow(ResultSet rs, int rowNumber) throws SQLException {
            LotSchCboBean result = (LotSchCboBean)new BeanHandlerService(LotSchCboBean.class).handle(rs);
            return result;
      }
    }

    //ロット情報ＳＱＬ実行
    private class FindKnnoCboQuery extends MappingSqlQuery {
        public FindKnnoCboQuery(DataSource ds) {
            super(ds, KNNO_CBO_QUERY);
            super.declareParameter(new SqlParameter("lta", Types.VARCHAR));
            super.declareParameter(new SqlParameter("ksd1", Types.VARCHAR));
            super.declareParameter(new SqlParameter("ksd2", Types.VARCHAR));
            compile();
        }
        public Object mapRow(ResultSet rs, int rowNumber) throws SQLException {
            LotSchCboBean result = (LotSchCboBean)new BeanHandlerService(LotSchCboBean.class).handle(rs);
            return result;
      }
    }

    //ロット情報ＳＱＬ実行
    private class FindJuaCboQuery extends MappingSqlQuery {
        public FindJuaCboQuery(DataSource ds) {
            super(ds, JUA_CBO_QUERY);
            super.declareParameter(new SqlParameter("lta", Types.VARCHAR));
            super.declareParameter(new SqlParameter("ksd1", Types.VARCHAR));
            super.declareParameter(new SqlParameter("ksd2", Types.VARCHAR));
            compile();
        }
        public Object mapRow(ResultSet rs, int rowNumber) throws SQLException {
            LotSchCboBean result = (LotSchCboBean)new BeanHandlerService(LotSchCboBean.class).handle(rs);
            return result;
      }
    }

    //ロット情報ＳＱＬ実行
    private class FindLtxCboQuery extends MappingSqlQuery {
        public FindLtxCboQuery(DataSource ds) {
            super(ds, LTX_CBO_QUERY);
            super.declareParameter(new SqlParameter("lta", Types.VARCHAR));
            super.declareParameter(new SqlParameter("ksd1", Types.VARCHAR));
            super.declareParameter(new SqlParameter("ksd2", Types.VARCHAR));
            compile();
        }
        public Object mapRow(ResultSet rs, int rowNumber) throws SQLException {
            LotSchCboBean result = (LotSchCboBean)new BeanHandlerService(LotSchCboBean.class).handle(rs);
            return result;
      }
    }

    //ロット情報ＳＱＬ実行
    private class FindTestQuery extends MappingSqlQuery {
        public FindTestQuery(DataSource ds) {
            super(ds, TEST_QUERY);
            super.declareParameter(new SqlParameter("lta", Types.VARCHAR));
            super.declareParameter(new SqlParameter("ksd1", Types.VARCHAR));
            super.declareParameter(new SqlParameter("ksd2", Types.VARCHAR));
            compile();
        }
        public Object mapRow(ResultSet rs, int rowNumber) throws SQLException {
            LotProgInfoBean result = (LotProgInfoBean)new BeanHandlerService(LotProgInfoBean.class).handle(rs);
            return result;
      }
    }

    /**
     * クラッド芯材情報取得ＳＱＬ実行
     * 2011/07/05 add
     */
    private class FindCladInfoQuery extends MappingSqlQuery {
        public FindCladInfoQuery(DataSource ds) {
            super(ds, getCladShinzaiQuery());
            super.declareParameter(new SqlParameter("LINKKEY", Types.VARCHAR));
            super.declareParameter(new SqlParameter("TBLORDER", Types.VARCHAR));
            compile();
        }
        public Object mapRow(ResultSet rs, int rowNumber) throws SQLException {
	    //System.out.println(rs.toString());
            CladInfoBean result = (CladInfoBean) new BeanHandlerService(CladInfoBean.class).handle(rs);
            return result;
        }
    }

    /**
     * クラッド皮材情報取得ＳＱＬ実行
     * 2011/07/05 add
     */
    private class FindCladInfoBoxQuery extends MappingSqlQuery {
        public FindCladInfoBoxQuery(DataSource ds) {
            super(ds, getCladKawazaiQuery());
            super.declareParameter(new SqlParameter("J1.LINKKEY", Types.VARCHAR));
	    super.declareParameter(new SqlParameter("J2.LINKKEY", Types.VARCHAR));
	    super.declareParameter(new SqlParameter("J3.LINKKEY", Types.VARCHAR));
	    super.declareParameter(new SqlParameter("J4.LINKKEY", Types.VARCHAR));
	    super.declareParameter(new SqlParameter("J5.LINKKEY", Types.VARCHAR));
	    super.declareParameter(new SqlParameter("J6.LINKKEY", Types.VARCHAR));
	    super.declareParameter(new SqlParameter("J7.LINKKEY", Types.VARCHAR));
	    super.declareParameter(new SqlParameter("J8.LINKKEY", Types.VARCHAR));
	    super.declareParameter(new SqlParameter("TBLORDER", Types.VARCHAR));
            compile();
        }
        public Object mapRow(ResultSet rs, int rowNumber) throws SQLException {
            CladInfoBoxBean result = (CladInfoBoxBean) new BeanHandlerService(CladInfoBoxBean.class).handle(rs);
            return result;
        }
    }

    ///徐冷　CF情報　共通SQL実行 sora
    private class FindCFInfoQuery extends MappingSqlQuery {
        public FindCFInfoQuery(DataSource ds) {
            super(ds, getCFCommonQuery());
            super.declareParameter(new SqlParameter("LINKKEY", Types.VARCHAR));
            super.declareParameter(new SqlParameter("TBLORDER", Types.VARCHAR));
            compile();
        }
        public Object mapRow(ResultSet rs, int rowNumber) throws SQLException {
            CFInfoBean result = (CFInfoBean)new BeanHandlerService(CFInfoBean.class).handle(rs);
            return result;
        }
    }

    ///徐冷　CF情報SQL実行　焼鈍BOX sora
    private class FindCFInfoBoxQuery extends MappingSqlQuery {
        public FindCFInfoBoxQuery(DataSource ds) {
            super(ds, getCFShodonQuery());
            super.declareParameter(new SqlParameter("LINKKEY", Types.VARCHAR));
            super.declareParameter(new SqlParameter("CYUZO_YY", Types.DECIMAL));
            super.declareParameter(new SqlParameter("TBLORDER", Types.VARCHAR));
	    //System.out.println("焼鈍SQL："+getCFShodonQuery());
            compile();
        }
        public Object mapRow(ResultSet rs, int rowNumber) throws SQLException {
            CFInfoBoxBean result = (CFInfoBoxBean)new BeanHandlerService(CFInfoBoxBean.class).handle(rs);
            return result;
        }
    }

    ///ｽﾀｯﾌ版ﾛｯﾄ情報取得ﾒｿｯﾄﾞ
    public List FindTest(String lta, String ksd1, String ksd2) throws SQLException,
            NotFoundException{
        DriverManagerDataSource dataSource = ConnectDataSource();
        // ロット情報SQLの検証
        FindTestQuery query1 = new FindTestQuery(dataSource);
        // ロット情報SQLの実行
        Object[] params = new Object[]{lta, ksd1, ksd2};
        List lst1 = query1.execute(params);
        // 0件判定
        if (lst1.size() == 0)
            throw new NotFoundException();
        // 結果を格納
        return lst1;
    }

    //ロット情報＆設備情報取得メソッド
    public List FindLtnoCbo(String lta, String ksd1, String ksd2) throws SQLException,
            NotFoundException{
        DriverManagerDataSource dataSource = ConnectDataSource();
        // ロット情報SQLの検証
        FindLtnoCboQuery query1 = new FindLtnoCboQuery(dataSource);
        // ロット情報SQLの実行
        Object[] params = new Object[]{lta, ksd1, ksd2};
        List lst1 = query1.execute(params);
        ArrayList lst = new ArrayList();
        lst.add(new SelectItem("",""));
        // 0件判定
        if (lst1.size() == 0) {
            //throw new NotFoundException();
            //lst1 = null;
        // 結果を格納
        } else {
            System.out.println(new java.util.Date().toString());
            for (int i=0; i<lst1.size(); i++) {
                LotSchCboBean lscb = (LotSchCboBean)lst1.get(i);
                lst.add(new SelectItem(lscb.getLtno(),lscb.getLtno()));
            }
            System.out.println(new java.util.Date().toString());
        }
        return lst;
    }

    //ロット情報＆設備情報取得メソッド
    public List FindCynoCbo(String lta, String ksd1, String ksd2) throws SQLException,
            NotFoundException{
        DriverManagerDataSource dataSource = ConnectDataSource();
        // ロット情報SQLの検証
        FindCynoCboQuery query1 = new FindCynoCboQuery(dataSource);
        // ロット情報SQLの実行
        Object[] params = new Object[]{lta, ksd1, ksd2};
        List lst1 = query1.execute(params);
        ArrayList lst = new ArrayList();
        lst.add(new SelectItem("",""));
        // 0件判定
        if (lst1.size() == 0) {
            //throw new NotFoundException();
            //lst1 = null;
        // 結果を格納
        } else {
            System.out.println(new java.util.Date().toString());
            for (int i=0; i<lst1.size(); i++) {
                LotSchCboBean lscb = (LotSchCboBean)lst1.get(i);
                lst.add(new SelectItem(lscb.getCyno(),lscb.getCyno()));
            }
            System.out.println(new java.util.Date().toString());
        }
        return lst;
    }

    //ロット情報＆設備情報取得メソッド
    public List FindKnnoCbo(String lta, String ksd1, String ksd2) throws SQLException,
            NotFoundException{
        DriverManagerDataSource dataSource = ConnectDataSource();
        // ロット情報SQLの検証
        FindKnnoCboQuery query1 = new FindKnnoCboQuery(dataSource);
        // ロット情報SQLの実行
        Object[] params = new Object[]{lta, ksd1, ksd2};
        List lst1 = query1.execute(params);
        ArrayList lst = new ArrayList();
        lst.add(new SelectItem("",""));
        // 0件判定
        if (lst1.size() == 0) {
            //throw new NotFoundException();
            //lst1 = null;
        // 結果を格納
        } else {
            System.out.println(new java.util.Date().toString());
            for (int i=0; i<lst1.size(); i++) {
                LotSchCboBean lscb = (LotSchCboBean)lst1.get(i);
                lst.add(new SelectItem(lscb.getKnno(),lscb.getKnno()));
            }
            System.out.println(new java.util.Date().toString());
        }
        return lst;
    }

    //ロット情報＆設備情報取得メソッド
    public List FindJuaCbo(String lta, String ksd1, String ksd2) throws SQLException,
            NotFoundException{
        DriverManagerDataSource dataSource = ConnectDataSource();
        // ロット情報SQLの検証
        FindJuaCboQuery query1 = new FindJuaCboQuery(dataSource);
        // ロット情報SQLの実行
        Object[] params = new Object[]{lta, ksd1, ksd2};
        List lst1 = query1.execute(params);
        ArrayList lst = new ArrayList();
        lst.add(new SelectItem("",""));
        // 0件判定
        if (lst1.size() == 0) {
            //throw new NotFoundException();
            //lst1 = null;
        // 結果を格納
        } else {
            System.out.println(new java.util.Date().toString());
            for (int i=0; i<lst1.size(); i++) {
                LotSchCboBean lscb = (LotSchCboBean)lst1.get(i);
                lst.add(new SelectItem(lscb.getJua(),lscb.getJua()));
            }
            System.out.println(new java.util.Date().toString());
        }
        return lst;
    }

    //ロット情報＆設備情報取得メソッド
    public List FindLtxCbo(String lta, String ksd1, String ksd2) throws SQLException,
            NotFoundException{
        DriverManagerDataSource dataSource = ConnectDataSource();
        // ロット情報SQLの検証
        FindLtxCboQuery query1 = new FindLtxCboQuery(dataSource);
        // ロット情報SQLの実行
        Object[] params = new Object[]{lta, ksd1, ksd2};
        List lst1 = query1.execute(params);
        ArrayList lst = new ArrayList();
        lst.add(new SelectItem("",""));
        // 0件判定
        if (lst1.size() == 0) {
            //throw new NotFoundException();
            //lst1 = null;
        // 結果を格納
        } else {
            System.out.println(new java.util.Date().toString());
            for (int i=0; i<lst1.size(); i++) {
                LotSchCboBean lscb = (LotSchCboBean)lst1.get(i);
                lst.add(new SelectItem(lscb.getLtx().toString(),lscb.getLtx().toString()));
            }
            System.out.println(new java.util.Date().toString());
        }
        return lst;
    }

    //検索キー（SearchKey）取得
    public SearchKeyBean FindSearchKeyInfo(Map params) throws SQLException,NotFoundException {

        DriverManagerDataSource dataSource = ConnectDataSource();
        //ｽﾀｯﾌ版ﾛｯﾄ情報SQLの検証 共通
        SearchKeyQuery searchkey = new SearchKeyQuery(params);

        FindSearchKeyQuery query = new FindSearchKeyQuery(dataSource, params, searchkey);
        //ｽﾀｯﾌ版ﾛｯﾄ情報SQLの実行 共通
        //Object[] paramss = new Object[]{ltno, knno};
        List list = new ArrayList();
        if(params.containsKey("LINKKEY")) {
            if(!((params.get("LINKKEY") == null) || (params.get("LINKKEY").equals("")))) {
                list.add(params.get("LINKKEY"));
            }
        }
        if(params.containsKey("LTNO")) {
            if(!((params.get("LTNO") == null) || (params.get("LTNO").equals("")))) {
                list.add(params.get("LTNO"));
            }
        }
        if(params.containsKey("CYNO")) {
            if(!((params.get("CYNO") == null) || (params.get("CYNO").equals("")))) {
                list.add(params.get("CYNO"));
            }
        }
        if(params.containsKey("KNNO")) {
            if(!((params.get("KNNO") == null) || (params.get("KNNO").equals("")))) {
                list.add(params.get("KNNO"));
            }
        }
        if(params.containsKey("JUNO")) {
            if(!((params.get("JUNO") == null) || (params.get("JUNO").equals("")))) {
                list.add(params.get("JUNO"));
            }
        }
        if(params.containsKey("CYUZO_YY")) {
            if(!((params.get("CYUZO_YY") == null))) {
                list.add(params.get("CYUZO_YY"));
            }
        }

        Object[] paramValues = list.toArray();

        List lst = query.execute(paramValues);
        // 0件判定
        if (lst.size() == 0)
            throw new NotFoundException();
        SearchKeyBean searchKeyInfo = (SearchKeyBean)lst.get(0);  // 最初の１件目だけ
        // 結果を格納
        return searchKeyInfo;
    }

    public List FindSearchKeyInfos(Map params) throws SQLException,NotFoundException {

        DriverManagerDataSource dataSource = ConnectDataSource();

        //ｽﾀｯﾌ版ﾛｯﾄ情報SQLの検証 共通
        SearchKeyQuery searchkey = new SearchKeyQuery(params);

        FindSearchKeyQuery query = new FindSearchKeyQuery(dataSource, params, searchkey);
        //ｽﾀｯﾌ版ﾛｯﾄ情報SQLの実行 共通
        //Object[] paramss = new Object[]{ltno, knno};
        List list = new ArrayList();
        if(params.containsKey("LINKKEY")) {
            if(!((params.get("LINKKEY") == null) || (params.get("LINKKEY").equals("")))) {
                list.add(params.get("LINKKEY"));
            }
        }
        if(params.containsKey("LTNO")) {
            if(!((params.get("LTNO") == null) || (params.get("LTNO").equals("")))) {
                list.add(params.get("LTNO"));
            }
        }
        if(params.containsKey("CYNO")) {
            if(!((params.get("CYNO") == null) || (params.get("CYNO").equals("")))) {
                list.add(params.get("CYNO"));
            }
        }
        if(params.containsKey("KNNO")) {
            if(!((params.get("KNNO") == null) || (params.get("KNNO").equals("")))) {
                list.add(params.get("KNNO"));
            }
        }
        if(params.containsKey("JUNO")) {
            if(!((params.get("JUNO") == null) || (params.get("JUNO").equals("")))) {
                list.add(params.get("JUNO"));
            }
        }
        if(params.containsKey("CYUZO_YY")) {
            if(!((params.get("CYUZO_YY") == null))) {
                list.add(params.get("CYUZO_YY"));
            }
        }
        if(params.containsKey("TBLORDER")) {
            if(!((params.get("TBLORDER") == null))) {
                list.add(params.get("TBLORDER"));
            }
        }

        Object[] paramValues = list.toArray();

        List lst = query.execute(paramValues);

        // 0件判定
        if (lst.size() == 0)
            throw new NotFoundException();

        // 結果を格納
        return lst;
    }

    // ロット問合せユーザー取得メソッド
    public LotDspUserBean FindLotDspUsersInfo(String userId) throws SQLException,NotFoundException {
	System.out.print("ID："+ userId);
	FindLotDspUsersQuery query = new FindLotDspUsersQuery(ConnectDataSource());
        List lst = query.execute(new Object[]{userId});
	//System.out.print("サイズ：："+lst.size());
        if (lst.size() == 0){
	    //System.out.print("@エラー1");
            throw new NotFoundException();
	}
        return (LotDspUserBean)lst.get(0);
    }

    //ｽﾀｯﾌ版ﾛｯﾄ情報取得ﾒｿｯﾄﾞ 共通情報
    public StaffCommonBean FindStaffCommonInfo(String linkkey,BigDecimal cyuzoYy,String tborderkey) throws SQLException,NotFoundException {
	DriverManagerDataSource dataSource = ConnectDataSource();
        //ｽﾀｯﾌ版ﾛｯﾄ情報SQLの検証 試験情報
        FindStaffCommonQuery query = new FindStaffCommonQuery(dataSource);
        //ｽﾀｯﾌ版ﾛｯﾄ情報SQLの実行 試験情報
	Object[] params = new Object[]{linkkey,cyuzoYy,tborderkey};
        List lst = query.execute(params);
        // 0件判定
        if (lst.size() == 0){
	    //System.out.print("@エラー2");
            throw new NotFoundException();
	}
        StaffCommonBean staffCommonInfo = (StaffCommonBean)lst.get(0);  // 最初の１件目だけ

        // 結果を格納
        return staffCommonInfo;

    }

    //ｽﾀｯﾌ版ﾛｯﾄ情報取得ﾒｿｯﾄﾞ 進度情報
    public StaffProgressBean FindStaffProgressInfo(String linkkey,BigDecimal cyuzoYy,String tborderkey) throws SQLException,NotFoundException {
	//System.out.println("," + linkkey + "," + cyuzoYy);

        DriverManagerDataSource dataSource = ConnectDataSource();
        //ｽﾀｯﾌ版ﾛｯﾄ情報SQLの検証 進度情報
        FindStaffProgressQuery query1 = new FindStaffProgressQuery(dataSource);
        //ｽﾀｯﾌ版ﾛｯﾄ情報SQLの実行 共通
        Object[] paramsCommon = new Object[]{linkkey,tborderkey}; //ADD
        List lst1 = query1.execute(paramsCommon);
        // 0件判定
        if (lst1.size() == 0){
	    //System.out.print("@エラー3");
            throw new NotFoundException();
	}
        StaffProgressBean staffProgressInfo = (StaffProgressBean)lst1.get(0);  // 最初の１件目だけ

        //ｽﾀｯﾌ版ﾛｯﾄ情報SQLの検証 進度情報 設備BOX
        FindStaffProgressBoxQuery query2 = new FindStaffProgressBoxQuery(dataSource);
        // 設備BOX情報SQLの実行
	//Object[] params2 = new Object[]{linkkey,cyuzoYy}; //TODO:
	//BigDecimal a = new BigDecimal(2013);
	Object[] paramsSbox = new Object[]{linkkey,cyuzoYy,tborderkey}; //ADD
        List lst2 = query2.execute(paramsSbox);
        // 0件判定
        if (lst2.size() == 0){
   	    //System.out.print("@エラー4");
            throw new NotFoundException();
	}
        staffProgressInfo.setStaffProgressBoxInfos(lst2);

        //ｽﾀｯﾌ版ﾛｯﾄ情報SQLの検証 進度情報 設計設備BOX
        FindStaffProgressSBoxQuery query3 = new FindStaffProgressSBoxQuery(dataSource);
        // 設備BOX情報SQLの実行
	Object[] paramsSbox2 = new Object[]{linkkey,cyuzoYy,tborderkey}; //ADD
        List lst3 = query3.execute(paramsSbox2);  //execute(linkkey) から変更
        // 0件判定
        if (lst3.size() == 0){
    	    //System.out.print("@エラー5");
            throw new NotFoundException();
	}
        staffProgressInfo.setStaffProgressSBoxInfos(lst3);

        //ｽﾀｯﾌ版ﾛｯﾄ情報SQLの検証 進度情報 実績設備BOX
        FindStaffProgressJBoxQuery query4 = new FindStaffProgressJBoxQuery(dataSource);
        // 設備BOX情報SQLの実行
        Object[] params = new Object[]{linkkey, linkkey, linkkey,cyuzoYy,tborderkey}; //ADD
        List lst4 = query4.execute(params);
        // 0件判定
        if (lst4.size() == 0){
   	    //System.out.print("@エラー6");
            throw new NotFoundException();
	}
	List lst5 = new ArrayList();
        boolean add_flag = false;
        for (int i=0; i<lst4.size(); i++) {
            StaffProgressJBoxBean bean = (StaffProgressJBoxBean)lst4.get(i);
            //bean.setJBGOOD(BigDecimal.TEN);
	   // System.out.println(bean.getJBGOOD());

	    if (add_flag || bean.getJBSM() != null) {
		//前ｵﾌ後ｵﾌの値設定
		if(!(bean.getJBFO() == null && bean.getJBBO() == null)){
		    if(bean.getJBFO() == null){
			bean.setJBFO("0");
		    }
		    if(bean.getJBBO() == null){
			bean.setJBBO("0");
		    }
		}
                lst5.add(bean);
                add_flag = true;
            }
        }
        staffProgressInfo.setStaffProgressJBoxInfos(lst5);

        // 結果を格納
        return staffProgressInfo;
    }

    //ｽﾀｯﾌ版ﾛｯﾄ情報取得ﾒｿｯﾄﾞ 製造情報
    public StaffManufactureBean FindStaffManufactureInfo(String linkkey,String tborderkey) throws SQLException,NotFoundException {
        DriverManagerDataSource dataSource = ConnectDataSource();
        //ｽﾀｯﾌ版ﾛｯﾄ情報SQLの検証 製造情報
        FindStaffManufactureQuery query = new FindStaffManufactureQuery(dataSource);
        // 製造情報SQLの実行
        Object[] params = new Object[]{linkkey,tborderkey}; //ADD
        List lst = query.execute(params);
        // 0件判定
        if (lst.size() == 0){
	   // System.out.print("@エラー7");
            throw new NotFoundException();
	}
        StaffManufactureBean ｓtaffManufactureInfo = (StaffManufactureBean)lst.get(0);  // 最初の１件目だけ

        // 結果を格納
        return ｓtaffManufactureInfo;

    }

    //ｽﾀｯﾌ版ﾛｯﾄ情報取得ﾒｿｯﾄﾞ 試験情報
    public StaffTestBean FindStaffTestInfo(String linkkey,String tborderkey) throws SQLException,NotFoundException {
        DriverManagerDataSource dataSource = ConnectDataSource();
        //ｽﾀｯﾌ版ﾛｯﾄ情報SQLの検証 試験情報
        FindStaffTestQuery query = new FindStaffTestQuery(dataSource);
        //ｽﾀｯﾌ版ﾛｯﾄ情報SQLの実行 試験情報
        Object[] params = new Object[]{linkkey, linkkey, linkkey, tborderkey};
        List lst = query.execute(params);
        // 0件判定
        if (lst.size() == 0){
	    //System.out.print("@エラー8");
	    throw new NotFoundException();
	}
        StaffTestBean staffTestInfo = (StaffTestBean)lst.get(0);  // 最初の１件目だけ

        // 結果を格納
        return staffTestInfo;

    }

    //ｽﾀｯﾌ版ﾛｯﾄ情報取得ﾒｿｯﾄﾞ 品質情報
    public StaffQualityBean FindStaffQualityInfo(String linkkey) throws SQLException,NotFoundException {
        DriverManagerDataSource dataSource = ConnectDataSource();
        //ｽﾀｯﾌ版ﾛｯﾄ情報SQLの検証 品質情報
        FindStaffQualityQuery query = new FindStaffQualityQuery(dataSource);
        //ｽﾀｯﾌ版ﾛｯﾄ情報SQLの実行 品質情報
        List lst = query.execute(linkkey);
        for(int i=0; i<lst.size(); i++) {
            StaffQualityBoxBean bean = (StaffQualityBoxBean)lst.get(i);
            if (i == 0) {
                bean.setSELECTED("1");
            } else {
                bean.setSELECTED("0");
            }
            lst.set(i, bean);
        }
        // 0件判定
        //if (lst.size() == 0) throw new NotFoundException();
        StaffQualityBean staffQualityInfo = new StaffQualityBean();
        staffQualityInfo.setStaffQualityBoxInfos(lst);

        // 結果を格納
        return staffQualityInfo;

    }


    //ICAS版ﾛｯﾄ情報取得ﾒｿｯﾄﾞ
    public IcasBean FindIcasInfo(String linkkey,BigDecimal cyuzoYy,String tborderkey) throws SQLException,NotFoundException {

	int IcasBoxSize = 0;
	String sJRFLG = "";
	String sSBSM  = "";

	DriverManagerDataSource dataSource = ConnectDataSource();
        //ｽﾀｯﾌ版ﾛｯﾄ情報SQLの検証 共通
        FindIcasQuery query = new FindIcasQuery(dataSource);
        //ｽﾀｯﾌ版ﾛｯﾄ情報SQLの実行 共通
        Object[] paramsIcas = new Object[]{linkkey, tborderkey};
        List lst = query.execute(paramsIcas);
        // 0件判定
        if (lst.size() == 0){
	    //System.out.print("@エラー9");
            throw new NotFoundException();
	}

	IcasBean icasInfo = (IcasBean)lst.get(0);  // 最初の１件目だけ

	// 2012/12/10 徐冷フラグ情報取得
	if(icasInfo.getJRFLG() == null)
	    sJRFLG = "0";
	else
	    sJRFLG = icasInfo.getJRFLG();

        //ICAS版ﾛｯﾄ情報SQLの検証 進度情報 設備BOX
        FindIcasBoxQuery query2 = new FindIcasBoxQuery(dataSource);
        // 設備BOX情報SQLの実行
        Object[] params = new Object[]{linkkey, cyuzoYy,tborderkey};
        List lst2 = query2.execute(params);
	System.out.println("FindIcasBoxQuery"+ lst2.size() + "," +linkkey+","+cyuzoYy+","+tborderkey);
	// 0件判定
        if (lst2.size() == 0){
           // System.out.print("@エラー10");
	    throw new NotFoundException();
	}
	IcasBoxSize = lst2.size();
	IcasBoxSize--;
	for(int i=0; i<lst2.size(); i++) {
	    IcasBoxBean bean = (IcasBoxBean)lst2.get(IcasBoxSize);
	    // 設計設備名=ANF AND 徐冷フラグ = 1 の場合作業パターンSBSMに"J"をセット
	    if(bean.getSBSM() == null)
		sSBSM = "";
	    else
		sSBSM = bean.getSBSM();

    	    if(sSBSM.equals("ANF") && sJRFLG.equals("1")){
		sJRFLG = icasInfo.getJRFLG();
		bean.setSBPAT("J");
		lst2.set(IcasBoxSize, bean);
		break;
	    }
	    IcasBoxSize--;
	}

        icasInfo.setIcasBoxInfos(lst2);

        // 結果を格納
        return icasInfo;

    }

    /**
     * クラッド情報取得メソッド
     * @param linkkey
     * @return CladInfoBean
     * @throws java.sql.SQLException
     * @throws jp.co.nikkeikin.nis.nagoya.lotdsp.NotFoundException
     * 2011/07/05 add
     */
    public CladInfoBean FindCladInfo(String linkkey,String tborderkey) throws SQLException, NotFoundException {

        DriverManagerDataSource dataSource = ConnectDataSource();

        // クラッド芯材情報取得SQL
        FindCladInfoQuery query1 = new FindCladInfoQuery(dataSource);
        // SQLの実行 共通
        Object[] paramsCommon = new Object[]{linkkey,tborderkey}; //ADD
        List lst1 = query1.execute(paramsCommon);
        // 0件判定
        if (lst1.size() == 0) {
	    //System.out.print("@エラー11");
            throw new NotFoundException();
        }
        // 最初の１件目だけ
        CladInfoBean cladInfo = (CladInfoBean)lst1.get(0);
        // クラッド皮材情報取得SQL
        FindCladInfoBoxQuery query2 = new FindCladInfoBoxQuery(dataSource);
        // SQLの実行
	Object[] params = new Object[]{linkkey, linkkey, linkkey, linkkey, linkkey, linkkey, linkkey, linkkey,tborderkey};
        List lst2 = query2.execute(params);
        // 0件判定
        if (lst2.size() > 0) {
            cladInfo.setCladInfoBox(lst2);
        }

        // 結果を格納
        return cladInfo;
    }

    /**
     * 徐冷　CF情報取得メソッド
     * @param linkkey
     * @return CFInfoBean
     * @throws java.sql.SQLException
     * @throws jp.co.nikkeikin.nis.nagoya.lotdsp.NotFoundException
     * 2012/12/14 add
     */
    public CFInfoBean FindCFInfo(String linkkey,BigDecimal cyuzoYy,String tborderkey) throws SQLException, NotFoundException {

        DriverManagerDataSource dataSource = ConnectDataSource();

        // 徐冷　共通情報取得SQL
        FindCFInfoQuery query1 = new FindCFInfoQuery(dataSource);
        // SQLの実行 共通
        Object[] paramCommon = new Object[]{linkkey, tborderkey};
        List lst1 = query1.execute(paramCommon);
        // 0件判定
        if (lst1.size() == 0) {
	    //.out.print("@エラー12");
            throw new NotFoundException();
        }
        // 最初の１件目だけ
        CFInfoBean CFInfo = (CFInfoBean)lst1.get(0);
        // 徐冷　焼鈍情報取得SQL
        FindCFInfoBoxQuery query2 = new FindCFInfoBoxQuery(dataSource);
        // SQLの実行
	System.out.println("CF_SQL:" + linkkey + "," + cyuzoYy + "," + tborderkey); //test
	Object[] params = new Object[]{linkkey, cyuzoYy,tborderkey};
        List lst2 = query2.execute(params);
        // 0件判定
        if (lst2.size() > 0) {
            CFInfo.setCFInfoBox(lst2);
        }

        // 結果を格納
        return CFInfo;
    }

}
