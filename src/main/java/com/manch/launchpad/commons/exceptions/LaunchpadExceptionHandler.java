package com.manch.launchpad.commons.exceptions;

import com.manch.launchpad.commons.responses.LaunchpadResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class LaunchpadExceptionHandler {
    LaunchpadResponse<?> response;
    public ResponseEntity<LaunchpadResponse<?>> handle(Throwable exception) {
        if (exception instanceof LaunchpadException launchpadException) {
            response = LaunchpadResponse.builder()
                    .responseCode(launchpadException.getResponseInfo().getResponseCode())
                    .data(launchpadException.getData())
                    .message(launchpadException.getMessage() != null ? exception.getMessage() : launchpadException.getResponseInfo().getMessage())
                    .statusCode(launchpadException.getResponseInfo().getStatusCode().value())
                    .build();

            return new ResponseEntity<>(response, launchpadException.getResponseInfo().getStatusCode());
        }
        response = LaunchpadResponse.internalServerError(null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
