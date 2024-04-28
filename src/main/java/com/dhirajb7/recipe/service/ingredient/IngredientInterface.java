package com.dhirajb7.recipe.service.ingredient;

import java.util.List;

import com.dhirajb7.recipe.factory.StringToObject;
import com.dhirajb7.recipe.modal.Ingredient;

public interface IngredientInterface {

	List<Ingredient> getAllIngredients();

	Ingredient getIntIngredientById(Long id);

	Ingredient addAnIngredient(Ingredient ingredient);

	StringToObject editAnIngredient(Long id, Ingredient ingredient);

	StringToObject deleteAnIngredent(Long id);

}
