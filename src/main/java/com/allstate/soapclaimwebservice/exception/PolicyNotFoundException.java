package com.allstate.soapclaimwebservice.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CUSTOM, customFaultCode = "{http://allstate.com/claim}001_POLICY_NOT_FOUND")
public class PolicyNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 3518170101751491969L;

    public PolicyNotFoundException(String message) {
        super(message);
    }
}