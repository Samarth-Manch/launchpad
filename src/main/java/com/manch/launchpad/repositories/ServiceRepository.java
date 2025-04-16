package com.manch.launchpad.repositories;

import com.manch.launchpad.entities.ServiceDependencyEntity;
import com.manch.launchpad.entities.ServiceEntity;

public interface ServiceRepository {
    ServiceEntity save(ServiceEntity serviceEntity);
    ServiceEntity update(ServiceEntity serviceEntity);
    ServiceEntity findById(String id);
    void deleteServiceByServiceId(String serviceId);
    void delete(String serviceId);
    ServiceDependencyEntity createDependency(ServiceDependencyEntity dependency);
}