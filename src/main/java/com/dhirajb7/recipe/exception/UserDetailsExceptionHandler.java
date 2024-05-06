package com.dhirajb7.recipe.exception;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dhirajb7.recipe.exception.userDetail.UserDetailAlreadyPresentException;
import com.dhirajb7.recipe.exception.userDetail.UserDetailCannotBeCreatedException;
import com.dhirajb7.recipe.exception.userDetail.UserDetailNotFoundException;
import com.dhirajb7.recipe.factory.GeneralExceptionGenerator;

@RestControllerAdvice
public class UserDetailsExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<Object> userDetailNotFound(UserDetailNotFoundException e) {
		GeneralExceptionGenerator ge = new GeneralExceptionGenerator(404, e.getMessage(),
				new Timestamp(System.currentTimeMillis()).toString());
		return new ResponseEntity<Object>(ge, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<Object> userDetailAlreadyPreset(UserDetailAlreadyPresentException e) {
		GeneralExceptionGenerator ge = new GeneralExceptionGenerator(409, e.getMessage(),
				new Timestamp(System.currentTimeMillis()).toString());
		return new ResponseEntity<Object>(ge, HttpStatus.CONFLICT);
	}

	@ExceptionHandler
	public ResponseEntity<Object> userDetailCannotBeCreated(UserDetailCannotBeCreatedException e) {
		GeneralExceptionGenerator ge = new GeneralExceptionGenerator(400, e.getMessage(),
				new Timestamp(System.currentTimeMillis()).toString());
		return new ResponseEntity<Object>(ge, HttpStatus.BAD_REQUEST);
	}

}
