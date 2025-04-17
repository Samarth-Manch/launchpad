package com.manch.launchpad.repositories.impl;

import com.manch.launchpad.commons.exceptions.LaunchpadException;
import com.manch.launchpad.commons.responses.ResponseInfoEnum;
import com.manch.launchpad.entities.ServiceDependencyEntity;
import com.manch.launchpad.entities.ServiceEntity;
import com.manch.launchpad.repositories.ServiceRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    @Transactional
    public ServiceEntity update(ServiceEntity serviceEntity) {
        entityManager.createQuery("UPDATE ServiceEntity SET serviceId = :serviceId WHERE id = :id ")
                        .setParameter("serviceId", serviceEntity.getServiceId())
                        .setParameter("id", serviceEntity.getId())
                        .executeUpdate();
        return serviceEntity;
    }

    @Override
    public ServiceEntity findById(String id) {
        List<ServiceEntity> serviceEntities = this.entityManager.createQuery("SELECT s FROM ServiceEntity s WHERE s.id = :id", ServiceEntity.class)
                .setParameter("id", id)
                .getResultList();

        if (serviceEntities.isEmpty()) {
            throw new LaunchpadException(ResponseInfoEnum.NOT_FOUND, "Service with id " + id + " not found");
        }

        return serviceEntities.getFirst();
    }

    @Override
    @Transactional
    public void delete(String serviceId) {
        entityManager.createQuery(
                "DELETE FROM ServiceEntity s WHERE s.id = :id", ServiceEntity.class)
                        .setParameter("id", serviceId)
                        .executeUpdate();
    }

    @Override
    @Transactional
    public ServiceDependencyEntity createDependency(ServiceDependencyEntity dependency) {
        entityManager.persist(dependency);
        return dependency;
    }

    @Override
    public ServiceEntity getServiceByServiceId(String serviceId) {
        List<ServiceEntity> entities=  entityManager.createQuery(
                "SELECT s FROM ServiceEntity s WHERE s.serviceId = :serviceId", ServiceEntity.class)
                .setParameter("serviceId", serviceId)
                .getResultList();

        if (entities.isEmpty()) {
            throw new LaunchpadException(ResponseInfoEnum.NOT_FOUND, "Service with id " + serviceId + " not found");
        }

        return entities.getFirst();
    }

    @Override
    @Transactional
    public void deleteServiceByServiceId(String serviceId){
        entityManager.createQuery(
                        "DELETE FROM ServiceEntity s WHERE s.serviceId = :id", ServiceEntity.class)
                .setParameter("id", serviceId)
                .executeUpdate();

    }
}
