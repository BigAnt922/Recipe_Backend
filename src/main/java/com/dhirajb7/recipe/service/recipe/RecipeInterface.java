package com.dhirajb7.recipe.service.recipe;

import java.util.List;

import com.dhirajb7.recipe.entity.Recipe;
import com.dhirajb7.recipe.modal.StringToObject;

public interface RecipeInterface {

	List<Recipe> getAllRecipes();

	Recipe getRecipeById(Long id);

	Recipe addRecipe(Recipe recipe);

	StringToObject editRecipe(Long id, Recipe recipe);

	StringToObject deleteRecipeById(Long id);

}
