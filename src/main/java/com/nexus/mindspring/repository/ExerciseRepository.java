package com.nexus.mindspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nexus.mindspring.model.ExerciseModel;

@Repository
public interface ExerciseRepository extends JpaRepository<ExerciseModel, Long> {
    @Query(value = "SELECT * FROM exercise WHERE id = :exerciseId", nativeQuery = true)
    ExerciseModel findByExerciseId(@Param("exerciseId") Long exerciseId);
}
