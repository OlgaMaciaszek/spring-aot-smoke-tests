package com.example.transactional.eventlistener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TransactionalEventListenerApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(TransactionalEventListenerApplication.class, args);
		Thread.currentThread().join(); // To be able to measure memory consumption
	}

}
