package com.dhirajb7.recipe.service.catagory;

import java.util.List;

import com.dhirajb7.recipe.entity.Catagory;
import com.dhirajb7.recipe.modal.StringToObject;

public interface CatagoryInterface {

	List<Catagory> getAllCatagories();

	Catagory getCatagoryById(Long id);

	Catagory addCatagory(Catagory catagory);

	StringToObject editCatagory(Long id, Catagory catagory);

	StringToObject deleteCatagory(Long id);

}
