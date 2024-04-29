package com.dhirajb7.recipe.service.recipe;

import java.util.List;

import com.dhirajb7.recipe.factory.StringToObject;
import com.dhirajb7.recipe.modal.Recipe;

public interface RecipeInterface {

	List<Recipe> getAllRecipes();

	Recipe getRecipeById(Long id);

	Recipe addRecipe(Recipe recipe);

	StringToObject editRecipe(Long id, Recipe recipe);

	StringToObject deleteRecipeById(Long id);

}
