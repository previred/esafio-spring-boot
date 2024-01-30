package com.desafio.spring.service;

import org.openapitools.model.Task;

import java.util.List;

public interface ITaskService {

    public void add(org.openapitools.model.Task model, long idUser, long idStatus);
    public Task update(org.openapitools.model.Task model, long idUser, long idStatus);

    public Task getById(String id);

    public void delete(String id);
    public List<Task> getAll();

}
