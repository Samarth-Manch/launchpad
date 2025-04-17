package com.manch.launchpad.services.impl;

import com.manch.launchpad.entities.PortEntity;
import com.manch.launchpad.models.request.PortModel;
import com.manch.launchpad.repositories.PortRepository;
import com.manch.launchpad.services.PortService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class PortServiceImpl implements PortService {
    PortRepository portRepository;

    @Override
    public PortModel createPort(PortModel portModel) {
        log.info("Creating port of the model: {}", portModel.toString());
        PortEntity portEntity = PortModel.toEntity(portModel);
        return PortModel.fromEntity(portRepository.save(portEntity));
    }

    @Override
    public PortModel getPort(String id) {
        return PortModel.fromEntity(portRepository.findById(id));
    }

    @Override
    public List<PortModel> getPortsByServiceId(String serviceId) {
        List<PortEntity> ports = portRepository.findAllByServiceId(serviceId);
        return ports.stream()
                .map(PortModel::fromEntity)
                .collect(Collectors.toList());
    }
}
