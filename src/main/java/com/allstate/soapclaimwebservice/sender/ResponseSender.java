package com.allstate.soapclaimwebservice.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ResponseSender {

    private static final Logger logger = LoggerFactory.getLogger(ResponseSender.class);
    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${springjms.responseQueue}")
    private String responseQueue;

    public void sendErrorResponse(String errorResponse) {
        logger.info("Error response sent");
        jmsTemplate.convertAndSend(responseQueue, errorResponse);
    }

    public void sendPolicyDetails(String policy) {
        logger.info("Policy details sent");
        jmsTemplate.convertAndSend(responseQueue, policy);
    }
}
