package org.service.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MQApp {
	
	public static void main( String[] args ) {
    	SpringApplication.run(MQApp.class, args);
    }
}
