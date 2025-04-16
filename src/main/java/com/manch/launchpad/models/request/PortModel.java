package com.manch.launchpad.models.request;

import com.manch.launchpad.entities.PortEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PortModel {
    private String id;

    @NotBlank(message = "Port IP is required")
    private String portIp;

    @NotBlank(message = "Private Port is required")
    private int privatePort;

    @NotBlank(message = "Public Port Id is required")
    private int publicPort;

    @NotBlank(message = "Service Id is required")
    private String serviceId;

    public static PortEntity toEntity(PortModel portModel) {
        return PortEntity.builder()
                .id(portModel.getId())
                .portIp(portModel.getPortIp())
                .privatePort(portModel.getPrivatePort())
                .serviceId(portModel.getServiceId())
                .build();
    }

    public static PortModel fromEntity(PortEntity portEntity) {
        return PortModel.builder()
                .id(portEntity.getId())
                .portIp(portEntity.getPortIp())
                .privatePort(portEntity.getPrivatePort())
                .serviceId(portEntity.getServiceId())
                .build();
    }

}
