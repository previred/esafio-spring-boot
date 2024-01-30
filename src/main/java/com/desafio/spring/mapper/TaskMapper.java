package com.desafio.spring.mapper;

import com.desafio.spring.repository.dao.Task;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class TaskMapper {

    public static  List<org.openapitools.model.Task> DaoToModel(List<com.desafio.spring.repository.dao.Task> lstDao) {
        List<org.openapitools.model.Task> lstModel = new ArrayList<>();

        for (com.desafio.spring.repository.dao.Task dao:
             lstDao) {
            org.openapitools.model.Task model = DaoToModel(dao);
            lstModel.add(model);
        }
        return lstModel;
    }

    public static  org.openapitools.model.Task DaoToModel(com.desafio.spring.repository.dao.Task dao) {
        org.openapitools.model.Task model = new org.openapitools.model.Task();
        model.setId(dao.getId());
        model.setName(dao.getNameTask());
        model.setDescription(dao.getDescriptionTask());
        model.setPriority(dao.getTaskPriority());
        model.setUser(dao.getUser() != null ?  dao.getUser().getName() : null);
        model.setStatus(dao.getStatusTask() != null ?  dao.getStatusTask().getNameStatusTask() : null);
        return model;
    }

    public static Task ModelToDAO(org.openapitools.model.Task model, long idUser, long idStatus) {
        com.desafio.spring.repository.dao.Task taskDao = new com.desafio.spring.repository.dao.Task();
        com.desafio.spring.repository.dao.User userDao = new com.desafio.spring.repository.dao.User();
        com.desafio.spring.repository.dao.TaskStatus taskStatusDao = new com.desafio.spring.repository.dao.TaskStatus();

        userDao.setId(idUser);
        taskStatusDao.setId(idStatus);

        if(StringUtils.hasText(model.getId())){
            taskDao.setId(model.getId());
        }
        taskDao.setNameTask(model.getName());
        taskDao.setTaskPriority(model.getPriority());
        taskDao.setDescriptionTask(model.getDescription());
        taskDao.setStatusTask(taskStatusDao);
        taskDao.setUser(userDao);
        return taskDao;
    }
}
