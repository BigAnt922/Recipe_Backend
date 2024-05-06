package com.dhirajb7.recipe.factory.UserDetail;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRoles {
	private String username;
	private List<String> roles;
}
