package com.dade.mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MongoServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoServerApplication.class, args);
	}
}
