package com.manch.launchpad.repositories;

import com.manch.launchpad.entities.ServiceEntity;

public interface ServiceRepository {
    ServiceEntity save(ServiceEntity serviceEntity);
    ServiceEntity update(ServiceEntity serviceEntity);
    ServiceEntity findById(String id);
    void delete(String serviceId);
}