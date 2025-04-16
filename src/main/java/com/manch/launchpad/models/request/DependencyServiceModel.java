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
public class DependencyServiceModel {

    private int id;

    @NotBlank(message = "Dependent Service ID is required.")
    private String dependentServiceId;

    @NotBlank(message = "Required Service ID is required")
    private String requiredServiceId;

    public static DependencyServiceModel fromEntity(ServiceDependencyEntity dependency) {
        return DependencyServiceModel.builder()
                .id(dependency.getId())
                .dependentServiceId(dependency.getDependentServiceId())
                .requiredServiceId(dependency.getRequiredServiceId())
                .build();
    }

    public static ServiceDependencyEntity toEntity(DependencyServiceModel dependency) {
        return ServiceDependencyEntity.builder()
                .id(dependency.getId())
                .dependentServiceId(dependency.getDependentServiceId())
                .requiredServiceId(dependency.getRequiredServiceId())
                .build();
    }

}
