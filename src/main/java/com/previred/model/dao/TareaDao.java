package com.previred.model.dao;

import com.previred.model.entitys.Tarea;
import org.springframework.data.repository.CrudRepository;

public interface TareaDao extends CrudRepository<Tarea,Long> {

}
