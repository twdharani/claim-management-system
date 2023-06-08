package com.allstate.soapclaimwebservice.service

import com.allstate.soapclaimwebservice.bean.Claim
import com.allstate.soapclaimwebservice.bean.Policy
import com.allstate.soapclaimwebservice.exception.PolicyNotFoundException
import com.allstate.soapclaimwebservice.sender.PolicyDetailsSender
import spock.lang.Specification

class PolicyDetailsServiceSpec extends Specification {

    PolicyDetailsService service = new PolicyDetailsService()

    def setup() {
        service.claimList = [
                new Claim(1, 12),
                new Claim(2, 13)
        ]
        service.policyDetailsList = [
                new Policy("HolderOne", 12, "Health", 20000, 2000),
                new Policy("HolderTwo", 13, "Auto", 80000, 4000)
        ]
    }

    def "should return the policy details when a valid claim number is provided"() {
        given:
        def mockPolicyDetailsSender = Mock(PolicyDetailsSender)
        service.policyDetailsSender = mockPolicyDetailsSender

        and:
        def claimNumber = 1

        when:
        Policy result = service.findByClaimNumber(claimNumber)

        then:
        result != null
        result.policyNumber == 12
        result.coverageName == "Health"
    }

    def "should throw PolicyNotFoundException when an invalid claim number is provided"() {
        given:
        PolicyDetailsService service = new PolicyDetailsService()
        service.policyDetailsSender = Mock(PolicyDetailsSender)

        when:
        service.findByClaimNumber(100)

        then:
        thrown(PolicyNotFoundException)
    }
}

