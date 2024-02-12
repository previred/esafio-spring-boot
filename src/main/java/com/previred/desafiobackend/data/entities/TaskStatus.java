package com.previred.desafiobackend.data.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * @author Miguel Angel
 * @since v1.0.0
 */


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "task_status")
@Table(name = "task_status")
public class TaskStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "taskStatus")
    private List<Task> tasks;
}
