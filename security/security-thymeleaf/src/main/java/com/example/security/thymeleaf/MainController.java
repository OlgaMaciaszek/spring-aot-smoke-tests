package com.example.security.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String index() {
		return "home";
	}

	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}

}
