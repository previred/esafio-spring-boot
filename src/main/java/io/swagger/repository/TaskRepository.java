package io.swagger.repository;

import io.swagger.entity.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    @Query("select t from TaskEntity t " +
            "where " +
            "(:title is null or t.title like %:title%)" +
            "and (:description is null or t.description like %:description%)" +
            "and (:status is null or t.status.id = :status)")
    Page<TaskEntity> filter(
            @Param("title") String title,
            @Param("description") String description,
            @Param("status") Long status,
            Pageable pageable);

}
