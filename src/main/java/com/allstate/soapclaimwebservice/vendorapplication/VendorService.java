package com.allstate.soapclaimwebservice.vendorapplication;

import com.allstate.soapclaimwebservice.bean.Policy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class VendorService {
    private static final Logger logger = LoggerFactory.getLogger(VendorService.class);

    @JmsListener(destination= "$(springjms.policyQueue)")
    public void receive(Policy policy){
        logger.info("Policy details received : "+ policy);
    }
}
