package com.bingage.serviceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bingage.entity.User;
import com.bingage.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    private final List<User> users = new ArrayList<>();

    @Override
    public ResponseEntity<String> createUser(User user) {
        // Check if user with the same email already exists
        for (User existingUser : users) {
            if (existingUser.getEmail().equals(user.getEmail())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("User with this email already exists");
            }
        }

        // Generating JWT token (For simplicity, consider generating a real JWT token here)
        String token = "generated-jwt-token";

        // Setting creation and update date
        user.setCreated(new Date());
        user.setUpdated(new Date());

        // Setting JWT token
        user.setJwtToken(token);

        users.add(user);
        return ResponseEntity.ok("User created successfully. JWT token: " + token);
    }

    @Override
    public ResponseEntity<String> getUser(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return ResponseEntity.ok("JWT token for user with email " + email + ": " + user.getJwtToken());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with email " + email);
    }

    @Override
    public ResponseEntity<String> updateUser(User updatedUser) {
        for (User user : users) {
            if (user.getJwtToken().equals(updatedUser.getJwtToken())) {
                user.setDob(updatedUser.getDob());
                user.setAddress(updatedUser.getAddress());
                user.setUpdated(new Date());
                return ResponseEntity.ok("User details updated successfully");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with provided JWT token");
    }

    @Override
    public ResponseEntity<String> deleteUser(String jwtToken) {
        users.removeIf(user -> user.getJwtToken().equals(jwtToken));
        return ResponseEntity.ok("User deleted successfully");
    }

    @Override
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<List<User>> filterUsersByCountry(String country) {
        List<User> filteredUsers = users.stream()
                .filter(user -> user.getAddress().getCountry().equalsIgnoreCase(country))
                .collect(Collectors.toList());
        return ResponseEntity.ok(filteredUsers);
    }
}