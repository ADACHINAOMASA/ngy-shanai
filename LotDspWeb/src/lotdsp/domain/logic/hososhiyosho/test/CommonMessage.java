package lotdsp.domain.logic.hososhiyosho.test;




/*
 * CommonMessage.java
 *
 * Created on 2006/07/10, 19:28
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */



import java.util.*;
import java.text.*;
//import jp.co.nikkeikin.nis.nagoya.common.*;
//import javax.faces.component.html.HtmlOutputText;
//import javax.xml.rpc.holders.StringHolder;
//import javax.faces.FacesException;

/**
 *
 * @author Hirohiko-Matsushita
 */
public class CommonMessage {
 
    /**
     * Creates CMSchViewInfo new instance of CommonMessage
     */
    public CommonMessage() {
    }

    public static Map getSoapMessage(String setsubi, int result) { return getSoapMessage(setsubi, result, null, ""); };
    /**
     * <p>SOAP関数からの戻り値<code>result</code>により、
     * エラーメッセージ情報を<code>Map</code>で返す</p>
     * @param result SOAP関数戻り値、functionName SOAP関数名
     * @return エラーメッセージ情報の<code>Map</code>
     *         result:SOAP関数戻り値
     *         function：SOAP関数名
     *         msgType：メッセージタイプ(error:エラー,warning:警告)
     *         msgStr：メッセージ内容
     */
    public static Map getSoapMessage(String setsubi, int result, Object [] objs) { return getSoapMessage(setsubi, result, objs, ""); };
    /**
     * <p>SOAP関数からの戻り値<code>result</code>により、
     * エラーメッセージ情報を<code>Map</code>で返す</p>
     * @param result SOAP関数戻り値、functionName SOAP関数名
     * @return エラーメッセージ情報の<code>Map</code>
     *         result:SOAP関数戻り値
     *         function：SOAP関数名
     *         msgType：メッセージタイプ(error:エラー,warning:警告)
     *         msgStr：メッセージ内容
     */        
    public static Map getSoapMessage(String setsubi, int result, Object [] objs, String soapFuncID) {
    /**
     * <p>SOAP関数からの戻り値<code>result</code>により、
     * エラーメッセージ情報を<code>Map</code>で返す</p>
     * @param result SOAP関数戻り値、functionName SOAP関数名
     * @return エラーメッセージ情報の<code>Map</code>
     *         result:SOAP関数戻り値
     *         function：SOAP関数名
     *         msgType：メッセージタイプ(error:エラー,warning:警告)
     *         msgStr：メッセージ内容
     */        
        String msgStr = null;
        String msgType = "error";
        if(soapFuncID == null) soapFuncID = "";
        switch (result) {
            case 0:
                break;
            case -3000: //メッセージの頭にデータ種別を追加（例）実績データがありません。
                msgStr = "処理対象のデータがありません。";
                break;
            case -4000:
                //msgStr = "ここには移動できません。パス順が間違っています。";
                if (objs == null) {
                    msgStr = "ここには移動／挿入できません。パス順が間違っています。";
                } else {
                    MessageFormat mf = new MessageFormat("ここには{0}できません。パス順が間違っています。");
                    msgStr = mf.format(objs);
                }
                break;
            case -4006:
                //msgStr = "ここには移動できません。ロールが違います。";
                if (objs == null) {
                    msgStr = "ここには移動／挿入できません。ロールが違います。";
                } else {
                    MessageFormat mf = new MessageFormat("ここには{0}できません。ロールが違います。");
                    msgStr = mf.format(objs);
                }
                break;
/*  2006/11/08 削除                                
            case -4007:
                msgStr = "このサイクルは選択できません。";
                break;
*/
            case -4008:
                msgStr = "他のユーザーがロット一覧を使用しています。";
                break;
            case -4012: // 2006/11/08 追加
                msgStr = "ロットのパス数が多すぎるため使用できません。";
                break;
            case -4013: // 2006/11/08 追加
                msgStr = "ロット数が多すぎるため使用できません。";
                break;
            case -4015: // 2007/12/18 追加
                msgStr = "この順番で分割するとパス順が矛盾するロットがあります。順番を見直して下さい。";
                break;
            case -4018:
                msgStr = "削除はできません。";
                break;
            case -4019:
                msgStr = "移動はできません。";
                break;
            case -4020:
                msgStr = "開始済みのため操作できません。";
                break;
            case -4021:
                msgStr = "設備停止コードがおかしい。";
                break;
            case -4022: // 2008/01/16 追加
                msgStr = "登録を全て完了させて下さい。";
                break;
            case -4023: // 2008/01/16 追加
                msgStr = "継続中は開始できません。登録を完了させて下さい。";
                break;
            case -4024: // 2008/01/23 追加
                msgStr = "製品開始後の継続登録はできません。";
                break;
            case -4025: // 2008/03/05 追加
                msgStr = "圧延完了信号許可中です。";
                break;
            case -7200: // 2009/03/13 変更　開始されましたがエラーが発生しました。
                msgType = "warning";                
                msgStr = "開始登録に失敗しました。";
                break;
            case -7201:
                msgType = "warning";
                msgStr = "実績登録に失敗しました。";
                break;
            case -7202: //2008/02/05 追加
                msgType = "warning";
                msgStr = "オペレータ登録に失敗しました。";
                break;
            case -7203: //2008/02/05 追加
                msgType = "warning";
                msgStr = "ロール登録に失敗しました。";
                break;
            case -7300:
                msgType = "warning";
                msgStr = "プリセット送信に失敗しました。";
                break;
            case -7301:
                msgStr = "プリセット送信がされていません。再送信を行ってください。";
                break;                   
            case -7400:     // 2006/11/08 追加
            case -7401:     // 2008/01/24 追加
                msgStr = "終了時刻エラー。";
                break;
            case -7501:
                if(soapFuncID.equals("SetTeisiJisseki4CM_ContKbn")
                || soapFuncID.equals("SetOperatorData")
                || soapFuncID.equals("SetRollData")) {
                    msgStr = "この設備からの処理はできません。";
                } else {
                    msgStr = "ロット№エラー。";
                }
                break;
            case -7502:
                if(soapFuncID.equals("SetTeisiJisseki4CM_ContKbn")) {
                    msgStr = "設備停止コードが未登録です。";
                } else if(soapFuncID.equals("SetOperatorData")) {
                    msgStr = "オペレータコードエラー。"; 
                } else {
                    msgStr = "肉厚入力エラー。";
                }
                break;
            case -7503: 
                if(soapFuncID.equals("SetOperatorData")) {
                    msgStr = "オペレータが未登録です。";
                } else {    
                    msgStr = "スプール入力エラー。";
                }
                break;
            case -7504: 
                if(soapFuncID.equals("SetRollData")) {
                    msgStr = "ロールコードエラー。";
                } else {
                    msgStr = "次工程入力エラー。";
                }
                break;
            case -7505: 
                if(soapFuncID.equals("SetRollData")) {
                    msgStr = "ロールコードが未登録です。";
                } else {
                    msgStr = "パターン入力エラー。";
                }
                break;
            case -7506: 
                msgStr = "出側板厚入力エラー。";
                break;
            case -7507: 
                msgStr = "出側コイル入力エラー。";
                break;
            case -7508: 
                msgStr = "開始日時入力エラー。";
                break;
            case -7509: 
                msgStr = "開始時刻入力エラー。";
                break;
            case -7510: 
                msgStr = "終了時刻入力エラー。";
                break;
            default:
                msgStr = "予期せぬエラー（ERROR_CODE=" + result + "）が発生しました。";
                break;
        }
        Map map = new HashMap();
        //SOAP関数戻り値
        map.put("result", new Integer(result));
        //メッセージタイプ
        map.put("msgType", msgType);
        //メッセージ内容
        map.put("msgStr", msgStr);
        //メッセージ設定
        /*
        if (message != null) {
            message.setStyle("color : red;");
            message.setValue(msgStr);
        }
         */
        return map;
                                                                
    }

