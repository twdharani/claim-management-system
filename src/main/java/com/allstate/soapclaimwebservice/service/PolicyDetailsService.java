package com.allstate.soapclaimwebservice.service;

import com.allstate.soapclaimwebservice.bean.Claim;
import com.allstate.soapclaimwebservice.bean.Policy;
import com.allstate.soapclaimwebservice.exception.PolicyNotFoundException;
import com.allstate.soapclaimwebservice.sender.PolicyDetailsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.allstate.soapclaimwebservice.constants.Constants.CLAIM_NUMBER_DOES_NOT_EXIST;

@Component
public class PolicyDetailsService {

    private static List<Policy> policyDetailsList = new ArrayList<>();
    private static List<Claim> claimList = new ArrayList<>();

    static {
        Policy policyDetails1 = new Policy("HolderOne", 123456789, "Health", 20000, 2000);
        policyDetailsList.add(policyDetails1);

        Policy policyDetails2 = new Policy("HolderTwo", 2, "Auto", 80000, 4000);
        policyDetailsList.add(policyDetails2);

        Claim claim1 = new Claim(12, 123456789);
        claimList.add(claim1);

        Claim claim2 = new Claim(13, 2);
        claimList.add(claim2);
    }


    @Autowired
    public PolicyDetailsSender policyDetailsSender;

    public Policy findByClaimNumber(int claimNumber) throws PolicyNotFoundException {
        Policy policy = null;
        for (Claim claim : claimList) {
            if (claim.getClaimNumber() == claimNumber) {
                int policyNumber = claim.getPolicyNumber();
                policy = getPolicyDetails(policyNumber);
            }
        }
        if (policy == null)
            throw new PolicyNotFoundException(CLAIM_NUMBER_DOES_NOT_EXIST + claimNumber);

        policyDetailsSender.sendPolicyDetails(policy);
        return policy;
    }

    private Policy getPolicyDetails(int policyNumber) {
        for (Policy policyDetails : policyDetailsList) {
            if (policyDetails.getPolicyNumber() == policyNumber) {
                return policyDetails;
            }
        }
        return null;
    }
}
