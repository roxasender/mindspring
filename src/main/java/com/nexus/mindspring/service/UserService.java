package com.nexus.mindspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nexus.mindspring.exception.UserAlreadyExistsException;
import com.nexus.mindspring.model.Role;
import com.nexus.mindspring.model.UserLessonProgresses;
import com.nexus.mindspring.model.UserModel;
import com.nexus.mindspring.repository.RoleRepository;
import com.nexus.mindspring.repository.UserLessonProgressesRepository;
import com.nexus.mindspring.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserLessonProgressesRepository userLessonProgressesRepository;

    @Override
    public Optional<UserModel> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserModel saveUser(UserModel user) {
        return userRepository.save(user);
    }

    @Override
    public boolean isUsernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean isEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserModel registerUser(UserModel user) {
        // Check if username or email already exists
        if (userRepository.existsByUsername(user.getUsername())){
            throw new UserAlreadyExistsException(user.getUsername() + " already exists");
        }
        if (userRepository.existsByEmail(user.getEmail())){
            throw new UserAlreadyExistsException(user.getEmail() + " already exists");
        }
        System.out.println("User: " + user);

        // Encode password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Set default role to user
        Role userRole = roleRepository.findByroleName("ROLE_USER").get();
        user.setRoles(Collections.singletonList(userRole));

        return userRepository.save(user);
    }
    
    @Override
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public boolean deleteUser(String username) {
        if (userRepository.existsByUsername(username)) {
            userRepository.deleteByUsername(username);
            return true;
        }
        return false;
    }

    public Optional<UserModel> getUserById(Long userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public List<UserLessonProgresses> getUserProgressByUserId(Long userId) {
        return userLessonProgressesRepository.findByUserId(userId);
    }
}
