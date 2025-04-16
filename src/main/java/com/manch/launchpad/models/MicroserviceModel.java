package com.manch.launchpad.models;

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

    int id;

    @NotBlank(message = "Microservice name is required")
    String microserviceName;

    @NotBlank(message = "User ID is required")
    String userId;
}
