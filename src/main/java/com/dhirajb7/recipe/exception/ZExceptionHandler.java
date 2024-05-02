package com.dhirajb7.recipe.exception;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dhirajb7.recipe.factory.GeneralExceptionGenerator;

@RestControllerAdvice
public class ZExceptionHandler {

//	if not above exception then 500

	@ExceptionHandler
	public ResponseEntity<Object> generalException(Exception e) {
		GeneralExceptionGenerator ge = new GeneralExceptionGenerator(500, e.toString(),
				new Timestamp(System.currentTimeMillis()).toString());
		return new ResponseEntity<Object>(ge, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
