package com.nexus.mindspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nexus.mindspring.model.ExerciseModel;

@Repository
public interface ExerciseRepository extends JpaRepository<ExerciseModel, Long> {
    @Query(value = "SELECT * FROM exercise WHERE exercise_id = :exerciseId", nativeQuery = true)
    ExerciseModel findByExerciseId(@Param("exerciseId") Long exerciseId);

    @Query(value = "SELECT * FROM exercise WHERE exercise_id = :exerciseId AND lesson_id = :lessonId", nativeQuery = true)
    ExerciseModel findByExerciseIdAndLessonId(@Param("exerciseId") Long exerciseId, @Param("lessonId") Long lessonId);

    @Query(value = "DELETE FROM exercise WHERE exercise_id = :exerciseId AND lesson_id = :lessonId", nativeQuery = true)
    void deleteByExerciseIdAndLessonId(@Param("exerciseId") Long exerciseId, @Param("lessonId") Long lessonId);
}
