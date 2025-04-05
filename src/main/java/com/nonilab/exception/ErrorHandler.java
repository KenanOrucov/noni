package com.nonilab.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.List;

import static com.nonilab.model.constants.ExceptionConstants.VALIDATION_EXCEPTION;
import static com.nonilab.model.enums.ExceptionMessages.HTTP_METHOD_NOT_ALLOWED;
import static com.nonilab.model.enums.ExceptionMessages.UNEXPECTED_EXCEPTION;
import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ErrorResponse handle(Exception ex) {
        log.error("Exception {}", ex);
        return new ErrorResponse(UNEXPECTED_EXCEPTION.getCode(), UNEXPECTED_EXCEPTION.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(METHOD_NOT_ALLOWED)
    public ErrorResponse handle(HttpRequestMethodNotSupportedException ex) {
        log.error("Exception {}", ex);
        return new ErrorResponse(HTTP_METHOD_NOT_ALLOWED.getCode(), HTTP_METHOD_NOT_ALLOWED.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse handle(NotFoundException ex) {
        log.error("NotFoundException {}", ex);
        return new ErrorResponse(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handle(BadCredentialsException ex) {
        log.error("BadCredentialsException: {}", ex);
        return new ErrorResponse("400", BAD_REQUEST.getReasonPhrase());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(FORBIDDEN)
    public ErrorResponse handle(AccessDeniedException ex) {
        log.error("AccessDeniedException: {}", ex);
        return new ErrorResponse("403", ex.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handle(AuthenticationException ex) {
        log.error("AuthException: ", ex);
        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(new ErrorResponse(null, ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handle(MethodArgumentNotValidException ex) {
        log.error("MethodArgumentNotValidException: ", ex);
        List<ValidationException> exceptions = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new ValidationException(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();
        return ErrorResponse.builder()
                .code(VALIDATION_EXCEPTION)
                .message(exceptions.get(0).getMessage())
                .build();
    }
}