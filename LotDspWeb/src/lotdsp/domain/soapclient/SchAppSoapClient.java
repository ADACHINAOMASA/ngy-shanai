package lotdsp.domain.soapclient;

import java.rmi.RemoteException;

import lotdsp.common.lotdsp.AuthenticationInfo;
import lotdsp.common.lotdsp.ErrorInfo;

public class SchAppSoapClient {

	private org.tempuri.ISchApp01SOAPProxy ischapp01soap = null;

	public SchAppSoapClient(String schTarget) {
		ischapp01soap = new org.tempuri.ISchApp01SOAPProxy(schTarget);
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

    public int lotInfCheck(java.lang.String authInf, java.lang.String setsubi, javax.xml.rpc.holders.StringHolder cycleKey, javax.xml.rpc.holders.IntHolder isLotEdit) throws java.rmi.RemoteException{
      return ischapp01soap.lotInfCheck(authInf, setsubi, cycleKey, isLotEdit);
    }

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

    //----------------------------------------------------------------------------------------------------------------------------------

	public AuthenticationInfo auth(String ip) {
		AuthenticationInfo authenInfo = new AuthenticationInfo();
		authenInfo.setIp(ip);

		javax.xml.rpc.holders.StringHolder userID = new javax.xml.rpc.holders.StringHolder();
        javax.xml.rpc.holders.StringHolder termID = new javax.xml.rpc.holders.StringHolder();
        javax.xml.rpc.holders.StringHolder setsubiName = new javax.xml.rpc.holders.StringHolder();
        javax.xml.rpc.holders.StringHolder useKbn = new javax.xml.rpc.holders.StringHolder();
        javax.xml.rpc.holders.IntHolder termKbn = new javax.xml.rpc.holders.IntHolder();

        String errorMsg = "マシン名とコンテキスト・ルートが取得できませんでした。";
		try {
			authenInfo.setError(false);

			//IPアドレスによる認証を行う
			int result = getAuthInf(ip, userID, termID, setsubiName, useKbn, termKbn);
			authenInfo.setResult(result);

			ErrorInfo errorInfo = new ErrorInfo();
			if (!SoapResultChecker.check(result, setsubiName.value, errorInfo)) {
				authenInfo.setError(true);
				authenInfo.setErrorMessage(errorMsg);
			}
			//認証が成功の場合
			else {
			}

		} catch (RemoteException e) {
			e.printStackTrace();
			authenInfo.setError(true);
			authenInfo.setErrorMessage(errorMsg);
		}

		return authenInfo;
	}

}
