package com.challenge.spa.domain;

import com.challenge.spa.application.shared.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusTask {
  private String id;
  private Status status;

  public StatusTask(Status status) {
    this.status = status;
  }

}
