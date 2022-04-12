package com.qa.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringExampleApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringExampleApplication.class, args);
		
//		Object byName = context.getBean("hello");
//		String byType = context.getBean(String.class);
//		String byBoth = context.getBean("hello", String.class);
//		
//		System.out.println(byName);
//		System.out.println(byType);
//		System.out.println(byBoth);
	}

//	@Bean
//	public String hello() {
//		return "Hello World!";
//	}
}
