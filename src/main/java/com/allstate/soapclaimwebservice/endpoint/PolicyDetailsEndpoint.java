package com.allstate.soapclaimwebservice.endpoint;

import com.allstate.soapclaimwebservice.bean.Policy;
import com.allstate.soapclaimwebservice.service.PolicyDetailsService;
import com.allstate.claim.GetPolicyDetailsRequest;
import com.allstate.claim.GetPolicyDetailsResponse;
import com.allstate.claim.PolicyDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class PolicyDetailsEndpoint {

    @Autowired
    PolicyDetailsService service;

    @PayloadRoot(namespace = "http://allstate.com/claim", localPart = "GetPolicyDetailsRequest")
    @ResponsePayload
    public GetPolicyDetailsResponse processPolicyDetailsRequest(@RequestPayload GetPolicyDetailsRequest request){
        GetPolicyDetailsResponse response = new GetPolicyDetailsResponse();
        Policy policy = service.findByClaimNumber(request.getClaimNumber());
//        policy.setPolicyNumber(1);
//        policy.setHolderName("Practice");
//        policy.setCoverageLimit(400000);

        return mapPolicyDetails(policy);
    }

    private GetPolicyDetailsResponse mapPolicyDetails(Policy policy) {
        GetPolicyDetailsResponse response = new GetPolicyDetailsResponse();
        response.setPolicyDetails(mapPolicy(policy));
        return response;
    }
    private PolicyDetails mapPolicy(Policy policy) {
        PolicyDetails details = new PolicyDetails();

        details.setPolicyNumber(policy.getPolicyNumber());

        details.setCoverageLimit(policy.getCoverageLimit());
        details.setHolderName(policy.getHolderName());
        details.setDeductible(policy.getDeductible());
        details.setCoverageName(policy.getCoverageName());

        return details;
    }
}
