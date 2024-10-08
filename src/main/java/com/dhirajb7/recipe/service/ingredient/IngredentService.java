package com.dhirajb7.recipe.service.ingredient;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhirajb7.recipe.entity.Ingredient;
import com.dhirajb7.recipe.exception.ingredient.IngredientAlreadyPresentException;
import com.dhirajb7.recipe.exception.ingredient.IngredientNotFoundException;
import com.dhirajb7.recipe.exception.ingredient.IngredientsCannotBeCreatedException;
import com.dhirajb7.recipe.modal.StringToObject;
import com.dhirajb7.recipe.repo.IngredientRepo;
import com.dhirajb7.recipe.service.Helper;

@Service
public class IngredentService implements IngredientInterface {

	@Autowired
	private IngredientRepo repo;

	@Autowired
	private Helper helper;

	@Override
	public List<Ingredient> getAllIngredients() {
		return repo.findAll();
	}

	@Override
	public Ingredient getIngredientById(Long id) {
		try {
			return repo.findById(id).get();
		} catch (Exception e) {
			throw new IngredientNotFoundException("Ingredient with id : " + id + " not found");
		}
	}

	@Override
	public Ingredient addIngredient(Ingredient ingredient) {
		try {
			ingredient.setName(ingredient.getName().toLowerCase());
			return repo.save(ingredient);
		} catch (Exception e) {
			if (e.getMessage().contains("unique")) {
				throw new IngredientAlreadyPresentException("Ingredient Already Present");
			} else {
				throw new IngredientsCannotBeCreatedException(e.getMessage());
			}
		}
	}

	@Override
	public StringToObject editIngredient(Long id, Ingredient ingredient) {
		try {
			String name = ingredient.getName();
			String imagePrefix = ingredient.getImagePrefix();
			byte[] image = ingredient.getImage();
			String description = ingredient.getDescription();
			boolean veg = ingredient.isVeg();

			Ingredient ingredientFromDB = repo.findById(id).get();

			String changeTracker = "";

			if (!(ingredientFromDB.getName().equalsIgnoreCase(name))) {
				changeTracker += "name ";
				repo.updateName(id, name);
			}

			if (!(ingredientFromDB.getImagePrefix().equalsIgnoreCase(imagePrefix))) {
				changeTracker += "imagePrefix ";
				repo.updateImagePrefix(id, imagePrefix);
			}

			if (!Arrays.equals(ingredientFromDB.getImage(), image)) {
				changeTracker += "image ";
				repo.updateImage(id, image);
			}

			if (!(ingredientFromDB.getDescription().equalsIgnoreCase(description))) {
				changeTracker += "description ";
				repo.updateDescription(id, description);
			}

			if (ingredientFromDB.isVeg() != veg) {
				changeTracker += "veg ";
				repo.updateVeg(id, veg);
			}

			return new StringToObject(helper.changeTrackerOutput(changeTracker));

		} catch (Exception e) {
			throw new IngredientNotFoundException("Ingredient with id : " + id + " not found");
		}
	}

	@Override
	public StringToObject deleteIngredent(Long id) {
		try {
			Ingredient ingridentFromDB = repo.findById(id).get();
			repo.delete(ingridentFromDB);
			return new StringToObject(id + " is deleted");
		} catch (Exception e) {
			throw new IngredientNotFoundException("Ingredient with id : " + id + " not found");
		}
	}

}
