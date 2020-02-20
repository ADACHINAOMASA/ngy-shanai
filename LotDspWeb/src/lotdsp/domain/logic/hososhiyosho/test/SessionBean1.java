package lotdsp.domain.logic.hososhiyosho.test;





import java.util.Date;
import java.util.Map;

import jp.co.nikkeikin.nis.nagoya.common.XmlDataModel;    

public class SessionBean1  {
    
    private org.tempuri.ISchApp01SOAPProxy ischapp01soap = null;
    
    //private org.tempuri.IIcasApp01SOAPProxy iicasapp01soap = null;
    
    javax.xml.rpc.holders.StringHolder userID = new javax.xml.rpc.holders.StringHolder();
    javax.xml.rpc.holders.StringHolder termID = new javax.xml.rpc.holders.StringHolder();
    javax.xml.rpc.holders.StringHolder setsubiName = new javax.xml.rpc.holders.StringHolder();
    javax.xml.rpc.holders.StringHolder useKbn = new javax.xml.rpc.holders.StringHolder();
    javax.xml.rpc.holders.IntHolder termKbn = new javax.xml.rpc.holders.IntHolder();
    
    /** 
     * <p>新しいセッションデータ Bean インスタンスを作成します。</p>
     */
    public SessionBean1() {
       
    	/*
        FacesContext ctxSch = FacesContext.getCurrentInstance();
        FacesContext ctxIcas = FacesContext.getCurrentInstance();        
        ExternalContext etxSch = ctxSch.getExternalContext();
        ExternalContext etxIcas = ctxIcas.getExternalContext();
        
        String schTarget = etxSch.getInitParameter("jp.co.nikkeikin.nis.nagoya.schsoapserver");
        String icasTarget = etxIcas.getInitParameter("jp.co.nikkeikin.nis.nagoya.icassoapserver");
        ischapp01soap = new org.tempuri.ISchApp01SOAPProxy(schTarget);
        iicasapp01soap = new org.tempuri.IIcasApp01SOAPProxy(icasTarget);

        //web.xmlのコンテキストパタメータからSchListAuto4CMの自動更新間隔（単位：msec）を取得
        FacesContext ctxUpd = FacesContext.getCurrentInstance();
        ExternalContext etxUpd = ctxUpd.getExternalContext();
        //int updateIntervalTarget = Integer.parseInt(etxUpd.getInitParameter("updateInterval"));
        //if(updateIntervalTarget != 0) updateInterval = updateIntervalTarget;

        // セッションオブジェクトの取得
        HttpSession hs = (javax.servlet.http.HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        // sessionIDの取得
        sessionId = hs.getId();
    */
    	
    	//return termID.value + "," + userID.value + "," + useKbn.value;
    	
    	//termID.value="NLM-FMV59010";
    	//userID.value="SHINICHI-WATANABE";
    	
    	/*
    	termID.value="NLM-FMV59010";
    	userID.value="DINESH-SHAKYA";
    	useKbn.value="2";
    	*/
    	
        
    	 //String schTarget = "http://nlmfangydev3:10080/";
    	 String schTarget = "http://nlmfangydev3:10080/soap/SchApp01SOAP.dll/soap/ISchApp01SOAP";
    	 
         //String icasTarget = "http://nlmfangydev3:10080/";
    	 //http://nlmfangydev3:10080/soap/IcasApp01SOAP.dll/soap/IIcasApp01SOAP
    		 
         ischapp01soap = new org.tempuri.ISchApp01SOAPProxy(schTarget);
         
         //iicasapp01soap = new org.tempuri.IIcasApp01SOAPProxy(icasTarget);

    }



    /** 
     * <p>このメソッドは、それを含むセッションが非活性化されようとしているときに
     * 呼び出されます。一般には、これは、分散サーブレットコンテナ内で、セッションが別の
     * コンテナインスタンスに転送されようとしているときに発生します。
     * この転送後は、転送が完了したことを示すために、<code>activate()</code> メソッド
     * が呼び出されます。</p>
     * 
     * <p>このメソッドをカスタマイズして、セッションだけではシリアライズできない
     * セッションデータまたはリソースに対する参照を解放します。</p>
     */
    public void passivate() {
    }

    /** 
     * <p>このメソッドは、それを含むセッションが再度アクティブにされたときに
     * 呼び出されます。</p>
     * 
     * <p>このメソッドをカスタマイズして、セッションだけではシリアライズできなかった
     * セッションデータまたはリソースに対する参照を再取得します。</p>
     */
    public void activate() {
    }

    /** 
     * <p>このメソッドは、この Bean がセッションスコープから削除されたときに
     * 呼び出されます。一般には、これは、セッションがタイムアウトするか、
     * アプリケーションによってセッションが終了されることで発生します。</p>
     * 
     * <p>このメソッドをカスタマイズして、<code>init()</code> メソッドの実行中、
     * あるいは後からアプリケーションの生存中に、割り当てられたリソースを
     * クリーンアップします。</p>
     */
    public void destroy() {
    }

    /**
     * ISchApp01SOAP関数
     * WNC06045 2007.11.23 追加/修正
     */
    public int getUseStatus(java.lang.String authInf, java.lang.String setsubi, javax.xml.rpc.holders.IntHolder enableEndSign, javax.xml.rpc.holders.IntHolder autoRefresh, javax.xml.rpc.holders.StringHolder lastUpdate, javax.xml.rpc.holders.IntHolder manualResult) throws java.rmi.RemoteException{
        return ischapp01soap.getUseStatus(authInf, setsubi, enableEndSign, autoRefresh, lastUpdate, manualResult);
    }
    
    public int setUseStatus(java.lang.String authInf, java.lang.String setsubi, int enableEndSign, int autoRefresh) throws java.rmi.RemoteException{
        return ischapp01soap.setUseStatus(authInf, setsubi, enableEndSign, autoRefresh);
    }

    public int autoTeisi4CM(java.lang.String authInf, java.lang.String setsubi) throws java.rmi.RemoteException{
        return ischapp01soap.autoTeisi4CM(authInf, setsubi);
    }

    public int makeSchSplit4CM(java.lang.String authInf, java.lang.String setsubi, int splitNo, java.lang.String cycleKey, java.lang.String lotList) throws java.rmi.RemoteException{
      return ischapp01soap.makeSchSplit4CM(authInf, setsubi, splitNo, cycleKey, lotList);
    }
    
    public int getTeisi(java.lang.String authInf, java.lang.String setsubi, javax.xml.rpc.holders.StringHolder teisiList) throws java.rmi.RemoteException{
      return ischapp01soap.getTeisi(authInf, setsubi, teisiList);
    }

    public int setTeisi(java.lang.String authInf, java.lang.String setsubi, float insLot, float insPass, float insDisp, java.lang.String cycleKey, java.lang.String teisiCd, int teisiTime) throws java.rmi.RemoteException{
      return ischapp01soap.setTeisi(authInf, setsubi, insLot, insPass, insDisp, cycleKey, teisiCd, teisiTime);
    }

    public int getCycleData(java.lang.String authInf, java.lang.String setsubi, javax.xml.rpc.holders.StringHolder cycleList) throws java.rmi.RemoteException{
      return ischapp01soap.getCycleData(authInf, setsubi, cycleList);
    }

    public int getCycleLotData(java.lang.String authInf, java.lang.String cycleKey, javax.xml.rpc.holders.StringHolder lotList) throws java.rmi.RemoteException{
      return ischapp01soap.getCycleLotData(authInf, cycleKey, lotList);
    }

    public int cycleMove(java.lang.String authInf, java.lang.String setsubi, float insNo, java.lang.String cycleList) throws java.rmi.RemoteException{
      return ischapp01soap.cycleMove(authInf, setsubi, insNo, cycleList);
    }

    public int cycleDelete(java.lang.String authInf, java.lang.String setsubi, java.lang.String cycleList) throws java.rmi.RemoteException{
      return ischapp01soap.cycleDelete(authInf, setsubi, cycleList);
    }   
    
    public int getLotDetail(java.lang.String authInf, java.lang.String setLinkKey, float passNo, javax.xml.rpc.holders.StringHolder lotData) throws java.rmi.RemoteException{
        return ischapp01soap.getLotDetail(authInf, setLinkKey, passNo, lotData);
    }
    
public int getAuthInf(java.lang.String IPAddress, javax.xml.rpc.holders.StringHolder userID, javax.xml.rpc.holders.StringHolder termID, javax.xml.rpc.holders.StringHolder setsubi, javax.xml.rpc.holders.StringHolder useKbn, javax.xml.rpc.holders.IntHolder termKbn) throws java.rmi.RemoteException{
  return ischapp01soap.getAuthInf(IPAddress, userID, termID, setsubi, useKbn, termKbn);
}
    
    public void setSchListData(XmlDataModel schListData)  {
        this.schListData = schListData;
    }

    public int getAutoCheck(java.lang.String authInf, java.lang.String setsubi, javax.xml.rpc.holders.IntHolder enableState) throws java.rmi.RemoteException{
        return ischapp01soap.getAutoCheck(authInf, setsubi, enableState);
    }
    
    public int getSchData4CM(java.lang.String authInf, java.lang.String setsubi, javax.xml.rpc.holders.StringHolder schList) throws java.rmi.RemoteException{
        return ischapp01soap.getSchData4CM(authInf, setsubi, schList);
    }
    
    public int getLotData4CM(java.lang.String authInf, java.lang.String setsubi, java.lang.String cycleKey, javax.xml.rpc.holders.StringHolder schList) throws java.rmi.RemoteException{
      return ischapp01soap.getLotData4CM(authInf, setsubi, cycleKey, schList);
    }

    public int setKaeriData4CM(java.lang.String authInf, java.lang.String setsubi, float inAtsu, float otAtsu, float haba, float XCompe, int rollTime, float insLot, float insPass, float dispPass, java.lang.String cycleKey) throws java.rmi.RemoteException{
      return ischapp01soap.setKaeriData4CM(authInf, setsubi, inAtsu, otAtsu, haba, XCompe, rollTime, insLot, insPass, dispPass, cycleKey);
    }

    public int getTenkenData4CM(java.lang.String authInf, java.lang.String setsubi, javax.xml.rpc.holders.StringHolder tenkenList) throws java.rmi.RemoteException{
      return ischapp01soap.getTenkenData4CM(authInf, setsubi, tenkenList);
    }

    public int getPassModData4CM(java.lang.String authInf, java.lang.String lotData, javax.xml.rpc.holders.StringHolder passModList, javax.xml.rpc.holders.IntHolder isCanModify, javax.xml.rpc.holders.IntHolder passCount, javax.xml.rpc.holders.IntHolder passOut, javax.xml.rpc.holders.IntHolder enablePassCount, javax.xml.rpc.holders.IntHolder enablePassOut) throws java.rmi.RemoteException{
      return ischapp01soap.getPassModData4CM(authInf, lotData, passModList, isCanModify, passCount, passOut, enablePassCount, enablePassOut);
    }

