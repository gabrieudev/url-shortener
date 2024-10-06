package com.api.url_shortener.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Objects;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ProblemDetail handleEntityNotFoundException(EntityNotFoundException e) {
        return e.toProblemDetail();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        problemDetail.setTitle("Data integrity error");
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("stacktrace", e.getStackTrace());
        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_ACCEPTABLE, Objects.requireNonNull(e.getFieldError()).getDefaultMessage());
        problemDetail.setTitle("Your request parameters didn't validate");
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("stacktrace", e.getStackTrace());
        return problemDetail;
    }

    @ExceptionHandler(UrlAlreadyExistsException.class)
    public ProblemDetail handleUrlAlreadyExistsException(UrlAlreadyExistsException e) {
        return e.toProblemDetail();
    }

}
