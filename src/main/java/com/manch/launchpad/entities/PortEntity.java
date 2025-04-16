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
@Table(name="port")
public class PortEntity {
    @Id
    @Column(name="id", insertable = true, updatable = false, unique = true, nullable = false)
    private String id;

    @Column(name="port_ip", nullable = false)
    private String portIp;

    @Column(name="private_port", nullable = false)
    private int privatePort;

    @Column(name="public_port", nullable = false)
    private int publicPort;

    @Column(name="service_id", nullable = false)
    private String serviceId;
}