    public int setPassModData4CM(java.lang.String authInf, java.lang.String setsubi, java.lang.String lotData, java.lang.String passModList, int passCount, int passOut) throws java.rmi.RemoteException{
      return ischapp01soap.setPassModData4CM(authInf, setsubi, lotData, passModList, passCount, passOut);
    }

    public int modPassCount4CM(java.lang.String authInf, java.lang.String lotData, java.lang.String passModList, int beforePassCount, int afterPassCount, javax.xml.rpc.holders.StringHolder afterPassModList, javax.xml.rpc.holders.IntHolder passCount, javax.xml.rpc.holders.IntHolder passOut, javax.xml.rpc.holders.IntHolder enablePassCount, javax.xml.rpc.holders.IntHolder enablePassOut) throws java.rmi.RemoteException{
      return ischapp01soap.modPassCount4CM(authInf, lotData, passModList, beforePassCount, afterPassCount, afterPassModList, passCount, passOut, enablePassCount, enablePassOut);
    }

    public int modPassOut4CM(java.lang.String authInf, java.lang.String passModList, int afterPassOut, javax.xml.rpc.holders.StringHolder afterPassModList, javax.xml.rpc.holders.IntHolder passOut, javax.xml.rpc.holders.IntHolder enablePassOut) throws java.rmi.RemoteException{
      return ischapp01soap.modPassOut4CM(authInf, passModList, afterPassOut, afterPassModList, passOut, enablePassOut);
    }

    public int schDelete(java.lang.String authInf, java.lang.String setsubi, java.lang.String delData) throws java.rmi.RemoteException{
      return ischapp01soap.schDelete(authInf, setsubi, delData);
    }

    public int getHinData(java.lang.String authInf, java.lang.String opnLinkKey, javax.xml.rpc.holders.StringHolder hinList) throws java.rmi.RemoteException{
      return ischapp01soap.getHinData(authInf, opnLinkKey, hinList);
    }

    public int lotCancel(java.lang.String authInf, java.lang.String cycleKey) throws java.rmi.RemoteException{
      return ischapp01soap.lotCancel(authInf, cycleKey);
    }

    public int lotDelete(java.lang.String authInf, java.lang.String delData) throws java.rmi.RemoteException{
      return ischapp01soap.lotDelete(authInf, delData);
    }

    public int makeSch4CM(java.lang.String authInf, java.lang.String setsubi, java.lang.String cycleKey) throws java.rmi.RemoteException{
      return ischapp01soap.makeSch4CM(authInf, setsubi, cycleKey);
    }

    public int lotMove(java.lang.String authInf, java.lang.String setsubi, java.lang.String cycleKey, java.lang.String moveData, float insNo) throws java.rmi.RemoteException{
      return ischapp01soap.lotMove(authInf, setsubi, cycleKey, moveData, insNo);
    }

    public int lotInsCheck(java.lang.String authInf, java.lang.String ltNo, java.lang.String setsubi, javax.xml.rpc.holders.StringHolder lotData) throws java.rmi.RemoteException{
      return ischapp01soap.lotInsCheck(authInf, ltNo, setsubi, lotData);
    }

    public int lotInsert(java.lang.String authInf, java.lang.String setsubi, java.lang.String cycleKey, java.lang.String insData, float insNo) throws java.rmi.RemoteException{
      return ischapp01soap.lotInsert(authInf, setsubi, cycleKey, insData, insNo);
    }

    public int sendSch4CM3(java.lang.String authInf) throws java.rmi.RemoteException{
      return ischapp01soap.sendSch4CM3(authInf);
    }

    public int setTenkenData4CM(java.lang.String authInf, java.lang.String setsubi, float insLot, float insPass, float dispPass, java.lang.String cycleKey, java.lang.String tenkenList) throws java.rmi.RemoteException{
      return ischapp01soap.setTenkenData4CM(authInf, setsubi, insLot, insPass, dispPass,cycleKey, tenkenList);
    }

    public int schMove(java.lang.String authInf, java.lang.String setsubi, float insLot, float insPass, float dispPass, java.lang.String cycleKey, java.lang.String moveList, int insKbn) throws java.rmi.RemoteException{
      return ischapp01soap.schMove(authInf, setsubi, insLot, insPass, dispPass, cycleKey, moveList, insKbn);
    }

    public int CM_RefreshPassCompe(java.lang.String authInf, java.lang.String setsubi, javax.xml.rpc.holders.StringHolder passModList) throws java.rmi.RemoteException{
      return ischapp01soap.CM_RefreshPassCompe(authInf, setsubi, passModList);
    }  
    
    public int CM_ModifyPassData(java.lang.String authInf, java.lang.String setsubi, java.lang.String beforePassData, java.lang.String afterPassData) throws java.rmi.RemoteException{
      return ischapp01soap.CM_ModifyPassData(authInf, setsubi, beforePassData, afterPassData);
    }  

//    public int lotInfCheck(java.lang.String authInf, java.lang.String setsubi, javax.xml.rpc.holders.StringHolder cycleKey, javax.xml.rpc.holders.IntHolder isLotEdit) throws java.rmi.RemoteException{
//      return ischapp01soap.lotInfCheck(authInf, setsubi, cycleKey, isLotEdit);
//    }
    
    public int lotInfChecks(java.lang.String authInf, java.lang.String selData, javax.xml.rpc.holders.IntHolder isEditing) throws java.rmi.RemoteException{
      return ischapp01soap.lotInfChecks(authInf, selData, isEditing);
    }
    
    public int passInsCheck(java.lang.String authInf, java.lang.String ltNo, java.lang.String setsubi, javax.xml.rpc.holders.StringHolder passData) throws java.rmi.RemoteException{
      return ischapp01soap.passInsCheck(authInf, ltNo, setsubi, passData);
    }
    
    public int passInsert(java.lang.String authInf, java.lang.String setsubi, float insLot, float insPass, float dispPass, java.lang.String cycleKey, java.lang.String insList, int insKbn) throws java.rmi.RemoteException{
      return ischapp01soap.passInsert(authInf, setsubi, insLot, insPass, dispPass, cycleKey, insList, insKbn);
    }
    public int getPassOrderData(java.lang.String authInf, java.lang.String setsubi, java.lang.String cycleKey, javax.xml.rpc.holders.StringHolder lotList) throws java.rmi.RemoteException{
      return ischapp01soap.getPassOrderData(authInf, setsubi, cycleKey, lotList);
    }
    
    public int cancelPassOrderData(java.lang.String authInf, java.lang.String cycleKey) throws java.rmi.RemoteException{
      return ischapp01soap.cancelPassOrderData(authInf, cycleKey);
    }
    
    public int modifyPassOrderData(java.lang.String authInf, java.lang.String setsubi, java.lang.String cycleKey, java.lang.String lotList) throws java.rmi.RemoteException{
      return ischapp01soap.modifyPassOrderData(authInf, setsubi, cycleKey, lotList);
    }
    
//    /**
//     * IIcasApp01SOAP関数
//     * WNC06045 2007.11.23 追加/修正
//     */
//    public int setKaishi4CM(java.lang.String authInf, java.lang.String setsubi) throws java.rmi.RemoteException{
//      return iicasapp01soap.setKaishi4CM(authInf, setsubi);
//    }
//
//    public int getLotJisseki4CM(java.lang.String authInf, java.lang.String setsubi, javax.xml.rpc.holders.StringHolder jskData) throws java.rmi.RemoteException{
//      return iicasapp01soap.getLotJisseki4CM(authInf, setsubi, jskData);
//    }
//
//    public int setLotJisseki4CM(java.lang.String authInf, java.lang.String setsubi, java.lang.String jskData) throws java.rmi.RemoteException{
//        return iicasapp01soap.setLotJisseki4CM(authInf, setsubi, jskData);
//    }  
//    
//    public int teisiContinue(java.lang.String authInf, java.lang.String setsubi) throws java.rmi.RemoteException{
//      return iicasapp01soap.teisiContinue(authInf, setsubi);
//    }
//
//    public int getJissekiKind4CM(java.lang.String authInf, java.lang.String setsubi, javax.xml.rpc.holders.IntHolder jskKind) throws java.rmi.RemoteException{
//      return iicasapp01soap.getJissekiKind4CM(authInf, setsubi, jskKind);
//    }
//
//    public int getTeisiJisseki4CM(java.lang.String authInf, java.lang.String setsubi, javax.xml.rpc.holders.StringHolder jskData) throws java.rmi.RemoteException{
//      return iicasapp01soap.getTeisiJisseki4CM(authInf, setsubi, jskData);
//    }
//
//    public int setTeisiJisseki4CM(java.lang.String authInf, java.lang.String setsubi, java.lang.String jskData, int contKbn) throws java.rmi.RemoteException{
//      return iicasapp01soap.setTeisiJisseki4CM(authInf, setsubi, jskData, contKbn);
//    }
//
//    public int getOperatorData(java.lang.String authInf, java.lang.String setsubi, java.lang.String mainOp, java.lang.String subOp, javax.xml.rpc.holders.StringHolder mainOpName, javax.xml.rpc.holders.StringHolder subOpName) throws java.rmi.RemoteException{
//      return iicasapp01soap.getOperatorData(authInf, setsubi, mainOp, subOp, mainOpName, subOpName);
//    }
//
//    public int setOperatorData(java.lang.String authInf, java.lang.String setsubi, java.lang.String mainOp, java.lang.String subOp) throws java.rmi.RemoteException{
//      return iicasapp01soap.setOperatorData(authInf, setsubi, mainOp, subOp);
//    }
//
//    public int setRollData(java.lang.String authInf, java.lang.String setsubi, java.lang.String WRUNo, java.lang.String WRDNo, java.lang.String BRUNo, java.lang.String BRDNo) throws java.rmi.RemoteException{
//      return iicasapp01soap.setRollData(authInf, setsubi, WRUNo, WRDNo, BRUNo, BRDNo);
//    }  
    
    /**
     * プロパティ hinKey の値を保持。
     */
    private String hinKey;

    /**
     * プロパティ hinKey の取得メソッド。
     * @return プロパティ hinKey の値。
     */
    public String getHinKey() {

        return this.hinKey;
    }

    /**
     * プロパティ hinKey の設定メソッド。
     * @param hinKey プロパティ hinKey の新しい値。
     */
    public void setHinKey(String hinKey) {

        this.hinKey = hinKey;
    }

