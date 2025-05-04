package com.example.FIrstProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example"})
public class FIrstProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FIrstProjectApplication.class, args);
	}

}
