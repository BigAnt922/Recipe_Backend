package com.dhirajb7.recipe.service.recipe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhirajb7.recipe.exception.recipe.RecipeAlreadyPresentException;
import com.dhirajb7.recipe.exception.recipe.RecipeCannotBeCreatedException;
import com.dhirajb7.recipe.exception.recipe.RecipeNotFoundException;
import com.dhirajb7.recipe.factory.StringToObject;
import com.dhirajb7.recipe.modal.Ingredient;
import com.dhirajb7.recipe.modal.Recipe;
import com.dhirajb7.recipe.repo.RecipeRepo;

@Service
public class RecipeService implements RecipeInterface {

	@Autowired
	private RecipeRepo repo;

	@Override
	public List<Recipe> getAllRecipes() {
		return repo.findAll();
	}

	@Override
	public Recipe getRecipeById(Long id) {
		try {
			return repo.findById(id).get();
		} catch (Exception e) {
			throw new RecipeNotFoundException("Recipe of id : " + id + " not found");
		}
	}

	@Override
	public Recipe addRecipe(Recipe recipe) {
		try {
			recipe.setName(recipe.getName().toLowerCase());
			return repo.save(recipe);
		} catch (Exception e) {
			if (e.getMessage().contains("unique")) {
				throw new RecipeAlreadyPresentException("Recipe Already Present");
			} else {
				throw new RecipeCannotBeCreatedException(e.getMessage());
			}
		}
	}

	@Override
	public StringToObject editRecipe(Long id, Recipe recipe) {
		try {

			String name = recipe.getName();
			String imagePrefix = recipe.getImagePrefix();
			byte[] image = recipe.getImage();
			String description = recipe.getDescription();
			List<String> steps = recipe.getSteps();
			List<Ingredient> ingredients = recipe.getIngredients();
			boolean veg = ingredients.stream().allMatch(ing -> ing.isVeg());

			Recipe recipeFromDB = repo.findById(id).get();

			String changeTracker = "";

			if (!(name.equalsIgnoreCase(recipeFromDB.getName()))) {
				changeTracker += "name ";
				repo.updateName(id, name);
			}

			if (!(imagePrefix.equalsIgnoreCase(recipeFromDB.getImagePrefix()))) {
				changeTracker += "imagePrefix ";
				repo.updateImagePrefix(id, imagePrefix);
			}

			if (image != recipeFromDB.getImage()) {
				changeTracker += "image ";
				repo.updateImage(id, image);
			}

			if (!(description.equalsIgnoreCase(recipeFromDB.getDescription()))) {
				changeTracker += "description ";
				repo.updateDescription(id, description);
			}

			if (steps != recipeFromDB.getSteps()) {
				changeTracker += "steps ";
				repo.updateSteps(id, steps);
			}

			if (ingredients != recipeFromDB.getIngredients()) {
				changeTracker += "ingredients ";
				repo.updateIngrdients(id, ingredients);
			}

			if (veg != recipeFromDB.isVeg()) {
				changeTracker += "veg ";
				repo.updateVeg(id, veg);
			}

			return new StringToObject(changeTrackerOutput(changeTracker));
		} catch (Exception e) {
			throw new RecipeNotFoundException("Recipe of id : " + id + " not found");
		}
	}

	@Override
	public StringToObject deleteRecipeById(Long id) {
		try {
			repo.deleteById(id);
			return new StringToObject(id + " is deleted");
		} catch (Exception e) {
			throw new RecipeNotFoundException("Recipe of id : " + id + " not found");
		}
	}

	private String changeTrackerOutput(String data) {

		if (data.length() == 0) {
			return "Nothing Changed";
		} else {
			return data.trim().replaceAll(" ", ", ") + " changed.";
		}

	}

}
