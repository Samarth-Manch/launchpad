package com.manch.launchpad.models.request;

import com.manch.launchpad.entities.VolumeEntity;

import javax.validation.constraints.NotBlank;

public class VolumeModel {
    private String volumeName;

    @NotBlank(message = "Volume source is required")
    private String volumeSource;

    @NotBlank(message = "Volume destination is required")
    private String volumeDestination;

    @NotBlank(message = "Service Id is required")
    private String serviceId;

    public static VolumeEntity toEntity(VolumeModel volumeModel) {
        return VolumeEntity.builder()
                .volumeName(volumeModel.volumeName)
                .volumeDestination(volumeModel.volumeDestination)
                .volumeSource(volumeModel.volumeSource)
                .serviceId(volumeModel.serviceId)
                .build();
    }

    public static VolumeModel fromEntity(VolumeEntity volumeEntity) {
        return VolumeEntity.builder()
                .volumeName(volumeEntity.volumeName)
                .volumeDestination(volumeEntity.volumeDestination)
                .volumeSource(volumeEntity.volumeSource)
                .serviceId(volumeEntity.serviceId)
                .build();
    }

}
