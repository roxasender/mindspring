package com.nexus.mindspring.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_lesson_progress")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLessonProgresses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userLessonProgressId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private LessonModel lesson;

    @ManyToMany
    @JoinTable(
        name = "user_lesson_progress_exercise",
        joinColumns = @JoinColumn(name = "user_lesson_progress_id"),
        inverseJoinColumns = @JoinColumn(name = "exercise_id")
    )
    private List<ExerciseModel> exercises;

    private String status; // "completed", "in-progress"
    private int score;
    private long timeSpent; // in minutes
}
