package com.example.desafio_spring_boot.domain.status_task;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

import com.example.desafio_spring_boot.domain.task.Task;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "status_task")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusNames statusName;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    @JsonBackReference
    private Task task;

    @Column(nullable = false)
    private LocalDateTime changedAt;
}
