package com.nexus.mindspring.service;

import org.springframework.stereotype.Service;

import com.nexus.mindspring.model.ExerciseModel;
import com.nexus.mindspring.repository.ExerciseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExerciseService implements IExerciseService {
    private final ExerciseRepository exerciseRepository;

    @Override
    public ExerciseModel createExercise(ExerciseModel exercise) {
        return exerciseRepository.save(exercise);
    }

    @Override
    public ExerciseModel getExerciseByExerciseIdAndLessonId(Long exerciseId, Long lessonId) {
        return exerciseRepository.findByExerciseIdAndLessonId(exerciseId, lessonId);
    }

    @Override
    public void deleteExercise(Long exerciseId, Long lessonId) {
        exerciseRepository.deleteByExerciseIdAndLessonId(exerciseId, lessonId);   
    }
}
