package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
    "com.example.spark.spring.domain", 
    "com.example.spark.repository", 
    "com.example.spring.config",
    "com.example.spring.util",
    "com.example.spark.config",
    "com.example.spark.controller", 
    "com.example"
})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args).registerShutdownHook();
	}
}
