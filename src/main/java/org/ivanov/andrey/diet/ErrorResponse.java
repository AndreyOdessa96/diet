package org.ivanov.andrey.diet;

public record ErrorResponse (
    ErrorCode errorCode,
    String message
) {}
