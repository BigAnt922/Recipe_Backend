package com.dhirajb7.recipe.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dhirajb7.recipe.modal.UserDetail;

public class UserDetailsToUserDetailsDB implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String pwd;
	private List<GrantedAuthority> authorities;
	private boolean status;

	public UserDetailsToUserDetailsDB(UserDetail userDetail) {
		this.name = userDetail.getUsername();
		this.pwd = userDetail.getPassword();
		this.authorities = Arrays.stream(userDetail.getRole().split(","))
						   .map(a-> new SimpleGrantedAuthority(a))
						   .collect(Collectors.toList());
		this.status = userDetail.isEnable();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.pwd;
	}

	@Override
	public String getUsername() {
		return this.name;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.status;
	}

}
