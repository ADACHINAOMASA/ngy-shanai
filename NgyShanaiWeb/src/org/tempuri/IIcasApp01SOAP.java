/**
 * IIcasApp01SOAP.java
 *
 * このファイルはWSDLから自動生成されました / [en]-(This file was auto-generated from WSDL)
 * Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java生成器によって / [en]-(by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.)
 */

package org.tempuri;

public interface IIcasApp01SOAP extends java.rmi.Remote {
    public int setKaishi4CM(java.lang.String authInf, java.lang.String setsubi) throws java.rmi.RemoteException;
    public int getLotJisseki4CM(java.lang.String authInf, java.lang.String setsubi, javax.xml.rpc.holders.StringHolder jskData) throws java.rmi.RemoteException;
    public int setLotJisseki4CM(java.lang.String authInf, java.lang.String setsubi, java.lang.String jskData) throws java.rmi.RemoteException;
    public int teisiContinue(java.lang.String authInf, java.lang.String setsubi) throws java.rmi.RemoteException;
    public int getJissekiKind4CM(java.lang.String authInf, java.lang.String setsubi, javax.xml.rpc.holders.IntHolder jskKind) throws java.rmi.RemoteException;
    public int getTeisiJisseki4CM(java.lang.String authInf, java.lang.String setsubi, javax.xml.rpc.holders.StringHolder jskData) throws java.rmi.RemoteException;
    public int setTeisiJisseki4CM(java.lang.String authInf, java.lang.String setsubi, java.lang.String jskData, int contKbn) throws java.rmi.RemoteException;
    public int getOperatorData(java.lang.String authInf, java.lang.String setsubi, java.lang.String mainOp, java.lang.String subOp, javax.xml.rpc.holders.StringHolder mainOpName, javax.xml.rpc.holders.StringHolder subOpName) throws java.rmi.RemoteException;
    public int setOperatorData(java.lang.String authInf, java.lang.String setsubi, java.lang.String mainOp, java.lang.String subOp) throws java.rmi.RemoteException;
    public int setRollData(java.lang.String authInf, java.lang.String setsubi, java.lang.String WRUNo, java.lang.String WRDNo, java.lang.String BRUNo, java.lang.String BRDNo) throws java.rmi.RemoteException;
}
