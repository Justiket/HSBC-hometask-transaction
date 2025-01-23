package com.caoyinglong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class HsbcHometaskTransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(HsbcHometaskTransactionApplication.class, args);
	}

}
