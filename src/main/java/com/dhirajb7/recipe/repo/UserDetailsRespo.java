package com.dhirajb7.recipe.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dhirajb7.recipe.modal.UserDetail;

import jakarta.transaction.Transactional;

@Repository
public interface UserDetailsRespo extends JpaRepository<UserDetail, Long> {

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE user_details SET enable=true WHERE user_details_id=:userDetailsId")
	void enableInUserDetails(@Param("userDetailsId") Long userDetailsId);

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "NSERT INTO users(username,password,enabled)VALUES (:username, :password,true)")
	void addUserInUsers(@Param("username") String username, @Param("password") String password);

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "INSERT INTO authorities(username,authority)VALUES (:username, :authority)")
	void addAuthorityInAuthorities(@Param("username") String username, @Param("authority") String authority);

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE user_details SET enable=false WHERE user_details_id=:userDetailsId")
	void disableInUserDetails(@Param("userDetailsId") Long userDetailsId);

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "DELETE FROM users WHERE username =:username")
	void deleteUserInUsers(@Param("username") String username);

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "DELETE FROM authorities WHERE username =:username")
	void deleteAuthorityInAuthorotiesBasedOnUsername(@Param("username") String username);

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE user_details SET roles=:roles WHERE user_details_id=:userDetailsId")
	void updateRoleInUserDetails(@Param("userDetailsId") Long userDetailsId, @Param("roles") List<String> roles);

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "DELETE FROM authorities WHERE username =:username AND authority=:authority")
	void deleteAuthorityByAuthority(@Param("username") String username, @Param("authority") String authority);

}
