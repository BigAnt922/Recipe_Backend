package com.dhirajb7.recipe.service.ingredient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhirajb7.recipe.exception.ingredients.IngredientAlreadyPresentException;
import com.dhirajb7.recipe.exception.ingredients.IngredientNotFoundException;
import com.dhirajb7.recipe.exception.ingredients.IngredientsCannotBeCreatedException;
import com.dhirajb7.recipe.factory.StringToObject;
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
	public Ingredient getIntIngredientById(Long id) {
		try {
			return repo.findById(id).get();
		} catch (Exception e) {
			throw new IngredientNotFoundException(e.getMessage());
		}
	}

	@Override
	public Ingredient addAnIngredient(Ingredient ingredient) {
		try {
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
	public StringToObject editAnIngredient(Long id, Ingredient ingredient) {
		try {
			String name = ingredient.getName();
			String imagePrefix = ingredient.getImagePrefix();
			byte[] image = ingredient.getImage();
			String description = ingredient.getDescription();
			boolean veg = ingredient.isVeg();

			Ingredient ingredientFromDB = repo.findById(id).get();

			if (ingredientFromDB.getName() != name) {
				repo.updateName(id, name);
			}

			if (ingredientFromDB.getImagePrefix() != imagePrefix) {
				repo.updateImagePrefix(id, imagePrefix);
			}

			if (ingredientFromDB.getImage() != image) {
				repo.updateImage(id, image);
			}

			if (ingredientFromDB.getDescription() != description) {
				repo.updateDescription(id, description);
			}

			if (ingredientFromDB.isVeg() != veg) {
				repo.updateVeg(id, veg);
			}

			return new StringToObject("Changes Done");

		} catch (Exception e) {
			throw new IngredientNotFoundException(e.getMessage());
		}
	}

	@Override
	public StringToObject deleteAnIngredent(Long id) {
		try {
			Ingredient ingredientToBeDeleted = repo.findById(id).get();
			repo.delete(ingredientToBeDeleted);
			return new StringToObject(id + " is deleted");
		} catch (Exception e) {
			throw new IngredientNotFoundException(e.getMessage());
		}
	}

}
