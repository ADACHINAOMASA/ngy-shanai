package lotdsp.domain.logic.common;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.tuscany.sca.data.collection.NotFoundException;

import lotdsp.common.lotdsp.CommonInfo;
import lotdsp.domain.nagoya.bean.CFInfoBean;
import lotdsp.domain.nagoya.bean.CladInfoBean;
import lotdsp.domain.nagoya.bean.IcasBean;
import lotdsp.domain.nagoya.bean.LotDspUserBean;
import lotdsp.domain.nagoya.bean.SearchKeyBean;
import lotdsp.domain.nagoya.bean.StaffCommonBean;
import lotdsp.domain.nagoya.bean.StaffManufactureBean;
import lotdsp.domain.nagoya.bean.StaffProgressBean;
import lotdsp.domain.nagoya.bean.StaffQualityBean;
import lotdsp.domain.nagoya.bean.StaffQualityBoxBean;
import lotdsp.domain.nagoya.bean.StaffTestBean;
import lotdsp.domain.nagoya.model.StaffRecalc;
import lotdsp.domain.nagoya.sql.LotDspService;

public class SearchDataLogic {
	/**
	 * 旧システムでは各画面で検索処理を実装していたが、新システムでは共通化する。
	 */


    /**
     * ロット問合せユーザー情報取得
     */
    public static String getLotDsp(CommonInfo in,String userId) {
	LotDspService svc = new LotDspService();
	LotDspUserBean lotDspUserBean = new LotDspUserBean();
	try {
		lotDspUserBean = svc.FindLotDspUsersInfo(userId);
		in.setLotDspUserBean(lotDspUserBean);

	} catch (SQLException e) {
		in.setMessage("実行エラー" + e.getErrorCode());
		in.setErrorFlg(true);
	    return "error";
	} catch (NotFoundException e) {
		in.setMessage("該当データがありません");
		in.setErrorFlg(true);
	    return "error";
	}
	return "success";
    }

	//検索キー（LinkKey）取得
    public static String getSearchKey(CommonInfo in,Map param) {
        LotDspService svc = new LotDspService();
        SearchKeyBean searchBean = new SearchKeyBean();
        //検索キー（LinkKey）取得
        try {
            List lst = svc.FindSearchKeyInfos(param);
            searchBean = ((SearchKeyBean)lst.get(0));
            in.setSearchKeyInfos(lst);
            in.setLotMaximum(lst.size());
        } catch(SQLException e){
			in.setMessage("実行エラー" + e.getErrorCode());
			in.setErrorFlg(true);
			System.err.println("Keyの存在なし");
            return "error";
        } catch(NotFoundException e){
			in.setMessage("該当データがありません");
			in.setErrorFlg(true);
            System.err.println("Keyの存在なし");
            return "error";
        }

        return searchBean.getLINKKEY();
    }

    //検索キー（CYUZO_YY）取得
    public static BigDecimal getSearchKey2(CommonInfo in,Map param) {
        LotDspService svc = new LotDspService();
        SearchKeyBean searchBean = new SearchKeyBean();
        //検索キー（CYUZO_YY）取得
        try {
            List lst = svc.FindSearchKeyInfos(param);
            searchBean = ((SearchKeyBean)lst.get(0));
            in.setSearchKeyInfos(lst);
        } catch(SQLException e) {
        	in.setMessage("実行エラー" + e.getErrorCode());
            return BigDecimal.ZERO;
        } catch(NotFoundException e) {
        	in.setMessage("該当データがありません");
            return BigDecimal.ZERO;
        }
        return searchBean.getCYUZO_YY();
    }


    //-------------------------各画面の検索処理-------------------------

