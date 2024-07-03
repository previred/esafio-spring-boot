package ar.com.challenge.desafio_spring_boot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TASK_STATES")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String status;

    @OneToMany(mappedBy = "status")
    private Set<Task> task = new HashSet<>();
}
