package ngyshanai.domain.logic.hososhiyosho.test;





/*
 * PassOrderList.java
 *
 * Created on 2006/10/09, 13:48
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */


import java.util.*;
import jp.co.nikkeikin.nis.nagoya.common.*;
import java.io.Serializable;

/**
 * PassOrderListは、PassOrderクラスに関する操作を行います
 * @author Kazuya-Isomura
 */
public class PassOrderList implements Serializable{
    
    /** インスタンスを生成する */
    public PassOrderList() {
    }
    
    /**
     * プロパティ passOrders の値を保持。
     */
    private List passOrders = new ArrayList();
    
    /**
     * プロパティ passOrders の取得メソッド。
     * @return プロパティ passOrders の値。
     */
    public List getPassOrders(){
        return this.passOrders;
    }
    
    /**
     * プロパティ passOrders の設定メソッド。
     * @param passOrders プロパティ passOrders の新しい値。
     */
    public void setPassOrders(List passOrders){
        this.passOrders = passOrders;
    }
    
    /**
     * xmlDataModelに格納されたXMLデータからpassOrdersプロパティを作成します
     * @param xmlDataModel 作成元のXMLデータオブジェクト
     */
    public void makeEditData(XmlDataModel xmlDataModel){
        if (!xmlDataModel.isRowAvailable()) return;
        XmlData xmldata = (XmlData)xmlDataModel.getWrappedData();
        String wKey = xmldata.getCellString(0, "SETLINKKEY");
        int index = 0;
        Date dt;
        java.util.Calendar cal = java.util.Calendar.getInstance();
        PassOrder passOrder = new PassOrder();
        passOrder.clearPassOrder();
        passOrder.clearPassNo();
        passOrder.clearEndDate();
        for (int idx=0; idx < xmlDataModel.getRowCount(); idx++){
            if (!xmldata.getRowDataValue(idx, "SETLINKKEY").toString().equals(wKey)){
                passOrder.setSetLinkKey(xmldata.getCellString(idx-1, "SETLINKKEY"));
                passOrder.setLtNo(xmldata.getCellString(idx-1, "LTNO"));
                passOrder.setLta(xmldata.getCellString(idx-1, "LTA"));
                passOrder.setGenAtsu(new Double(xmldata.getCellString(idx-1, "GENATSU")).doubleValue());
                passOrder.setDstAtsu(new Double(xmldata.getCellString(idx-1, "DSTATSU")).doubleValue());
                passOrder.setDstHaba(new Double(xmldata.getCellString(idx-1, "DSTHABA")).doubleValue());
                passOrder.setNextSetsubi(xmldata.getCellString(idx-1, "NEXTSETSUBI"));
                passOrder.setPassCount(new Double(xmldata.getCellString(idx-1, "PASSCOUNT")).intValue());
                passOrders.add(passOrder);
                passOrder = new PassOrder();
                wKey = xmldata.getCellString(idx, "SETLINKKEY");
                index = 0;
                passOrder.clearPassOrder();
                passOrder.clearPassNo();
                passOrder.clearEndDate();
            }
            passOrder.setPassOrderByIndex(index, new Double(xmldata.getCellString(idx, "PASSORDER")).intValue());
            passOrder.setPassNoByIndex(index, new Double(xmldata.getCellString(idx, "PASSNO")).doubleValue());
            dt = (Date)xmldata.getRowDataValue(idx, "ENDDATE");
            if (dt != null){
                cal.setTime(dt);
                passOrder.setEndDateByIndex(index, cal.getTime());
            }
            index++;
        }
        passOrder.setSetLinkKey(xmldata.getCellString(xmlDataModel.getRowCount()-1, "SETLINKKEY"));
        passOrder.setLtNo(xmldata.getCellString(xmlDataModel.getRowCount()-1, "LTNO"));
        passOrder.setLta(xmldata.getCellString(xmlDataModel.getRowCount()-1, "LTA"));
        passOrder.setGenAtsu(new Double(xmldata.getCellString(xmlDataModel.getRowCount()-1, "GENATSU")).doubleValue());
        passOrder.setDstAtsu(new Double(xmldata.getCellString(xmlDataModel.getRowCount()-1, "DSTATSU")).doubleValue());
        passOrder.setDstHaba(new Double(xmldata.getCellString(xmlDataModel.getRowCount()-1, "DSTHABA")).doubleValue());
        passOrder.setNextSetsubi(xmldata.getCellString(xmlDataModel.getRowCount()-1, "NEXTSETSUBI"));
        passOrder.setPassCount(new Double(xmldata.getCellString(xmlDataModel.getRowCount()-1, "PASSCOUNT")).intValue());
        passOrders.add(passOrder);
    }
    
