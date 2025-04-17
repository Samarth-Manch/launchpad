package com.manch.launchpad.services.impl;

import com.manch.launchpad.entities.ServiceEntity;
import com.manch.launchpad.enums.DeploymentServiceEnum;
import com.manch.launchpad.models.request.MicroserviceModel;
import com.manch.launchpad.models.request.ServiceDependencyModel;
import com.manch.launchpad.models.request.ServiceModel;
import com.manch.launchpad.repositories.MicroserviceRepository;
import com.manch.launchpad.services.MicroserviceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class MicroserviceServiceImpl implements MicroserviceService {
    MicroserviceRepository microserviceRepository;

    @Override
    public MicroserviceModel createMicroservice(MicroserviceModel microserviceModel) {
        log.info("Creating microservice of name: {}", microserviceModel.getMicroserviceName());
        return MicroserviceModel.fromEntity(microserviceRepository.save(MicroserviceModel.toEntity(microserviceModel)));
    }

    @Override
    public MicroserviceModel getMicroserviceModel(Long microserviceId) {
        return MicroserviceModel.fromEntity(microserviceRepository.findById(microserviceId));
    }

    @Override
    public MicroserviceModel updateMicroservice(MicroserviceModel microserviceModel) {
        log.info("Attempting to update microservice of this model: {}", microserviceModel.toString());
        return MicroserviceModel.fromEntity(microserviceRepository.update(MicroserviceModel.toEntity(microserviceModel)));
    }

    @Override
    public MicroserviceModel updateMicroserviceDeployment(Long microserviceId, DeploymentServiceEnum deployment) {
        log.info("Attempting to update deployment of microservice of this id: {}", microserviceId);
        MicroserviceModel microservice = this.getMicroserviceModel(microserviceId);
        microservice.setDeployment(deployment);
        return MicroserviceModel.fromEntity(microserviceRepository.update(MicroserviceModel.toEntity(microservice)));
    }

    @Override
    public List<ServiceDependencyModel> getServiceDependencies(Long microserviceId) {
        return microserviceRepository.findServicesOfMicroserviceDependency(microserviceId)
                .stream()
                .map(ServiceDependencyModel::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceModel> getServicesOfMicroservice(Long microserviceId) {
        List<ServiceEntity> serviceEntities = microserviceRepository.findServicesOfMicroservice(microserviceId);
        return serviceEntities.stream()
                .map(ServiceModel::fromEntity)
                .collect(Collectors.toList());
    }
}
