package org.example.studysprint.dto.task;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskResponse {

    private Long id;
    private String title;
    private String subject;
    private boolean completed;
    private Instant createdAt;
    private Instant completedAt;

}