    //public static String getSelectCheckMessage(String type, int counter) { return getSelectCheckMessage(type, counter, null); };
    public static String getSelectCheckMessage(String type, int counter) {
        String msgStr = null;
        if (type.equals("oneNotFirst")) {
            switch (counter) {
                case -2: // 2007/12/18 追加
                    return "先頭行は指定できません。";
                default:
                    msgStr = null;
                    break;
            }
        }
        if (type.equals("one") || type.equals("oneNotFirst")) {
            switch (counter) {
                case 0:
                    msgStr = "一覧から１つ選択して下さい。";
                    break;
                case 1:
                    msgStr = null;
                    break;
                default:
                    msgStr = "選択は１つだけにして下さい。";
                    break;
            }
        } else if (type.equals("plural")) {
            switch (counter) {
                case 0:
                    msgStr = "一覧から１つ選択して下さい。";                    
                    break;
                default:
                    msgStr = null;
                    break;
            }
        } else if (type.equals("check")) {
            switch (counter) {
                case -1:
                    msgStr = "選択したパスに開始されているパスがあります。";
                    break;
                default:
                    msgStr = null;
                    break;
            }
        }
        /*
        //メッセージ設定
        if (message != null) {
            message.setStyle("color : red;");
            message.setValue(msgStr);
        }
         */
        return msgStr;
    }
    
