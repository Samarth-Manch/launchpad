package com.manch.launchpad.commons.responses;

public class LaunchpadResponse<T> {
    private String statusCode;
    private String message;
    private T data;
}
