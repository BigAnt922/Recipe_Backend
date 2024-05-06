package com.dhirajb7.recipe.service.userDetail;

import java.util.List;

import com.dhirajb7.recipe.factory.StringToObject;
import com.dhirajb7.recipe.factory.UserDetail.UserRoles;
import com.dhirajb7.recipe.factory.UserDetail.UserStatus;
import com.dhirajb7.recipe.modal.UserDetail;

public interface UserDetailsInterface {

	List<UserDetail> getAllUserDetails();

	UserDetail getUserDetailById(Long id);

	UserDetail addUserDetail(UserDetail userDetail);

	StringToObject editUserEnableStatus(Long id, UserStatus userStatus);

	StringToObject editUserRoles(Long id, UserRoles userRoles);

	StringToObject deleteUserDetail(Long id);

}
