package com.dhirajb7.recipe.service.userDetail;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dhirajb7.recipe.entity.UserDetail;
import com.dhirajb7.recipe.exception.userDetail.UserDetailAlreadyPresentException;
import com.dhirajb7.recipe.exception.userDetail.UserDetailCannotBeCreatedException;
import com.dhirajb7.recipe.exception.userDetail.UserDetailNotFoundException;
import com.dhirajb7.recipe.exception.userDetail.UserNotEnabled;
import com.dhirajb7.recipe.modal.StringToObject;
import com.dhirajb7.recipe.modal.UserDetail.UserPassword;
import com.dhirajb7.recipe.repo.UserDetailsRespo;

@Service
public class UserDetailService implements UserDetailsInterface {

	@Autowired
	private UserDetailsRespo repo;
	
	@Autowired
	private PasswordEncoder encoder;

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
			userDetail.setPassword(encoder.encode(userDetail.getPassword()));
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
	public StringToObject editUserDetailPassword(Long userDetailId, UserPassword userPassword) {
		
		Long idFromDB = repo.findByUsername(userPassword.getUsername()).get().getUserDetailsId();
		String passwordFRomDB = repo.findByUsername(userPassword.getUsername()).get().getPassword();
		
		
		if(userDetailId.equals(idFromDB)) {
			
			if(encoder.matches(userPassword.getPassword(), passwordFRomDB)) {
				throw new UserDetailAlreadyPresentException("New Password matches old password");
			}else {
				String newPassword = encoder.encode(userPassword.getPassword()); 
				repo.updatePassword(newPassword, userDetailId);
				return new StringToObject("Password changed for : "+userDetailId);
			}
			
		}else {
			throw new UserDetailAlreadyPresentException("Different User");
		}
		
	}

	@Override
	public StringToObject editUserDetailStatus(Long userDetailId) {
		 Optional<UserDetail> userDetail = repo.findById(userDetailId);
		 
		 if(userDetail.isPresent()) {
			 boolean statusFromDB = userDetail.get().isEnable();
			 repo.updateUserStatus(!statusFromDB,userDetailId);
			 return new StringToObject("Status has been chnaged for id : " + userDetailId);
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
