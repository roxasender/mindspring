package com.nexus.mindspring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
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

    private String status; // "completed", "in-progress"
    private int score;
    private long timeSpent; // in minutes
}
