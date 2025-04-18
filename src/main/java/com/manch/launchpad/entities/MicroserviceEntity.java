package com.manch.launchpad.entities;

import com.manch.launchpad.enums.DeploymentServiceEnum;
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
@Table(name="microservice")
public class MicroserviceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable = false)
    private Integer id;

    @Column(name="microserviceName", nullable = false)
    private String microserviceName;

    @Column(name="user_id", nullable = false)
    private String userId;

    @Column(name="deployment", nullable = false)
    private DeploymentServiceEnum deployment;
}
