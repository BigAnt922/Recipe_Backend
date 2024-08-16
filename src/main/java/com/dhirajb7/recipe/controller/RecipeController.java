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

import com.dhirajb7.recipe.entity.Recipe;
import com.dhirajb7.recipe.service.recipe.RecipeInterface;

@RestController
@RequestMapping(path = "recipe")
public class RecipeController {

	@Autowired
	private RecipeInterface service;

	@GetMapping(path = "/")
	public ResponseEntity<Object> getAllRecipes() {
		return new ResponseEntity<Object>(service.getAllRecipes(), HttpStatus.OK);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Object> getRecipeById(@PathVariable Long id) {
		return new ResponseEntity<Object>(service.getRecipeById(id), HttpStatus.OK);
	}

	@PostMapping(path = "/")
	public ResponseEntity<Object> addRecipe(@RequestBody Recipe recipe) {
		return new ResponseEntity<Object>(service.addRecipe(recipe), HttpStatus.CREATED);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Object> editRecipe(@PathVariable Long id, @RequestBody Recipe recipe) {
		return new ResponseEntity<Object>(service.editRecipe(id, recipe), HttpStatus.OK);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> deleteRecipeById(@PathVariable Long id) {
		return new ResponseEntity<Object>(service.deleteRecipeById(id), HttpStatus.OK);
	}

}
