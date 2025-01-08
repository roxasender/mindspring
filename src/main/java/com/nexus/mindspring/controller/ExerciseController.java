package com.nexus.mindspring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nexus.mindspring.model.ExerciseModel;
import com.nexus.mindspring.request.ExerciseRequest;
import com.nexus.mindspring.response.ExerciseDTO;
import com.nexus.mindspring.service.IExerciseService;
import com.nexus.mindspring.service.ILessonService;
import com.nexus.mindspring.service.IUserLessonProgressService;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/exercise")
@RequiredArgsConstructor
public class ExerciseController {
    private final IUserLessonProgressService userLessonProgressService;
    private final IExerciseService exerciseService;
    private final ILessonService lessonService;

    // update and get answer score
    @GetMapping("/answer")
    public ResponseEntity<Boolean> isAnswerCorrect(@RequestBody ExerciseRequest request) {
        return userLessonProgressService.isAnswerCorrect(request.getUserId(), request.getLessonId(), request.getExerciseId(), request.getAnswer())
                ? ResponseEntity.ok(true)
                : ResponseEntity.ok(false);
    }

    // create a new exercise
    @PostMapping("/create")
    public ResponseEntity<String> createExercise(@RequestBody ExerciseModel exercise) {
        try {
            exerciseService.createExercise(exercise);
            return ResponseEntity.ok("Exercise created successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    // get exercise by exercise id and lesson id
    @GetMapping("/get")
    public ResponseEntity<? extends ExerciseDTO> getExerciseByExerciseIdAndLessonId(@RequestBody ExerciseRequest request) {
        ExerciseModel exercise = exerciseService.getExerciseByExerciseIdAndLessonId(request.getExerciseId(), request.getLessonId());
        ExerciseDTO exerciseDTO = new ExerciseDTO(exercise.getExerciseId(), exercise.getTitle(), exercise.getContent());
        return exercise != null ? ResponseEntity.ok(exerciseDTO) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(exerciseDTO);
    }


    // get all exercises by lesson id
    @GetMapping("/all")
    public ResponseEntity<List<ExerciseDTO>> getAllExercisesByLessonId(@RequestBody ExerciseRequest request) {
        List<ExerciseModel> exercises = lessonService.getAllExercisesByLessonId(request.getLessonId());
        List<ExerciseDTO> exerciseDTOs = exercises.stream()
            .map(exercise -> new ExerciseDTO(
                exercise.getExerciseId(),
                exercise.getTitle(),
                exercise.getContent(),
                exercise.getCorrectAnswer(),
                exercise.getPoint()
            )).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(exerciseDTOs);
    }
    

    // delete exercise by exercise id and lesson id
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteExercise(@RequestParam ExerciseRequest request) {
        try {
            exerciseService.deleteExercise(request.getExerciseId(), request.getLessonId());
            return ResponseEntity.ok("Exercise deleted successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    // update exercise by exercise id and lesson id (put)
    
    
}
