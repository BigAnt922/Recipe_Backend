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

import com.dhirajb7.recipe.entity.Catagory;
import com.dhirajb7.recipe.modal.StringToObject;
import com.dhirajb7.recipe.service.catagory.CatagoryInterface;

@RestController
@RequestMapping(path = "catagory")
public class CatagoryController {

	@Autowired
	private CatagoryInterface service;

	@GetMapping(path = "/")
	public ResponseEntity<Object> getAllCatagories() {
		return new ResponseEntity<Object>(service.getAllCatagories(), HttpStatus.OK);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Object> getCatagoryById(@PathVariable Long id) {
		return new ResponseEntity<Object>(service.getCatagoryById(id), HttpStatus.OK);
	}

	@PostMapping(path = "/")
	public ResponseEntity<Object> addCatagory(@RequestBody Catagory catagory) {
		return new ResponseEntity<Object>(service.addCatagory(catagory), HttpStatus.CREATED);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<StringToObject> editIngredient(@PathVariable Long id, @RequestBody Catagory catagory) {
		return new ResponseEntity<StringToObject>(service.editCatagory(id, catagory), HttpStatus.OK);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<StringToObject> deleteIngredient(@PathVariable Long id) {
		return new ResponseEntity<StringToObject>(service.deleteCatagory(id), HttpStatus.OK);
	}
}
