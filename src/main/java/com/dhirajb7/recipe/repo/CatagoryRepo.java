package com.dhirajb7.recipe.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dhirajb7.recipe.entity.Catagory;

import jakarta.transaction.Transactional;

@Repository
public interface CatagoryRepo extends JpaRepository<Catagory, Long> {

	@Transactional
	@Modifying
	@Query("update Catagory e set e.name = :name where e.catagoryId = :catagoryId")
	void updateName(@Param("catagoryId") long catagoryId, @Param("name") String name);

	@Transactional
	@Modifying
	@Query("update Catagory e set e.imagePrefix = :imagePrefix where e.catagoryId = :catagoryId")
	void updateImagePrefix(@Param("catagoryId") long catagoryId, @Param("imagePrefix") String imagePrefix);

	@Transactional
	@Modifying
	@Query("update Catagory e set e.image = :image where e.catagoryId = :catagoryId")
	void updateImage(@Param("catagoryId") long catagoryId, @Param("image") byte[] image);

	@Transactional
	@Modifying
	@Query("update Catagory e set e.description = :description where e.catagoryId = :catagoryId")
	void updateDescription(@Param("catagoryId") long catagoryId, @Param("description") String description);

	@Transactional
	@Modifying
	@Query("update Catagory e set e.openCloseTimings = :openCloseTimings where e.catagoryId = :catagoryId")
	void updateTimings(@Param("catagoryId") long catagoryId, @Param("openCloseTimings") List<String> openCloseTimings);

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "INSERT INTO catagory_recipe(catagory_id, recipe_id)VALUES (:catagoryId, :recipeId)")
	void updateNewCatagory(@Param("catagoryId") long catagoryId, @Param("recipeId") long recipeId);

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "DELETE FROM catagory_recipe WHERE catagory_id =:catagoryId AND recipe_id=:recipeId ")
	void updateRemovedCatagory(@Param("catagoryId") long catagoryId, @Param("recipeId") long recipeId);

}
