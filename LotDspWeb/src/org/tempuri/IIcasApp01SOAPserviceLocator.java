/**
 * IIcasApp01SOAPserviceLocator.java
 *
 * このファイルはWSDLから自動生成されました / [en]-(This file was auto-generated from WSDL)
 * Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java生成器によって / [en]-(by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.)
 */

package org.tempuri;

public class IIcasApp01SOAPserviceLocator extends org.apache.axis.client.Service implements org.tempuri.IIcasApp01SOAPservice {

    public IIcasApp01SOAPserviceLocator() {
    }


    public IIcasApp01SOAPserviceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public IIcasApp01SOAPserviceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // IIcasApp01SOAPPortのプロキシクラスの取得に使用します / [en]-(Use to get IPAuthenticateLogic proxy class for IIcasApp01SOAPPort)
    private java.lang.String IIcasApp01SOAPPort_address = "http://nlmfangydev3:10080/soap/IcasApp01SOAP.dll/soap/IIcasApp01SOAP";

    public java.lang.String getIIcasApp01SOAPPortAddress() {
        return IIcasApp01SOAPPort_address;
    }

    // WSDDサービス名のデフォルトはポート名です / [en]-(The WSDD service name defaults to the port name.)
    private java.lang.String IIcasApp01SOAPPortWSDDServiceName = "IIcasApp01SOAPPort";

    public java.lang.String getIIcasApp01SOAPPortWSDDServiceName() {
        return IIcasApp01SOAPPortWSDDServiceName;
    }

    public void setIIcasApp01SOAPPortWSDDServiceName(java.lang.String name) {
        IIcasApp01SOAPPortWSDDServiceName = name;
    }

    public org.tempuri.IIcasApp01SOAP getIIcasApp01SOAPPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(IIcasApp01SOAPPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getIIcasApp01SOAPPort(endpoint);
    }

    public org.tempuri.IIcasApp01SOAP getIIcasApp01SOAPPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.tempuri.IIcasApp01SOAPbindingStub _stub = new org.tempuri.IIcasApp01SOAPbindingStub(portAddress, this);
            _stub.setPortName(getIIcasApp01SOAPPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setIIcasApp01SOAPPortEndpointAddress(java.lang.String address) {
        IIcasApp01SOAPPort_address = address;
    }

    /**
     * 与えられたインターフェースに対して、スタブの実装を取得します。 / [en]-(For the given interface, get the stub implementation.)
     * このサービスが与えられたインターフェースに対してポートを持たない場合、 / [en]-(If this service has no port for the given interface,)
     * ServiceExceptionが投げられます。 / [en]-(then ServiceException is thrown.)
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.tempuri.IIcasApp01SOAP.class.isAssignableFrom(serviceEndpointInterface)) {
                org.tempuri.IIcasApp01SOAPbindingStub _stub = new org.tempuri.IIcasApp01SOAPbindingStub(new java.net.URL(IIcasApp01SOAPPort_address), this);
                _stub.setPortName(getIIcasApp01SOAPPortWSDDServiceName());
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
        if ("IIcasApp01SOAPPort".equals(inputPortName)) {
            return getIIcasApp01SOAPPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "IIcasApp01SOAPservice");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "IIcasApp01SOAPPort"));
        }
        return ports.iterator();
    }

    /**
    * 指定したポート名に対するエンドポイントのアドレスをセットします / [en]-(Set the endpoint address for the specified port name.)
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("IIcasApp01SOAPPort".equals(portName)) {
            setIIcasApp01SOAPPortEndpointAddress(address);
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
