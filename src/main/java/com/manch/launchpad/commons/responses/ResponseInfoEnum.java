package com.manch.launchpad.commons.responses;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ResponseInfoEnum {
    OK(HttpStatusCode.valueOf(200), "Operation successful", "SE0200"),
    CREATED(HttpStatusCode.valueOf(201), "Resource created successfully", "SE0201"),

    BAD_REQUEST(HttpStatusCode.valueOf(400), "Bad request", "SE0400"),
    UNAUTHORIZED(HttpStatusCode.valueOf(401), "Unauthorized access", "SE0401"),
    FORBIDDEN(HttpStatusCode.valueOf(403), "Access forbidden", "SE0403"),
    NOT_FOUND(HttpStatusCode.valueOf(404), "Resource not found", "SE0404"),

    INTERNAL_SERVER_ERROR(HttpStatusCode.valueOf(500), "Internal server error", "SE0500"),
    SERVICE_UNAVAILABLE(HttpStatusCode.valueOf(503), "Service unavailable", "SE0503");

    private final HttpStatusCode status;
    private final String message;
    private final String responseCode;

    ResponseInfoEnum(HttpStatusCode status, String message, String responseCode) {
        this.status = status;
        this.message = message;
        this.responseCode = responseCode;
    }

    public HttpStatusCode getStatusCode() {
        return status;
    }
}