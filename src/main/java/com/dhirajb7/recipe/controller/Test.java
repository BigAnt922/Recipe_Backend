package com.dhirajb7.recipe.controller;

import com.dhirajb7.recipe.factory.StringToObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "test")
public class Test {

	@Value("${environment}")
	private String envName;

	@GetMapping(path = "/")
	public ResponseEntity<StringToObject> test() {
		return new ResponseEntity<>(new StringToObject("Working in env " + envName), HttpStatus.OK);
	}

}
