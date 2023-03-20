package com.example.cloud.openfeign;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@EnableFeignClients(clients = TestServiceClient.class)
public class CloudOpenFeignApplication implements CommandLineRunner {

	private static final Log LOG = LogFactory.getLog(CloudOpenFeignApplication.class);

	@Autowired
	private TestServiceClient testServiceClient;

	public static void main(String[] args) {
		SpringApplication.run(CloudOpenFeignApplication.class, args);
	}

	@Override
	public void run(String... args) {
		callTestService();
	}

	private void callTestService() {
		if (LOG.isInfoEnabled()) {
			LOG.info(testServiceClient.test());
		}
	}

}

@FeignClient("test-service")
interface TestServiceClient {

	@GetMapping("/")
	String test();

}
