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
@Table(name="service")
public class ServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id", insertable = true, updatable = false, unique = true, nullable = false)
    private String id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="service_image", nullable = false)
    private String serviceImage;

    @Column(name="description", nullable = true)
    private String description;

    @Column(name="status", nullable = true)
    private String status;

    @Column(name="microserviceId", nullable = true)
    private String microserviceId;
}
