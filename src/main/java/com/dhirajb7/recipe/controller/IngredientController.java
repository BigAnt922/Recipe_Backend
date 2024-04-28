package com.dhirajb7.recipe.controller;

import java.util.List;

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
import com.dhirajb7.recipe.service.ingredient.IngredentService;

@RestController
@RequestMapping(path = "ingredient")
public class IngredientController {

	@Autowired
	private IngredentService service;

	@GetMapping(path = "/")
	public ResponseEntity<List<Ingredient>> getAllIngredients() {
		List<Ingredient> allIngredients = service.getAllIngredients();
		return new ResponseEntity<List<Ingredient>>(allIngredients, HttpStatus.OK);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Ingredient> getIngredientById(@PathVariable("id") Long id) {
		return new ResponseEntity<Ingredient>(service.getIntIngredientById(id), HttpStatus.OK);
	}

	@PostMapping(path = "/")
	public ResponseEntity<Ingredient> addIngredient(@RequestBody Ingredient ingredient) {
		return new ResponseEntity<Ingredient>(service.addAnIngredient(ingredient), HttpStatus.CREATED);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<StringToObject> editIngredient(@PathVariable("id") Long id,
			@RequestBody Ingredient ingredient) {
		return new ResponseEntity<StringToObject>(service.editAnIngredient(id, ingredient), HttpStatus.OK);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<StringToObject> deleteIngredient(@PathVariable("id") Long id) {
		return new ResponseEntity<StringToObject>(service.deleteAnIngredent(id), HttpStatus.OK);
	}
}
