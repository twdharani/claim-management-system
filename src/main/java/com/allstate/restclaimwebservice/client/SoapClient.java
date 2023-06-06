package com.allstate.restclaimwebservice.client;

import com.allstate.claim.GetPolicyDetailsRequest;
import com.allstate.claim.GetPolicyDetailsResponse;
import com.allstate.claim.PolicyDetails;
import com.allstate.soapclaimwebservice.bean.Policy;
import jakarta.xml.bind.JAXBElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;


@Component
public class SoapClient extends WebServiceGatewaySupport {
    public GetPolicyDetailsResponse getPolicyDetails(String url, Object request){
        JAXBElement res = (JAXBElement) getWebServiceTemplate().marshalSendAndReceive(url, request);
        return (GetPolicyDetailsResponse) res.getValue();
    }
}
