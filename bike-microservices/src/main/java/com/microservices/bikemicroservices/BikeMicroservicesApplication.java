package com.microservices.bikemicroservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BikeMicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BikeMicroservicesApplication.class, args);
	}

}
