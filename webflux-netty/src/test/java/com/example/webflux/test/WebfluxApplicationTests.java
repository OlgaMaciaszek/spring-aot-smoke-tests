package com.example.webflux.test;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.webflux.Foobar;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class WebfluxApplicationTests implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Autowired
	Foobar foobar;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Test
	void contextLoads() {
		assertThat(this.applicationContext).as("ApplicationContextAware").isNotNull();
	}

	@Test
	void autowiringWorks() {
		assertThat(this.foobar).as("@Autowired field").isNotNull();
	}

}
