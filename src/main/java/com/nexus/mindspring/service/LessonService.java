package com.nexus.mindspring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nexus.mindspring.model.ExerciseModel;
import com.nexus.mindspring.model.LessonModel;
import com.nexus.mindspring.model.UserLessonProgresses;
import com.nexus.mindspring.model.UserModel;
import com.nexus.mindspring.repository.LessonRepository;
import com.nexus.mindspring.repository.UserLessonProgressesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LessonService implements ILessonService {
    private final LessonRepository lessonRepository;
    private final UserLessonProgressesRepository userLessonProgressesRepository;
    private final UserService userService;

    @Override
    public List<LessonModel> getAllLessons() {
        return lessonRepository.findAll();
    }

    @Override
    public Optional<LessonModel> getLessonById(Long lessonId) {
        return lessonRepository.findBylessonId(lessonId);
    }

    @Override
    public boolean startLesson(Long userId, Long lessonId) {
        UserModel user = userService.getUserById(userId).get();
        LessonModel lesson = lessonRepository.findBylessonId(lessonId).get();
        if (lesson != null && user != null) {
            UserLessonProgresses userLessonProgresses = new UserLessonProgresses();
            userLessonProgresses.setUser(user);
            userLessonProgresses.setLesson(lesson);
            userLessonProgresses.setStatus("in-progress");
            userLessonProgressesRepository.save(userLessonProgresses);
            return true;
        }
        return false;
    }

    @Override
    public UserLessonProgresses completeLesson(Long userId, Long lessonId, int score, Long timeSpent) {
        UserLessonProgresses userLessonProgresses = userLessonProgressesRepository.findByUserIdAndLessonId(userId, lessonId);
        if (userLessonProgresses != null) {
            userLessonProgresses.setStatus("completed");
            userLessonProgresses.setScore(score);
            userLessonProgresses.setTimeSpent(timeSpent);
            return userLessonProgressesRepository.save(userLessonProgresses);
        }
        return null;
    }

    @Override
    public LessonModel addLesson(LessonModel lesson) {
        return lessonRepository.save(lesson);
    }

    @Override
    @Transactional
    public boolean deleteLesson(Long lessonId) {
        if (lessonRepository.existsBylessonId(lessonId)) {
            lessonRepository.deleteBylessonId(lessonId);
            return true;
        }
        return false;
    }

    @Override
    public List<ExerciseModel> getAllExercisesByLessonId(Long lessonId) {
        return lessonRepository.getAllExercisesByLessonId(lessonId);
    }

}
