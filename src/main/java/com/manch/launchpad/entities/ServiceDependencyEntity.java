package com.manch.launchpad.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDependencyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable = false)
    private int id;

    @Column(name = "dependent_service_id", nullable = false)
    private String dependentServiceId;

    @Column(name = "required_service_id", nullable = false)
    private String requiredServiceId;

    @Column(name = "microservice_id", nullable = false)
    private String microserviceId;
}
