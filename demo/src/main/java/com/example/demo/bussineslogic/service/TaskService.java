package com.example.demo.bussineslogic.service;

import com.example.demo.model.DTO.DtoTask;
import com.example.demo.model.DTO.DtoTaskResponse;

import java.util.List;

public interface TaskService {

    void save(DtoTask task) throws Exception;

    List<DtoTaskResponse>  findAll();

    DtoTaskResponse getTaskById(Long id) throws Exception;

    void update(Long id, DtoTask oDtoTaskDetails) throws Exception;

    void delete(Long id) throws Exception ;
}
