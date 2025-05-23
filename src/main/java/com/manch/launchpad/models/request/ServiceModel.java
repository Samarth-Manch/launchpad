package com.manch.launchpad.models.request;


import com.manch.launchpad.entities.ServiceEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceModel {

    @NotBlank(message = "Id is required")
    private String id;

    private String serviceId;

    @NotBlank(message = "Service name is required")
    private String name;

    @NotBlank(message = "Service Image is required")
    private String serviceImage;

    @NotBlank(message = "Service Description is required")
    private String description;

    @NotBlank(message = "Microservice Id is required")
    private String microserviceId;

    private List<String> env;

    private String status;

    public static ServiceEntity toEntity(ServiceModel serviceModel) {
        return ServiceEntity.builder()
                .id(serviceModel.getId())
                .description(serviceModel.getDescription())
                .microserviceId(serviceModel.getMicroserviceId())
                .name(serviceModel.getName())
                .serviceImage(serviceModel.getServiceImage())
                .status(serviceModel.getStatus())
                .env(serviceModel.getEnv())
                .serviceId(serviceModel.getServiceId())
                .build();
    }

    public static ServiceModel fromEntity(ServiceEntity serviceEntity) {
        return ServiceModel.builder()
                .id(serviceEntity.getId())
                .description(serviceEntity.getDescription())
                .microserviceId(serviceEntity.getMicroserviceId())
                .name(serviceEntity.getName())
                .serviceImage(serviceEntity.getServiceImage())
                .status(serviceEntity.getStatus())
                .env(serviceEntity.getEnv())
                .serviceId(serviceEntity.getServiceId())
                .build();
    }
}