    /**
     * プロパティ lotListData の値を保持。
     */
    private XmlDataModel lotListData;

    /**
     * プロパティ lotListData の取得メソッド。
     * @return プロパティ lotListData の値。
     */
    public XmlDataModel getLotListData() {

        return this.lotListData;
    }

    /**
     * プロパティ lotListData の設定メソッド。
     * @param lotListData プロパティ lotListData の新しい値。
     */
    public void setLotListData(XmlDataModel lotListData) {

        this.lotListData = lotListData;
    }

    /**
     * プロパティ selectId の値を保持。
     */
    private String selectId;

    /**
     * プロパティ selectId の取得メソッド。
     * @return プロパティ selectId の値。
     */
    public String getSelectId() {

        return this.selectId;
    }

    /**
     * プロパティ selectId の設定メソッド。
     * @param selectId プロパティ selectId の新しい値。
     */
    public void setSelectId(String selectId) {
        this.selectId = selectId;
    }

    /**
     * プロパティ schListData の値を保持。
     */
    private XmlDataModel schListData;

    /**
     * プロパティ schListMenu4CMData の取得メソッド。
     * @return プロパティ schListMenu4CMData の値。
     */
    public XmlDataModel getSchListData() throws Exception {
        return this.schListData;
    }

    /**
     * プロパティ firstRowIndex の値を保持。
     */
    private int firstRowIndex;

    /**
     * プロパティ firstRowIndex の取得メソッド。
     * @return プロパティ firstRowIndex の値。
     */
    public int getFirstRowIndex() {

        return this.firstRowIndex;
    }

    /**
     * プロパティ firstRowIndex の設定メソッド。
     * @param firstRowIndex プロパティ firstRowIndex の新しい値。
     */
    public void setFirstRowIndex(int firstRowIndex) {

        this.firstRowIndex = firstRowIndex;
    }

    /**
     * プロパティ lotList4CMCycleKey の値を保持。
     */
    private String lotList4CMCycleKey;

    /**
     * プロパティ lotList4CMCycleKey の取得メソッド。
     * @return プロパティ lotList4CMCycleKey の値。
     */
    public String getLotList4CMCycleKey() {

        return this.lotList4CMCycleKey;
    }

    /**
     * プロパティ lotList4CMCycleKey の設定メソッド。
     * @param lotList4CMCycleKey プロパティ lotList4CMCycleKey の新しい値。
     */
    public void setLotList4CMCycleKey(String lotList4CMCycleKey) {

        this.lotList4CMCycleKey = lotList4CMCycleKey;
    }

    /**
     * プロパティ tenkenbanData の値を保持。
     */
    private XmlDataModel tenkenbanData;

    /**
     * プロパティ tenkenbanData の取得メソッド。
     * @return プロパティ tenkenbanData の値。
     */
    public XmlDataModel getTenkenbanData() {

        return this.tenkenbanData;
    }

    /**
     * プロパティ tenkenbanData の設定メソッド。
     * @param tenkenbanData プロパティ tenkenbanData の新しい値。
     */
    public void setTenkenbanData(XmlDataModel tenkenbanData) {

        this.tenkenbanData = tenkenbanData;
    }

    /**
     * プロパティ schList4CMFirstIndex の値を保持。
     */
    private int schList4CMFirstIndex;

    /**
     * プロパティ schList4CMFirstIndex の取得メソッド。
     * @return プロパティ schList4CMFirstIndex の値。
     */
    public int getSchList4CMFirstIndex() {

        return this.schList4CMFirstIndex;
    }

    /**
     * プロパティ schList4CMFirstIndex の設定メソッド。
     * @param schList4CMFirstIndex プロパティ schList4CMFirstIndex の新しい値。
     */
    public void setSchList4CMFirstIndex(int schList4CMFirstIndex) {

        this.schList4CMFirstIndex = schList4CMFirstIndex;
    }

    /**
     * プロパティ lotInfoData の値を保持。
     */
    private XmlDataModel lotInfoData;

    /**
     * プロパティ lotInfoData の取得メソッド。
     * @return プロパティ lotInfoData の値。
     */
    public jp.co.nikkeikin.nis.nagoya.common.XmlDataModel getLotInfoData() {

        return this.lotInfoData;
    }

    /**
     * プロパティ lotInfoData の設定メソッド。
     * @param lotInfoData プロパティ lotInfoData の新しい値。
     */
    public void setLotInfoData(jp.co.nikkeikin.nis.nagoya.common.XmlDataModel lotInfoData) {

        this.lotInfoData = lotInfoData;
    }

    /**
     * プロパティ insLotSchList4CM の値を保持。
     */
    private float insLotSchList4CM;

    /**
     * プロパティ insLotSchList4CM の取得メソッド。
     * @return プロパティ insLotSchList4CM の値。
     */
    public float getInsLotSchList4CM() {

        return this.insLotSchList4CM;
    }

    /**
     * プロパティ insLotSchList4CM の設定メソッド。
     * @param insLotSchList4CM プロパティ insLotSchList4CM の新しい値。
     */
    public void setInsLotSchList4CM(float insLotSchList4CM) {

        this.insLotSchList4CM = insLotSchList4CM;
    }

    /**
     * プロパティ cycleKeySchList4CM の値を保持。
     */
    private String cycleKeySchList4CM;

    /**
     * プロパティ cycleKeySchList4CM の取得メソッド。
     * @return プロパティ cycleKeySchList4CM の値。
     */
    public String getCycleKeySchList4CM() {

        return this.cycleKeySchList4CM;
    }

    /**
     * プロパティ cycleKeySchList4CM の設定メソッド。
     * @param cycleKeySchList4CM プロパティ cycleKeySchList4CM の新しい値。
     */
    public void setCycleKeySchList4CM(String cycleKeySchList4CM) {

        this.cycleKeySchList4CM = cycleKeySchList4CM;
    }

    /**
     * プロパティ insPassSchList4CM の値を保持。
     */
    private float insPassSchList4CM;

    /**
     * プロパティ insPassSchList4CM の取得メソッド。
     * @return プロパティ insPassSchList4CM の値。
     */
    public float getInsPassSchList4CM() {

        return this.insPassSchList4CM;
    }

    /**
     * プロパティ insPassSchList4CM の設定メソッド。
     * @param insPassSchList4CM プロパティ insPassSchList4CM の新しい値。
     */
    public void setInsPassSchList4CM(float insPassSchList4CM) {

        this.insPassSchList4CM = insPassSchList4CM;
    }

    /**
     * プロパティ hinListData の値を保持。
     */
    private XmlDataModel hinListData;

    /**
     * プロパティ hinListData の取得メソッド。
     * @return プロパティ hinListData の値。
     */
    public XmlDataModel getHinListData() {

        return this.hinListData;
    }

    /**
     * プロパティ hinListData の設定メソッド。
     * @param hinListData プロパティ hinListData の新しい値。
     */
    public void setHinListData(XmlDataModel hinListData) {

        this.hinListData = hinListData;
    }

    /**
     * プロパティ hinDispReturnPage の値を保持。
     */
    private String hinDispReturnPage;

    /**
     * プロパティ hinDispReturnPage の取得メソッド。
     * @return プロパティ hinDispReturnPage の値。
     */
    public String getHinDispReturnPage() {

        return this.hinDispReturnPage;
    }

    /**
     * プロパティ hinDispReturnPage の設定メソッド。
     * @param hinDispReturnPage プロパティ hinDispReturnPage の新しい値。
     */
    public void setHinDispReturnPage(String hinDispReturnPage) {

        this.hinDispReturnPage = hinDispReturnPage;
    }

    /**
     * プロパティ openLinkKeySchList4CM の値を保持。
     */
    private String openLinkKeySchList4CM;

    /**
     * プロパティ openLinkKeySchList4CM の取得メソッド。
     * @return プロパティ openLinkKeySchList4CM の値。
     */
    public String getOpenLinkKeySchList4CM() {

        return this.openLinkKeySchList4CM;
    }

    /**
     * プロパティ openLinkKeySchList4CM の設定メソッド。
     * @param openLinkKeySchList4CM プロパティ openLinkKeySchList4CM の新しい値。
     */
    public void setOpenLinkKeySchList4CM(String openLinkKeySchList4CM) {

        this.openLinkKeySchList4CM = openLinkKeySchList4CM;
    }

    /**
     * プロパティ hinDispLtNo の値を保持。
     */
    private String hinDispLtNo;

    /**
     * プロパティ hinDispLtno の取得メソッド。
     * @return プロパティ hinDispLtno の値。
     */
    public String getHinDispLtNo()  {

        return this.hinDispLtNo;
    }

    /**
     * プロパティ hinDispLtno の設定メソッド。
     * @param hinDispLtno プロパティ hinDispLtno の新しい値。
     */
    public void setHinDispLtNo(String hinDispLtNo)  {

        this.hinDispLtNo = hinDispLtNo;
    }

    /**
     * プロパティ tenkenListRowIndex の値を保持。
     */
    private int tenkenListRowIndex;

    /**
     * プロパティ tenkenListRowIndex の取得メソッド。
     * @return プロパティ tenkenListRowIndex の値。
     */
    public int getTenkenListRowIndex() {

        return this.tenkenListRowIndex;
    }

    /**
     * プロパティ tenkenListRowIndex の設定メソッド。
     * @param tenkenListRowIndex プロパティ tenkenListRowIndex の新しい値。
     */
    public void setTenkenListRowIndex(int tenkenListRowIndex) {

        this.tenkenListRowIndex = tenkenListRowIndex;
    }

    /**
     * プロパティ lotMoveListRowIndex の値を保持。
     */
    private int lotMoveListRowIndex;

    /**
     * プロパティ lotMoveListRowIndex の取得メソッド。
     * @return プロパティ lotMoveListRowIndex の値。
     */
    public int getLotMoveListRowIndex() {

        return this.lotMoveListRowIndex;
    }

    /**
     * プロパティ lotMoveListRowIndex の設定メソッド。
     * @param lotMoveListRowIndex プロパティ lotMoveListRowIndex の新しい値。
     */
    public void setLotMoveListRowIndex(int lotMoveListRowIndex) {

        this.lotMoveListRowIndex = lotMoveListRowIndex;
    }

    /**
     * プロパティ passListData の値を保持。
     */
    private XmlDataModel passListData;

    /**
     * プロパティ passListData の取得メソッド。
     * @return プロパティ passListData の値。
     */
    public XmlDataModel getPassListData() {

        return this.passListData;
    }

    /**
     * プロパティ passListData の設定メソッド。
     * @param passListData プロパティ passListData の新しい値。
     */
    public void setPassListData(XmlDataModel passListData) {

        this.passListData = passListData;
    }

