package com.dhirajb7.recipe.service.userDetail;

import java.util.List;

import com.dhirajb7.recipe.factory.StringToObject;
import com.dhirajb7.recipe.modal.UserDetail;

public interface UserDetailsInterface {

	List<UserDetail> getAllUserDetails();

	UserDetail getUserDetailById(Long id);

	UserDetail addUserDetail(UserDetail userDetail);
	
	StringToObject editUserDetailPassword(Long userDetailId, UserDetail userDetail);
	
	StringToObject editUserDetailStatus(Long userDetailId, UserDetail userDetail);

	StringToObject deleteUserDetail(Long id);

}
