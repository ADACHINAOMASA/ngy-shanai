/**
 * ISchApp01SOAPservice.java
 *
 * このファイルはWSDLから自動生成されました / [en]-(This file was auto-generated from WSDL)
 * Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java生成器によって / [en]-(by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.)
 */

package org.tempuri;

public interface ISchApp01SOAPservice extends javax.xml.rpc.Service {
    public java.lang.String getISchApp01SOAPPortAddress();

    public org.tempuri.ISchApp01SOAP getISchApp01SOAPPort() throws javax.xml.rpc.ServiceException;

    public org.tempuri.ISchApp01SOAP getISchApp01SOAPPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
