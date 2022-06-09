package com.microservices.carmicroservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CarMicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarMicroservicesApplication.class, args);
	}

}
