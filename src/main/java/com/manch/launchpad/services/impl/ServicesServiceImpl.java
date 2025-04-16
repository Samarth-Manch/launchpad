package com.manch.launchpad.services.impl;

import com.manch.launchpad.entities.ServiceEntity;
import com.manch.launchpad.repositories.ServiceRepository;
import com.manch.launchpad.services.ServicesService;

public class ServicesServiceImpl implements ServicesService {
    ServiceRepository serviceRepository;

    @Override
    public ServiceEntity createService(ServiceEntity service) {
        return this.serviceRepository.save(service);
    }

    @Override
    public ServiceEntity updateService(ServiceEntity service) {
        return this.serviceRepository.save(service);
    }

    @Override
    public ServiceEntity getService(String id) {
        return this.serviceRepository.findById(id);
    }
}
