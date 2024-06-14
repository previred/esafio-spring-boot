package com.desafio.spring.ec.bs.repository;

import com.desafio.spring.ec.bs.repository.common.GenericRepository;
import com.desafio.spring.ec.ds.entity.Tasks;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends GenericRepository<Tasks, Long> {
}
