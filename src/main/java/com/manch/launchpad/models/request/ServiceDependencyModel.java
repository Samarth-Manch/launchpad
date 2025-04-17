package com.manch.launchpad.models.request;

import com.manch.launchpad.entities.ServiceDependencyEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDependencyModel {

    private int id;

    @NotBlank(message = "Dependent Service ID is required.")
    private String dependentServiceId;

    @NotBlank(message = "Required Service ID is required")
    private String requiredServiceId;

    @NotBlank(message = "Microservice ID is required")
    private Long microserviceId;

    public static ServiceDependencyModel fromEntity(ServiceDependencyEntity dependency) {
        return ServiceDependencyModel.builder()
                .id(dependency.getId())
                .dependentServiceId(dependency.getDependentServiceId())
                .requiredServiceId(dependency.getRequiredServiceId())
                .microserviceId(dependency.getMicroserviceId())
                .build();
    }

    public static ServiceDependencyEntity toEntity(ServiceDependencyModel dependency) {
        return ServiceDependencyEntity.builder()
                .id(dependency.getId())
                .dependentServiceId(dependency.getDependentServiceId())
                .requiredServiceId(dependency.getRequiredServiceId())
                .microserviceId(dependency.getMicroserviceId())
                .build();
    }

}
