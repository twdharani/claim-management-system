package com.allstate.soapclaimwebservice.sender;

import com.allstate.soapclaimwebservice.bean.Policy;
import jakarta.xml.bind.JAXB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.io.StringWriter;

@Component
public class PolicyDetailsSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${springjms.policyDetailsQueue}")
    private String policyDetailsQueue;

    public void sendPolicyDetails(Policy policy) {
        String xmlString = marshallToXml(policy);
        jmsTemplate.convertAndSend(policyDetailsQueue, xmlString);
    }

    private String marshallToXml(Object object) {
        StringWriter writer = new StringWriter();
        JAXB.marshal(object, writer);
        return writer.toString();
    }
}
