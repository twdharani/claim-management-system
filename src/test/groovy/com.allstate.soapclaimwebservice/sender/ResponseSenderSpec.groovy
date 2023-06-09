package com.allstate.soapclaimwebservice.sender

import org.springframework.jms.core.JmsTemplate
import spock.lang.Specification

class ResponseSenderSpec extends Specification {

    JmsTemplate jmsTemplate = Mock(JmsTemplate)

//    Logger mockLogger
    def sender = new ResponseSender()

//    def setup() {
//        mockLogger = Mock(Logger)
//        mockLogger = org.slf4j.LoggerFactory.getLogger(ResponseSender.class);
////        sender = new ResponseSender(logger: mockLogger)
//    }

    def "should return policy details when valid policy details are present"() {
        given:
        def policyDetailsString = """
        <policy>
            <coverageLimit>20000.0</coverageLimit>
            <coverageName>Health</coverageName>
            <deductible>2000.0</deductible>
            <holderName>HolderOne</holderName>
            <policyNumber>12</policyNumber>
        </policy>
        """

        and:
        sender.jmsTemplate = jmsTemplate

        when:
        sender.sendPolicyDetails(policyDetailsString)

        then:
        1 * jmsTemplate.convertAndSend(_, policyDetailsString)
//        1 * mockLogger.info("Policy details sent")
    }

    def "should return error response when invalid policy number is given"() {
        given:
        def errorMessage = "Invalid Policy Number"

        and:
        def sender = new ResponseSender()
        sender.jmsTemplate = jmsTemplate

        when:
        sender.sendErrorResponse(errorMessage)

        then:
        1 * jmsTemplate.convertAndSend(_, errorMessage)
    }
}
