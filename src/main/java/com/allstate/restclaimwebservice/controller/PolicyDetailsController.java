package com.allstate.restclaimwebservice.controller;

import com.allstate.claim.GetPolicyDetailsRequest;
import com.allstate.claim.GetPolicyDetailsResponse;
import com.allstate.claim.ObjectFactory;
import com.allstate.claim.PolicyDetails;
import com.allstate.restclaimwebservice.client.SoapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class PolicyDetailsController{

    @Autowired
    private SoapClient soapClient;
    @GetMapping(value = "policy/{claimNumber}")
    public PolicyDetails getPolicyDetails(@PathVariable int claimNumber){
        ObjectFactory objectFactory = new ObjectFactory();
        GetPolicyDetailsRequest policyDetailsRequest = new GetPolicyDetailsRequest();
        policyDetailsRequest.setClaimNumber(claimNumber);
        GetPolicyDetailsResponse response = soapClient.getPolicyDetails("http://localhost:8081/ws/claim",
                objectFactory.createPolicyDetails(claimNumber));
        return response.getPolicyDetails();
    }

    }




