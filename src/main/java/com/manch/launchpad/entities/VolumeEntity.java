package com.manch.launchpad.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VolumeEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name="volume_name", updatable = false, nullable = false, unique = true)
    private String volumeName;

    @Column(name="volume_source", nullable = false)
    private String volumeSource;

    @Column(name="volume_destination", nullable = false)
    private String volumeDestination;

    @Column(name="serviceId", nullable = false)
    private String serviceId;
}
