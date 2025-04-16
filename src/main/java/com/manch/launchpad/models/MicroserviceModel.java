package com.manch.launchpad.models;

import com.manch.launchpad.entities.MicroserviceEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MicroserviceModel {

    Integer id;

    @NotBlank(message = "Microservice name is required")
    String microserviceName;

    @NotBlank(message = "User ID is required")
    String userId;

    public static MicroserviceEntity toEntity(MicroserviceModel microserviceModel) {
        return MicroserviceEntity.builder()
                .microserviceName(microserviceModel.getMicroserviceName())
                .userId(microserviceModel.getUserId())
                .microserviceName(microserviceModel.getMicroserviceName())
                .build();
    }

    public static MicroserviceModel fromEntity(MicroserviceEntity microserviceEntity) {
        return MicroserviceModel.builder()
                .id(microserviceEntity.getId())
                .microserviceName(microserviceEntity.getMicroserviceName())
                .userId(microserviceEntity.getUserId())
                .microserviceName(microserviceEntity.getMicroserviceName())
                .build();
    }
}