    /**
     * ｽﾀｯﾌ版ﾛｯﾄ情報取得
     */
    public static String getStaffInfoData(CommonInfo in,String paraLinkkey,BigDecimal cyuzoYy) {
        LotDspService svc = new LotDspService();
        StaffCommonBean staffCommonBean = new StaffCommonBean();
        StaffProgressBean staffProgressBean = new StaffProgressBean();
        StaffManufactureBean staffManufactureBean = new StaffManufactureBean();
        StaffTestBean staffTestBean = new StaffTestBean();
        StaffQualityBean staffQualityBean = new StaffQualityBean();
        //ｽﾀｯﾌ版ﾛｯﾄ情報取得　共通情報
        try {
        	staffCommonBean = svc.FindStaffCommonInfo(paraLinkkey,cyuzoYy);
        	in.setStaffCommonBean(staffCommonBean);

        } catch(SQLException e){
			in.setMessage("実行エラー" + e.getErrorCode());
			in.setErrorFlg(true);
            return "error";
        } catch(NotFoundException e){
			in.setMessage("該当データがありません");
			in.setErrorFlg(true);
            return "error";
        }

        //String paraLinkkey = ((StaffCommonBean)sb1.getStaffCommonInfo()).getLINKKEY();

        //ｽﾀｯﾌ版ﾛｯﾄ情報取得　進度情報
        try {
        	staffProgressBean = svc.FindStaffProgressInfo(paraLinkkey,cyuzoYy);
        	in.setStaffProgressBean(new StaffRecalc().setSbmh(staffProgressBean));

        } catch(SQLException e){
			in.setMessage("実行エラー" + e.getErrorCode());
			in.setErrorFlg(true);
            return "error";
        } catch(NotFoundException e){
			in.setMessage("進度情報のデータがありません");
			in.setErrorFlg(true);
            return "error";
        }

        //ｽﾀｯﾌ版ﾛｯﾄ情報取得　製造情報
        try {
        	staffManufactureBean = svc.FindStaffManufactureInfo(paraLinkkey);
        	in.setStaffManufactureBean(staffManufactureBean);

        } catch(SQLException e){
			in.setMessage("実行エラー" + e.getErrorCode());
			in.setErrorFlg(true);
            return "error";
        } catch(NotFoundException e){
			in.setMessage("製造情報のデータがありません");
			in.setErrorFlg(true);
            return "error";
        }

        //ｽﾀｯﾌ版ﾛｯﾄ情報取得　試験情報
        try {
        	staffTestBean = svc.FindStaffTestInfo(paraLinkkey);
        	in.setStaffTestBean(staffTestBean);

        } catch(SQLException e){
			in.setMessage("実行エラー" + e.getErrorCode());
			in.setErrorFlg(true);
            return "error";
        } catch(NotFoundException e){
			in.setMessage("試験情報のデータがありません");
			in.setErrorFlg(true);
            return "error";
        }

        //ｽﾀｯﾌ版ﾛｯﾄ情報取得　品質情報
        try {
            in.setQualityInfoTabRendered(true);
            staffQualityBean = svc.FindStaffQualityInfo(paraLinkkey);
            in.setStaffQualityBean(staffQualityBean);

            List list = staffQualityBean.getStaffQualityBoxInfos();
            if (list.size() != 0) {
                in.setQualityDetail((StaffQualityBoxBean)list.get(0));
            }
	    else {
                StaffQualityBoxBean bean = new StaffQualityBoxBean();
                in.setQualityDetail(bean);
            }
        } catch(SQLException e){
			in.setMessage("実行エラー" + e.getErrorCode());
			in.setErrorFlg(true);
            return "error";
        } catch(NotFoundException e){
            //this.message.setValue("品質情報のデータがありません");
            in.setQualityInfoTabRendered(false);
			in.setMessage("品質情報のデータがありません");
			in.setErrorFlg(true);
            return "success";
        }
        return "success";
    }

    /**
     * ICAS版ﾛｯﾄ情報取得
     */
    public static String getIcasInfoData(CommonInfo in,String paraLinkkey,BigDecimal cyuzoYy) {
    	//サービス(sql)の取得
        LotDspService svc = new LotDspService();
        IcasBean icasBean = new IcasBean();
        //ICAS版ﾛｯﾄ情報取得
        try {
        	//sqlの実行
        	icasBean = svc.FindIcasInfo(paraLinkkey,cyuzoYy);
        	in.setIcasBean(icasBean);

        } catch(SQLException e){
			in.setMessage("実行エラー" + e.getErrorCode());
			in.setErrorFlg(true);
            return "error";
        } catch(NotFoundException e){
			in.setMessage("進度情報のデータがありません");
			in.setErrorFlg(true);
            return "error";
        }
        return "success";
    }

    /**
     * クラッド情報を取得します。
     * @param paraLinkkey
     * @return 取得結果(success or error)
     * 2011/07/07 add
     */
    public static String getCladInfoData(CommonInfo in,String paraLinkkey) {
        LotDspService svc = new LotDspService();
        CladInfoBean cladInfoBean = new CladInfoBean();
        // クラッド情報取得
        try {
        	cladInfoBean = svc.FindCladInfo(paraLinkkey);
        	in.setCladInfoBean(cladInfoBean);

        } catch (SQLException e) {
			in.setMessage("実行エラー" + e.getErrorCode());
			in.setErrorFlg(true);
            return "error";
        } catch (NotFoundException e) {
	    // クラッド情報が必ずあるわけではないので見つからなくてもエラーにしない
	    return "success";
        }
        return "success";
    }

    /**
     * 徐冷情報を取得します。
     * @param paraLinkkey
     * @return 取得結果(success or error)
     * 2011/07/07 add
     */
    public static String getCFInfoData(CommonInfo in,String paraLinkkey,BigDecimal cyuzoYy) {
        LotDspService svc = new LotDspService();
        CFInfoBean cFInfoBean = new CFInfoBean();
        // 徐冷情報取得
        try {
        	cFInfoBean = svc.FindCFInfo(paraLinkkey,cyuzoYy);
            in.setcFInfoBean(cFInfoBean);
        } catch (SQLException e) {
			in.setMessage("実行エラー" + e.getErrorCode());
			in.setErrorFlg(true);
            return "error";
        } catch (NotFoundException e) {
	    // 徐冷情報が必ずあるわけではないので見つからなくてもエラーにしない
	    return "success";
        }
        return "success";
    }

}
