package com.nexus.mindspring.service;

public interface IUserLessonProgressService {
    boolean isAnswerCorrect(Long userId, Long lessonId, Long exerciseId, String answer);
}
