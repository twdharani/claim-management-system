package com.allstate.soapclaimwebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class ClaimManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClaimManagementSystemApplication.class, args);
	}

}
