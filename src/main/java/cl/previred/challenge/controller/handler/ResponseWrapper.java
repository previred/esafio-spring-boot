package cl.previred.challenge.controller.handler;

import java.time.LocalDateTime;

public record ResponseWrapper<T>(LocalDateTime timestamp, T payload) {
}
