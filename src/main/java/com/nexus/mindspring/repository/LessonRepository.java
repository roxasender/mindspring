package com.nexus.mindspring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexus.mindspring.model.LessonModel;

public interface LessonRepository extends JpaRepository<LessonModel, Long> {
    Optional<LessonModel> findBylessonId(Long lessonId);
    Boolean existsBylessonId(Long lessonId);
    void deleteBylessonId(Long lessonId);
}
