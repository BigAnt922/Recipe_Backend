package com.dhirajb7.recipe.service.catagory;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhirajb7.recipe.entity.Catagory;
import com.dhirajb7.recipe.entity.Recipe;
import com.dhirajb7.recipe.exception.catagory.CatagoryAlreadyPresentException;
import com.dhirajb7.recipe.exception.catagory.CatagoryCannotBeCreatedException;
import com.dhirajb7.recipe.exception.catagory.CatagoryNotFoundException;
import com.dhirajb7.recipe.modal.StringToObject;
import com.dhirajb7.recipe.repo.CatagoryRepo;
import com.dhirajb7.recipe.service.Helper;

@Service
public class CatagoryService implements CatagoryInterface {

	@Autowired
	private CatagoryRepo repo;

	@Autowired
	private Helper helper;

	@Override
	public List<Catagory> getAllCatagories() {
		return repo.findAll();
	}

	@Override
	public Catagory getCatagoryById(Long id) {
		try {
			return repo.findById(id).get();
		} catch (CatagoryNotFoundException e) {
			throw new CatagoryNotFoundException("Catagory with id : " + id + " not found");
		}
	}

	@Override
	public Catagory addCatagory(Catagory catagory) {
		try {
			catagory.setName(catagory.getName().toLowerCase());
			return repo.save(catagory);
		} catch (Exception e) {
			if (e.getMessage().contains("unique")) {
				throw new CatagoryAlreadyPresentException("Catagory Already Present");
			} else {
				throw new CatagoryCannotBeCreatedException(e.getMessage());
			}
		}
	}

	@Override
	public StringToObject editCatagory(Long id, Catagory catagory) {
		try {

			String name = catagory.getName();
			String imagePrefix = catagory.getImagePrefix();
			byte[] image = catagory.getImage();
			String description = catagory.getDescription();
			List<String> openCloseTimings = catagory.getOpenCloseTimings();
			List<Recipe> recipes = catagory.getRecipes();
			List<Long> recipesIds = recipes.stream().map(recipe -> recipe.getRecipeId()).collect(Collectors.toList());

			Catagory catagoryFromDB = repo.findById(id).get();
			List<Long> recipesIdsFromDB = catagoryFromDB.getRecipes().stream().map(recipe -> recipe.getRecipeId())
					.collect(Collectors.toList());

			String changeTracker = "";

			if (!name.equalsIgnoreCase(catagoryFromDB.getName())) {
				changeTracker += "name ";
				repo.updateName(id, name);
			}

			if (!imagePrefix.equalsIgnoreCase(catagoryFromDB.getImagePrefix())) {
				changeTracker += "imagePrefix ";
				repo.updateImagePrefix(id, imagePrefix);
			}

			if (image != catagoryFromDB.getImage()) {
				changeTracker += "image ";
				repo.updateImage(id, image);
			}

			if (!description.equalsIgnoreCase(catagoryFromDB.getDescription())) {
				changeTracker += "description ";
				repo.updateDescription(id, description);
			}

			if (!openCloseTimings.equals(catagoryFromDB.getOpenCloseTimings())) {
				changeTracker += "timings ";
				repo.updateTimings(id, openCloseTimings);
			}

			if (!recipesIds.equals(recipesIdsFromDB)) {
				changeTracker += "recipes ";

				List<Long> newlyAddedCatagoryIds = helper.getChangedIngredent(recipesIdsFromDB, recipesIds);

				for (Long changedId : newlyAddedCatagoryIds) {

					if (recipesIds.contains(changedId)) {
						repo.updateNewCatagory(id, changedId);
					} else {
						repo.updateRemovedCatagory(id, changedId);
					}

				}

			}

			return new StringToObject(helper.changeTrackerOutput(changeTracker));
		} catch (CatagoryNotFoundException e) {
			throw new CatagoryNotFoundException("Catagory with id : " + id + " not found");
		}

	}

	@Override
	public StringToObject deleteCatagory(Long id) {
		try {
			Catagory catagoryFromDB = repo.findById(id).get();
			repo.delete(catagoryFromDB);
			return new StringToObject(id + " is deleted");
		} catch (CatagoryNotFoundException e) {
			throw new CatagoryNotFoundException("Catagory with id : " + id + " not found");
		}
	}

}
