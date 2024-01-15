package com.challenge.spa.adapter.out.persistence.statustask;

import com.challenge.spa.application.shared.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringStatusTaskRepository extends JpaRepository<StatusTaskEntity, String> {
  Optional<StatusTaskEntity> findByStatus(Status status);
}
