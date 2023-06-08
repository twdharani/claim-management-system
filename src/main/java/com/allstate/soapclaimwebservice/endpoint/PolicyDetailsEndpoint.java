package com.allstate.soapclaimwebservice.endpoint;


import com.allstate.soapclaimwebservice.bean.Policy;
import com.allstate.soapclaimwebservice.exception.InvalidClaimNumberException;
import com.allstate.soapclaimwebservice.model.GetPolicyDetailsRequest;
import com.allstate.soapclaimwebservice.model.GetPolicyDetailsResponse;
import com.allstate.soapclaimwebservice.model.PolicyDetails;
import com.allstate.soapclaimwebservice.service.PolicyDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import static com.allstate.soapclaimwebservice.constants.Constants.INVALID_CLAIM_NUMBER_ERROR_MESSAGE;

@Endpoint
public class PolicyDetailsEndpoint {

    @Autowired
    PolicyDetailsService service;

    private static void validateClaimNumber(GetPolicyDetailsRequest request) {
        if (request.getClaimNumber() == 0) {
            throw new InvalidClaimNumberException(INVALID_CLAIM_NUMBER_ERROR_MESSAGE);
        }
    }

    @PayloadRoot(namespace = "http://allstate.com/soapclaimwebservice/model", localPart = "GetPolicyDetailsRequest")
    @ResponsePayload
    public GetPolicyDetailsResponse getPolicyDetails(@RequestPayload GetPolicyDetailsRequest request) throws InvalidClaimNumberException {
        validateClaimNumber(request);
        Policy policy = service.findByClaimNumber(request.getClaimNumber());

        return convertToPolicyDetails(policy);
    }

    private GetPolicyDetailsResponse convertToPolicyDetails(Policy policy) {
        GetPolicyDetailsResponse response = new GetPolicyDetailsResponse();
        PolicyDetails policyDetails = setXmlPolicyDetails(policy);
        response.setPolicyDetails(policyDetails);
        return response;
    }

    private PolicyDetails setXmlPolicyDetails(Policy policy) {
        PolicyDetails details = new PolicyDetails();

        details.setPolicyNumber(policy.getPolicyNumber());
        details.setCoverageLimit(policy.getCoverageLimit());
        details.setHolderName(policy.getHolderName());
        details.setDeductible(policy.getDeductible());
        details.setCoverageName(policy.getCoverageName());

        return details;
    }
}
