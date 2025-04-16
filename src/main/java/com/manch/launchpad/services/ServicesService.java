package com.manch.launchpad.services;

import com.manch.launchpad.entities.ServiceEntity;

public interface ServicesService {
    ServiceEntity createService(ServiceEntity service);
    ServiceEntity updateService(ServiceEntity service);
    ServiceEntity getService(String id);
}
