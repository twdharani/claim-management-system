package com.allstate.soapclaimwebservice.endpoint;

import com.allstate.claim.GetPolicyDetailsRequest;
import com.allstate.claim.GetPolicyDetailsResponse;
import com.allstate.claim.PolicyDetails;
import com.allstate.soapclaimwebservice.bean.Policy;
import com.allstate.soapclaimwebservice.exception.InvalidClaimNumberException;
import com.allstate.soapclaimwebservice.service.PolicyDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class PolicyDetailsEndpoint {

    @Autowired
    PolicyDetailsService service;

    private static void validateClaimNumber(GetPolicyDetailsRequest request) {
        if (request.getClaimNumber() == 0) {
            throw new InvalidClaimNumberException("Invalid Claim Number: please provide valid number");
        }
    }

    @PayloadRoot(namespace = "http://allstate.com/claim", localPart = "GetPolicyDetailsRequest")
    @ResponsePayload
    public GetPolicyDetailsResponse processPolicyDetailsRequest(@RequestPayload GetPolicyDetailsRequest request) throws InvalidClaimNumberException {
        validateClaimNumber(request);
        Policy policy = service.findByClaimNumber(request.getClaimNumber());

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
