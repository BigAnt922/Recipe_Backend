package com.dhirajb7.recipe.service.userDetail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhirajb7.recipe.exception.userDetail.UserDetailAlreadyPresentException;
import com.dhirajb7.recipe.exception.userDetail.UserDetailCannotBeCreatedException;
import com.dhirajb7.recipe.exception.userDetail.UserDetailNotFoundException;
import com.dhirajb7.recipe.exception.userDetail.UserNotEnabled;
import com.dhirajb7.recipe.factory.StringToObject;
import com.dhirajb7.recipe.factory.UserDetail.UserRoles;
import com.dhirajb7.recipe.factory.UserDetail.UserStatus;
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
		} catch (UserDetailNotFoundException e) {
			throw new UserDetailNotFoundException("User with id : " + id + " not found");
		}
	}

	@Override
	public UserDetail addUserDetail(UserDetail userDetail) {
		try {
			userDetail.setUsername(userDetail.getUsername().toLowerCase());
			userDetail.setEmail(userDetail.getEmail().toLowerCase());
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
	public StringToObject editUserEnableStatus(Long id, UserStatus userStatus) {
		try {
			UserDetail detail = repo.findById(id).get();
			String usernameFromDB = detail.getUsername();
			String usernameFromRequest = userStatus.getUsername();
			if (usernameFromDB.equalsIgnoreCase(usernameFromRequest)) {
				if (detail.isEnable()) {
					if (userStatus.isStatus()) {
						return new StringToObject("no change");
					} else {
						repo.disableInUserDetails();
						repo.deleteInUsers(usernameFromRequest);
						repo.deleteInAuthoroties(usernameFromRequest);
						return new StringToObject("under construction");
					}
				} else {
					if (userStatus.isStatus()) {
						repo.enableInUserDetails();
						repo.enableInUsers();
						repo.enableInAuthoroties();
						return new StringToObject("under construction");
					} else {
						return new StringToObject("no change");
					}
				}
			} else {
				throw new UserNotEnabled("username is not proper");
			}
		} catch (UserDetailNotFoundException e) {
			throw new UserDetailNotFoundException("User with id : " + id + " not found");
		}
	}

	@Override
	public StringToObject editUserRoles(Long id, UserRoles userRoles) {
		try {
			UserDetail detail = repo.findById(id).get();
			String usernameFromDB = detail.getUsername();
			String usernameFromRequest = userRoles.getUsername();
			if (usernameFromDB.equalsIgnoreCase(usernameFromRequest) && detail.isEnable()) {
				repo.updateRoleInUserDetails();
				repo.updateRoleInAuthoroties();
				return new StringToObject("under construction");
			} else {
				throw new UserNotEnabled("user not enabled or username is wrong");
			}
		} catch (UserDetailNotFoundException e) {
			throw new UserDetailNotFoundException("User with id : " + id + " not found");
		}
	}

	@Override
	public StringToObject deleteUserDetail(Long id) {
		try {
			UserDetail detail = repo.findById(id).get();
			if (detail.isEnable()) {
				String username = detail.getUsername();
				repo.deleteInUsers(username);
				repo.deleteInAuthoroties(username);
			}
			repo.delete(detail);
			return new StringToObject("User deleted.");
		} catch (Exception e) {
			throw new UserDetailNotFoundException("User with id : " + id + " not found");
		}
	}

}
