package com.test.previred.infrastructure.adapter.h2.task;

import com.test.previred.domain.model.task.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;



public interface TaskMapper {

        public static Task toDomain(TaskEntity taskEntity) {
            if (taskEntity == null) {
                return null;
            }

            Task task = new Task();
            task.setId(String.valueOf(taskEntity.getId()));
            task.setDescription(taskEntity.getDescription());
            task.setStatus(taskEntity.getStatus().getName()); // Supongo que TaskStatusEntity tiene un campo 'name'

            return task;
        }

        public static TaskEntity toEntity(Task task) {
            if (task == null) {
                return null;
            }

            TaskEntity taskEntity = new TaskEntity();
            taskEntity.setDescription(task.getDescription());

            // Aquí debes configurar el status utilizando un servicio o un repositorio
            // taskEntity.setStatus(taskStatusRepository.findByName(task.getStatus()));

            return taskEntity;
        }
    }