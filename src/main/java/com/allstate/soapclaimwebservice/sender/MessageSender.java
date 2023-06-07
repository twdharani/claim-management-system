package com.allstate.soapclaimwebservice.sender;

import com.allstate.soapclaimwebservice.bean.Policy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("$(springjms.policyQueue)")
    private String queue;

    public void sendPolicyDetails(Policy policy) {
        jmsTemplate.convertAndSend(queue, policy);
    }
}
