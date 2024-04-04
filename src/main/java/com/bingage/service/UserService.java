package com.bingage.service;


import java.util.List;

import org.springframework.http.ResponseEntity;

import com.bingage.entity.User;

public interface UserService {
	
    ResponseEntity<String> createUser(User user);

    ResponseEntity<String> getUser(String email);

    ResponseEntity<String> updateUser(User updatedUser);

    ResponseEntity<String> deleteUser(String jwtToken);

    ResponseEntity<List<User>> getAllUsers();

    ResponseEntity<List<User>> filterUsersByCountry(String country);
    
}