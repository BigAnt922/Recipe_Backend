package com.dhirajb7.recipe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dhirajb7.recipe.modal.UserDetail;

import jakarta.transaction.Transactional;

@Repository
public interface UserDetailsRespo extends JpaRepository<UserDetail, Long> {

	// enable - userDetails
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "")
	void enableInUserDetails();

	// enable - users
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "")
	void enableInUsers();

	// enable - autoroties
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "")
	void enableInAuthoroties();

	// disable - userDetails
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "")
	void disableInUserDetails();

	// disable - users
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "")
	void deleteInUsers(String username);

	// disable - autoroties
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "")
	void deleteInAuthoroties(String username);

	// update roles - userDetails - only if enable
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "")
	void updateRoleInUserDetails();

	// update roles - users - only if enable
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "")
	void updateRoleInAuthoroties();

}
