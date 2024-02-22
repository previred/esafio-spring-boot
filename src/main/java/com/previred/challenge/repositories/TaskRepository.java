package com.previred.challenge.repositories;

import com.previred.challenge.model.TaskModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends CrudRepository<TaskModel, Integer>,
        PagingAndSortingRepository<TaskModel, Integer> {

    Optional<TaskModel> findByIdAndUserId(Integer id, Integer userId);

    Page<TaskModel> findByUserId(Integer userId, Pageable pageable);

}
