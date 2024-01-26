package com.test.previred.infrastructure.rest.controller.common;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Response<T> {
    private Long count;
    private T data;
    private LocalDateTime date;
    private HttpStatus status = HttpStatus.OK;
    private List<String> message = new ArrayList<>();

    public Response(T data, HttpStatus status, Long count) {
        this.data = data;
        this.status = status;
        this.count = count;
        this.date = LocalDateTime.now();
    }
}