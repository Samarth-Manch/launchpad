package com.manch.launchpad.models.request;

import com.manch.launchpad.entities.MicroserviceEntity;
import com.manch.launchpad.enums.DeploymentServiceEnum;
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

    @NotBlank(message = "Deployment is required")
    DeploymentServiceEnum deployment;

    public static MicroserviceEntity toEntity(MicroserviceModel microserviceModel) {
        return MicroserviceEntity.builder()
                .microserviceName(microserviceModel.getMicroserviceName())
                .userId(microserviceModel.getUserId())
                .microserviceName(microserviceModel.getMicroserviceName())
                .deployment(microserviceModel.getDeployment())
                .build();
    }

    public static MicroserviceModel fromEntity(MicroserviceEntity microserviceEntity) {
        return MicroserviceModel.builder()
                .id(microserviceEntity.getId())
                .microserviceName(microserviceEntity.getMicroserviceName())
                .userId(microserviceEntity.getUserId())
                .microserviceName(microserviceEntity.getMicroserviceName())
                .deployment(microserviceEntity.getDeployment())
                .build();
    }
}
