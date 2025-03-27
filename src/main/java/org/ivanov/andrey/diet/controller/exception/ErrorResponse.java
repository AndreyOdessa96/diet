package org.ivanov.andrey.diet.controller.exception;

public record ErrorResponse (
    ErrorCode errorCode,
    String message
) {}
