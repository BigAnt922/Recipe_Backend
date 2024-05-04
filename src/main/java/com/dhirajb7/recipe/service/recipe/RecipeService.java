package com.dhirajb7.recipe.service.recipe;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhirajb7.recipe.exception.recipe.RecipeAlreadyPresentException;
import com.dhirajb7.recipe.exception.recipe.RecipeCannotBeCreatedException;
import com.dhirajb7.recipe.exception.recipe.RecipeNotFoundException;
import com.dhirajb7.recipe.factory.StringToObject;
import com.dhirajb7.recipe.modal.Ingredient;
import com.dhirajb7.recipe.modal.Recipe;
import com.dhirajb7.recipe.repo.RecipeRepo;
import com.dhirajb7.recipe.service.Helper;

@Service
public class RecipeService implements RecipeInterface {

	@Autowired
	private RecipeRepo repo;

	@Autowired
	private Helper helper;

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
			recipe.setVeg(recipe.getIngredients().stream().allMatch(ing -> ing.isVeg()));
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
			List<Long> ingredientsIds = ingredients.stream().map(a -> a.getIngredientId()).collect(Collectors.toList());
			boolean veg = ingredients.stream().allMatch(ing -> ing.isVeg());

			Recipe recipeFromDB = repo.findById(id).get();
			List<Long> alreadyPresentIdInDB = recipeFromDB.getIngredients().stream().map(a -> a.getIngredientId())
					.collect(Collectors.toList());

			String changeTracker = "";

			if (!(name.equalsIgnoreCase(recipeFromDB.getName()))) {
				changeTracker += "name ";
				repo.updateName(id, name);
			}

			if (!(imagePrefix.equalsIgnoreCase(recipeFromDB.getImagePrefix()))) {
				changeTracker += "imagePrefix ";
				repo.updateImagePrefix(id, imagePrefix);
			}

			if (!Arrays.equals(image, recipe.getImage())) {
				changeTracker += "image ";
				repo.updateImage(id, image);
			}

			if (!(description.equalsIgnoreCase(recipeFromDB.getDescription()))) {
				changeTracker += "description ";
				repo.updateDescription(id, description);
			}

			if (!steps.equals(recipeFromDB.getSteps())) {
				changeTracker += "steps ";
				repo.updateSteps(id, steps);
			}

			if (!ingredientsIds.equals(alreadyPresentIdInDB)) {
				changeTracker += "ingredients ";

				List<Long> newlyAddedIngredentIds = helper.getChangedIngredent(alreadyPresentIdInDB, ingredientsIds);

				for (Long changedId : newlyAddedIngredentIds) {

					if (ingredientsIds.contains(changedId)) {
						repo.updateNewIngrdient(id, changedId);
					} else {
						repo.updateRemovedIngrdient(id, changedId);
					}

				}

			}

			if (veg != recipeFromDB.isVeg()) {
				changeTracker += "veg ";
				repo.updateVeg(id, veg);
			}

			return new StringToObject(helper.changeTrackerOutput(changeTracker));
		} catch (RecipeNotFoundException e) {
			throw new RecipeNotFoundException("Recipe of id : " + id + " not found");
		}
	}

	@Override
	public StringToObject deleteRecipeById(Long id) {
		try {
			Recipe recipeFromDB = repo.findById(id).get();
			repo.delete(recipeFromDB);
			return new StringToObject(id + " is deleted");
		} catch (Exception e) {
			throw new RecipeNotFoundException("Recipe of id : " + id + " not found");
		}
	}

}
