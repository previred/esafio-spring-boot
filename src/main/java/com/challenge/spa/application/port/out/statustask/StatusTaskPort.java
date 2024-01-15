package com.challenge.spa.application.port.out.statustask;

import com.challenge.spa.application.shared.Status;
import com.challenge.spa.domain.StatusTask;

import java.util.Optional;

public interface StatusTaskPort {
  Optional<StatusTask> findByStatus(Status status);
}
