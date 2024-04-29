package com.dhirajb7.recipe.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dhirajb7.recipe.modal.Ingredient;
import com.dhirajb7.recipe.modal.Recipe;

import jakarta.transaction.Transactional;

public interface RecipeRepo extends JpaRepository<Recipe, Long> {

	@Transactional
	@Modifying
	@Query("update Recipe e set e.name = :name where e.recipeId = :recipeId")
	void updateName(@Param("recipeId") long recipeId, @Param("name") String name);

	@Transactional
	@Modifying
	@Query("update Recipe e set e.imagePrefix = :imagePrefix where e.recipeId = :recipeId")
	void updateImagePrefix(@Param("recipeId") long recipeId, @Param("imagePrefix") String imagePrefix);

	@Transactional
	@Modifying
	@Query("update Recipe e set e.image = :image where e.recipeId = :recipeId")
	void updateImage(@Param("recipeId") long recipeId, @Param("image") byte[] image);

	@Transactional
	@Modifying
	@Query("update Recipe e set e.description = :description where e.recipeId = :recipeId")
	void updateDescription(@Param("recipeId") long recipeId, @Param("description") String description);

	@Transactional
	@Modifying
	@Query("update Recipe e set e.steps = :steps where e.recipeId = :recipeId")
	void updateSteps(@Param("recipeId") long recipeId, @Param("steps") List<String> steps);

	@Transactional
	@Modifying
	@Query("update Recipe e set e.ingredients = :ingredients where e.recipeId = :recipeId")
	void updateIngrdients(@Param("recipeId") long recipeId, @Param("ingredients") List<Ingredient> ingredients);

	@Transactional
	@Modifying
	@Query("update Recipe e set e.veg = :veg where e.recipeId = :recipeId")
	void updateVeg(@Param("recipeId") long recipeId, @Param("veg") boolean veg);

}
