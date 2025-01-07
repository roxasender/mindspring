package com.nexus.mindspring.service;

import java.util.Optional;

import com.nexus.mindspring.model.UserModel;

public interface IUserService {
    Optional<UserModel> getUserByUsername(String username);
    UserModel saveUser(UserModel user);
    boolean isUsernameExists(String username);
    boolean isEmailExists(String email);
    UserModel registerUser(UserModel user);
    // void deleteUser(UserModel user);
}
