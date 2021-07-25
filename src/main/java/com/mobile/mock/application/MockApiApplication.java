package com.mobile.mock.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.mobile.mock.api")
public class MockApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MockApiApplication.class, args);
	}

}
