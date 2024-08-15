package com.dhirajb7.recipe.modal;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userDetailsId;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;

	private List<String> roles; // must be ["ROLE_XYZ","ROLE_ABC"]

	private boolean enable;

}
