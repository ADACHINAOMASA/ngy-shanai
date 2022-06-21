package ngyshanai.domain.soapclient;

public class IcasAppSoapClient {
	
	private org.tempuri.IIcasApp01SOAPProxy iicasapp01soap = null;

	/*
	private javax.xml.rpc.holders.StringHolder userID = new javax.xml.rpc.holders.StringHolder();
	private javax.xml.rpc.holders.StringHolder termID = new javax.xml.rpc.holders.StringHolder();
	private javax.xml.rpc.holders.StringHolder setsubiName = new javax.xml.rpc.holders.StringHolder();
	private javax.xml.rpc.holders.StringHolder useKbn = new javax.xml.rpc.holders.StringHolder();
	private javax.xml.rpc.holders.IntHolder termKbn = new javax.xml.rpc.holders.IntHolder();
	*/
	
	private String termID;
	private String userID;
	private String useKbn;
	
	public IcasAppSoapClient(String icasTarget) {
		this.iicasapp01soap = new org.tempuri.IIcasApp01SOAPProxy(icasTarget);
	}
    
	public String getAuthInfString() {
        return termID + "," + userID + "," + useKbn;
    }
    
	/**
	  * IIcasApp01SOAP関数
	  * WNC06045 2007.11.23 追加/修正
	  */
	 public int setKaishi4CM(java.lang.String authInf, java.lang.String setsubi) throws java.rmi.RemoteException{
	   return iicasapp01soap.setKaishi4CM(authInf, setsubi);
	 }
	
	 public int getLotJisseki4CM(java.lang.String authInf, java.lang.String setsubi, javax.xml.rpc.holders.StringHolder jskData) throws java.rmi.RemoteException{
	   return iicasapp01soap.getLotJisseki4CM(authInf, setsubi, jskData);
	 }
	
	 public int setLotJisseki4CM(java.lang.String authInf, java.lang.String setsubi, java.lang.String jskData) throws java.rmi.RemoteException{
	     return iicasapp01soap.setLotJisseki4CM(authInf, setsubi, jskData);
	 }  
	 
	 public int teisiContinue(java.lang.String authInf, java.lang.String setsubi) throws java.rmi.RemoteException{
	   return iicasapp01soap.teisiContinue(authInf, setsubi);
	 }
	
	 public int getJissekiKind4CM(java.lang.String authInf, java.lang.String setsubi, javax.xml.rpc.holders.IntHolder jskKind) throws java.rmi.RemoteException{
	   return iicasapp01soap.getJissekiKind4CM(authInf, setsubi, jskKind);
	 }
	
	 public int getTeisiJisseki4CM(java.lang.String authInf, java.lang.String setsubi, javax.xml.rpc.holders.StringHolder jskData) throws java.rmi.RemoteException{
	   return iicasapp01soap.getTeisiJisseki4CM(authInf, setsubi, jskData);
	 }
	
	 public int setTeisiJisseki4CM(java.lang.String authInf, java.lang.String setsubi, java.lang.String jskData, int contKbn) throws java.rmi.RemoteException{
	   return iicasapp01soap.setTeisiJisseki4CM(authInf, setsubi, jskData, contKbn);
	 }
	
	 public int getOperatorData(java.lang.String authInf, java.lang.String setsubi, java.lang.String mainOp, java.lang.String subOp, javax.xml.rpc.holders.StringHolder mainOpName, javax.xml.rpc.holders.StringHolder subOpName) throws java.rmi.RemoteException{
	   return iicasapp01soap.getOperatorData(authInf, setsubi, mainOp, subOp, mainOpName, subOpName);
	 }
	
	 public int setOperatorData(java.lang.String authInf, java.lang.String setsubi, java.lang.String mainOp, java.lang.String subOp) throws java.rmi.RemoteException{
	   return iicasapp01soap.setOperatorData(authInf, setsubi, mainOp, subOp);
	 }
	
	 public int setRollData(java.lang.String authInf, java.lang.String setsubi, java.lang.String WRUNo, java.lang.String WRDNo, java.lang.String BRUNo, java.lang.String BRDNo) throws java.rmi.RemoteException{
	   return iicasapp01soap.setRollData(authInf, setsubi, WRUNo, WRDNo, BRUNo, BRDNo);
	 } 
	
	
}