    public static String getManualResultMessage(int returnCode) {
        String msgStr = null;
        switch (returnCode) {
            case 0:
                break;
            case 2: //異常信号 2008/02/04追加
                msgStr = "次が設備停止のため、手動画面に切り替えました。";
                break;
            case 10: //異常信号
                msgStr = "異常信号により手動画面に切り替えました。";
                break;
            case 11: //時間間隔不足
                msgStr = "圧延完了信号が前回完了時より早すぎたため、手動画面に切り替えました。";
                break;
            case 12: //コイル径異常
                msgStr = "出側コイル径の値が異常値を示していたため、手動画面に切り替えました。";
                break;
            case 13: //通信異常
                msgStr = "圧延完了信号受信にて通信エラーが発生したため、手動画面に切り替えました。";
                break;
            case 14: //データ異常
                msgStr = "圧延完了信号のデータが異常なため、手動画面に切り替えました。";
                break;
            case 15: //多重処理依頼
                msgStr = "圧延完了信号の多重処理が依頼されたため、手動画面に切り替えました。";
                break;
            case 16: //キー不一致
                msgStr = "圧延完了信号の対象ロットが一致しないため、手動画面に切り替えました。";
                break;
            case 17: //データ処理異常
                msgStr = "圧延完了信号処理でデータ処理エラーが発生したため、手動画面に切り替えました。";
                break;
            default:
                msgStr = "予期せぬエラー（ERROR_CODE=" + String.valueOf(returnCode) + "）が発生したため、手動画面に切り替えました。";
                break;
        }
        return msgStr;
    }
    
    public static String getAutoCheckMessage(int returnCode) {
        String msgStr = null;
        switch (returnCode) {
            case 0:
                break;
            case 1:
                msgStr = "開始登録して下さい。";
                break;
            case 2:
                msgStr = "次は設備停止です。";
                break;
            case 3:
                msgStr = "スケジュールがありません。";
                break;
            default:
                msgStr = "予期せぬエラー（ERROR_CODE=" + String.valueOf(returnCode) + "）が発生したため、自動画面に切り替えることができません。";
                break;
        }
        return msgStr;
    }    
    
    
    /*
    public static void setCompletionMessage(SessionBean1 sb1, String msgStr, HtmlOutputText message) {
        //message.setStyle("color : white;");
        //message.setValue(msgStr);
        sb1.setResultMessage(msgStr);
    }
     */
    /*
    public static void setResultMessage(SessionBean1 sb1, HtmlOutputText message) {
        String msgStr = sb1.getResultMessage();
        if ((msgStr == "") || (msgStr == null)) {
//            message.setStyle("color : red;");
            //message.setValue(null);
        } else {
//            message.setStyle("color : white;");
            //message.setValue(msgStr);
            //sb1.setResultMessage(null);
        }
        //sb1.setWarningMessage(null);
    }
     */
    /*
    public static void clearMessage(SessionBean1 sb1) {
        if (sb1.getErrorMessage() != null) sb1.setErrorMessage(null);
        if (sb1.getWarningMessage() != null) sb1.setWarningMessage(null);
        if (sb1.getInfoMessage() != null) sb1.setInfoMessage(null);        
    }
     */

}
