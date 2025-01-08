package com.nexus.mindspring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "exercise")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exerciseId;

    private String title;
    private String content;
    private int correctAnswer;
    private int point;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private LessonModel lesson;
}
