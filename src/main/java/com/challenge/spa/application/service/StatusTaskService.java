package com.challenge.spa.application.service;

import com.challenge.spa.application.port.in.FindStatusPort;
import com.challenge.spa.application.port.out.statustask.StatusTaskPort;
import com.challenge.spa.application.shared.Status;
import com.challenge.spa.domain.StatusTask;
import org.springframework.stereotype.Component;

@Component
public class StatusTaskService implements FindStatusPort {

  private final StatusTaskPort statusTaskPort;

  public StatusTaskService(StatusTaskPort statusTaskPort) {
    this.statusTaskPort = statusTaskPort;
  }

  @Override
  public StatusTask findByStatus(Status status) {
    return statusTaskPort
            .findByStatus(status)
            .orElseThrow(() -> new RuntimeException("Not found status"));
  }
}
