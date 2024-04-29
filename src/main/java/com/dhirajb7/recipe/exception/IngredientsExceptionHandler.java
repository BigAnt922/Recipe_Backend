package com.dhirajb7.recipe.exception;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dhirajb7.recipe.exception.ingredients.IngredientAlreadyPresentException;
import com.dhirajb7.recipe.exception.ingredients.IngredientNotFoundException;
import com.dhirajb7.recipe.exception.ingredients.IngredientsCannotBeCreatedException;
import com.dhirajb7.recipe.factory.GeneralExceptionGenerator;

@RestControllerAdvice
public class IngredientsExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<Object> ingridentNotFound(IngredientNotFoundException e) {
		GeneralExceptionGenerator ge = new GeneralExceptionGenerator(404, e.getMessage(),
				new Timestamp(System.currentTimeMillis()).toString());
		return new ResponseEntity<Object>(ge, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<Object> ingredientAlreadyPreset(IngredientAlreadyPresentException e) {
		GeneralExceptionGenerator ge = new GeneralExceptionGenerator(409, e.getMessage(),
				new Timestamp(System.currentTimeMillis()).toString());
		return new ResponseEntity<Object>(ge, HttpStatus.CONFLICT);
	}

	@ExceptionHandler
	public ResponseEntity<Object> ingridentCannotBeCreated(IngredientsCannotBeCreatedException e) {
		GeneralExceptionGenerator ge = new GeneralExceptionGenerator(400, e.getMessage(),
				new Timestamp(System.currentTimeMillis()).toString());
		return new ResponseEntity<Object>(ge, HttpStatus.BAD_REQUEST);
	}

}
