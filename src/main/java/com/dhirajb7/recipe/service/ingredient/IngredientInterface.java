package com.dhirajb7.recipe.service.ingredient;

import java.util.List;

import com.dhirajb7.recipe.modal.Ingredient;

public interface IngredientInterface {
	
	List<Ingredient> getAllIngredients();
	
	Ingredient getIntIngredientById(long id);
	
	Ingredient addAnIngredient(Ingredient ingredient);
	
	Ingredient editAnIngredient(Ingredient ingredient);
	
	void deleteAnIngredent(long id);

}
