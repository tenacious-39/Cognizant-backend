package com.vitacure.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitacure.authservice.model.User;

/**
 * 
 * @author Kritidipta Ghosh
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
}
