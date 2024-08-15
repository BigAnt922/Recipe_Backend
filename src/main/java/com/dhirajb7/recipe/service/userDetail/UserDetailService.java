package com.dhirajb7.recipe.service.userDetail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhirajb7.recipe.exception.userDetail.UserDetailAlreadyPresentException;
import com.dhirajb7.recipe.exception.userDetail.UserDetailCannotBeCreatedException;
import com.dhirajb7.recipe.exception.userDetail.UserDetailNotFoundException;
import com.dhirajb7.recipe.exception.userDetail.UserNotEnabled;
import com.dhirajb7.recipe.factory.StringToObject;
import com.dhirajb7.recipe.modal.UserDetail;
import com.dhirajb7.recipe.repo.UserDetailsRespo;

@Service
public class UserDetailService implements UserDetailsInterface {

	@Autowired
	private UserDetailsRespo repo;

	@Override
	public List<UserDetail> getAllUserDetails() {
		return repo.findAll();
	}

	@Override
	public UserDetail getUserDetailById(Long id) {
		try {
			return repo.findById(id).get();
		} catch (Exception e) {
			throw new UserDetailNotFoundException("User with id : " + id + " not found");
		}
	}

	@Override
	public UserDetail addUserDetail(UserDetail userDetail) {
		try {
			userDetail.setUsername(userDetail.getUsername().toLowerCase());
			userDetail.setEnable(false);
			return repo.save(userDetail);
		} catch (Exception e) {
			if (e.getMessage().contains("unique")) {
				throw new UserDetailAlreadyPresentException("User Already Present");
			} else {
				throw new UserDetailCannotBeCreatedException(e.getMessage());
			}
		}
	}
	
	@Override
	public StringToObject editUserDetailPassword(Long userDetailId, UserDetail userDetail) {
		Long idFromDB = repo.findByUsername(userDetail.getUsername()).get().getUserDetailsId();
		String passwordFRomDB = repo.findByUsername(userDetail.getUsername()).get().getPassword();
		
		if(userDetailId.equals(idFromDB)) {
			
			if(userDetail.getPassword().equals(passwordFRomDB)) {
				throw new UserDetailAlreadyPresentException("User Password Are Same");
			}else {
				return new StringToObject("Password changed for : "+userDetailId);
			}
			
		}else {
			throw new UserDetailNotFoundException("User with id : " + userDetailId + " not found");
		}
		
	}

	@Override
	public StringToObject editUserDetailStatus(Long userDetailId, UserDetail userDetail) {
		Long idFromDB = repo.findByUsername(userDetail.getUsername()).get().getUserDetailsId();
		boolean statusFromDB = repo.findByUsername(userDetail.getUsername()).get().isEnable();

		if(userDetailId.equals(idFromDB)) {
			
			if( userDetail.isEnable()==statusFromDB) {
				throw new UserDetailAlreadyPresentException("User Enable Status Are Same.");
			}else {
				return new StringToObject("Status changed for : "+userDetailId);
			}
			
		}else {
			throw new UserDetailNotFoundException("User with id : " + userDetailId + " not found");
		}

	}

	// user must me disabled and then deleted
	@Override
	public StringToObject deleteUserDetail(Long id) {
		try {
			UserDetail detail = repo.findById(id).get();
			if (detail.isEnable()) {
				throw new UserNotEnabled("user must be disabled"); // user must be disabled to delete
			} else {
				repo.delete(detail);
				return new StringToObject("User deleted.");
			}
		} catch (UserNotEnabled e) {
			throw new UserNotEnabled(e.getMessage());
		} catch (Exception e) {
			throw new UserDetailNotFoundException("User with id : " + id + " not found");
		}
	}


}
