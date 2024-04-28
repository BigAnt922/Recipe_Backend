package com.dhirajb7.recipe.service.ingredient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhirajb7.recipe.modal.Ingredient;
import com.dhirajb7.recipe.repo.IngredientRepo;

@Service
public class IngredentService implements IngredientInterface {

	@Autowired
	private IngredientRepo repo;

	@Override
	public List<Ingredient> getAllIngredients() {
	return repo.findAll();
	}

	@Override
	public Ingredient getIntIngredientById(long id) {
		return repo.findById(id).get();
	}

	@Override
	public Ingredient addAnIngredient(Ingredient ingredient) {
		return repo.save(ingredient);
	}

	@Override
	public Ingredient editAnIngredient(Ingredient ingredient) {
		return null;// not implimented
	}

	@Override
	public void deleteAnIngredent(long id) {
		repo.deleteById(id);
	}
	
}
