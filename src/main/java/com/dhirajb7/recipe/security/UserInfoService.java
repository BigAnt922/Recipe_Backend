package com.dhirajb7.recipe.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

import com.dhirajb7.recipe.entity.UserDetail;
import com.dhirajb7.recipe.repo.UserDetailsRespo;

public class UserInfoService implements UserDetailsManager {
	
	@Autowired
	private UserDetailsRespo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserDetail> byUsername = repo.findByUsername(username);
		if(byUsername.isPresent()) {
			return new UserDetailsToUserDetailsDB(byUsername.get());
		}else {
			throw new UsernameNotFoundException(username);
		}
	}

	@Override
	public void createUser(UserDetails user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUser(UserDetails user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(String username) {
		// TODO Auto-generated method stub

	}

	@Override
	public void changePassword(String oldPassword, String newPassword) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean userExists(String username) {
		UserDetail byUsername = repo.findByUsername(username).get();
		return byUsername.isEnable();
	}

}
