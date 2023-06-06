package com.allstate.soapclaimwebservice.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CUSTOM, customFaultCode = "{http://allstate.com/claim}002_INVALID_CLAIM_NUMBER")
public class InvalidClaimNumberException extends RuntimeException {

    public InvalidClaimNumberException(String message) {
        super(message);
    }
}
