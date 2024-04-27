package com.dhirajb7.recipe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "test")
public class Test {
	
	@GetMapping(path = "/")
	public String test() {
		return "working";
	}

}
