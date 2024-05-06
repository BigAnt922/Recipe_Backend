package com.dhirajb7.recipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhirajb7.recipe.factory.StringToObject;
import com.dhirajb7.recipe.modal.Ingredient;
import com.dhirajb7.recipe.service.ingredient.IngredientInterface;

@RestController
@RequestMapping(path = "ingredient")
public class IngredientController {

	@Autowired
	private IngredientInterface service;

	@GetMapping(path = "/")
	public ResponseEntity<Object> getAllIngredients() {
		return new ResponseEntity<Object>(service.getAllIngredients(), HttpStatus.OK);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Object> getIngredientById(@PathVariable("id") Long id) {
		return new ResponseEntity<Object>(service.getIngredientById(id), HttpStatus.OK);
	}

	@PostMapping(path = "/")
	public ResponseEntity<Object> addIngredient(@RequestBody Ingredient ingredient) {
		return new ResponseEntity<Object>(service.addIngredient(ingredient), HttpStatus.CREATED);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<StringToObject> editIngredient(@PathVariable("id") Long id,
			@RequestBody Ingredient ingredient) {
		return new ResponseEntity<StringToObject>(service.editIngredient(id, ingredient), HttpStatus.OK);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<StringToObject> deleteIngredient(@PathVariable("id") Long id) {
		return new ResponseEntity<StringToObject>(service.deleteIngredent(id), HttpStatus.OK);
	}
}
