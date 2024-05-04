package com.dhirajb7.recipe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dhirajb7.recipe.modal.Ingredient;

import jakarta.transaction.Transactional;

@Repository
public interface IngredientRepo extends JpaRepository<Ingredient, Long> {

	@Transactional
	@Modifying
	@Query("update Ingredient e set e.name = :name where e.ingredientId = :ingredientId")
	void updateName(@Param("ingredientId") long ingredientId, @Param("name") String name);

	@Transactional
	@Modifying
	@Query("update Ingredient e set e.imagePrefix = :imagePrefix where e.ingredientId = :ingredientId")
	void updateImagePrefix(@Param("ingredientId") long ingredientId, @Param("imagePrefix") String imagePrefix);

	@Transactional
	@Modifying
	@Query("update Ingredient e set e.image = :image where e.ingredientId = :ingredientId")
	void updateImage(@Param("ingredientId") long ingredientId, @Param("image") byte[] image);

	@Transactional
	@Modifying
	@Query("update Ingredient e set e.description = :description where e.ingredientId = :ingredientId")
	void updateDescription(@Param("ingredientId") long ingredientId, @Param("description") String description);

	@Transactional
	@Modifying
	@Query("update Ingredient e set e.veg = :veg where e.ingredientId = :ingredientId")
	void updateVeg(@Param("ingredientId") long ingredientId, @Param("veg") boolean veg);

}
