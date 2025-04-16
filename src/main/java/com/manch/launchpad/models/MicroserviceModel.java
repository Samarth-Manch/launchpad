package com.manch.launchpad.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MicroserviceModel {

    int id;

    @NotBlank(message = "Microservice name is required")
    String microserviceName;

    @NotBlank(message = "User ID is required")
    String userId;
}