    /**
     * プロパティ hinDispListRowIndex の値を保持。
     */
    private int hinDispListRowIndex;

    /**
     * プロパティ hinDispListRowIndex の取得メソッド。
     * @return プロパティ hinDispListRowIndex の値。
     */
    public int getHinDispListRowIndex() {

        return this.hinDispListRowIndex;
    }

    /**
     * プロパティ hinDispListRowIndex の設定メソッド。
     * @param hinDispListRowIndex プロパティ hinDispListRowIndex の新しい値。
     */
    public void setHinDispListRowIndex(int hinDispListRowIndex) {

        this.hinDispListRowIndex = hinDispListRowIndex;
    }

    /**
     * プロパティ hinDispDetail の値を保持。
     */
    private java.util.Map hinDispDetail;

    /**
     * プロパティ hinDispDetail の取得メソッド。
     * @return プロパティ hinDispDetail の値。
     */
    public java.util.Map getHinDispDetail() {

        return this.hinDispDetail;
    }

    /**
     * プロパティ hinDispDetail の設定メソッド。
     * @param hinDispDetail プロパティ hinDispDetail の新しい値。
     */
    public void setHinDispDetail(java.util.Map hinDispDetail) {

        this.hinDispDetail = hinDispDetail;
    }

    /**
     * プロパティ passCount の値を保持。
     */
    private int passCount;

    /**
     * プロパティ passCount の取得メソッド。
     * @return プロパティ passCount の値。
     */
    public int getPassCount() {

        return this.passCount;
    }

    /**
     * プロパティ passCount の設定メソッド。
     * @param passCount プロパティ passCount の新しい値。
     */
    public void setPassCount(int passCount) {

        this.passCount = passCount;
    }

    /**
     * プロパティ passOut の値を保持。
     */
    private int passOut;

    /**
     * プロパティ passOut の取得メソッド。
     * @return プロパティ passOut の値。
     */
    public int getPassOut() {

        return this.passOut;
    }

    /**
     * プロパティ passOut の設定メソッド。
     * @param passOut プロパティ passOut の新しい値。
     */
    public void setPassOut(int passOut) {

        this.passOut = passOut;
    }

    /**
     * プロパティ enablePassCount の値を保持。
     */
    private int enablePassCount;

    /**
     * プロパティ enablePassCount の取得メソッド。
     * @return プロパティ enablePassCount の値。
     */
    public int getEnablePassCount() {

        return this.enablePassCount;
    }

    /**
     * プロパティ enablePassCount の設定メソッド。
     * @param enablePassCount プロパティ enablePassCount の新しい値。
     */
    public void setEnablePassCount(int enablePassCount) {

        this.enablePassCount = enablePassCount;
    }

    /**
     * プロパティ enablePassOut の値を保持。
     */
    private int enablePassOut;

    /**
     * プロパティ enablePassOut の取得メソッド。
     * @return プロパティ enablePassOut の値。
     */
    public int getEnablePassOut() {

        return this.enablePassOut;
    }

    /**
     * プロパティ enablePassOut の設定メソッド。
     * @param enablePassOut プロパティ enablePassOut の新しい値。
     */
    public void setEnablePassOut(int enablePassOut) {

        this.enablePassOut = enablePassOut;
    }

    /**
     * プロパティ lotPasOtAtsuChangeIndex の値を保持。
     */
    private int lotPasOtAtsuChangeIndex;

    /**
     * プロパティ lotPasOtAtsuChangeIndex の取得メソッド。
     * @return プロパティ lotPasOtAtsuChangeIndex の値。
     */
    public int getLotPasOtAtsuChangeIndex() {

        return this.lotPasOtAtsuChangeIndex;
    }

    /**
     * プロパティ lotPasOtAtsuChangeIndex の設定メソッド。
     * @param lotPasOtAtsuChangeIndex プロパティ lotPasOtAtsuChangeIndex の新しい値。
     */
    public void setLotPasOtAtsuChangeIndex(int lotPasOtAtsuChangeIndex) {

        this.lotPasOtAtsuChangeIndex = lotPasOtAtsuChangeIndex;
    }

    /**
     * プロパティ insNoLotList4CM の値を保持。
     */
    private float insNoLotList4CM;

    /**
     * プロパティ insNoLotList4CM の取得メソッド。
     * @return プロパティ insNoLotList4CM の値。
     */
    public float getInsNoLotList4CM() {

        return this.insNoLotList4CM;
    }

    /**
     * プロパティ insNoLotList4CM の設定メソッド。
     * @param insNoLotList4CM プロパティ insNoLotList4CM の新しい値。
     */
    public void setInsNoLotList4CM(float insNoLotList4CM) {

        this.insNoLotList4CM = insNoLotList4CM;
    }

    /**
     * プロパティ lotInsertUpDown の値を保持。
     */
    private String lotInsertUpDown;

    /**
     * プロパティ lotInsertUpDown の取得メソッド。
     * @return プロパティ lotInsertUpDown の値。
     */
    public String getLotInsertUpDown() {

        return this.lotInsertUpDown;
    }

    /**
     * プロパティ lotInsertUpDown の設定メソッド。
     * @param lotInsertUpDown プロパティ lotInsertUpDown の新しい値。
     */
    public void setLotInsertUpDown(String lotInsertUpDown) {

        this.lotInsertUpDown = lotInsertUpDown;
    }

    /**
     * プロパティ jskInputData の値を保持。
     */
    private Map jskInputData;

    /**
     * プロパティ jskInputData の取得メソッド。
     * @return プロパティ jskInputData の値。
     */
    public Map getJskInputData() {

        return this.jskInputData;
    }

    /**
     * プロパティ splitNoLotList4CM の値を保持。
     */
    private int splitNoLotList4CM;

    /**
     * プロパティ splitNoLotList4CM の取得メソッド。
     * @return プロパティ splitNoLotList4CM の値。
     */
    public int getSplitNoLotList4CM() {

        return this.splitNoLotList4CM;
    }

    /**
     * プロパティ splitNoLotList4CM の設定メソッド。
     * @param splitNoLotList4CM プロパティ splitNoLotList4CM の新しい値。
     */
    public void setSplitNoLotList4CM(int splitNoLotList4CM) {

        this.splitNoLotList4CM = splitNoLotList4CM;
    }
    
    /**
     * プロパティ jskInputData の設定メソッド。
     * @param jskInputData プロパティ jskInputData の新しい値。
     */
    public void setJskInputData(Map jskInputData) {

        this.jskInputData = jskInputData;
    }

    /**
     * プロパティ schListRowIndex の値を保持。
     */
    private int schListRowIndex;

    /**
     * プロパティ schListRowIndex の取得メソッド。
     * @return プロパティ schListRowIndex の値。
     */
    public int getSchListRowIndex() {

        return this.schListRowIndex;
    }

    /**
     * プロパティ schListRowIndex の設定メソッド。
     * @param schListRowIndex プロパティ schListRowIndex の新しい値。
     */
    public void setSchListRowIndex(int schListRowIndex) {

        this.schListRowIndex = schListRowIndex;
    }

    /**
     * プロパティ schMoveData の値を保持。
     */
    private XmlDataModel schMoveData;

    /**
     * プロパティ schMoveData の取得メソッド。
     * @return プロパティ schMoveData の値。
     */
    public XmlDataModel getSchMoveData() {

        return this.schMoveData;
    }

    /**
     * プロパティ schMoveData の設定メソッド。
     * @param schMoveData プロパティ schMoveData の新しい値。
     */
    public void setSchMoveData(XmlDataModel schMoveData) {

        this.schMoveData = schMoveData;
    }

    /**
     * プロパティ schMoveRowIndex の値を保持。
     */
    private int schMoveRowIndex;

    /**
     * プロパティ schMoveRowIndex の取得メソッド。
     * @return プロパティ schMoveRowIndex の値。
     */
    public int getSchMoveRowIndex() {

        return this.schMoveRowIndex;
    }

    /**
     * プロパティ schMoveRowIndex の設定メソッド。
     * @param schMoveRowIndex プロパティ schMoveRowIndex の新しい値。
     */
    public void setSchMoveRowIndex(int schMoveRowIndex) {

        this.schMoveRowIndex = schMoveRowIndex;
    }

    /**
     * プロパティ schPasModifyData の値を保持。
     */
    private XmlDataModel schPasModifyData;

    /**
     * プロパティ schPasModifyData の取得メソッド。
     * @return プロパティ schPasModifyData の値。
     */
    public XmlDataModel getSchPasModifyData() {

        return this.schPasModifyData;
    }

    /**
     * プロパティ schPasModifyData の設定メソッド。
     * @param schPasModifyData プロパティ schPasModifyData の新しい値。
     */
    public void setSchPasModifyData(XmlDataModel schPasModifyData) {

        this.schPasModifyData = schPasModifyData;
    }

    public String getAuthInfString() {
       return termID.value + "," + userID.value + "," + useKbn.value;
    }

    /**
     * プロパティ setsubi の値を保持。
     */
    private String setsubi;

    /**
     * プロパティ setsubi の取得メソッド。
     * @return プロパティ setsubi の値。
     */
    public String getSetsubi()  {

        return this.setsubi;
    }

    /**
     * プロパティ setsubi の設定メソッド。
     * @param setsubi プロパティ setsubi の新しい値。
     */
    public void setSetsubi(String setsubi)  {

        this.setsubi = setsubi;
    }

    /**
     * プロパティ beforeSchPasModifyData の値を保持。
     */
    private XmlDataModel beforeSchPasModifyData;

    /**
     * プロパティ beforeSchPasModifyData の取得メソッド。
     * @return プロパティ beforeSchPasModifyData の値。
     */
    public XmlDataModel getBeforeSchPasModifyData() {

        return this.beforeSchPasModifyData;
    }

    /**
     * プロパティ beforeSchPasModifyData の設定メソッド。
     * @param beforeSchPasModifyData プロパティ beforeSchPasModifyData の新しい値。
     */
    public void setBeforeSchPasModifyData(XmlDataModel beforeSchPasModifyData) {

        this.beforeSchPasModifyData = beforeSchPasModifyData;
    }

    /**
     * プロパティ contextTopMessage の値を保持。
     */
    private String contextTopMessage;

    /**
     * プロパティ contextTopMessage の取得メソッド。
     * @return プロパティ contextTopMessage の値。
     */
    //Converter & Validator Error Message
    public String getContextTopMassage() {
        String msgStr = jp.co.nikkeikin.nis.nagoya.common.jsfLib.getTopMassageString(new String[] {"title","submittedValue"});
        if ((msgStr != null) && (!(msgStr.equals("")))) {
            //this.resultMessage = null;            
            return msgStr;
        } else if ((warningMessage != null) && (!(warningMessage.equals("")))) {
            //this.resultMessage = null;            
            return warningMessage.toString();
        } else if ((resultMessage != null) && (!(resultMessage.equals("")))) {
            return resultMessage.toString();
        }
        return null;
        //return jp.co.nikkeikin.nis.nagoya.common.jsfLib.getTopMassageString(new String[] {"title","submittedValue"});
    }

