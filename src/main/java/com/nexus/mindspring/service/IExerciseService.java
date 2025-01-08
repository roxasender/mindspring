package com.nexus.mindspring.service;

import com.nexus.mindspring.model.ExerciseModel;

public interface IExerciseService {
    public ExerciseModel createExercise(ExerciseModel exercise);
    public ExerciseModel getExerciseByExerciseIdAndLessonId(Long exerciseId, Long lessonId);
    void deleteExercise(Long exerciseId, Long lessonId);
}
