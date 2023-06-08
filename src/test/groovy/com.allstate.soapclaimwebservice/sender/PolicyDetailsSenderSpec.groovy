import com.allstate.soapclaimwebservice.bean.Policy
import com.allstate.soapclaimwebservice.sender.PolicyDetailsSender
import jakarta.xml.bind.JAXB
import org.springframework.jms.core.JmsTemplate
import spock.lang.Specification

class PolicyDetailsSenderSpec extends Specification {
    JmsTemplate jmsTemplate = Mock(JmsTemplate)

    def "Send policy details successfully to queue"() {
        given:
        def policy = new Policy()

        and:
        def sender = new PolicyDetailsSender()
        sender.jmsTemplate = jmsTemplate

        when:
        sender.sendPolicyDetails(policy)

        then:
        1 * jmsTemplate.convertAndSend(_, _ as String)
    }

    def "should marshal object to XML string"() {
        given:
        def object = new Policy("HolderOne", 12, "Health", 20000, 2000);

        StringWriter writer = new StringWriter();
        JAXB.marshal(object, writer);
        def sender = new PolicyDetailsSender()

        def expectedXml = """
        <policy>
            <coverageLimit>20000.0</coverageLimit>
            <coverageName>Health</coverageName>
            <deductible>2000.0</deductible>
            <holderName>HolderOne</holderName>
            <policyNumber>12</policyNumber>
        </policy>
        """

        when:
        def result = sender.marshallToXml(object);

        then:
        def expected = new XmlSlurper().parseText(expectedXml)
        def actual = new XmlSlurper().parseText(result)

        expected == actual
    }
}

