/**
 * ISchApp01SOAP.java
 *
 * このファイルはWSDLから自動生成されました / [en]-(This file was auto-generated from WSDL)
 * Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java生成器によって / [en]-(by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.)
 */

package org.tempuri;

public interface ISchApp01SOAP extends java.rmi.Remote {
    public int getSchData4CM(java.lang.String authInf, java.lang.String setsubi, javax.xml.rpc.holders.StringHolder schList) throws java.rmi.RemoteException;
    public int setKaeriData4CM(java.lang.String authInf, java.lang.String setsubi, float inAtsu, float otAtsu, float haba, float XCompe, int rollTime, float insLot, float insPass, float insDisp, java.lang.String cycleKey) throws java.rmi.RemoteException;
    public int getLotData4CM(java.lang.String authInf, java.lang.String setsubi, java.lang.String cycleKey, javax.xml.rpc.holders.StringHolder lotList) throws java.rmi.RemoteException;
    public int getTenkenData4CM(java.lang.String authInf, java.lang.String setsubi, javax.xml.rpc.holders.StringHolder tenkenList) throws java.rmi.RemoteException;
    public int getPassModData4CM(java.lang.String authInf, java.lang.String lotData, javax.xml.rpc.holders.StringHolder passModList, javax.xml.rpc.holders.IntHolder isCanModify, javax.xml.rpc.holders.IntHolder passCount, javax.xml.rpc.holders.IntHolder passOut, javax.xml.rpc.holders.IntHolder enablePassCount, javax.xml.rpc.holders.IntHolder enablePassOut) throws java.rmi.RemoteException;
    public int setPassModData4CM(java.lang.String authInf, java.lang.String setsubi, java.lang.String lotData, java.lang.String passModList, int passCount, int passOut) throws java.rmi.RemoteException;
    public int modPassCount4CM(java.lang.String authInf, java.lang.String lotData, java.lang.String passModList, int beforePassCount, int afterPassCount, javax.xml.rpc.holders.StringHolder afterPassModList, javax.xml.rpc.holders.IntHolder passCount, javax.xml.rpc.holders.IntHolder passOut, javax.xml.rpc.holders.IntHolder enablePassCount, javax.xml.rpc.holders.IntHolder enablePassOut) throws java.rmi.RemoteException;
    public int modPassOut4CM(java.lang.String authInf, java.lang.String passModList, int afterPassOut, javax.xml.rpc.holders.StringHolder afterPassModList, javax.xml.rpc.holders.IntHolder passOut, javax.xml.rpc.holders.IntHolder enablePassOut) throws java.rmi.RemoteException;
    public int schDelete(java.lang.String authInf, java.lang.String setsubi, java.lang.String delData) throws java.rmi.RemoteException;
    public int getHinData(java.lang.String authInf, java.lang.String opnLinkKey, javax.xml.rpc.holders.StringHolder hinList) throws java.rmi.RemoteException;
    public int lotCancel(java.lang.String authInf, java.lang.String cycleKey) throws java.rmi.RemoteException;
    public int lotDelete(java.lang.String authInf, java.lang.String delData) throws java.rmi.RemoteException;
    public int makeSch4CM(java.lang.String authInf, java.lang.String setsubi, java.lang.String cycleKey) throws java.rmi.RemoteException;
    public int lotMove(java.lang.String authInf, java.lang.String setsubi, java.lang.String cycleKey, java.lang.String moveData, float insNo) throws java.rmi.RemoteException;
    public int lotInsCheck(java.lang.String authInf, java.lang.String ltNo, java.lang.String setsubi, javax.xml.rpc.holders.StringHolder lotData) throws java.rmi.RemoteException;
    public int lotInsert(java.lang.String authInf, java.lang.String setsubi, java.lang.String cycleKey, java.lang.String insData, float insNo) throws java.rmi.RemoteException;
    public int sendSch4CM3(java.lang.String authInf) throws java.rmi.RemoteException;
    public int setTenkenData4CM(java.lang.String authInf, java.lang.String setsubi, float insLot, float insPass, float insDisp, java.lang.String cycleKey, java.lang.String tenkenList) throws java.rmi.RemoteException;
    public int schMove(java.lang.String authInf, java.lang.String setsubi, float insLot, float insPass, float insDisp, java.lang.String cycleKey, java.lang.String moveList, int insKbn) throws java.rmi.RemoteException;
    public int CM_RefreshPassCompe(java.lang.String authInf, java.lang.String setsubi, javax.xml.rpc.holders.StringHolder passModList) throws java.rmi.RemoteException;
    public int CM_ModifyPassData(java.lang.String authInf, java.lang.String setsubi, java.lang.String beforePassData, java.lang.String afterPassData) throws java.rmi.RemoteException;
    public int getAuthInf(java.lang.String IPAddress, javax.xml.rpc.holders.StringHolder userID, javax.xml.rpc.holders.StringHolder termID, javax.xml.rpc.holders.StringHolder setsubi, javax.xml.rpc.holders.StringHolder useKbn, javax.xml.rpc.holders.IntHolder termKbn) throws java.rmi.RemoteException;
    public int lotInfCheck(java.lang.String authInf, java.lang.String satsubi, javax.xml.rpc.holders.StringHolder cycleKey, javax.xml.rpc.holders.IntHolder isLotEdit) throws java.rmi.RemoteException;
    public int lotInfChecks(java.lang.String authInf, java.lang.String selData, javax.xml.rpc.holders.IntHolder isEditing) throws java.rmi.RemoteException;
    public int passInsCheck(java.lang.String authInf, java.lang.String ltNo, java.lang.String setsubi, javax.xml.rpc.holders.StringHolder passData) throws java.rmi.RemoteException;
    public int passInsert(java.lang.String authInf, java.lang.String setsubi, float insLot, float insPass, float insDisp, java.lang.String cycleKey, java.lang.String insList, int insKbn) throws java.rmi.RemoteException;
    public int getPassOrderData(java.lang.String authInf, java.lang.String setsubi, java.lang.String cycleKey, javax.xml.rpc.holders.StringHolder lotList) throws java.rmi.RemoteException;
    public int cancelPassOrderData(java.lang.String authInf, java.lang.String cycleKey) throws java.rmi.RemoteException;
    public int modifyPassOrderData(java.lang.String authInf, java.lang.String setsubi, java.lang.String cycleKey, java.lang.String lotList) throws java.rmi.RemoteException;
    public int getUseStatus(java.lang.String authInf, java.lang.String setsubi, javax.xml.rpc.holders.IntHolder enableEndSign, javax.xml.rpc.holders.IntHolder autoRefresh, javax.xml.rpc.holders.StringHolder lastUpdate, javax.xml.rpc.holders.IntHolder manualResult) throws java.rmi.RemoteException;
    public int setUseStatus(java.lang.String authInf, java.lang.String setsubi, int enableEndSign, int autoRefresh) throws java.rmi.RemoteException;
    public int getAutoCheck(java.lang.String authInf, java.lang.String setsubi, javax.xml.rpc.holders.IntHolder enableState) throws java.rmi.RemoteException;
    public int makeSchSplit4CM(java.lang.String authInf, java.lang.String setsubi, int splitNo, java.lang.String cycleKey, java.lang.String lotList) throws java.rmi.RemoteException;
    public int getTeisi(java.lang.String authInf, java.lang.String setsubi, javax.xml.rpc.holders.StringHolder teisiList) throws java.rmi.RemoteException;
    public int setTeisi(java.lang.String authInf, java.lang.String setsubi, float insLot, float insPass, float insDisp, java.lang.String cycleKey, java.lang.String teisiCd, int teisiTime) throws java.rmi.RemoteException;
    public int getCycleData(java.lang.String authInf, java.lang.String setsubi, javax.xml.rpc.holders.StringHolder cycleList) throws java.rmi.RemoteException;
    public int getCycleLotData(java.lang.String authInf, java.lang.String cycleKey, javax.xml.rpc.holders.StringHolder lotList) throws java.rmi.RemoteException;
    public int cycleMove(java.lang.String authInf, java.lang.String setsubi, float insNo, java.lang.String cycleList) throws java.rmi.RemoteException;
    public int cycleDelete(java.lang.String authInf, java.lang.String setsubi, java.lang.String cycleList) throws java.rmi.RemoteException;
    public int autoTeisi4CM(java.lang.String authInf, java.lang.String setsubi) throws java.rmi.RemoteException;
    public int getLotDetail(java.lang.String authInf, java.lang.String setLinkKey, float passNo, javax.xml.rpc.holders.StringHolder lotData) throws java.rmi.RemoteException;
}
