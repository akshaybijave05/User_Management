package com.bingage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bingage.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
    User findByEmail(String email);
    
}
