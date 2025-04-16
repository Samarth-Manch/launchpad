package com.manch.launchpad.models.request;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

import javax.validation.constraints.NotBlank;

public class PortModel {
    private String id;

    @NotBlank(message = "Port IP is required")
    private String portIp;

    @NotBlank(message = "Private Port is required")
    private int privatePort;

    @NotBlank(message = "Public Port Id is required")
    private int publicPort;

    @NotBlank(message = "Service Id is required")
    private String serviceId;

}
