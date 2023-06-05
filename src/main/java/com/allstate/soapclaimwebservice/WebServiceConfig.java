package com.allstate.soapclaimwebservice;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

//Enable Spring Web Services
@EnableWs
// Spring Configuration
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter{
    // MessageDispatcherServlet
    // ApplicationContext
    // url -> /ws/*

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) {
        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
        messageDispatcherServlet.setApplicationContext(context);
        messageDispatcherServlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(messageDispatcherServlet, "/ws/*");
    }

    // /ws/claim.wsdl
    @Bean(name = "claim")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema claimSchema) {
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setPortTypeName("ClaimPort");
        definition.setTargetNamespace("http://allstate.com/claim");
        definition.setLocationUri("/ws");
        definition.setSchema(claimSchema);
        return definition;
    }

    @Bean
    public XsdSchema claimSchema() {
        return new SimpleXsdSchema(new ClassPathResource("policy-details.xsd"));
    }


    // https://spring.io/blog/2022/12/02/spring-ws-samples-upgraded-for-spring-boot-3-0
    // XwsSecurity has been deprecated in JakartaEE 9+

    //XwsSecurityInterceptor
    //	@Bean
    //	public XwsSecurityInterceptor securityInterceptor(){
    //		XwsSecurityInterceptor securityInterceptor = new XwsSecurityInterceptor();
    //		//Callback Handler -> SimplePasswordValidationCallbackHandler
    //		securityInterceptor.setCallbackHandler(callbackHandler());
    //		//Security Policy -> securityPolicy.xml
    //		securityInterceptor.setPolicyConfiguration(new ClassPathResource("securityPolicy.xml"));
    //		return securityInterceptor;
    //	}

//    @Bean
//    public Wss4jSecurityInterceptor securityInterceptor() {
//        Wss4jSecurityInterceptor securityInterceptor = new Wss4jSecurityInterceptor();
//        securityInterceptor.setSecurementActions("UsernameToken");
//        securityInterceptor.setSecurementUsername("user");
//        securityInterceptor.setSecurementPassword("password");
//
//        return securityInterceptor;
//    }

    //	@Bean
    //	public SimplePasswordValidationCallbackHandler callbackHandler() {
    //		SimplePasswordValidationCallbackHandler handler = new SimplePasswordValidationCallbackHandler();
    //		handler.setUsersMap(Collections.singletonMap("user", "password"));
    //		return handler;
    //	}

    // Interceptors.add -> XwsSecurityInterceptor
//    @Override
//    public void addInterceptors(List<EndpointInterceptor> interceptors) {
//        interceptors.add(securityInterceptor());
//    }

}
