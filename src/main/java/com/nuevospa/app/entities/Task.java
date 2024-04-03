package com.nuevospa.app.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String detail;
    @Column(updatable = false)
    private LocalDateTime createAt = LocalDateTime.now();
    @Column(updatable = true, insertable = false)
    private LocalDateTime deleteAt;
    @ManyToOne
    @JoinColumn(name = "task_status_id")
    private TaskStatus taskStatus;
}