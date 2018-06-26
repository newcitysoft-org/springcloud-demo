package com.newcitysoft.study.helloservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class HelloServiceApplication2 {

	public static void main(String[] args) {
		SpringApplication.run(HelloServiceApplication2.class, args);
	}
}
