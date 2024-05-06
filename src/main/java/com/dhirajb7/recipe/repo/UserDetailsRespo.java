package com.dhirajb7.recipe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dhirajb7.recipe.modal.UserDetail;

@Repository
public interface UserDetailsRespo extends JpaRepository<UserDetail, Long> {

}
