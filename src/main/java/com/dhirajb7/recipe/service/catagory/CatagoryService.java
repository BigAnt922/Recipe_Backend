package com.dhirajb7.recipe.service.catagory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhirajb7.recipe.exception.catagory.CatagoryAlreadyPresentException;
import com.dhirajb7.recipe.exception.catagory.CatagoryCannotBeCreatedException;
import com.dhirajb7.recipe.exception.catagory.CatagoryNotFoundException;
import com.dhirajb7.recipe.factory.StringToObject;
import com.dhirajb7.recipe.modal.Catagory;
import com.dhirajb7.recipe.repo.CatagoryRepo;

@Service
public class CatagoryService implements CatagoryInterface {

	@Autowired
	private CatagoryRepo repo;

	@Override
	public List<Catagory> getAllCatagories() {
		return repo.findAll();
	}

	@Override
	public Catagory getCatagoryById(Long id) {
		try {
			return repo.findById(id).get();
		} catch (Exception e) {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StringToObject deleteCatagory(Long id) {
		try {
			Catagory catagoryFromDB = repo.findById(id).get();
			repo.delete(catagoryFromDB);
			return new StringToObject(id + " is deleted");
		} catch (Exception e) {
			throw new CatagoryNotFoundException("Catagory with id : " + id + " not found");
		}
	}

}
