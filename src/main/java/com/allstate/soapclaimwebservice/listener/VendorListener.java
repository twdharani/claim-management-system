package com.allstate.soapclaimwebservice.listener;

import com.allstate.soapclaimwebservice.sender.ResponseSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

import static com.allstate.soapclaimwebservice.constants.Constants.INVALID_POLICY_NUMBER;


@Component
public class VendorListener {
    private static final Logger logger = LoggerFactory.getLogger(VendorListener.class);

    @Autowired
    private ResponseSender responseSender;

    @JmsListener(destination = "${springjms.policyDetailsQueue}")
    public void receiveAndHandlePolicyDetails(String xmlPolicyDetails) throws ParserConfigurationException, IOException, SAXException {
        String policyNumber = getPolicyNumber(xmlPolicyDetails);
        logger.info("Policy details received : " + policyNumber);
        if (policyNumber.length() <= 8) {
            responseSender.sendErrorResponse(INVALID_POLICY_NUMBER);
        } else {
            responseSender.sendPolicyDetails(xmlPolicyDetails);
        }
    }

    private String getPolicyNumber(String xmlPolicyDetails) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource inputSource = new InputSource();
        inputSource.setCharacterStream(new StringReader(xmlPolicyDetails));

        Document document = builder.parse(inputSource);
        String policyNumber = document.getElementsByTagName("policyNumber").item(0).getTextContent();
        return policyNumber;
    }

}
