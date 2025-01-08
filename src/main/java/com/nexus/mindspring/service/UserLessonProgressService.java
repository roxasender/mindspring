package com.nexus.mindspring.service;

import org.springframework.stereotype.Service;

import com.nexus.mindspring.model.ExerciseModel;
import com.nexus.mindspring.model.UserLessonProgresses;
import com.nexus.mindspring.repository.ExerciseRepository;
import com.nexus.mindspring.repository.UserLessonProgressesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserLessonProgressService implements IUserLessonProgressService {
    private final ExerciseRepository exerciseRepository;
    private final UserLessonProgressesRepository userLessonProgressesRepository;

    @Override
    public boolean isAnswerCorrect(Long userId, Long lessonId, Long exerciseId, String answer) {
        ExerciseModel exercise = exerciseRepository.findByExerciseId(exerciseId);
        if (exercise != null && exercise.getCorrectAnswer() == Integer.parseInt(answer)) {
            UserLessonProgresses progress = userLessonProgressesRepository.findByUserIdAndLessonId(userId, lessonId);
            progress.setScore(progress.getScore() + exercise.getPoint());
            userLessonProgressesRepository.save(progress);
            return true;
        } else {
            return false;
        }
    }
    
}
