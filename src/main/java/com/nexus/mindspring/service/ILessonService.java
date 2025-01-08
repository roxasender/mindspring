package com.nexus.mindspring.service;

import java.util.List;
import java.util.Optional;

import com.nexus.mindspring.model.LessonModel;
import com.nexus.mindspring.model.UserLessonProgresses;

public interface ILessonService {
    List<LessonModel> getAllLessons();
    Optional<LessonModel> getLessonById(Long lessonId);
    boolean startLesson(Long userId, Long lessonId);
    UserLessonProgresses completeLesson(Long userId, Long lessonId, int score, Long timeSpent);
    LessonModel addLesson(LessonModel lesson);
    boolean deleteLesson(Long lessonId);
}
