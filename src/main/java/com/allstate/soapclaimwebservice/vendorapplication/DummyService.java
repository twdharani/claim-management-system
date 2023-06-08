package com.allstate.soapclaimwebservice.vendorapplication;

import com.allstate.soapclaimwebservice.bean.Policy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class DummyService {
    private static final Logger logger = LoggerFactory.getLogger(VendorService.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("$(springjms.policyQueue)")
    private String queue;

    @Value("$(springjms.dummyQueue)")
    private String queue2;

    @JmsListener(destination= "$(springjms.policyQueue)")
    public void receive1(Policy policy) {
        String errorResponse = "Invalid Policy number";
        jmsTemplate.convertAndSend(queue, errorResponse);

    }
    @JmsListener(destination= "$(springjms.dummyQueue)")
    public void receive2(Policy policy) {
        jmsTemplate.convertAndSend(queue2, policy);
    }
}
