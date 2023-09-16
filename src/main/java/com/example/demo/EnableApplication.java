package com.example.demo;


import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
//@ComponentScan(basePackages = {"com.example.demo","com.example.demo.controllers", "com.example.demo.repositories"})
public class EnableApplication {

	
	
	
	public static void main(String[] args) {
		SpringApplication.run(EnableApplication.class, args);
	}
	

}
