package com.nuevospa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nuevospa.entity.TareaEntity;


public interface TareaRepository extends JpaRepository<TareaEntity, Long> {
    
}