package com.mobtail.exception.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Getter
@Builder
public class ErrorResponse {

    private String message;
    private HttpStatus status;
    private Integer code;
    private Instant happenedAt;

    private ErrorResponse(final String message, final HttpStatus status, final Integer code, final Instant happenedAt) {
        this.message = message;
        this.status = status;
        this.code = code;
        this.happenedAt = happenedAt;
    }
}
