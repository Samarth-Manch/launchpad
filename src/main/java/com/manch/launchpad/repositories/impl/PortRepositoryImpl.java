package com.manch.launchpad.repositories.impl;

import com.manch.launchpad.commons.exceptions.LaunchpadException;
import com.manch.launchpad.commons.responses.ResponseInfoEnum;
import com.manch.launchpad.entities.PortEntity;
import com.manch.launchpad.repositories.PortRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sound.sampled.Port;
import java.util.List;

@Repository
@AllArgsConstructor
public class PortRepositoryImpl implements PortRepository {
    EntityManager entityManager;

    @Override
    @Transactional
    public PortEntity save(PortEntity port) {
        this.entityManager.persist(port);
        return port;
    }

    @Override
    public PortEntity findById(String id) {
        List<PortEntity> portEntities = this.entityManager.createQuery(
                        "SELECT p FROM PortEntity p WHERE p.id = :id", PortEntity.class)
                .setParameter("id", id)
                .getResultList();

        if (portEntities.isEmpty()){
            throw new LaunchpadException(ResponseInfoEnum.NOT_FOUND, "Port with given id not found");
        }

        return portEntities.getFirst();
    }

    @Override
    public List<PortEntity> findAllByServiceId(String serviceId) {
        return this.entityManager.createQuery(
                        "SELECT p FROM PortEntity p WHERE p.serviceId = :serviceId", PortEntity.class)
                .setParameter("serviceId", serviceId)
                .getResultList();
    }
}
