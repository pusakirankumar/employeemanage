package com.example.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.employee")
public class AppBoot {

	public static void main(String[] args) {
		SpringApplication.run(AppBoot.class, args);
	}

}
