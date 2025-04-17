package com.manch.launchpad.services.impl;

import com.manch.launchpad.models.request.ServiceDependencyModel;
import com.manch.launchpad.models.request.ServiceModel;
import com.manch.launchpad.repositories.ServiceRepository;
import com.manch.launchpad.services.ServicesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ServicesServiceImpl implements ServicesService {
    ServiceRepository serviceRepository;

    @Override
    public ServiceModel createService(ServiceModel service) {
        log.info("Attempting to create a new service in local database: {}", service.toString());
        return ServiceModel.fromEntity(this.serviceRepository.save(ServiceModel.toEntity(service)));
    }

    @Override
    public ServiceModel updateService(ServiceModel service) {
        log.info("Attempting to update an existing service in local database: {}", service.toString());
        return ServiceModel.fromEntity(this.serviceRepository.update(ServiceModel.toEntity(service)));
    }

    @Override
    public ServiceModel getService(String id) {
        return ServiceModel.fromEntity(this.serviceRepository.findById(id));
    }

    @Override
    public ServiceDependencyModel createDependency(ServiceDependencyModel dependency) {
        log.info("Adding a dependency in microservice {} of model: {}", dependency.getMicroserviceId(), dependency.toString());
        return ServiceDependencyModel.fromEntity(serviceRepository.createDependency(ServiceDependencyModel.toEntity(dependency)));
    }

    @Override
    public void removeService(String id) {
        log.info("Attempting to remove a service of Id: {}", id);
        this.serviceRepository.delete(id);
    }

    @Override
    public void removeServiceByServiceId(String serviceId) {
        log.info("Attempting to remove a service of serviceId: {}", serviceId);
        this.serviceRepository.deleteServiceByServiceId(serviceId);
    }

    @Override
    public ServiceModel getServiceByServiceId(String serviceId) {
        return ServiceModel.fromEntity(this.serviceRepository.getServiceByServiceId(serviceId));
    }
}
