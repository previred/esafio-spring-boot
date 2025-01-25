package com.example.desafio_spring_boot.domain.task;

import com.example.desafio_spring_boot.domain.user.User;

import lombok.Data;

@Data
public class TaskRequestDto {
    private String title;
    private String description;
    private Long idUser;

    public Task toTask(Long id) {
        return transformeToTask(id);
    }

    public Task toTask() {
        return transformeToTask(null);
    }

    private Task transformeToTask(Long id) {
        User user = new User();
        user.setId(idUser);
        Task task = new Task();
        if (id != null) {
            task.setId(id);
        }
        task.setTitle(title);
        task.setDescription(description);
        task.setIdUser(user);
        return task;
    }
}
