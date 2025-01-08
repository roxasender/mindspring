package com.nexus.mindspring.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLessonProgressDTO {
    private Long lessonId;
    private String lessonTitle;
    private String status;
    private int score;
    private long timeSpent;

    public UserLessonProgressDTO(Long lessonId, String lessonTitle, String status) {
        this.lessonId = lessonId;
        this.lessonTitle = lessonTitle;
        this.status = status;
    }

    public UserLessonProgressDTO(Long lessonId, String lessonTitle, String status, int score, Long timeSpent) {
        this.lessonId = lessonId;
        this.lessonTitle = lessonTitle;
        this.status = status;
        this.score = score;
        this.timeSpent = timeSpent;
    }
}
