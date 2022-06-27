/**
 * ISchApp01SOAPserviceLocator.java
 *
 * このファイルはWSDLから自動生成されました / [en]-(This file was auto-generated from WSDL)
 * Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java生成器によって / [en]-(by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.)
 */

package org.tempuri;

public class ISchApp01SOAPserviceLocator extends org.apache.axis.client.Service implements org.tempuri.ISchApp01SOAPservice {

    public ISchApp01SOAPserviceLocator() {
    }


    public ISchApp01SOAPserviceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ISchApp01SOAPserviceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // ISchApp01SOAPPortのプロキシクラスの取得に使用します / [en]-(Use to get CMSchViewInfo proxy class for ISchApp01SOAPPort)
    private java.lang.String ISchApp01SOAPPort_address = "http://nlmfangydev3:10080/soap/SchApp01SOAP.dll/soap/ISchApp01SOAP";

    public java.lang.String getISchApp01SOAPPortAddress() {
        return ISchApp01SOAPPort_address;
    }

    // WSDDサービス名のデフォルトはポート名です / [en]-(The WSDD service name defaults to the port name.)
    private java.lang.String ISchApp01SOAPPortWSDDServiceName = "ISchApp01SOAPPort";

    public java.lang.String getISchApp01SOAPPortWSDDServiceName() {
        return ISchApp01SOAPPortWSDDServiceName;
    }

    public void setISchApp01SOAPPortWSDDServiceName(java.lang.String name) {
        ISchApp01SOAPPortWSDDServiceName = name;
    }

    public org.tempuri.ISchApp01SOAP getISchApp01SOAPPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ISchApp01SOAPPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getISchApp01SOAPPort(endpoint);
    }

    public org.tempuri.ISchApp01SOAP getISchApp01SOAPPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.tempuri.ISchApp01SOAPbindingStub _stub = new org.tempuri.ISchApp01SOAPbindingStub(portAddress, this);
            _stub.setPortName(getISchApp01SOAPPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setISchApp01SOAPPortEndpointAddress(java.lang.String address) {
        ISchApp01SOAPPort_address = address;
    }

    /**
     * 与えられたインターフェースに対して、スタブの実装を取得します。 / [en]-(For the given interface, get the stub implementation.)
     * このサービスが与えられたインターフェースに対してポートを持たない場合、 / [en]-(If this service has no port for the given interface,)
     * ServiceExceptionが投げられます。 / [en]-(then ServiceException is thrown.)
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.tempuri.ISchApp01SOAP.class.isAssignableFrom(serviceEndpointInterface)) {
                org.tempuri.ISchApp01SOAPbindingStub _stub = new org.tempuri.ISchApp01SOAPbindingStub(new java.net.URL(ISchApp01SOAPPort_address), this);
                _stub.setPortName(getISchApp01SOAPPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("インターフェースに対するスタブの実装がありません: / [en]-(There is no stub implementation for the interface:)  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * 与えられたインターフェースに対して、スタブの実装を取得します。 / [en]-(For the given interface, get the stub implementation.)
     * このサービスが与えられたインターフェースに対してポートを持たない場合、 / [en]-(If this service has no port for the given interface,)
     * ServiceExceptionが投げられます。 / [en]-(then ServiceException is thrown.)
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ISchApp01SOAPPort".equals(inputPortName)) {
            return getISchApp01SOAPPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "ISchApp01SOAPservice");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "ISchApp01SOAPPort"));
        }
        return ports.iterator();
    }

    /**
    * 指定したポート名に対するエンドポイントのアドレスをセットします / [en]-(Set the endpoint address for the specified port name.)
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ISchApp01SOAPPort".equals(portName)) {
            setISchApp01SOAPPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" 未知のポートに対してはエンドポイントのアドレスをセットできません / [en]-(Cannot set Endpoint Address for Unknown Port)" + portName);
        }
    }

    /**
    * 指定したポート名に対するエンドポイントのアドレスをセットします / [en]-(Set the endpoint address for the specified port name.)
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
