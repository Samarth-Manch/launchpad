package com.manch.launchpad.repositories.impl;

import com.manch.launchpad.entities.MicroserviceEntity;
import com.manch.launchpad.repositories.MicroserviceRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class MicroserviceRepositoryImpl implements MicroserviceRepository {
    private EntityManager entityManager;

    @Override
    @Transactional
    public MicroserviceEntity save(MicroserviceEntity microservice) {
        this.entityManager.persist(microservice);
        return microservice;
    }

    @Override
    @Transactional
    public MicroserviceEntity update(MicroserviceEntity microservice) {
        this.entityManager.merge(microservice);
        return microservice;
    }

    public MicroserviceEntity findById(Long id) {
        return this.entityManager.createQuery("SELECT ms FROM MicroserviceEntity ms WHERE ms.id = :id", MicroserviceEntity.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
