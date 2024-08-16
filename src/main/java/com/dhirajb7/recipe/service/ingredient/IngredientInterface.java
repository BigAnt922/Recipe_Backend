package com.dhirajb7.recipe.service.ingredient;

import java.util.List;

import com.dhirajb7.recipe.entity.Ingredient;
import com.dhirajb7.recipe.modal.StringToObject;

public interface IngredientInterface {

	List<Ingredient> getAllIngredients();

	Ingredient getIngredientById(Long id);

	Ingredient addIngredient(Ingredient ingredient);

	StringToObject editIngredient(Long id, Ingredient ingredient);

	StringToObject deleteIngredent(Long id);

}
