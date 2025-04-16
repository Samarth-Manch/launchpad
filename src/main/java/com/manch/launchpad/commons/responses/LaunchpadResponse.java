package com.manch.launchpad.commons.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import org.apache.hc.core5.http.HttpStatus;

@Data
@Builder
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LaunchpadResponse<T> {
    private String message;
    private String responseCode;
    private int statusCode;
    private T data;

    public static <T> LaunchpadResponse<T> ok(T data) {
        return LaunchpadResponse.<T>builder()
                .message(ResponseInfoEnum.OK.getMessage())
                .responseCode(ResponseInfoEnum.OK.getResponseCode())
                .statusCode(ResponseInfoEnum.OK.getStatusCode().value())
                .data(data)
                .build();
    }

    public static <T> LaunchpadResponse<T> created(T data) {
        return LaunchpadResponse.<T>builder()
                .message(ResponseInfoEnum.CREATED.getMessage())
                .responseCode(ResponseInfoEnum.CREATED.getResponseCode())
                .statusCode(ResponseInfoEnum.CREATED.getStatusCode().value())
                .data(data)
                .build();
    }

    public static <T> LaunchpadResponse<T> badRequest(T data, ResponseInfoEnum error) {
        return LaunchpadResponse.<T>builder()
                .message(error.getMessage())
                .responseCode(ResponseInfoEnum.BAD_REQUEST.getResponseCode())
                .statusCode(ResponseInfoEnum.BAD_REQUEST.getStatusCode().value())
                .data(data)
                .build();
    }

    public static <T> LaunchpadResponse<T> unauthorized(T data) {
        return LaunchpadResponse.<T>builder()
                .message(ResponseInfoEnum.UNAUTHORIZED.getMessage())
                .responseCode(ResponseInfoEnum.UNAUTHORIZED.getResponseCode())
                .statusCode(ResponseInfoEnum.UNAUTHORIZED.getStatusCode().value())
                .data(data)
                .build();
    }

    public static <T> LaunchpadResponse<T> forbidden(T data) {
        return LaunchpadResponse.<T>builder()
                .message(ResponseInfoEnum.FORBIDDEN.getMessage())
                .responseCode(ResponseInfoEnum.FORBIDDEN.getResponseCode())
                .statusCode(ResponseInfoEnum.FORBIDDEN.getStatusCode().value())
                .data(data)
                .build();
    }

    public static <T> LaunchpadResponse<T> notFound(T data) {
        return LaunchpadResponse.<T>builder()
                .message(ResponseInfoEnum.NOT_FOUND.getMessage())
                .responseCode(ResponseInfoEnum.NOT_FOUND.getResponseCode())
                .statusCode(ResponseInfoEnum.NOT_FOUND.getStatusCode().value())
                .data(data)
                .build();
    }

    public static <T> LaunchpadResponse<T> internalServerError(T data) {
        return LaunchpadResponse.<T>builder()
                .message(ResponseInfoEnum.INTERNAL_SERVER_ERROR.getMessage())
                .responseCode(ResponseInfoEnum.INTERNAL_SERVER_ERROR.getResponseCode())
                .statusCode(ResponseInfoEnum.INTERNAL_SERVER_ERROR.getStatusCode().value())
                .data(data)
                .build();
    }

    public static <T> LaunchpadResponse<T> serviceUnavailable(T data) {
        return LaunchpadResponse.<T>builder()
                .message(ResponseInfoEnum.SERVICE_UNAVAILABLE.getMessage())
                .responseCode(ResponseInfoEnum.SERVICE_UNAVAILABLE.getResponseCode())
                .statusCode(ResponseInfoEnum.SERVICE_UNAVAILABLE.getStatusCode().value())
                .data(data)
                .build();
    }
}
