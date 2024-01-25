package com.spa.task.service;

import java.util.List;

public interface Crud<T> {

    List<T> findAll();
    T findById(Long id);
    T create(T obj);
    T update(T obj, Long id);
    void delete(Long id);

}
