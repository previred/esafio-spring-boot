package cl.previred.challenge.controller.handler;


import java.time.LocalDateTime;

public record ErrorResponse(LocalDateTime timestamp, String error) {

}
