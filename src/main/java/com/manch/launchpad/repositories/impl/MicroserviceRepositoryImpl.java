package com.manch.launchpad.repositories.impl;

import com.manch.launchpad.commons.exceptions.LaunchpadException;
import com.manch.launchpad.commons.responses.ResponseInfoEnum;
import com.manch.launchpad.entities.MicroserviceEntity;
import com.manch.launchpad.entities.ServiceEntity;
import com.manch.launchpad.repositories.MicroserviceRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        List<MicroserviceEntity> microservice = this.entityManager.createQuery("SELECT ms FROM MicroserviceEntity ms WHERE ms.id = :id", MicroserviceEntity.class)
                .setParameter("id", id)
                .getResultList();

        if (microservice.isEmpty()) {
            throw new LaunchpadException(ResponseInfoEnum.NOT_FOUND, "Microservice of given ID does not exist", null);
        }

        return microservice.getFirst();
    }

    @Override
    public List<ServiceEntity> findServicesOfMicroservice(Long id) {
        return this.entityManager.createQuery("SELECT s FROM ServiceEntity s WHERE s.microserviceId = :id", ServiceEntity.class)
                .setParameter("id", id)
                .getResultList();
    }
}
