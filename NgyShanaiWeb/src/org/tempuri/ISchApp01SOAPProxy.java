package org.tempuri;

public class ISchApp01SOAPProxy implements org.tempuri.ISchApp01SOAP {
  private String _endpoint = null;
  private org.tempuri.ISchApp01SOAP iSchApp01SOAP = null;

  public ISchApp01SOAPProxy() {
    _initISchApp01SOAPProxy();
  }

  public ISchApp01SOAPProxy(String endpoint) {
    _endpoint = endpoint;
    _initISchApp01SOAPProxy();
  }

  private void _initISchApp01SOAPProxy() {
    try {
      iSchApp01SOAP = (new org.tempuri.ISchApp01SOAPserviceLocator()).getISchApp01SOAPPort();
      if (iSchApp01SOAP != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)iSchApp01SOAP)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)iSchApp01SOAP)._getProperty("javax.xml.rpc.service.endpoint.address");
      }

    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }

  public String getEndpoint() {
    return _endpoint;
  }

  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (iSchApp01SOAP != null)
      ((javax.xml.rpc.Stub)iSchApp01SOAP)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);

  }

  public org.tempuri.ISchApp01SOAP getISchApp01SOAP() {
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP;
  }

  public int getSchData4CM(java.lang.String authInf, java.lang.String setsubi, javax.xml.rpc.holders.StringHolder schList) throws java.rmi.RemoteException{
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP.getSchData4CM(authInf, setsubi, schList);
  }

  public int setKaeriData4CM(java.lang.String authInf, java.lang.String setsubi, float inAtsu, float otAtsu, float haba, float XCompe, int rollTime, float insLot, float insPass, float insDisp, java.lang.String cycleKey) throws java.rmi.RemoteException{
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP.setKaeriData4CM(authInf, setsubi, inAtsu, otAtsu, haba, XCompe, rollTime, insLot, insPass, insDisp, cycleKey);
  }

  public int getLotData4CM(java.lang.String authInf, java.lang.String setsubi, java.lang.String cycleKey, javax.xml.rpc.holders.StringHolder lotList) throws java.rmi.RemoteException{
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP.getLotData4CM(authInf, setsubi, cycleKey, lotList);
  }

  public int getTenkenData4CM(java.lang.String authInf, java.lang.String setsubi, javax.xml.rpc.holders.StringHolder tenkenList) throws java.rmi.RemoteException{
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP.getTenkenData4CM(authInf, setsubi, tenkenList);
  }

  public int getPassModData4CM(java.lang.String authInf, java.lang.String lotData, javax.xml.rpc.holders.StringHolder passModList, javax.xml.rpc.holders.IntHolder isCanModify, javax.xml.rpc.holders.IntHolder passCount, javax.xml.rpc.holders.IntHolder passOut, javax.xml.rpc.holders.IntHolder enablePassCount, javax.xml.rpc.holders.IntHolder enablePassOut) throws java.rmi.RemoteException{
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP.getPassModData4CM(authInf, lotData, passModList, isCanModify, passCount, passOut, enablePassCount, enablePassOut);
  }

  public int setPassModData4CM(java.lang.String authInf, java.lang.String setsubi, java.lang.String lotData, java.lang.String passModList, int passCount, int passOut) throws java.rmi.RemoteException{
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP.setPassModData4CM(authInf, setsubi, lotData, passModList, passCount, passOut);
  }

  public int modPassCount4CM(java.lang.String authInf, java.lang.String lotData, java.lang.String passModList, int beforePassCount, int afterPassCount, javax.xml.rpc.holders.StringHolder afterPassModList, javax.xml.rpc.holders.IntHolder passCount, javax.xml.rpc.holders.IntHolder passOut, javax.xml.rpc.holders.IntHolder enablePassCount, javax.xml.rpc.holders.IntHolder enablePassOut) throws java.rmi.RemoteException{
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP.modPassCount4CM(authInf, lotData, passModList, beforePassCount, afterPassCount, afterPassModList, passCount, passOut, enablePassCount, enablePassOut);
  }

  public int modPassOut4CM(java.lang.String authInf, java.lang.String passModList, int afterPassOut, javax.xml.rpc.holders.StringHolder afterPassModList, javax.xml.rpc.holders.IntHolder passOut, javax.xml.rpc.holders.IntHolder enablePassOut) throws java.rmi.RemoteException{
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP.modPassOut4CM(authInf, passModList, afterPassOut, afterPassModList, passOut, enablePassOut);
  }

  public int schDelete(java.lang.String authInf, java.lang.String setsubi, java.lang.String delData) throws java.rmi.RemoteException{
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP.schDelete(authInf, setsubi, delData);
  }

  public int getHinData(java.lang.String authInf, java.lang.String opnLinkKey, javax.xml.rpc.holders.StringHolder hinList) throws java.rmi.RemoteException{
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP.getHinData(authInf, opnLinkKey, hinList);
  }

  public int lotCancel(java.lang.String authInf, java.lang.String cycleKey) throws java.rmi.RemoteException{
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP.lotCancel(authInf, cycleKey);
  }

  public int lotDelete(java.lang.String authInf, java.lang.String delData) throws java.rmi.RemoteException{
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP.lotDelete(authInf, delData);
  }

  public int makeSch4CM(java.lang.String authInf, java.lang.String setsubi, java.lang.String cycleKey) throws java.rmi.RemoteException{
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP.makeSch4CM(authInf, setsubi, cycleKey);
  }

  public int lotMove(java.lang.String authInf, java.lang.String setsubi, java.lang.String cycleKey, java.lang.String moveData, float insNo) throws java.rmi.RemoteException{
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP.lotMove(authInf, setsubi, cycleKey, moveData, insNo);
  }

  public int lotInsCheck(java.lang.String authInf, java.lang.String ltNo, java.lang.String setsubi, javax.xml.rpc.holders.StringHolder lotData) throws java.rmi.RemoteException{
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP.lotInsCheck(authInf, ltNo, setsubi, lotData);
  }

  public int lotInsert(java.lang.String authInf, java.lang.String setsubi, java.lang.String cycleKey, java.lang.String insData, float insNo) throws java.rmi.RemoteException{
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP.lotInsert(authInf, setsubi, cycleKey, insData, insNo);
  }

  public int sendSch4CM3(java.lang.String authInf) throws java.rmi.RemoteException{
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP.sendSch4CM3(authInf);
  }

  public int setTenkenData4CM(java.lang.String authInf, java.lang.String setsubi, float insLot, float insPass, float insDisp, java.lang.String cycleKey, java.lang.String tenkenList) throws java.rmi.RemoteException{
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP.setTenkenData4CM(authInf, setsubi, insLot, insPass, insDisp, cycleKey, tenkenList);
  }

  public int schMove(java.lang.String authInf, java.lang.String setsubi, float insLot, float insPass, float insDisp, java.lang.String cycleKey, java.lang.String moveList, int insKbn) throws java.rmi.RemoteException{
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP.schMove(authInf, setsubi, insLot, insPass, insDisp, cycleKey, moveList, insKbn);
  }

  public int CM_RefreshPassCompe(java.lang.String authInf, java.lang.String setsubi, javax.xml.rpc.holders.StringHolder passModList) throws java.rmi.RemoteException{
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP.CM_RefreshPassCompe(authInf, setsubi, passModList);
  }

  public int CM_ModifyPassData(java.lang.String authInf, java.lang.String setsubi, java.lang.String beforePassData, java.lang.String afterPassData) throws java.rmi.RemoteException{
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP.CM_ModifyPassData(authInf, setsubi, beforePassData, afterPassData);
  }

  public int getAuthInf(java.lang.String IPAddress, javax.xml.rpc.holders.StringHolder userID, javax.xml.rpc.holders.StringHolder termID, javax.xml.rpc.holders.StringHolder setsubi, javax.xml.rpc.holders.StringHolder useKbn, javax.xml.rpc.holders.IntHolder termKbn) throws java.rmi.RemoteException{
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP.getAuthInf(IPAddress, userID, termID, setsubi, useKbn, termKbn);
  }

  public int lotInfCheck(java.lang.String authInf, java.lang.String setsubi, javax.xml.rpc.holders.StringHolder cycleKey, javax.xml.rpc.holders.IntHolder isLotEdit) throws java.rmi.RemoteException{
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP.lotInfCheck(authInf, setsubi, cycleKey, isLotEdit);
  }

  public int lotInfChecks(java.lang.String authInf, java.lang.String selData, javax.xml.rpc.holders.IntHolder isEditing) throws java.rmi.RemoteException{
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP.lotInfChecks(authInf, selData, isEditing);
  }

  public int passInsCheck(java.lang.String authInf, java.lang.String ltNo, java.lang.String setsubi, javax.xml.rpc.holders.StringHolder passData) throws java.rmi.RemoteException{
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP.passInsCheck(authInf, ltNo, setsubi, passData);
  }

  public int passInsert(java.lang.String authInf, java.lang.String setsubi, float insLot, float insPass, float insDisp, java.lang.String cycleKey, java.lang.String insList, int insKbn) throws java.rmi.RemoteException{
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP.passInsert(authInf, setsubi, insLot, insPass, insDisp, cycleKey, insList, insKbn);
  }

  public int getPassOrderData(java.lang.String authInf, java.lang.String setsubi, java.lang.String cycleKey, javax.xml.rpc.holders.StringHolder lotList) throws java.rmi.RemoteException{
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP.getPassOrderData(authInf, setsubi, cycleKey, lotList);
  }

  public int cancelPassOrderData(java.lang.String authInf, java.lang.String cycleKey) throws java.rmi.RemoteException{
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP.cancelPassOrderData(authInf, cycleKey);
  }

  public int modifyPassOrderData(java.lang.String authInf, java.lang.String setsubi, java.lang.String cycleKey, java.lang.String lotList) throws java.rmi.RemoteException{
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP.modifyPassOrderData(authInf, setsubi, cycleKey, lotList);
  }

  public int getUseStatus(java.lang.String authInf, java.lang.String setsubi, javax.xml.rpc.holders.IntHolder enableEndSign, javax.xml.rpc.holders.IntHolder autoRefresh, javax.xml.rpc.holders.StringHolder lastUpdate, javax.xml.rpc.holders.IntHolder manualResult) throws java.rmi.RemoteException{
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP.getUseStatus(authInf, setsubi, enableEndSign, autoRefresh, lastUpdate, manualResult);
  }

  public int setUseStatus(java.lang.String authInf, java.lang.String setsubi, int enableEndSign, int autoRefresh) throws java.rmi.RemoteException{
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP.setUseStatus(authInf, setsubi, enableEndSign, autoRefresh);
  }

  public int getAutoCheck(java.lang.String authInf, java.lang.String setsubi, javax.xml.rpc.holders.IntHolder enableState) throws java.rmi.RemoteException{
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP.getAutoCheck(authInf, setsubi, enableState);
  }

  public int makeSchSplit4CM(java.lang.String authInf, java.lang.String setsubi, int splitNo, java.lang.String cycleKey, java.lang.String lotList) throws java.rmi.RemoteException{
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP.makeSchSplit4CM(authInf, setsubi, splitNo, cycleKey, lotList);
  }

  public int getTeisi(java.lang.String authInf, java.lang.String setsubi, javax.xml.rpc.holders.StringHolder teisiList) throws java.rmi.RemoteException{
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP.getTeisi(authInf, setsubi, teisiList);
  }

  public int setTeisi(java.lang.String authInf, java.lang.String setsubi, float insLot, float insPass, float insDisp, java.lang.String cycleKey, java.lang.String teisiCd, int teisiTime) throws java.rmi.RemoteException{
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP.setTeisi(authInf, setsubi, insLot, insPass, insDisp, cycleKey, teisiCd, teisiTime);
  }

  public int getCycleData(java.lang.String authInf, java.lang.String setsubi, javax.xml.rpc.holders.StringHolder cycleList) throws java.rmi.RemoteException{
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP.getCycleData(authInf, setsubi, cycleList);
  }

  public int getCycleLotData(java.lang.String authInf, java.lang.String cycleKey, javax.xml.rpc.holders.StringHolder lotList) throws java.rmi.RemoteException{
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP.getCycleLotData(authInf, cycleKey, lotList);
  }

  public int cycleMove(java.lang.String authInf, java.lang.String setsubi, float insNo, java.lang.String cycleList) throws java.rmi.RemoteException{
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP.cycleMove(authInf, setsubi, insNo, cycleList);
  }

  public int cycleDelete(java.lang.String authInf, java.lang.String setsubi, java.lang.String cycleList) throws java.rmi.RemoteException{
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP.cycleDelete(authInf, setsubi, cycleList);
  }

  public int autoTeisi4CM(java.lang.String authInf, java.lang.String setsubi) throws java.rmi.RemoteException{
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP.autoTeisi4CM(authInf, setsubi);
  }

  public int getLotDetail(java.lang.String authInf, java.lang.String setLinkKey, float passNo, javax.xml.rpc.holders.StringHolder lotData) throws java.rmi.RemoteException{
    if (iSchApp01SOAP == null)
      _initISchApp01SOAPProxy();
    return iSchApp01SOAP.getLotDetail(authInf, setLinkKey, passNo, lotData);
  }


}