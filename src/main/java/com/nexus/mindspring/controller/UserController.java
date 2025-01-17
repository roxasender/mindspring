package com.nexus.mindspring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexus.mindspring.model.UserLessonProgresses;
import com.nexus.mindspring.model.UserModel;
import com.nexus.mindspring.response.UserLessonProgressDTO;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import com.nexus.mindspring.service.ILessonService;
import com.nexus.mindspring.service.IUserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;





@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<UserModel> getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username)
                .map(user -> ResponseEntity.status(HttpStatus.OK).body(user))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // delete user
    @DeleteMapping("/delete/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        if (userService.deleteUser(username)) {
            return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }
    
    
    // get all users
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserModel>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    // Get user's progress
    @GetMapping("/{userId}/progress")
    //@PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<UserLessonProgressDTO>> getUserProgress(@PathVariable Long userId) {
        List<UserLessonProgresses> userProgresses = userService.getUserProgressByUserId(userId);
        List<UserLessonProgressDTO> userLessonProgressDTOs = userProgresses.stream()
            .map(progress -> new UserLessonProgressDTO(
                progress.getLesson().getLessonId(), 
                progress.getLesson().getTitle(), 
                progress.getStatus(), 
                progress.getScore(), 
                progress.getTimeSpent()))
            .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(userLessonProgressDTOs);
    }
    
}
