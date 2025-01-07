package com.nexus.mindspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexus.mindspring.model.UserModel;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    // Find by username
    Optional<UserModel> findByUsername(String username);
    
    // Check if username exists
    Boolean existsByUsername(String username);

    // Check if email exists
    Boolean existsByEmail(String email);

    // Delete user by username
    void deleteByUsername(String username);

}
