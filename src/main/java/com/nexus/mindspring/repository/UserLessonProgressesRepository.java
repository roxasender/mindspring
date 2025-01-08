package com.nexus.mindspring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nexus.mindspring.model.UserLessonProgresses;

@Repository
public interface UserLessonProgressesRepository extends JpaRepository<UserLessonProgresses, Long> {

    @Query(value = "SELECT * FROM user_lesson_progress p WHERE p.user_id = :userId AND p.lesson_id = :lessonId", nativeQuery = true)
    UserLessonProgresses findByUserIdAndLessonId(@Param("userId") Long userId, @Param("lessonId") Long lessonId);

    @Query(value = "SELECT * FROM user_lesson_progress p WHERE p.user_id = :user_id", nativeQuery = true)
    List<UserLessonProgresses> findByUserId(@Param("user_id") Long userId);
}
