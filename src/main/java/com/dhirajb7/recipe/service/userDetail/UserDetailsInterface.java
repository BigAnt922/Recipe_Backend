package com.dhirajb7.recipe.service.userDetail;

import java.util.List;

import com.dhirajb7.recipe.entity.UserDetail;
import com.dhirajb7.recipe.modal.StringToObject;
import com.dhirajb7.recipe.modal.UserDetail.UserPassword;

public interface UserDetailsInterface {

	List<UserDetail> getAllUserDetails();

	UserDetail getUserDetailById(Long id);

	UserDetail addUserDetail(UserDetail userDetail);
	
	StringToObject editUserDetailPassword(Long userDetailId, UserPassword userPassword);
	
	StringToObject editUserDetailStatus(Long userDetailId);

	StringToObject deleteUserDetail(Long id);

}
