package com.desafio.spring.ec.bs.services.common;

import com.desafio.spring.ec.bs.exception.ModelNotFoundException;
import com.desafio.spring.ec.bs.repository.common.GenericRepository;

import java.io.Serializable;
import java.util.List;

public abstract class CRUDImpl<T, ID extends Serializable> implements ICRUD<T, ID> {

    protected abstract GenericRepository<T, ID> getRepo();

    @Override
    public T save(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public T update(ID id, T t) throws Exception {
        getRepo().findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
        return getRepo().save(t);
    }

    @Override
    public List<T> findAll() throws Exception {
        return getRepo().findAll();
    }

    @Override
    public T findById(ID id) throws Exception {
        return getRepo().findById(id).orElseThrow(()-> new ModelNotFoundException("ID NOT FOUND: " + id));
    }

    @Override
    public void delete(ID id) throws Exception {
        getRepo().findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
        getRepo().deleteById(id);
    }
}
