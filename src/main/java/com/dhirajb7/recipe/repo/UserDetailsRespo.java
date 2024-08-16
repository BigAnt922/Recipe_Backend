package com.dhirajb7.recipe.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dhirajb7.recipe.entity.UserDetail;

import jakarta.transaction.Transactional;


@Repository
public interface UserDetailsRespo extends JpaRepository<UserDetail, Long> {
	
	Optional<UserDetail> findByUsername(String username);
	
	@Transactional
	@Modifying
	@Query("update UserDetail e set e.enable = :status where e.userDetailsId = :userDetailsId")
	void updateUserStatus(@Param("status") boolean status, @Param("userDetailsId") long userDetailsId);

	@Transactional
	@Modifying
	@Query("update UserDetail e set e.password = :newPassword where e.userDetailsId = :userDetailsId")
	void updatePassword(@Param("newPassword") String newPassword,@Param("userDetailsId") Long  userDetailId);

}
