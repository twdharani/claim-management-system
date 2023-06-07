package com.allstate.soapclaimwebservice.service;

import com.allstate.soapclaimwebservice.bean.Claim;
import com.allstate.soapclaimwebservice.bean.Policy;
import com.allstate.soapclaimwebservice.exception.PolicyNotFoundException;
import com.allstate.soapclaimwebservice.sender.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PolicyDetailsService {

    @Autowired
    public MessageSender sender;

    private static List<Policy> policyDetailsList = new ArrayList<>();
    private static List<Claim> claimList = new ArrayList<>();

    static {
        Policy policyDetails1 = new Policy("Aads", 1, "Health", 40000, 2000);
        policyDetailsList.add(policyDetails1);

        Policy policyDetails2 = new Policy("Tw", 2, "Health", 80000, 4000);
        policyDetailsList.add(policyDetails2);

        Claim claim1 = new Claim(12, 1);
        claimList.add(claim1);

        Claim claim2 = new Claim(13, 2);
        claimList.add(claim2);
    }

    public Policy findByClaimNumber(int claimNumber) throws PolicyNotFoundException {
        Policy policy = null;
        for (Claim claim : claimList) {
            if (claim.getClaimNumber() == claimNumber) {
                int policyNumber = claim.getPolicyNumber();
                policy = getPolicyDetails(policyNumber);
            }
        }
        if (policy == null)
            throw new PolicyNotFoundException("Claim Number does not exist " + claimNumber);

        sender.sendPolicyDetails(policy);
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
