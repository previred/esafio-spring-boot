package org.openapitools.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task_status", schema = "public")
public class TaskStatusEntity {

    @Id
    @Column(name = "task_status_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID taskStatusId;
    private String description;
}
