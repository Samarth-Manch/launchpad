package com.manch.launchpad.repositories;

import com.manch.launchpad.entities.PortEntity;

import java.util.List;

public interface PortRepository {
    PortEntity save(PortEntity entity);
    PortEntity findById(String id);
    List<PortEntity> findAllByServiceId(String serviceId);
}
