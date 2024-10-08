package com.dhirajb7.recipe.exception;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dhirajb7.recipe.exception.recipe.RecipeAlreadyPresentException;
import com.dhirajb7.recipe.exception.recipe.RecipeCannotBeCreatedException;
import com.dhirajb7.recipe.exception.recipe.RecipeNotFoundException;
import com.dhirajb7.recipe.modal.GeneralExceptionGenerator;

@RestControllerAdvice
public class RecipesExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<Object> recipeNotFound(RecipeNotFoundException e) {
		GeneralExceptionGenerator ge = new GeneralExceptionGenerator(404, e.getMessage(),
				new Timestamp(System.currentTimeMillis()).toString());
		return new ResponseEntity<Object>(ge, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<Object> recipeAlreadyPreset(RecipeAlreadyPresentException e) {
		GeneralExceptionGenerator ge = new GeneralExceptionGenerator(409, e.getMessage(),
				new Timestamp(System.currentTimeMillis()).toString());
		return new ResponseEntity<Object>(ge, HttpStatus.CONFLICT);
	}

	@ExceptionHandler
	public ResponseEntity<Object> recipeCannotBeCreated(RecipeCannotBeCreatedException e) {
		GeneralExceptionGenerator ge = new GeneralExceptionGenerator(400, e.getMessage(),
				new Timestamp(System.currentTimeMillis()).toString());
		return new ResponseEntity<Object>(ge, HttpStatus.BAD_REQUEST);
	}

}
