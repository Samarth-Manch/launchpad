package com.manch.launchpad.repositories;

import com.manch.launchpad.entities.MicroserviceEntity;

public interface MicroserviceRepository {
    MicroserviceEntity save(MicroserviceEntity microservice);
    MicroserviceEntity update(MicroserviceEntity microservice);
    MicroserviceEntity findById(Long id);
}
