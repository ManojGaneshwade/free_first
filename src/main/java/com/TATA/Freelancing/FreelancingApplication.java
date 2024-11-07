package com.TATA.Freelancing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FreelancingApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreelancingApplication.class, args);
	}

}