    /**
     * プロパティ contextTopMessage の設定メソッド。
     * @param contextTopMessage プロパティ contextTopMessage の新しい値。
     */
    public void setContextTopMessage(String contextTopMessage) {
        this.contextTopMessage = contextTopMessage;
    }

    /**
     * プロパティ resultMessage の値を保持。
     */
    private String resultMessage;

    /**
     * プロパティ rsultMessage の取得メソッド。
     * @return プロパティ rsultMessage の値。
     */
    public String getResultMessage()  {

        return this.resultMessage;
    }

    /**
     * プロパティ rsultMessage の設定メソッド。
     * @param rsultMessage プロパティ rsultMessage の新しい値。
     */
    public void setResultMessage(String resultMessage)  {

        this.resultMessage = resultMessage;
    }

    /**
     * プロパティ updateDateTime の値を保持。
     */
    private Date updateDateTime;

    /**
     * プロパティ updateDateTime の取得メソッド。
     * @return プロパティ updateDateTime の値。
     */
    public Date getUpdateDateTime() {

        return this.updateDateTime;
    }

    /**
     * プロパティ updateDateTime の設定メソッド。
     * @param updateDateTime プロパティ updateDateTime の新しい値。
     */
    public void setUpdateDateTime(Date updateDateTime) {

        this.updateDateTime = updateDateTime;
    }

    /**
     * プロパティ messageStyle の値を保持。
     */
    private String messageStyle;

    /**
     * プロパティ messageStyle の取得メソッド。
     * @return プロパティ messageStyle の値。
     */
    public String getMessageStyle() {
        String msgStr = jp.co.nikkeikin.nis.nagoya.common.jsfLib.getTopMassageString(new String[] {"title","submittedValue"});
        if ((msgStr != null) && (!(msgStr.equals("")))) {
            return "color : red;";
            //return "errorMessage";
        } else if ((this.errorMessage != null) && (!(this.errorMessage.equals("")))) {
            return "color : red;";
            //return "warningMessage";            
        } else if ((this.warningMessage != null) && (!(this.warningMessage.equals("")))) {
            return "color : red;";
            //return "warningMessage";
        } else if ((this.infoMessage != null) && (!(this.infoMessage.equals("")))) {
            return "color : white;";
            //return "warningMessage";    
        } else {
            return "color : white;";
            //return "infoMessage";
        }     
        //return "color : red;";
        //return this.messageStyle;
    }

    /**
     * プロパティ messageStyle の設定メソッド。
     * @param messageStyle プロパティ messageStyle の新しい値。
     */
    public void setMessageStyle(String messageStyle) {

        this.messageStyle = messageStyle;
    }

    /**
     * プロパティ lotList4CMRedirect の値を保持。
     */
    private boolean lotList4CMRedirect;

    /**
     * プロパティ lotList4CMRedirect の取得メソッド。
     * @return プロパティ lotList4CMRedirect の値。
     */
    public boolean isLotList4CMRedirect() {

        return this.lotList4CMRedirect;
    }

    /**
     * プロパティ lotList4CMRedirect の設定メソッド。
     * @param lotList4CMRedirect プロパティ lotList4CMRedirect の新しい値。
     */
    public void setLotList4CMRedirect(boolean lotList4CMRedirect) {

        this.lotList4CMRedirect = lotList4CMRedirect;
    }

    /**
     * プロパティ lotList4CMRedirect の値を保持。
     */
    private boolean schPassOrderModifyRedirect;

    /**
     * プロパティ lotList4CMRedirect の取得メソッド。
     * @return プロパティ lotList4CMRedirect の値。
     */
    public boolean isSchPassOrderModifyRedirect() {

        return this.schPassOrderModifyRedirect;
    }

    /**
     * プロパティ lotList4CMRedirect の設定メソッド。
     * @param lotList4CMRedirect プロパティ lotList4CMRedirect の新しい値。
     */
    public void setSchPassOrderModifyRedirect(boolean schPassOrderModifyRedirect) {

        this.schPassOrderModifyRedirect = schPassOrderModifyRedirect;
    }

    /**
     * プロパティ warningMessage の値を保持。
     */
    private String warningMessage;

    /**
     * プロパティ warningMessage の取得メソッド。
     * @return プロパティ warningMessage の値。
     */
    public String getWarningMessage() {

        return this.warningMessage;
    }

    /**
     * プロパティ warningMessage の設定メソッド。
     * @param warningMessage プロパティ warningMessage の新しい値。
     */
    public void setWarningMessage(String warningMessage) {

        this.warningMessage = warningMessage;
    }

    /**
     * プロパティ completionMessageRendered の値を保持。
     */
    private boolean completionMessageRendered;

    /**
     * プロパティ completionMessageRendered の取得メソッド。
     * @return プロパティ completionMessageRendered の値。
     */
    public boolean isCompletionMessageRendered() {
        String msgStr = jp.co.nikkeikin.nis.nagoya.common.jsfLib.getTopMassageString(new String[] {"title","submittedValue"});
        if ((msgStr != null) && (!(msgStr.equals("")))) {
            return false;
        } else if ((warningMessage != null) && (!(warningMessage.equals("")))) {
            return false;
        } else {
            return true;
        }
        //return this.completionMessageRendered;
    }

    /**
     * プロパティ errorMessage の値を保持。
     */
    private String errorMessage;

    /**
     * プロパティ errorMessage の取得メソッド。
     * @return プロパティ errorMessage の値。
     */
    public String getErrorMessage() {

        return this.errorMessage;
    }

    /**
     * プロパティ errorMessage の設定メソッド。
     * @param errorMessage プロパティ errorMessage の新しい値。
     */
    public void setErrorMessage(String errorMessage) {

        this.errorMessage = errorMessage;
    }

    /**
     * プロパティ infoMessage の値を保持。
     */
    private String infoMessage;

    /**
     * プロパティ infoMessage の取得メソッド。
     * @return プロパティ infoMessage の値。
     */
    public String getInfoMessage() {

        return this.infoMessage;
    }

    /**
     * プロパティ infoMessage の設定メソッド。
     * @param infoMessage プロパティ infoMessage の新しい値。
     */
    public void setInfoMessage(String infoMessage) {

        this.infoMessage = infoMessage;
    }

    /**
     * プロパティ message の値を保持。
     */
    private String message;

    /**
     * プロパティ message の取得メソッド。
     * @return プロパティ message の値。
     */
    public String getMessage() {
        String msg = null;
        String validatorMessage = jp.co.nikkeikin.nis.nagoya.common.jsfLib.getTopMassageString(new String[] {"title","submittedValue"});
        if ((validatorMessage != null) && (!(validatorMessage.equals("")))) {
            //this.resultMessage = null;
            msg = validatorMessage;
        } else if ((errorMessage != null) && (!(errorMessage.equals("")))) {
            //this.resultMessage = null;
            msg = errorMessage;
        } else if ((warningMessage != null) && (!(warningMessage.equals("")))) {
            msg = warningMessage.toString();
        } else if ((infoMessage != null) && (!(infoMessage.equals("")))) {
            msg = infoMessage;
        }
        //this.errorMessage = null;
        //this.warningMessage = null;
        //this.infoMessage = null;        
        return msg;
        //return this.message;
    }

    /**
     * プロパティ message の設定メソッド。
     * @param message プロパティ message の新しい値。
     */
    public void setMessage(String message) {

        this.message = message;
    }

    /**
     * プロパティ schListDelData の値を保持。
     */
    private XmlDataModel schListDelData;

    /**
     * プロパティ schListDelData の取得メソッド。
     * @return プロパティ schListDelData の値。
     */
    public XmlDataModel getSchListDelData() {

        return this.schListDelData;
    }

    /**
     * プロパティ schListDelData の設定メソッド。
     * @param schListDelData プロパティ schListDelData の新しい値。
     */
    public void setSchListDelData(XmlDataModel schListDelData) {

        this.schListDelData = schListDelData;
    }

    /**
     * プロパティ lotDeleteData の値を保持。
     */
    private XmlDataModel lotDeleteData;

    /**
     * プロパティ lotDeleteData の取得メソッド。
     * @return プロパティ lotDeleteData の値。
     */
    public XmlDataModel getLotDeleteData() {

        return this.lotDeleteData;
    }

    /**
     * プロパティ lotDeleteData の設定メソッド。
     * @param lotDeleteData プロパティ lotDeleteData の新しい値。
     */
    public void setLotDeleteData(XmlDataModel lotDeleteData) {

        this.lotDeleteData = lotDeleteData;
    }

    public void clearMessage() {
        if (errorMessage != null) errorMessage = null;
        if (warningMessage != null) warningMessage = null;
        if (infoMessage != null) infoMessage = null;        
    }

    /**
     * プロパティ lotPasModifyLtNo の値を保持。
     */
    private String lotPasModifyLtNo;

    /**
     * プロパティ lotPasModifyLtNo の取得メソッド。
     * @return プロパティ lotPasModifyLtNo の値。
     */
    public String getLotPasModifyLtNo() {

        return this.lotPasModifyLtNo;
    }

    /**
     * プロパティ lotPasModifyLtNo の設定メソッド。
     * @param lotPasModifyLtNo プロパティ lotPasModifyLtNo の新しい値。
     */
    public void setLotPasModifyLtNo(String lotPasModifyLtNo) {

        this.lotPasModifyLtNo = lotPasModifyLtNo;
    }

    /**
     * プロパティ lotList4CMFirstIndex の値を保持。
     */
    private int lotList4CMFirstIndex;

    /**
     * プロパティ lotList4CMFirstIndex の取得メソッド。
     * @return プロパティ lotList4CMFirstIndex の値。
     */
    public int getLotList4CMFirstIndex() {

        return this.lotList4CMFirstIndex;
    }

    /**
     * プロパティ lotList4CMFirstIndex の設定メソッド。
     * @param lotList4CMFirstIndex プロパティ lotList4CMFirstIndex の新しい値。
     */
    public void setLotList4CMFirstIndex(int lotList4CMFirstIndex) {

        this.lotList4CMFirstIndex = lotList4CMFirstIndex;
    }

    /**
     * プロパティ passModFlag の値を保持。
     */
    private static final int PASS_MOD_FLAG_ATSU = 0;
    private static final int PASS_MOD_FLAG_PASSCOUNT = 1;
    private static final int PASS_MOD_FLAG_PASSOUT = 2;
    private boolean[] passModFlag = new boolean[3];

