package com.nexus.mindspring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexus.mindspring.model.LessonModel;
import com.nexus.mindspring.model.UserLessonProgresses;
import com.nexus.mindspring.request.LessonProgressRequest;
import com.nexus.mindspring.response.UserLessonProgressDTO;
import com.nexus.mindspring.service.ILessonService;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/lesson")
@RequiredArgsConstructor
public class LessonController {
    private final ILessonService lessonService;

    // get all lessons
    @GetMapping("/all")
    public ResponseEntity<List<LessonModel>> getAllLessons() {
        return ResponseEntity.status(HttpStatus.OK).body(lessonService.getAllLessons());
    }

    // get lesson by id
    @GetMapping("/")
    public ResponseEntity<LessonModel> getLessonById(@RequestParam Long lessonId) {
        return lessonService.getLessonById(lessonId)
                .map(lesson -> ResponseEntity.status(HttpStatus.OK).body(lesson))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // get lesson by laguage

    // start a lesson
    @PostMapping("/start")
    // @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> startALesson(@RequestBody LessonProgressRequest request) {
        if (lessonService.startLesson(request.getUserId(), request.getLessonId())) {
            return ResponseEntity.status(HttpStatus.OK).body("Lesson started successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson not found or already started");
    }
    

    // submit lesson progress
    @PostMapping("/complete")
    // @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserLessonProgressDTO> completeALesson(@RequestBody LessonProgressRequest request) {
        UserLessonProgresses progress = lessonService.completeLesson(request.getUserId(), request.getLessonId(), request.getScore(), request.getTimeSpent());
        if (progress == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        UserLessonProgressDTO progressDTO = new UserLessonProgressDTO(
            progress.getLesson().getLessonId(), 
            progress.getLesson().getTitle(), 
            progress.getStatus(),
            progress.getScore(),
            progress.getTimeSpent());
        return ResponseEntity.status(HttpStatus.OK).body(progressDTO);
    }

    // add lesson
    @PostMapping("/add")
    public ResponseEntity<String> addLesson(@RequestBody LessonModel lesson) {
        try {
            lessonService.addLesson(lesson);
            return ResponseEntity.status(HttpStatus.OK).body("Lesson added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add lesson");
        }
    }
    

    // update lesson

    // delete lesson
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteLesson(@RequestParam Long lessonId) {
        if (lessonService.deleteLesson(lessonId)) {
            return ResponseEntity.status(HttpStatus.OK).body("Lesson deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson not found");
    }

}
