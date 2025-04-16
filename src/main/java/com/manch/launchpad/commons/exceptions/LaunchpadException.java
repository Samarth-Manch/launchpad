package com.manch.launchpad.commons.exceptions;

import com.manch.launchpad.commons.responses.ResponseInfoEnum;
import lombok.Getter;

@Getter
public class LaunchpadException extends RuntimeException {
    private final ResponseInfoEnum responseInfo;
    private final Object data; // Optional extra data

    public LaunchpadException(ResponseInfoEnum responseInfo, String customMessage) {
        this(responseInfo, customMessage, null);
    }

    public LaunchpadException(ResponseInfoEnum responseInfo, String customMessage, Object data) {
        super(customMessage != null ? customMessage : responseInfo.getMessage());
        this.responseInfo = responseInfo;
        this.data = data;
    }
}
