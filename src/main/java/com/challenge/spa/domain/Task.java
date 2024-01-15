package com.challenge.spa.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
  private String id;
  private StatusTask statusTask;
  private String nameTask;
  private String descriptionTask;
}
