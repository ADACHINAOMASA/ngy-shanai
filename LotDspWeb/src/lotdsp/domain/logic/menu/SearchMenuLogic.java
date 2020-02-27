package lotdsp.domain.logic.menu;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import lotdsp.common.lotdsp.CommonInfo;
import lotdsp.domain.logic.common.SearchDataLogic;
import lotdsp.domain.nagoya.util.DateUtil;
import lotdsp.domain.nagoya.util.StringUtil;
import nis.framework.ejb.logic.Logic;
import nis.framework.ejb.logic.ServiceContext;
public class SearchMenuLogic {

	//表示モードの設定
    public static final String ICAS_MODE  = "0";
    public static final String STAFF_MODE = "1";
    public static final String BOTH_MODE  = "2";
    public static final String OUT_MODE   = "3";

	@Inject
	protected ServiceContext svContext;

	@Logic
	public CommonInfo execute(CommonInfo in) {
		//ロット番号と検査番号は大文字に変換する
        String inputLtno = in.getSearchLtno().toUpperCase();
        String inputKnno = in.getSearchKnno().toUpperCase();
        String inputId = in.getUserId();
        String inputPw = in.getPassword();

        //入力チェック
        if((inputLtno == null || inputLtno.equals("")) && (inputKnno == null || inputKnno.equals(""))) {
			in.setMessage("検索条件を入力して下さい");
			in.setErrorFlg(true);
        }else {
        	if(StringUtil.hasValue(inputId) && StringUtil.hasValue(inputPw)) {
        		// SQL実行してユーザー取得
        		if(StringUtil.isEqual("error", SearchDataLogic.getLotDsp(in,inputId)) ) {
        			in.setMessage("許可されたユーザーではありません");
        			in.setErrorFlg(true);
        		}else {
        			String userPass = in.getLotDspUserBean().getUserpass();
        			if(StringUtil.isEqual(inputPw, userPass)) {
        				String ip = in.getIp();
        				System.out.println(in.getLotDspUserBean().getSimei() + "さんがログインしました。<IP:"+ ip +"><日時：" + DateUtil.getCurrentDateString() + ">");
        				//ログインしたらログイン情報を保持する
        				in.setLoginFlg(true);
        			}else {
            			in.setMessage("パスワードが違います");
            			in.setErrorFlg(true);
        			}
        		}
        	}else if(StringUtil.hasValue(inputId)) {
    			in.setMessage("パスワードを入力してください");
    			in.setErrorFlg(true);
        	}else if(StringUtil.hasValue(inputPw)) {
    			in.setMessage("ユーザーIDを入力してください");
    			in.setErrorFlg(true);
        	}else {
        	System.out.print("ID/PW 未入力"); // *****この場合ICASモードへ
        	}
        }

        // ここまででエラーあればおしまい
        if(in.isErrorFlg()) {
        	return in;
        }

        //最終的なモードを設定する
    	if(StringUtil.isEqual(ICAS_MODE, in.getMode()) ) {
    	    if(!StringUtil.hasValue(inputId) && !StringUtil.hasValue(inputPw)) {
    	    	in.setMode(ICAS_MODE);
    	    }else {
    	    	in.setMode(BOTH_MODE);
    	    }
    	}

		try {
			//各画面の検索結果格納用変数の宣言
	        String resStaff = null;
	        String resIcas  = null;
	        String resClad  = null;
	        String resCF    = null;

			//検索パラメータの設定
			Map param = new HashMap();
			param.put("LTNO", in.getSearchLtno());
			param.put("KNNO", in.getSearchKnno());

			//検索用のキー情報を取得
			String paraLinkkey = SearchDataLogic.getSearchKey(in,param);
			BigDecimal paraCyuzoYy = SearchDataLogic.getSearchKey2(in,param);

			//各画面の検索を実行
			// スタッフ情報
	        resStaff = SearchDataLogic.getStaffInfoData(in,paraLinkkey, paraCyuzoYy);

	        // ICAS情報
			resIcas = SearchDataLogic.getIcasInfoData(in,paraLinkkey,paraCyuzoYy);

			// 2011/07/06 クラッド情報取得処理・処理結果判定の追加
			resClad = SearchDataLogic.getCladInfoData(in,paraLinkkey);

			// 2012/12/14 徐冷情報取得処理・処理結果判定の追加
			resCF = SearchDataLogic.getCFInfoData(in,paraLinkkey,paraCyuzoYy);

			//現在表示ページを0に設定する
			in.setNowPage(0);
			//入力したロット番号と検査番号を改めて設定
			in.setSearchLtno(inputLtno);
			in.setSearchKnno(inputKnno);

			//検索結果とモードによって初期表示する画面を設定
	        if (resStaff.equals("success") && resIcas.equals("success") && resClad.equals("success") && resCF.equals("success")) {
	            if (in.getMode().equals(ICAS_MODE)) {
	            	in.setNextGamen("IcasInfo");
	            }else if (in.getMode().equals(STAFF_MODE)) {
	            	in.setNextGamen("ProgressInfo");
	            }else if (in.getMode().equals(BOTH_MODE)) {
	            	in.setNextGamen("ProgressInfo");
	            }else if (in.getMode().equals(OUT_MODE)) {
	            	in.setNextGamen("ErrorPage");
	            }
	        }

		} catch (Exception e) {
			in.setMessage(e.toString());
			in.setErrorFlg(true);
		}

		return in;
	}
}
