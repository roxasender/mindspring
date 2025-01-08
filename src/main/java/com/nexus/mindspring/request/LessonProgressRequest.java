package com.nexus.mindspring.request;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LessonProgressRequest {
    @NotBlank
    private Long userId;
    @NotBlank
    private Long lessonId;
    private int score;
    private long timeSpent; // in miniutes
    private List<Integer> exerciseAnwsers;
}
