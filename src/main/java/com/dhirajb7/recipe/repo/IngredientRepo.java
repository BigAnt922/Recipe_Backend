package com.dhirajb7.recipe.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dhirajb7.recipe.modal.Ingredient;

public interface IngredientRepo extends JpaRepository<Ingredient, Long>{

}
