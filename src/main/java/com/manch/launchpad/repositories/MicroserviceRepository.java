package com.manch.launchpad.repositories;

import com.manch.launchpad.entities.MicroserviceEntity;
import com.manch.launchpad.entities.ServiceDependencyEntity;
import com.manch.launchpad.entities.ServiceEntity;

import java.util.List;

public interface MicroserviceRepository {
    MicroserviceEntity save(MicroserviceEntity microservice);
    MicroserviceEntity update(MicroserviceEntity microservice);
    MicroserviceEntity findById(Long id);
    List<ServiceEntity> findServicesOfMicroservice(Long id);
    List<ServiceDependencyEntity> findServicesOfMicroserviceDependency(Long microserviceId);
}