    /**
     * プロパティ passModFlag の取得メソッド。
     * @return プロパティ passModFlag の値。
     */
    /*
    public boolean[] getPassModFlag() {

        return this.passModFlag;
    }
    */

    /**
     * プロパティ passModFlag の設定メソッド。
     * @param passModFlag プロパティ passModFlag の新しい値。
     */
    /*
    public void setPassModFlag(boolean[] passModFlag) {

        this.passModFlag = passModFlag;
    }
    */

    public boolean isPassModFlagAtsu() {
        return passModFlag[PASS_MOD_FLAG_ATSU];
    }

    public boolean isPassModFlagPassCount() {
        return passModFlag[PASS_MOD_FLAG_PASSCOUNT];
    }

    public boolean isPassModFlagPassOut() {
        return passModFlag[PASS_MOD_FLAG_PASSOUT];
    }
    
    public void setPassModFlagAtsu(boolean flag){
        this.passModFlag[PASS_MOD_FLAG_ATSU] = flag;
    }
    
    public void setPassModFlagPassCount(boolean flag){
        this.passModFlag[PASS_MOD_FLAG_PASSCOUNT] = flag;
    }
    
    public void setPassModFlagPassOut(boolean flag){
        this.passModFlag[PASS_MOD_FLAG_PASSOUT] = flag;
    }
       
    public void clearPassModFlag(){
        for (int idx = 0; idx < this.passModFlag.length; idx++) passModFlag[idx] = false;
    }

    /**
     * プロパティ nowCycle の値を保持。
     */
    private boolean notNowCycle;

    /**
     * プロパティ nowCycle の取得メソッド。
     * @return プロパティ nowCycle の値。
     */
    public boolean isNotNowCycle() {

        return this.notNowCycle;
    }

    /**
     * プロパティ nowCycle の設定メソッド。
     * @param nowCycle プロパティ nowCycle の新しい値。
     */
    public void setNotNowCycle(boolean notNowCycle) {

        this.notNowCycle = notNowCycle;
    }

    /**
     * プロパティ lotInsertKind の値を保持。
     */
    static final String LOT_INSERT_KIND_LOTLIST = "LotList4CM";
    static final String LOT_INSERT_KIND_SCHLIST = "SchList4CM";
    private String lotInsertKind;

    /**
     * プロパティ lotInsertKind の取得メソッド。
     * @return プロパティ lotInsertKind の値。
     */
    public String getLotInsertKind() {

        return this.lotInsertKind;
    }

    /**
     * プロパティ lotInsertKind の設定メソッド。
     * @param lotInsertKind プロパティ lotInsertKind の新しい値。
     */
    public void setLotInsertKind(String lotInsertKind) {

        this.lotInsertKind = lotInsertKind;
    }

    /**
     * プロパティ passOrderBefData の値を保持。
     */
    private XmlDataModel passOrderBefData;

    /**
     * プロパティ passOrderBefData の取得メソッド。
     * @return プロパティ passOrderBefData の値。
     */
    public XmlDataModel getPassOrderBefData() {

        return this.passOrderBefData;
    }

    /**
     * プロパティ passOrderBefData の設定メソッド。
     * @param passOrderBefData プロパティ passOrderBefData の新しい値。
     */
    public void setPassOrderBefData(XmlDataModel passOrderBefData) {

        this.passOrderBefData = passOrderBefData;
    }

    private PassOrderList passOrderList = new PassOrderList();
    
    public PassOrderList getPassOrderList(){
        return this.passOrderList;
    }
    
    public void setPassOrderList(PassOrderList passOrderList){
        this.passOrderList = passOrderList;
    }

    /**
     * プロパティ paraMode の値を保持。
     */
    private String paraMode;

    /**
     * プロパティ paraMode の取得メソッド。
     * @return プロパティ paraMode の値。
     */
    public String getParaMode() {

        return this.paraMode;
    }

    /**
     * プロパティ paraMode の設定メソッド。
     * @param paraMode プロパティ paraMode の新しい値。
     */
    public void setParaMode(String paraMode) {

        this.paraMode = paraMode;
    }

    /**
     * プロパティ schList4CMDispType の値を保持。
     */
    private int schList4CMDispType;

    /**
     * プロパティ schList4CMDispType の取得メソッド。
     * @return プロパティ schList4CMDispType の値。
     */
    public int getSchList4CMDispType() {

        return this.schList4CMDispType;
    }

    /**
     * プロパティ schList4CMDispType の設定メソッド。
     * @param schList4CMDispType プロパティ schList4CMDispType の新しい値。
     */
    public void setSchList4CMDispType(int schList4CMDispType) {

        this.schList4CMDispType = schList4CMDispType;
    }

    /**
     * プロパティ updateInterval の値を保持。
     * 単位：msec　初期値：5秒
     */
    private int updateInterval = 5000;

    /**
     * プロパティ updateInterval の取得メソッド。
     * @return プロパティ updateInterval の値。
     */
    public int getUpdateInterval() {

        return this.updateInterval;
    }
    
    /**
     * プロパティ updateInterval の設定メソッド。
     * @param updateInterval プロパティ updateInterval の新しい値。
     */
    public void setUpdateInterval(int updateInterval) {

        this.updateInterval = updateInterval;
    }

    /**
     * プロパティ dispPassSchList4CM の値を保持。
     */
    private float dispPassSchList4CM;

    /**
     * プロパティ dispPassSchList4CM の取得メソッド。
     * @return プロパティ dispPassSchList4CM の値。
     */
    public float getDispPassSchList4CM() {

        return this.dispPassSchList4CM;
    }

    /**
     * プロパティ dispPassSchList4CM の設定メソッド。
     * @param dispPassSchList4CM プロパティ dispPassSchList4CM の新しい値。
     */
    public void setDispPassSchList4CM(float dispPassSchList4CM) {

        this.dispPassSchList4CM = dispPassSchList4CM;
    }

    /**
     * プロパティ manualButtonDisable の値を保持。
     */
    private boolean manualButtonDisable = false;

    /**
     * プロパティ displayTypeDisable の取得メソッド。
     * @return プロパティ displayTypeDisable の値。
     */
    public boolean isManualButtonDisable()     {

        return this.manualButtonDisable;
    }

    /**
     * プロパティ displayTypeDisable の設定メソッド。
     * @param displayTypeDisable プロパティ displayTypeDisable の新しい値。
     */
    public void setManualButtonDisable(boolean manualButtonDisable)     {

        this.manualButtonDisable = manualButtonDisable;
    }

    /**
     * プロパティ manualButtonRendered の値を保持。
     */
    private boolean manualButtonRendered = true;

    /**
     * プロパティ displayTypeRendered の取得メソッド。
     * @return プロパティ displayTypeRendered の値。
     */
    public boolean isManualButtonRendered()     {

        return this.manualButtonRendered;
    }

    /**
     * プロパティ displayTypeRendered の設定メソッド。
     * @param displayTypeRendered プロパティ displayTypeRendered の新しい値。
     */
    public void setManualButtonRendered(boolean manualButtonRendered)     {

        this.manualButtonRendered = manualButtonRendered;
    }

    /**
     * プロパティ permissionButtonDisable の値を保持。
     */
    private boolean permissionButtonDisable = false;
   
    /**
     * プロパティ permitionDisable の取得メソッド。
     * @return プロパティ permitionDisable の値。
     */
    public boolean isPermissionButtonDisable()   {

        return this.permissionButtonDisable;
    }

    /**
     * プロパティ permitionDisable の設定メソッド。
     * @param permitionDisable プロパティ permitionDisable の新しい値。
     */
    public void setPermissionButtonDisable(boolean permissionButtonDisable)   {

        this.permissionButtonDisable = permissionButtonDisable;
    }
    /**
     * プロパティ permissionButtonDisable の値を保持。
     */
    private boolean permissionButtonRendered = false;
   
    /**
     * プロパティ permissionButtonRendered の取得メソッド。
     * @return プロパティ permissionButtonRendered の値。
     */
    public boolean isPermissionButtonRendered()   {

        return this.permissionButtonRendered;
    }

    /**
     * プロパティ permissionButtonRendered の設定メソッド。
     * @param permissionButtonRendered プロパティ permissionButtonRendered の新しい値。
     */
    public void setPermissionButtonRendered(boolean permissionButtonRendered)   {

        this.permissionButtonRendered = permissionButtonRendered;
    }

    /**
     * プロパティ permissionButtonStyle の値を保持。
     */
    private String permissionButtonStyle;

    /**
     * プロパティ permissionButtonStyle の取得メソッド。
     * @return プロパティ permissionButtonStyle の値。
     */
    public String getPermissionButtonStyle() {
        //permissionButtonDisable
        if(permissionButtonDisable) {
            return "button-blue";
        } else {
            return "button";
        }
        //return this.permissionButtonStyle;
    }

    /**
     * プロパティ permissionButtonStyle の設定メソッド。
     * @param permissionButtonStyle プロパティ permissionButtonStyle の新しい値。
     */
    public void setPermissionButtonStyle(String permissionButtonStyle) {

        this.permissionButtonStyle = permissionButtonStyle;
    }

    /**
     * プロパティ denialButtonStyle の値を保持。
     */
    private String denialButtonStyle;

    /**
     * プロパティ denialButtonStyle の取得メソッド。
     * @return プロパティ denialButtonStyle の値。
     */
    public String getDenialButtonStyle() {
        //permissionButtonDisable
        if(!permissionButtonDisable) {
            return "button-red";
        } else {
            return "button";
        }
        //return this.denialButtonStyle;
    }

    /**
     * プロパティ denialButtonStyle の設定メソッド。
     * @param denialButtonStyle プロパティ denialButtonStyle の新しい値。
     */
    public void setDenialButtonStyle(String denialButtonStyle) {

        this.denialButtonStyle = denialButtonStyle;
    }

    /**
     * プロパティ lastUpdate の値を保持。
     */
    private String lastUpdate;

    /**
     * プロパティ lastUpdate の取得メソッド。
     * @return プロパティ lastUpdate の値。
     */
    public String getLastUpdate() {

        return this.lastUpdate;
    }

    /**
     * プロパティ lastUpdate の設定メソッド。
     * @param lastUpdate プロパティ lastUpdate の新しい値。
     */
    public void setLastUpdate(String lastUpdate) {

        this.lastUpdate = lastUpdate;
    }

    /**
     * プロパティ enableEndSign の値を保持。
     */
    private int enableEndSign;

    /**
     * プロパティ enableEndSign の取得メソッド。
     * @return プロパティ enableEndSign の値。
     */
    public int getEnableEndSign() {

        return this.enableEndSign;
    }

