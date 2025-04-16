package com.manch.launchpad.entities;

import com.manch.launchpad.utility.converters.StringListConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @Column(name="serviceId", insertable = true, updatable = false, nullable = true)
    private String serviceId;

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

    @Convert(converter = StringListConverter.class)
    @Column(name="env")
    private List<String> env;
}
