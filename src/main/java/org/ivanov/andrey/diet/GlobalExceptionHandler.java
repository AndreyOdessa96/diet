package org.ivanov.andrey.diet;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.ivanov.andrey.diet.ErrorCode.INTERNAL_ERROR;
import static org.ivanov.andrey.diet.ErrorCode.INVALID_PARAMS;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex) {
        String message = ex.getConstraintViolations()
                .stream()
                .findFirst()
                .map(ConstraintViolation::getMessage)
                .orElse("Validation failed");

        ErrorResponse error = new ErrorResponse(INVALID_PARAMS, message);
        return new ResponseEntity<>(error, BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAnyException(Exception ex) {
        logger.error("Unexpected error occurred", ex);

        ErrorResponse error = new ErrorResponse(INTERNAL_ERROR, "Unexpected error: " + ex.getMessage());
        return new ResponseEntity<>(error, INTERNAL_SERVER_ERROR);
    }
}
