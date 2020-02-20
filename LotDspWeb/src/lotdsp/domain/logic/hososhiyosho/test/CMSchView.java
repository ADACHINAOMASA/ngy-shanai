package lotdsp.domain.logic.hososhiyosho.test;




import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.faces.model.DataModel;

import jp.co.nikkeikin.nis.nagoya.common.XmlData;
import jp.co.nikkeikin.nis.nagoya.common.XmlDataModel;
import lotdsp.common.msg.hososhiyosho.LotInfo;
import nis.framework.util.StringUtil;


public class CMSchView  {
	
	/*
    public String update_action() {

        try {
            
        	SessionBean1 sb1 = new SessionBean1();
            String setsubi = "L-3";
            
            javax.xml.rpc.holders.StringHolder schList = new javax.xml.rpc.holders.StringHolder();
            int result = sb1.getSchData4CM(sb1.getAuthInfString(), setsubi, schList);
            
            Map map = CommonMessage.getSoapMessage(setsubi, result);
            if (map.get("msgStr") == null) {
                XmlDataModel xmlModel = CommonSchOperator.getXmlDataModel(sb1, schList);
                sb1.setSchListData(xmlModel);
                //更新日時設定
                CommonSchOperator.setUpdateDateTime(sb1);
                //ドロップダウンリストの値設定
                sb1.setSetsubi(setsubi);
                //スケジュールデータを設定
                sb1.setInfoMessage(setsubi + "のスケジュール情報の更新が完了しました。");
            } else {
                sb1.setErrorMessage(map.get("msgStr").toString());
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }   
        
    }
    */
    
    
	public static void main(String[] arg) {
    	SessionBean1 sb1 = new SessionBean1();
        String setsubi = "L-3";
        
        javax.xml.rpc.holders.StringHolder schList = new javax.xml.rpc.holders.StringHolder();
        
        try {
        	
			//int result = sb1.getSchData4CM("NLM-NIS-P160935,LEANG-SAY,2", setsubi, schList);

        	System.out.println("AuthInfString="+sb1.getAuthInfString());
        	
			int result = sb1.getSchData4CM(sb1.getAuthInfString(), setsubi, schList);

			System.err.println(schList.value);
			
			
			
			Map map = CommonMessage.getSoapMessage(setsubi, result);
			
            if (map.get("msgStr") == null) {
            	
                XmlDataModel xmlModel = CommonSchOperator.getXmlDataModel(sb1, schList);
                sb1.setSchListData(xmlModel);

                
                //更新日時設定
                CommonSchOperator.setUpdateDateTime(sb1);
                
                //ドロップダウンリストの値設定
                sb1.setSetsubi(setsubi);
                
                //スケジュールデータを設定
                sb1.setInfoMessage(setsubi + "のスケジュール情報の更新が完了しました。");
                
                
                System.out.println("ﾛｰﾙ="+CommonSchOperator.GetRollValue(sb1.getSchListData(), 1, 4));
                System.out.println("ﾛｯﾄ№="+CommonSchOperator.GetLtNoValue(sb1.getSchListData(), 5, 9));
                
                
//				Iterator<?> it = xmlModel.iterator();
//				while (it.hasNext()) {
//					Map<?, ?> m = (Map<?, ?>) it.next();
//					
//					//System.out.println(getString(m.toString()));
//
//					LotInfo lotInfo = new LotInfo();
//
//					//lotInfo.setRoll(getString(m, "TEISIJPNAME"));
//					//lotInfo.setLotNo(getString(m, "LTNO"));
//					//lotInfo.setZaishitsu(getString(m, "LTA"));
//					
//					System.out.println(map.get("TEISIJPNAME"));
//		            System.out.println(map.get("LTNO"));
//		                
//
//				}
				
                
                
            

            } else {
                sb1.setErrorMessage(map.get("msgStr").toString());
            }
			
			//System.err.println("result="+result+" "+sb1.getInfoMessage());
			//System.err.println(sb1.getSchListData().toString());
            
			
		} catch (RemoteException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
        
    	
    }
    
	protected static  String getString(String str) throws UnsupportedEncodingException {
		return new String(str.getBytes("UTF-8"), "UTF-8");
	}
	
	protected static String getString(Map<?, ?> map, String key) throws UnsupportedEncodingException {
		String str = ((String) map.get(key));
		if (StringUtil.hasNoValue(str)) {
			return "";
		}
		return getString(str);
	}
	
}
