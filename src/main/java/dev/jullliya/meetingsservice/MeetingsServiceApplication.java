package com.example.meetingsservice;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MeetingsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeetingsServiceApplication.class, args);
	}

}
