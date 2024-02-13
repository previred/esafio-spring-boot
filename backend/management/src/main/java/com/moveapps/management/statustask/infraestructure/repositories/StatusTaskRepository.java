package com.moveapps.management.statustask.infraestructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moveapps.management.statustask.infraestructure.entities.StatusTaskEntity;

@Repository
public interface StatusTaskRepository extends JpaRepository<StatusTaskEntity, String>{

}

