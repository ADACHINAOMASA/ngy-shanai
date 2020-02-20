package lotdsp.domain.logic.hososhiyosho.test;



import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
/*
 * CommonSchOperator.java
 *
 * Created on 2006/07/11, 15:03
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */
import java.util.Date;
import java.util.Map;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.xml.rpc.holders.StringHolder;

import jp.co.nikkeikin.nis.nagoya.common.XmlData;
import jp.co.nikkeikin.nis.nagoya.common.XmlDataModel;
import lotdsp.common.lotdsp.ErrorInfo;

/**
 * @author Hirohiko-Matsushita
 * 作成者の名前を示します。コンパイル時に-authorオプションをつけたとき、作成者を示すエントリがHTMLに追加されます。
 * @version バージョン番号を示します。コンパイル時に-versionオプションをつけたとき、バージョンを示すエントリがHTMLに追加されます。
 * @see 関連項目を示します。クラス名などが書かれ、それに対するHTMLが見つかれば自動的にリンクされます。
 */
public class CommonSchOperator {

    /* CYCLEKEY保存用 */
    public static String cyclekey;
    public static Double inslot;
    public static Double inspass;
    public static Double insdisp;

    /* メッセージ */
    public static String msg;

    /** Creates CMSchViewInfo new instance of CommonSchOperator */
    public CommonSchOperator() {
        cyclekey = null;
        inslot = new Double(0.0);
        inspass = new Double(0.0);
        insdisp = new Double(0.0);
        msg = null;
    }

