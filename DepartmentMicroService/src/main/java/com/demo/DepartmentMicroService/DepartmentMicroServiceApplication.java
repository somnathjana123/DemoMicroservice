package com.demo.DepartmentMicroService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DepartmentMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepartmentMicroServiceApplication.class, args);
	}
	@Bean
	   public RestTemplate getRestTemplate() {
	      return new RestTemplate();
	   }

}
