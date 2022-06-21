package ngyshanai.domain.logic.search;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import ngyshanai.common.lotdsp.CommonInfo;
import ngyshanai.common.msg.search.SearchInfo;
import ngyshanai.domain.logic.common.SearchDataLogic;
import nis.framework.ejb.logic.Logic;
import nis.framework.ejb.logic.ServiceContext;
public class SearchActionLogic {

	@Inject
	protected ServiceContext svContext;

	@Logic
	public CommonInfo execute(CommonInfo in, SearchInfo searchInfo) {
		// 値設定
		in.setSearchLtno(searchInfo.getLotNo());
		in.setSearchKnno(searchInfo.getKensaNo());

		//ロット番号と検査番号は大文字に変換する
        String inputLtno = in.getSearchLtno().toUpperCase();
        String inputKnno = in.getSearchKnno().toUpperCase();

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
			String paraLinkkey = SearchDataLogic.getSearchKey(in, param, searchInfo.getNowPage());
			BigDecimal paraCyuzoYy = SearchDataLogic.getSearchKey2(in, param, searchInfo.getNowPage());
			String tborderkey = SearchDataLogic.getSearchKey3(in,param, searchInfo.getNowPage());

			//各画面の検索を実行
			// スタッフ情報
	        resStaff = SearchDataLogic.getStaffInfoData(in, paraLinkkey, paraCyuzoYy,tborderkey);

	        // ICAS情報
			resIcas = SearchDataLogic.getIcasInfoData(in, paraLinkkey, paraCyuzoYy,tborderkey);

			// 2011/07/06 クラッド情報取得処理・処理結果判定の追加
			resClad = SearchDataLogic.getCladInfoData(in, paraLinkkey,tborderkey);

			// 2012/12/14 徐冷情報取得処理・処理結果判定の追加
			resCF = SearchDataLogic.getCFInfoData(in, paraLinkkey, paraCyuzoYy,tborderkey);

			//現在表示ページを設定
			in.setNowPage(searchInfo.getNowPage());

			//入力したロット番号と検査番号を改めて設定
			in.setSearchLtno(inputLtno);
			in.setSearchKnno(inputKnno);

	        if (resStaff.equals("success") && resIcas.equals("success") && resClad.equals("success") && resCF.equals("success")) {
                in.setTabSetRendered(true);
	        } else {
	        	in.setMessage("該当データがありません");
                in.setTabSetRendered(false);
	        }

		} catch (Exception e) {
			in.setMessage(e.toString());
			in.setErrorFlg(true);
		}

		return in;
	}
}