    /**
     * プロパティ enableEndSign の設定メソッド。
     * @param enableEndSign プロパティ enableEndSign の新しい値。
     */
    public void setEnableEndSign(int enableEndSign) {

        this.enableEndSign = enableEndSign;
    }

    /**
     * プロパティ autoRefresh の値を保持。
     */
    private int autoRefresh;

    /**
     * プロパティ autoRefresh の取得メソッド。
     * @return プロパティ autoRefresh の値。
     */
    public int getAutoRefresh() {

        return this.autoRefresh;
    }

    /**
     * プロパティ autoRefresh の設定メソッド。
     * @param autoRefresh プロパティ autoRefresh の新しい値。
     */
    public void setAutoRefresh(int autoRefresh) {

        this.autoRefresh = autoRefresh;
    }

    /**
     * プロパティ schListLastUpdate の値を保持。
     */
    private String schListLastUpdate;

    /**
     * プロパティ schListLastUpdate の取得メソッド。
     * @return プロパティ schListLastUpdate の値。
     */
    public String getSchListLastUpdate() {

        return this.schListLastUpdate;
    }

    /**
     * プロパティ schListLastUpdate の設定メソッド。
     * @param schListLastUpdate プロパティ schListLastUpdate の新しい値。
     */
    public void setSchListLastUpdate(String schListLastUpdate) {

        this.schListLastUpdate = schListLastUpdate;
    }

    /**
     * プロパティ autoButtonDisable の値を保持。
     */
    private boolean autoButtonDisable;

    /**
     * プロパティ schListAutoButtonDisable の取得メソッド。
     * @return プロパティ schListAutoButtonDisable の値。
     */
    public boolean isAutoButtonDisable()  {

        return this.autoButtonDisable;
    }

    /**
     * プロパティ schListAutoButtonDisable の設定メソッド。
     * @param schListAutoButtonDisable プロパティ schListAutoButtonDisable の新しい値。
     */
    public void setAutoButtonDisable(boolean autoButtonDisable)  {

        this.autoButtonDisable = autoButtonDisable;
    }

    /**
     * プロパティ autoButtonRendered の値を保持。
     */
    private boolean autoButtonRendered;

    /**
     * プロパティ schListAutoButtonRendered の取得メソッド。
     * @return プロパティ schListAutoButtonRendered の値。
     */
    public boolean isAutoButtonRendered()  {

        return this.autoButtonRendered;
    }

    /**
     * プロパティ schListAutoButtonRendered の設定メソッド。
     * @param schListAutoButtonRendered プロパティ schListAutoButtonRendered の新しい値。
     */
    public void setAutoButtonRendered(boolean autoButtonRendered)  {

        this.autoButtonRendered = autoButtonRendered;
    }

    /**
     * プロパティ autoButtonStyle の値を保持。
     */
    private String autoButtonStyle;

    /**
     * プロパティ controlButtonStyle の取得メソッド。
     * @return プロパティ controlButtonStyle の値。
     */
    public String getAutoButtonStyle()  {
        if(autoButtonDisable) {
            return "button-blue";
        } else {
            return "button";
        }
        //return this.autoButtonStyle;
    }

    /**
     * プロパティ controlButtonStyle の設定メソッド。
     * @param controlButtonStyle プロパティ controlButtonStyle の新しい値。
     */
    public void setAutoButtonStyle(String autoButtonStyle)  {

        this.autoButtonStyle = autoButtonStyle;
    }

    /**
     * プロパティ manualButtonStyle の値を保持。
     */
    private String manualButtonStyle;

    /**
     * プロパティ manuButtonStyle の取得メソッド。
     * @return プロパティ manuButtonStyle の値。
     */
    public String getManualButtonStyle() {
        if(manualButtonDisable) {
            return "button-red";
        } else {
            return "button";
        }
        //return this.manuButtonStyle;
    }

    /**
     * プロパティ manuButtonStyle の設定メソッド。
     * @param manuButtonStyle プロパティ manuButtonStyle の新しい値。
     */
    public void setManualButtonStyle(String manualButtonStyle)  {

        this.manualButtonStyle = manualButtonStyle;
    }

    /**
     * プロパティ rowInfRendered の値を保持。
     */
    private boolean rowInfRendered;

    /**
     * プロパティ rowInfRendered の取得メソッド。
     * @return プロパティ rowInfRendered の値。
     */
    public boolean isRowInfRendered() {

        return this.rowInfRendered;
    }

    /**
     * プロパティ rowInfRendered の設定メソッド。
     * @param rowInfRendered プロパティ rowInfRendered の新しい値。
     */
    public void setRowInfRendered(boolean rowInfRendered) {

        this.rowInfRendered = rowInfRendered;
    }
    
    
    /**
     * 冷延スケジュール一覧画面毎にボタンの属性設定
     * manualButtonDisable は、画面頁、前、次、再送、ロット一覧、作業順、修正、移動、上挿入、下挿入、削除、ロット情報のRenderedに設定
     * manualButtonRendered は、停止予定、点検板、返り材のRenderedに設定
     */
    public void setSchListButtonProperty(int dispType) {
        switch (dispType) {
            case CommonConst.DISP_TYPE_AUTO:
                autoButtonDisable = true;
                autoButtonRendered = true;
                manualButtonDisable = false;
                manualButtonRendered = true;
                permissionButtonDisable = true;
                permissionButtonRendered = false;
                denialButtonDisable = false;
                rowInfRendered = false;
                break;
            case CommonConst.DISP_TYPE_MANU_ALL:
                autoButtonDisable = false;
                autoButtonRendered = true;
                manualButtonDisable = true;
                manualButtonRendered = true;
                permissionButtonDisable = false;
                permissionButtonRendered = true;
                denialButtonDisable = true;
                rowInfRendered = true;
                break;
            case CommonConst.DISP_TYPE_MANU_KANRYO:
                autoButtonDisable = false;
                autoButtonRendered = true;
                manualButtonDisable = true;
                manualButtonRendered = true;
                permissionButtonDisable = true;
                permissionButtonRendered = true;
                denialButtonDisable = false;
                rowInfRendered = true;
                break;
            case CommonConst.DISP_TYPE_MANU_DANDORI:
                autoButtonDisable = false;
                autoButtonRendered = false;
                manualButtonDisable = true;
                manualButtonRendered = false;
                permissionButtonRendered = false;
                rowInfRendered = true;
                break;
            case CommonConst.DISP_TYPE_CYCLE:
                break;
            default :
                break;
        }
        schList4CMDispType = dispType;
    }

    /**
     * プロパティ denialButtonDisable の値を保持。
     */
    private boolean denialButtonDisable;

    /**
     * プロパティ denialButtonDisable の取得メソッド。
     * @return プロパティ denialButtonDisable の値。
     */
    public boolean isDenialButtonDisable() {

        return this.denialButtonDisable;
    }

    /**
     * プロパティ denialButtonDisable の設定メソッド。
     * @param denialButtonDisable プロパティ denialButtonDisable の新しい値。
     */
    public void setDenialButtonDisable(boolean denialButtonDisable) {

        this.denialButtonDisable = denialButtonDisable;
    }

    /**
     * プロパティ icasKaishiTime の値を保持。
     */
    private long icasKaishiTime;

    /**
     * プロパティ icasKaishiTime の取得メソッド。
     * @return プロパティ icasKaishiTime の値。
     */
    public long getIcasKaishiTime() {

        return this.icasKaishiTime;
    }

    /**
     * プロパティ icasKaishiTime の設定メソッド。
     * @param icasKaishiTime プロパティ icasKaishiTime の新しい値。
     */
    public void setIcasKaishiTime(long icasKaishiTime) {

        this.icasKaishiTime = icasKaishiTime;
    }

    /**
     * プロパティ insDispSchList4CM の値を保持。
     */
    private float insDispSchList4CM;

    /**
     * プロパティ insDispSchList4CM の取得メソッド。
     * @return プロパティ insDispSchList4CM の値。
     */
    public float getInsDispSchList4CM() {

        return this.insDispSchList4CM;
    }

    /**
     * プロパティ insDispSchList4CM の設定メソッド。
     * @param insDispSchList4CM プロパティ insDispSchList4CM の新しい値。
     */
    public void setInsDispSchList4CM(float insDispSchList4CM) {

        this.insDispSchList4CM = insDispSchList4CM;
    }

    /**
     * プロパティ jissekiOpCodeLastTime の値を保持。
     */
    private String jissekiOpCodeLastTime;

    /**
     * プロパティ jissekiOpCodeLastTime の取得メソッド。
     * @return プロパティ jissekiOpCodeLastTime の値。
     */
    public String getJissekiOpCodeLastTime() {

        return this.jissekiOpCodeLastTime;
    }

    /**
     * プロパティ jissekiOpCodeLastTime の設定メソッド。
     * @param jissekiOpCodeLastTime プロパティ jissekiOpCodeLastTime の新しい値。
     */
    public void setJissekiOpCodeLastTime(String jissekiOpCodeLastTime) {

        this.jissekiOpCodeLastTime = jissekiOpCodeLastTime;
    }

    /**
     * プロパティ jissekiStaTimeLastTime の値を保持。
     */
    private String jissekiStaTimeLastTime;

    /**
     * プロパティ jissekiStaTimeLastTime の取得メソッド。
     * @return プロパティ jissekiStaTimeLastTime の値。
     */
    public String getJissekiStaTimeLastTime() {

        return this.jissekiStaTimeLastTime;
    }

    /**
     * プロパティ jissekiStaTimeLastTime の設定メソッド。
     * @param jissekiStaTimeLastTime プロパティ jissekiStaTimeLastTime の新しい値。
     */
    public void setJissekiStaTimeLastTime(String jissekiStaTimeLastTime) {

        this.jissekiStaTimeLastTime = jissekiStaTimeLastTime;
    }

    /**
     * プロパティ jissekiEndTimeLastTime の値を保持。
     */
    private String jissekiEndTimeLastTime;

    /**
     * プロパティ jissekiEndTimeLastTime の取得メソッド。
     * @return プロパティ jissekiEndTimeLastTime の値。
     */
    public String getJissekiEndTimeLastTime() {

        return this.jissekiEndTimeLastTime;
    }

    /**
     * プロパティ jissekiEndTimeLastTime の設定メソッド。
     * @param jissekiEndTimeLastTime プロパティ jissekiEndTimeLastTime の新しい値。
     */
    public void setJissekiEndTimeLastTime(String jissekiEndTimeLastTime) {

        this.jissekiEndTimeLastTime = jissekiEndTimeLastTime;
    }

    /**
     * プロパティ setsubiJissekiAction の値を保持。
     */
    private String setsubiJissekiAction;

