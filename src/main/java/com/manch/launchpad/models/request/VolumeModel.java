package com.manch.launchpad.models.request;

import jakarta.persistence.Column;

import javax.validation.constraints.NotBlank;

public class VolumeModel {
    private String volumeName;

    @NotBlank(message = "Volume source is required")
    private String volumeSource;

    @NotBlank(message = "Volume destination is required")
    private String volumeDestination;

    @NotBlank(message = "Service Id is required")
    private String serviceId;

}
