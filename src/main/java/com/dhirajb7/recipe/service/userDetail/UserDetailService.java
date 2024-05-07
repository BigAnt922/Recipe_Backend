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
import com.dhirajb7.recipe.service.Helper;

@Service
public class UserDetailService implements UserDetailsInterface {

	@Autowired
	private UserDetailsRespo repo;

	@Autowired
	private Helper helper;

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
	public StringToObject editUserEnableStatus(Long id, UserStatus userStatus) {
		try {
			UserDetail detail = repo.findById(id).get();
			String usernameFromDB = detail.getUsername();
			String usernameFromRequest = userStatus.getUsername();
			if (usernameFromDB.equalsIgnoreCase(usernameFromRequest)) {
				if (detail.isEnable()) {
					if (userStatus.isStatus()) {
						return new StringToObject("no change to be done");
					} else {
						repo.disableInUserDetails(id);
						repo.deleteUserInUsers(usernameFromDB);
						repo.deleteAuthorityInAuthorotiesBasedOnUsername(usernameFromDB);
						return new StringToObject("usernameFromDB " + "disabled");
					}
				} else {
					if (userStatus.isStatus()) {
						String passwordFromDB = detail.getPassword();
						List<String> roles = detail.getRoles();
						repo.enableInUserDetails(id);
						repo.addUserInUsers(usernameFromDB, passwordFromDB);
						for (String role : roles) {
							repo.addAuthorityInAuthorities(usernameFromDB, role);
						}
						return new StringToObject("usernameFromDB " + " enabled.");
					} else {
						return new StringToObject("no change to be done");
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
				List<String> rolesInDB = detail.getRoles();
				List<String> rolesInRequest = userRoles.getRoles();
				repo.updateRoleInUserDetails(id, rolesInRequest); // update roles in user details DB
				List<String> roles = helper.getChangedRoles(rolesInDB, rolesInRequest);
				// update authorities
				for (String role : roles) {
					if (rolesInRequest.contains(role)) {
						repo.addAuthorityInAuthorities(usernameFromDB, role);
					} else {
						repo.deleteAuthorityByAuthority(usernameFromDB, role);
					}
				}
				return new StringToObject("Roles Updated");
			} else {
				throw new UserNotEnabled("user not enabled");
			}
		} catch (UserDetailNotFoundException e) {
			throw new UserDetailNotFoundException("User with id : " + id + " not found");
		}
	}

	// user must me disabled and then deleted
	@Override
	public StringToObject deleteUserDetail(Long id) {
		try {
			UserDetail detail = repo.findById(id).get();
			if (detail.isEnable()) {
				repo.delete(detail);
				return new StringToObject("User deleted.");
			} else {
				throw new UserNotEnabled("user must be disabled"); // user must be disabled
			}
		} catch (Exception e) {
			throw new UserDetailNotFoundException("User with id : " + id + " not found");
		}
	}

}
