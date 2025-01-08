package com.nexus.mindspring.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ExerciseRequest {
    @NotBlank
    private Long exerciseId;
    @NotBlank
    private Long userId;
    @NotBlank
    private Long lessonId;
    private String answer;
}
