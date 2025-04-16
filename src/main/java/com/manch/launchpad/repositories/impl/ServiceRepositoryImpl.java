package com.manch.launchpad.repositories.impl;

import com.manch.launchpad.entities.MicroserviceEntity;
import com.manch.launchpad.entities.ServiceEntity;
import com.manch.launchpad.repositories.ServiceRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class ServiceRepositoryImpl implements ServiceRepository {
    private EntityManager entityManager;

    @Override
    @Transactional
    public ServiceEntity save(ServiceEntity serviceEntity) {
        entityManager.persist(serviceEntity);
        return serviceEntity;
    }

    @Override
    public ServiceEntity update(ServiceEntity serviceEntity) {
        entityManager.merge(serviceEntity);
        return serviceEntity;
    }

    @Override
    public ServiceEntity findById(String id) {
        return this.entityManager.createQuery("SELECT s FROM ServiceEntity s WHERE s.id = :id", ServiceEntity.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
