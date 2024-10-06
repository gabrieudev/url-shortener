package com.api.url_shortener.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.time.Instant;

public class UrlAlreadyExistsException extends RuntimeException {
    public UrlAlreadyExistsException(String message) {
        super(message);
    }

    public ProblemDetail toProblemDetail() {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, super.getLocalizedMessage());
        problemDetail.setTitle("Already exists");
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("stacktrace", super.getStackTrace());
        return problemDetail;
    }
}
