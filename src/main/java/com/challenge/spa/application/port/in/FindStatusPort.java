package com.challenge.spa.application.port.in;

import com.challenge.spa.application.shared.Status;
import com.challenge.spa.domain.StatusTask;

public interface FindStatusPort {
  StatusTask findByStatus(String status);
}
