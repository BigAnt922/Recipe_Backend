package com.dhirajb7.recipe.exception;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dhirajb7.recipe.exception.catagory.CatagoryAlreadyPresentException;
import com.dhirajb7.recipe.exception.catagory.CatagoryCannotBeCreatedException;
import com.dhirajb7.recipe.exception.catagory.CatagoryNotFoundException;
import com.dhirajb7.recipe.factory.GeneralExceptionGenerator;

@RestControllerAdvice
public class CatagoriesExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<Object> catagoryNotFound(CatagoryNotFoundException e) {
		GeneralExceptionGenerator ge = new GeneralExceptionGenerator(404, e.getMessage(),
				new Timestamp(System.currentTimeMillis()).toString());
		return new ResponseEntity<Object>(ge, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<Object> catagoryAlreadyPreset(CatagoryAlreadyPresentException e) {
		GeneralExceptionGenerator ge = new GeneralExceptionGenerator(409, e.getMessage(),
				new Timestamp(System.currentTimeMillis()).toString());
		return new ResponseEntity<Object>(ge, HttpStatus.CONFLICT);
	}

	@ExceptionHandler
	public ResponseEntity<Object> catagoryCannotBeCreated(CatagoryCannotBeCreatedException e) {
		GeneralExceptionGenerator ge = new GeneralExceptionGenerator(400, e.getMessage(),
				new Timestamp(System.currentTimeMillis()).toString());
		return new ResponseEntity<Object>(ge, HttpStatus.BAD_REQUEST);
	}

}
