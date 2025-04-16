package com.manch.launchpad.services.impl;

import com.manch.launchpad.models.request.MicroserviceModel;
import com.manch.launchpad.repositories.MicroserviceRepository;
import com.manch.launchpad.services.MicroserviceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MicroserviceServiceImpl implements MicroserviceService {
    MicroserviceRepository microserviceRepository;

    @Override
    public MicroserviceModel createMicroservice(MicroserviceModel microserviceModel) {
        return MicroserviceModel.fromEntity(microserviceRepository.save(MicroserviceModel.toEntity(microserviceModel)));
    }

    @Override
    public MicroserviceModel getMicroserviceModel(Long microserviceId) {
        return MicroserviceModel.fromEntity(microserviceRepository.findById(microserviceId));
    }
}
