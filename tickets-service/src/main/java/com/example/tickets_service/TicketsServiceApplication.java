package com.example.tickets_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient  // 🔥 Enable Eureka Client
@EnableFeignClients
@EnableScheduling
public class TicketsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketsServiceApplication.class, args);
	}

}