    /**
     * プロパティ setsubiJissekiAction の取得メソッド。
     * @return プロパティ setsubiJissekiAction の値。
     */
    public String getSetsubiJissekiAction() {

        return this.setsubiJissekiAction;
    }

    /**
     * プロパティ setsubiJissekiAction の設定メソッド。
     * @param setsubiJissekiAction プロパティ setsubiJissekiAction の新しい値。
     */
    public void setSetsubiJissekiAction(String setsubiJissekiAction) {

        this.setsubiJissekiAction = setsubiJissekiAction;
    }

    /**
     * プロパティ cycleListData の値を保持。
     */
    private jp.co.nikkeikin.nis.nagoya.common.XmlDataModel cycleListData;

    /**
     * プロパティ cycleListData の取得メソッド。
     * @return プロパティ cycleListData の値。
     */
    public jp.co.nikkeikin.nis.nagoya.common.XmlDataModel getCycleListData() {

        return this.cycleListData;
    }

    /**
     * プロパティ cycleListData の設定メソッド。
     * @param cycleListData プロパティ cycleListData の新しい値。
     */
    public void setCycleListData(jp.co.nikkeikin.nis.nagoya.common.XmlDataModel cycleListData) {

        this.cycleListData = cycleListData;
    }

    /**
     * プロパティ cycleLotListData の値を保持。
     */
    private jp.co.nikkeikin.nis.nagoya.common.XmlDataModel cycleLotListData;

    /**
     * プロパティ cycleLotListData の取得メソッド。
     * @return プロパティ cycleLotListData の値。
     */
    public jp.co.nikkeikin.nis.nagoya.common.XmlDataModel getCycleLotListData() {

        return this.cycleLotListData;
    }

    /**
     * プロパティ cycleLotListData の設定メソッド。
     * @param cycleLotListData プロパティ cycleLotListData の新しい値。
     */
    public void setCycleLotListData(jp.co.nikkeikin.nis.nagoya.common.XmlDataModel cycleLotListData) {

        this.cycleLotListData = cycleLotListData;
    }

    /**
     * プロパティ inputMethod の値を保持。
     */
    private String inputMethod;

    /**
     * プロパティ inputMethod の取得メソッド。
     * @return プロパティ inputMethod の値。
     */
    public String getInputMethod() {

        return this.inputMethod;
    }

    /**
     * プロパティ inputMethod の設定メソッド。
     * @param inputMethod プロパティ inputMethod の新しい値。
     */
    public void setInputMethod(String inputMethod) {

        this.inputMethod = inputMethod;
    }

    /**
     * プロパティ teisiName の値を保持。
     */
    //private String teisiName;

    /**
     * プロパティ teisiName の取得メソッド。
     * @return プロパティ teisiName の値。
     */
    //public String getTeisiName() {

    //    return this.teisiName;
    //}

    /**
     * プロパティ teisiName の設定メソッド。
     * @param teisiName プロパティ teisiName の新しい値。
     */
    //public void setTeisiName(String teisiName) {

    //    this.teisiName = teisiName;
    //}

    /**
     * プロパティ teisiCd の値を保持。
     */
    //private String teisiCd;

    /**
     * プロパティ teisiCd の取得メソッド。
     * @return プロパティ teisiCd の値。
     */
    //public String getTeisiCd() {

    //    return this.teisiCd;
    //}

    /**
     * プロパティ teisiCd の設定メソッド。
     * @param teisiCd プロパティ teisiCd の新しい値。
     */
    //public void setTeisiCd(String teisiCd) {

    //   this.teisiCd = teisiCd;
    //}

    /**
     * プロパティ teisiInfoData の値を保持。
     */
    private XmlDataModel teisiInfoData;

    /**
     * プロパティ teisiInfoData の取得メソッド。
     * @return プロパティ teisiInfoData の値。
     */
    public XmlDataModel getTeisiInfoData() {

        return this.teisiInfoData;
    }

    /**
     * プロパティ teisiInfoData の設定メソッド。
     * @param teisiInfoData プロパティ teisiInfoData の新しい値。
     */
    public void setTeisiInfoData(XmlDataModel teisiInfoData) {

        this.teisiInfoData = teisiInfoData;
    }

    /**
     * プロパティ cycleList4CMFirstIndex の値を保持。
     */
    private int cycleList4CMFirstIndex;

    /**
     * プロパティ cycleListFirstIndex の取得メソッド。
     * @return プロパティ cycleListFirstIndex の値。
     */
    public int getCycleList4CMFirstIndex()  {

        return this.cycleList4CMFirstIndex;
    }

    /**
     * プロパティ cycleListFirstIndex の設定メソッド。
     * @param cycleListFirstIndex プロパティ cycleListFirstIndex の新しい値。
     */
    public void setCycleList4CMFirstIndex(int cycleList4CMFirstIndex)  {

        this.cycleList4CMFirstIndex = cycleList4CMFirstIndex;
    }

    /**
     * プロパティ cycleListRowIndex の値を保持。
     */
    private int cycleListRowIndex;

    /**
     * プロパティ cycleListRowIndex の取得メソッド。
     * @return プロパティ cycleListRowIndex の値。
     */
    public int getCycleListRowIndex() {

        return this.cycleListRowIndex;
    }

    /**
     * プロパティ cycleListRowIndex の設定メソッド。
     * @param cycleListRowIndex プロパティ cycleLotListRowIndex の新しい値。
     */
    public void setCycleListRowIndex(int cycleListRowIndex) {

        this.cycleListRowIndex = cycleListRowIndex;
    }

    /**
     * プロパティ cycleDeleteData の値を保持。
     */
    private XmlDataModel cycleDeleteData;

    /**
     * プロパティ cycleDeleteData の取得メソッド。
     * @return プロパティ cycleDeleteData の値。
     */
    public XmlDataModel getCycleDeleteData() {

        return this.cycleDeleteData;
    }

    /**
     * プロパティ cycleDeleteData の設定メソッド。
     * @param cycleDeleteData プロパティ cycleDeleteData の新しい値。
     */
    public void setCycleDeleteData(XmlDataModel cycleDeleteData) {

        this.cycleDeleteData = cycleDeleteData;
    }

    /**
     * プロパティ paraSetsubi の値を保持。
     */
    private String paraSetsubi;

    /**
     * プロパティ paraSetsubi の取得メソッド。
     * @return プロパティ paraSetsubi の値。
     */
    public String getParaSetsubi() {

        return this.paraSetsubi;
    }

    /**
     * プロパティ paraSetsubi の設定メソッド。
     * @param paraSetsubi プロパティ paraSetsubi の新しい値。
     */
    public void setParaSetsubi(String paraSetsubi) {
   
        this.paraSetsubi = paraSetsubi;
    }
    
    /**
     * プロパティ sessionId の値を保持。
     */
    private String sessionId;

    /**
     * プロパティ sessionId の取得メソッド。
     * @return プロパティ sessionId の値。
     */
    public String getSessionId() {

        return this.sessionId;
    }

    /**
     * プロパティ sessionId の設定メソッド。
     * @param sessionId プロパティ sessionId の新しい値。
     */
    public void setSessionId(String sessionId) {
   
        this.sessionId = sessionId;
    }    

    /**
     * プロパティ dispType の値を保持。
     */
    private int dispType;

    /**
     * プロパティ dispType の取得メソッド。
     * @return プロパティ dispType の値。
     */
    public int getDispType() {

        return this.dispType;
    }

    /**
     * プロパティ dispType の設定メソッド。
     * @param dispType プロパティ dispType の新しい値。
     */
    public void setDispType(int dispType) {
   
        this.dispType = dispType;
    }

    /**
     * プロパティ cycleInsertIndex の値を保持。
     */
    private int cycleInsertIndex = 0;

    /**
     * プロパティ cycleInsertIndex の取得メソッド。
     * @return プロパティ cycleInsertIndex の値。
     */
    public int getCycleInsertIndex() {

        return this.cycleInsertIndex;
    }

    /**
     * プロパティ cycleInsertIndex の設定メソッド。
     * @param cycleInsertIndex プロパティ cycleInsertIndex の新しい値。
     */
    public void setCycleInsertIndex(int cycleInsertIndex) {

        this.cycleInsertIndex = cycleInsertIndex;
    }

    /**
     * プロパティ cycleInsertOrder の値を保持。
     */
    private float cycleInsertOrder = 0;

    /**
     * プロパティ cycleInsertOrder の取得メソッド。
     * @return プロパティ cycleInsertOrder の値。
     */
    public float getCycleInsertOrder() {

        return this.cycleInsertOrder;
    }

    /**
     * プロパティ cycleInsertOrder の設定メソッド。
     * @param cycleInsertOrder プロパティ cycleInsertOrder の新しい値。
     */
    public void setCycleInsertOrder(float cycleInsertOrder) {

        this.cycleInsertOrder = cycleInsertOrder;
    }
    
    /**
     * プロパティ focusId の値を保持。
     */
    private String focusId;

    /**
     * プロパティ focusId の取得メソッド。
     * @return プロパティ focusId の値。
     */
    public String getFocusId() {

        return this.focusId;
    }

    /**
     * プロパティ focusId の設定メソッド。
     * @param focusId プロパティ focusId の新しい値。
     */
    public void setFocusId(String focusId) {
   
        this.focusId = focusId;
    }
        
    /**
     * プロパティ schList4CMFirstCycleKey の値を保持。
     */
    private String schList4CMFirstCycleKey;

    /**
     * プロパティ schList4CMFirstCycleKey の取得メソッド。
     * @return プロパティ schList4CMFirstCycleKey の値。
     */
    public String getSchList4CMFirstCycleKey() {

        return this.schList4CMFirstCycleKey;
    }

    /**
     * プロパティ schList4CMFirstCycleKey の設定メソッド。
     * @param schList4CMFirstCycleKey プロパティ schList4CMFirstCycleKey の新しい値。
     */
    public void setSchList4CMFirstCycleKey(String schList4CMFirstCycleKey) {
   
        this.schList4CMFirstCycleKey = schList4CMFirstCycleKey;
    }
    
    /**
     * プロパティ useStatusLastUpdate の値を保持。
     */
    private String useStatusLastUpdate;

    /**
     * プロパティ useStatusLastUpdate の取得メソッド。
     * @return プロパティ useStatusLastUpdate の値。
     */
    public String getUseStatusLastUpdate() {

        return this.useStatusLastUpdate;
    }

    /**
     * プロパティ useStatusLastUpdate の設定メソッド。
     * @param useStatusLastUpdate プロパティ useStatusLastUpdate の新しい値。
     */
    public void setUseStatusLastUpdate(String useStatusLastUpdate) {

        this.useStatusLastUpdate = useStatusLastUpdate;
    }
    
}
