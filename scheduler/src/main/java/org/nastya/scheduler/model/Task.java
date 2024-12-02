package org.nastya.scheduler.model;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String header;

    @Column(nullable = false)
    private String text;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "status", nullable = false)
    private Boolean isDone;

    @Column(name = "done_time")
    private LocalDateTime time;
}
