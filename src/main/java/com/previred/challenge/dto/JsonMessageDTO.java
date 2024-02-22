package com.previred.challenge.dto;

public record JsonMessageDTO(String message) {

    public static JsonMessageDTO ok() {
        return new JsonMessageDTO("ok");
    }

}
