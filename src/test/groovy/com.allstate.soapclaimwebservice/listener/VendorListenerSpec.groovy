package com.allstate.soapclaimwebservice.listener

import com.allstate.soapclaimwebservice.sender.ResponseSender
import org.springframework.jms.core.JmsTemplate
import spock.lang.Specification

class VendorListenerSpec extends Specification {

    JmsTemplate jmsTemplate = Mock(JmsTemplate)

    def sender = new ResponseSender()

    def consumer = new VendorListener(sender)

    def "should consume policy details message when policy number is greater than or equal to 8 digits"() {
        given:
        sender.jmsTemplate = jmsTemplate
        def policyDetailsString = """
        <policy>
            <coverageLimit>20000.0</coverageLimit>
            <coverageName>Health</coverageName>
            <deductible>2000.0</deductible>
            <holderName>HolderOne</holderName>
            <policyNumber>12982032908302</policyNumber>
        </policy>
        """

        when:
        consumer.receiveAndHandlePolicyDetails(policyDetailsString)

        then:
//        1 * sender.sendErrorResponse(errorMsg)
        1 * jmsTemplate.convertAndSend(_, policyDetailsString)

    }

    def "should consume error message when policy number is less than 8 digits"() {
        given:
        sender.jmsTemplate = jmsTemplate
        def errorMsg = "Invalid Policy number"
        def policyDetailsString = """
        <policy>
            <coverageLimit>20000.0</coverageLimit>
            <coverageName>Health</coverageName>
            <deductible>2000.0</deductible>
            <holderName>HolderOne</holderName>
            <policyNumber>145</policyNumber>
        </policy>
        """

        when:
        consumer.receiveAndHandlePolicyDetails(policyDetailsString)

        then:
        1 * jmsTemplate.convertAndSend(_, errorMsg)

    }
}
