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
    @Column(nullable = false, length = 50)
    private String header;

    @Column(nullable = false, length = 255)
    private String text;

    @Column(nullable = false)
    private Integer userId;

    @Column(nullable = false)
    private boolean isDone;

    @Column(nullable = false)
    private LocalDateTime time;
}
