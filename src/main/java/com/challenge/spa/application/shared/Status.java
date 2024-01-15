package com.challenge.spa.application.shared;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Status {
  CREATED,
  ATTENDED,
  INVALID;

  private static Stream<String> names() {
    return Stream.of(Status.values()).map(Status::name);
  }
  public static Status getValue(String name) {
    return names()
            .filter(value -> value.equals(name))
            .map(Status::valueOf)
            .findFirst()
            .orElse(Status.INVALID);

  }

}
