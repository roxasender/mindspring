package com.nexus.mindspring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nexus.mindspring.model.ExerciseModel;
import com.nexus.mindspring.model.LessonModel;

public interface LessonRepository extends JpaRepository<LessonModel, Long> {
    Optional<LessonModel> findBylessonId(Long lessonId);
    Boolean existsBylessonId(Long lessonId);
    void deleteBylessonId(Long lessonId);

    @Query(value = "SELECT * FROM exercise WHERE lesson_id = :lessonId", nativeQuery = true)
    //@Query(value = "SELECT e.* FROM exercise e JOIN lesson l ON e.exercise_id = l.exercise_id WHERE l.lesson_id = :lessonId", nativeQuery = true)
    List<ExerciseModel> getAllExercisesByLessonId(@Param("lessonId") Long lessonId);
}
