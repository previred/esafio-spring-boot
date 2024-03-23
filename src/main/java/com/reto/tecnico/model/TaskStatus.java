package com.reto.tecnico.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class TaskStatus {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long taskStatusId;

  private String status;
  private LocalDateTime lastModified;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "taskId")
  @JsonIgnore
  private Tasks task;

}
