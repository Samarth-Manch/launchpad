package com.manch.launchpad.services.impl;

import com.manch.launchpad.entities.ServiceEntity;
import com.manch.launchpad.models.request.ServiceModel;
import com.manch.launchpad.repositories.ServiceRepository;
import com.manch.launchpad.services.ServicesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ServicesServiceImpl implements ServicesService {
    ServiceRepository serviceRepository;

    @Override
    public ServiceModel createService(ServiceModel service) {
        return ServiceModel.fromEntity(this.serviceRepository.save(ServiceModel.toEntity(service)));
    }

    @Override
    public ServiceModel updateService(ServiceModel service) {
        return ServiceModel.fromEntity(this.serviceRepository.save(ServiceModel.toEntity(service)));
    }

    @Override
    public ServiceModel getService(String id) {
        return ServiceModel.fromEntity(this.serviceRepository.findById(id));
    }
}