    /**
     * passOrdersプロパティからXMLデータモデルにデータを引き渡します
     * @param xmlDataModel 作成元のXMLデータオブジェクト
     * @return 正常ならxmlDataModel、データ無しならnull。
     */
    public XmlDataModel makeXMLData(XmlDataModel xmlDataModel){
        if (!xmlDataModel.isRowAvailable()) return null;
        XmlDataModel xmldatamodel = new XmlDataModel();
        XmlData xmldata = (XmlData)xmlDataModel.getWrappedData();
        String wSetLinkKey, wSetLinkKey2;
        double wPassNo, wPassNo2;
        // 全件回す
        for(int rowIndex = 0; rowIndex < xmldata.getRowCount(); rowIndex++){
            wSetLinkKey = xmldata.getCellString(rowIndex, "SETLINKKEY");
            wPassNo = new Double(xmldata.getCellString(rowIndex, "PASSNO")).doubleValue();
            for(int odx1=0; odx1<passOrders.size(); odx1++){
                wSetLinkKey2 = ((PassOrder)passOrders.get(odx1)).getSetLinkKey();
                if(wSetLinkKey2.equals(wSetLinkKey)){
                    for(int rdx1=0; rdx1<PassOrder.PASS_ORDER_MAX; rdx1++){
                        wPassNo2 = ((PassOrder)passOrders.get(odx1)).getPassNoByIndex(rdx1);
                        if(wPassNo2 == wPassNo){
                            xmldata.setCellString(rowIndex, "PASSORDER", new Double(((PassOrder)passOrders.get(odx1)).getPassOrderByIndex(rdx1)).toString());
                            break;
                        }
                    }
                }
            }
        }
        xmldatamodel.setWrappedData(xmldata);
        return xmldatamodel;
    }
    
    /**
     * passOrdersの全Listに対する passOrderプロパティ内の同じ値を検査する
     * @return 同じ値がある場合はその値。無ければ-1
     */
    public int checkSamePassOrder(){
        for(int odx1=0; odx1<passOrders.size(); odx1++){
            for(int rdx1=0; rdx1<PassOrder.PASS_ORDER_MAX; rdx1++){
                for(int odx2=0; odx2<passOrders.size(); odx2++){
                    for(int rdx2=0; rdx2<PassOrder.PASS_ORDER_MAX; rdx2++){
                        if((odx1==odx2) && (rdx1==rdx2)) continue;
                        if((((PassOrder)passOrders.get(odx1)).getPassOrderByIndex(rdx1) == 0) || (((PassOrder)passOrders.get(odx2)).getPassOrderByIndex(rdx2) == 0)) continue;
                        if(((PassOrder)passOrders.get(odx1)).getPassOrderByIndex(rdx1) == ((PassOrder)passOrders.get(odx2)).getPassOrderByIndex(rdx2)){
                            return ((PassOrder)passOrders.get(odx1)).getPassOrderByIndex(rdx1);
                        }
                    }
                }
            }
        }
        return -1;
    }
    
    /**
     * passOrdersの全Listに対する passOrderプロパティ内格納順序が昇順かを検査する
     * @return 昇順なら-1, 違っていればListのindex
     */
    public int checkPassOrderAscending(){
        int wPassOrder1, wPassOrder2;
        for(int odx1=0; odx1<passOrders.size(); odx1++){
            for(int rdx1=0; rdx1<PassOrder.PASS_ORDER_MAX; rdx1++){
                wPassOrder1 = ((PassOrder)passOrders.get(odx1)).getPassOrderByIndex(rdx1);
                if(wPassOrder1 == 0) continue;
                for(int rdx2=rdx1; rdx2<PassOrder.PASS_ORDER_MAX; rdx2++){
                    if(rdx1==rdx2) continue;
                    wPassOrder2 = ((PassOrder)passOrders.get(odx1)).getPassOrderByIndex(rdx2);
                    if(wPassOrder2 == 0) continue;
                    if(wPassOrder1 > wPassOrder2) return odx1;
                }
            }
        }
        return -1;
    }
    
    /**
     * passOrdersの全Listに対する passOrderプロパティ内格納番号が1～全て揃っているか検査する
     * @return 正常なら0, 番号が抜けていればその番号
     */
    public int checkUnknownPassOrder(){
        int wMaxPassOrder = 0;
        int wPassOrder;
        int wSeq = 1;
        boolean isHit;
        
        // まずPassOrderの最大値を見つける
        for(int odx1=0; odx1<passOrders.size(); odx1++){
            for(int rdx1=0; rdx1<PassOrder.PASS_ORDER_MAX; rdx1++){
                wPassOrder = ((PassOrder)passOrders.get(odx1)).getPassOrderByIndex(rdx1);
                if(wMaxPassOrder < wPassOrder) wMaxPassOrder = wPassOrder;
            }
        }
        // 飛び番が無いか探す
        while(wSeq <= wMaxPassOrder){
            isHit = false;
            for(int odx1=0; odx1<passOrders.size(); odx1++){
                for(int rdx1=0; rdx1<PassOrder.PASS_ORDER_MAX; rdx1++){
                    wPassOrder = ((PassOrder)passOrders.get(odx1)).getPassOrderByIndex(rdx1);
                    if(wSeq == wPassOrder){
                        isHit = true;
                        break;
                    }
                }
                if(isHit) break;
            }
            if(!isHit) return wSeq;
            wSeq++;
        }
        return 0;
    }
}
