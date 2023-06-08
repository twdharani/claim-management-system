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
public class VendorService {
    private static final Logger logger = LoggerFactory.getLogger(VendorService.class);

    @Autowired
    private DummyService dummyService;

    @JmsListener(destination= "$(springjms.policyQueue)")
    public void rec(Policy policy){
        logger.info("Policy details received : "+ policy);
        String stringPolicyNumber = Integer.toString(policy.getPolicyNumber());
        if( stringPolicyNumber.length() <=  8) {
           dummyService.receive1(policy);
        }
        else{
            dummyService.receive2(policy);
        }
    }

//    @JmsListener(destination= "$(springjms.policyQueue)")
//    public void receive(Policy policy){
//        logger.info("Policy details received : "+ policy);
//
//        String stringPolicyNumber = Integer.toString(policy.getPolicyNumber());
//        if( stringPolicyNumber.length() <=  8) {
//            System.out.println("inside less 8 digit");
//            String errorResponse = "Invalid Policy number";
//            jmsTemplate.convertAndSend(queue, errorResponse);
//        }


}
