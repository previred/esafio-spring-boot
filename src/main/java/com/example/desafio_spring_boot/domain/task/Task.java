package com.example.desafio_spring_boot.domain.task;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

import com.example.desafio_spring_boot.domain.status_task.StatusTask;
import com.example.desafio_spring_boot.domain.user.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "El título es requerido")
    private String title;

    @Column()
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull(message = "El usuario asignado es requerido")
    private User idUser;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<StatusTask> statusHistory;

    @Transient
    public StatusTask getCurrentStatus() {
        return statusHistory.stream()
                .max((s1, s2) -> s1.getChangedAt().compareTo(s2.getChangedAt()))
                .orElse(null);
    }
}
