package com.nexus.mindspring.service;

import java.util.Optional;
import java.util.List;

import com.nexus.mindspring.model.UserModel;

public interface IUserService {
    Optional<UserModel> getUserByUsername(String username);
    UserModel saveUser(UserModel user);
    boolean isUsernameExists(String username);
    boolean isEmailExists(String email);
    UserModel registerUser(UserModel user);
    List<UserModel> getAllUsers();
    boolean deleteUser(String username);
}
