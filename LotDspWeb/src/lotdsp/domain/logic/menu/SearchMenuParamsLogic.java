package lotdsp.domain.logic.menu;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import lotdsp.common.lotdsp.CommonInfo;
import lotdsp.domain.logic.common.SearchDataLogic;
import lotdsp.domain.nagoya.util.StringUtil;
import nis.framework.ejb.logic.Logic;
import nis.framework.ejb.logic.ServiceContext;
public class SearchMenuParamsLogic {

	//表示モードの設定
    public static final String ICAS_MODE  = "0";
    public static final String STAFF_MODE = "1";
    public static final String BOTH_MODE  = "2";
    public static final String OUT_MODE   = "3";
    
    // タブ
    public static final String TAB_ICAS = "0";
    public static final String TAB_PROGRESS = "1";
    public static final String TAB_MANUFACTURING = "2";
    public static final String TAB_TEST = "3";
    public static final String TAB_QUALITY = "4";
	public static final String TAB_CF = "5";
    
    public static final String SITE_WORKS  = "1";  // 現場用

	@Inject
	protected ServiceContext svContext;

	@Logic
	public CommonInfo execute(CommonInfo in) {

		//各画面の検索結果格納用変数の宣言
        String resStaff = null;
        String resIcas  = null;
        String resClad  = null;
        String resCF    = null;
        
		String inputLinkkey = in.getParamLinkkey();
		String inputCyno = "";
        String inputLtno = "";
        String inputKnno = "";

		Map param = new HashMap();
		
        // パラメーター設定
		if (StringUtil.hasValue(inputLinkkey)) {
			inputLtno = (inputLinkkey.substring(0, 9).trim()).toUpperCase();
	        inputKnno = in.getSearchKnno().toUpperCase();
		} else {
	        inputLtno = in.getSearchLtno().toUpperCase();
	        inputKnno = in.getSearchKnno().toUpperCase();
			inputCyno = in.getParamCyno();
		}

		try {
			
			//検索パラメータの設定
			param.put("KNNO", inputKnno);
			param.put("LTNO", inputLtno);
			param.put("CYNO", inputCyno);
			param.put("LINKKEY", inputLinkkey);
			
			if ((StringUtil.hasValue(inputLinkkey)) || (StringUtil.hasValue(inputLtno) || StringUtil.hasValue(inputKnno) || StringUtil.hasValue(inputCyno))) {
				
				//検索用のキー情報を取得
				String paraLinkkey = SearchDataLogic.getSearchKey(in,param, 0);
				BigDecimal paraCyuzoYy = SearchDataLogic.getSearchKey2(in,param, 0);

				//各画面の検索を実行
				// スタッフ情報
		        resStaff = SearchDataLogic.getStaffInfoData(in,paraLinkkey, paraCyuzoYy);

		        // ICAS情報
				resIcas = SearchDataLogic.getIcasInfoData(in,paraLinkkey,paraCyuzoYy);

				// 2011/07/06 クラッド情報取得処理・処理結果判定の追加
				resClad = SearchDataLogic.getCladInfoData(in,paraLinkkey);

				// 2012/12/14 徐冷情報取得処理・処理結果判定の追加
				resCF = SearchDataLogic.getCFInfoData(in,paraLinkkey,paraCyuzoYy);

		        if (!("error".equals(resStaff) || "error".equals(resIcas))) {
			        if (ICAS_MODE.equals(in.getMode())) {
		            	in.setNextGamen("IcasInfo");
			        } else if (STAFF_MODE.equals(in.getMode()) ||BOTH_MODE.equals(in.getMode())) {
                        if (TAB_ICAS.equals(in.getTab())) {
    		            	in.setNextGamen("IcasInfo");
                        } else if (TAB_PROGRESS.equals(in.getTab())) {
    		            	in.setNextGamen("ProgressInfo");
                        } else if (TAB_MANUFACTURING.equals(in.getTab())) {
    		            	in.setNextGamen("ManufacturingInfo");
                        } else if (TAB_TEST.equals(in.getTab())) {
    		            	in.setNextGamen("TestInfo");
                        } else if (TAB_QUALITY.equals(in.getTab())) {
    		            	in.setNextGamen("QualityInfo");
                        } else if (TAB_CF.equals(in.getTab())) {
    		            	in.setNextGamen("CFInfo");
                        } else {
    		            	in.setNextGamen("ProgressInfo");
                        }
			        }
	                in.setTabSetRendered(true);
		        }
				
			} else {
				if (ICAS_MODE.equals(in.getMode()) && SITE_WORKS.equals(in.getSite())) {
	            	in.setNextGamen("IcasInfo");
                    in.setTabSetRendered(false);
				}
			}

			//現在表示ページを0に設定する
			in.setNowPage(0);
			
			//入力したロット番号と検査番号を改めて設定
			in.setSearchLtno(inputLtno);
			in.setSearchKnno(inputKnno);
			
		} catch (Exception e) {
			in.setMessage(e.toString());
			in.setErrorFlg(true);
		}

		return in;
	}
}
