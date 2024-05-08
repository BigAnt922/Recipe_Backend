package com.dhirajb7.recipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhirajb7.recipe.factory.UserDetail.UserRoles;
import com.dhirajb7.recipe.factory.UserDetail.UserStatus;
import com.dhirajb7.recipe.modal.UserDetail;
import com.dhirajb7.recipe.service.userDetail.UserDetailsInterface;

@RestController
@RequestMapping(path = "/user")
public class UserDetailController {

	@Autowired
	private UserDetailsInterface service;

	@GetMapping(path = "/")
	public ResponseEntity<Object> getAllUsers() {
		return new ResponseEntity<Object>(service.getAllUserDetails(), HttpStatus.OK);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Object> getUserById(@PathVariable Long id) {
		return new ResponseEntity<Object>(service.getUserDetailById(id), HttpStatus.OK);
	}

	@PostMapping(path = "/")
	public ResponseEntity<Object> addUser(@RequestBody UserDetail userDetail) {
		return new ResponseEntity<Object>(service.addUserDetail(userDetail), HttpStatus.CREATED);
	}

	@PutMapping(path = "/status/{id}")
	public ResponseEntity<Object> editUserStatus(@PathVariable Long id, @RequestBody UserStatus userStatus) {
		return new ResponseEntity<Object>(service.editUserEnableStatus(id, userStatus), HttpStatus.OK);
	}

	@PutMapping(path = "/roles/{id}")
	public ResponseEntity<Object> editUserRoles(@PathVariable Long id, @RequestBody UserRoles userRoles) {
		return new ResponseEntity<Object>(service.editUserRoles(id, userRoles), HttpStatus.OK);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> deleteDisabledUsers(@PathVariable Long id) {
		return new ResponseEntity<Object>(service.deleteUserDetail(id), HttpStatus.OK);
	}
}