    public static Map getXmlDataMap(SessionBean1 sb1,StringHolder soapstr, int rowindex) {
        XmlData xmldata = null;
        try {
            if(soapstr == null || soapstr.value.equals("")) {
                return null;
            } else {
                xmldata = new XmlData(soapstr.value);
                if(xmldata == null) {
                    sb1.setErrorMessage("予期せぬエラーが発生しました。(CommonSchOperator.XmlData.Null)");
                    //message.setValue("予期せぬエラーが発生しました。(XmlData : null)");
                    return null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            sb1.setErrorMessage("予期せぬエラーが発生しました。(CommonSchOperator.getXmlDataMap.XmlData.Exception)");
            //message.setValue("予期せぬエラーが発生しました。(XmlData : Exception)");
            return null;
        }
        if(xmldata.getRowCount() == 0) {
            return null;
        } else {
            return xmldata.getRowDataValue(rowindex);
        }
    }


    public static XmlDataModel getXmlDataModel(SessionBean1 sb1, StringHolder soapstr) {
        XmlData xmldata = null;
        XmlDataModel xmldatamodel = null;
        try {
            if(soapstr == null || soapstr.value.equals("")) {
                return null;
            } else {
                xmldata = new XmlData(soapstr.value);
                if(xmldata.getRowCount() == 0 ) sb1.setErrorMessage("データがありません。");
                if(xmldata == null) {
                    sb1.setErrorMessage("予期せぬエラーが発生しました。(CommonSchOperator.XmlData.Null)");
                    //message.setValue("予期せぬエラーが発生しました。(XmlData : null)");
                    return null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            sb1.setErrorMessage("予期せぬエラーが発生しました。(CommonSchOperator.getXmlDataModel.XmlData.Exception)");
            //message.setValue("予期せぬエラーが発生しました。(XmlData : Exception)");
            return null;
        }
        try {
            xmldatamodel = new XmlDataModel(xmldata);
            if(xmldatamodel == null) {
                sb1.setErrorMessage("予期せぬエラーが発生しました。(CommonSchOperator.getXmlDataModel.XmlDataModel.Null)");
                //message.setValue("予期せぬエラーが発生しました。(XmlDataModel : null)");
                return null;
            }
            return xmldatamodel;
        } catch (Exception e) {
            e.printStackTrace();
            sb1.setErrorMessage("予期せぬエラーが発生しました。(CommonSchOperator.getXmlDataModel.XmlDataModel.Exception)");
            //message.setValue("予期せぬエラーが発生しました。(XmlDataModel : Exception)");
            return null;
        }
    }

    /**
     * XmlデータをShift_JIS形式でパースする
     * @param sb1
     * @param soapstr
     * @return
     */
    public static XmlDataModel getXmlDataModel(ErrorInfo sb1, StringHolder soapstr) {
        XmlData xmldata = null;
        try {
            if(soapstr == null || soapstr.value.equals("")) {
            	sb1.setErrorMessage("データがありません。");
                return null;
            } else {
                xmldata = new XmlData(soapstr.value);
                if(xmldata.getRowCount() == 0 ) {
                	sb1.setErrorMessage("データがありません。");
                	return null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            sb1.setErrorMessage("予期せぬエラーが発生しました。(CommonSchOperator.getXmlDataModel.XmlData.Exception)");
            return null;
        }

        try {
            return new XmlDataModel(xmldata);
        } catch (Exception e) {
            e.printStackTrace();
            sb1.setErrorMessage("予期せぬエラーが発生しました。(CommonSchOperator.getXmlDataModel.XmlDataModel.Exception)");
            return null;
        }
    }

    /**
     * XmlデータをUTF-8形式でパースする
     * @param sb1
     * @param soapstr
     * @return
     */
    public static XmlDataModel getXmlDataModelUtf8(ErrorInfo sb1, StringHolder soapstr) {
        XmlData xmldata = null;
        try {
        	xmldata = new XmlData();
            if(soapstr == null || soapstr.value.equals("")) {
            	sb1.setErrorMessage("データがありません。");
                return null;
            } else {
            	xmldata.setXmlDataUtf8(soapstr.value);
                if(xmldata.getRowCount() == 0 ) {
                	sb1.setErrorMessage("データがありません。");
                	return null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            sb1.setErrorMessage("予期せぬエラーが発生しました。(CommonSchOperator.getXmlDataModel.XmlData.Exception)");
            return null;
        }

        try {
            return new XmlDataModel(xmldata);
        } catch (Exception e) {
            e.printStackTrace();
            sb1.setErrorMessage("予期せぬエラーが発生しました。(CommonSchOperator.getXmlDataModel.XmlDataModel.Exception)");
            return null;
        }
    }


    public static boolean refreshCycleListData(SessionBean1 sb1) {
        try {
            int result = -9999;
            javax.xml.rpc.holders.StringHolder cycleList = new javax.xml.rpc.holders.StringHolder();
            result = sb1.getCycleData(sb1.getAuthInfString(), sb1.getSetsubi(), cycleList);
            Map map = CommonMessage.getSoapMessage(sb1.getSetsubi(), result);
            if (map.get("msgStr") == null) {
                sb1.setCycleListData(getXmlDataModel(sb1, cycleList));
                //初期化
                sb1.setCycleLotListData(null);
                //更新日時設定
                setUpdateDateTime(sb1);
                return true;
            } else {
                sb1.setErrorMessage(map.get("msgStr").toString());
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            sb1.setErrorMessage("予期せぬエラーが発生しました。(CommonSchOperator.refreshLotListData.Exception)");
            //if (message != null) message.setValue("予期せぬエラーが発生しました。(refreshLotListData : Exception)");
            return false;
        }
    }

    public static boolean refreshLotListData(SessionBean1 sb1) { return refreshLotListData(sb1, null); };
    public static boolean refreshLotListData(SessionBean1 sb1, String cycleKey) {
        try {
            int result = -9999;
            javax.xml.rpc.holders.StringHolder soapstr = new javax.xml.rpc.holders.StringHolder();
            if (cycleKey == null) {
                result = sb1.getLotData4CM(sb1.getAuthInfString(), sb1.getSetsubi(), sb1.getCycleKeySchList4CM(), soapstr);
            } else {
                result = sb1.getLotData4CM(sb1.getAuthInfString(), sb1.getSetsubi(), cycleKey, soapstr);
            }
            // 開始サイクルはフラグを設定    // 2006/11/08 追加
            sb1.setNotNowCycle(true);
            if (result == -4007) {
                sb1.setNotNowCycle(false);
                result = 0;
            }
            Map map = CommonMessage.getSoapMessage(sb1.getSetsubi(), result);
            if (map.get("msgStr") == null) {
                sb1.setLotList4CMFirstIndex(0); // 2006/11/08 追加
                sb1.setLotListData(getXmlDataModel(sb1, soapstr));
                //更新日時設定
                setUpdateDateTime(sb1);
                return true;
            } else {
                    sb1.setErrorMessage(map.get("msgStr").toString());
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            sb1.setErrorMessage("予期せぬエラーが発生しました。(CommonSchOperator.refreshLotListData.Exception)");
            //if (message != null) message.setValue("予期せぬエラーが発生しました。(refreshLotListData : Exception)");
            return false;
        }
    }

    public static int refreshSchListData(SessionBean1 sb1) {
        int result;
        int stkSchListFirst = sb1.getSchList4CMFirstIndex();
        javax.xml.rpc.holders.StringHolder soapstr = new javax.xml.rpc.holders.StringHolder();
        try {
            result = sb1.getSchData4CM(sb1.getAuthInfString(), sb1.getSetsubi(), soapstr);
        } catch (Exception e) {
            e.printStackTrace();
            sb1.setErrorMessage("予期せぬエラーが発生しました。(CommonSchOperator.refreshSchListData.Exception)");
            //if (message != null) message.setValue("予期せぬエラーが発生しました。(getSchData4CM : Exception)");
            return stkSchListFirst;
        }
        Map map = CommonMessage.getSoapMessage(sb1.getSetsubi(), result);
        if (map.get("msgStr") == null) {
            XmlData xmldata = null;
            XmlDataModel xmldatamodel = null;
            try {
                //System.out.println(soapstr.value);
                xmldata = new XmlData(soapstr.value);
                //データ有無チェック
                if(xmldata.getRowCount() == 0) {
                    sb1.setErrorMessage("データがありません。");
                    return 0;
                }
                if(xmldata == null) {
                    sb1.setErrorMessage("予期せぬエラーが発生しました。(CommonSchOperator.refreshSchListData.XmlData.Null)");
                    //if (message != null) message.setValue("予期せぬエラーが発生しました。(XmlData : null)");
                    return stkSchListFirst;
                }
            } catch (Exception e) {
                e.printStackTrace();
                sb1.setErrorMessage("予期せぬエラーが発生しました。(CommonSchOperator.refreshSchListData.XmlData.Exception)");
                //if (message != null) message.setValue("予期せぬエラーが発生しました。(XmlData : Exception)");
                return stkSchListFirst;
            }
            /**
             *　初期表示行設定
             *　以下のパターンの場合に行セットする
             *　完了(9)・一般(0)、完了(9)・開始(1)、完了(9)・実績待ち(2)、完了(9)・継続(3)の場合は完了行
             *　完了(9)・完了(9)の場合は最後の完了行
             *　実績待ち(2)・一般(0)、実績待ち(2)・開始(1)、実績待ち(2)・継続(3)の場合は実績待ち行
             *　継続(3)・一般(0)、継続(3)・開始(1)、継続(3)・実績待ち(2)の場合は継続行
             */
            int j = 0;
            int first = 0;
            int first_9_2 = -1;
            int rowCount = xmldata.getRowCount();
            for (int i=0; i<rowCount - 1; i++) {
                if (i < rowCount - 1) {
                    j = i + 1;
                } else {
                    j = i;
                }
                int trksts_i = ((Double)xmldata.getRowDataValue(i).get("TRKSTS")).intValue();
                int trksts_j = ((Double)xmldata.getRowDataValue(j).get("TRKSTS")).intValue();
                switch (trksts_i) {
                    //完了
                    case 9:
                        if ((trksts_j == 0) || (trksts_j == 1)) {
                            first = i;
                            if (first_9_2 == -1) first_9_2 = i;
                        } else if ((trksts_j == 2) || (trksts_j == 3))  {
                            first = j;
                            if (first_9_2 == -1) first_9_2 = j;
                        } else if ((trksts_j == 9)) {
                            first = j;
                        }
                        break;
                    //実績待ち
                    case 2:
                    //継続
                    case 3:
                    //開始
                    case 1:
                        if ((trksts_j == 0) || (trksts_j == 1) || (trksts_j == 2) || (trksts_j == 3)) {
                            first = i;
                            if (first_9_2 == -1) first_9_2 = i;
                        }
                        break;
                }
                /*
                if ((((Double)xmldata.getRowDataValue(i).get("TRKSTS")).intValue() == 9 ) & (((Double)xmldata.getRowDataValue(j).get("TRKSTS")).intValue() != 9))  {
                    first = i;
                }
                 */
            }
            if(first_9_2 != -1) first = first_9_2;
            //前回と今回の先頭データのCYCLEKEYが不一致の場合、他ユーザーによるスケジュール更新が行われたと判断して今回の表示位置に強制変更する
            if(sb1.getSchList4CMFirstCycleKey() != null) {
                if(!(sb1.getSchList4CMFirstCycleKey().equals(xmldata.getRowDataValue(0, "CYCLEKEY").toString()))) {
                    sb1.setSchList4CMFirstIndex(first);
                }
            }
            sb1.setSchList4CMFirstCycleKey(xmldata.getRowDataValue(0, "CYCLEKEY").toString());

            try {

                xmldatamodel = new XmlDataModel(xmldata);
                if(xmldatamodel == null) {
                    sb1.setErrorMessage("予期せぬエラーが発生しました。(CommonSchOperator.refreshSchListData.XmlDataModel.Null)");
                    //if (message != null) message.setValue("予期せぬエラーが発生しました。(XmlDataModel : null)");
                    return stkSchListFirst;
                }
                sb1.setSchListData(xmldatamodel);
                //更新日時設定
                setUpdateDateTime(sb1);
                //許可、拒否の状態更新
                javax.xml.rpc.holders.IntHolder enableEndSign = new javax.xml.rpc.holders.IntHolder();
                javax.xml.rpc.holders.IntHolder autoRefresh = new javax.xml.rpc.holders.IntHolder();
                javax.xml.rpc.holders.StringHolder lastUpdate = new javax.xml.rpc.holders.StringHolder();
                javax.xml.rpc.holders.IntHolder manualResult = new javax.xml.rpc.holders.IntHolder();
                result = sb1.getUseStatus(sb1.getAuthInfString(), sb1.getSetsubi(), enableEndSign, autoRefresh, lastUpdate, manualResult);
                if(result == 0) {
                    if(sb1.termKbn.value == 1) { //メインオペレータ画面のみ
                        switch (enableEndSign.value) {
                            case 0 : //拒否
                                sb1.setSchList4CMDispType(CommonConst.DISP_TYPE_MANU_ALL);
                                sb1.setSchListButtonProperty(sb1.getSchList4CMDispType());
                                break;
                            case 1 : //許可
                                switch (autoRefresh.value) {
                                    case 0 : //手動
                                        sb1.setSchList4CMDispType(CommonConst.DISP_TYPE_MANU_KANRYO);
                                        sb1.setSchListButtonProperty(sb1.getSchList4CMDispType());
                                        break;
                                    case 1 : //自動
                                        break;
                                }
                                break;
                        }
                    }
                    //GetUseStausのLastUpdateを設定
                    sb1.setUseStatusLastUpdate(lastUpdate.value);
                } else {
                    sb1.setErrorMessage("利用ステータス情報取得に失敗しました（ERR_CODE = " + String.valueOf(result) + "）");
                }
                return first;
            } catch (Exception e) {
                e.printStackTrace();
                sb1.setErrorMessage("予期せぬエラーが発生しました。(CommonSchOperator.refreshSchListData.XmlDataModel.Null)");
                //if (message != null) message.setValue("予期せぬエラーが発生しました。(XmlDataModel : Exception)");
                return stkSchListFirst;
            }
        } else {
            sb1.setErrorMessage(map.get("msgStr").toString());
        }
        return stkSchListFirst;
    }

    public static void setUpdateDateTime(SessionBean1 sb1) {
        sb1.setUpdateDateTime(new Date());
    }

    public static void redirectPage(String szPage)
    {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            javax.faces.application.Application app = context.getApplication();
            UIViewRoot view = app.getViewHandler().createView(context, szPage);
            context.setViewRoot(view);
            context.renderResponse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isLotInfChecks(SessionBean1 sb1, String xmlDataStr) {
        try {
            javax.xml.rpc.holders.IntHolder isEditing = new javax.xml.rpc.holders.IntHolder();
            int result = sb1.lotInfChecks(sb1.getAuthInfString(), xmlDataStr, isEditing);
            Map map = CommonMessage.getSoapMessage(sb1.getSetsubi(), result);
            if (map == null) return false;
            if (map.get("msgStr") == null) {
                if (isEditing.value != 0) {
                    sb1.setErrorMessage("ロット一覧で使用中のデータが選択されています。");
                    //if (message != null) message.setValue("ロット一覧で使用中のデータが選択されています。");
                    return false;
                }
            } else {
                sb1.setErrorMessage(map.get("msgStr").toString());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean refreshPassOrderData(SessionBean1 sb1) { return refreshPassOrderData(sb1, null); };
    public static boolean refreshPassOrderData(SessionBean1 sb1, String cycleKey) {
        try {
            int result = -9999;
            javax.xml.rpc.holders.StringHolder soapstr = new javax.xml.rpc.holders.StringHolder();
            if (cycleKey == null) {
                result = sb1.getPassOrderData(sb1.getAuthInfString(), sb1.getSetsubi(), sb1.getCycleKeySchList4CM(), soapstr);
            } else {
                result = sb1.getPassOrderData(sb1.getAuthInfString(), sb1.getSetsubi(), cycleKey, soapstr);
            }
            Map map = CommonMessage.getSoapMessage(sb1.getSetsubi(), result);
            if (map.get("msgStr") == null) {
                sb1.setPassOrderBefData(CommonSchOperator.getXmlDataModel(sb1, soapstr));
                sb1.getPassOrderList().makeEditData(sb1.getPassOrderBefData());
                return true;
            } else {
                    sb1.setErrorMessage(map.get("msgStr").toString());
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            sb1.setErrorMessage("予期せぬエラーが発生しました。(CommonSchOperator.refreshLotListData.Exception)");
            //if (message != null) message.setValue("予期せぬエラーが発生しました。(refreshLotListData : Exception)");
            return false;
        }
    }

    /* Disable & Rendered */

    /**
     * プロパティ hinDisabled の取得メソッド。
     * @return プロパティ hinDisabled の値。
     */
    public static boolean IsHinDispRendered(XmlDataModel xmlModel) {
        try {
            if(xmlModel == null) return false;
            if(xmlModel.isRowAvailable()) {
                    Map map = (Map)xmlModel.getRowData();
                    if (map == null) return false;
                    String hindsp = (String)map.get("HINDSP");
                    if (hindsp == null) return false;
            }
            //return hindsp.trim().equals("1");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        //return this.hinDisabled;
    }

    /**
     * プロパティ selCheckDisabled の取得メソッド。
     * @return プロパティ selCheckDisabled の値。
     */
    public static boolean IsSelCheckDisabled(XmlDataModel xmlModel) {
        try {
            if(xmlModel == null) return false;
            if(xmlModel.isRowAvailable()) {
                Map map = (Map)xmlModel.getRowData();
                if (map == null) return false;
                String selected = (String)map.get("SELECTED");
                if (selected == null) return false;
                return (selected.trim().toString().equals("9"));
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * プロパティ selCheckRendered の取得メソッド。
     * @return プロパティ selCheckRendered の値。
     */
    public static boolean IsSelCheckRendered(XmlDataModel xmlModel, boolean isAutoButtonDisable) {
        try {
            if(xmlModel == null) return false;
            if(xmlModel.isRowAvailable()) {
                 //Map map = (Map)this.schList1.getRowData();
                Map map = (Map)xmlModel.getRowData();
                if (map == null) return false;
                //自動の場合はボタン、手動の場合はチェックボックスを表示
                if(isAutoButtonDisable) {
                    //自動
                    return false;
                } else {
                    //手動
                    Double trksts = (Double)map.get("TRKSTS");
                    Double stskbn = (Double)map.get("STSKBN");
                    int cyclekbn = Integer.parseInt(map.get("CYCLEKBN").toString());
                    if (trksts == null) {
                        return false;
                    }
                    //return ((trksts.intValue() == 0 || trksts.intValue() == 1)
                    //       && (cyclekbn != 0 && cyclekbn != 2 && stskbn.intValue() != 9));
                    return ((trksts.intValue() == 0 || trksts.intValue() == 1) && cyclekbn != 2);
                }
            }
            return false;
        } catch (Exception e ) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * プロパティ selCheckButtonRendered の取得メソッド。
     * @return プロパティ selCheckButtonRendered の値。
     */
    public static boolean GetSelCheckButtonRendered(XmlDataModel xmlModel, boolean isAutoButtonDisable) {
        try {
            if(xmlModel == null) return false;
            if(xmlModel.isRowAvailable()) {
                Map map = (Map)xmlModel.getRowData();
                if(map == null) return false;
                if(map.get(CommonConst.TEISI_ITEM) != null) {
                    return false;
                } else if(isAutoButtonDisable) {
                    if(Integer.parseInt(map.get("DATAKBN").toString()) == 2) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        //return this.selCheckButtonRendered;
    }

    /* Style Class */

    /**
     * プロパティ schListRowStyle の取得メソッド。
     * @return プロパティ schListRowStyle の値。
     */
    public static String getSchListRowStyle(XmlDataModel xmlModel) {
        try {
            if(xmlModel == null) return "background-color : white;";
            if(xmlModel.isRowAvailable()) {
                Map map = (Map)xmlModel.getRowData();
                if (map == null) return "row-white-black";
                int trksts = ((Double)map.get("TRKSTS")).intValue();
                int datakbn = Integer.parseInt(map.get("DATAKBN").toString());
                switch (trksts) {
                    case CommonConst.TRKSTS_IPPAN_CODE: //一般
                        switch (datakbn) {
                            case CommonConst.DATAKBN_TEISI_CODE:
                                return "background-color : lime;";
                            case CommonConst.DATAKBN_LOTINF_CODE:
                            case CommonConst.DATAKBN_TENKENBAN_CODE:
                            case CommonConst.DATAKBN_KAERIZAI_CODE:
                            default:
                                return "background-color : white;";
                        }
                    case CommonConst.TRKSTS_KAISI_CODE: //開始
                    case CommonConst.TRKSTS_KEIZOKU_CODE: //継続
                        return "background-color : aqua;";
                    case CommonConst.TRKSTS_JISSEKI_CODE: //実績待
                        return "background-color : teal;";
                    case CommonConst.TRKSTS_KANRYO_CODE: //完了
                        return "background-color : navy;";
                    default:
                        return "background-color : white;";
                }
            }
            return "background-color : white;";
        } catch (Exception e) {
            return "background-color : white;";
        }
        //return this.schListRowStyle;
    }

    public static String GetSchListRowClasses(XmlDataModel xmlModel, int rowFirst, int rows) {
        try {
            if(xmlModel == null) return "row-white-black";
            StringBuffer schList1ColumnStyle = new StringBuffer();
            for (int i = rowFirst; i < rowFirst + rows; i++ ) {
                xmlModel.setRowIndex(i);
                if (xmlModel.isRowAvailable()) {
                    Map map = (Map)xmlModel.getRowData();
                    if (map == null) return "row-white-black";
                    int trksts = ((Double)map.get("TRKSTS")).intValue();
                    int datakbn = Integer.parseInt(map.get("DATAKBN").toString());
                    switch (trksts) {
                        case CommonConst.TRKSTS_IPPAN_CODE:     //一般
                            switch (datakbn) {
                                case CommonConst.DATAKBN_TEISI_CODE:
                                    schList1ColumnStyle.append("row-lime-black");
                                    break;
                                case CommonConst.DATAKBN_LOTINF_CODE:
                                case CommonConst.DATAKBN_TENKENBAN_CODE:
                                case CommonConst.DATAKBN_KAERIZAI_CODE:
                                default:
                                    schList1ColumnStyle.append("row-white-black");
                                    break;
                            }
                            break;
                        case CommonConst.TRKSTS_KAISI_CODE:     //開始
                        case CommonConst.TRKSTS_KEIZOKU_CODE:   //継続
                            schList1ColumnStyle.append("row-aqua-black");
                            break;
                        case CommonConst.TRKSTS_JISSEKI_CODE:   //実績待
                            schList1ColumnStyle.append("row-teal-white");
                            break;
                        case CommonConst.TRKSTS_KANRYO_CODE:    //完了
                            schList1ColumnStyle.append("row-navy-yellow");
                            break;
                        default:
                            schList1ColumnStyle.append("row-white-black");
                            break;
                    }
                    if ((rowFirst + rows) != i) schList1ColumnStyle.append(",");
                }
            }
            return schList1ColumnStyle.toString();
        } catch (Exception e) {
            return "row-white-black";
        }
    }

    /**
     * プロパティ columnClasses の取得メソッド。
     * @return プロパティ columnClasses の値。
     */
    public static String GetColumnClasses(XmlDataModel xmlModel) {
        try {
            if(xmlModel == null) return ",,,,,,,,,,,,column-black";
            if (xmlModel.isRowAvailable()) {
                Map map = (Map)xmlModel.getRowData();
                if (map == null) return ",,,,,,,,,,,,column-black";
                Double cycleOrder = (Double)map.get("CYCLEORDER");
                if( cycleOrder.intValue() % 2 == 0) {
                    return ",,,,,,,,,,,,column-black";
                } else {
                    return ",,,,,,,,,,,,column-orange";
                }
            }
            return ",,,,,,,,,,,,column-black";
        } catch (Exception e) {
            return ",,,,,,,,,,,,column-black";
        }
        //return this.columnClasses;
    }

    /**
     * プロパティ cycleStyleClass の取得メソッド。
     * @return プロパティ cycleStyleClass の値。
     */
    public static String GetCycleStyleClass(XmlDataModel xmlModel) {
        try {
            if(xmlModel == null) return "column-black";
            if (xmlModel.isRowAvailable()) {
                Map map = (Map)xmlModel.getRowData();
                if (map == null) return "column-black";
                Double cycleOrder = (Double)map.get("CYCLEORDER");
                if( cycleOrder.intValue() % 2 == 0) {
                    return "column-black";
                } else {
                    return "column-orange";
                }
            }
            return "column-black";
        } catch (Exception e) {
            return "column-black";
        }
        //return this.cycleStyleClass;
    }

    /* Value Set + Format Change */

    /**
     * プロパティ selectedConverter の取得メソッド。
     * @return プロパティ selectedConverter の値。
     */
    public static String GetSelCheckValue(XmlDataModel xmlModel) {
        try {
            if(xmlModel == null) return null;
            if (xmlModel.isRowAvailable()) {
                Map map = (Map)xmlModel.getRowData();
                if (map.get("SELECTED").equals("9")) {
                    return "9";
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        //return this.selectedConverter;
    }

    /**
     * プロパティ chkStsConverter の取得メソッド。
     * @return プロパティ chkStsConverter の値。
     */
    public static String GetChkStsValue(XmlDataModel xmlModel) {
        try {
            if(xmlModel == null) return null;
            if (xmlModel.isRowAvailable()) {
                Map map = (Map)xmlModel.getRowData();
                if (map == null) return null;
                Double chksts = (Double)map.get("CHKSTS");
                switch (chksts.intValue()) {
                    case 0:
                        return null;
                    case 1:
                        return "ロット\n無し";
                    case 2:
                        return "板厚\n変更";
                    case 3:
                        return "板幅\n変更";
                    case 4:
                        return "支給先\n変更";
                    case 5:
                        return "パス\n変更";
                    case 6:
                        return "前パス\n未スケ";
                    case 7:
                        return "板厚が\n違う";
                }
            }
            return null;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

     /**
     * プロパティ selectedConverter の取得メソッド。
     * @return プロパティ selectedConverter の値。
     */
    public static boolean IsSelCheckValue(XmlDataModel xmlModel) {
        try {
            if(xmlModel == null) return false;
            if (xmlModel.isRowAvailable()) {
                Map map = (Map)xmlModel.getRowData();
                if (map == null) return false;
                if (map.get("SELECTED").equals("1")) {
                    return true;
                }
            }
            return false;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * プロパティ selCheckValue の設定メソッド。
     * @param selCheckValue プロパティ selCheckValue の新しい値。
     * チェックボックス用
     */
    public static void SetSelCheckValueCheckBox(XmlDataModel xmlModel, int rowIndex, boolean selCheckValue) {
        try {
            if(xmlModel != null) {
                if (selCheckValue) {
                    xmlModel.setCellString(rowIndex, "SELECTED", "1");
                } else {
                    xmlModel.setCellString(rowIndex, "SELECTED", "0");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * プロパティ selectedConverter の設定メソッド。
     * @param selectedConverter プロパティ selectedConverter の新しい値。
     * ラジオボタン用
     */
    public static void SetSelCheckValueRadioButton(XmlDataModel xmlModel, String selectedConverter, int rowIndex) {
        try {
            if (xmlModel != null && selectedConverter != null) {
                int rowCount = xmlModel.getRowCount();
                for (int i=0; i<rowCount; i++) {
                    if (((XmlData)xmlModel.getWrappedData()).getCellString(i, "SELECTED").equals("1")) {
                    } else {
                        if (i == rowIndex) {
                                if (selectedConverter.equals("9")) {
                                    xmlModel.setCellString(i, "SELECTED", "9");
                                } else {
                                    xmlModel.setCellString(i, "SELECTED", "0");
                                }
                                //this.selectedConverter = selectedConverter;
                        } else {
                            xmlModel.setCellString(i, "SELECTED", "0");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int SelCheckCounterCheckBox(XmlDataModel xmlModel) {
        try {
            // CYCLEKEY保存用
            /**
            String cyclekey;
            Double inslot;
            Double inspass;
            Double insdisp;
             */
            int counter = 0;
            if(xmlModel != null) {
                //int selCheckTrksts = 0;
                int rowCount = xmlModel.getRowCount();
                XmlData xmldata = (XmlData)xmlModel.getWrappedData();
                for (int i=0; i<rowCount; i++) {
                    if (xmldata.getRowDataValue(i, "SELECTED").equals("1")) {
                        //選択パス内で開始されているパスがある場合は変更不可
                        //if (xmldata.getRowDataValue(i, "TRKSTS") != null) {
                        //    Double dbl = new Double(xmldata.getRowDataValue(i, "TRKSTS").toString());
                        //    if (dbl.intValue() == 1) selCheckTrksts = -1;
                        //}
                        cyclekey = xmldata.getRowDataValue(i, "CYCLEKEY").toString();
                        inslot = (Double)xmldata.getRowDataValue(i, "LOTORDER");
                        inspass = (Double)xmldata.getRowDataValue(i, "PASSORDER");
                        insdisp = (Double)xmldata.getRowDataValue(i, "DISPORDER");
                        counter++;
                    }
                }
            }
            return counter;
        } catch (Exception e) {
            e.printStackTrace();
            return -9999;
        }
    }

    public static int SelCheckCounterRadioButton(XmlDataModel xmlModel) {
        try {
            int counter = 0;
            if(xmlModel != null) {
                int rowCount = xmlModel.getRowCount();
                XmlData xmldata = (XmlData)xmlModel.getWrappedData();
                XmlData lotCheckData = new XmlData(xmldata.getXmlData());
                for (int i=0; i<rowCount; i++) {
                    if (xmldata.getRowDataValue(i, "SELECTED").equals("9")) {
                        cyclekey = xmldata.getRowDataValue(i, "CYCLEKEY").toString();
                        inslot = (Double)xmldata.getRowDataValue(i, "LOTORDER");
                        inspass = (Double)xmldata.getRowDataValue(i, "PASSORDER");
                        xmldata.setCellString(i, "SELECTED", "0");
                        counter++;
                    }
                    if (lotCheckData.getRowDataValue(i, "SELECTED").equals("0") || lotCheckData.getRowDataValue(i, "SELECTED").equals("1")) {
                        lotCheckData.setCellString(i, "SELECTED", "0");
                    } else if (lotCheckData.getRowDataValue(i, "SELECTED").equals("9")) {
                        lotCheckData.setCellString(i, "SELECTED", "1");
                    }
                }
            }
            return counter;
        } catch (Exception e) {
            e.printStackTrace();
            return -9999;
        }
    }

    /**
     * プロパティ schDateSelect の取得メソッド。
     * @return プロパティ schDateSelect の値。
     */
    public static Date GetSchDateValue(XmlDataModel xmlModel) {
        try {
            if(xmlModel == null) return null;
            if (xmlModel.isRowAvailable()) {
                Map map = (Map)xmlModel.getRowData();
                if (map == null) return null;
                if (map.get("ENDDATE") == null) {
                    if (map.get("SCHDATE") != null) {
                        return (Date)map.get("SCHDATE");
                    }
                } else {
                    return (Date)map.get("ENDDATE");
                }
            }
            return null;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
        //return this.schDateSelect;
    }

    public static boolean IsModifyCheck(XmlDataModel xmlModel) {
        try {
            if(xmlModel == null) return false;
            msg = null;
            XmlData xmldata = (XmlData)xmlModel.getWrappedData();
            int rowCount = xmldata.getRowCount();
            for (int i=0; i<rowCount; i++) {
                Map map = (Map)xmldata.getRowDataValue(i);
                if (map == null) return false;
                int datakbn = Integer.parseInt(map.get("DATAKBN").toString());
                if (map.get("SELECTED").equals("1")
                && ((datakbn == CommonConst.DATAKBN_TENKENBAN_CODE)
                || (datakbn == CommonConst.DATAKBN_KAERIZAI_CODE))) {
                    msg = CommonConst.TENKENBAN_NAME + "、" + CommonConst.KAERIZAI_NAME + "が含まれています。";
                    //message.setValue("点検板、返り材が含まれています。");
                    //completionMessage.setValue(null);
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * プロパティ ltNoConverter の取得メソッド。
     * @return プロパティ ltNoConverter の値。
     */
    public static String GetLtNoValue(XmlDataModel xmlModel, int teisiSta, int teisiEnd) {
        try {
            if(xmlModel == null) return null;
            if (xmlModel.isRowAvailable()) {
                Map map = (Map)xmlModel.getRowData();
                if(map == null) return null;
                if(map.get(CommonConst.TEISI_ITEM) == null) {
                    return map.get("LTNO").toString();
/*
                    int datakbn = Integer.parseInt(map.get("DATAKBN").toString());
                    switch (datakbn) {
                        case CommonConst.DATAKBN_LOTINF_CODE:
                            return map.get("LTNO").toString();
                        case CommonConst.DATAKBN_TENKENBAN_CODE:
                            return CommonConst.TENKENBAN_NAME;
                        case CommonConst.DATAKBN_KAERIZAI_CODE:
                            return CommonConst.KAERIZAI_NAME;
*/
                } else {
                    return CommonUtility.ExtractString(map.get(CommonConst.TEISI_ITEM).toString(), teisiSta, teisiEnd);
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        //return this.ltNoConverter;
    }

    /**
     * プロパティ hotclNoConverter の取得メソッド。
     * @return プロパティ hotclNoConverter の値。
     */
    public static String GetHotclNoValue(XmlDataModel xmlModel) {
        try {
            if(xmlModel == null) return null;
            if (xmlModel.isRowAvailable()) {
                Map map = (Map)xmlModel.getRowData();
                if(map == null) return null;
                if(map.get(CommonConst.TEISI_ITEM) == null) {
                    if(map.get("HOTCL_NO") != null) {
                        return map.get("HOTCL_NO").toString();
                    }
                } else {
                    return CommonUtility.ExtractString(map.get(CommonConst.TEISI_ITEM).toString(), 1, 4);
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        //return this.hotclNoConverter;
    }

    /**
     * プロパティ ltaConverter の取得メソッド。
     * @return プロパティ ltaConverter の値。
     */
    public static String GetLtaValue(XmlDataModel xmlModel, int teisiSta, int teisiEnd) {
        try {
            if(xmlModel == null) return null;
            if (xmlModel.isRowAvailable()) {
                Map map = (Map)xmlModel.getRowData();
                if(map == null) return null;
                if(map.get(CommonConst.TEISI_ITEM) == null) {
                    return map.get("LTA").toString();
                } else {
                    return CommonUtility.ExtractString(map.get(CommonConst.TEISI_ITEM).toString(), teisiSta, teisiEnd);
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        //return this.ltaConverter;
    }

    /**
     * プロパティ inAtsuConverter の取得メソッド。
     * @return プロパティ inAtsuConverter の値。
     */
    public static String GetInAtsuValue(XmlDataModel xmlModel, int teisiSta, int teisiEnd) {
        try {
            if(xmlModel == null) return null;
            if (xmlModel.isRowAvailable()) {
                Map map = (Map)xmlModel.getRowData();
                if(map == null) return null;
                if(map.get(CommonConst.TEISI_ITEM) == null) {
                    DecimalFormat decimalFormat = new DecimalFormat(CommonConst.FORMAT_INATSU);
                    return decimalFormat.format(map.get("INATSU"));
                } else {
                    return CommonUtility.ExtractString(map.get(CommonConst.TEISI_ITEM).toString(), teisiSta, teisiEnd);
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        //return this.inAtsuConverter;
    }

    /**
     * プロパティ otAtsuConverter の取得メソッド。
     * @return プロパティ otAtsuConverter の値。
     */
    public static String GetOtAtsuValue(XmlDataModel xmlModel, int teisiSta, int teisiEnd) {
        try {
            if(xmlModel == null) return null;
            if (xmlModel.isRowAvailable()) {
                Map map = (Map)xmlModel.getRowData();
                if(map == null) return null;
                if(map.get(CommonConst.TEISI_ITEM) == null) {
                    DecimalFormat decimalFormat = new DecimalFormat(CommonConst.FORMAT_OTATSU);
                    return decimalFormat.format(map.get("OTATSU"));
                } else {
                    return CommonUtility.ExtractString(map.get(CommonConst.TEISI_ITEM).toString(), teisiSta, teisiEnd);
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        //return this.otAtsuConverter;
    }

    /**
     * プロパティ akkaritsuConverter の取得メソッド。
     * @return プロパティ akkaritsuConverter の値。
     */
    public static String GetAkkaritsuValue(XmlDataModel xmlModel) {
        try {
            if(xmlModel == null) return null;
            if (xmlModel.isRowAvailable()) {
                Map map = (Map)xmlModel.getRowData();
                if (map == null) return null;
                if(map.get(CommonConst.TEISI_ITEM) == null) {
                    DecimalFormat decimalFormat = new DecimalFormat(CommonConst.FORMAT_AKKARITSU);
                    return decimalFormat.format(map.get("AKKARITSU"));
                } else {
                    return CommonUtility.ExtractString(map.get(CommonConst.TEISI_ITEM).toString(), 19, 22);
                }
            }
            return "0.0";
        } catch (Exception e) {
            e.printStackTrace();
            return "0.0";
        }
        //return this.akkaritsuConverter;
    }

    /**
     * プロパティ otHabaConverter の取得メソッド。
     * @return プロパティ otHabaConverter の値。
     */
    public static String GetOtHabaValue(XmlDataModel xmlModel, int teisiSta, int teisiEnd) {
        try {
            if(xmlModel == null) return null;
            if (xmlModel.isRowAvailable()) {
                Map map = (Map)xmlModel.getRowData();
                if(map == null) return null;
                if(map.get(CommonConst.TEISI_ITEM) == null) {
                    DecimalFormat decimalFormat = new DecimalFormat(CommonConst.FORMAT_OTHABA);
                    return decimalFormat.format(map.get("OTHABA"));
                } else {
                    return CommonUtility.ExtractString(map.get(CommonConst.TEISI_ITEM).toString(), teisiSta, teisiEnd);
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        //return this.otHabaConverter;
    }

    /**
     *
     */
    public static String GetLtxValue(XmlDataModel xmlModel, int teisiSta, int teisiEnd) {
        try {
            if(xmlModel == null) return null;
            if (xmlModel.isRowAvailable()) {
                Map map = (Map)xmlModel.getRowData();
                if(map == null) return null;
                if(map.get(CommonConst.TEISI_ITEM) == null) {
                    DecimalFormat decimalFormat = new DecimalFormat(CommonConst.FORMAT_LTX);
                    return decimalFormat.format(map.get("LTX"));
                } else {
                    return CommonUtility.ExtractString(map.get(CommonConst.TEISI_ITEM).toString(), teisiSta, teisiEnd);
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        //return this.otHabaConverter;
    }

    /**
     *
     */
    public static String GetLtyValue(XmlDataModel xmlModel, int teisiSta, int teisiEnd) {
        try {
            if(xmlModel == null) return null;
            if (xmlModel.isRowAvailable()) {
                Map map = (Map)xmlModel.getRowData();
                if(map == null) return null;
                if(map.get(CommonConst.TEISI_ITEM) == null) {
                    DecimalFormat decimalFormat = new DecimalFormat(CommonConst.FORMAT_LTY);
                    return decimalFormat.format(map.get("LTY"));
                } else {
                    return CommonUtility.ExtractString(map.get(CommonConst.TEISI_ITEM).toString(), teisiSta, teisiEnd);
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        //return this.otHabaConverter;
    }

    /**
     *
     */
    public static String GetKeijyoValue(XmlDataModel xmlModel, int teisiSta, int teisiEnd) {
        try {
            if(xmlModel == null) return null;
            if (xmlModel.isRowAvailable()) {
                Map map = (Map)xmlModel.getRowData();
                if(map == null) return null;
                if(map.get(CommonConst.TEISI_ITEM) == null) {
                    if(map.get("KEIJYO") != null) {
                        return map.get("KEIJYO").toString();
                    }
                } else {
                    return CommonUtility.ExtractString(map.get(CommonConst.TEISI_ITEM).toString(), teisiSta, teisiEnd);
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        //return this.otHabaConverter;
    }

    /**
     *
     */
    public static String GetYotoJpValue(XmlDataModel xmlModel, int teisiSta, int teisiEnd) {
        try {
            if(xmlModel == null) return null;
            if (xmlModel.isRowAvailable()) {
                Map map = (Map)xmlModel.getRowData();
                if(map == null) return null;
                if(map.get(CommonConst.TEISI_ITEM) == null) {
                    if(map.get("YOTOJP") != null) {
                        //用途名が8文字以上の場合は7文字+…
                        if(map.get("YOTOJP").toString().length() > 8) {
                            return map.get("YOTOJP").toString().substring(0, 7) + "...";
                        } else {
                            return map.get("YOTOJP").toString();
                        }
                    }
                } else {
                    return CommonUtility.ExtractString(map.get(CommonConst.TEISI_ITEM).toString(), teisiSta, teisiEnd);
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        //return this.otHabaConverter;
    }

    /**
     *
     */
    public static String GetPassValue(XmlDataModel xmlModel, int teisiSta, int teisiEnd) {
        try {
            if(xmlModel == null) return null;
            if (xmlModel.isRowAvailable()) {
                Map map = (Map)xmlModel.getRowData();
                if(map == null) return null;
                if(map.get(CommonConst.TEISI_ITEM) == null) {
                    if(map.get("PASS") != null) {
                        return map.get("PASS").toString();
                    }
                } else {
                    return CommonUtility.ExtractString(map.get(CommonConst.TEISI_ITEM).toString(), teisiSta, teisiEnd);
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        //return this.otHabaConverter;
    }

    /**
     *
     */
    public static String GetNextSetsubiValue(XmlDataModel xmlModel, int teisiSta, int teisiEnd) {
        try {
            if(xmlModel == null) return null;
            if (xmlModel.isRowAvailable()) {
                Map map = (Map)xmlModel.getRowData();
                if(map == null) return null;
                if(map.get(CommonConst.TEISI_ITEM) == null) {
                    if(map.get("NEXTSETSUBI") != null) {
                        return map.get("NEXTSETSUBI").toString();
                    }
                } else {
                    return CommonUtility.ExtractString(map.get(CommonConst.TEISI_ITEM).toString(), teisiSta, teisiEnd);
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        //return this.otHabaConverter;
    }

    /**
     * プロパティ xCompeConverter の取得メソッド。
     * @return プロパティ xCompeConverter の値。
     */
    public static String GetXCompeValue(XmlDataModel xmlModel) {
        try {
            if(xmlModel == null) return null;
            if (xmlModel.isRowAvailable()) {
                Map map = (Map)xmlModel.getRowData();
                if(map == null) return null;
                if(map.get(CommonConst.TEISI_ITEM) == null) {
                    DecimalFormat decimalFormat = new DecimalFormat(CommonConst.FORMAT_XCOMPE);
                    return decimalFormat.format(map.get("XCOMPE"));
                } else {
                    return CommonUtility.ExtractString(map.get(CommonConst.TEISI_ITEM).toString(), 27, 29);
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        //return this.xCompeConverter;
    }

    /**
     *
     */
    public static String GetRollValue(XmlDataModel xmlModel, int teisiSta, int teisiEnd) {
        try {
            if(xmlModel == null) return null;
            if (xmlModel.isRowAvailable()) {
                Map map = (Map)xmlModel.getRowData();
                if(map == null) return null;
                if(map.get(CommonConst.TEISI_ITEM) == null) {
                    if(map.get("ROLL") != null) {
                        return map.get("ROLL").toString();
                    }
                } else {
                    return CommonUtility.ExtractString(map.get(CommonConst.TEISI_ITEM).toString(), teisiSta, teisiEnd);
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        //return this.otHabaConverter;
    }

    /**
     * プロパティ trkStsConverter の取得メソッド。
     * @return プロパティ trkStsConverter の値。
     */
    public static String GetTrkStsValue(XmlDataModel xmlModel) {
        try {
            if(xmlModel == null) return null;
            if (xmlModel.isRowAvailable()) {
                Map map = (Map)xmlModel.getRowData();
                if(map == null) return null;
                switch (Double.valueOf(map.get("TRKSTS").toString()).intValue()) {
                    case CommonConst.TRKSTS_KAISI_CODE:
                        return CommonUtility.GetStrFormat((Date)map.get("STATIME"), "HH:mm");
                    case CommonConst.TRKSTS_IPPAN_CODE:
                        switch (Integer.parseInt(map.get("DATAKBN").toString(), 10)) {
                            case CommonConst.DATAKBN_TEISI_CODE:
                                return GetStopTimeString(map.get(CommonConst.STOPTIME_ITEM).toString());
                            default:
                                return null;
                        }
                    default:
                        return map.get("TRKSTSMOJI").toString();
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        //return this.trkStsConverter;
    }

    /**
     * スケジュール一覧で設備停止がチェックされているか
     */
    public static boolean IsSelCheckTeisi(XmlDataModel xmlModel) {
        try {
            if(xmlModel == null) return false;
            msg = null;
            int rowCount = xmlModel.getRowCount();
            XmlData xmldata = (XmlData)xmlModel.getWrappedData();
            for (int i=0; i<rowCount; i++) {
                if (xmldata.getRowDataValue(i, "SELECTED").equals("1")) {
                    //選択パス内で開始されているパスがある場合は変更不可
                    if (Integer.parseInt(xmldata.getRowDataValue(i, "DATAKBN").toString(), 10) == CommonConst.DATAKBN_TEISI_CODE) {
                        msg = "設備停止は指定できません。";
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * プロパティ teisiRow の取得メソッド。
     * @return プロパティ teisiRow の値。
     */
    public static boolean IsTeisiRow(XmlDataModel xmlModel) {
        try {
            if(xmlModel == null) return false;
            if (xmlModel.isRowAvailable()) {
                Map map = (Map)xmlModel.getRowData();
                if(map == null) return false;
                if(map.get(CommonConst.TEISI_ITEM) != null) return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        //return this.teisiRow;
    }

    public static String GetStopTimeString(String stopTime) {
        BigDecimal stoptime = new BigDecimal(stopTime);
        BigDecimal oneDay = new BigDecimal("86400.0");
        BigDecimal result = stoptime.divide(oneDay, 1, BigDecimal.ROUND_HALF_UP);
        if(result.doubleValue() >= 1.0) {
            DecimalFormat decimalFormat = new DecimalFormat(CommonConst.FORMAT_STOPTIME_DAY);
            return decimalFormat.format(result.doubleValue()) + "日";
        } else {
            BigDecimal oneHour = new BigDecimal("60.0");
            result = stoptime.divide(oneHour, 0, BigDecimal.ROUND_HALF_UP);
            if(result.equals(new BigDecimal("0.0"))) {
                return null;
            } else {
                DecimalFormat decimalFormat = new DecimalFormat(CommonConst.FORMAT_STOPTIME_MIN);
                return decimalFormat.format(result.doubleValue()) + "分";
            }
        }
    }

    /**
     * テータ表の列値の加工
     * @param   xmlModel          データ表の値（XmlDataModel）
     * @param   cellName          列名（大文字指定）
     * @param   outputformat      出力フォーマット
     * @param   beingCheckName    存在チェック項目
     * @param   staIndex          存在チェック項目の文字抽出の開始位置
     * @param   endIndex          存在チェック項目の文字抽出の終了位置
     * @return  列値を加工した値（String型）
     * @throws
     * @see
     **/
    public static String GetCellValue(XmlDataModel xmlModel, String cellName) { return GetCellValue(xmlModel, cellName, null, null, 0, 0);};
    public static String GetCellValue(XmlDataModel xmlModel, String cellName, String outputformat) { return GetCellValue(xmlModel, cellName, outputformat, null, 0, 0);};
    public static String GetCellValue(XmlDataModel xmlModel, String cellName, String outputformat, String beingCheckName, int staIndex, int endIndex) {
        try {
            if(xmlModel == null) return null;
            if (xmlModel.isRowAvailable()) {
                Map map = (Map)xmlModel.getRowData();
                if(map == null) return null;
                if(map.get(beingCheckName) == null) {
                    if(map.get(cellName.toUpperCase()) instanceof String) {
                        return map.get(cellName.toUpperCase()).toString();
                    } else if(map.get(cellName.toUpperCase()) instanceof Double
                            || map.get(cellName.toUpperCase()) instanceof Float
                            || map.get(cellName.toUpperCase()) instanceof Integer) {
                        if(outputformat == null) {
                            return map.get(cellName.toUpperCase()).toString();
                        } else {
                            DecimalFormat decimalFormat = new DecimalFormat(outputformat);
                            return decimalFormat.format(map.get(cellName.toUpperCase()));
                        }
                    } else if(map.get(cellName.toUpperCase()) instanceof Date) {
                        SimpleDateFormat df;
                        if(outputformat == null) {
                            df = new SimpleDateFormat("M/dd HH:mm");
                        } else {
                            df = new SimpleDateFormat(outputformat);
                        }
                        return df.format(map.get(cellName.toUpperCase()));
                    }
                } else {
                    return CommonUtility.ExtractString(map.get(beingCheckName).toString(), staIndex, endIndex);
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        //return this.inAtsuConverter;
    }

}