package com.foodConsensus.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/hello")
public class HelloController {
	
	//Test endpoint that just returns hello world
	@GetMapping("")
	public String HelloWorld() {
		return "Hello World!";
	}
	
}
