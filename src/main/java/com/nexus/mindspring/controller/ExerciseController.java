package com.nexus.mindspring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexus.mindspring.request.ExerciseRequest;
import com.nexus.mindspring.service.IUserLessonProgressService;
import com.nexus.mindspring.service.UserLessonProgressService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/exercise")
@RequiredArgsConstructor
public class ExerciseController {
    private final IUserLessonProgressService userLessonProgressService;

    // update and get answer score
    @GetMapping
    public ResponseEntity<Boolean> getExerciseAnswer(@RequestParam ExerciseRequest request) {
        return userLessonProgressService.isAnswerCorrect(request.getUserId(), request.getLessonId(), request.getExerciseId(), request.getAnswer())
                ? ResponseEntity.ok(true)
                : ResponseEntity.ok(false);
    }


    
    
}
