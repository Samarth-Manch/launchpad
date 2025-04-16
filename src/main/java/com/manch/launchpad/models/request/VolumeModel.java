package com.manch.launchpad.models.request;

import com.manch.launchpad.entities.VolumeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
        return VolumeModel.builder()
                .volumeName(volumeEntity.getVolumeName())
                .volumeDestination(volumeEntity.getVolumeDestination())
                .volumeSource(volumeEntity.getVolumeSource())
                .serviceId(volumeEntity.getServiceId())
                .build();
    }

}
