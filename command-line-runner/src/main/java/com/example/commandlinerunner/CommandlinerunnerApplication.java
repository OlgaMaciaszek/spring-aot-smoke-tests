package com.example.commandlinerunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SimpleBeanDefinitionRegistrar.class)
public class CommandlinerunnerApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(CommandlinerunnerApplication.class, args);
		Thread.currentThread().join(); // To be able to measure memory consumption
	}

}
