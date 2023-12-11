package com.mycompany.signupservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.mycompany.signupservice.controller", "com.mycompany.signupservice.service",
		"com.mycompany.signupservice.service.impl", "com.mycompany.signupservice.repository",
		"com.mycompany.signupservice.config", "com.mycompany.signupservice.security",
		"com.mycompany.signupservice.exception"})
public class SignupServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SignupServiceApplication.class, args);
	}
}
