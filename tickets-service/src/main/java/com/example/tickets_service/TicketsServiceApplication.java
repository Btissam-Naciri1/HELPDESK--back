package com.example.tickets_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient  // 🔥 Enable Eureka Client
@EnableFeignClients
public class TicketsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketsServiceApplication.class, args);
	}

}
