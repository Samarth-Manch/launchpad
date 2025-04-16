package com.manch.launchpad.commons.responses;

import lombok.Getter;

@Getter
public enum ResponseInfoEnum {
    OK(200, "Operation successful", "SE0200"),
    CREATED(201, "Resource created successfully", "SE0201"),

    BAD_REQUEST(400, "Bad request", "SE0400"),
    UNAUTHORIZED(401, "Unauthorized access", "SE0401"),
    FORBIDDEN(403, "Access forbidden", "SE0403"),
    NOT_FOUND(404, "Resource not found", "SE0404"),

    INTERNAL_SERVER_ERROR(500, "Internal server error", "SE0500"),
    SERVICE_UNAVAILABLE(503, "Service unavailable", "SE0503");

    private final int status;
    private final String message;
    private final String responseCode;

    ResponseInfoEnum(int status, String message, String responseCode) {
        this.status = status;
        this.message = message;
        this.responseCode = responseCode;
    }

    public int getStatusCode() {
        return status;
    }
}