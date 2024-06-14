package com.desafio.spring.ec.bs.repository;

import com.desafio.spring.ec.bs.repository.common.GenericRepository;
import com.desafio.spring.ec.ds.entity.Status;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends GenericRepository<Status, Long> {
}
