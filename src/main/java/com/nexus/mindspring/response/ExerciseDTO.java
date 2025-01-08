package com.nexus.mindspring.response;

import com.nexus.mindspring.model.LessonModel;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExerciseDTO {
    private Long exerciseId;

    private String title;
    private String content;
    private int correctAnswer;
    private int point;

    private LessonModel lesson;

    public ExerciseDTO(Long exerciseId, String title, String content, int correctAnswer, int point, LessonModel lesson) {
        this.exerciseId = exerciseId;
        this.title = title;
        this.content = content;
        this.correctAnswer = correctAnswer;
        this.point = point;
        this.lesson = lesson;
    }

    public ExerciseDTO(Long exerciseId, String title, String content, int correctAnswer, int point) {
        this.exerciseId = exerciseId;
        this.title = title;
        this.content = content;
        this.correctAnswer = correctAnswer;
        this.point = point;
    }

    public ExerciseDTO(Long exerciseId, String title, String content) {
        this.exerciseId = exerciseId;
        this.title = title;
        this.content = content;
    }
}
