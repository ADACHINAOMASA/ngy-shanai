package org.tempuri;

public class IIcasApp01SOAPProxy implements org.tempuri.IIcasApp01SOAP {
  private String _endpoint = null;
  private org.tempuri.IIcasApp01SOAP iIcasApp01SOAP = null;
  
  public IIcasApp01SOAPProxy() {
    _initIIcasApp01SOAPProxy();
  }
  
  public IIcasApp01SOAPProxy(String endpoint) {
    _endpoint = endpoint;
    _initIIcasApp01SOAPProxy();
  }
  
  private void _initIIcasApp01SOAPProxy() {
    try {
      iIcasApp01SOAP = (new org.tempuri.IIcasApp01SOAPserviceLocator()).getIIcasApp01SOAPPort();
      if (iIcasApp01SOAP != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)iIcasApp01SOAP)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)iIcasApp01SOAP)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (iIcasApp01SOAP != null)
      ((javax.xml.rpc.Stub)iIcasApp01SOAP)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.tempuri.IIcasApp01SOAP getIIcasApp01SOAP() {
    if (iIcasApp01SOAP == null)
      _initIIcasApp01SOAPProxy();
    return iIcasApp01SOAP;
  }
  
  public int setKaishi4CM(java.lang.String authInf, java.lang.String setsubi) throws java.rmi.RemoteException{
    if (iIcasApp01SOAP == null)
      _initIIcasApp01SOAPProxy();
    return iIcasApp01SOAP.setKaishi4CM(authInf, setsubi);
  }
  
  public int getLotJisseki4CM(java.lang.String authInf, java.lang.String setsubi, javax.xml.rpc.holders.StringHolder jskData) throws java.rmi.RemoteException{
    if (iIcasApp01SOAP == null)
      _initIIcasApp01SOAPProxy();
    return iIcasApp01SOAP.getLotJisseki4CM(authInf, setsubi, jskData);
  }
  
  public int setLotJisseki4CM(java.lang.String authInf, java.lang.String setsubi, java.lang.String jskData) throws java.rmi.RemoteException{
    if (iIcasApp01SOAP == null)
      _initIIcasApp01SOAPProxy();
    return iIcasApp01SOAP.setLotJisseki4CM(authInf, setsubi, jskData);
  }
  
  public int teisiContinue(java.lang.String authInf, java.lang.String setsubi) throws java.rmi.RemoteException{
    if (iIcasApp01SOAP == null)
      _initIIcasApp01SOAPProxy();
    return iIcasApp01SOAP.teisiContinue(authInf, setsubi);
  }
  
  public int getJissekiKind4CM(java.lang.String authInf, java.lang.String setsubi, javax.xml.rpc.holders.IntHolder jskKind) throws java.rmi.RemoteException{
    if (iIcasApp01SOAP == null)
      _initIIcasApp01SOAPProxy();
    return iIcasApp01SOAP.getJissekiKind4CM(authInf, setsubi, jskKind);
  }
  
  public int getTeisiJisseki4CM(java.lang.String authInf, java.lang.String setsubi, javax.xml.rpc.holders.StringHolder jskData) throws java.rmi.RemoteException{
    if (iIcasApp01SOAP == null)
      _initIIcasApp01SOAPProxy();
    return iIcasApp01SOAP.getTeisiJisseki4CM(authInf, setsubi, jskData);
  }
  
  public int setTeisiJisseki4CM(java.lang.String authInf, java.lang.String setsubi, java.lang.String jskData, int contKbn) throws java.rmi.RemoteException{
    if (iIcasApp01SOAP == null)
      _initIIcasApp01SOAPProxy();
    return iIcasApp01SOAP.setTeisiJisseki4CM(authInf, setsubi, jskData, contKbn);
  }
  
  public int getOperatorData(java.lang.String authInf, java.lang.String setsubi, java.lang.String mainOp, java.lang.String subOp, javax.xml.rpc.holders.StringHolder mainOpName, javax.xml.rpc.holders.StringHolder subOpName) throws java.rmi.RemoteException{
    if (iIcasApp01SOAP == null)
      _initIIcasApp01SOAPProxy();
    return iIcasApp01SOAP.getOperatorData(authInf, setsubi, mainOp, subOp, mainOpName, subOpName);
  }
  
  public int setOperatorData(java.lang.String authInf, java.lang.String setsubi, java.lang.String mainOp, java.lang.String subOp) throws java.rmi.RemoteException{
    if (iIcasApp01SOAP == null)
      _initIIcasApp01SOAPProxy();
    return iIcasApp01SOAP.setOperatorData(authInf, setsubi, mainOp, subOp);
  }
  
  public int setRollData(java.lang.String authInf, java.lang.String setsubi, java.lang.String WRUNo, java.lang.String WRDNo, java.lang.String BRUNo, java.lang.String BRDNo) throws java.rmi.RemoteException{
    if (iIcasApp01SOAP == null)
      _initIIcasApp01SOAPProxy();
    return iIcasApp01SOAP.setRollData(authInf, setsubi, WRUNo, WRDNo, BRUNo, BRDNo);
  }
  
  
}