package org.nastya.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @Column(nullable = false, unique = true)
    private String header;

    @Column(nullable = false)
    private String text;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "status", nullable = false)
    private boolean isDone;

    @Column(name = "done_time", nullable = false)
    private LocalDateTime time;
}
